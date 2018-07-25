//Versi ke 6
package Controller;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import Kelas.*;
import java.util.*;

import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;


public class AuthenticationController implements Initializable{
    @FXML
    public List <Pengguna> pg = new ArrayList();
    @FXML
    public Label lbIncorrect;
    @FXML
    private AnchorPane cariBtn;
    @FXML
    private TextField tfCariAwal;
    @FXML
    private ImageView gmb5, gmb6, gmb7;
    
    //Method bantuan untuk memanggil fungsi di kelas pengguna, yang digunakan untuk
    //memeriksa apakah pengguna termasuk Alumni atau Admin atau tidak keduanya
    private String cekUserPass(int id, String un, String pw){
        String h=pg.get(id).bacaUserDanPass(id, un, pw);
        System.out.println("hasil: " + h);
        if(h.equalsIgnoreCase("Ad")){h="Admin";}
        else if (h.equalsIgnoreCase("Al")){h="Alumni";}
        else{System.out.println(h);}
    return h;}
    
    @FXML
    private void PindahHalaman(ActionEvent event, String halaman) throws IOException {
        Parent balikProfil_parent = FXMLLoader.load(getClass().getResource("/View/"+ halaman));
        Stage balikProfil_stage = new Stage();
        balikProfil_stage.setMaximized(true);
        Scene balikProfil_scene = new Scene(balikProfil_parent);
        balikProfil_stage.setScene(balikProfil_scene);
        ((javafx.scene.Node) event.getSource()).getScene().getWindow().hide();
        balikProfil_stage.show();}
    
    public void bacaData() throws Exception{
        String Nama, findAlumni, DataAlumni, un, pw;
        int id;
        
        //Persiapan membaca File XML
        File fileXML = new File("xmlfindAlumni.xml");
        DocumentBuilderFactory produsen = DocumentBuilderFactory.newInstance();
        DocumentBuilder pembuat = produsen.newDocumentBuilder();
        Document dok = pembuat.parse(fileXML);
        dok.getDocumentElement().normalize();
        
        //Membaca tag utama (opsional)
        findAlumni = dok.getDocumentElement().getNodeName();
        
        //Menginisialisasi tag yang akan dibaca
        NodeList listXML = dok.getElementsByTagName("DataAlumni");
        
        for(int i=0; i < listXML.getLength(); i++){
            //Membuat node (atribut) yang akan dibaca (di contoh ada 5 node)
            Node nodeXML = listXML.item(i);
            
            //Membaca tiap node (atribut) (opsional)
            DataAlumni = nodeXML.getNodeName();
            
            //Mengambil node untuk tiap iterasi (5 node = 5 iterasi)
            if(nodeXML.getNodeType() == Node.ELEMENT_NODE){
                Element elemenku = (Element) nodeXML;
                
                //Memindahkan ke variabel sementara
                id = Integer.parseInt(elemenku.getAttribute("id"));
                Nama = elemenku.getElementsByTagName("Nama").item(0).getTextContent();
                un = elemenku.getElementsByTagName("Username").item(0).getTextContent();
                pw = elemenku.getElementsByTagName("Password").item(0).getTextContent();
                
                //Memasukkan data yang didapat ke List
                pg.add(new Pengguna(id, un, pw));
                pg.get(i).tambahAlumni(id,Nama,un,pw,"","","","","","","","","","","","","","",LocalDate.parse("1111-11-11"));
            }
        }
    }
    
    //Action yang digunakan untuk perpindahan scene dari homepage ke halaman login sebagai admin
    //Lanjutan dari "LoginAsAdmin", action ini digunakan untuk pindah scene ke homepage
    //admin jika login berhasil. Jika tidak maka, akan muncul label di bawahnya bahwa terjadi kesalahan    
    @FXML
    public void LoginAsAdmin(ActionEvent event) throws IOException{
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");
        dialog.setHeaderText("You must login first!");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        javafx.scene.Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
        loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            String un = usernamePassword.getKey();
            String pw = usernamePassword.getValue();
            if(un.length()>=6){
                String id = String.valueOf(un.charAt(0));
                for(int m=0;m<pg.size();m++){
                    if(id.equals(String.valueOf(m))){
                        int Id = Integer.parseInt(id);
                        String hasil = this.cekUserPass(Id, un, pw);
                        if(hasil.equals("Admin")){
                            try {
                                PindahHalaman(event, "admin1.fxml");
                                break;
                            } catch (IOException ex) { Logger.getLogger(AuthenticationController.class.getName()).log(Level.SEVERE, null, ex);}
                        }else{lbIncorrect.setText(hasil);} 
                    }else if(m==pg.size() && String.valueOf(m)!=id){lbIncorrect.setText("Username or Password incorrect");}
                }                
            }else{
                if(un.equals("") && pw.equals("")){ lbIncorrect.setText("Please fill the Username or Password");}
                else{lbIncorrect.setText("Username or Password incorrect");}
            }
        });
    }
    
    @FXML
    public void SearchButton(ActionEvent event) throws Exception{ PindahHalaman(event, "cariNama.fxml");}
    
    @FXML
    public void CancelButtonAlumni(ActionEvent event) throws IOException{ PindahHalaman(event, "editProfil.fxml");}
    
    @FXML
    public void LogoutButton(ActionEvent event) throws IOException{ PindahHalaman(event, "homepage.fxml");}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            bacaData();
        } catch (Exception ex) {
            Logger.getLogger(AuthenticationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

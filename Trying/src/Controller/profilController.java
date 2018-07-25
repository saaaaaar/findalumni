//VERSI ke 6
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
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class profilController implements Initializable{
    public List <Pengguna> pg = new ArrayList();
    public manajemenController MC = new manajemenController();
    public editController EC = new editController();
    
    @FXML
    public Label idAlumni,lbDOB,lbNationality,lbOccupation,lbCompany,lbPosition,lbNoHp,
            lbEmail,lbAddress,lbFullname,lbDescription,lbExperience,lbMajor,lbAttGrad,lbSkills,lbSMP,lbSMA,lbKuliah;
    
    public void bacaData() throws Exception{
        String Nama, findAlumni, DataAlumni, Username, Pass, Jurusan, Tingkat, Skill, Usaha, Jabatan,
                Alamat, Deskripsi, Email, Yog, Kebangsaan, JHS, SHS, College, Phone, POB, Occupation, Bahasa;
        LocalDate DOB;
        int id;
        
        //Persiapan membaca File XML
        File fileXML = new File("xmlfindAlumni.xml");
        DocumentBuilderFactory produsen = DocumentBuilderFactory.newInstance();
        DocumentBuilder pembuat = produsen.newDocumentBuilder();
        Document dok = pembuat.parse(fileXML);
        dok.getDocumentElement().normalize();
        
        findAlumni = dok.getDocumentElement().getNodeName();
        NodeList listXML = dok.getElementsByTagName("DataAlumni");
        
        for(int i=0;i<listXML.getLength(); i++){
            Node nodeXML = listXML.item(i);
            DataAlumni = nodeXML.getNodeName();
            if(nodeXML.getNodeType() == Node.ELEMENT_NODE){
                Element elemenku = (Element) nodeXML;

                id = Integer.parseInt(elemenku.getAttribute("id"));
                Nama = elemenku.getElementsByTagName("Nama").item(0).getTextContent();
                Username = elemenku.getElementsByTagName("Username").item(0).getTextContent();
                Pass = elemenku.getElementsByTagName("Password").item(0).getTextContent();
                Jurusan = elemenku.getElementsByTagName("Jurusan").item(0).getTextContent();
                Tingkat = elemenku.getElementsByTagName("Tingkat").item(0).getTextContent();
                Yog = elemenku.getElementsByTagName("YearsOfGrad").item(0).getTextContent();
                Deskripsi = elemenku.getElementsByTagName("Deskripsi").item(0).getTextContent();
                Email = elemenku.getElementsByTagName("Email").item(0).getTextContent();
                Alamat = elemenku.getElementsByTagName("Alamat").item(0).getTextContent();
                Kebangsaan = elemenku.getElementsByTagName("Kebangsaan").item(0).getTextContent();
                JHS = elemenku.getElementsByTagName("JHS").item(0).getTextContent();
                SHS = elemenku.getElementsByTagName("SHS").item(0).getTextContent();
                College = elemenku.getElementsByTagName("College").item(0).getTextContent();
                POB = elemenku.getElementsByTagName("POB").item(0).getTextContent();
                Phone = elemenku.getElementsByTagName("Phone").item(0).getTextContent();
                Occupation = elemenku.getElementsByTagName("Occupation").item(0).getTextContent();
                Bahasa = elemenku.getElementsByTagName("Bahasa").item(0).getTextContent();
                DOB = LocalDate.parse(elemenku.getElementsByTagName("DOB").item(0).getTextContent());

                pg.add(new Pengguna(id, Username, Pass));
                pg.get(i).tambahAlumni(id,Nama,Username,Pass,Jurusan,Yog,"S1", 
                        Deskripsi,Kebangsaan,Email,Alamat,JHS,SHS,College,POB,Phone,Occupation,Bahasa,DOB);
                
                //Membaca bagian kemampuan 
                NodeList listKemp = elemenku.getElementsByTagName("Kemampuan");
                Element elemenkuu = (Element) elemenku;
                NodeList listJenisKemp = elemenkuu.getElementsByTagName("JenisKemampuan");
                for(int n=0;n<listJenisKemp.getLength();n++){
                    Node nodeJenisKemp = listJenisKemp.item(n);
                    if(nodeJenisKemp.getNodeType() == Node.ELEMENT_NODE){
                        Element elemenkuuu = (Element) elemenku;
                        Skill = elemenkuuu.getElementsByTagName("JenisKemampuan").item(0).getTextContent();
                        pg.get(i).tambahKemampuan(id, n, Skill);
                    }
                }
                
                //Membaca bagian perusahaan dan jabatan yang dilakukan Alumni
                NodeList listPrsh = elemenku.getElementsByTagName("Perusahaan");
                Element elemenkuuu = (Element) elemenku;
                NodeList listPerusahaan = elemenkuuu.getElementsByTagName("NamaPerusahaan");
                for(int m=0;m<listPerusahaan.getLength();m++){
                    Node nodePerh = listPerusahaan.item(m);
                    if(nodePerh.getNodeType() == Node.ELEMENT_NODE){
                        Element elemenkuuuu = (Element) elemenkuuu;
                        Usaha = elemenkuuuu.getElementsByTagName("NamaPerusahaan").item(m).getTextContent();
                        pg.get(i).tambahPerusahaan(id, m, Usaha);
                        
                        NodeList listJabatan = elemenkuuuu.getElementsByTagName("NamaJabatan");
                        Node nodeJbt = listJabatan.item(0);
                        if(nodeJbt.getNodeType() == Node.ELEMENT_NODE){
                            Element elemenkuuuuu = (Element) elemenkuuuu;
                            Jabatan = elemenkuuuuu.getElementsByTagName("NamaJabatan").item(0).getTextContent();
                            pg.get(i).tambahJabatan(id, m, 0, Jabatan);
                        }
                    }
                }
            }
        }
    }
    private void setSchool(String ambil, Label lb){
        int urutan = 0, ke = 0;
        String hasil = "";
        for(int j=0;j<ambil.length();j++){
            if(ambil.charAt(j) == ',' || j == ambil.length()-1){
                if(j == ambil.length()-1){hasil = hasil + ambil.substring(ke, j-1) + "\n";}
                else{hasil = hasil + ambil.substring(ke,j+1) + "\n";
                }ke = j+1;}
            }
        lb.setText(hasil);
    }
    
    @FXML
    public void bacaHistory()throws Exception{
        String Nama, History, hasilKemampuan = "", hasilPerusahaan = "";
        
        //Persiapan membaca File XML
        File fileXML = new File("xmlHistory.xml");
        DocumentBuilderFactory produsen = DocumentBuilderFactory.newInstance();
        DocumentBuilder pembuat = produsen.newDocumentBuilder();
        Document dok = pembuat.parse(fileXML);
        dok.getDocumentElement().normalize();
        
        //Membaca tag utama (opsional)
        History = dok.getDocumentElement().getNodeName();
        
        //Menginisialisasi tag yang akan dibaca
        Nama = dok.getElementsByTagName("Laman").item(0).getTextContent();
        idAlumni.setText(Nama);
        int id = Integer.parseInt(Nama);
        
        for(int i=0;i<pg.size();i++){
            if(id==i){
                lbFullname.setText(pg.get(i).getAlumniName(i).toUpperCase());
                lbNationality.setText(pg.get(i).getKebangsaan(i)); 
                lbEmail.setText(pg.get(i).getEmail(i));  
                lbAddress.setText(pg.get(i).getAlamat(i));
                lbDescription.setText(pg.get(i).getDeskirpsi(i));
                lbMajor.setText(pg.get(i).getJurusan(i) + " - " + pg.get(i).getTingkat(i));
                lbAttGrad.setText(pg.get(i).getYOG(i));
                lbDOB.setText(pg.get(i).getDOB(i).toString());
                lbNoHp.setText(pg.get(i).getPhone(i));
                
                EC.setSchoolLabel(pg.get(i).getOccupation(i), lbOccupation, lbCompany, lbPosition);                
                setSchool(pg.get(i).getJHS(i), lbSMP);
                setSchool(pg.get(i).getSHS(i), lbSMA);
                setSchool(pg.get(i).getCollege(i), lbKuliah);
                
                for(int j=0;j<Integer.parseInt(pg.get(i).getPanjangKemampuan(i));j++){
                    if(pg.get(i).getKemampuan(i,j)!=""){
                        hasilKemampuan = hasilKemampuan + pg.get(i).getKemampuan(i,j) + ", ";}}
                lbSkills.setText(hasilKemampuan);
                
                for(int j=0;j<Integer.parseInt(pg.get(i).getPanjangPerusahaan(i));j++){
                    if(pg.get(i).getPerusahaan(i,j)!="" && pg.get(i).getJabatan(i,j,j)!=""){
                        hasilPerusahaan = hasilPerusahaan + pg.get(i).getPerusahaan(i,j) + 
                            "\n" + pg.get(i).getJabatan(i,j,j) + "\n\n";}}
                lbExperience.setText(hasilPerusahaan);
            }
        }
    }
    
    @FXML
    public void homepageButton(ActionEvent event) throws Exception{ MC.SearchButton(event);}
    
    @FXML
    public void editprofilalumni (ActionEvent event){
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
                for(int i=0;i<pg.size();i++){
                    if(lbFullname.getText().equalsIgnoreCase(pg.get(i).getAlumniName(i))){
                        String idNama = pg.get(i).getUser();
                        String kunci = pg.get(i).getPassword();
                        if(un.equals(idNama) && pw.equals(kunci)){
                                try {
                                    Parent admin1_parent = FXMLLoader.load(getClass().getResource("/View/AlumnieditProfil.fxml"));
                                    Stage admin1_stage = new Stage();
                                    admin1_stage.setMaxHeight(722.0);
                                    Scene admin1_scene = new Scene(admin1_parent);
                                    admin1_stage.setScene(admin1_scene);
                                    ((javafx.scene.Node) event.getSource()).getScene().getWindow().hide();
                                    admin1_stage.show();
                                    break;
                                } catch (IOException ex) {
                                    Logger.getLogger(profilController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                        }else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("Username or Password incorrect!" + "\n" + "Try Again");
                            alert.showAndWait();
                            break;
                        }
                    }
                }
            }
        });      
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            bacaData();
            bacaHistory();
        } catch (Exception ex) {
            Logger.getLogger(profilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

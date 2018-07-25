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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

//pengelolaan data alumni
public class manajemenController implements Initializable{
    @FXML
    private TextField txFullname, txNationality, txPOB, txYOG, 
            txLanguage, txPhone, txMajor, txEmail, txAddress, txOccupation, txAwards, txNamaSMP, txNamaSMA, txNamaKuliah, 
            txAlamatSMP, txAlamatSMA, txAlamatKuliah,txSkill, txTahunSMP, txTahunSMA, txTahunKuliah,
            txtPerusahaanSekarang, txPositionSekarang, txtExperience, txtLocExperience,
            txUsername, txPassword;
    
    @FXML
    private TextArea txDesc;
    @FXML
    private Label idAlumni;
    @FXML
    private DatePicker txDOB;
    
    @FXML
    public List <Pengguna> pg = new ArrayList();

    public AuthenticationController AC = new AuthenticationController();
    
    @FXML
    public void LogOutButton(ActionEvent event) throws IOException{ AC.LogoutButton(event);}

    public void simpanData() throws Exception{        
        //Persiapan pembuatan File XML
        DocumentBuilderFactory produsen = DocumentBuilderFactory.newInstance();
        DocumentBuilder pembuat = produsen.newDocumentBuilder();
        Document dokumen = pembuat.newDocument();
        dokumen.setXmlStandalone(true);
        Element rootElement = dokumen.createElement("findAlumni");
        dokumen.appendChild(rootElement);
       
        //Tulis XML (Membangun data XML dari List)
        for(int i=0; i <pg.size();i++){
            Element elemenAlm = dokumen.createElement("DataAlumni");
            elemenAlm.setAttribute("id",""+pg.get(i).getIdAl(i));
            rootElement.appendChild(elemenAlm);
            
            Element elemenNama = dokumen.createElement("Nama");
            elemenNama.setTextContent(pg.get(i).getAlumniName(i));
            elemenAlm.appendChild(elemenNama);
            
            Element elemenYOG = dokumen.createElement("YearsOfGrad");
            elemenYOG.setTextContent(pg.get(i).getYOG(i));
            elemenAlm.appendChild(elemenYOG);
            
            Element elemenDesc = dokumen.createElement("Deskripsi");
            elemenDesc.setTextContent(pg.get(i).getDeskirpsi(i));
            elemenAlm.appendChild(elemenDesc);
            
            Element elemenAlamat = dokumen.createElement("Alamat");
            elemenAlamat.setTextContent(pg.get(i).getAlamat(i));
            elemenAlm.appendChild(elemenAlamat);
            
            Element elemenBangsa = dokumen.createElement("Kebangsaan");
            elemenBangsa.setTextContent(pg.get(i).getKebangsaan(i));
            elemenAlm.appendChild(elemenBangsa);
            
            Element elemenEmail = dokumen.createElement("Email");
            elemenEmail.setTextContent(pg.get(i).getEmail(i));
            elemenAlm.appendChild(elemenEmail);
            
            Element elemenJurusan = dokumen.createElement("Jurusan");
            elemenJurusan.setTextContent(pg.get(i).getJurusan(i));
            elemenAlm.appendChild(elemenJurusan);
            
            Element elemenTingkat = dokumen.createElement("Tingkat");
            elemenTingkat.setTextContent(pg.get(i).getTingkat(i));
            elemenAlm.appendChild(elemenTingkat);
            
            Element elemenJHS = dokumen.createElement("JHS");
            elemenJHS.setTextContent(pg.get(i).getJHS(i));
            elemenAlm.appendChild(elemenJHS);
            
            Element elemenSHS = dokumen.createElement("SHS");
            elemenSHS.setTextContent(pg.get(i).getSHS(i));
            elemenAlm.appendChild(elemenSHS);
            
            Element elemenCollege = dokumen.createElement("College");
            elemenCollege.setTextContent(pg.get(i).getCollege(i));
            elemenAlm.appendChild(elemenCollege);
            
            Element elemenBahasa = dokumen.createElement("Bahasa");
            elemenBahasa.setTextContent(pg.get(i).getBahasa(i));
            elemenAlm.appendChild(elemenBahasa);
            
            Element elemenPhone = dokumen.createElement("Phone");
            elemenPhone.setTextContent(pg.get(i).getPhone(i));
            elemenAlm.appendChild(elemenPhone);
            
            Element elemenPOB = dokumen.createElement("POB");
            elemenPOB.setTextContent(pg.get(i).getPOB(i));
            elemenAlm.appendChild(elemenPOB);
            
            Element elemenOccupation = dokumen.createElement("Occupation");
            elemenOccupation.setTextContent(pg.get(i).getOccupation(i));
            elemenAlm.appendChild(elemenOccupation);
            
            Element elemenDOB = dokumen.createElement("DOB");
            elemenDOB.setTextContent(String.valueOf(pg.get(i).getDOB(i)));
            elemenAlm.appendChild(elemenDOB);
            
            Element elemenUname = dokumen.createElement("Username");
            elemenUname.setTextContent(pg.get(i).getUser());
            elemenAlm.appendChild(elemenUname);
            
            Element elemenPassword = dokumen.createElement("Password");
            elemenPassword.setTextContent(pg.get(i).getPassword());
            elemenAlm.appendChild(elemenPassword);
            
            String ambil_3 = pg.get(i).getPanjangKemampuan(i);
            String ambil = pg.get(i).getPanjangPerusahaan(i);
            
            Element elemenKemampuan = dokumen.createElement("Kemampuan");
            elemenKemampuan.setAttribute("Jlk",ambil_3);
            elemenAlm.appendChild(elemenKemampuan);
            if(ambil_3!=""){
                for(int j=0;j<Integer.parseInt(ambil_3);j++){
                    if(pg.get(i).getIdK(i,j)!=""){
                        Element elemenJenisKemampuan = dokumen.createElement("JenisKemampuan");
                        elemenJenisKemampuan.setAttribute("idK",pg.get(i).getIdK(i,j));
                        elemenJenisKemampuan.setTextContent(pg.get(i).getKemampuan(i,j));
                        elemenKemampuan.appendChild(elemenJenisKemampuan);
                    } 
                }
            }           
            
            Element elemenPerusahaan = dokumen.createElement("Perusahaan");
            elemenPerusahaan.setAttribute("Jlp",ambil);
            elemenAlm.appendChild(elemenPerusahaan);
            if(ambil!=""){
                for(int k=0;k<Integer.parseInt(ambil);k++){
                    //Bagian Perusahaan
                    if(pg.get(i).getIdP(i,k)!=""){
                        Element elemenNamaPerusahaan = dokumen.createElement("NamaPerusahaan");
                        elemenNamaPerusahaan.setAttribute("idP",""+pg.get(i).getIdP(i,k));
                        elemenNamaPerusahaan.setTextContent(pg.get(i).getPerusahaan(i,k));
                        elemenPerusahaan.appendChild(elemenNamaPerusahaan);

                    //Bagian Jabatan
                    if(pg.get(i).getIdJ(i ,k, k)!=""){
                        Element elemenNamaJabatan = dokumen.createElement("NamaJabatan");
                        elemenNamaJabatan.setAttribute("idJ",""+pg.get(i).getIdJ(i,k,k));
                        elemenNamaJabatan.setTextContent(pg.get(i).getJabatan(i,k,k)); //alumni,usahaKe,jabatanKe
                        elemenNamaPerusahaan.appendChild(elemenNamaJabatan);} 
                    }
                }
            }
        }
         //Membuat File XML 
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer tr = tf.newTransformer();
        DOMSource dom = new DOMSource(dokumen);
        StreamResult hasil = new StreamResult(new File ("xmlfindAlumni.xml"));
        tr.transform(dom, hasil);
        
        System.out.println("File berhasil dibuat.");
    }
    public void tampil(){
        for(int i=0;i<pg.size();i++){
            System.out.println("id:" + pg.get(i).getIdAl(i));
            System.out.println("Nama: " + pg.get(i).getAlumniName(i));
            System.out.println("Kemampuan: " + pg.get(i).getPanjangKemampuan(i));
            for(int j=0;j<Integer.parseInt(pg.get(i).getPanjangKemampuan(i));j++){
                System.out.println("Jenis Kemampuan: " + pg.get(i).getKemampuan(i,j));
            }
            System.out.println("Perusahaan: " + pg.get(i).getPanjangPerusahaan(i));
            for(int j=0;j<Integer.parseInt(pg.get(i).getPanjangPerusahaan(i));j++){
                System.out.println("Nama Perusahaan:" + pg.get(i).getPerusahaan(i,j));
                System.out.println("Nama Jabatan: " + pg.get(i).getJabatan(i,j,j));
            }
        }
    }
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
                pg.get(i).tambahAlumni(id,Nama,Username,Pass,Jurusan,"S1", 
                        Yog,Deskripsi,Kebangsaan,Email,Alamat,JHS,SHS,College,POB,Phone,Occupation,Bahasa,DOB);
                
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
                        for(int n=0;n<listJabatan.getLength();n++){
                            Node nodeJbt = listJabatan.item(n);
                            if(nodeJbt.getNodeType() == Node.ELEMENT_NODE){
                                Element elemenkuuuuu = (Element) elemenkuuuu;
                                Jabatan = elemenkuuuuu.getElementsByTagName("NamaJabatan").item(0).getTextContent();
                                pg.get(i).tambahJabatan(id, m, n, Jabatan);
                            }
                        }
                    }
                }
            }
        }
        this.tampil();
    }
    public void SearchButtonD(ActionEvent event, String ambil) throws Exception{
        boolean check = true;
        if(ambil == "admineditProfil.fxml"){check = false;}
        
        Parent EditProfil_parent = FXMLLoader.load(getClass().getResource("/View/" + ambil));
        Stage EditProfil_stage =  new Stage();
        EditProfil_stage.setMaximized(check);
        Scene EditProfil_scene = new Scene(EditProfil_parent);
        EditProfil_stage.setScene(EditProfil_scene);
        EditProfil_stage.show();
        ((javafx.scene.Node) event.getSource()).getScene().getWindow().hide();}
    
    @FXML
    public void NewAlumniButton(ActionEvent event) throws Exception{ SearchButtonD(event, "admineditProfil.fxml");}
    
    @FXML
    public void SimpanDataButton(ActionEvent event) throws Exception{
        String Nama, User, Pass, Jurusan, Kemampuan, LocEx, Experience,
                Kebangsaan, Yog, Tingkat, Desk, Email, Alamat, JHS, SHS, College,
                Phone, POB, Occupation, Bahasa, UserBaru = "";
        LocalDate DOB;
        
        int id = pg.size();
        Nama = txFullname.getText();
        
        for(int i=0;i<6;i++){
            if(Nama.charAt(i) == ' '){i++;}
            UserBaru = UserBaru + Nama.charAt(i);}
        User = String.valueOf(id) + UserBaru ;
        
        if(Nama.length() < 8){ Pass = Nama;}
        else{ Pass = Nama.substring(0,7);}
        
        Jurusan = txMajor.getText();
        Kemampuan = txSkill.getText();
        Experience = txtExperience.getText(); //Penggantinya experience biar lebih fleksibel
        LocEx = txtLocExperience.getText(); //Pengganti bagian Keterangan dari experience. sengaja biar namanya ngk ganti banyak
        Kebangsaan = txNationality.getText();
        Yog = txYOG.getText();
        Desk = txDesc.getText();
        Email = txEmail.getText();
        Alamat = txAddress.getText();
        JHS = txNamaSMP.getText() + "," + txTahunSMP.getText() + "," + txAlamatSMP.getText();
        SHS = txNamaSMA.getText() + "," + txTahunSMA.getText() + "," + txAlamatSMA.getText();
        College = txNamaKuliah.getText() + "," + txTahunKuliah.getText() + "," + txAlamatKuliah.getText();
        Phone = txPhone.getText();
        Bahasa = txLanguage.getText();
        Occupation = txOccupation.getText() + "," + txtPerusahaanSekarang.getText() + "," + txPositionSekarang.getText();
        DOB = txDOB.getValue();
        POB = txPOB.getText();
        
        if(Nama.equals("") || User.equals("") || Pass.equals("") || Jurusan.equals("") || 
            Desk.equals("") || Alamat.equals("") || Email.equals("")){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Some areas not fill yet!" + "\n" + "Please check the form!");
            alert.showAndWait();            
        }else{
            Alert Confalert = new Alert(Alert.AlertType.CONFIRMATION);
            Confalert.setTitle("Message from page");
            Confalert.setHeaderText("Make sure your data is valid.");
            Confalert.setContentText("Do you want to save data?");

            Optional<ButtonType> result = Confalert.showAndWait();
            if (result.get() == ButtonType.OK){
                //Admin klik OK
                pg.add(new Pengguna(id, User, Pass));
                pg.get(id).tambahAlumni(id,Nama,User,Pass,Jurusan,"S1", 
                        Yog,Desk,Kebangsaan,Email,Alamat,JHS,SHS,College,POB,Phone,Occupation,Bahasa,DOB);
                pg.get(id).tambahKemampuan(id,0,Kemampuan);
                pg.get(id).tambahPerusahaan(id,0,Experience);
                pg.get(id).tambahJabatan(id,0,0,LocEx);
                simpanData();
            
                SearchButtonD(event, "admin1.fxml");
            }
        }
    }
    
    @FXML
    public void SearchButton(ActionEvent event) throws Exception{ SearchButtonD(event, "cariNama.fxml");}
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            bacaData();
        } catch (Exception ex) {
            Logger.getLogger(manajemenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


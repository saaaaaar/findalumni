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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

public class inisiasiController implements Initializable{
    @FXML
    List <Pengguna> pg = new ArrayList();
    @FXML
    private TextField tfCariAwal, tfCari, txIDAlumni;
    @FXML
    private Label lbID,lbName,lbReference;
    @FXML
    private ComboBox cbSearch;
    @FXML
    ObservableList<String> pilihan = FXCollections.observableArrayList("Name", "Company", "Position", "Skill", "Major");
    
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
    }
    public void cariNama(String namaA){
        String aw, ak, hasilID = "", hasilNama = "";
        int b = namaA.length();
        int ad;
        
        aw = String.valueOf(namaA.charAt(0)); //membaca karakter pertama input cari
        ak = String.valueOf(namaA.charAt(b-1)); //membaca krakter terakhir inputan cari
        for(int i=0;i<pg.size();i++){
            String ambil = pg.get(i).getAlumniName(i);
            for(int j=0;j<ambil.length();j++){
                ad = j+b-1;
                if(ad>=ambil.length()){break;}
                else{
                    if(String.valueOf(ambil.charAt(j)).equalsIgnoreCase(aw)){
                        if(String.valueOf(ambil.charAt(ad)).equalsIgnoreCase(ak)){
                            String h = ambil.substring(j, j+b);    
                            if(h.equalsIgnoreCase(namaA)){
                                hasilID = hasilID + pg.get(i).getIdAl(i) + "\n";
                                hasilNama = hasilNama + ambil + "\n";
                            break;}
                        }
                    }
                }
            }
        }   
        lbID.setText(hasilID);
        lbName.setText(hasilNama);
    }
    public void cariJabatan(String namaJ){
        String aw, ak, hasilID = "", hasilNama = "", hasilJabatan = "";
        int b = namaJ.length();
        int ad;
        
        aw = String.valueOf(namaJ.charAt(0)); //membaca karakter pertama input cari
        ak = String.valueOf(namaJ.charAt(b-1)); //membaca krakter terakhir inputan cari
        for(int i=0;i<pg.size();i++){ //Panjang alumni
            for(int j=0;j<Integer.parseInt(pg.get(i).getPanjangPerusahaan(i));j++){ //Panjang Perusahaan
                String ambil = pg.get(i).getJabatan(i,j,j);
                    for(int n=0;n<ambil.length();n++){
                        ad = j+b-1;
                        if(ad>=ambil.length()){break;}
                        else{
                            if(String.valueOf(ambil.charAt(j)).equalsIgnoreCase(aw)){
                                if(String.valueOf(ambil.charAt(ad)).equalsIgnoreCase(ak)){
                                    String h = ambil.substring(j, j+b);    
                                    if(h.equalsIgnoreCase(namaJ)){
                                        hasilID = hasilID + pg.get(i).getIdAl(i) + "\n";
                                        hasilJabatan = hasilJabatan + ambil + "\n";
                                        hasilNama = hasilNama + pg.get(i).getAlumniName(i) + "\n";
                                        break;}
                                }
                            }
                        }
                    }
                }
            }
        lbID.setText(hasilID);
        lbName.setText(hasilNama);
        lbReference.setText(hasilJabatan);
        }
    public void cariKemampuan(String namaK){
        String aw, ak, hasilID = "", hasilNama = "", hasilKemampuan = "";
        int b = namaK.length();
        int ad;
        
        aw = String.valueOf(namaK.charAt(0)); //membaca karakter pertama input cari
        ak = String.valueOf(namaK.charAt(b-1)); //membaca krakter terakhir inputan cari
        for(int i=0;i<pg.size();i++){
            for(int m=0;m<Integer.parseInt(pg.get(i).getPanjangKemampuan(i));m++){ //perulangan membaca banyak kemampuan alumni
                String ambil = pg.get(i).getKemampuan(i,m);
                for(int j=0;j<ambil.length();j++){ //perulangan untuk membaca karater kemampuan
                    ad = j+b-1;
                    if(ad>=ambil.length()){break;}
                    else{
                        if(String.valueOf(ambil.charAt(j)).equalsIgnoreCase(aw)){
                            if(String.valueOf(ambil.charAt(ad)).equalsIgnoreCase(ak)){
                                String h = ambil.substring(j, j+b);    
                                if(h.equalsIgnoreCase(namaK)){
                                    hasilID = hasilID + pg.get(i).getIdAl(i) + "\n";
                                    hasilNama = hasilNama + pg.get(i).getAlumniName(i) + "\n";
                                    hasilKemampuan = hasilKemampuan + ambil + "\n";
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }   
        lbID.setText(hasilID);
        lbName.setText(hasilNama);
        lbReference.setText(hasilKemampuan);
    }
    public void cariPerusahaan(String namaP){
        String aw, ak, hasilID = "", hasilNama = "", hasilPerusahaan = "";
        int b = namaP.length();
        int ad;
        
        aw = String.valueOf(namaP.charAt(0)); //membaca karakter pertama input cari
        ak = String.valueOf(namaP.charAt(b-1)); //membaca krakter terakhir inputan cari
        for(int i=0;i<pg.size();i++){
            for(int m=0;m<Integer.parseInt(pg.get(i).getPanjangPerusahaan(i));m++){ //perulangan membaca banyak kemampuan alumni
                String ambil = pg.get(i).getPerusahaan(i,m);
                for(int j=0;j<ambil.length();j++){ //perulangan untuk membaca karater kemampuan
                    ad = j+b-1;
                    if(ad>=ambil.length()){break;}
                    else{
                        if(String.valueOf(ambil.charAt(j)).equalsIgnoreCase(aw)){
                            if(String.valueOf(ambil.charAt(ad)).equalsIgnoreCase(ak)){
                                String h = ambil.substring(j, j+b);    
                                if(h.equalsIgnoreCase(namaP)){
                                    hasilID = hasilID + pg.get(i).getIdAl(i) + "\n";
                                    hasilNama = hasilNama + pg.get(i).getAlumniName(i) + "\n";
                                    hasilPerusahaan = hasilPerusahaan + ambil + "\n";
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }   
        lbID.setText(hasilID);
        lbName.setText(hasilNama);
        lbReference.setText(hasilPerusahaan);
    }
    public void cariJurusan(String namaJr){
        String aw, ak, hasilID = "", hasilNama = "", hasilJurusan = "";
        int b = namaJr.length();
        int ad;
        
        aw = String.valueOf(namaJr.charAt(0)); //membaca karakter pertama input cari
        ak = String.valueOf(namaJr.charAt(b-1)); //membaca krakter terakhir inputan cari
        for(int i=0;i<pg.size();i++){
            String ambil = pg.get(i).getJurusan(i);
            for(int j=0;j<ambil.length();j++){
                ad = j+b-1;
                if(ad>=ambil.length()){break;}
                else{
                    if(String.valueOf(ambil.charAt(j)).equalsIgnoreCase(aw)){
                        if(String.valueOf(ambil.charAt(ad)).equalsIgnoreCase(ak)){
                            String h = ambil.substring(j, j+b);    
                            if(h.equalsIgnoreCase(namaJr)){
                                hasilID = hasilID + pg.get(i).getIdAl(i) + "\n";
                                hasilNama = hasilNama + pg.get(i).getAlumniName(i) + "\n";
                                hasilJurusan = hasilJurusan + ambil + "\n";
                            break;}
                        }
                    }
                }
            }
        }   
        lbID.setText(hasilID);
        lbName.setText(hasilNama);
    }
    public void pindahHalaman(ActionEvent event, String alamat) throws IOException{
        Parent EditProfil_parent = FXMLLoader.load(getClass().getResource("/View/" + alamat));
        Stage EditProfil_stage =  new Stage();
        Scene EditProfil_scene = new Scene(EditProfil_parent);
        EditProfil_stage.setScene(EditProfil_scene);
        EditProfil_stage.show();
        ((javafx.scene.Node) event.getSource()).getScene().getWindow().hide();
    }
    
    @FXML
    public void simpanHistory(String a) throws Exception{
        //Persiapan pembuatan File XML
        DocumentBuilderFactory produsen = DocumentBuilderFactory.newInstance();
        DocumentBuilder pembuat = produsen.newDocumentBuilder();
        Document dokumen = pembuat.newDocument();
        dokumen.setXmlStandalone(true);
        Element rootElement = dokumen.createElement("History");
        dokumen.appendChild(rootElement);
        
        Element elemenAlm = dokumen.createElement("Laman");
        elemenAlm.setTextContent(a);
        rootElement.appendChild(elemenAlm);
        
         //Membuat File XML 
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer tr = tf.newTransformer();
        DOMSource dom = new DOMSource(dokumen);
        StreamResult hasil = new StreamResult(new File ("xmlHistory.xml"));
        tr.transform(dom, hasil);
        
        System.out.println("File berhasil dibuat.");
    }
    
    @FXML
    public void openResult(ActionEvent event) throws IOException{ 
        try {
            this.simpanHistory(tfCari.getText());
        } catch (Exception ex) { Logger.getLogger(inisiasiController.class.getName()).log(Level.SEVERE, null, ex);}
        pindahHalaman(event, "cariNama.fxml");}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            bacaData();
            cbSearch.setItems(pilihan);
            cbSearch.setValue("Search by:");
        } catch (Exception ex) {
            Logger.getLogger(inisiasiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

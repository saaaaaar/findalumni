/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kelas;
import java.time.LocalDate;
import java.util.*;

public class DataAlumni{
    public String jurusan,tingkat,nama,username,pass,deskripsi,kebangsaan,
            yog,email,alamat,JHS,SHS,College,POB,Phone,Occ,bahasa;
    private LocalDate DOB = LocalDate.parse("0001-01-01");
    public int Id;
    public List <Kemampuan> skill = new ArrayList();
    public List <Perusahaan> usaha = new ArrayList();
    public boolean is_Aktif;
    
    public DataAlumni(int id, String nm, String un, String pw, String jr, String y, 
            String tg, String des,String bg, String em, String al, String jhs, String shs,
            String college,String pob,String phone,String occ,String bhs,LocalDate dob){
        this.Id = id;
        this.nama = nm;
        this.username = un;
        this.pass = pw;
        this.jurusan = jr;
        this.tingkat = tg;
        this.deskripsi = des;
        this.kebangsaan = bg;
        this.yog = y;
        this.email = em;
        this.alamat = al;
        this.JHS = jhs;
        this.SHS = shs;
        this.College = college;
        this.POB = pob;
        this.Phone = phone;
        this.Occ = occ;
        this.bahasa = bhs;
        this.DOB = dob;
    }
    
    //beberapa method untuk menambahkan data berupa usaha, skill
    public void tambahPerusahaan(int id, String pr){usaha.add(new Perusahaan(id, pr));}
    public void tambahKemampuan(int id, String sk){skill.add(new Kemampuan(id,sk));}
    public void tambahJabatan(int usahaKe, int idJ, String namaJ){
        for(int i=0;i<usaha.size();i++){
            if(usaha.get(i).getId()==usahaKe){ usaha.get(i).tambahJabatan(idJ, namaJ);}}}
    
    //beberapa method untuk melakukan pengeditan data
    public void editKemampuan(int idK, String namaS){
        for(int i=0;i<skill.size();i++){
            if(i==idK){ skill.set(i, new Kemampuan(i,namaS));}} } 
    public void editPerusahaanJabatan(int usahaKe, String namaP, int jabKe, String namaJ){
        for(int i=0;i<usaha.size();i++){
            if(i==usahaKe){
                usaha.set(i, new Perusahaan(i, namaP));
                usaha.get(i).editJabatan(jabKe,namaJ);}}
    } 
    public void editDataAlumni(String na, String jr, String tg, String un, String pw, String des, 
            String y, String bg, String em, String al, String jhs, String shs, String college,
            String pob,String phone,String occ,String bhs,LocalDate dob){
        this.nama = na;
        this.jurusan = jr;
        this.tingkat = tg;
        this.username = un;
        this.pass = pw;
        this.deskripsi = des;
        this.yog = y;
        this.kebangsaan = bg;
        this.email = em;     
        this.alamat = al;
        this.JHS = jhs;
        this.SHS = shs;
        this.College = college;
        this.POB = pob;
        this.Phone = phone;
        this.Occ = occ;
        this.bahasa = bhs;
        this.DOB = dob;
    }
    
    //beberapa method untuk membaca data: pembacaan Id
    public int getIdAl(){return this.Id;}    
    public String getIdK(int id){
        String hasil = "";
        for(int i=0;i<skill.size();i++){
            if(skill.get(i).getId()==id){
                hasil = String.valueOf(skill.get(i).getId());
                break;}
        } return hasil;}
    public String getIdP(int id){ //Mendapatkan id Perusahaan dengan pencocokan inputan parameter
        String hasil = "0";
        for(int i=0;i<usaha.size();i++){
            if(usaha.get(i).getId()==id){
                hasil = String.valueOf(usaha.get(i).getId());
                break;}
        } return hasil;}
    public String getIdJ(int idP, int id){
        String hasil = "";
        for(int i=0;i<usaha.size();i++){
            if(usaha.get(i).getId()==idP){
                hasil = String.valueOf(usaha.get(i).getIdJ(id));
                break;}
        } return hasil;}
    
    public int getPanjangPerusahaan(){return usaha.size();}
    public String getPanjangJabatan(int idP){
        String hasil="";
        for(int i=0;i<usaha.size();i++){
            if(usaha.get(i).getId()==idP){
                hasil = String.valueOf(usaha.get(i).getPanjangJabatan());
                break;}
        }return hasil;}
    public int getPanjangKemampuan(){return skill.size();}
    
    public String getNama(){return this.nama;}
    public String getUsername(){return this.username;}
    public String getPassword(){return this.pass;}
    public String getJrs(){return this.jurusan;}
    public String getTingkat(){return this.tingkat;}
    public String getDeskripsi(){return this.deskripsi;}
    public String getEmail(){return this.email;}
    public String getKebangsaan(){return this.kebangsaan;}
    public String getAlamat(){return this.alamat;}
    public String getYOG(){return this.yog;}
    public String getJHS(){return this.JHS;}
    public String getSHS(){return this.SHS;}
    public String getCollege(){return this.College;}
    public String getOccupation(){return this.Occ;}
    public String getPOB(){return this.POB;}
    public String getBahasa(){return this.bahasa;}
    public String getPhone(){return this.Phone;}
    public LocalDate getDOB(){return this.DOB;}
    public String getSkill(int idK){ // memasukkan id kemampuan ke berapa
        String hasil="";
        for(int i=0;i<skill.size();i++){
            if(skill.get(i).getId()==idK){hasil = skill.get(i).getSkill();}
        }return hasil;}
    public String getPerusahaan(int usahaKe){
        String hasil="";
        for(int i=0;i<usaha.size();i++){
            if(usaha.get(i).getId()==usahaKe){hasil = usaha.get(i).getUsaha();}
        }return hasil;}
    public String getJabatan(int usahaKe, int jabKe){ //Yang fleksibel adalah parameter bagian int jabKe 
        String hasil="";
        for(int i=0;i<usaha.size();i++){
            if(usaha.get(i).getId()==usahaKe){hasil = usaha.get(i).getJabatan(jabKe);}
        }return hasil;}
}

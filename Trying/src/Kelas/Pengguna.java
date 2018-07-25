package Kelas;
import java.time.LocalDate;
import java.util.*;

public class Pengguna {
    public int Id;
    public String username, pass;
    public List <DataAlumni> alumni = new ArrayList();
    public boolean is_Aktif;
    
    public Pengguna(int id, String un, String pw){
        this.Id = id;
        this.username = un;
        this.pass = pw;}
    
    public String bacaUserDanPass(int id, String un, String pw){
        String hasil="Username or password incorrect";
        if(id>=1 && id<alumni.size()){
            for(int i=0;i<=alumni.size();i++){
                if(un.equals(alumni.get(i).getUsername()) && pw.equals(alumni.get(i).getPassword())){
                    hasil = "Al";
                    i=alumni.size();}}
        }else if(id==0){
            if(un.equals(alumni.get(0).getUsername()) && pw.equals(alumni.get(0).getPassword())){ hasil = "Ad";}
        }else{ hasil = "Account not found";
        }return hasil;}
    
    //beberapa method untuk menambahkan data
    public void tambahAlumni(int idAl, String nm, String un, String pw, String jr, String y, String tg, 
            String des, String bg, String em, String al, String jhs, String shs, String college, 
            String pob, String phone, String occ, String bhs, LocalDate dob){
        alumni.add(new DataAlumni(idAl,nm,un,pw,jr,y,tg,des,bg,em,al,jhs,shs,college,pob,phone,occ,bhs,dob));}
    public void tambahPerusahaan(int idA, int usahaKe, String namaP){
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==idA){ alumni.get(i).tambahPerusahaan(usahaKe, namaP);}}}
    public void tambahJabatan(int idA, int usahaKe, int jabKe, String namaJ){
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==idA){alumni.get(i).tambahJabatan(usahaKe,jabKe,namaJ);}}}
    public void tambahKemampuan(int idA, int sKe, String namaS){
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==idA){alumni.get(i).tambahKemampuan(sKe, namaS);}}}
    
    
    //beberapa method untuk mendapatkan data
    public int getPanjangAlumni(){return alumni.size();}
    public String getPanjangPerusahaan(int i){ //id Alumni
        String hasil = "";
        for(int j=0;j<alumni.size();j++){
            if(alumni.get(j).getIdAl()==i){
                hasil = String.valueOf(alumni.get(j).getPanjangPerusahaan());
                break;}}
        return hasil;}
    public String getPanjangJabatan(int idAl, int idP){ //id Perusahaan
        String hasil = "";
        for(int j=0;j<alumni.size();j++){
            if(alumni.get(j).getIdAl()==idAl){
                for(int m=0;m<alumni.get(j).getPanjangPerusahaan();m++){
                    String ambil = alumni.get(j).getIdP(m);
                    if(ambil!=""){
                        if(Integer.parseInt(ambil)==idP){
                            hasil =  alumni.get(j).getPanjangJabatan(m);
                            break;}}}}
        }return hasil;}
    public String getPanjangKemampuan(int idAl){
        String hasil = "";
        for(int j=0;j<alumni.size();j++){
            if(alumni.get(j).getIdAl()==idAl){
                hasil =  String.valueOf(alumni.get(j).getPanjangKemampuan());
                break;}}
        return hasil;}
    
    public int getIdPe(){return this.Id;}
    public String getIdAl(int id){
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==id){
                hasil = String.valueOf(alumni.get(i).getIdAl());
                break;}
        }return hasil;}
    public String getIdK(int idAl, int idK){
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==idAl){
                hasil = alumni.get(i).getIdK(idK);
                break;}
        }return hasil;}
    public String getIdP(int idAl, int idP){ //yang fleksibel yang idP
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==idAl){
                hasil = alumni.get(i).getIdP(idP);
                break;}
        }return hasil;}
    public String getIdJ(int idAl, int idP, int id){
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==idAl){
                hasil = alumni.get(i).getIdJ(idP, id);
                break;}
        }return hasil;}
    
    public String getUser(){return this.username;}
    public String getPassword(){return this.pass;}
    public String getKemampuan(int idAl, int idK){
        String hasil = "";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==idAl){
                hasil = alumni.get(i).getSkill(idK);
                break;}
        }return hasil;}
    public String getJurusan(int id){
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==id){hasil = alumni.get(i).getJrs();break;}
        }return hasil;}
    public String getDeskirpsi(int id){
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==id){hasil = alumni.get(i).getDeskripsi();break;}
        }return hasil;}
    public String getAlamat(int id){
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==id){hasil = alumni.get(i).getAlamat();break;}
        }return hasil;}
    public String getEmail(int id){
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==id){hasil = alumni.get(i).getEmail();break;}
        }return hasil;}
    public String getKebangsaan(int id){
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==id){hasil = alumni.get(i).getKebangsaan();break;}
        }return hasil;}
    public String getYOG(int id){
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==id){hasil = alumni.get(i).getYOG();break;}
        }return hasil;}
    public String getTingkat(int id){
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==id){hasil = alumni.get(i).getTingkat(); break;}
        }return hasil;}
    public String getJHS(int id){
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==id){hasil = alumni.get(i).getJHS(); break;}
        }return hasil;}
    public String getSHS(int id){
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==id){hasil = alumni.get(i).getSHS(); break;}
        }return hasil;}
    public String getCollege(int id){
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==id){hasil = alumni.get(i).getCollege();}
        }return hasil;}
    public String getPOB(int id){
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==id){hasil = alumni.get(i).getPOB();}
        }return hasil;}
    public String getOccupation(int id){
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==id){hasil = alumni.get(i).getOccupation();}
        }return hasil;}
    public String getBahasa(int id){
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==id){hasil = alumni.get(i).getBahasa();}
        }return hasil;}
    public String getPhone(int id){
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==id){hasil = alumni.get(i).getPhone();}
        }return hasil;}
    public LocalDate getDOB(int id){
        LocalDate hasil = LocalDate.parse("1111-11-11");
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==id){hasil = alumni.get(i).getDOB();}
        }return hasil;}
    public String getPerusahaan(int idAl, int idP){ 
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==idAl){hasil = alumni.get(i).getPerusahaan(idP);}
        }return hasil;}
    public String getJabatan(int idAl, int idP, int idJ){
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==idAl){hasil = alumni.get(i).getJabatan(idP, idJ);}
        }return hasil;}
    public String getAlumniName(int id){
        String hasil="";
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==id){ hasil = alumni.get(i).getNama();}
        }return hasil;}
    
    //Method editing    
    public void editAlumni(int id, String na, String jr, String tg, String un, String pw, String des, 
            String y, String bg, String em, String al, String jhs, String shs, String college,
            String pob,String phone,String occ,String bhs,LocalDate dob){
        for(int i=0;i<alumni.size();i++){
            if(alumni.get(i).getIdAl()==id){alumni.get(i).editDataAlumni(na,jr,tg,un,pw,des,y,bg,em,
                    al,jhs,shs,college,pob,phone,occ,bhs,dob);}
        }
    }
}

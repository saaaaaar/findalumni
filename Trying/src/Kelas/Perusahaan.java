
package Kelas;
import java.util.*;

public class Perusahaan{
    public int Id;
    public String usaha;
    public List <Jabatan> jabatan = new ArrayList();
    
    public Perusahaan(int id, String pr){
        this.Id = id;
        this.usaha = pr;}
    
    public int getPanjangJabatan(){
        return jabatan.size();}
    public String getUsaha(){
        return this.usaha;}
    public int getId(){
        return this.Id;}
    public String getIdJ(int id){
        String hasil = "";
        for(int i=0;i<jabatan.size();i++){
            if(jabatan.get(i).getId()==id){
                hasil = String.valueOf(jabatan.get(i).getId());
                break;}
        } return hasil;}
    public String getJabatan(int idJ){
        String hasil="";
            for(int i=0;i<jabatan.size();i++){
                if(jabatan.get(i).getId()==idJ){ hasil = jabatan.get(i).getJabatan();}
        }return hasil;}

    public void tambahJabatan(int jabKe, String namaJ){ //dengan jabKe = id Jabatan
        
        jabatan.add(new Jabatan(jabKe, namaJ));}
    public void editJabatan(int jabKe, String namaJ){
        for(int i=0;i<jabatan.size();i++){
            if(i==jabKe){
                jabatan.set(i, new Jabatan(i, namaJ));}}
        }
}

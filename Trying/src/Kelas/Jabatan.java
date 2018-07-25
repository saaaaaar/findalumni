
package Kelas;

public class Jabatan {
    public int Id;
    public String jabatan;
    
    public Jabatan(int id, String jb){
        this.Id = id;
        this.jabatan = jb;
    }
    public int getId(){
        return this.Id;}
    public String getJabatan(){
        return this.jabatan;}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kelas;
public class Kemampuan {
    public int Id;
    public String skill;
    
    public Kemampuan(int id, String namaS){
        this.Id = id;
        this.skill = namaS;}
    
    public String getSkill(){
        return this.skill;}
    
    public int getId(){
        return this.Id;}
}

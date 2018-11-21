package com.users.web;

import com.users.web.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author mno
 */
@ManagedBean
@RequestScoped
public class join {

    
    private String id_pesilat;
    public void setid_pesilat(String id_pesilat) {
        this.id_pesilat = id_pesilat;
    }
    public String getid_pesilat() {
        return id_pesilat;
    }

    private String nama_pesilat;
    public void setnama_pesilat(String nama_pesilat) {
        this.nama_pesilat = nama_pesilat;
    }
    public String getnama_pesilat() {
        return nama_pesilat;
    }
    
    private String nama_kelas;
    public void setnama_kelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }
    public String getnama_kelas() {
        return nama_kelas;
    }
    
    private String keterangan;
    public void setketerangan(String keterangan) {
        this.keterangan = keterangan;
    }
    public String getketerangan() {
        return keterangan;
    }
   
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 

    public ArrayList getGet_all_join() throws Exception{
        ArrayList list_of_join=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select pesilat.*,kelas.id_kelas,kelas.nama_kelas,kelas.keterangan from pesilat left join kelas on pesilat.id_kelas = kelas.id_kelas order by pesilat.id_pesilat asc");
            while(rs.next()){
                join obj_Join = new join();
                obj_Join.setid_pesilat(rs.getString("id_pesilat"));
                obj_Join.setnama_pesilat(rs.getString("nama_pesilat"));
                obj_Join.setnama_kelas(rs.getString("nama_kelas"));
                obj_Join.setketerangan(rs.getString("keterangan"));
                list_of_join.add(obj_Join);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_join;
}
    
    public join() {
    }
    
}
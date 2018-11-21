/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @author Hakushuu
 */
@ManagedBean
@RequestScoped
public class kelas {

    /**
     * Creates a new instance of kelas
     */
    
    private String id_kelas;
    public void setid_kelas(String id_kelas) {
        this.id_kelas = id_kelas;
    }
    public String getid_kelas() {
        return id_kelas;
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

public String Edit_kelas(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String Field_id_kelas = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from kelas where id_kelas="+Field_id_kelas);
          kelas obj_kelas = new kelas();
          rs.next();
          obj_kelas.setid_kelas(rs.getString("id_kelas"));
          obj_kelas.setnama_kelas(rs.getString("nama_kelas"));
          obj_kelas.setketerangan(rs.getString("keterangan"));
          sessionMap.put("Editkelas", obj_kelas);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/edit_kelas.xhtml?faces-redirect=true";   
}

public String Delete_kelas(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String Field_id_kelas = params.get("action");
      try {
         Koneksi obj_koneksi = new Koneksi();
         Connection connection = obj_koneksi.get_connection();
         PreparedStatement ps = connection.prepareStatement("delete from kelas where id_kelas=?");
         ps.setString(1, Field_id_kelas);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "/kelas.xhtml?faces-redirect=true";   
}

public String Update_kelas(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_id_kelas= params.get("Update_id_kelas");
        try {
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update kelas set id_kelas=?, nama_kelas=?, keterangan=? where id_kelas=?");
            ps.setString(1, id_kelas);
            ps.setString(2, nama_kelas);
            ps.setString(3, keterangan);
            ps.setString(4, Update_id_kelas);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/kelas.xhtml?faces-redirect=true";   
}
    
    public ArrayList getGet_all_kelas() throws Exception{
        ArrayList list_of_kelas=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from kelas");
            while(rs.next()){
                kelas obj_Kelas = new kelas();
                obj_Kelas.setid_kelas(rs.getString("id_kelas"));
                obj_Kelas.setnama_kelas(rs.getString("nama_kelas"));
                obj_Kelas.setketerangan(rs.getString("keterangan"));
                list_of_kelas.add(obj_Kelas);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_kelas;
}
    
public String Tambah_kelas(){
        try {
            Connection connection=null;
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            PreparedStatement ps=connection.prepareStatement("insert into kelas(id_kelas, nama_kelas, keterangan) value('"+id_kelas+"','"+nama_kelas+"','"+keterangan+"')");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/kelas.xhtml?faces-redirect=true";
    }
    
    public kelas() {
    }
    
}

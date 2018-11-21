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
public class pesilat {

    /**
     * Creates a new instance of pesilat
     */
    
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
    
    private String id_kelas;
    public void setid_kelas(String id_kelas) {
        this.id_kelas = id_kelas;
    }
    public String getid_kelas() {
        return id_kelas;
    }
   
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 

public String Edit_pesilat(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String Field_id_pesilat = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from pesilat where id_pesilat="+Field_id_pesilat);
          pesilat obj_pesilat = new pesilat();
          rs.next();
          obj_pesilat.setid_pesilat(rs.getString("id_pesilat"));
          obj_pesilat.setnama_pesilat(rs.getString("nama_pesilat"));
          obj_pesilat.setid_kelas(rs.getString("id_kelas"));
          sessionMap.put("Editpesilat", obj_pesilat);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/edit_pesilat.xhtml?faces-redirect=true";   
}

public String Delete_pesilat(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String Field_id_pesilat = params.get("action");
      try {
         Koneksi obj_koneksi = new Koneksi();
         Connection connection = obj_koneksi.get_connection();
         PreparedStatement ps = connection.prepareStatement("delete from pesilat where id_pesilat=?");
         ps.setString(1, Field_id_pesilat);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "/index.xhtml?faces-redirect=true";   
}

public String Update_pesilat(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_id_pesilat= params.get("Update_id_pesilat");
        try {
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update pesilat set id_pesilat=?, nama_pesilat=?, id_kelas=? where id_pesilat=?");
            ps.setString(1, id_pesilat);
            ps.setString(2, nama_pesilat);
            ps.setString(3, id_kelas);
            ps.setString(4, Update_id_pesilat);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/index.xhtml?faces-redirect=true";   
}
    
    public ArrayList getGet_all_pesilat() throws Exception{
        ArrayList list_of_pesilat=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from pesilat");
            while(rs.next()){
                pesilat obj_Pesilat = new pesilat();
                obj_Pesilat.setid_pesilat(rs.getString("id_pesilat"));
                obj_Pesilat.setnama_pesilat(rs.getString("nama_pesilat"));
                obj_Pesilat.setid_kelas(rs.getString("id_kelas"));
                list_of_pesilat.add(obj_Pesilat);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_pesilat;
}
    
public String Tambah_pesilat(){
        try {
            Connection connection=null;
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            PreparedStatement ps=connection.prepareStatement("insert into pesilat(id_pesilat, nama_pesilat, id_kelas) value('"+id_pesilat+"','"+nama_pesilat+"','"+id_kelas+"')");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/index.xhtml?faces-redirect=true";
    }
    
    public pesilat() {
    }
    
}

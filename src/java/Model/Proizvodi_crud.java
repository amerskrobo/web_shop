/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.ServletException;


/**
 *
 * @author Korisnik
 */

public class Proizvodi_crud  {
   
    
    public void unesiProizvod(InputStream inputStream,Proizvodi proizvodi) throws ClassNotFoundException, IOException, ServletException{
        
          Connect_db conn_db = new Connect_db();
          Connection conn = conn_db.getConnection();
          
          try{
              
              if(proizvodi.getNaziv_proizvoda()!=null && !(proizvodi.getNaziv_proizvoda().isEmpty())&& proizvodi.getKolicina()!=0 && inputStream!=null  && !(proizvodi.getNaziv_slike().isEmpty()) && proizvodi.getCijena()!=0){
                  String sql= "insert into proizvodi(naziv_proizvoda,opis_proizvoda,kolicina,slika,cijena,naziv_slike)values(?,?,?,?,?,?)";
                  PreparedStatement statement = conn.prepareStatement(sql);
                  
                  statement.setString(1, proizvodi.getNaziv_proizvoda());
                  statement.setString(2, proizvodi.getOpis_proizvoda());
                  statement.setInt(3, proizvodi.getKolicina());
                  
                  
                  statement.setBlob(4, inputStream);
                      
                  
                  statement.setInt(5, proizvodi.getCijena());
                  statement.setString(6, proizvodi.getNaziv_slike());
                 statement.execute();
                 
             }else{
                  System.out.println("Nesto nije uredi");
              }
              
            
          }
          catch(SQLException ex){
              System.out.println("Error in database unesiProizvod: \n"+ ex.getMessage());
          }
          
    }
    public static int brojProizvoda(Proizvodi proizvodi) throws ClassNotFoundException{
         Connect_db conn_db = new Connect_db();
         Connection conn = conn_db.getConnection();
        
        try{
            Statement st = conn.createStatement();
            st.executeQuery("select count(product_id)from proizvodi");
            ResultSet rs = st.getResultSet();
            while (rs.next()){
                proizvodi.setBr_proizvoda(rs.getInt(1)); 
            }
        
        }catch(SQLException ex){
            System.out.println("Error in dataBase brojProizvoda: \n"+ ex.getMessage());
        }
        return proizvodi.getBroj_proizvoda();
    }
    
   
    public  List<Proizvodi>kupljeniProizvodi(List namjeraKupiti) throws ClassNotFoundException{
        
        List<Proizvodi>boughtProduct = new ArrayList<Proizvodi>();
         Connect_db conn_db = new Connect_db();
         Connection conn = conn_db.getConnection();
        
         try{
        String kupljeni_Proizvodi = "select naziv_proizvoda,cijena from proizvodi where product_id=?"; 
        PreparedStatement prep_st = conn.prepareStatement(kupljeni_Proizvodi);
             System.out.println(namjeraKupiti.size());
        if(!namjeraKupiti.isEmpty()){
        for(int i=0;i<namjeraKupiti.size();i++){
        prep_st.setInt(1, (int) namjeraKupiti.get(i));
            
        ResultSet rs = prep_st.executeQuery();
        
        
        while(rs.next()){
        Proizvodi proizvodi = new Proizvodi();
        proizvodi.setNaziv_proizvoda(rs.getString("naziv_proizvoda"));
        proizvodi.setCijena(rs.getInt("cijena"));
        boughtProduct.add(proizvodi);}
        }
        } 
        } catch (SQLException sx){
             System.out.println("Error in kupljeni proizvodi" + sx.getMessage());
        }
        return boughtProduct;
    }
    public  List<Proizvodi> allProducts() throws ClassNotFoundException{
        List<Proizvodi> sviProizvodi= new ArrayList<Proizvodi>();
        Blob img;
         Connect_db conn_db = new Connect_db();
         Connection conn = conn_db.getConnection();
        
          try{
            Statement st = conn.createStatement();
            st.executeQuery("select product_id, naziv_proizvoda,opis_proizvoda,kolicina, cijena,naziv_slike,slika from proizvodi");
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                 Proizvodi proizvodi= new Proizvodi();
                 proizvodi.setProduct_id(rs.getInt("product_id"));
                 proizvodi.setNaziv_proizvoda(rs.getString("naziv_proizvoda"));
                 proizvodi.setOpis_proizvoda(rs.getString("opis_proizvoda"));
                 proizvodi.setKolicina(rs.getInt("kolicina"));
                 proizvodi.setCijena(rs.getInt("cijena"));
                 proizvodi.setNaziv_slike(rs.getString("naziv_slike"));
                 img = rs.getBlob(7);
                 proizvodi.setImageData(img.getBytes(1, (int)img.length()));
                 String slikaBr = Base64.getEncoder().encodeToString(proizvodi.getImageData());
                 proizvodi.setStringSlike(slikaBr);
                 sviProizvodi.add(proizvodi);
                
                
            }
          
          } catch (SQLException ex){
                System.out.println("Error in database allProducts: \n"+ ex.getMessage());
            }
          
        return sviProizvodi;
    }
    public static String sviProizvodi() throws ClassNotFoundException{
     
        StringBuilder svi_proizvodi= new StringBuilder();
         Connect_db conn_db = new Connect_db();
         Connection conn = conn_db.getConnection();
       
        try{
            Statement st = conn.createStatement();
            st.executeQuery("select product_id, naziv_proizvoda,opis_proizvoda,kolicina, cijena,naziv_slike from proizvodi");
            ResultSet rs = st.getResultSet();
            
           
            
            
            svi_proizvodi.append("ID   "+"Naziv Proizvoda  "+"Opis proizvoda  "+"Kolicina  "+"Cijena  "+"Naziv slike  "+"Slika"+"\n");
            while(rs.next()){
                
                svi_proizvodi.append(rs.getString("product_id"));
                svi_proizvodi.append("    ");
                svi_proizvodi.append(rs.getString("naziv_proizvoda"));
                svi_proizvodi.append("      ");
                svi_proizvodi.append(rs.getString("opis_proizvoda"));
                svi_proizvodi.append("      ");
                svi_proizvodi.append(rs.getString("kolicina"));
                svi_proizvodi.append("      ");
                svi_proizvodi.append(rs.getString("cijena"));
                svi_proizvodi.append("      ");
                svi_proizvodi.append(rs.getString("naziv_slike"));
                svi_proizvodi.append("       ");
                svi_proizvodi.append("\n");
                
            }
            rs.close();
            
           } catch(SQLException ex){
                System.out.println("Error in database sviProizvodi: \n"+ ex.getMessage());
            }
        return svi_proizvodi.toString();
        
    }
    
    public static byte[] getPhoto(){
        Proizvodi proizvodi = new Proizvodi();
        
        Blob img;
         Connect_db conn_db = new Connect_db();
         Connection conn = conn_db.getConnection();
        
         try{
          Statement st = conn.createStatement();
          st.executeQuery("select slika from proizvodi");
          ResultSet rs = st.getResultSet();
          
          while(rs.next()){
              img = rs.getBlob(1);
              proizvodi.setImageData(img.getBytes(1, (int)img.length()));
              
              
          }
          
         }catch(SQLException ex){
             System.out.println("Error in database getPhoto: \n"+ ex.getMessage());
         }
        //String slikaBr = Base64.getEncoder().encodeToString(imageData);
        return proizvodi.getImageData();
    }
    public void deleteProduct(int product_id) throws ClassNotFoundException{
         Connect_db conn_db = new Connect_db();
         Connection conn = conn_db.getConnection();
        try{
            Statement st = conn.createStatement();
            st.executeQuery("select product_id from proizvodi");
            ResultSet rs = st.getResultSet();
            Statement st_1= conn.createStatement();
           
            if(product_id!=0){
                while(rs.next()){
                    
                   if(product_id==rs.getInt("product_id")){
                       
                       st_1.execute("delete from proizvodi where product_id='"+product_id+"'");
                   }
                   }
               
            }
            
        }catch(SQLException ex){
            System.out.println("Error in database connection izbrisi proizvod: \n"+ ex.getMessage());
        }
    }
    public void updateProizvod(Proizvodi proizvodi) throws ClassNotFoundException{
         Connect_db conn_db = new Connect_db();
         Connection conn = conn_db.getConnection();
        try{
          Statement st = conn.createStatement();
          st.executeQuery("select product_id from proizvodi");
           ResultSet rs = st.getResultSet();
          String sql = "update proizvodi set naziv_proizvoda=?,opis_proizvoda=?,kolicina=?,slika=?,cijena=?,naziv_slike=? where product_id=?";
          String[] sql_1 ={"naziv_proizvoda=?","opis_proizvoda=?","kolicina=?","slika=?","cijena=?","naziv_slike=?"};
          String sql_base="update proizvodi set ";
          String sql_last=" where product_id=?";
          StringBuilder sql_contain = new StringBuilder();
          sql_contain.append(sql_base);
          PreparedStatement prep_st= conn.prepareStatement(sql);
          
          
            if(proizvodi.getProduct_id()!=0){
                while(rs.next()){
                   if(proizvodi.getProduct_id()==rs.getInt("product_id")){
                      
                      for(int i=0;i<1;i++){
                          int j=0;
                          if(proizvodi.getNaziv_proizvoda()!=null && !proizvodi.getNaziv_proizvoda().isEmpty()) {j++; sql_contain.append(sql_1[0]);}
                          if(proizvodi.getOpis_proizvoda()!=null && !proizvodi.getOpis_proizvoda().isEmpty()) {if(j>0){sql_contain.append(",");}j++; sql_contain.append(sql_1[1]);}
                          if(proizvodi.getKolicina()!=0) {if(j>0){sql_contain.append(",");} j++;sql_contain.append(sql_1[2]);}
                          if(proizvodi.getInputStream()!=null) {if(j>0){sql_contain.append(",");}j++;sql_contain.append(sql_1[3]);}
                          if(proizvodi.getCijena()!=0) {if(j>0){sql_contain.append(",");}j++;sql_contain.append(sql_1[4]);}
                          if(proizvodi.getNaziv_slike()!=null && !proizvodi.getNaziv_slike().isEmpty()) {if(j>0){sql_contain.append(",");}j++;sql_contain.append(sql_1[5]);} 
                          
                      }
                      sql_contain.append(sql_last);
                      PreparedStatement prep_st_final = conn_db.getConnection().prepareStatement(sql_contain.toString());
                      //System.out.println(sql_contain.toString()); Ako nesto nije uredu ukljuciti ovaj dio i provjeriti stanje SQL statementa
                      
                      for(int i=0;i<1;i++){
                          int j=1;
                          if(proizvodi.getNaziv_proizvoda()!=null && !proizvodi.getNaziv_proizvoda().isEmpty()) {prep_st_final.setString(j, proizvodi.getNaziv_proizvoda());j++;}
                          if(proizvodi.getOpis_proizvoda()!=null && !proizvodi.getOpis_proizvoda().isEmpty()) {prep_st_final.setString(j, proizvodi.getOpis_proizvoda());j++;}
                          if(proizvodi.getKolicina()!=0) {prep_st_final.setInt(j, proizvodi.getKolicina());j++;}
                          if(proizvodi.getInputStream()!=null) { prep_st_final.setBlob(j, proizvodi.getInputStream());j++;}
                          if(proizvodi.getCijena()!=0) { prep_st_final.setInt(j, proizvodi.getCijena());j++;}
                          if(proizvodi.getNaziv_slike()!=null && !proizvodi.getNaziv_slike().isEmpty()) {prep_st_final.setString(j, proizvodi.getNaziv_proizvoda());j++;}
                          
                          System.out.println(j);
                          prep_st_final.setInt(j, proizvodi.getProduct_id());
                          j=1;
                      }
                      prep_st_final.execute();
                       
                       
                       
                   }
                   }
                 
            }
          
        } catch(SQLException ex){
            System.out.println("Error in database connection updateKupci: \n"+ ex.getMessage());
        }
    }
    public void stanjeProizvod(List namjera_kupiti) throws ClassNotFoundException{
         Connect_db conn_db = new Connect_db();
         Connection conn = conn_db.getConnection();
        try{
            
            String sql1 = "update proizvodi set kolicina=kolicina-1 where product_id=? and kolicina>0";
            PreparedStatement prep_st = conn.prepareStatement(sql1);
            for(int i=0;i<namjera_kupiti.size();i++){
                prep_st.setInt(1, (int)namjera_kupiti.get(i));
                prep_st.executeUpdate();
            }
               
          
        }catch(SQLException ex){
            System.out.println("Error in database connection stanjeProizvod: \n"+ ex.getMessage());
        } 
        
    }
}


package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;



public class Kupci {
    
    private String ime;
    private String prezime;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private LocalDate date_of_birth;
    private String email;
    private int id_kupca;
    
    public String getIme(){
        return ime;
    }
    
    public String getPrezime(){
        return prezime;
    }
    public LocalDate getDate_of_birth(){
        return date_of_birth;
    }
    public String getEmail(){
        return email;
    }
    public int getId_kupca(){
        return id_kupca;
    }
    public void setIme(String ime){
        this.ime=ime;
    }
    public void setPrezime(String prezime){
        this.prezime=prezime;
    }
    public void setDate_of_birth(LocalDate date_of_birth){
        this.date_of_birth=date_of_birth;
    }
    public void setEmail(String email){
        this.email= email;
    }
    public void setId_kupca(int id_kupca){
        this.id_kupca=id_kupca;
    }
    
    
   
    public void unesiKupca() {
        Connect_db conn_db = new Connect_db();
        Connection conn = conn_db.getConnection();
           
            try{
            if(ime!=null && !(ime.isEmpty())&& prezime!=null && !(prezime.isEmpty())){
                Statement st = conn.createStatement();
                st.execute("insert into kupci(Ime,Prezime,Date_of_birth,email) values ('"+ime+"','"+prezime+"','"+date_of_birth+"','"+email+"')");
                
            }
            conn.close();
            }catch(SQLException ex){
             System.out.println("Error in database connection: \n"+ex.getMessage());
        }
    }
   
    public void izbrisiKupca() {
        Connect_db conn_db = new Connect_db();
        Connection conn = conn_db.getConnection();
        Connection conn_1= conn_db.getConnection();
        try{
            Statement st = conn.createStatement();
            st.executeQuery("select id_kupci from kupci");
            ResultSet rs = st.getResultSet();
            Statement st_1 = conn_1.createStatement();
           
            if(id_kupca!=0){
                while(rs.next()){
                   if(id_kupca==rs.getInt("id_kupci")){
                       st_1.execute("delete from kupci where id_kupci='"+id_kupca+"'");
                   }
                   }
                 
            }
         
         conn.close();
        }catch(SQLException ex){
            System.out.println("Error in database connection izbrisi kupca: \n"+ ex.getMessage());
        }
        
    }
    
    public static String sviKupci(){
        StringBuilder svi_kupci = new StringBuilder();
        Connect_db conn_db = new Connect_db();
        Connection conn = conn_db.getConnection();
        try{
            Statement st = conn.createStatement();
            st.executeQuery("select id_kupci, Ime, Prezime,Date_of_birth, email from kupci");
            ResultSet rs = st.getResultSet();
            
            svi_kupci.append("ID    "+"Ime   "+"Prezime    "+ "    Datum rođenja  "+ "E-mail"+"\n");
            svi_kupci.append("--------------------------------------------------------------------"+"\n");
            while(rs.next()){
                svi_kupci.append(rs.getString("id_kupci"));
                svi_kupci.append("     ");
                svi_kupci.append(rs.getString("Ime"));
                svi_kupci.append("  ");
                svi_kupci.append(rs.getString("Prezime"));
                if((rs.getString("Ime").length()+rs.getString("Prezime").length())>10){
                svi_kupci.append("   ");
                svi_kupci.append(rs.getString("Date_of_birth"));
                svi_kupci.append("     ");
                svi_kupci.append(rs.getString("email"));
                svi_kupci.append("\n");
                }
                else{
                svi_kupci.append("         ");
                svi_kupci.append(rs.getString("Date_of_birth"));
                svi_kupci.append("     ");
                svi_kupci.append(rs.getString("email"));
                svi_kupci.append("\n");
                }
                
                
            }
             svi_kupci.append("--------------------------------------------------------------------"+"\n");
             
            conn.close();
        } catch(SQLException ex){
            System.out.println("Error in database connection sviKupci: \n "+ex.getMessage());
        }
        return svi_kupci.toString();
    }
    
    public void updateKupci() {
        Connect_db conn_db = new Connect_db();
        Connection conn = conn_db.getConnection();
        try{
          Statement st = conn.createStatement();
          st.executeQuery("select id_kupci from kupci");
           ResultSet rs = st.getResultSet();
           
            if(id_kupca!=0){
                while(rs.next()){
                   if(id_kupca==rs.getInt("id_kupci")){
                       if(ime!=null && prezime!=null && date_of_birth!=null && email!=null){
                       st.execute("update kupci set Ime='"+ime+"', Prezime='"+prezime+"', Date_of_birth='"+date_of_birth+"', email='"+email+"' where id_kupci='"+id_kupca+"'");}
                       else if(ime!=null && prezime.isEmpty() && date_of_birth==null && email.isEmpty()){
                           st.execute("update kupci set Ime='"+ime+"' where id_kupci='"+id_kupca+"'");
                       }
                       else if(ime.isEmpty() && prezime!=null && date_of_birth==null && email.isEmpty()){
                           st.execute("update kupci set Prezime='"+prezime+"' where id_kupci='"+id_kupca+"'");
                       }
                       else if(ime.isEmpty() && prezime.isEmpty() && date_of_birth!=null && email.isEmpty()){
                           st.execute("update kupci set Date_of_birth='"+date_of_birth+"' where id_kupci='"+id_kupca+"'");
                       }
                       else if(ime.isEmpty() && prezime.isEmpty() && date_of_birth==null && email!=null){
                           st.execute("update kupci set email='"+email+"' where id_kupci='"+id_kupca+"'");
                       }
                       
                   }
                   }
                 
            }
            
         conn.close();
        } catch(SQLException ex){
            System.out.println("Error in database connection updateKupci: \n"+ ex.getMessage());
        }
    }
    public void kupac_proizvod(List namjera_kupiti_proizvod, int korisnik) throws ClassNotFoundException{
    StringBuilder kupac_proizvodi = new StringBuilder();
    Connect_db conn_db = new Connect_db();
    Connection conn = conn_db.getConnection();
    try{
         
         String sql = "insert into kupljena_roba(naziv_proizvoda,naziv_korisnika,vrijeme) values (  (select naziv_proizvoda from proizvodi where product_id=?),(select ime from kupci where id_kupci=?), now())";
         PreparedStatement prep_st = conn.prepareStatement(sql);
         for(int i=0;i<namjera_kupiti_proizvod.size();i++){
         prep_st.setInt(1, (int)namjera_kupiti_proizvod.get(i));
         prep_st.setInt(2, korisnik);
         prep_st.execute();
         }
         conn.close();
    }catch(SQLException ex){
        System.out.println("Error in database: kupac_proizvod \n"+ ex.getMessage());
    }

    
}
    public static String kupljeni_proizvodi() throws ClassNotFoundException{
        StringBuilder svi_kupljeni_proizvodi = new StringBuilder();
        Connect_db conn_db = new Connect_db();
        Connection conn = conn_db.getConnection();
        try{
            Statement st = conn.createStatement();
            st.executeQuery("select naziv_proizvoda,naziv_korisnika, vrijeme from kupljena_roba");
            ResultSet rs = st.getResultSet();
            
            svi_kupljeni_proizvodi.append("Korisnik    "+ "Kupljeni proizvod"+ "    Datum/Vrijeme "+"\n");
            svi_kupljeni_proizvodi.append("---------------------------------------------------"+"\n");
            while(rs.next()){
                svi_kupljeni_proizvodi.append(rs.getString("naziv_korisnika"));
                svi_kupljeni_proizvodi.append("        ");
                svi_kupljeni_proizvodi.append(rs.getString("naziv_proizvoda"));
                svi_kupljeni_proizvodi.append("              ");
                svi_kupljeni_proizvodi.append(rs.getString("vrijeme"));
                svi_kupljeni_proizvodi.append("\n");
                
            }
            
            
         conn.close();
        }catch(SQLException ex){
            System.out.println("Error in kupljeni_proizvodi: \n"+ ex.getMessage());
            
    }
        return svi_kupljeni_proizvodi.toString();
}
    public List lista_id_kupca() throws ClassNotFoundException{
        List lista_id_kupca = new ArrayList();
        Connect_db conn_db = new Connect_db();
        Connection conn = conn_db.getConnection();
        try{
            Statement st = conn.createStatement();
            st.executeQuery("select id_kupci from kupci");
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                lista_id_kupca.add(rs.getInt("id_kupci"));
            }
            
            
        conn.close();
        }catch(SQLException ex){
            System.out.println("Error in list_id_kupca: \n"+ ex.getMessage());
            
        }
        return lista_id_kupca;
    }
}


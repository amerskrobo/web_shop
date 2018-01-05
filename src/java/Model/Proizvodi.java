
package Model;


import java.io.File;
import java.io.InputStream;
import java.util.List;



/**
 *
 * @author Korisnik
 */
public class Proizvodi {
    private int product_id;
    private String naziv_proizvoda;
    private String opis_proizvoda;
    private int kolicina;
    private File slika;
    private String naziv_slike;
    private int cijena;
    private byte[] imageData;
    private int br_proizvoda;
    private String stringSlike;
    private InputStream inputStream;
    private List namjera_kupiti;
    private int id_kupca;
    
    public int getId_kupca(){
        return id_kupca;
    }
    
    public List getNamjera_kupiti(){
        return namjera_kupiti;
    }
    
    public InputStream getInputStream(){
        return inputStream;
    }
    public String getStringSlike(){
        return stringSlike;
    }
    public int getBroj_proizvoda(){
        return br_proizvoda;
    }
    public byte[]getImageData(){
        return imageData;
    }
    public int getProduct_id(){
        return product_id;
    }
    public String getNaziv_proizvoda(){
        return naziv_proizvoda;
    }
    public String getOpis_proizvoda(){
        return opis_proizvoda;
    }
    public int getKolicina(){
        return kolicina;
    }
    public File getSlika(){
        return slika;
    }
    public String getNaziv_slike(){
        return naziv_slike;
    }
    public int getCijena(){
        return cijena;
    }
    
    public void setProduct_id(int product_id){
        this.product_id=product_id;
    }
    public void setNaziv_proizvoda(String naziv_proizvoda){
        this.naziv_proizvoda=naziv_proizvoda;
    }
    public void setOpis_proizvoda(String opis_proizvoda){
        this.opis_proizvoda=opis_proizvoda;
    }
    public void setKolicina(int kolicina){
        this.kolicina=kolicina;
    }
    public void setSlika(File slika){
        this.slika=slika;
    }
    public void setNaziv_slike(String naziv_slike){
        this.naziv_slike=naziv_slike;
    }
    public void setCijena(int cijena){
        this.cijena=cijena;
    }
    public void setImageData(byte[] imageData){
        this.imageData=imageData;
    }
    public void setBr_proizvoda(int br_proizvoda){
        this.br_proizvoda=br_proizvoda;
    }
    public void setStringSlike(String stringSlike){
        this.stringSlike=stringSlike;
    }
    public void setInputStream(InputStream inputStream){
        this.inputStream=inputStream;
    }
    public void setNamjeraKupiti(List namjera_kupiti){
        this.namjera_kupiti=namjera_kupiti;
    }
    public void setId_kupca(int id_kupca){
        this.id_kupca=id_kupca;
    }
    
    @Override
    public String toString(){
       return "Proizvodi [product_id="+product_id+"naziv_proizvoda="+naziv_proizvoda+"opis_proizvoda="+opis_proizvoda+"kolicina="+kolicina+"slika="+slika+"naziv_slike="+naziv_slike+"cijena="+cijena+"br_proizvoda="+br_proizvoda+"stringSlike="+stringSlike+"namjera_kupiti="+namjera_kupiti+"id_kupca="+id_kupca+"]"; 
    }
    
    
    
}

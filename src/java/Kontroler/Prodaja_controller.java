
package Kontroler;


import Model.Kupci;
import Model.Proizvodi;
import Model.Proizvodi_crud;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Prodaja_controller {
    Proizvodi_crud proizvodi_crud= new Proizvodi_crud();
    Proizvodi proizvodi = new Proizvodi();
    Kupci kupci = new Kupci();
    List  namjera_kupiti = new ArrayList();
    
    @RequestMapping(value="/prodaja", method=RequestMethod.GET)
    public String createForm(ModelMap model) throws ClassNotFoundException, SQLException{
        Proizvodi_crud proizvodi_crud= new Proizvodi_crud();
        List<Proizvodi> proizvodi=proizvodi_crud.allProducts();
        model.addAttribute("sviProizvodi", proizvodi);
        List<Proizvodi>kupljeniProizvodi = proizvodi_crud.kupljeniProizvodi(namjera_kupiti);
        
        model.addAttribute("kupljeniProizvodi", kupljeniProizvodi);
        model.addAttribute("Placeno", new Proizvodi());
       return "/prodaja";
    }
    
    @RequestMapping(value="/prodaja", method=RequestMethod.POST)
    public String kupiProizvodi(@ModelAttribute("Placeno")Proizvodi svi_proizvodi, ModelMap model, @RequestParam(required=false) Integer kupljeni_proizvod,@RequestParam String action) throws ClassNotFoundException, SQLException{
        
        if(action.equals("Kupi proizvod")){
        
        namjera_kupiti.add(kupljeni_proizvod);
       // proizvodi.setNamjera_kupiti(kupljeni_proizvod);
       // proizvodi_crud.namjeraKupiti();
        proizvodi_crud.kupljeniProizvodi(namjera_kupiti);
        createForm(model);
        
        }
        else if(action.equals("Plati")){
           
            System.out.println(kupci.lista_id_kupca().contains(svi_proizvodi.getId_kupca()));
            if(svi_proizvodi.getId_kupca()!=0 && kupci.lista_id_kupca().contains(svi_proizvodi.getId_kupca())){
            proizvodi_crud.stanjeProizvod(namjera_kupiti);
            System.out.println(svi_proizvodi.getProduct_id());
            
            kupci.kupac_proizvod(namjera_kupiti,svi_proizvodi.getId_kupca());
            namjera_kupiti.clear();}
            createForm(model);
            
        }
        
        
        
        
        
        return "/prodaja";
    }
}


package Kontroler;

import Model.Kupci;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Kupci_controller  {
    @RequestMapping(value="/kupci", method=RequestMethod.GET)
    public String createForm(ModelMap model)throws ClassNotFoundException{
        model.addAttribute("Kupci", new Kupci());
        model.addAttribute("Kupcii", Kupci.sviKupci());
        model.addAttribute("Kupciii", Kupci.kupljeni_proizvodi());
        
        
        return "kupci";
    }
    
    @RequestMapping(value="/kupci", method=RequestMethod.POST)
    public String addKupci(@ModelAttribute("Kupci")Kupci kupci,ModelMap model, @RequestParam String action) throws ClassNotFoundException{
        if(action.equals("Dodaj novog kupca")){
        kupci.unesiKupca();
        createForm(model);
        }
        else if(action.equals("Izbrisi kupca")){
        kupci.izbrisiKupca();
        createForm(model);
        
       }
        else if(action.equals("Izmjeni postojeceg kupca")){
        kupci.updateKupci();
        createForm(model);
        }
        
        return "/kupci";
    }
    
}

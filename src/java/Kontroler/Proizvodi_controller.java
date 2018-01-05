
package Kontroler;


import Model.Proizvodi;
import Model.Proizvodi_crud;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.ModelAndView;

@Controller
public class Proizvodi_controller extends HttpServlet{
     Proizvodi_crud proizvodi_crud= new Proizvodi_crud();
     
     Proizvodi proizvodi = new Proizvodi();
  
     
    @RequestMapping(value="/proizvodi", method=RequestMethod.GET)
    public String createForm(ModelMap model) throws ClassNotFoundException{
        model.addAttribute("Proizvodi", new Proizvodi());
        model.addAttribute("Proizvodii", proizvodi_crud.sviProizvodi());
        
        List <String>slike = new ArrayList<String>();
        String slikaBr = Base64.getEncoder().encodeToString(proizvodi_crud.getPhoto());
        slike.add(slikaBr);
        model.addAttribute("image", slike.get(0));
        model.addAttribute("br_proizvoda", proizvodi_crud.brojProizvoda(proizvodi));
        
        List<Proizvodi> proizvodi=proizvodi_crud.allProducts();
        model.addAttribute("sviProizvodi", proizvodi);
        
        //List listProizvodi = proizvodi.allProducts();
        //model.addAttribute("sviProizvodi", listProizvodi);
        
        
        
        return "/proizvodi";
    }
      
    @RequestMapping(value="/proizvodi", method=RequestMethod.POST)
    
    public String addProizvodi(@ModelAttribute("Proizvodi")Proizvodi proizvodi, ModelMap model,HttpServletRequest request,
            HttpServletResponse response,@RequestParam String action) throws ClassNotFoundException, IOException, ServletException{
       
       
       if(action.equals("Dodaj proizvod")){
           
        Part filePart = request.getPart("slika");
        InputStream inputStream = filePart.getInputStream();
        proizvodi_crud.unesiProizvod(inputStream,proizvodi);
        
        createForm(model);
     }
        else if(action.equals("Izbrisi proizvod")){
            proizvodi_crud.deleteProduct(proizvodi.getProduct_id());
            createForm(model);
            
        }
        else if(action.equals("Izmjeni proizvod")){
           Part filePart = request.getPart("slika");
           if(filePart.getSize()>0){
           InputStream inputStream = filePart.getInputStream(); 
           proizvodi.setInputStream(inputStream);
           }
           proizvodi_crud.updateProizvod(proizvodi);
                createForm(model);
                
                }
        
        return "/proizvodi";
    }
    
    
}

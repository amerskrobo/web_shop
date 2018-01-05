<%-- 
    Document   /* global system */

/* global system */

: prodaja
    Created on : Sep 6, 2017, 3:26:59 PM
    Author     : Korisnik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            header{
               
                height: 100px;
                border-bottom: solid;
                position: relative;
                vertical-align: central;
                
            
            }
            h1{
                display: inline;
                position: relative;
                text-align: left;
                vertical-align: middle;
                bottom: -20px;
                border-style: solid;
                border-width: 2px;
                padding: 3px;
                background-color: buttonhighlight;
                
            }
            footer{
                position: relative;
                border-bottom: solid;
                margin-bottom: 20px;
                height: 100px;
                border-top: solid;
                font-size: 23px;
                text-align: center;
                bottom: -30px;
            }
           
            section{
                width: 65%;
                height: 400px;
                background-color: peru;
                position: relative;
                left: 50px;
                bottom: -10px;
                overflow: scroll;
                border-bottom: solid;
                padding-bottom: 5px;
                
                
            }
            section#Kupovina{
                position: relative;
                width: 25%;
                height: 400px;
                overflow: scroll;
                left: 1000px;
                top: -400px;
            }
            div#plati{
                position: relative;
                width: 25%;
                height: 80px;
                left: 1000px;
                top: -400px;
                border-bottom: solid;
                font-size: 20px;
            }
            div#plati h3{
                margin-bottom: 3px;
                margin-top: 3px;
            }
            nav{
                position: relative;
                display: flex;
                height: 40px;
                background-color: khaki;
                width: 100%;}
            div{
                margin: 10px;
                width: 10em;
            }
            div#Kolicina{
                margin: 10px;
                width:50px;
            }
            div#Cijena{
                margin: 10px;
                width:50px;
            }
            div#SlikaPr{
                position: relative;
                margin-left: 5px;
                left: 60px;
            }
            div#Petlja{
                margin: 10px;
                height: 7em;
                width: 98%;
                background-color: aliceblue;
                
            }
            div#Kupljeni{
                width: 90%;
                height: 100px;
                border-bottom: solid;
                
            }
            
            
            textarea#NazivKupljenog{
                margin-top: 4px;
            }
            textarea{
                position: relative;
                top: -10%;
                margin-right: 10px;
                right: -2%;
                font-weight: bold;
                font-size: 15px;
            }
            img{
                position: relative;
                right: -7%;
                width: 20%;
                margin-right: 10px;
            }
            b#proba{  position: relative;
                display: inline-block;
                top: -45%;
                right: -8%;
                text-align: center;
                font-size: 30px;
                margin-right: 35px;    
                width: 90px;
            }
            b#proba2{  position: relative;
                display: inline-block;
                top: -45%;
                right: -8%;
                text-align: center;
                font-size: 30px;
                margin-right: 35px;    
                width: 90px;
            }
            
            b#proba1{
                display: inline-block;
                position:relative;
                top: -45%;
                right: -5%;
                text-align: center;
                font-size: 30px;
                margin-right: 15px;
                width: 30px;
                
            }
            input#Kupi{
                position: relative;
                left: 60px;
                top: -30px;
                height: 30px;
            }
            input#Platii{
                position: relative;
                height: 30px;
                right: -10px;
                width: 100px;
                bottom: 0px;
            }
            div#Placanje{
                
            }
           div#Petlja p{position: relative;
                top: -40%;
                right: -8%;
                text-align: center;
                font-size: 30px;
                margin-right: 10px;   
                width: 30px;
                
            }
            
            
        </style>
    </head>
    <body>
        <header>
            <h1><a href="http://localhost:8080/Web_prodavnica/prodaja.htm">Prodaja</a></h1>
            <h1><a href="http://localhost:8080/Web_prodavnica/kupci.htm">Kupci</a></h1>
            <h1><a href="http://localhost:8080/Web_prodavnica/proizvodi.htm">Proizvodi</a></h1>
        </header>
        <section>
            <nav>
                <div id="Naziv Proizvoda">Naziv Proizvoda</div>
                <div id="Opis Proizvoda">Opis Proizvoda</div>
                <div id="Kolicina">Kolicina</div>
                <div id="Cijena">Cijena</div>
                <div id="SlikaPr">Slika Proizvoda</div>
            </nav>
            <c:forEach var="i"  items="${sviProizvodi}">
                <form:form action="prodaja.htm" method="post" >
                <div id="Petlja">
                    
                    <textarea rows="5" cols="15" readonly>${i.getNaziv_proizvoda()}</textarea>
                    <textarea rows="5" cols="15" readonly>${i.getOpis_proizvoda()}</textarea>
                    <b id="proba1">${i.getKolicina()}</b>
                    <b id="proba">${i.getCijena()} KM</b>
                    <img src="data:image/jpeg;charset=utf-8;base64,${i.getStringSlike()}" width="190" height="110" />
                    <input type="hidden" value="${i.getProduct_id()}" name="kupljeni_proizvod"/>
                    <input id="Kupi" type="submit" value="Kupi proizvod" name="action"/>
                    
                </div>
                </form:form>
            </c:forEach> 
        </section>
        
        <section id="Kupovina">
            
            <nav id="Ukupno">&emsp; Naziv proizvoda &emsp;&emsp;&emsp;&emsp; Cijena </nav>
            <c:forEach var="j" items="${kupljeniProizvodi}">
            <div id="Kupljeni">
                <textarea id="NazivKupljenog" rows="5" cols="15" readonly>${j.getNaziv_proizvoda()}</textarea>
                <b id="proba2" >${j.getCijena()} KM</b>
                
            </div>
            </c:forEach>
            
        </section>
        <div id="plati"> 
            <h3 id="ukupno">Ukupno za platiti: </h3>
             <form:form  action="prodaja.htm" method="post" commandName="Placeno">
                 </br>
                 <form:label path="id_kupca">User ID</form:label>
                 <form:input path="id_kupca" size="5"></form:input>
                 <input id="Platii" type="submit" value="Plati" name="action"/>
                            
            </form:form>
        </div>
        <script >
            //var paragraph = document.getElementById('plati');
            //var y = paragraph.getElementsByTagName('h3');
           // console.log(y);
            var sum=0;
            var petlja = document.getElementById("Kupovina");
            console.log(petlja.getElementsByTagName('b'));
            var c = petlja.getElementsByTagName('b').proba2.innerHTML;
            var d = parseInt(c,10);
            console.log(d);
            console.log(petlja.getElementsByTagName('div').length);
            
            for(var count=0; count<petlja.getElementsByTagName('div').length; count++){
             var f = petlja.getElementsByTagName('div');
             var a = f[count].getElementsByTagName('b').proba2.innerHTML; 
             var e = parseInt(a,10);
             sum +=e;
             console.log(sum);
            }
            //y.innerHTML="Ukupno za platitii: " + sum+ "KM";
            document.getElementById('ukupno').innerHTML= "Ukupno za platiti: "+sum+"KM";
        </script>
        
          
    </body>
    <footer>CopyRight by Amer </br> 2017</footer>
</html>

<%-- 
    Document   : proizvodi
    Created on : Sep 6, 2017, 3:27:16 PM
    Author     : Korisnik
--%>

<%@page import="Model.Proizvodi"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proizvodi</title>
        <style>
            textarea#product_description{
                left: 0%;
            }
            input#product_quantity{
                position: relative;
                right: -0.4%;
            }
            input[type=submit]{
                background-color: lightsteelblue;
            }
            input[type=file]::-webkit-file-upload-button{
                background: lightsteelblue;
                
                
            }
            input#slika{
                position: relative;
                right: -5.2%;
            }
            input#product_picture_name{
                position:relative;
                right: -2.5%;
            }
            section{
                width: 70%;
                height: 300px;
                background-color: peru;
                position: relative;
                left: 360px;
                bottom: 220px;
                overflow: scroll;
                border-bottom: solid;
                padding-bottom: 5px;
                
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
            div#ID{
                margin: 10px;
                width:3em;
            }
            div#Kolicina{
                margin: 10px;
                width:3em;
            }
            div#Cijena{
                margin: 10px;
                width:3em;
            }
            div#SlikaPr{
                margin-left: 60px;
            }
            div#Petlja{
                margin: 10px;
                height: 7em;
                width: 95%;
                background-color: aliceblue;
                
            }
            div#lijevo{
                
                border-style: solid;
                width: 200px;
                bottom: -15px;
                position: relative;
            }
            
            div#desno{
                position: relative;
                left: 350px;
                top: -290px;
                width: 70%;
                border-style: solid;
            }
            b{  position: relative;
                top: -40%;
                text-align: center;
                font-size: 30px;
                margin-right: 25px;    
            }
            b#proba{  position: relative;
                top: -40%;
                right: -8%;
                text-align: center;
                font-size: 30px;
                margin-right: 35px;    
                width: 120px;
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
                right: -10%;
            }
            footer{
                position: relative;
                border-bottom: solid;
                margin-bottom: 20px;
                height: 100px;
                border-top: solid;
                font-size: 23px;
                text-align: center;
            }
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
        </style>
    </head>
    
    <body>
        <header>
            <h1 ><a href="http://localhost:8080/Web_prodavnica/prodaja.htm">Prodaja</a></h1>
            <h1 ><a href="http://localhost:8080/Web_prodavnica/kupci.htm">Kupci</a></h1>
            <h1 ><a href="http://localhost:8080/Web_prodavnica/proizvodi.htm">Proizvodi</a></h1>
        </header>
        <h2 id="unknown">Proizvodi &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; Uredi Proizvode</h2>
        
        <div id="lijevo">
        <form:form action="proizvodi.htm" method="post" commandName="Proizvodi" enctype="multipart/form-data" >
            <form:label path="naziv_proizvoda">Unesite naziv proizvoda</form:label>
            <form:input id="product_name" path="naziv_proizvoda" placeholder="product name.."></form:input></br>
            <form:label path="opis_proizvoda">Kratko opisite proizvod.</form:label>
            <form:textarea id="product_description" rows="2" cols="19" path="opis_proizvoda" placeholder="product description..."></form:textarea></br></br>
            <form:label path="kolicina">Unesite kolicinu</form:label>
            <form:input id="product_quantity" path="kolicina"></form:input>
            <form:label path="slika">Unesite sliku</form:label>
            <form:input type="file" path="slika" name="slika"></form:input></br>
            <form:label path="naziv_slike">Unesite naziv slike</form:label>
            <form:input id="product_picture_name" path="naziv_slike"></form:input></br>
            <form:label path="cijena">Unesite cijenu proizvoda</form:label>
            <form:input id="product_picture_price" path="cijena"></form:input></br>
            <input type="submit" name="action" value="Dodaj proizvod"/>
        </form:form> </div>
        
        <div id="desno">
        
        <form:form action="proizvodi.htm" mehtod="post" commandName="Proizvodi">
                <form:label path="product_id">Unesite ID kupca</form:label></br>
                <form:input id="id" path="product_id" placeholder="ID proizvoda"></form:input></br>
                <input type="submit" name="action" value="Izbrisi proizvod"/>
        </form:form></br>
        <form:form action="proizvodi.htm" method="post" commandName="Proizvodi" enctype="multipart/form-data" >
            <form:label path="product_id">Unesite product_id</form:label>
            <form:input id="id_proizvoda" path="product_id"></form:input> 
            <form:label path="naziv_proizvoda">Unesite novi naziv proizvoda</form:label>
            <form:input id="product_name" path="naziv_proizvoda" placeholder="new product name.."></form:input></br>
            <form:label path="opis_proizvoda">Unesite novi opis proizvoda</form:label>
            <form:textarea id="product_description" rows="2" cols="19" path="opis_proizvoda" placeholder="new product description..."></form:textarea></br>
            <form:label path="kolicina">Unesite novo stanje/kolicinu</form:label>
            <form:input id="product_quantity" path="kolicina"></form:input>
            <form:label path="slika">Unesite novu sliku</form:label>
            <form:input type="file" path="slika" name="slika"></form:input></br>
            <form:label path="naziv_slike">Unesite novi naziv slike</form:label>
            <form:input id="product_picture_name" path="naziv_slike"></form:input></br>
            <form:label path="cijena">Unesite novu cijenu proizvoda</form:label>
            <form:input id="product_picture_price" path="cijena"></form:input></br>
            <input type="submit" name="action" value="Izmjeni proizvod"/>
        </form:form>
        </div>
        
        <!--<label for="lista_proizvoda" id="lista_proizvoda_label">Svi proizvodi</label></br>
        <textarea id="lista_proizvoda" rows="20" cols="100" readonly>${Proizvodii}</textarea>
        
        <label for="lista_opisa" id="lista_opisa_label"></label></br>-->
        <section>
            <nav>
                <div id="ID"> ID</div>
                <div id="Naziv Proizvoda">Naziv Proizvoda</div>
                <div id="Opis Proizvoda">Opis Proizvoda</div>
                <div id="Kolicina">Kolicina</div>
                <div id="Cijena">Cijena</div>
                <div id="SlikaPr">Slika Proizvoda</div>
            </nav>
            <c:forEach var="i"  items="${sviProizvodi}">
                <div id="Petlja">
                    <b id="1">${i.getProduct_id()}</b>
                    <textarea rows="5" cols="15" readonly>${i.getNaziv_proizvoda()}</textarea>
                    <textarea rows="5" cols="15" readonly>${i.getOpis_proizvoda()}</textarea>
                    <b id="proba">${i.getKolicina()}</b>
                    <b id="proba">${i.getCijena()} KM</b>
                    <img src="data:image/jpeg;charset=utf-8;base64,${i.getStringSlike()}" width="190" height="110" />
                </div>
            </c:forEach> 
          
            
            
            
        </section>
        
       <!-- <form action="proizvodi.htm" method="post" commandName="Proizvodi" enctype="multipart/form-data">
                <input type="file" path="slika" name="slike"></input>
                <input type="submit" value="Dodaj Proizvod"></input>
            </form>-->
    </body>
    <footer>CopyRight by Amer </br> 2017</footer>
</html>

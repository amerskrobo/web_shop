<%-- 
    Document   : kupci
    Created on : Sep 6, 2017, 3:29:31 PM
    Author     : Korisnik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kupci</title>
        <style>
            #left{
                position: relative;
                float:left;
                width: 15%;
                border-style: solid;
                height: 200px;
                margin-right: 200px;
                
                
            }
            #right{
                position: relative;
                float:right;
                width: 70%;
                top: -200px;
                border-style: solid;
               
            }
            #down{
                nav-down: auto;
                float:left;
                
            }
            header{
                height: 100px;
                border-bottom: solid;
                position: relative;
                vertical-align: central;
            }
            footer{
                position: relative;
                bottom: -550px;
                border-bottom: solid;
                margin-bottom: 20px;
                height: 100px;
                border-top: solid;
                font-size: 23px;
                text-align: center;
                left: -10px;
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
            textarea{
                overflow: scroll;
                height: 300px;
            }
            
            div#kupljeni_proizvodi{
                
                height: 330px;
                width: 400px;
                position: relative;
                right: -800px;
                top: -330px;
            }
            textarea#kupljeni_proizvodi{
                overflow: scroll;
                height: 300px;
                width: 450px;
            }
            
        </style>
    </head>
    <body>
        <header>
            <h1><a href="http://localhost:8080/Web_prodavnica/prodaja.htm">Prodaja</a></h1>
            <h1><a href="http://localhost:8080/Web_prodavnica/kupci.htm">Kupci</a></h1>
            <h1><a href="http://localhost:8080/Web_prodavnica/proizvodi.htm">Proizvodi</a></h1>
        </header>
        <h2>Kupci &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; &emsp;&emsp; Uredi kupce</h2>
        <div id="left">
        <form:form action="kupci.htm" method="post" commandName="Kupci">
            <form:label path="ime">Unesite vaše ime:</form:label></br>
            <form:input id="name" path="ime" placeholder="your name.."></form:input></br>
            <form:label path="prezime">Unesite vaše prezime:</form:label></br>
            <form:input id="surname" path="prezime" placeholder="your surname.."></form:input></br>
            <form:label path="date_of_birth">Unesite vaš datum rođenja:</form:label></br>
            <form:input id="date_of_birth" path="date_of_birth" type="date" placeholder="dd/mm/yyyy.."></form:input></br>
            <form:label path="email">Unesite vaš email:</form:label></br>
            <form:input id="email" path="email" placeholder="your email..."></form:input></br>
            <input type="submit" name="action" value="Dodaj novog kupca"/>
        </form:form></br></br>
            <label for="lista_kuapaca" id="lista_kupaca_label">Svi kupci</label></br>
            <textarea id="lista_kupaca" rows="20" cols="100" readonly>${Kupcii}</textarea>
           <div id="kupljeni_proizvodi"> 
            <label for="lista_kupaca" id="kupljeni_proizvodi">Kupljeni proizvodi</label></br>
            <textarea id="kupljeni_proizvodi" rows="20" cols="80" readonly >${Kupciii}</textarea>
           </div>
        </div>
        <div id="right">
        <form:form action="kupci.htm" mehtod="post" commandName="Kupci">
                <form:label path="id_kupca">Unesite ID kupca</form:label></br>
                <form:input id="id" path="id_kupca" placeholder="ID kupca"></form:input></br>
                <input type="submit" name="action" value="Izbrisi kupca"/>
        </form:form></br>
        <form:form action="kupci.htm" method="post" commandName="Kupci">
            <form:label path="id_kupca">Unesite ID kupca</form:label>
            <form:input id="id_izmjeni" path="id_kupca" placeholder="ID kupca"></form:input>
            <form:label path="ime">Izmjenite ime kupca</form:label>
            <form:input id="ime_izmjeni" path="ime" placeholder="Novo ime"></form:input>
            <form:label path="prezime">Izmjenite prezime kupca</form:label>
            <form:input id="prezime_izmjeni" path="prezime" paleholder="Novo prezime"></form:input>
            <form:label path="date_of_birth">Izmjeni datum rodjenja</form:label>
            <form:input id="datum_rodjenja_izmjeni" path="date_of_birth" type="date" placeholder="dd/mm/yyyy.."></form:input>
            <form:label path="email">Izmjeni email adresu</form:label>
            <form:input id="email_izmjeni" path="email" placeholder="Nova email adresa"></form:input></br>
            <input type="submit" name="action" value="Izmjeni postojeceg kupca"/>
            
        </form:form>
        
        </div>
        
        

       
    </body>
    <footer>CopyRight by Amer </br> 2017</footer> 
</html>

<%-- 
    Document   : gamePage
    Created on : May 5, 2015, 3:55:22 PM
    Author     : Gurpartap Gill
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.lang.*" %>

<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="monopoly.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        
       
          
    <title>Game Page</title>
    </head>


    <body>
        
        <table width="90%" border="1">
          <tr>
            <td width="22%" class="header"><img src="images/enigma.jpg" width="101" height="102" alt="Logo" /></td>
            <td width="54%" class="header"><img src="images/monopoly-text.jpg" width="593" height="120" alt="Monopoly Text" /></td>
            <td width="24%" class="blue"><button class="header"><a href="lobby.jsp">Leave Game</a></button></td>
          </tr>
        </table>
    <!--Left Side starts here-->
    <table align ="left" width="150" border="1">
            <tr>
              <td bgcolor="#1EB25A">&nbsp;</td>
            </tr>
            <tr>
              <td><p class="h4">List of Players</p>
                <p>Player 1</p>
                <p>Player 2</p>
                <p>Player 3</p>
                <p>Player 4</p>
                <p>&nbsp;</p></td>
            </tr>
            <tr>
              <td><p class="h4">Game Actions</p>
                  

                <button id = "dice">Roll Dice</button>
                   
                <p id ="h"></p>

                <p>Buy house / hotel</p>
                <p>Sell house / hotel</p>
                <p>Sell property</p>
                <p>Sell / trade </p>
                <p>Get out of Jail</p></td>
            </tr>
            <tr>
              <td bgcolor="#CDE5CF">&nbsp;</td>
            </tr>
        </table>
    <!--would like to have the canvas at center-->
        <p align ="center"><canvas id = "monopolyCanvas" align = "center" width="700" height="700">My Canvas</canvas></p>
        
        <script src ="monopoly.js"></script>
        <script>
            /*here is our jQuery hnadling much of the HTML DOM & JavaScript functionality
                 * once the HTML doc is uploaded, then it checks for button wir=th id =dice. Once this button
                 * gets clicked, an AJAX call is made using simple GET beacuse here we are only getting the data 
                 * from the server. 
                 * To get(), we supply the Servlet URL, some parameters that GameServelet checks in order to roll
                 * the dice. Then the ;ast arg function(resp) is the callback function that holds the data returned
                 * by server, in this case the GameServlet  */
                $(document).ready(function(){
                    
                    $("#dice").click(function(){
                    $.get('Game.GameServlet', {"diceRoll": true},function(resp){
                        
                        document.getElementById("h").innerHTML = "Dice rolled: "+resp;
                         movePlayer(parseInt(resp)); // convert the diceTotal to int and pass on to movePlayer() to move token
                    })
                    .fail(function() { // on failure of the GET request
                alert("Request failed.");
                });
            });
        });
               
                    
        </script>
      <!--right side starts here-->
    <table width="200" border="1" align = "right">
                <tr>
                    <p>
                        <input name="Leavegame" type="submit" class="button" id="Leavegame" value="Leave Game" onclick="myFunction()" />
                    </p>
                </tr>
                <tr>
                  <td bgcolor="#1EB25A">&nbsp;</td>
                </tr>
                <tr>
                  <td><p>Property List</p>
                  <select>
                    <option value="Player 1">Player 1</option>
                    <option value="Player 2">Player 2</option>
                    <option value="Player 3">Player 3</option>
                    <option value="Player 4">Player 4</option>
                  </select>
                    <p>Name, Rent</p></td>
                </tr>
                <tr>
                    <td>
                        <p class="h4"><span class="h4">Player Info</span></p>
                        <select>
                          <option value="Player 1">Player 1</option>
                          <option value="Player 2">Player 2</option>
                          <option value="Player 3">Player 3</option>
                          <option value="Player 4">Player 4</option>
                        </select>

                        <p>Bank balance</p>
                        <p>Mortgage total</p>
                        <p>Property total value</p>
                        <p>Number of houses</p>
                        <p>Number of hotels</p>
                        <p>In jail?</p>
                    </td>
                </tr>
                <tr>
                  <td bgcolor="#CDE5CF">&nbsp;</td>
                </tr>
    </table>
  

<p>&nbsp;</p>
    </body>




<%-- 
    Document   : gamePage
    Created on : May 5, 2015, 3:55:22 PM
    Author     : Gurpartap Gill
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.lang.*" import="java.sql.*" import = "Game.Dice" %>

<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="monopoly.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
       
    <title>Game Page</title>
    </head>

    
    <body>
      <div align="center">
        <table width="90%" border="0">
          <tr>
            <td width="22%" class="header"><img src="images/enigma.jpg" width="101" height="102" alt="Logo" /></td>
            <td width="54%" class="header"><img src="images/monopoly-text.jpg" width="593" height="120" alt="Monopoly Text" /></td>
            <td width="24%" class="blue" align="right"><button class="header"><a href="lobby.jsp">Leave Game</a></button></td>
          </tr>
        </table>
        <hr>
      <table width="90%" border="0" >
          <tr>
            <td valign="top"> 
              <!--Left Side starts here-->
                <table align ="left" width="180" border="1">
                        <tr>
                          <td bgcolor="#1EB25A">&nbsp;</td>
                        </tr>
                        <tr>
                          <td><p class="h4">List of Players</p>
                            <p><label for="player1">Player 1</label></p>
                            <p><label for="player2">Player 2</label></p>
                            <p><label for="player3">Player 3</label></p>
                            <p><label for="player4">Player 4</label></p>
                          </td>
                        </tr>
                        <tr>
                          <td><p class="h4">Game Actions</p>


                            <button onclick = "movePlayer()">Roll Dice</button>
                                <%! Dice myDice = new Dice();%>
                            <p id ="h"></p>

                            <button>Buy house / hotel</button>
                            <p></p>
                            <button>Sell house / hotel</button><p></p>
                            <button>Sell property</button><p></p>
                            <button>Sell / trade </button><p></p>
                            <button>Get out of Jail</button><p></p></td>
                        </tr>
                        <tr>
                          <td bgcolor="#CDE5CF">&nbsp;</td>
                        </tr>
                </table>
            
            </td>
          
          <td valign="top">
                <!--would like to have the canvas at center-->
        <p align ="center"><canvas id = "monopolyCanvas" align = "center" width="700" height="700" >My Canvas</canvas></p>
            <script>
            
                /*global variables and can be accessed anywhere in the script*/
                  var prevCenterX = 646; // holds previous X of token
                  var prevCenterY = 646; // holds prevoius y of token
                  var centerX = 646; // holds current token position x
                  var centerY = 646; // holds current token position y
                  var canvas = document.getElementById("monopolyCanvas");
                  var context = canvas.getContext("2d");
                  var token = new Image();
                  token.src = "images/blue_token.png";
                  context.fillRect(prevCenterX, prevCenterY, 25, 10);
                    /*windows.onload = function(){
                      context.beginPath();
                      context.arc(646, 646, 10, 0, 2 * Math.PI, false);
                      context.fillStyle = 'black';
                      context.fill();
                      context.lineWidth = 5;
                        //context.lineWidth = 5;
                  //};*/
                 
                function movePlayer(){
                    
                    <% myDice.rollDice();%>
                        var diceTotal = <%= myDice.getDiceTotal()%>;
                    //var diceTotal = rollDice();
                    var i = 0;
                    document.getElementById("h").innerHTML = "Dice rolled: "+diceTotal;

                    for(; i < diceTotal; i++){
                    /*starting from go, and moving left on board*/
                      if((centerX === 646 && centerY === 646) || centerY === 646){
                          /*when moving from Corner-Space, decrease x by 74*/

                          /*change x for the max of 10 diceValue, and the rest goes */
                          //for(; i < diceTotal; i++){
                              /*moving from Go corner to next space. moving from space to GoJail corner */
                              if((centerX === 646 && centerY === 646) || centerX === 124)
                                  centerX = xSpaceCornerLeft();
                              /*moving from GoJail corner to space*/
                              else if(centerX === 50 && centerY ===646)
                                  centerY = ySpaceCornerUp();
                              else if (centerX > 50)
                                  centerX = centerX - 56;
                              else 
                                  centerY = centerY - 56;

                          /*if the token hit the bottom-left corner, then change Y to go up also*/

                      }
                      /*if the token is the bottom-left corner or left vertical row. Then moves up*/
                      else if((centerX === 50 && centerY === 646) || centerX === 50){

                          //for(; i < diceTotal; i++){
                              /*moving from Jail corner to next space. moving from space to Free Parking corner*/
                              if((centerX === 50 && centerY === 646) || centerY === 124 )
                                  centerY = ySpaceCornerUp();

                              /*moving from Free Parking corner to next space*/
                              else if(centerX === 50 && centerY ===50)
                                  centerX = xSpaceCornerRight();
                              else if(centerY > 50)
                                  centerY = centerY - 56;
                              else 
                                  centerX = centerX + 56;

                             //}
                          /*now if the token reaches top-left corner, then change X to go right also*/

                      }
                      /*if the token is at the top-left corner or the top horizontal row. Then move towards right*/
                      else if((centerX === 50 && centerY === 50) || centerY === 50){
                          //for(; i < diceTotal; i++){
                              /*moving from FreeParking to next space. moving from space to GoJail corner*/
                              if((centerX === 50 && centerY === 50) || centerX === 572)
                                  centerX = xSpaceCornerRight();

                              else if(centerX < 646)
                                  centerX = centerX + 56;
                              /*moving from GoJail corner to next space*/
                              else if(centerX === 646 && centerY ===50)
                                  centerY = ySpaceCornerDown();
                              else
                                  centerY = centerY + 56;
                             //}
                          /*now if the token reaches at top-right corner, change Y to go down.*/

                      }
                      /*if the token is at the top-right corner or right vertical row. Then move the token down*/
                      else if((centerX === 646 && centerY === 50) || centerX === 646){
                          //for(; i< diceTotal; i++){
                              /*move from GoJail corner to space. moving from space to Go Corner*/
                              if((centerX === 646 && centerY === 50) || centerY === 572)
                                  centerY = ySpaceCornerDown();
                              /*move from Go corner to next space*/
                              else if(centerX === 646 && centerY ===646)
                                  centerX = xSpaceCornerLeft();
                              else if(centerY < 646)
                                  centerY = centerY + 56;
                             
                             else
                                 centerX = centerX - 56;
                          /*now if Token reaches bottom-left corner, then change X to go left*/

                      }
                  }
                      
                      //token.onload = function() {
                      context.clearRect(prevCenterX, prevCenterY, 25, 10);
                      context.fillRect(centerX, centerY, 25, 10);
                      saveOldXY(centerX, centerY);
                        //};
                      /*context.beginPath();
                      context.arc(centerX, centerY, 10, 0, 2 * Math.PI, false);
                      context.fillStyle = 'black';
                      context.fill();
                      context.lineWidth = 5; */

            }
                function saveOldXY(x, y){
                    prevCenterX = x;
                    prevCenterY = y;
                }
                /*changing x, y co-ordinates as token moves between Space-Corner or vice versa*/
                function xSpaceCornerLeft(){
                    return centerX - 74;
                }
                function ySpaceCornerUp(){
                    return centerY - 74;
                }
                function xSpaceCornerRight(){
                    return centerX + 74;
                }
                function ySpaceCornerDown(){
                    return centerY + 74;
                }
              
          </script>            
          </td>
            <td valign="top">
                <!--right side starts here-->
                <table width="250" border="1" align = "right">
                            <tr>
                              <td bgcolor="#1EB25A">&nbsp;</td>
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

                                    <p align="left"><strong>Property</strong></p>
                                    <ul align="left">
                                      <li>Number of real estate <label for="numRealEstate">##</label></li>
                                      <li>Number of railroads <label for="numRailRoads">##</label></li>
                                      <li>Number of utilities <label for="numUtilities">##</label></li>
                                    </ul>
                                    <p align="left"><strong>Account Balance </strong><label for="acctBalance">##</label></p>
                                    <p align="left"><strong>In jail? </strong><label for="inJail">yes/no</label></p>
                                </td>
                            </tr>
                            <tr>
                              <td bgcolor="#CDE5CF">&nbsp;</td>
                            </tr>
                </table>             
            </td> 
          </tr>
      </table>
      </div>
    </body>




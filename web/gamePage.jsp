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

    <!--table width="90%" border="1">
      <tr>
        <td width="22%" class="header"><img src="images/enigma.jpg" width="101" height="102" alt="Logo" /></td>
        <td width="54%" class="header"><img src="images/monopoly-text.jpg" width="593" height="120" alt="Monopoly Text" /></td>
        <td width="24%" class="blue"><button class="header"><a href="lobby.jsp">Leave Game</a></button></td>
      </tr>
    </table-->
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

                                <button id = "payTaxes">Pay Taxes</button>
                                <button id = "getOutOfJail">Get out of Jail</button>
                                <p>Buy house / hotel</p>
                                <p>Sell house / hotel</p>
                                <p>Sell property</p>
                            </td>
                        </tr>
                        <tr>
                            <td bgcolor="#CDE5CF">&nbsp;</td>
                        </tr>
                    </table>
                </td>

                <td valign="top">
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
                        $(document).ready(function () {

                            $("#dice").click(function () {

                                /*first check if the player is not in Jail*/
                                /*$.get('Game.GameServlet', {"param": "isInJail"},
                                 function(resp){
                                 setIsInJail(resp);
                                 })
                                 .fail(function() { // on failure of the GET request
                                 alert("Request failed to check if player in Jail.");
                                 });*/

                                if (getIsInJail().localeCompare("Yes") === 0) {
                                    alert("You are in Jail. Pay fine to get Out and Play");
                                }
                                else {
                                    $.get('Game.GameServlet', {"param": "rollDice"}, function (resp) {

                                        movePlayer(parseInt(resp)); // convert the diceTotal to int and pass on to movePlayer() to move token
                                        updateSpaceId(); // updates spaceId
                                        $("#myConsole").append("You rolled " + resp + " diceValue.<br>");
                                        if (returnSpaceId() == 4 || returnSpaceId() == 38) {
                                            alert("ROLL SOME CASH NOW TO IRS.\n PAY YOUR TAXES!");
                                            /*$.get('Game.GameServlet',{"param": "payTax", "taxAmount": "50"}, function(resp){
                                             document.getElementById("myConsole").append = resp;
                                             
                                             })
                                             .fail(function(){
                                             alert("Not able to Pay Taxes!");
                                             });*/
                                        }
                                        else if(returnSpaceId() == 0){
                                            
                                        }
                                        else if(returnSpaceId() == 10){
                                            
                                        }
                                        
                                        else if(returnSpaceId() == 20){
                                            
                                        }
                                        else if (returnSpaceId() == 30) {
                                            /*move player's token to Jail space*/
                                            alert("OOPS, You are on your way to Jail!");
                                            moveToJail(); //moves Player Token to Jail Space
                                            setIsInJail("Yes");
                                            setSpaceId(10);    
                                            document.getElementById("isInJail").value = "Yes";
                                            $("#myConsole").append("You are in Jail Now.<br>");
                                        }
                                        /*for chance/community cards*/
                                        else if (returnSpaceId() === 2) {
                                            
                                        }
                                        else if (returnSpaceId() === 7) {
                                            
                                        }
                                        else if (returnSpaceId() === 17) {
                                            
                                        }
                                        else if (returnSpaceId() === 22) {
                                            
                                        }
                                        else if (returnSpaceId() === 33) {
                                            
                                        }
                                        else if (returnSpaceId() === 36) {
                                            
                                        }
                                        else {

                                            //$("#myConsole").append(returnSpaceId() + "<br>");

                                            if (confirm("You landed on Property: "+returnSpaceId()+"\nWould you like to buy ?") === true) {

                                                $.get('Game.GameServlet',
                                                        {"param": "buyProperty", "spaceId": returnSpaceId().toString()}, function (resp) {
                                                    if (resp.localeCompare("True") === 0) {

                                                        $("#myConsole").append("You bought a property: " + returnSpaceId() + "<br>");
                                                    }
                                                    else {
                                                        alert("Unable to buy Property");
                                                    }

                                                })
                                                        .fail(function () {
                                                            alert("Failed to buy Property!");
                                                        });
                                            }
                                        }





                                        document.getElementById("h").innerHTML = "Dice rolled: " + resp + " SpaceId: " + returnSpaceId();
                                    })
                                            .fail(function () { // on failure of the GET request
                                                alert("Request failed to roll dice.");
                                            });
                                    /*creates get request to update Player's status inJail now*/
                                    /*$.get('Game.GameServlet', {"param": "inJail"}, function(){
                                     alert("You are in Jail Now. Get Out of Jail after paying a fine.");
                                     })
                                     .fail(function() { // on failure of the GET request
                                     alert("Request failed to set Player to Jail.");
                                     });*/

                                }



                            });

                            $("#getOutOfJail").click(function () {
                                /*charge the player $50 and update his account balance, and ask to roll dice*/
                                if (getIsInJail().localeCompare("Yes") === 0) {
                                    setIsInJail("No");
                                    alert("Congrats, you outta Jail. Roll Dice to continue your turn!")
                                    document.getElementById("isInJail").value = "No";
                                    $("#myConsole").append("You are out of Jail Now.<br>");
                                }
                                else
                                    alert("You are currently Not In jail :)");
                            });

                            //document.getElementById("h").innerHTML = "Dice rolled: "+resp+" SpaceId: "+returnSpaceId();

                        });

                        //});


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
                                <p align="left"><strong>Account Balance </strong><label for="acctBalance">$500</label></p>
                                <p align="left"><strong>In jail? </strong><output id ="isInJail"for="inJail">No</output></p>
                            </td>
                        </tr>
                        <tr>
                            <td bgcolor="#CDE5CF">&nbsp;</td>
                        </tr>
                        <tr>
                            <td>
                                <div id = "myConsole" style="align:right;height:250px;width:250px;border:1px solid #ccc;font:12px;overflow:auto"></div>
                            </td>
                        </tr>
                    </table>    

        </table>
    </div>
</body>

<!--table width="200" border="1" align = "right">
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
<!--/select>
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
</body-->




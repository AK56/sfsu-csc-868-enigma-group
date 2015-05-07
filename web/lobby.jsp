<%-- 
    Document   : lobby
    Created on : Apr 22, 2015, 8:17:02 PM
    Author     : JattMac
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
       <link href="monopoly.css" rel="stylesheet">
            <title>Lobby Page</title>
    </head>
    <body>
        <% 
    int game1 = 3;
    int game2 = 2;
    int game3 = 1;
    int playerId1 = 1;
    int playerId2 = 2;
    int playerId3 = 3;
    int playerId4 = 4;
    int playerId5 = 5;
    int playerId6 = 6;
    ArrayList g1 = new ArrayList();
    g1.add(playerId1);
    g1.add(playerId2);
    g1.add(playerId3);
    ArrayList g2 = new ArrayList();
    g2.add(playerId4);
    g2.add(playerId5);
    ArrayList g3 = new ArrayList();
    g3.add(playerId6);
    
            session.setAttribute("game1", g1);
            session.setAttribute("game2", g2);
            session.setAttribute("game3", g3);
            
            String firstName = (String) session.getAttribute("firstName");
            String lastName = (String) session.getAttribute("lastName");
            String userName = (String) session.getAttribute("userName");
            %>
        <div align="center">
<table width="90%" border="0">
  <tr>
    <td width="22%" class="header"><a href="index.jsp"><img src="images/enigma.jpg" width="101" height="102" alt="Logo" /></a></td>
    <td width="54%" class="header"><img src="images/monopoly-text.jpg" width="593" height="120" alt="Monopoly Text" /></td>
    <td width="24%" class="blue"><p class="header"><a href="index.jsp">Logout</a></p></td>
  </tr>
</table>
<table width="90%" border="1" cellpadding="10">
  <tr>
    <td width="33%" class="header"><a href="index.jsp">Home</a></td>
    <td width="33%" valign="top" class="header"><a href="about.html">About Monopoly</a></td>
    <td width="33%" class="header"><a href="how-to-play.html">How To Play</a></td>
  </tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table width="55%" height="376" border="1" cellpadding="40">
  <tr>
    <td width="573"><h2>Personal Info </h2>
        <p>&lt;Real Name&gt;</p><p><%=firstName%>&nbsp; <%= lastName%> </p>
        <p>&lt;User Name&gt;</p><p><%=userName%></p>
      <p>
        <input name="Edit User Info" type="submit" class="button" id="Edit User Info" value="Edit User Info" />
        </p>
		<p>
        <input name="Logout" type="submit" class="button" id="Logout" value="Logout" />
        </p>
      <p>&nbsp;</p>
      <p>&nbsp;</p></td>
    <td width="487">
        <p><a href="gamePage.jsp">Join game 1, currently <%=game1%> players</a></p>
        <p><a href="gamePage.jsp">Join game 2, currently <%=game2%> players</a></p>
        <p><a href="gamePage.jsp">Join game 3, currently <%=game3%> players</a></p>
        <p>List of others users logged in wanting to play a game.
      </p>
      <p>List of games.</p>
      <p>
        <%--<input name="Start a Game" type="submit" class="button" id="Start a Game" value="Start a Game / Join Game" /> --%>
      </p></td>
  </tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
</div>
    </body>
</html>

<%-- 
    Document   : lobby
    Created on : Apr 24, 2015, 2:11:05 PM
    Author     : robertmoon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="monopoly.css" rel="stylesheet">
<title>Lobby Page</title>
</head>

<body>
<div align="center">
<table width="90%" border="0">
  <tr>
    <td width="22%" class="header"><a href="index.html"><img src="images/enigma.jpg" width="101" height="102" alt="Logo" /></a></td>
    <td width="54%" class="header"><img src="images/monopoly-text.jpg" width="593" height="120" alt="Monopoly Text" /></td>
    <td width="24%" class="blue"><p class="header"><a href="login.html">Login</a> / <a href="register.html">Register</a></p></td>
  </tr>
</table>
<table width="90%" border="1" cellpadding="10">
  <tr>
    <td width="33%" class="header"><a href="index.html">Home</a></td>
    <td width="33%" valign="top" class="header"><a href="about-monopoly.html">About Monopoly</a></td>
    <td width="33%" class="header"><a href="how-to-play.html">How To Play</a></td>
  </tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table width="55%" height="376" border="1" cellpadding="40">
  <tr>
    <td width="573"><h2>Personal Info </h2>
      <p>&lt;Real Name&gt;</p>
      <p>&lt;User Name&gt;</p>
      <p>
        <input name="Edit User Info" type="submit" class="button" id="Edit User Info" value="Edit User Info" />
        </p>
		<p>
        <input name="Logout" type="submit" class="button" id="Logout" value="Logout" />
        </p>
      <p>&nbsp;</p>
      <p>&nbsp;</p></td>
    <td width="487"><p>List of others users logged in wanting to play a game.
      </p>
      <p>List of games.</p>
      <p>
        <input name="Start a Game" type="submit" class="button" id="Start a Game" value="Start a Game / Join Game" />
      </p></td>
  </tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
</div>
</body>
</html>

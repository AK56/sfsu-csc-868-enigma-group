<%-- 
    Document   : login
    Created on : Apr 26, 2015, 9:03:50 PM
    Author     : robertmoon
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="monopoly.css" rel="stylesheet">
<title>Login</title>
</head>

<body>
    <jsp:useBean id="player" class="Player"/>
    <script>
        function setLoginAndPassword() {
            var name = document.getElementById("username").valueOf();
        }
    </script>
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
<table width="90%" border="0">
  <tr>
    <td><p class="xl"><span class="h1">Login</span></p>
      <hr /></td>
  </tr>
</table>
<table width="90%" border="0">
  <tr>
    <td class="alignLeft"><form id="form1" name="form1" method="post" action="">
      <p>
        <label for="username"></label>
      </p>
      <table width="90%" border="0">
        <tr>
          <td align="right">Username:</td>
          <td align="left"><label for="password"></label>
            <input name="username" type="text" class="blue" id="username2" size="35" width="400px"/></td>
        </tr>
        <tr>
          <td align="right">Password:</td>
          <td align="left"><input name="password" type="text" class="blue" id="username" size="35" width="400px"/></td>
        </tr>
        <tr>
          <td align="right"><p>&nbsp;</p>
            <p>
              <input name="submit" type="buton" class="button" id="submit" value="Submit" onclick="setLoginAndPassword()"/>
              </p>
            <p>&nbsp;</p></td>
          <td align="left">&nbsp;</td>
        </tr>
      </table>
    </form></td>
  </tr>
</table>
<p>&nbsp;</p>
</div>
</body>
</html>

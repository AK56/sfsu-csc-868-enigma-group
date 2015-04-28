<%-- 
    Document   : game
    Created on : Apr 27, 2015, 5:33:30 PM
    Author     : robertmoon
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="monopoly.css" rel="stylesheet">
<title>Game Page</title>
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
<table width="90%" border="0">
  <tr>
    <td><table width="200" border="1" align="center">
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
          <p>Roll dice</p>
          <p>Buy house / hotel</p>
          <p>Sell house / hotel</p>
          <p>Sell property</p>
          <p>Sell / trade </p>
          <p>Get out of Jail</p></td>
      </tr>
      <tr>
        <td bgcolor="#CDE5CF">&nbsp;</td>
      </tr>
    </table></td>
    <td align="center"><p><img src="images/monopoly-board.jpg" width="700" height="705" alt="monopolyBoard" /></p></td>
    <td><table width="200" border="1" align="center">
	  <tr>
	  <p>
        <input name="Leavegame" type="submit" class="button" id="Leavegame" value="Leave Game" />
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
          <p>In jail?</p></td>
      </tr>
      <tr>
        <td bgcolor="#CDE5CF">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
<p>&nbsp;</p>
</div>
</body>
</html>

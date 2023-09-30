<?php 
session_start();
try
{
 $db= new PDO('mysql:host=localhost;dbname=dev_web', 'root','');
 $db-> setAttribute(PDO::ATTR_CASE , PDO::CASE_LOWER);
 $db-> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
}
catch(Exception $e)
{
 die('Erreur : '.$e->getMessage());
}
?>



<!doctype html>
<html lang="fr">

<head>
	<meta charset="utf-8">
	<title>LESPORT</title>
	<link rel="stylesheet" href="Css/page.css" type="text/css" />
	
</head>
<body>
<?php if(isset($_SESSION['username'])&&$_SESSION['username']=='root'){
	?>

<header>
<div class="img-header">
			<div class="text">
				<h1 class="brand">LESPORT</h1>
			</div>
		</div>
		<nav>
			<ul>
				<li><a href="php/produits.php?cat=Chaussure">Chaussure</a></li>
				<li><a href="php/produits.php?cat=Complements alimentaires">Complements alimentaires</a></li>
				<li><a href="php/produits.php?cat=Equipement">Equipement</a></li>
				<li><a href="php/Contact.php">Contact</a></li>
				<li><a href="php/deconnexion.php">Deconexion</a></li>
			</ul>
		</nav>
</header>

<?php }
else {
	?>
	<header>
<div class="img-header">
			<div class="text">
				<h1 class="brand">LESPORT</h1>
			</div>
		</div>
		<nav>
			<ul>
				<li><a href="php/produits.php?cat=Chaussure">Chaussure</a></li>
				<li><a href="php/produits.php?cat=Complements alimentaires">Complements alimentaires</a></li>
				<li><a href="php/produits.php?cat=Equipement">Equipement</a></li>
				<li><a href="php/Contact.php">Contact</a></li>
				<li><a href="php/Connexion.php">Connexion</a></li>
			</ul>
		</nav>
</header>
<?php
} ?>

	<div class="global">
	<div id="menu">
			<h2 class="centrer">Sociéte LESPORT</h2>
			<ul>
				<li><a href="Index.php" title="Retourner à l'Accueil">Accueil</a></li>
				
			</ul>
			<hr>
			<p> <u> Nos produits </u></p>
			<ul>
				<li><a href="php/produits.php?cat=Chaussure">Chaussure</a></li>
				<li><a href="php/produits.php?cat=Complements alimentaires">Complements alimentaires</a></li>
				<li><a href="php/produits.php?cat=Equipement">Equipement</a></li>
				<li><a href="php/Contact.php">Contact</a></li>
			</ul>
			
		</div>

		<div id="content">
			
			<h1>Bienvenue chez la societé LESPORT</h1>
				<br>
				
				<img src="Image/Capture.PNG" alt="" width="100%" /> 
				<p>Ici on est specialisé dans le materiel sportif ! <br> Vous pouvez prendre contact avec notre centre relation client au : 06 66 92 02 64.
				</p>
				
			<br> <br>
		

		</div>
   			
	</div>

	<?php include('include/footer.php'); ?>
	</body>
</html>
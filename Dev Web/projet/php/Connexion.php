<?php 
	session_start();
    $user= 'root';
    $password_admin ='123';

    if (isset($_POST['submit'])){

        $username = $_POST['username'] ;
        $password = $_POST['password'] ;

    
	
    if( $username && $password)
    {
        if($username==$user && $password==$password_admin)
        {
            $_SESSION['username']=$username;
            header('Location: ../admin/admin.php');
        }
        else
        { 
            header('Location: ../php/connexion.php') ;
        }
    }
    else 
    {
		header('Location: ../index.php') ;
    }
}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<title>Connexion</title>
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />
	<meta name="generator" content="Geany 1.36" />
	<link rel="stylesheet" href="../Css/page.css" type="text/css" />
</head>
<body>
<?php include("../include/header.php"); ?>

<div class="global">
	<div id="menu">
			<h2 class="centrer">Sociéte LESPORT</h2>
			<ul>
				<li><a href="../Index.php" title="Retourner à l'Accueil">Accueil</a></li>
				
			</ul>
			<hr>
			<p> <u> Nos produits </u></p>
			<ul>
				<li><a href="produits.php?cat=Chaussure">Chaussure</a></li>
				<li><a href="produits.php?cat=Complements alimentaires">Complements alimentaires</a></li>
				<li><a href="produits.php?cat=Equipement">Equipement</a></li>
				<li><a href="Contact.php">Contact</a></li>
			</ul>
			
		</div>
	<div id="container">
		<center>
		<br><br>
			<form action="#" method="POST" class="connectForm">
				<h1>Connexion</h1>
				<div>
					<label><b>Nom d'utilisateur</b></label>
					<input type="text" placeholder="Entrer le nom d'utilisateur" name="username" required >
				</div>
				<br>
				<div>
					<label><b>Mot de passe</b></label>
					<input type="password" placeholder="Entrer le mot de passe" name="password" required>
				</div>
				<br>
				<button type="submit" name="submit">Login</button>
			</form>
			<br><br>
			</center>
	</div>
</div>
</body>
<?php include("../include/footer.php"); ?>
</html>
    

<?php session_start(); ?>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>contact</title>
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
		<div id="content">
                <?php   if(isset($_SESSION['contact'])&& $_SESSION['contact']=='false')
                {
                    echo ' <p> <h1> Votre demande de contact ne respecte pas les normes attendus </h1> </p>';
                }
                if(isset($_SESSION['contact'])&& $_SESSION['contact']=='true')
                {
                    echo ' <p> <h1> Votre demande de contact à bien etait envoyé </h1> </p>';
                }

                    $_SESSION['contact']=NULL;
                ?>
			<p> <h1>Demande de contact</h1> <br>
	        <form action="ContactTest.php" class="Contactform" method="post" onsubmit="return check_form()">

			<div class="form">
					<label for="contact"> Date du contact :</label>
					<input id="contact" type="naissance" id="contact" placeholder="JJ/MM/AA" name="contact" >
					<div class="error_contact" id="error_contact">la date doit être au format JJ/MM/AAAA</div>
                    

			</div> 

            <div id="nom" class="form">
                <label for="name">Nom : </label>
                <input type="text" id="name" placeholder="Votre nom" name="user_name">
                <div class="error_msg" id="error_name">Veuillez entrer une chaîne de maximum 30 caractères pour votre nom de famille</div>
                
                
            </div>
            <div id="prenom" class="form">
                <label for="fname">Prénom : </label>
                <input type="text" id="fname" placeholder="Votre prénom" name="user_fname">
                <div class="error_msg" id="error_fname">Veuillez entrer une chaîne de maximum 30 caractère pour votre prénom</div>
                
            </div>
            <div class="form">
                <label for="mail">Mail : </label>
                <input type="email" id="mail" placeholder="Votre email" name="user_mail">
                <div class="error_msg" id="error_mail">Le mail doit avoir le format suivant : [chaine]@[chaine] et se terminer par .fr ou .com en moins de 30 caractères</div>
                
            </div>

            <div >
				<label for="genre">Genre : </label>

                <input type="radio" id="choicemale" name="choixgenre" value="Homme "style="margin-left: 170px;">
                <label for="choicemale">Homme</label>

                <input type="radio" id="choicefemale" name="choixgenre" value="Femme">
                <label for="choicefemale">Femme</label>
                <div class="error_msg" id="error_sex">Veuillez cocher votre sexe</div>
               
            </div>
			<br><br>
            <div class="form">
                <label for="choixmetier">Fonction : </label>

                <select name="metiers" id="metiers">
                    <option value=""></option>
                    <option value="etudiant">Etudiant</option>
                    <option value="Professeur">Professeur</option>
                  
                </select>
                <div class="error_msg" id="error_job">Veuillez sélectionner un métier</div>
               
            </div>

            <div class="form">
                <label for="naissance">Date de naissance : </label>
                <input type="naissance" id="naissance" name="naissance" placeholder="JJ/MM/AAAA">
                <div class="error_msg" id="error_birthday">la date doit être au format JJ/MM/AAAA</div>

                
            </div>

			<div class="form">
					<label for="sujet">Sujet</label>
					<input type="text" name="user_sujet" id="sujet" >		
                   
			</div>
            <div class="form">
                <label for="content">Message : </label>
                
                <textarea name="content" id="content_form" rows="10" cols="30"></textarea>
                <div class="error_msg" id="error_text">Il faut écrire votre message ne dépassant pas 800 caractères</div>
               
           		</div>

            <div class="form">
                <input type="submit" value="Envoyer!" onclick="check_form()">
               <!-- <input button value="Envoyer!" onclick="check_form()"> -->
            </div>
        </form>
		
        
</div>



		</div>

	</div>
	
    <?php include("../include/footer.php"); ?>

	<script>
                document.getElementById("error_name").style.display = 'none';
                document.getElementById("error_fname").style.display = 'none';
                document.getElementById("error_mail").style.display = 'none';
                document.getElementById("error_sex").style.display = 'none';
                document.getElementById("error_job").style.display = 'none';
                document.getElementById("error_birthday").style.display = 'none';
                document.getElementById("error_text").style.display = 'none';
				document.getElementById("error_contact").style.display = 'none';

            </script>
	</body>
</html>
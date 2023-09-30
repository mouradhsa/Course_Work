<?php
if(isset($_SESSION['username']) && $_SESSION['username']=='root'){

 
?>
<header>
<div class="img-header">
			<div class="text">
				<h1 class="brand">LESPORT</h1>
			</div>
		</div>
		<nav>
			<ul>
				<li><a href="../php/produits.php?cat=Chaussure">Chaussure</a></li>
				<li><a href="../php/produits.php?cat=Complements alimentaires">Complements alimentaires</a></li>
				<li><a href="../php/produits.php?cat=Equipement">Equipement</a></li>
				<li><a href="../php/Contact.php">Contact</a></li>
				<li><a href="../php/deconnexion.php">Deconnexion</a></li>
			</ul>
		</nav>
</header>

<?php }

else
{

	?>
	<header>
<div class="img-header">
			<div class="text">
				<h1 class="brand">LESPORT</h1>
			</div>
		</div>
		<nav>
			<ul>
				<li><a href="../php/produits.php?cat=Chaussure">Chaussure</a></li>
				<li><a href="../php/produits.php?cat=Complements alimentaires">Complements alimentaires</a></li>
				<li><a href="../php/produits.php?cat=Equipement">Equipement</a></li>
				<li><a href="../php/Contact.php">Contact</a></li>
				<li><a href="../php/connexion.php">Connexion</a></li>
			</ul>
		</nav>
</header>
<?php
}?>
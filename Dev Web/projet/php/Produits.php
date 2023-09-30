<?php session_start(); 
require 'panier.class.php';
require 'db.class.php';
$DB= new DB();
$panier=new panier($DB);


try
{
 $db= new PDO('mysql:host=localhost;dbname=dev_web', 'root', '');
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
	<link rel="stylesheet" href="../Css/page.css" type="text/css" />
    <script charset="utf-8" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
	<script src="../JS/index.js" type="text/javascript"></script>
</head>
<body>

<?php include("../include/header.php"); 
?>

<div class="global">
	
		<?php
			include("../include/sidebar.php");
		?>
	<div id="content">
    
		<center>
		<table>
            <thead>
                    <th>Photo</th> 
                    <th>Nom</th>
				    <th>Réference</th> 
				    <th>Description</th>
				    <th>Prix</th>
                    <th>Panier</th>
				    <th class="stock">Stock </th>
            </thead>
            
            <thead>
           
            <?php
          /*  foreach ($_SESSION["categories"][$_GET["cat"]] as $key => $value) {

                echo " <tr>
                            <td><img src=\"../Image/". $value["img"] . "\" class=\"imageItem\" height=\"200\" onclick=\"zoom(this)\"/></td>
                            <td>" . $value["nom"] . "</td>
                            <td>" . $value["ref"] . "</td>
                            <td>" . $value["desc"] . "</td>
                            <td>" . $value["prix"] . "</td>
                            <td> 
                                <form>
                                    <input class= \"moins\" onclick= \"retirerQuantite(this) \" type= \"button\" value= \"-\"/>
                                    <input class= \"result\" type= \"number\" value= \"5\" max= \"10\" name= \"resultat\"/>
                                    <input class= \"plus\" onclick= \"ajoutQuantite(this)\" type= \"button\" value= \"+\"/>
                                </form>
                                <button> Ajouter au panier </button>
                            </td>
                            <td class= \"stock\"> 10 </td>
                        <tr> ";

                            
                            
                            
            }  
            */
			$select= $db->prepare("SELECT * FROM products");
			$select->execute();
			
			while($s=$select->fetch(PDO::FETCH_OBJ))
			{
				if ($_GET['cat']==($s->categorie)){
                   
				?>
               

				<tr>
                    <td><img src="../Admin/imgs/<?php echo $s->title;?>.jpg "  alt="" style="width: 200px; height:200px; border:solid 6px #b4d0fc; border-radius:100px;"/> </td>
					<td><?php echo $s->title; ?></td>
					<td><?php echo $s->id; ?></td>
					<td><?php echo $s->description; ?></td>
					<td><?php echo $s->price; ?></td>
                    
                    
					
					<td> 
                                <form method="POST" action="panier.php">
                                    <input class= "moins" onclick= "retirerQuantite(this) " type= "button" value= "-"/>
                                    <input class= "result" type= "number" value= "0" max= "stock" name= "resultat"/>
                                    <input class= "plus" onclick= "ajoutQuantite(this)" type= "button" value= "+">

                                   <a class="add addPanier" href="addpanier.php?id2=<?php echo $s->id2; ?>">Ajouter au panier</a> 
                                </form>
                            </td>
                           
                            <td class= "stock"> <?php echo $s->stock; ?> </td>
				</tr>
               
				<?php
                
			}}
            
		
        ?>
               
            </thead>
            
        </table>
		</center>
		<br>
		 <center> 
         <?php
if(isset($_SESSION['username']) && $_SESSION['username']=='root'){

 
?>
         <button onclick='toggle()'>Stock </button> </center>
    <?php } ?>
	</div>

</div>


<?php include("../include/footer.php"); ?>


</body>
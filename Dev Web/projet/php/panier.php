<?php
session_start();
require 'panier.class.php';
require 'db.class.php';
$DB= new DB();
$panier=new panier($DB);
?>

<!doctype html>
<html lang="fr">

<head>
	<meta charset="utf-8">
	<title>LESPORT</title>
	<link rel="stylesheet" href="../Css/page.css" type="text/css" />
	
</head>
<body>

<?php include("../include/header.php"); 
?>

<div class="global">
	
		<?php
			include("../include/sidebar.php");
		?>
	<div id="content">
    <br><br><br><br><br>
		<center>
    <form method="POST" action="panier.php">
		<table>
                    <tr>
                        <td colspan="5"> Votre Panier</td>
                    </tr>   

                    <tr>
                        <td>Libellé produit</td>
                        <td>Prix unitaire</td>
                        <td>Quantité</td>
                        <td>Total</td>
                        <td>Action</td>
                    </tr>
        
            <?php
            $ids=array_keys($_SESSION['panier']);
            if(empty($ids))
            {
                $products =array();
            }
            else 
            {
                $products=$DB->query('SELECT * FROM products WHERE id2 IN('.implode(',',$ids).')');
            }
           
                foreach($products as $product):
            ?>
            <tr>    <td><?php echo $product->title; ?></td>
                    <td><?php echo number_format($product->price,2,',',' '); ?> &euro;</td> 
                    <td><input type="text" name="panier[quantity][<?php echo $product->id2; ?>]" value="<?php echo $_SESSION['panier'][$product->id2];?>"></td>
                    <td><?php echo number_format($panier->total(),2,',',' ') ?>&euro; </td>
                    <td><a href="panier.php?delPanier=<?php echo $product->id2; ?>">X</a></td>
                    <?php 
                        endforeach;
                    ?>
            </tr>
            <input type="submit" value="recalculer" style="width: 100px;">
            </table>
    </form>
    <br><br><br><br><br><br><br><br>
		</center>
		<br>
		
	</div>

</div>


<?php include("../include/footer.php"); ?>


</body>
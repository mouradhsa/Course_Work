<?php session_start(); 
require 'panier.class.php';
require 'db.class.php';
$DB= new DB();
$panier=new panier($DB);
$json=array('error'=>true);
if(isset($_GET['id2']))
{
    $product= $DB->query('SELECT id2 FROM products WHERE id2=:id2',array('id2' =>$_GET['id2']));
    if(empty($product))
    {
        $json['message']='Ce produit existe pas';
    }
    $panier->add($product[0]->id2);
    $json['error']=false;
    $json['message']='Le produit a bien ete ajoute a votre panier';
    
}
else
{
    $json['message']= "Vous n'avez pas séléctionné de produit à ajouter au panier";
}

echo json_encode($json);
?>
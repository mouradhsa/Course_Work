<?php 
session_start();

?>

<h1>Bienvenue, <?php echo $_SESSION['username'] ?> </h1>

<a href="?action=add">Ajouter un produit</a>
<a href="?action=modifyanddelete">Modifier/supprimer un produit</a>
<a href="../Index.php">Retourner à l'acceuil</a>  <br><br>
<?php
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

    if(isset($_SESSION['username']))
    {
        if(isset($_GET['action'])) {
            if($_GET['action']=='add')
            {
                if(isset($_POST['submit']))
                {
                    $title=$_POST['title'];
                    $description=$_POST['description'];
                    $price=$_POST['price'];
                    $ref=$_POST['ref'];
                    $categorie=$_POST['categorie'];
                    $stock=$_POST['stock'];
                    $id=$_POST['id'];

                    $img= $_FILES['img']['name'];
                    $img_tmp=$_FILES['img']['tmp_name'];

                    if(!empty($img_tmp))
                    {
                        $image= explode('.',$img);

                        $image_ext = end($image);

                        if(in_array(strtolower($image_ext),array('png','jpg','jpeg'))===false) 
                        {
                            echo'veuillez rentrez une image ayan pour extension : png, jpg, jpeg';
                        }
                        else
                        {
                            $image_size=getimagesize($img_tmp);

                            if($image_size['mime']=='image/jpeg')
                            {
                                $image_src = imagecreatefromjpeg($img_tmp);
                            }
                            else if ($image_size['mime']=='image/png')
                            {
                                $image_src = imagecreatefrompng($img_tmp);
                            }
                            else
                            {
                                $image_src = false;
                                echo ' Veuillez rentrer une image valide ';
                            }

                            if($image_src !==false)
                            {
                                $image_width=200;

                                if($image_size[0]==$image_width)
                                {
                                    $image_finale= $image_src;
                                }
                                else
                                {
                                    $new_width[0]=$image_width;
                                    $new_height[1]=200;
                                    $image_finale=imagecreatetruecolor($new_width[0],$new_height[1]);

                                    imagecopyresampled($image_finale, $image_src,0,0,0,0,$new_width[0],$new_height[1],$image_size[0],$image_size[1]);

                                }
                                imagejpeg($image_finale,'imgs/'.$title.'.jpg');
                            }
                        }
                    }
                    else
                    {
                        echo 'Veuillez rentrer une image';
                    }
                }
                if(isset($_POST['submit'])&& !empty($img_tmp)){

                if($title && $description && $price && $stock && $categorie && $ref)
                {
                $req=$db->prepare("INSERT INTO products VALUES ('$id','$ref','$title','$description','$price','$categorie','$stock')");
                $req->execute();


                //foreach($_SESSION["categories"] as $key => $value){
                //echo "<div class=\"top_choice\"><a href=\"produits.php?cat=".$key."\">".$key."</a></div>";
                //}
                // $test = $db -> query('SELECT title FROM products');

                // while($a = $test -> fetch()){
                //     echo "".$a['title']."";
                // }
                }

            else 
            {
                echo 'Veuillez remplir tous les champs';
            }
        }
?>
        <form action="" method="post" enctype="multipart/form-data">
            <h3>Titre du produit :</h3>  <input type="text" name="title"/>
            <h3>Description du produit :</h3> <textarea name="description"></textarea>
            <h3>Prix</h3> <input type="text" name="price"/>
            <h3>ref</h3> <input type="text" name="ref"/> 
            <h3>Id</h3> <input type="text" name="id"/> 
            <h3>Stock :</h3> <input type="text" name="stock"/>  
            <br> <br>
            <select name="categorie" id="fonction-select" >
						<option value="">--Veuillez choisir une categorie</option>
						<option value="Chaussure">Chaussure</option>
						<option value="Complements alimentaires">Complements alimentaires</option>
                        <option value="Equipement">Equipement</option>
			</select>
            <br> <br>
            <input type="file" name="img"/>
            <br><br>
            <input type="submit" name="submit"/>
        </form>
<?php
        }

        else if ($_GET['action']=='modifyanddelete')
        {
            $select= $db->prepare("SELECT * FROM products");
            $select->execute();

            while ($s=$select->fetch(PDO::FETCH_OBJ)) 
            {
                echo $s ->title; ?>
                <a href="?action=modify&amp;id=<?php echo $s->id; ?>">Modifier</a>
                <a href="?action=delete&amp;id=<?php echo $s->id; ?>">X</a> <br>

                <?php
            }
        }

        else if ($_GET['action']=='modify') 
        {

            $id=$_GET['id'];
            $select= $db -> prepare("SELECT * FROM products WHERE id='$id'");
            $select -> execute();

            $data = $select ->fetch(PDO::FETCH_OBJ);
            ?>
            <form action="" method="post">
                <h3>Titre du produit :</h3>  <input value="<?php echo $data->title; ?> " type="text" name="title"/>
                <h3>Description du produit :</h3> <textarea name="description"><?php echo $data->description; ?></textarea>
                <h3>Prix</h3> <input value="<?php echo $data->price; ?>" type="text" name="price"/>
                <h3>Id</h3> <input value="<?php echo $data->id; ?>" type="text" name="ref"/>
                <h3>Stock :</h3> <input type="text" name="stock" value=" <?php echo $data->stock ?> "/>  

                <h3>Categorie : <?php echo $data->categorie; ?></h3>
                
                <select name="categorie" id="fonction-select" >
						<option value="">--Veuillez choisir une categorie</option>
						<option value="Chaussure">Chaussure</option>
						<option value="Complements alimentaires">Complements alimentaires</option>
                        <option value="Equipement">Equipement</option>
			    </select>
                <br><br>
                <input type="submit" name="submit" value="Modifier"/>
            </form>

            <?php
                if(isset($_POST['submit']))
                {
                    $stock=$_POST['stock'];
                    $title=$_POST['title'];
                    $description=$_POST['description'];
                    $price=$_POST['price'];
                    $ref=$_POST['ref'];
                    $categorie=$_POST['categorie'];
                    
                    if($categorie==''){
                        echo 'Veuillez entrer une categorie';
                    }
                    else
                    {
                    $update=$db->prepare("UPDATE products SET title='$title',description='$description', price ='$price' , id='$ref' , categorie='$categorie' , stock='$stock' WHERE id='$id'");
                    $update-> execute();

                    header('Location: admin.php?action=modifyanddelete');
                    }
                }
            
        }
        
        else if ($_GET['action']=='delete')
        {
            $id=$_GET['id'];
            $delete= $db->prepare("DELETE FROM products WHERE id='$id'");
            $delete->execute();
        }

        else 
        {
            die('Une erreur s\'est produite');
        }
    }
}
    else
    {
        header('Location: ../index.php');
    }
?>





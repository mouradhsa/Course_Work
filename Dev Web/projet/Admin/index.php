<?php 
session_start();

    $user= 'root';
    $password_admin ='';

    if (isset($_POST['submit'])){

        $username = $_POST['username'] ;
        $password = $_POST['password'] ;

    }

    if( $username && $password)
    {
        if($username==$user && $password==$password_admin)
        {
            $_SESSION['username']=$username;
            header('Location: admin.php');
        }
        else
        { 
            echo 'Identifiants erroné' ;
        }
    }
    else 
    {
       echo 'Veuillez remplir les champs' ; 
    }
?>
<link rel="stylesheet" href="../Css/page.css" type="text/css">
<h1>Admins - Connexion</h1>
<form action="" method="post">
    <h3>Pseudo :</h3>    <input type="text" name="username"/> <br> <br>
    <h3>Mot-de-passe :</h3> <input type="password" name="password"/> <br> <br>
    <input type="submit" name="submit"/> <br> <br>
</form>



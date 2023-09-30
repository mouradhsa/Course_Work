<?php
session_start();

if(!(isset($_POST['contact'])))
{ 
    $_SESSION['contact']='false';
}
else{
    $contact = $_POST['contact'];
    if(!(strlen($contact) == 10))
    { 
        $_SESSION['contact']='false';
    }
    else
    {
        try{
            $theDay = intval(substr($contact, 0, 2));
            $theMonth = intval(substr($contact, 3, 4));
            $theYear = intval(substr($contact, 6, 9));
            if( !((checkdate($theMonth, $theDay, $theYear))) )
            {
                $_SESSION['contact']='false';
            }
        }
        catch(Exception $e)
        {
            $_SESSION['contact']='false';
        }
    }
} 

if(! ((isset($_POST['user_name']))) )
{ 
    $_SESSION['contact']='false';
}
elseif(! ((strlen($_POST['user_name'])<=30) && $_POST['user_name'] != "") )
{ 
   $_SESSION['contact']='false';
}

                    if(! ((isset($_POST['user_fname'])) )  )
                    {
                        $_SESSION['contact']='false';
                    }
                   elseif(! ((strlen($_POST['user_fname'])<=30) && $_POST['user_fname'] != "") )
                    {
                        $_SESSION['contact']='false';
                    }


                    if(! ((isset($_POST['user_mail'])) && (strlen($_POST['user_mail'])<=30) )  )
                    {
                        $_SESSION['contact']='flase'; 
                    }
                    elseif(! (filter_var($_POST['user_mail'], FILTER_VALIDATE_EMAIL)))
                    {
                        $_SESSION['contact']='flase';
                    }

                    if(!(isset($_POST['metiers'])))
                    { 
                    $_SESSION['contact']='false';
                    }


                if(!(isset($_POST['naissance'])))
                { 
                    $_SESSION['contact']='false';
                }
                else{
                    $birthday = $_POST['naissance'];
                    if(!(strlen($birthday) == 10))
                    { 
                        $_SESSION['contact']='false';
                    }
                    else
                    {
                        try{
                            $theDay = intval(substr($birthday, 0, 2));
                            $theMonth = intval(substr($birthday, 3, 4));
                            $theYear = intval(substr($birthday, 6, 9));
                            if( !((checkdate($theMonth, $theDay, $theYear))) )
                            {
                                $_SESSION['contact']='false';
                            }
                        }
                        catch(Exception $e)
                        {
                            $_SESSION['contact']='false';
                        }
                    }
                } 


                if(! ((isset($_POST['user_sujet'])) && (strlen($_POST['user_sujet'])<=50))  )
                    {
                        $_SESSION['contact']='false'; 
                    }

                    if(! ((isset($_POST['content'])) && (strlen($_POST['content'])<=800)) )
                    {
                        $_SESSION['contact']='false'; 
                    }

if($_SESSION['contact']!='false' )
{
    $_SESSION['contact']='true';
}


    header('Location: Contact.php');

?>
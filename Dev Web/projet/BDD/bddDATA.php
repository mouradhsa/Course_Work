<?php
class DB{

   include ('Bdd.php');

    public function __construct($host=null,$username=null,$password=null,$database=null)
    {
        if($host !=null)
        {
            $this->host =$host;
            $this->username=$username;
            $this->password=$password;
            $this->database=$database;
        }

        try
        {
            $this->db = new PDO('mysql:host='.$this->host.';dbname='.$this-> database, $this->username, $this->password, 
            array(PDO::MYSQL_ATTR_INIT_COMMAND =>'SET NAMES UTF8',
            PDO::ATTR_ERRMODE =>PDO::ERRMODE_WARNING));
            echo 'vous etes connecté';
            
        }
        catch(PDOException $e)
        {
            die('Impossible de se connecter');
        }
    }

    public function query($sql,$data=array())
    {
        $req=$this->db->prepare($sql);
        $req->execute($data); 
        return $req->fetchAll(PDO::FETCH_OBJ);

    }
}


?>
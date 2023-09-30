<?php
    

    class panier {

        private $DB;

        public function __construct($DB)
        {
            if(!isset($_SESSION))
            {
                session_start();
            }
            if(!isset($_SESSION['panier']))
            {
                $_SESSION['panier']=array();
            }
            $this->DB=$DB;
            if(isset($_GET['delPanier']))
            {
                $this->del($_GET['delPanier']);
            }
            if(isset($_POST['panier']))
            {
                $this->recalc();
            }
        }

        public function recalc()
        {
            $_SESSION['panier']=$_POST['panier']['quantity'];
        }
        public function count()
        {
            array_sum($_SESSION['panier']);
        }

        public function total()
        {
            $total=0;
            $ids=array_keys($_SESSION['panier']);
            if(empty($ids))
            {
                $products =array();
            }
            else 
            {
                $products=$this->DB->query('SELECT id2 , price FROM products WHERE id2 IN('.implode(',',$ids).')');
            }
            foreach( $products as $product)
            {
                $total +=$product->price * $_SESSION['panier'][$product->id2];
            }
            return $total;
        }

        public function add($product_id2)
        {
            if(isset($_SESSION['panier'][$product_id2])){
                $_SESSION['panier'][$product_id2]++;
            }
            else
            {
            $_SESSION['panier'][$product_id2]=1;
            }
        }

        public function del($product_id2){
          unset($_SESSION['panier'][$product_id2]);
        }
    }

?>


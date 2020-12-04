<?php 
session_start();
        
if(!isset($_SESSION['customer_login'])) 
   header('location:index.php');   
?>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Customer Homepage</title>
        
        <link rel="stylesheet" href="newcss.css">
    </head>
        <?php include 'header.php' ?>
        <div class='content'>
            
           <?php include 'customer_navbar.php'?>
            
            <h1 style="text-align: center; margin-top: 100px;">WELCOME <?php echo $_SESSION['cust_name'] ?><h1>
            
        </div>
        <?php include 'footer.php';?>
    </body>
</html>
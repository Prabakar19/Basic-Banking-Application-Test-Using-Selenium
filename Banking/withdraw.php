<?php
include 'dbconn.php'; 
session_start();
        
if(!isset($_SESSION['customer_login'])) 
   header('location:index.php');   

    if(isset($_REQUEST['submit']))
    {

        $acc_no=$_SESSION['acc_no'];
        $amount=$_REQUEST['amt'];

        $sql = "UPDATE customer SET amount= amount - $amount WHERE acc_no='$acc_no'";

        $result=mysqli_query($conn, $sql);

        echo "<script>alert('Rs.$amount has withdrawn from your account')</script>";
        //header('location:withdraw.php');
    }
  
?>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Deposite</title>
        
        <link rel="stylesheet" href="newcss.css">
    </head>
        <?php include 'header.php' ?>
        <div class='content'>
        
            
           <?php include 'customer_navbar.php'?>
        
            <form method="post">
            <h1 align="center">Withdrawal</h1>    
            <div cass="dep" align="center" style="margin-top: 80px;">

            Enter Amount: <input type="text" name="amt"/>
            <br><br>
            <input type="submit" name="submit" value="Withdraw"/></div>

        </form>
        </div>
        <?php include 'footer.php';?>
    </body>
</html>
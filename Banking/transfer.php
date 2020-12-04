<?php
include 'dbconn.php'; 
session_start();
        
if(!isset($_SESSION['customer_login'])) 
   header('location:index.php');   

    if(isset($_REQUEST['submit']))
    {

        $acc_no=$_SESSION['acc_no'];
        $acc=$_REQUEST['acc'];
        $amount=$_REQUEST['amt'];

        $sql = "UPDATE customer SET amount= amount - $amount WHERE acc_no='$acc_no'";

        $sql1 = "UPDATE customer SET amount= amount + $amount WHERE acc_no='$acc'";

        $result=mysqli_query($conn, $sql);
        $result1=mysqli_query($conn, $sql1);

        echo "<script>alert('Rs.$amount has transfered to $acc')</script>";
        //header('location:transfer.php');
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
            <h1 align="center">Transfer Funds</h1>    
            <div cass="dep" align="center" style="margin-top: 40px;">

                <table border="1">
                    <tr><td>
            Enter Account No:</td><td> <input type="text" name="acc"/></td></tr>
            <tr>
            <td>Enter Amount:</td><td><input type="text" name="amt"/></td></tr>
            <td colspan="2" align="center">
            <input type="submit" name="submit" value="Transfer"/>
        </table>
        </div>

        </form>
        </div>
        <?php include 'footer.php';?>
    </body>
</html>
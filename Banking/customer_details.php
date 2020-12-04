<?php 
session_start();
        
if(!isset($_SESSION['customer_login'])) 
   header('location:index.php');   


    $name=$_SESSION['cust_name'];
    $acc_no=$_SESSION['acc_no'];
    $gender=$_SESSION['gender'];
    $age=$_SESSION['age'];
    $dob=$_SESSION['dob'];
    $phone=$_SESSION['phone'];
    $email=$_SESSION['email'];
    $acc_type=$_SESSION['acc_type'];
    $address=$_SESSION['address'];
    

?>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Customer Details</title>
        
        <link rel="stylesheet" href="newcss.css">
    </head>
        <?php include 'header.php' ?>
        <div class='content'>
            
           <?php include 'customer_navbar.php'?>
           <h2 align="center">Customer Details</h2>
           <table border="1" align="center" style="margin-top: 50px;">
                <tr><td>NAME</td><td><?php echo $name;?></td></tr>
                <tr><td>ACCOUNT NO</td><td><?php echo $acc_no;?></td></tr>
                <tr><td>GENDER</td><td><?php echo $gender;?></td></tr>
                <tr><td>AGE</td><td><?php echo $age;?></td></tr>
                <tr><td>DATE OF BIRTH</td><td><?php echo $dob;?></td></tr>
                <tr><td>PHONE NO</td><td><?php echo $phone;?></td></tr>
                <tr><td>EMAIL</td><td><?php echo $email;?></td></tr>
                <tr><td>ACCOUNT TYPE</td><td><?php echo $acc_type;?></td></tr>
                <tr><td>ADDRESS</td><td><?php echo $address;?></td></tr>

           </table> 
            


        </div>
        <?php include 'footer.php';?>
    </body>
</html>
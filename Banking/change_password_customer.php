<?php 
session_start();
include 'dbconn.php';
        
if(!isset($_SESSION['customer_login'])) 
    header('location:index.php');   


if(isset($_REQUEST['change_password']))
    {

        $uname=$_SESSION['email'];
        
        $sql="SELECT password FROM customer WHERE email='$uname'";
            
        $result=mysqli_query($conn,$sql);
            
        $rws=  mysqli_fetch_array($result);

        $old= $_REQUEST['old_password'];

        $new=  $_REQUEST['new_password'];

        $again=  $_REQUEST['again_password'];

        if($rws[0]==$old && $new==$again)
        {
                
            $sql1="UPDATE customer SET password='$new' WHERE email='$uname'";

            mysqli_query($conn,$sql1); 
                header('location:customer_home.php');
        }
        else
        {
                
                header('location:change_password_customer.php');
        } 
   }
    
?>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Change Password</title>
        
        <link rel="stylesheet" href="newcss.css">
        <style>
        .content_customer table,th,td {
    padding:6px;
    border: 1px solid #2E4372;
   border-collapse: collapse;
   text-align: center;
}
</style>
    </head>
        <?php include 'header.php' ?>
        <div class='content'>
           <?php include 'customer_navbar.php'?>
    <h3 style="text-align:center;color:#2E4372;"><u>Change Password</u></h3>
            <form action="" method="POST">
                <table align="center">
                    <tr>
                        <td>Enter old password:</td>
                        <td><input type="password" name="old_password" required=""/></td>
                    </tr>
                    <tr>
                        <td>Enter new password:</td>
                        <td><input type="password" name="new_password" required=""/></td>
                    </tr>
                    <tr>
                        <td>Enter password again:</td>
                        <td><input type="password" name="again_password" required=""/></td>
                    </tr>
                    </table>
                    
                       <table align="center"><tr>
                        <td><input type="submit" name="change_password" value="Change Password" class="addstaff_button"/></td>
                    </tr>
                </table>
            </form>
            
        </div>
        <?php include 'footer.php';?>
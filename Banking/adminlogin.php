<?php 
include 'dbconn.php';
if(!isset($_SESSION['admin_login']))
{
    if(isset($_REQUEST['submitBtn']))
    {

    $username=$_REQUEST['uname'];
    $password=$_REQUEST['pwd'];
  
    $sql = "SELECT * FROM admin WHERE id='$username' AND password='$password'";

        $result=mysqli_query($conn,$sql);

        
        $rws= mysqli_fetch_array($result);

       // echo($rws[0]);
       // echo($rws[1]);
       // echo($rws[2]);     

        if($username==$rws[0] && $password==$rws[2]) 
        {
            session_start();

            $_SESSION['admin_login']=1;
            $_SESSION['admin_name']=$rws[1];
            $_SESSION['admin_id']=$rws[0];
            header('location:admin_hompage.php'); 
        }
        else
            header('location:adminlogin.php');      
    }
}
else 
{
    header('location:admin_hompage.php');
}
?>
<?php 
// session_start();
        
// if(isset($_SESSION['admin_login'])) 
//    header('location:admin_homepage.php');   
?>

<!DOCTYPE html>
<html>
    <head>
        <title>Admin Login - Online Banking</title>
        
        <link rel="stylesheet" href="newcss.css">
    </head>
<?php
include 'header.php'; ?>

<div class='content'>
<div class="user_login">
    <form action='' method='POST'>
        <table align="center">
            <tr><td><span class="caption">Admin Login</span></td></tr>
            <tr><td colspan="2"><hr></td></tr>
            <tr><td>UserID:</td></tr>
            <tr><td><input type="text" name="uname" required></td></tr>
            <tr><td>Password:</td></tr>
            <tr><td><input type="password" name="pwd" required></td></tr>
            <tr><td class="button1"><input type="submit" name="submitBtn" value="Log In" class="button"></td></tr>
        </table>
    </form>
            </div>
        </div>
          
<?php include 'footer.php';
?>
?>
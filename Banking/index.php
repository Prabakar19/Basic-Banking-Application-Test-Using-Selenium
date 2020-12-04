<?php 
if(isset($_REQUEST['submitBtn']))
{
    include 'dbconn.php';
    
    $username=$_REQUEST['uname'];
    // $salt="password";
    // $password= sha1($_REQUEST['pwd'].$salt);
    $password=$_REQUEST['pwd'];
  
    $sql = "SELECT * FROM customer WHERE email='$username' AND password='$password'";
    
    
    $result=mysqli_query($conn, $sql);
    
    $rws=mysqli_fetch_array($result);  
    
    $user=$rws[6];
    $pwd=$rws[10];
    $name=$rws[0];    
    
    if($user==$username && $pwd==$password){
        session_start();
        $_SESSION['customer_login']=1;
        $_SESSION['cust_name']=$name;
        $_SESSION['acc_no']=$rws[1];
        $_SESSION['gender']=$rws[2];
        $_SESSION['age']=$rws[3];
        $_SESSION['dob']=$rws[4];
        $_SESSION['phone']=$rws[5];
        $_SESSION['email']=$rws[6];
        $_SESSION['acc_type']=$rws[7];
        $_SESSION['amount']=$rws[8];
        $_SESSION['address']=$rws[9];
        $_SESSION['password']=$rws[10];
        
        header('location:customer_home.php'); 
    
     }
    else   
    {
           
        header('location:index.php');  
    }
}
?>
<?php 
// session_start();
        
// if(isset($_SESSION['customer_login'])) 
//     header('location:customer_home.php');   
?>

<!DOCTYPE html>

<html>
    <head>
        
     <title>Online Banking</title>
        <link rel="stylesheet" href="newcss.css">
    </head>
    <body>
        <?php include 'header.php' ?>
        
        <div class="wrapper">
        
            <div class="user_login">
                <form action='' method='POST'>
                    <table align="left">
                        <tr><td><span class="caption">User Login</span></td></tr>
                        <tr><td colspan="2"><hr></td></tr>
                        <tr><td>Email:</td></tr>
                        <tr><td><input type="text" name="uname" required></td> </tr>
                        <tr><td>Password:</td></tr>
                        <tr><td><input type="password" name="pwd" required></td></tr>
                        
                        <tr><td class="button1"><input type="submit" name="submitBtn" value="Log In" class="button"></td></tr>
                    </table>
                </form>
                </div>
        
            <div class="image">
                <img src="home.jpg" height="100%" width="100%"/>
            </div>
            
                   
        </div>
    <?php include 'footer.php' ?>

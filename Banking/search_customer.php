<?php 

session_start();
     
include 'dbconn.php';   
if(!isset($_SESSION['admin_login'])) 
    header('location:adminlogin.php');   
   
    $a="";
    $b="";
    $c="";
    $d="";
    $e="";
    $f="";
    $g="";
    $h="";
    $i="";
    $j="";
    $k="";


    $name="";
    $acc_no="";
    $gender="";
    $age="";
    $dob="";
    $phone="";
    $email="";
    $acc_type="";
    $amt="";
    $address="";
    
    if(isset($_REQUEST['submit']))
    {
         
        $acc_no=$_REQUEST['acc_no'];
        

        $sql="SELECT * FROM customer WHERE acc_no='$acc_no'";

        $result=mysqli_query($conn,$sql);

        $rws=mysqli_fetch_array($result);

        

            $a="Customer";
            $b="Customer's Name";
            $c="Account No";
            $d="Gender";
            $e="Age";
            $f="Date Of Birth";
            $g="Phone No";
            $h="Email";
            $i="Account Type";
            $j="Balance";
            $k="Address";


            $name=$rws[0];
            $acc_no=$rws[1];
            $gender=$rws[2];
            $age=$rws[3];
            $dob=$rws[4];
            $phone=$rws[5];
            $email=$rws[6];
            $acc_type=$rws[7];
            $amt=$rws[8];
            $address=$rws[9];
    


        // echo "<script>alert('account deleted')</script>";
       // header('location:delete_customer.php');
    }

?>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Search Customer</title>
        
        <link rel="stylesheet" href="newcss.css">
    </head>
<?php include 'header.php'; ?>
<div class='content_addstaff'>
    <?php include 'admin_navbar.php'?>
            <div class='addstaff'>

<form method="post">
            <table align="center">
                <tr><td colspan='2' align='center' style='color:#2E4372;'><h3><u>Search Customer</u></h3></td></tr>
                <tr>
                    <td> Account No</td>
                    <td><input type="text" name="acc_no" required=""/></td>
                </tr>
                <tr>
                    <td colspan="2" align='center' style='padding-top:20px'><input type="submit" name="submit" value="Search Customer" class="addstaff_button"/></td>
                </tr>
            </table>
            <table align="center">
                <tr><td colspan='2' align='center' style='color:#2E4372;'><h3><u><?php echo $a;?></u></h3></td></tr>
                <tr>
                    <td><?php echo $b;?></td>
                    <td><?php echo $name;?></td>
                </tr>
                <tr>
                    <td><?php echo $c;?></td>
                    <td><?php echo $acc_no;?></td>
                </tr>
                <tr>
                    <td> <?php echo $d;?></td>
                    <td><?php echo $gender;?></td>
                </tr>
                <tr>
                    <td> <?php echo $e;?></td>
                    <td><?php echo $age;?></td>
                </tr>
                <tr>
                    <td><?php echo $f;?></td>
                    <td><?php echo $dob;?></td>
                </tr>
                <tr>
                    <td><?php echo $g;?></td>
                    <td><?php echo $phone;?></td>
                </tr>
                <tr>
                    <td><?php echo $h;?></td>
                    <td><?php echo $email;?></td>
                </tr>
                <tr>
                    <td><?php echo $i;?></td>
                    <td><?php echo $acc_type;?></td>
                </tr>
                <tr>
                    <td><?php echo $j;?></td>
                    <td><?php echo $amt;?></td>
                </tr>
                <tr>
                    <td><?php echo $k;?></td>
                    <td><?php echo $address;?></td>
                </tr>

            </table>
            </div>
    </div>
        </form>
<?php include 'footer.php';?>
    </body>
</html>
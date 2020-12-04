<?php 

session_start();
     
include 'dbconn.php';   
if(!isset($_SESSION['admin_login'])) 
    header('location:adminlogin.php');   
   

       


?>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add Customer</title>
        
        <link rel="stylesheet" href="newcss.css">
    </head>
<?php include 'header.php'; ?>
<div class='content_addstaff'>
    <?php include 'admin_navbar.php'?>
            <div class='addstaff'>

<form method="post">
    <h3 align="center" style="color:#2E4372;">Customer List</h3>
            <table align="center" border="1">
                <tr><td>Name</td><td>Account No</td><td>Gender</td><td>Age</td><td>DOB</td><td>Phone</td><td>Email</td><td>Account Type</td><td>Amount</td><td>Address</td></tr>

        
                <?php
                    $sql="SELECT * FROM customer";

                    $result=mysqli_query($conn,$sql);

                    if($result->num_rows > 0)
                    {
                        while($row=mysqli_fetch_assoc($result))
                        {
                            echo "<tr><td>".$row["cust_name"]."</td>
                             <td>".$row["acc_no"]."</td><td>".$row["gender"]."</td><td>".$row["age"]."</td><td>".$row["dob"]."</td><td>".$row["mobileno"]."</td><td>".$row["email"]."</td><td>".$row["acc_type"]."</td><td>".$row["amount"]."</td><td>".$row['address']."</td></tr>";
                        }
                    }
                    else
                        echo "no one has account";
                ?>

            </table>
            </div>
    </div>
        </form>
<?php include 'footer.php';?>
    </body>
</html>
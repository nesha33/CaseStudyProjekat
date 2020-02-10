<?php

$conn = mysqli_connect("localhost","id12538981_dedovicneda","neda1234","id12538981_baza");

 mysqli_query($conn,"SET NAMES 'utf8'");

 $username = $_POST["UserName"];
 $password = $_POST["Password"];

 
 $query = 'Select * from korisnici where email = "'.$username.'"';
 $result = mysqli_query($conn,$query) or die('error: ' . mysqli_error());
 
 
 if(mysqli_num_rows($result) == 1){
   
    echo "1";
  
 }else{
     $Sql_Query = "INSERT INTO korisnici (email,password) values ('$username','$password')";

 if(mysqli_query($conn,$Sql_Query)) {
 echo '2';
}
else {
 echo '0';
 }
 }
 
?>
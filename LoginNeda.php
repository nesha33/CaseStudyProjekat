<?php

 

 
 
  $conn = mysqli_connect("localhost","id12538981_dedovicneda","neda1234","id12538981_baza");
 
 mysqli_query($conn,"SET NAMES 'utf8'");
 
 $username = $_POST["UserName"];
 $password = $_POST["Password"];
 
 
$query = "SELECT * FROM korisnici";
 $result = mysqli_query($conn,$query) or die('error: ' . mysqli_error());
 
 while($row = $result->fetch_assoc()) {
    
    if($row["email"] == $username && $row["password"] == $password){
        echo $row["id"];
        return;
    }
    
    }
    
    
    echo "0";














 
 
 
 

?>
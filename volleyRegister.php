<?php
require_once('dbConnect.php');
    $username = $_POST["username"];
    $password = $_POST["password"];
    $cell = $_POST["cell"];
     function registerUser() {
        global $con, $username, $password, $cell;
        $passwordHash = password_hash($password, PASSWORD_DEFAULT);
        $statement = mysqli_prepare($con, "INSERT INTO users (username, password, cell) VALUES (?, ?, ?)");
        mysqli_stmt_bind_param($statement, "sss", $username, $passwordHash, $cell);
        mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);     
    }
    function usernameAvailable() {
        global $con, $username;
        $statement = mysqli_prepare($con, "SELECT * FROM users WHERE username = ?"); 
        mysqli_stmt_bind_param($statement, "s", $username);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        $count = mysqli_stmt_num_rows($statement);
        mysqli_stmt_close($statement); 
        if ($count < 1){
            return true; 
        }else {
            return false; 
        }
    }
    $response = array();
    $response["success"] = false;  
    if (usernameAvailable()){
        registerUser();
        $response["success"] = true;  
    }
    
    echo json_encode($response);
    ?>
<?php
require_once('dbConnect.php');
$username = $_POST["username"];
$password = $_POST["password"];
$statement = mysqli_prepare($con, "SELECT * FROM users WHERE username = ?");
mysqli_stmt_bind_param($statement, "s", $username);
mysqli_stmt_execute($statement);
mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement, $colUserID, $colUsername, $colPassword, $colCell);
    
$response = array();
$response["success"] = false;  

while(mysqli_stmt_fetch($statement)){
		$verify = password_verify($password, $colPassword);
        if ($verify) {
        $response["success"] = true; 
        $response["cell"] = $colCell; 
    }
}
echo json_encode($response);
?>
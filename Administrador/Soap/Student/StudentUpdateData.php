<?php

//ruta de la clase nusoap.php
require_once "../../vendor/econea/nusoap/src/nusoap.php";
//Nombre del servicio
$namespace = "Soap/Student/StudentUpdateDataSOAP";
$server = new soap_server();
$server->configureWSDL("studentUpdateDataSoap",$namespace); 
$server->wsdl->schemaTargeNamespace = $namespace;

//Estructura del servicio 
$server->wsdl->addComplexType(
    'studentUpdateDataSoap',
    'complexType',
    'struct',
    'all',
    '',
    array(
        'user_role_input' => array('name' => 'user_role_input', 'type' => 'xsd:string'),
        'id_student_input' => array('name' => 'id_student_input', 'type' => 'xsd:string'),
        'dni_input' => array('name' => 'dni_input', 'type' => 'xsd:string'),
        'email_input' => array('name' => 'email_input', 'type' => 'xsd:string'),
        'lastname_input' => array('name' => 'lastname_input', 'type' => 'xsd:string'),
        'name_input' => array('name' => 'name_input', 'type' => 'xsd:string'),
        'phone_input' => array('name' => 'phone_input', 'type' => 'xsd:string'),
        'pass_input' => array('name' => 'pass_input', 'type' => 'xsd:string')
    ) 
);

//Estructura de la Respuesta del Servicio
$server->wsdl->addComplexType(
    'response',
    'complexType',
    'struct',
    'all',
    '',
    array(
        'Mensaje' => array('name' => 'Mensaje', 'type' => 'xsd:string'),
        'Resultado' => array('name' => 'Resultado', 'type' => 'xsd:boolean'),
    )
);

$server->register(
    "studentUpdateDataSoapService",
    array("studentUpdateDataSoap" => "tns:studentUpdateDataSoap"),
    array("studentUpdateDataSoap" => "tns:response"),
    $namespace,
    false,
    "rpc",
    "encoded",
    "Actualiza los datos de un estudiante validando si lo realiza un Admin"
);

function studentUpdateDataSoapService($request){

    $mensaje = "Estudiante actualizado corectamente";
    $resultado = true;

    //Guardo la request
    $role = $request['user_role_input'];
    $id = $request['id_student_input'];
    $email = $request['email_input'];
    $phone = $request['phone_input'];
    $pass = $request['pass_input'];//NO TE OLVIDES DE HASHEAR LA PASSWORD

    $connection = new mysqli("localhost", "root", "", "db_api_rest_soap"); //Seteo la conexion


   $getStudentQuery = "SELECT * FROM user WHERE id=$id"; //Query para traer el estudiante a actualizar
   $resultGetStudent = mysqli_query($connection, $getStudentQuery);

   if (mysqli_num_rows($resultGetStudent) > 0) {
    
        $student = mysqli_fetch_assoc($resultGetStudent);

        $email = $email != null ? $email : $student["email"];
        $phone = $phone != null ? $phone : $student["phone"];
        $pass = $pass != null ? $pass : $student["password"]; //HASHEAR ACAAAAAAA

        if($role == "admin"){ //si es role admin le permito hace el cambio de datos sensibles
            $dni = $request['dni_input'] != null ? $request['dni_input'] : $student["dni"];
            $name = $request['name_input'] != null ? $request['name_input'] : $student["name"];
            $lastname = $request['lastname_input'] != null ? $request['lastname_input'] : $student["lastname"];
        }

    } else {
        $resultado = false;
        $mensaje = "Error al traer estudiante: ".mysqli_error($connection); //informo el error

        return array(
            "Mensaje" => $mensaje,
            "Resultado" => $resultado
        );
    }

    $updateStudentQuery = "UPDATE user SET dni='$dni', email='$email', lastname='$lastname', name='$name', phone='$phone', password='$pass' WHERE id=$id"; //Query para actualizar estudiante
    if(mysqli_query($connection, $updateStudentQuery)){

    } else {
            $resultado = false;
            $mensaje = "Error al actualizar estudiante: ".mysqli_error($connection); //informo el error
    }

    return array(
        "Mensaje" => $mensaje,
        "Resultado" => $resultado
    );
}


$POST_DATA = file_get_contents("php://input");
$server->service($POST_DATA);
exit();

?>
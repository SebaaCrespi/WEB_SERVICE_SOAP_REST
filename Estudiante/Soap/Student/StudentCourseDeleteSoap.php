<?php

//ruta de la clase nusoap.php
require_once "../../vendor/econea/nusoap/src/nusoap.php";
//Nombre del servicio
$namespace = "Soap/StudentCourseDeleteSOAP";
$server = new soap_server();
$server->configureWSDL("studentCourseDelete",$namespace); 
$server->wsdl->schemaTargeNamespace = $namespace;

//Estructura del servicio 
$server->wsdl->addComplexType(
    'studentCourseDelete',
    'complexType',
    'struct',
    'all',
    '',
    array(
        'id_course_input' => array('name' => 'id_course_input', 'type' => 'xsd:string'),
        'userType_input' => array('name' => 'userType_input', 'type' => 'xsd:string')
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
    "studentCourseDeleteService",
    array("studentCourseDelete" => "tns:studentCourseDelete"),
    array("studentCourseDelete" => "tns:response"),
    $namespace,
    false,
    "rpc",
    "encoded",
    "Actualiza los datos de un usuario"
);

function studentCourseDeleteService($request){

        $userType = $request['userType_input'];
        $mensaje = "";
        $resultado = true;
    if (in_array($userType, array("teacher", "admin", "student"))){
        $id = $request['id_course_input'];
        
        $user_query = "DELETE FROM student_course WHERE id=$id"; //Para borrar el registro
        
        $connection = new mysqli("localhost", "root", "", "db_api_rest_soap"); //el segundo es el username, el tercero la password, el cuarto database y el quinto (opcional) el puerto
        
        if (mysqli_query($connection, $user_query)) { //si se borra el registro
                $mensaje = "$userType eliminado correctamente";
        } else { //si no se borra el registro
            $resultado = false;
            $mensaje = "Error padre: ".mysqli_error($connection); //informo el error
        }
    } else { //Si se envia tipo de usuario invalido (válidos son: admin, student y teacher)
        $mensaje = "Error: El usuario no se elimina, ya que el tipo $userType no es correcto"; 
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
<?php

//ruta de la clase nusoap.php
require_once "../../vendor/econea/nusoap/src/nusoap.php";
//Nombre del servicio
$namespace = "Soap/Student/StudentGetCourseSOAP";
$server = new soap_server();
$server->configureWSDL("studentGetCourseSoap",$namespace); 
$server->wsdl->schemaTargeNamespace = $namespace;
//Estructura del servicio 
$server->wsdl->addComplexType(
    'listaDeObjetos',
    'complexType',
    'array',
    '',
    'SOAP-ENC:Array',
    array(),
    array(
        array('ref' => 'SOAP-ENC:arrayType', 'wsdl:arrayType' => 'tns:object_to_list[]')
    )
);

$server->wsdl->addComplexType(
    'recibe_parametros',
    'complexType',
    'struct',
    'all',
    '',
    array()
);

$server->wsdl->addComplexType(
    'response',
    'complexType',
    'struct',
    'all',
    '',
    array(
        'Objetos' => array('name' => 'Objetos', 'type' => 'tns:listaDeObjetos'),
        'Resultado' => array('name' => 'Resultado', 'type' => 'xsd:boolean')
    )
);

$server->register(
    'studentGetCourseSoapService',
    array('name' => 'tns:recibe_parametros'),
    array('name' => 'tns:response'),
    $namespace,
    false,
    'rpc',
    'encoded',
    'Recibe una orden de compra y regresa un número de autorización'
);

function studentGetCourseSoapService($request){

    $array_objects = array();
    $resultado = false;

    $user_query = "SELECT * FROM course WHERE 1";
    $connection = new mysqli("localhost", "root", "", "db_api_rest_soap"); //el segundo es el username, el tercero la password, el cuarto database y el quinto (opcional) el puerto
    
    $resultado_query = mysqli_query($connection, $user_query);

    while($response_query_row = mysqli_fetch_array($resultado_query)){
        // conseguir nombre de la materia
        $matter_query = "SELECT * FROM matter WHERE id=".$response_query_row['matter_id'];
        $resultado_query_matter = mysqli_query($connection, $matter_query);
        $response_query_matter_row = mysqli_fetch_array($resultado_query_matter);
        // conseguir id del docente
        $teacher_query = "SELECT * FROM teacher_course WHERE course_id=".$response_query_row['id'];
        $resultado_query_teacher = mysqli_query($connection, $teacher_query);
        $response_query_teacher_row = mysqli_fetch_array($resultado_query_teacher);
        $prueba = $response_query_teacher_row;
        // conseguir nombre del docente
        $teacher_name_query = "SELECT * FROM user WHERE id=".$response_query_teacher_row['teacher_id'];
        $resultado_query_teacher_name = mysqli_query($connection, $teacher_name_query);
        $response_query_teacher_name_row = mysqli_fetch_array($resultado_query_teacher_name);
        $objeto = [    
            "course_id" => $response_query_row['id'], 
            "matter_id" => $response_query_row['matter_id'],
            "matter_name" => $response_query_matter_row['name'],
            "teacher_id" => $response_query_teacher_row['teacher_id'],
            "teacher_name" => $response_query_teacher_name_row['lastname']." ".$response_query_teacher_name_row['name'],
            "until_change_note" => $response_query_row['until_change_note'],
            "date_start" => $response_query_row['date_start'],
            "date_end" => $response_query_row['date_end'],
            "day_course" => $response_query_row['day_course'],
            "turn" => $response_query_row['turn']
        ];
    
        array_push($array_objects, $objeto);
        $resultado = true;

    }

    return array(
        "Objetos" => $array_objects,
        "Resultado" => $resultado
    );
}
$POST_DATA = file_get_contents("php://input");
$server->service($POST_DATA);
exit();

?>
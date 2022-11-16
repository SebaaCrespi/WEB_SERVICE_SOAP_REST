<?php

//ruta de la clase nusoap.php
require_once "../../vendor/econea/nusoap/src/nusoap.php";
//Nombre del servicio
$namespace = "Soap/StudentGetFinalNoteSOAP";
$server = new soap_server();
$server->configureWSDL("studentGetFinalNoteSoap",$namespace); 
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
        array('ref' => 'SOAP-ENC:arrayType', 'wsdl:arrayType' => 'tns:object_to_list[]'),        
    )
);

$server->wsdl->addComplexType(
    'recibe_parametros',
    'complexType',
    'struct',
    'all',
    '',
    array(
        'student_id' => array('name' => 'student_id', 'type' => 'xsd:string'),
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
        'Objetos' => array('name' => 'Objetos', 'type' => 'tns:listaDeObjetos'),
        'Resultado' => array('name' => 'Resultado', 'type' => 'xsd:boolean')
    )
);

$server->register(
    'studentGetFinalNoteSoapService',
    array('name' => 'tns:recibe_parametros'),
    array('name' => 'tns:response'),
    $namespace,
    false,
    'rpc',
    'encoded',
    'Recibe una orden de compra y regresa un número de autorización'
);

function studentGetFinalNoteSoapService($request){
    $id = $request['student_id'];
    $array_objects = array();
    $resultado = false;

    $user_query = "SELECT m.name as nombre, m.year_carrer as anio, sc.note_first as nota1, sc.note_second as nota2, se.note as nota_final FROM student s 
                    INNER JOIN student_course sc ON s.id = sc.student_id 
                    INNER JOIN course c ON sc.course_id = c.id 
                    INNER JOIN matter m ON c.matter_id = m.id 
                    INNER JOIN exam e ON m.id = e.matter_id 
                    INNER JOIN student_exam se ON e.id = se.exam_id 
                    WHERE s.id=".$id;
    $connection = new mysqli("localhost", "root", "", "db_api_rest_soap"); //el segundo es el username, el tercero la password, el cuarto database y el quinto (opcional) el puerto
    
    $resultado_query = mysqli_query($connection, $user_query);
    $contador_promedio = 0;
    $acumulador_notas = 0;
    while($response_query_row = mysqli_fetch_array($resultado_query)){
        $promedio_nota_cursada = ($response_query_row['nota1'] + $response_query_row['nota2'])/2;
        $promedio_nota_final = ($promedio_nota_cursada + $response_query_row['nota_final'])/2;
        $contador_promedio = $contador_promedio+1;
        $acumulador_notas = $acumulador_notas+$promedio_nota_final;
        $objeto = [    
            "nombre" => $response_query_row['nombre'],
            "year_carrer" => $response_query_row['anio'],
            "nota_final" => $promedio_nota_final,
        ];
    
        array_push($array_objects, $objeto);
        $resultado = true;

    }
    $objeto = ["promedio_final" => $acumulador_notas/$contador_promedio];
    array_push($array_objects, $objeto);

    return array(
        "Objetos" => $array_objects,
        "Resultado" => $resultado
    );
}

$POST_DATA = file_get_contents("php://input");
$server->service($POST_DATA);
exit();

?>
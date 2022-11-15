<?php

//ruta de la clase nusoap.php
require_once "../../vendor/econea/nusoap/src/nusoap.php";
//Nombre del servicio
$namespace = "Soap/StudentEnrollCourseSOAP";
$server = new soap_server();
$server->configureWSDL("studentEnrollCourseSoap",$namespace); 
$server->wsdl->schemaTargeNamespace = $namespace;

//Estructura del servicio 
$server->wsdl->addComplexType(
    'studentEnrollCourseSoap',
    'complexType',
    'struct',
    'all',
    '',
    array(
        'course_id' => array('name' => 'course_id', 'type' => 'xsd:string'),
        'student_id' => array('name' => 'student_id', 'type' => 'xsd:string'),
        'day_course' => array('name' => 'day_course', 'type' => 'xsd:string'),
        'turn' => array('name' => 'turn', 'type' => 'xsd:string'),
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
    "studentEnrollCourseSoapService",
    array("studentEnrollCourseSoap" => "tns:studentEnrollCourseSoap"),
    array("studentEnrollCourseSoap" => "tns:response"),
    $namespace,
    false,
    "rpc",
    "encoded",
    "Inserta un estudiante en un curso"
);

function studentEnrollCourseSoapService($request){

        $course_id = $request['course_id'];
        $student_id = $request['student_id'];
        $day_course = $request['day_course'];
        $turn = $request['turn'];
        $mensaje = "";
        $resultado = true;
        $horario = true;
        
        $check_course_time_query = "SELECT * FROM student_course sc INNER JOIN course c ON sc.course_id = c.id WHERE sc.student_id=".$student_id;

        $user_query = "INSERT INTO student_course (course_id, student_id, note_first, note_second) VALUES
        ('$course_id', '$student_id', '0', '0')"; //Para insertar el padre        
        
        $connection = new mysqli("localhost", "root", "", "db_api_rest_soap"); //el segundo es el username, el tercero la password, el cuarto database y el quinto (opcional) el puerto
        $resultado_query_check = mysqli_query($connection, $check_course_time_query);

        while($response_query_row = mysqli_fetch_array($resultado_query_check)){
            if($response_query_row['day_course'] == $day_course && $response_query_row['turn'] == $turn){
                $horario = false;
                $mensaje = "Ya estas anotado a una materia en ese horario"; //informo el error
            }
        }

        if($horario){
            if (mysqli_query($connection, $user_query)) { //si se inserta el registro
                $mensaje = "agregado correctamente";
            } else { //si no se inserta el registro
                $resultado = false;
                $mensaje = "Error al insertar el registro: ".mysqli_error($connection); //informo el error
            }
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
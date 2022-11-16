import * as React from 'react';
import ResponsiveAppBar from './navBar.jsx'
import { useEffect, useState } from 'react';
import { getCourseStudents, getCourseStudentsExcel } from '../services/listCourses';
import { useNavigate, useSearchParams  } from 'react-router-dom';
import { FormGroup, Modal, ModalBody, ModalFooter, ModalHeader } from 'reactstrap';

function Home() {
    const [searchParams, setSearchParams] = useSearchParams();
    const [uploadFile, setUploadFile] = useState();
    const [modalCursada, setModalCursada] = useState(false);
    const [modalExamen, setModalExamen] = useState(false);
    const [list, setList] = useState([]);
    let navigate = useNavigate();
    const queryParameters = new URLSearchParams(window.location.search)
    const course_id_global = queryParameters.get("course_id")

    const onFileChange = event => {
        setUploadFile(event.target.files[0])
    }
    const onFileUpload = () => {        
        let formData = new FormData();
        formData.append('file', uploadFile);
        fetch('http://localhost:5000/upload_grades_by_excel',{
            method: 'POST',
            body: formData
        })
    }
    const onFileUploadExam = () => {
        let formData = new FormData();
        formData.append('file', uploadFile);
        var exam_id = 1;
        fetch('http://localhost:5000/upload_exam_grades_by_excel?exam_id='+exam_id,{
            method: 'POST',
            body: formData
        })
    }

    const alterModalCursada = () => {
        setModalCursada(prev => !prev)
    }
    const alterModalExamen = () => {
        setModalExamen(prev => !prev)
    }
    useEffect(() => {
        const queryParameters = new URLSearchParams(window.location.search)
        const course_id = queryParameters.get("course_id")
        console.log("param", course_id)
        let mounted = true;
        getCourseStudents(course_id).then(items => {
            if(mounted) {
                setList(items)
                console.log(items)
            }
        })
        return () => mounted = false;
    }, [])

	return(
		<div>
            <ResponsiveAppBar/>
            <div className='buttonNotes'>   
                <button class="btn btn-primary notes" onClick={alterModalCursada}>
                    Cargar notas de cursada
                </button>
                <Modal isOpen={modalCursada}>
                    <ModalHeader>
                    <div><h3>Cargar notas de cursada</h3></div>
                    </ModalHeader>

                    <ModalBody>                    
                        <FormGroup>
                            <input type="file" onChange={onFileChange} />
                        </FormGroup>
                    </ModalBody>

                    <ModalFooter>
                        <button class="btn btn-primary notes" onClick={onFileUpload}>
                            Subir archivo
                        </button>
                        <button class="btn btn-secondary notes" onClick={alterModalCursada}>
                            Cancelar
                        </button>
                    </ModalFooter>
                </Modal>               
                <button class="btn btn-primary notes" onClick={alterModalExamen}>
                    Cargar notas de examen
                </button>
                <Modal isOpen={modalExamen}>
                    <ModalHeader>
                    <div><h3>Cargar notas de examen</h3></div>
                    </ModalHeader>

                    <ModalBody>                    
                        <FormGroup>
                            <input type="file" onChange={onFileChange} />
                        </FormGroup>
                    </ModalBody>

                    <ModalFooter>
                        <button class="btn btn-primary notes" onClick={onFileUploadExam}>
                            Subir archivo
                        </button>
                        <button class="btn btn-secondary notes" onClick={alterModalExamen}>
                            Cancelar
                        </button>
                    </ModalFooter>
                </Modal> 
                <a class="btn btn-primary" href={`http://localhost:5000/course_students_to_excel?course_id=${course_id_global}&instance_type=0`} role="button">Descargar listado de alumnos</a>
            </div>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                    <th scope="col">DNI</th>
                    <th scope="col">Apellido</th>
                    <th scope="col">Nombre</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        list.map((student) => {
                            console.log(student)
                            return(
                                <tr key={student.dni.toString()} onClick={() => navigate(`/course/${student.dni}`)}>
                                    <th scope="row">{student.dni}</th>
                                    <td>{student.lastname}</td>
                                    <td>{student.name}</td>
                                </tr>
                            )
                        })
                    }
                </tbody>
            </table>
        </div>
	)

}

export default Home;
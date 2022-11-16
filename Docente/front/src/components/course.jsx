import * as React from 'react';
import ResponsiveAppBar from './navBar.jsx'
import { useEffect, useState } from 'react';
import { getCourseStudents, getCourseStudentsExcel } from '../services/listCourses';
import { useNavigate, useSearchParams  } from 'react-router-dom';

function Home() {
    const [searchParams, setSearchParams] = useSearchParams();
    const [uploadFile, setUploadFile] = useState();
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

        /* 
         myfile = request.files['file']
        print(myfile)
        filedir = './files'

        fout = open(filedir + '/' + myfile.filename,'wb')
        fout.write(myfile.read())
        fout.close()
        */
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
                <input type="file" onChange={onFileChange} />
                <button class="btn btn-primary notes" onClick={onFileUpload}>
                    Cargar notas de alumnos
                </button>
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
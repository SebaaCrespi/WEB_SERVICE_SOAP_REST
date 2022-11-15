import * as React from 'react';
import ResponsiveAppBar from './navBar.jsx'
import { useEffect, useState } from 'react';
import { getCourseStudents, getCourseStudentsExcel } from '../services/listCourses';
import { useNavigate, useSearchParams  } from 'react-router-dom';

function Home() {
    const [searchParams, setSearchParams] = useSearchParams();
    const [list, setList] = useState([]);
    let navigate = useNavigate();
    const queryParameters = new URLSearchParams(window.location.search)
    const course_id_global = queryParameters.get("course_id")

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
            <a class="btn btn-primary" href={`http://localhost:5000/course_students_to_excel?course_id=${course_id_global}&instance_type=0`} role="button">Descargar listado de alumnos</a>                            
        </div>
	)

}

export default Home;
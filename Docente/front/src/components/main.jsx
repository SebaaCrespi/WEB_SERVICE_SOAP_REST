import * as React from 'react';
import ResponsiveAppBar from './navBar.jsx'
import { useEffect, useState } from 'react';
import { getCourses } from '../services/listCourses';
import { useNavigate } from 'react-router-dom';

function Home() {

    const [list, setList] = useState([]);
    let navigate = useNavigate();

    useEffect(() => {
        let mounted = true;
        getCourses().then(items => {
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
                    <th scope="col">#</th>
                    <th scope="col">Nombre de la materia</th>
                    <th scope="col">Es cuatrimestral</th>
                    <th scope="col">Año de la materia</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        list.map((course) => {
                            return(
                                <tr key={course.id.toString()} onClick={() => navigate(`/course?course_id=${course.id}`)}>
                                    <th scope="row">{course.id}</th>
                                    <td>{course.name}</td>
                                    <td>{course.is_first_four_month}</td>
                                    <td>{course.year_carrer}</td>
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
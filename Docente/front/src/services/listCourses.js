export function getCourses() {
    return fetch('http://localhost:5000/get_teacher_courses_by_ID?teacher_id=2')
      .then(data => data.json())
  }

export function getCourseStudents(course_id) {
    return fetch(`http://localhost:5000/get_course_students?course_id=${course_id}`)
      .then(data => data.json())
  }

export function getCourseStudentsExcel() {
    return fetch(`http://localhost:5000/course_students_to_excel?course_id=1&instance_type=0`)
      .then(data => data.json())
  }
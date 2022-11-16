from importsAndConfigs import app
from intermediary.userIntermediary import IntermediaryUser
from intermediary.teacherIntermediary import IntermediaryTeacher
from intermediary.teacher_courseIntermediary import IntermediaryTeacherCourse
from intermediary.student_courseIntermediary import IntermediaryStudentCourse
from intermediary.studentExamIntermediary import IntermediaryStudentExam

@app.route('/', methods=['GET'])
def hello():
    return "Service (OK)"

# PUNTO A

@app.route('/get_users', methods=['GET'])
def get_users():
    return IntermediaryUser.get_users()

@app.route('/get_ID_by_dni', methods=['GET'])
def get_ID_by_dni():
    return IntermediaryUser.get_ID_by_dni("9876543")

@app.route('/get_user_by_ID', methods=['GET'])
def get_user_by_ID():
    return IntermediaryUser.get_user_by_ID()

@app.route('/get_teachers', methods=['GET'])
def get_teachers():
    return IntermediaryTeacher.get_teachers()

@app.route('/get_teacher_by_ID', methods=['GET'])
def get_teacher_by_ID():
    return IntermediaryTeacher.get_teacher_by_ID()

@app.route('/get_teachers_courses', methods=['GET'])
def get_teachers_courses():
    return IntermediaryTeacherCourse.get_teachers_courses()

@app.route('/get_teacher_courses_by_ID', methods=['GET'])
def get_teacher_courses_by_ID():
    return IntermediaryTeacherCourse.get_teacher_courses_by_ID()

# PUNTO B

@app.route('/get_course_students', methods=['GET'])
def get_course_students():
    return IntermediaryStudentCourse.get_course_students()

@app.route('/course_students_to_excel', methods=['GET'])
def course_students_to_excel():
    return IntermediaryStudentCourse.course_students_to_excel()


# PUNTO C

@app.route('/get_student_courses', methods=['GET'])
def get_student_courses():
    return IntermediaryStudentCourse.get_student_courses()

@app.route('/get_student_course_by_ID', methods=['GET'])
def get_student_course_by_ID():
    return IntermediaryStudentCourse.get_student_course_by_ID()

@app.route('/get_course_by_ID', methods=['GET'])
def get_course_by_ID():
    return IntermediaryStudentCourse.get_course_by_ID()

@app.route('/upload_grades_by_excel', methods=['POST'])
def upload_grades_by_excel():
    return IntermediaryStudentCourse.upload_grades_by_excel()

@app.route('/get_final_course_note_students', methods=['GET'])
def get_final_course_note_students():
    return IntermediaryStudentCourse.get_final_course_note_students()

# PUNTO D
@app.route('/get_students_exams', methods=['GET'])
def get_students_exams():
    return IntermediaryStudentExam.get_students_exams()

@app.route('/upload_exam_grades_by_excel', methods=['GET'])
def upload_exam_grades_by_excel():
    return IntermediaryStudentExam.upload_exam_grades_by_excel()

if __name__=='__main__':
	app.run(debug=True) 
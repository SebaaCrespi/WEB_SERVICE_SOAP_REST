from importsAndConfigs import app
from schemas.studentExamSchema import StudentExamSchema
from entities.studentExamEntity import StudentExamEntity
from intermediary.userIntermediary import IntermediaryUser
from intermediary.student_courseIntermediary import IntermediaryStudentCourse
from flask import request, jsonify
import pandas as pd

db = StudentExamEntity.prepare_table_student_exam_and_get_db()
# with app.app_context():
#     db.create_all() #creo todas las tablas (en este caso 1)

student_schema = StudentExamSchema() #Instanciamos para poder iteractuar con bd para ABM de uno
students_schemas = StudentExamSchema(many=True) #Para varios/muchos

class IntermediaryStudentExam():

    def get_students_exams():
        students_exams = StudentExamEntity.query.all()
        for student_exam in students_exams:
            print(student_exam.note)
        return 'OK'

    def get_matter_by_ID(id):
        student_exam = StudentExamEntity.query.filter(StudentExamEntity.id == id).first()
        return student_exam

    def upload_exam_grades_by_excel():
        exam_id = request.json['exam_id']
        df = pd.read_excel('./files/exam_grades.xlsx')
        notes = []
        for index, row in df.iterrows():
            student_id = IntermediaryUser.get_ID_by_dni(row['dni'])
            student_exam = StudentExamEntity.query.filter(StudentExamEntity.student_id == student_id, StudentExamEntity.exam_id == exam_id).first()
            student_exam.note = row['note']
            db.session.commit()
            notes.append(student_exam)
        result = students_schemas.dump(notes)
        return jsonify(result)

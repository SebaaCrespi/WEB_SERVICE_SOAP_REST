from importsAndConfigs import app
from schemas.teacherSchema import TeacherSchema
from schemas.userSchema import UserSchema
from schemas.matterSchema import MatterSchema
from entities.teacher_courseEntity import TeacherCourseEntity
from .courseIntermediary import IntermediaryCourse
from flask import request, jsonify
import json

db = TeacherCourseEntity.prepare_table_teacher_course_and_get_db()
# with app.app_context():
#     db.create_all() #creo todas las tablas (en este caso 1)

teacher_schema = TeacherSchema() #Instanciamos para poder iteractuar con bd para ABM de uno
teacher_schemas = TeacherSchema(many=True) #Para varios/muchos
user_schema = UserSchema()
matter_schema = MatterSchema()
matters_schema = MatterSchema(many=True)

class IntermediaryTeacherCourse():

    def get_teachers_courses():
        teachers_courses = TeacherCourseEntity.query.all()
        for teacher_course in teachers_courses:
            print(teacher_course.teacher_id)
        return 'OK'

    def get_teacher_courses_by_ID():
        args = request.args
        id = args.get("teacher_id")        
        teacher_courses = TeacherCourseEntity.query.filter(TeacherCourseEntity.teacher_id == id).all()
        courses = []
        for teacher_course in teacher_courses:
            course = IntermediaryCourse.get_course_by_ID(teacher_course.course_id)
            courses.append(course)
        result = matters_schema.dump(courses)
        return jsonify(result)

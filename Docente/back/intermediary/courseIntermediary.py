from importsAndConfigs import app
from schemas.courseSchema import CourseSchema
from entities.courseEntity import CourseEntity
from .matterIntermediary import IntermediaryMatter
from flask import request

db = CourseEntity.prepare_table_course_and_get_db()
# with app.app_context():
#     db.create_all() #creo todas las tablas (en este caso 1)

course_schema = CourseSchema() #Instanciamos para poder iteractuar con bd para ABM de uno
course_schemas = CourseSchema(many=True) #Para varios/muchos

class IntermediaryCourse():

    def get_courses():
        courses = CourseEntity.query.all()
        for course in courses:
            print(course.teacher_id)
        return 'OK'

    def get_course_by_ID():
        id = request.json['id']
        course = CourseEntity.query.filter(CourseEntity.id == id).first()
        return 'OK'

    def get_course_by_ID(id):
        course = CourseEntity.query.filter(CourseEntity.id == id).first()
        matter = IntermediaryMatter.get_matter_by_ID(course.matter_id)
        return matter
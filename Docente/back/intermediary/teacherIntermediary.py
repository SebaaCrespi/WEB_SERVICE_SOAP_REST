from importsAndConfigs import app
from schemas.teacherSchema import TeacherSchema
from schemas.userSchema import UserSchema
from entities.teacherEntity import TeacherEntity
from .userIntermediary import IntermediaryUser
from flask import request

db = TeacherEntity.prepare_table_teacher_and_get_db()
# with app.app_context():
#     db.create_all() #creo todas las tablas (en este caso 1)

teacher_schema = TeacherSchema() #Instanciamos para poder iteractuar con bd para ABM de uno
teacher_schemas = TeacherSchema(many=True) #Para varios/muchos
user_schema = UserSchema()

class IntermediaryTeacher():

    def get_teachers():
        teachers = TeacherEntity.query.all()
        for teacher in teachers:
            print(teacher.id)
        return 'OK'

    def get_teacher_by_ID():
        id = request.json['id']
        teacher = TeacherEntity.query.get(id)
        user = IntermediaryUser.get_user_by_ID(teacher.id)
        print(user.name)
        return user_schema.jsonify(user)

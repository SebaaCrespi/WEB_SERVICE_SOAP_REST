from importsAndConfigs import app
from schemas.userSchema import UserSchema
from entities.userEntity import UserEntity
from flask import request

db = UserEntity.prepare_table_user_and_get_db()
# with app.app_context():
#     db.create_all() #creo todas las tablas (en este caso 1)

user_schema = UserSchema() #Instanciamos para poder iteractuar con bd para ABM de uno
user_schemas = UserSchema(many=True) #Para varios/muchos

class IntermediaryUser():

    def get_users():
        users = UserEntity.query.all()
        for user in users:
            print(user.name)
        return 'OK'

    def get_user_by_ID():
        id = request.json['id']
        user = UserEntity.query.get(id)
        print(user.name)
        return 'OK'

    def get_user_by_ID(id):
        user = UserEntity.query.get(id)        
        return user

    def add_new_user():
        data = request.json
        new_match = UserEntity(data) #creo una instancia
        db.session.add(new_match) #agrego a la bd
        db.session.commit() #termino
        return user_schema.jsonify(new_match)
    
    def get_ID_by_dni(dni):
        user = UserEntity.query.filter(UserEntity.dni == dni).first()        
        return user.id
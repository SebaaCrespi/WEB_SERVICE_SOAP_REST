from importsAndConfigs import db
from sqlalchemy.dialects.mysql import LONGTEXT

class UserEntity(db.Model):
    __tablename__ = 'user'
    id = db.Column(db.Integer, primary_key=True)
    dni = db.Column(db.String(255))
    email = db.Column(db.String(180))
    lastname = db.Column(db.String(255))
    name = db.Column(db.String(255))
    password = db.Column(db.String(255))
    phone = db.Column(db.String(255))
    roles = db.Column(LONGTEXT)
    discr = db.Column(db.String(255))

    def __init__(self, data): #constructor, se ejecuta cada vez que se instancia la clase
        self.id = data['id']
        self.dni = data['dni']
        self.email = data['email']
        self.lastname = data['lastname']
        self.name = data['name']
        self.password = data['password']
        self.phone = data['phone']
        self.roles = data['roles']
        self.discr = data['discr']

    def prepare_table_user_and_get_db():
        return db
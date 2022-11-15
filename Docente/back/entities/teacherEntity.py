from importsAndConfigs import db

class TeacherEntity(db.Model):
    __tablename__ = 'teacher'
    id = db.Column(db.Integer, primary_key=True)

    def __init__(self, data): #constructor, se ejecuta cada vez que se instancia la clase
        self.id = data['id']

    def prepare_table_teacher_and_get_db():
        return db
from importsAndConfigs import db

class TeacherCourseEntity(db.Model):
    __tablename__ = 'teacher_course'
    teacher_id = db.Column(db.Integer, primary_key=True)
    course_id = db.Column(db.Integer, primary_key=True)

    def __init__(self, data): #constructor, se ejecuta cada vez que se instancia la clase
        self.id = data['teacher_id']
        self.id = data['course_id']

    def prepare_table_teacher_course_and_get_db():
        return db
from importsAndConfigs import db

class StudentCourseEntity(db.Model):
    __tablename__ = 'student_course'
    id = db.Column(db.Integer, primary_key=True)
    course_id = db.Column(db.Integer)
    student_id = db.Column(db.Integer)
    note_first = db.Column(db.Integer)
    note_second = db.Column(db.Integer)

    def __init__(self, data): #constructor, se ejecuta cada vez que se instancia la clase
        self.id = data['id']
        self.course_id = data['course_id']
        self.student_id = data['student_id']
        self.note_first = data['note_first']
        self.note_second = data['note_second']

    def prepare_table_student_course_and_get_db():
        return db
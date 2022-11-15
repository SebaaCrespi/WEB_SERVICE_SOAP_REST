from importsAndConfigs import db
from sqlalchemy.dialects.mysql import TINYINT

class StudentExamEntity(db.Model):
    __tablename__ = 'student_exam'
    id = db.Column(db.Integer, primary_key=True)
    exam_id = db.Column(db.Integer)
    student_id = db.Column(db.Integer)
    note = db.Column(db.Integer)
    is_active = db.Column(TINYINT)

    def __init__(self, data): #constructor, se ejecuta cada vez que se instancia la clase
        self.id = data['id']
        self.exam_id = data['exam_id']
        self.student_id = data['student_id']
        self.note = data['note']
        self.is_active = data['is_active']

    def prepare_table_student_exam_and_get_db():
        return db
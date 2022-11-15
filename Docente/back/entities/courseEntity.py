from importsAndConfigs import db

class CourseEntity(db.Model):
    __tablename__ = 'course'
    id = db.Column(db.Integer, primary_key=True)
    matter_id = db.Column(db.Integer)
    until_change_note = db.Column(db.Date)
    date_start = db.Column(db.Date)
    date_end = db.Column(db.Date)
    day_course = db.Column(db.String(45))
    turn = db.Column(db.String(45))

    def __init__(self, data): #constructor, se ejecuta cada vez que se instancia la clase
        self.id = data['id']
        self.matter_id = data['matter_id']
        self.until_change_note = data['until_change_note']
        self.date_start = data['date_start']
        self.date_end = data['date_end']
        self.day_course = data['day_course']
        self.turn = data['turn']

    def prepare_table_course_and_get_db():
        return db
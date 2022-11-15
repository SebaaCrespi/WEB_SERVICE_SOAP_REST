from importsAndConfigs import db
from sqlalchemy.dialects.mysql import TINYINT

class MatterEntity(db.Model):
    __tablename__ = 'matter'
    id = db.Column(db.Integer, primary_key=True)
    is_first_four_month = db.Column(TINYINT)
    name = db.Column(db.String(255))
    year_carrer = db.Column(db.String(255))

    def __init__(self, data): #constructor, se ejecuta cada vez que se instancia la clase
        self.id = data['id']
        self.is_first_four_month = data['is_first_four_month']
        self.name = data['name']
        self.year_carrer = data['year_carrer']

    def prepare_table_matter_and_get_db():
        return db
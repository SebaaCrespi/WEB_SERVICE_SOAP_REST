from importsAndConfigs import app
from schemas.matterSchema import MatterSchema
from entities.matterEntity import MatterEntity
from flask import request

db = MatterEntity.prepare_table_matter_and_get_db()
# with app.app_context():
#     db.create_all() #creo todas las tablas (en este caso 1)

matter_schema = MatterSchema() #Instanciamos para poder iteractuar con bd para ABM de uno
matter_schemas = MatterSchema(many=True) #Para varios/muchos

class IntermediaryMatter():

    def get_matters():
        matters = MatterEntity.query.all()
        for matter in matters:
            print(matter.name)
        return 'OK'

    def get_matter_by_ID(id):
        matter = MatterEntity.query.filter(MatterEntity.id == id).first()
        return matter

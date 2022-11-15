from importsAndConfigs import ma 

class MatterSchema(ma.Schema):
	class Meta:
		fields = (
				'id',
				'is_first_four_month',
                'name',
                'year_carrer',
				)
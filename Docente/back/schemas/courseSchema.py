from importsAndConfigs import ma 

class CourseSchema(ma.Schema):
	class Meta:
		fields = (
				'id',
				'matter_id ',
                'until_change_note',
                'date_start',
                'date_end',
                'day_course',
                'turn',
				)
from importsAndConfigs import ma 

class StudentExamSchema(ma.Schema):
	class Meta:
		fields = (
				'id',
				'exam_id',
                'student_id',
                'note',
                'is_active',
				)
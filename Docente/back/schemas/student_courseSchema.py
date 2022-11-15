from importsAndConfigs import ma 

class StudentCourseSchema(ma.Schema):
	class Meta:
		fields = (
				'id',
				'course_id',
                'student_id',
                'note_first',
                'note_second',
				)
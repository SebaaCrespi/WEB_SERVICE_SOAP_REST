from importsAndConfigs import ma 

class TeacherCourseSchema(ma.Schema):
	class Meta:
		fields = (
				'teacher_id',
				'course_id',
				)
from importsAndConfigs import ma 

class UserSchema(ma.Schema):
	class Meta:
		fields = (
				'id',
				'dni',
                'email',
                'lastname',
                'name',
                'password',
                'phone',
                'roles',
                'discr'
				)
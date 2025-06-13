```toml
name = 'Update Password'
description = 'edit only password'
method = 'PATCH'
url = 'http://localhost:8080/api/user/100/change-password'
sortWeight = 5000000
id = '0dab51a8-fa5e-4f02-9636-241ef72d7723'

[body]
type = 'JSON'
raw = '''
{
  "email": "bob.sull@gmail.engsk",
  "password": "12345678",
  "comparePassword": "12345678"
}'''
```

```toml
name = 'Delete'
description = 'Change isActive True to False'
method = 'DELETE'
url = 'http://localhost:8080/api/user/15/delete'
sortWeight = 6000000
id = '2080920e-a51e-42ee-9b83-eaeec9975d8c'

[body]
type = 'JSON'
raw = '''
{
  "email": "bob.sull@gmail.engsk",
  "password": "12345678",
  "comparePassword": "12345678"
}'''
```

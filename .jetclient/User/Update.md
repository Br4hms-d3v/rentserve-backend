```toml
name = 'Update'
description = 'edit an user'
method = 'PUT'
url = 'http://localhost:8080/api/user/99/edit'
sortWeight = 4000000
id = '2fbb1329-2889-49e1-bd9d-3f2679e4fc87'

[body]
type = 'JSON'
raw = '''
{
  "name": "Wanda Wasselin",
  "firstName": "Wanda",
  "birthdate": "1972-03-1",
  "pseudo": "Wandi",
  "email": "wanda.wasselin@hotmail.com",
  "street": "1 rue van aa",
  "city": "Bruxelles",
  "zipCode": "1000"
}'''
```

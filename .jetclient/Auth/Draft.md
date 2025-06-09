```toml
name = 'Draft'
method = 'POST'
url = 'http://localhost:8080/registration'
sortWeight = 1000000
id = '484bc45a-3a49-4b0b-9c76-59a1432703c1'

[body]
type = 'JSON'
raw = '''
{
  "name": "Bobette",
  "firstName": "bobette",
  "birthdate": "1950-06-09",
  "pseudo": "bobette1",
  "email": "bobette@gmail.ezn",
  "password": "12345678",
  "street": "123 rue ikea",
  "city": "1000",
  "zipCode": "Bruxelles",
}'''
```

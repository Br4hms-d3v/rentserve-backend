```toml
name = 'Get user'
description = 'Display data user by id'
method = 'GET'
url = 'http://localhost:8080/api/user/105'
sortWeight = 1000000
id = '15268fdc-ef31-4d81-a034-df49037b06a0'

[body]
type = 'JSON'
raw = '''
{
  "pseudo": "malik2",
  "password": "12345678",
}'''
```

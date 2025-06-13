```toml
name = 'List of users'
description = 'List of users by role'
method = 'GET'
url = 'http://localhost:8080/api/user/list/ADMIN'
sortWeight = 3000000
id = '5ededc33-da4d-4914-a96f-176e1d0c4267'

[body]
type = 'JSON'
raw = '''
{
  "pseudo": "malik2",
  "password": "12345678",
}'''
```

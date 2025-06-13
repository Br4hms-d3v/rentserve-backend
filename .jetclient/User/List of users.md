```toml
name = 'List of users'
description = 'Display list of all users'
method = 'GET'
url = 'http://localhost:8080/api/user/list'
sortWeight = 2000000
id = '1c77d091-6b35-4805-a15c-dd08e11771cc'

[body]
type = 'JSON'
raw = '''
{
  "pseudo": "malik2",
  "password": "12345678",
}'''
```

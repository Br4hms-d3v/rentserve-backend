```toml
name = 'Log in'
description = 'log in with an account '
method = 'POST'
url = 'http://localhost:8080/api/auth/login'
sortWeight = 2000000
id = '86da2dcd-8c2d-459e-a5eb-038799bb62df'

[body]
type = 'JSON'
raw = '''
{
  "pseudo": "malik2",
  "password": "12345678",
}'''
```

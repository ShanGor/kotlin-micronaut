# Micronaut sample project for Kotlin


### JWT enabled for clients
> Reference to the [guide](https://guides.micronaut.io/micronaut-security-jwt-kotlin/guide/index.html)
#### JWT token acquire
Post below json body to /login
```
{
	"username": "hello",
	"password": "world"
}
```
#### JWT token renew
Post below json body to /oauth/access_token
```
{
	"grant_type": "refresh_token",
	"refresh_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZWxsbyIsIm5iZiI6MTU4MTk1MDUzNCwicm9sZXMiOltdLCJpc3MiOiJrb3RsaW4tbWljcm9uYXV0IiwiaWF0IjoxNTgxOTUwNTM0fQ._S_bZcEdo2ZxBqbM6wcTR7RNj69rOO_1TOP5UqWw8CM"
}
```
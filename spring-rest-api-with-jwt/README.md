#spring-rest-api-with-jwt

##Open-Api Specification URL: http://localhost:8080/swagger-ui/index.html

## Authentication

> > To generate a JWT token, make a POST request to `http://localhost:8080/auth` with the following payload:

```json
{
  "email": "abc@test.com",
  "password": "test"
}
```

> > To test employee API endpoints please provide the generated jwt token as Bearer token

#Example

curl --location --request GET 'localhost:8080/employees' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYmNAdGVzdC5jb20iLCJpYXQiOjE3MTU3ODk4MDEsImV4cCI6MTcxNTgyNTgwMX0.R32s_0xSO85QUYOugJJr9L6SiLCdoIOW57i-Xk0Xy4A' \
--data '{
"employeeName": "Simon",
"salary": 1000
}'

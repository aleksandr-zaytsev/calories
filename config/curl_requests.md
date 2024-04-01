### get all meals for user
GET http://localhost:8080/topjava/rest/meals

### get meal by id
GET http://localhost:8080/topjava/rest/meals/100009

### create meal
POST http://localhost:8080/topjava/rest/meals
{
"dateTime": "2024-04-01T21:00:00",
"description": "Желудок стонет, просит кушать",
"calories": 1000
}

### update meal
PUT http://localhost:8080/topjava/rest/meals/100012
{
"dateTime": "2024-04-01T21:00:00",
"description": "Решил не переедать на ночь",
"calories": 100
}

### delete meal by id
DELETE http://localhost:8080/topjava/rest/meals/100012

### filter meals list without params
GET http://localhost:8080/topjava/rest/meals/filter

### filter meals list with only startDate param
GET http://localhost:8080/topjava/rest/meals/filter?startDate=2020-01-31

### filter meals list with all available params
GET http://localhost:8080/topjava/rest/meals/filter?startDate=2020-01-31&startTime=10:00&endDate=2020-01-31&endTime=20:00
# SpringBoot

My Sample project that uses SpringBoot.

Spring boot is Awesome!!! 
- Fewer or no configs
- Easy to setup (Took me less than a day!!) 

The project is a User REST API.

Database - postgreSQL, Mybatis

# Installation
1. Use user.sql to create users table in postgreSQL database.

2. In conf folder use the database.properties-template to create a database.properties file in this folder.

3. In the root folder run 
```gradle
	gradlew clean build
```
4. Execute the jar file in build/libs 
			
			OR
			
   In eclipse run the following class as a Java Application
   /src/com/keymane/Application.java



# Examples

```json
/user/add POST
Request Json
{ 
"nationalId":"1",
"email":"test@test.com",
"firstName":"test1",
"lastName":"test1",
"password":"pward123123123",
"phoneNumber":"0700111111"
}

Response Json
{"success":true,"messages":{"message":"The User Account has been created successfully.","title":""},"errors":{},"data":{},"targetUrl":null,"httpResponseCode":201,"message":"The User Account has been created successfully."}


/user/{id} POST
/user/phone/{phoneNumber} POST
Request Json
{ 
"nationalId":"1",
"email":"test@test.com",
"firstName":"test1_upate",
"lastName":"test1_upate",
"password":"pward123123123",
"phoneNumber":"0700111111"
}

Response Json
{"success":true,"messages":{"message":"The User Account has been updated successfully.","title":""},"errors":{},"data":{},"targetUrl":null,"httpResponseCode":200,"message":"The User Account has been updated successfully."}


/users GET

Response Json
{"success":true,"messages":{},"errors":{},"data":{"users":[{"id":null,"nationalId":1,"email":"test@test.com","firstName":"test1","lastName":"test1","phoneNumber":"0700111111","password":null,"newPassword":null}]},"targetUrl":null,"httpResponseCode":200,"message":null}
```

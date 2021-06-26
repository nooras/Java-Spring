## Details of User API Rest:

1. Com.users.UserAppilcation => Main file

2. Com.users.entity User.java => User Entity Class

3. Com.users.dto- UserDTO.java => User DTO(Data transfer Object) Class and validation

4.  Com.users.repository - UserRepository Extends CrudRepository. With the help of CrudRepository we can use save, findById, findALL etc methods.

5. Com.users.service

a. UserService-interface

b. 3 methods => UserServicelmpl class

- addUser > add user in DB

- updateUser => Update users Birthdate and pincode

- DeleteUser >>Delete User(Deactivate) with id

6. Com.users.api-3 api for Add, update and delete user User Api class=> For mapping post, delete, put request

7. Com.users.utility
    - Errorinfo java => For handling error
    - ExceptionControllAdvice => For handliding validation and exception
    - LoggingAspect => For log in file

8. Com.users.exception => For handling Exception

9. application.properties => Info related to db and some exception message

10. ValidationMessage.properties => For showing validation message

11. Log4j2.propertied => For log in file propeties

12. test.txt => All testcases
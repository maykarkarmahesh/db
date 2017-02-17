# db
deutche-bank-assignment


## PREREQUISITE
- IDE (Eclipse , Intellij)
- mavn
- POSTMAN

## BUILD
- `mvn -Plocal clean install` : Run this command through command prompt. It will executes the clean build life cycle and the install build phase in the default build life cycle.

## RUN
- Import project to IDE.
- Build project using above mentioend build instructions.
- Go to `Application.java` file and run it. It will initilise all the necessary things required to run the project.
- Open POSTMAN and import `https://www.getpostman.com/collections/3877bd8174bcc48b54d5`.
- Add shop details using `Add Shop` details.

## TEST CASE EXECUTION
There are two ways through which you can run the test cases :
- When you run `mvn -Plocal clean install` , it will run all the test cases as pom.xml contains `maven-surefire-plugin` to run test cases.
- Open ShopServiceImplTest.java and run it by clicking on `Run ShopServiceImplTest.java`



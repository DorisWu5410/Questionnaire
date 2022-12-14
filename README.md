# Questionnaire

## This is a backend of the questionnare built in Spring framework.

To start, add configuration (Tomcat) and connetct to database from AWS RDS by adding new data source. Infomation about the DB is provided in ApplicationConfig.java.  

## UML
![image](https://github.com/DorisWu5410/Questionnaire/blob/main/postman_screenshot/UML.png)

## Assumptions

1. The user need to sigup and login to start answering questions.
2. When calling the api that get a question (/question), it will return a question that the user has not answered.
3. After the user has submitted answers to all the questions, the endpoint (/question) will return no question and print a message: "you have answered all the questions!"

## Endpoints
1. (/register) User sign up.
2. (/login) User login
3. (/question) Get one not answered question
4. (/question/{qustionId}/submit-answer) Submit answer to a target question specified by questionId

## Postman test link

https://www.getpostman.com/collections/80aaa2f96dbe5cb20af6

## Sample procedure

1. User sign up. (/register)
![image](https://github.com/DorisWu5410/Questionnaire/blob/main/postman_screenshot/register.png)

2. User login. (/login)
![image](https://github.com/DorisWu5410/Questionnaire/blob/main/postman_screenshot/login.png)

3. Get question (/question)
![image](https://github.com/DorisWu5410/Questionnaire/blob/main/postman_screenshot/getQuestion1.png)

4. Answer question (/question/1/submit-answer)
![image](https://github.com/DorisWu5410/Questionnaire/blob/main/postman_screenshot/submitAnswer1.png)

![image](https://github.com/DorisWu5410/Questionnaire/blob/main/postman_screenshot/submitAnswer1_2.png)

5. Get question (/question)
![image](https://github.com/DorisWu5410/Questionnaire/blob/main/postman_screenshot/getQuestion2.png)

6. Answer question (/question/4/submit-answer)
![image](https://github.com/DorisWu5410/Questionnaire/blob/main/postman_screenshot/submitAnswer2.png)

7. Finished
![image](https://github.com/DorisWu5410/Questionnaire/blob/main/postman_screenshot/allAnswered.png)

## Reflections

To make the application scalable enough to handle various type of questions, it is a good solution to create an Question abstract class and endend it by different types of questions like TriviaQuestion, MatrixQuestion, by adding different implementations of input, validation criterion etc.  




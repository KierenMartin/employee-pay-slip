# employee-pay-slip
Repo for Seisma coding test.

**Assumptions Made**
  - Payment dates always start at the beginning of the month and end on the last day of the month in the input file. The program will ignore different numbers.
  - It is assumed that payments are always monthly and do not extend beyond a single month, eg. "01 March - 31 July" will not be processed correctly because it spans multiple months. The date will be treated as "01 March - 31 March" instead.
  - The amount of days in certain months is defined by the current year the program is run on (28 days in Feb in the year 2021).
  - Annual salaries inputted into the program are assumed to be integer values.
  - Months are inputted into the program as numbers in JSON, where 1 is January and 12 is December.

**How To Run**  
  On your local machine, it's possible to run this using the following steps (tested on Windows 10):
  1. Download project files.
  2. Open a command prompt and navigate to the project folder.
  3. Run the command `mvnw spring-boot:run`
  4. You should see the Spring Boot ASCII at some point and the command prompt will not return to accepting input.

At this point, it should be running.
- You can test if it is running by feeding it some JSON:
  `curl localhost:8080 -X POST -d {} -H "Content-type: text/plain"`
- It should return an error:
  `A JSONArray text must start with '[' at 1 [character 2 line 1]`

**Additional notes**
  - Project code has been written in Visual Studio Code using jdk-14 on Windows 10.
  - Erroneous input may or may not be correctly dealt with.
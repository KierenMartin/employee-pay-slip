# employee-pay-slip
Repo for Seisma coding test.

**Assumptions Made**
  - Payment dates always start at the beginning of the month and end on the last day of the month in the input file. The program will ignore different numbers.
  - It is assumed that payments are always monthly and do not extend beyond a single month, eg. "01 March - 31 July" will not be processed correctly because it spans multiple months. The date will be treated as "01 March - 31 March" instead.
  - The amount of days in certain months is defined by the current year the program is run on (28 days in Feb in the year 2021).
  - Annual salaries inputted into the program are assumed to be integer values. No decimals!
  - Months are inputted into the program as numbers in JSON. See *src/resources/jsoninput.json* for proof of this.

**How To Run**
  *These methods have only been tested in a Windows 10 environment*
  - To run the program, download the **target\employee-pay-slip-1.jar** file and run it from the command line using **java -jar employee-pay-slip-1.jar "input\file\location.csv"**.
  - Alternatively, you can run the **Run With Input File.bat** if you have downloaded the entire project repo.
  - You could also run the **Compile With Maven.bat** file if you happen to have maven installed and use that to compile a fresh jar file.

**Additional notes**
  - This project has been developed, tested and run in Visual Studio Code using jdk-14 on Windows 10.
  - Erroneous input may or may not be correctly dealt with, but there has been attempts at preventing as many cases as possible that would cause a crash.

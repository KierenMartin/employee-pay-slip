# employee-pay-slip
Repo for Seisma coding test.

**Assumptions Made**
  - Payment dates always start at the beginning of the month and end on the last day of the month in the input file. The program should be able to ignore different values, but this assumption was made anyway.
  - It is assumed that payments are always monthly and do not extend beyond a single month (eg "01 March - 31 July" will not be processed correctly). In fact, it would be sufficient to write "01 March" instead of the full "01 March - 31 March" with the way the program processes this data.
  - Erroneous input may or may not be correctly dealt with, but there has been attempts at preventing as many cases as possible that would cause a crash.
  - The amount of days in certain months is defined by the current year the program is run on (28 days in Feb in the year 2021).

**How To Run**
  *These methods have only been tested in a Windows 10 environment*
  - To run the program, download the **target\employee-pay-slip-1.jar** file and run it from the command line using **java -jar employee-pay-slip-1.jar "input\file\location.csv"**.
  - Alternatively, you can run the **Run With Input File.bat** if you have downloaded the entire project repo.

Additional notes:
  - This project has been developed, tested and run in Visual Studio Code using jdk-14.
  - I was keen to set this up on an AWS EC2 instance, but had issues with setting up payment information for my account that I realise would not be solveable within a short period of time.

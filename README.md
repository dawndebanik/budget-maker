# Splitwise Budget Maker

This command line app uses the Splitwise API to generate a report of the user's spending within in a given timeframe. On top of the categories offered by Splitwise, htis app also adds addtiitonl functionatlity to create user defined categories (that might be more sutied to the user's context) by usin a system of word-matching rules.

## Setup

1. Clone the repository.
2. Make sure your JAVA_HOME is pointing to JDK17. You can check that with the following command:
  `echo $JAVA_HOME`. If it's not JDK17, download JDK17 and point it to the same. For example, in Mac: 
  ```
  export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home
  ```
3. Login to slitwise and go to https://secure.splitwise.com/apps/new to register yourself to get your API key. (Fill out the fields in the form as per your wish - it doesn't really matter what you enter)
4. Create a file named `config.yml` under `/var/conf` directory. The contents of the file should be like as shown below. Paste your API key in the mentioned field.
```
apiKey: <your API key>

appConfig:
  splitwiseV3BaseUrl: https://secure.splitwise.com/api/v3.0
  getExpensesEndpoint: get_expenses
  getCurrentUserEndpoint: get_current_user
```
5. You're now all set!

## Running the app

1. Go to the root of the project directory and run the following command:
```
./gradlew run  --args="<start-date> <end-date> <report-file.csv>"
```
2. Replace start date, end date and the name of the report file as per your needs.

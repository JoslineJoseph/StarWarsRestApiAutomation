All the dependencies used in this project is defined in pom.xml file

Results of the tests would be saved in results/RunResults.html

Test Description is provided in src/test/java/com.loyaltyone/features package
Step Definition for each steps is defined under src/test/java/com.loyaltyone.steps package

RunCucumberTests.java is the class created to run cucumber tests from command line or from jenkins. 
To run from jenkins, ensure maven is configured for the project and you can use mvn test -Pcucumber-tests  to test the tests

Running from command line
  1 Require maven to be installed and added to the environment variable
  2 command :  mvn test -Pcucumber-tests 
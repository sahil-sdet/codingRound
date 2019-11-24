Things which I worked on

1. Modularized framework by creating separate files for creating Driver,
 performing actions like click, enter text etc (Seleniumfunctions), a PropertyUtility.
2. Added Logging Support
3. Handled Exceptions
4. Implemented Locators Finding Strategies
5. Multi Country Support. We can have different property files for different countries and run it by defining
profile in pom.xml and supplying in run command. eg mvn test -Pindia
6. Tried to add sup[port for FireFox as well

##Things I could not cover
1. Could not work on Reporting due to busy schedule
2. Could not work on capturing screen shots part.



###Commands to Run Tests
mvn clean install -Pindia
mvn test -Pindia


##Locator Stragies
Two locator strategies have been used.
1. reading Locators from PropertyFiles.
2. defining locators in class itself.


##Abstraction
Used abstraction in case of complex functionalities like picking Web Search results


##Incase you face Driver-Browser Mismatch=Exception
Please copy the drive (compatible to your machine browser) in src/main/resources/driver/ and update
in DriverManager.createChromeDriver() or DriverManager.createFireFoxDriver() accordingly

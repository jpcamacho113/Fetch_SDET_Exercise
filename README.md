# Fetch_SDET_Exercise

## Requirements
Java install link: https://www.java.com/en/download/manual.jsp
Maven download link: https://maven.apache.org/download.cgi
Maven installation link: https://maven.apache.org/install.html

Any necessary jar files for the Java classpath have been included.



## Running the application

### Terminal
Navigate to the Fetch_SDET_Exercise directory after obtaining the necessary files and run the below command:
*java -cp selenium-java-4.14.0\lib\* .\scale\src\main\java\com\weightscale\ScaleClass.java*

### IDE
The application should function similar to any other obtained from Github.



## The Algorithm

The way I chose to find the fake gold bar is by seperating the 9 possibilities into 3 groups. I first compare the weight of the first two groups (group1 and group2 var names). If group1 weighs less than group2 then I know group1 has the fake and vice versa. If they are the same weight then I know the last group, group3 has it. From there I follow the same logic just with the individual gold bars. I compare the first bar in the group to the second bar. If the first weighs less then that is the fake but if the second weighs less then that is the answer. If they are equal then the last bar in the group is the fake. This ensures that every time the application is ran the fake gold bar will be found in exactly two weighings.
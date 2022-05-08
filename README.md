# JavaSyncService
There are two application as part of this project
  
  1.UpdateCSV.java :
    This application will generate new records randomly and append them to the csv file.
  
  2.CSVSync.java :
    This application will check for new records in the csv file for every 2 minutes and update the records in MySQL database.
    
 Installation
 -----------
 
 clone the repository
 
 run maven install command
 ```bash
 mvn clean install
 ```
 this will generate the executable jar file.
 
 Database Setup
 
 This service used mysql-connector-java-8.0.19. download the jdbc driver jar and set the CLASSPATH.
 Create Table Statement
 ```sql
 
 ```
 
 Run CSVSync.java
 ```bash
 java -cp Jarpath/CSVSyncService-1.0-SNAPSHOT.jar CSVSync jdbc:mysql://hostname:port_number/database_name db_username db_password csv_file_path
 ```
 
 Run UpdateCSV.java
 ```bash
 java -cp Jarpath/CSVSyncService-1.0-SNAPSHOT.jar UpdateCSV csv_file_path
 ```
 
 

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
 mvn clean compile assembly:single
 ```
 this will generate the executable jar file.
 
 Database Setup:
 
 Create Table Statement
 ```sql
 create table database_name.records 
(
	timestamp varchar(20),ver varchar(20), product_family varchar(20), country varchar(20), device_type varchar(20),
	os varchar(20), checkout_failure_count double(8,4), payment_api_failure_count double(8,4), purchase_count double(9,4), 
    revenue double(9,4)
);
 ```
 
 Instuctions for running services:
 ---------------------------------
 
 CSVSyncService\target\CSVSyncService-1.0-SNAPSHOT.jar is already part of this git repo in target folder. 
 
 Run CSVSync.java
 ```bash
 java -cp {project_path}\CSVSyncService\target\CSVSyncService-1.0-SNAPSHOT.jar CSVSync jdbc:mysql://hostname:port_number/database_name db_username db_password csv_file_path
 ```
 
 Run UpdateCSV.java
 ```bash
 java -cp {project_path}\CSVSyncService\target\CSVSyncService-1.0-SNAPSHOT.jar UpdateCSV csv_file_path
 ```
 
 DONE!

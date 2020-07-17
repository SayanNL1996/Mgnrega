MGNREGA
================================

Introduction
----
This is the console application for MGNREGA. Data is input through the console.


### Configuration parameters
**Email and Password** are the configuration parameter required to make the login for each user.
The parameters are entered in the console to enter the system.

### Authentication
A call is made to the SQLITE database with the login credentials, the credentials are validated for existence and henceforth the login is made; else "Unauthorized User" message is displayed.

### SQLITE_CONNECTION 
This file is used to create a database connection to the SQLite database. The database file thus created is - "mgnrega_database.sqlite".

### LOGIN
This file is used to authenticate the users logging in the system. This is the  main file to be run so that the system can be accessed. 
Some valid credentials to access the system as different users are:

```
**BDO**  
  Email: sayan@gmail.com
  Password: Sayan@1

**GPM**
  Email: 
  Password: 

**Member**
  Email: 
  Password: 
   
```


###FLOW
1. Run Login.java file and comment line 46,47 from Dbconnection.java.
2. Choose BDO and enter the credentials provided above.
3. Enter 1 for GPM Management.
4. Again Enter 1 for Create Gpm.
5. After taking all inputs it shows database is locked and the problem shows on line 75 in Bdo.java 

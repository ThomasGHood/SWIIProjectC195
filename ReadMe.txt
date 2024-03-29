WGU Scheduling App 
SWIIProjectC195


This application's purpose is to provide a scheduling service to clients. 
Clients are represented as Customer objects with Appointment objects. Customers can
be added and modified, as well as the Appointments. The manipulation of the objects 
is handled by the application using ObservableLists to contain the objects in a 
volatile state; the application saves the volatile states to persistent memory in a 
SQL database.

Author: Thomas Hood
Contact: thood30@wgu.edu
Version: 1.0
Date: 2022 May 03

IDE & SDK Environment
IntelliJ IDEA 2021.2.1 (Community Edition)

SDK: Java JDK 11.0.9.12 -->
JavaFX SDK 11.0.2

MySQL Connector Driver
mysql-connector-java-8.0.282

Supported Reports

ReportOne: generates a report that gives a count of total customer appointments, 
filtered by type and month.
ReportTwo: generates a report that returns a schedule for a specified contact.
ReportThree: returns a count of customers per contact. 

Application Operation

LoginScreen: Displayed first, where the user is required to enter username and 
password, then login to the application, or cancel to exit. Note: successful and
unsuccessful login attempts are logged in the login_activity.txt file.

Appointments Dashboard: upon logging into the application, an information alert will 
appear notifying the user of any appointments scheduled within 15 minutes of logging in,
or if there are no appointments pending. 
-Week- displays appointments for the week in the TableView at the window's top.
-Month- displays appointments for the month in the TableView at the window's top.
-reset- displays all appointments (default).
-Report- Menu allows the user to select one of three reports.
-Display- Report sends the selected report to the TextArea at the bottom of the 
Appointments Dashboard.
-Logout- returns the user to the LoginScreen.
-Exit- exits the application.
-Manage- Customer Appointments goes to the Customer Appointment Manager.

Customer Appointment Manager:</b> Where customers and appointments are created and edited.
Under Customers:
-Add- opens a menu to add a customer.
-Edit- opens a menu to edit an existing customer.
-Remove- deletes the selected customer and all associated appointments.
Under Appointments:
-Add- opens a menu to add an appointment.
-Edit- opens a menu to edit an existing appointment.
-Remove- deletes the selected appointment.
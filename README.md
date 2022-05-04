# WGU Scheduling App 
--><b>SWIIProjectC195</b>


This application's purpose is to provide a scheduling service to clients. 
Clients are represented as Customer objects with Appointment objects. Customers can
be added and modified, as well as the Appointments. The manipulation of the objects 
is handled by the application using ObservableLists to contain the objects in a 
volatile state; the application saves the volatile states to persistent memory in a 
SQL database.

<br><b>Author:</b> Thomas Hood
<br><b>Contact:</b> thood30@wgu.edu
<br><b>Version:</b> 1.0
<br><b>Date:</b> 2022 May 03

# IDE & SDK Environment
IntelliJ IDEA 2021.2.1 (Community Edition)

SDK: Java JDK 11.0.9.12 -->
JavaFX SDK 11.0.2

## MySQL Connector Driver
mysql-connector-java-8.0.282

# Supported Reports
ReportOne: generates a report that gives a count of total customer appointments, 
filtered by type and month.
<br>ReportTwo: generates a report that returns a schedule for a specified contact.
<br>ReportThree: returns a count of customers per contact. 

# Application Operation
<b>LoginScreen:</b> Displayed first, where the user is required to enter username and 
password, then login to the application, or cancel to exit. Note: successful and
unsuccessful login attempts are logged in the login_activity.txt file.

<b>Appointments Dashboard:</b> upon logging into the application, an information alert will 
appear notifying the user of any appointments scheduled within 15 minutes of logging in,
or if there are no appointments pending. 
<br>-Week- displays appointments for the week in the TableView at the window's top.
<br>-Month- displays appointments for the month in the TableView at the window's top.
<br>-reset- displays all appointments (default).
<br>-Report- Menu allows the user to select one of three reports.
<br>-Display- Report sends the selected report to the TextArea at the bottom of the 
Appointments Dashboard.
<br>-Logout- returns the user to the LoginScreen.
<br>-Exit- exits the application.
<br>-Manage- Customer Appointments goes to the Customer Appointment Manager.

<b>Customer Appointment Manager:</b> Where customers and appointments are created and edited.
<br>Under Customers:
<br>-Add- opens a menu to add a customer.
<br>-Edit- opens a menu to edit an existing customer.
<br>-Remove- deletes the selected customer and all associated appointments.
<br>Under Appointments:
<br>-Add- opens a menu to add an appointment.
<br>-Edit- opens a menu to edit an existing appointment.
<br>-Remove- deletes the selected appointment.

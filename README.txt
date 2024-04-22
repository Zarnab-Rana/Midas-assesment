#Getting Started.

<----------  Run application via IDE ----------------->

To Run the application via IDE follow the below instructions:
1) Run the docker-compose.yml file via "docker-compose up" command.
2) Run the temporal serve via command "docker-compose -f docker-compose-mysql.yml up"
2) Go to the IDE and start the application through main spring boot class.
<----------------------------------------------------->





<--------- Run application via Docker  --------------->

To run the application via Docker just follow the below instruction:
1) Run the docker-compoase-with-tempora.ymll file via docker command "docker-compose -f docker-compose-with-temporal.yml up"

Note: For now application is not running via docker due to issue that the application cannot connect to the temporal client inside the container,
all configurations are set and compose and docker file scripts are correct.
<----------------------------------------------------->



<--------- Run application test--------------->

To run the application test just run the below command:
1) gradle test



**************************************************************************** Code Implementation   *************************************************************************************

*** Stripe Integration ******

For stripe integration just did the following step:
1) SDK was already integrated
2) I added the API key of stripe , my account was already created attached my key.
3) Created the CustomerService class to connect with stripe on initialization of application API key is set in Stripe.
4) created the Interface for Stripe APIS to use the APIS e.g create Customer and update customer.

****************************


***** APIS Implementation *******

**************
Create Account:
**************

1) To create account in our application first we validate the account e.g. duplicate account.
2) If account is validated successfully then we Initialize the worker to start work flow of create account.
3) For doing the create account task we are using temporal workflow, a workflow named "CreateAccountFlow" is created.
4) Create the implementation of CreateAccount and call the createAccount method for this.
5) In CreateAccountWorkflow we have two activities to perform 
   1) Create Account at Stripe  (After account is successfully created at stripe, it gives us the unique ID of customer , at our database it is stored as providerId)
   2) Save Account at our database. 
6) After performing the above two activities createAccountworkflow is completed and account is successfully created.


**************
Update Account:
**************
1) For update account added the API specs In the schema file.
2) To update account in our application first we validate the account e.g. account exist in our database.
3) If account is validated successfully then we Initialize the worker to start work flow of update account.
4) For doing the update account task we are using temporal workflow, a workflow named "UpdateAccountFlow" is created.
5) Create the implementation of Update Account and call the UpdateAccount method for this.
6) In UpdateAccountWorkflow we have two activities to perform 
   1) Update Account at Stripe  (First customer is retrieved from stripe using provider Id, if it is valid then account is updated successfully at stripe.)
   2) update Account at our database. 
7) After performing the above two activities updateAccountworkflow is completed and account is successfully updated.


*************************************


***** Temporal Server Integration *******

1) To integrate the temporal in our application SDK was already added
2) To Run the server created docker-compose file and run it via command.

*****************************************









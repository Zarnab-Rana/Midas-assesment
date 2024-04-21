#Getting Started.

<----------  Run application via IDE ----------------->

To Run the application via IDE follow the below instructions:
1) Run the docker-compose.yml file via "docker-compose up" command.
2) Run the temporal serve via command "docker-compose -f docker-compose-mysql.yml up"
2) Go to the IDE and start the application through main springboot class.
<----------------------------------------------------->





<--------- Run application via Docker  --------------->

To run the application via Docker just follow the below instruction:
1) Run the docker-compose-with-tempora.ymll file via docker command "docker-compose -f docker-compose-with-temporal.yml up"

Note: For now application is not running via docker due to issue that the application cannot connect to the temporal client inside the container,
all configurations are set and compose and docker file scripts are correct.
<----------------------------------------------------->



<--------- Run application test--------------->

To run the application test just run the below command:
1) gradle test
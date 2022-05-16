# Business Diversity Information As a Service

This is a Java / Gradle / Spring Boot (version 2.6.7) application that loads the business diversity information

## **How To Run**

1. Clone this repository
2. Make sure you are using JDK 1.8
3. Run 'gradlew.bat' (Windows) or 'gradlew' (Linux) to download the gradle wrapper
4. Run 'gradlew clean build' to build the task
5. Add Credentials required for local development for Spring Cloud GCP Data Spanner Framework config:

   - Login to Goggle Cloud Platform
   - Navigate to APIs and Services
   - Go to Credentials
   - Select the service account to use in local development
   - Select "Keys" menu in the selected service account
   - Select "ADD KEY" to generate / download a key to use in local development
   - Rename the downloaded key to Key.json and add it to the resource directory (src/main/resources)

6. Once successfully built, you can run the service by one of these two methods:
   java -jar -Dspring.profiles.active=local build/libs/BIaaS-0.0.1-SNAPSHOT.jar
   or
   gradlew bootRun

## **How To Deploy**

1. Run 'gradlew jib --image=add-your-image-container-ur' to generate and publish the latest image
2. Login to Google Cloud Platform
3. Navigate to 'Cloud Run'
4. Select your service
5. Select 'EDIT & DEPLOY NEW REVISION'
6. Select 'Container image URL' in 'General' section to get the latest image
7. Click 'Deploy'

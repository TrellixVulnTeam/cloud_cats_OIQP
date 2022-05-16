# cloud_cats
## Data Sources
1. [City of New York](https://data.cityofnewyork.us/Business/M-WBE-LBE-and-EBE-Certified-Business-List/ci93-uc8s)
   - API End Point - https://data.cityofnewyork.us/resource/ci93-uc8s.json
2. [State of Texas](https://comptroller.texas.gov/purchasing/vendor/registration/search-tips.php)
3. [State of Connecticut](https://data.ct.gov/Business/Connecticut-Business-Registry-Business-Master/n7gp-d28j)
   - API End Point - https://data.ct.gov/resource/n7gp-d28j.json?status=Active


## UI
The UI is an angular applicaiton and can be found in the CloudCatsUI folder.
To build the angular application cd to the folder and run
1. npm install
2. npm build
This will create a dist folder with the build output.
To run the UI on your local computer run step 1, then npm start.
The build files are copied to a gcp bucket for static hosting.
The latest UI can be accessed at: https://storage.googleapis.com/cloud-cats-ui/index.html

## API
The API Layer is composed of Java applications. The applicaitons are containerized and pushed to gcp container registry.
After images are in the gcp container registry, the images are deployed on cloud run.

## Database
The database is gcp cloud spanner. There was a one time dataload with latest data, and a job to sync latest data with a java application.

## Data Ingestion
Data Ingestion module takes care of fetching the information about various businesses in a given locality from external data sources. Currently, it supports two localities - City of New York and State of Connecticut. This module is developed using Spring Boot. It uses Spring's REST template to fetch the JSON data from the supported data source endpoints. It filters and maps the fetched data to the diversity related information for the application and stores it into GCP Spanner database. This module is deployed as 'Cloud Functions' on GCP and can be accessed using the following URLs (one for each data source):
-  https://data-ingestion-t6oppuclgq-uc.a.run.app/data/nyc
-  https://data-ingestion-t6oppuclgq-uc.a.run.app/data/ct

These two HTTP endpoints exposed by the module are invoked periodically with CRON jobs scheduled in the Cloud Scheduler discussed below.

## Cloud Scheduler
There is a scheduled job that runs every saturday night at 11:45pm. This scheduled job runs and makes an HTTP Get request to a java API.
The Java API will get the latest data from the configured datasource and sync the data to cloud spanner.

## Cloud Functions
There is a cloud function deployed that will sync the texas company data to our local database. A cloud scheduled job will need to call this function one a week to sync the data weekly.

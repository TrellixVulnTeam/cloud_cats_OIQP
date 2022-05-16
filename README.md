# cloud_cats
## Data Sources
1. [City of New York](https://data.cityofnewyork.us/Business/M-WBE-LBE-and-EBE-Certified-Business-List/ci93-uc8s)
   - API End Point - https://data.cityofnewyork.us/resource/ci93-uc8s.json
2. [State Texas](https://comptroller.texas.gov/purchasing/vendor/registration/search-tips.php)


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

## Cloud Scheduler
There is a scheduled job that runs every saturday night at 11:45pm. This scheduled job runs and makes an HTTP Get request to a java API.
The Java API will get the latest data from the configured datasource and sync the data to cloud spanner.

## Cloud Functions
There is a cloud function deployed that will sync the texas company data to our local database. A cloud scheduled job will need to call this function one a week to sync the data weekly.

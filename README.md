# cloud_cats
## Data Sources
1. [City of New York](https://data.cityofnewyork.us/Business/M-WBE-LBE-and-EBE-Certified-Business-List/ci93-uc8s)
   - API End Point - https://data.cityofnewyork.us/resource/ci93-uc8s.json


## UI
The UI is an angular applicaiton and can be found in the CloudCatsUI folder.
To build the angular application cd to the folder and run
1. npm install
2. npm build
This will create a dist folder with the build output.
To run the UI on your local computer run step 1, then npm start.
The build files are copied to a gcp bucket for static hosting.
The latest UI can be accessed at: https://storage.googleapis.com/cloud-cats-ui/index.html

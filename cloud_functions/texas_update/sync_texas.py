import io
import csv
import numpy as np
import pandas as pd
import requests
from bs4 import BeautifulSoup
from google.cloud import spanner


def insert_BUSINESS_INFO(database, tmp_df):
    with database.batch() as batch:
        batch.insert(
            table='BUSINESS_INFO',
            columns=(
                'BUS_ID', 'BUS_NAME', 'BUS_STATE', 'OWNERS'
            ),
            values = tmp_df.tolist()
        )

def insert_BUSINESS_DIVERSITY_INFO(database, tmp_df):
    with database.batch() as batch:
        batch.insert(
            table='BUSINESS_DIVERSITY_INFO',
            columns=(
                'BUS_ID', 'BUS_DIV_ID', 'MINORITY_OWNED', 'SMALL_BUSINESS', 'WOMEN_OWNED'
            ),
            values = tmp_df.tolist()
        )

def sync_texas(request):
    """Responds to any HTTP request.
    Args:
        request (flask.Request): HTTP request object.
    Returns:
        The response text or any set of values that can be turned into a
        Response object using
        `make_response <http://flask.pocoo.org/docs/1.0/api/#flask.Flask.make_response>`.
    """
    r = requests.post('https://mycpa.cpa.state.tx.us/tpasscmblsearch/cmblOutputAsCSV.do?searchType=HUBs+Only&vendorId=&vendorNumber=&vndrNameCond=contains&vendorName=&_inclInactiveVndrs=on&secNIGPClassCd%5B0%5D=&items%5B0%5D=&highwayDist%5B0%5D=&secNIGPClassCd%5B1%5D=&items%5B1%5D=&highwayDist%5B1%5D=&secNIGPClassCd%5B2%5D=&items%5B2%5D=&highwayDist%5B2%5D=&category=&countyName=&cityCond=begins+with&cityName=&zipCond=exact&zip=&nonSolDistricts=&selectedColumns=vid&selectedColumns=companyName&selectedColumns=contactPerson&selectedColumns=mailingAddress&selectedColumns=city&selectedColumns=state&selectedColumns=zip&selectedColumns=country&selectedColumns=email&selectedColumns=phone&selectedColumns=ethnicity&selectedColumns=gender&selectedColumns=smallBusiness&outputAs=OP_AS_PIPE_LIST&sortBy=SORT_BY_VNDR_NAME&search=search')
    soup = BeautifulSoup(r.text, 'html.parser')
    data = soup.find(id="my-parent-selector")
    results = []
    for row in data.contents:
      text = str(row)
      text = text.replace('\t', '')
      text = text.replace('\r', '')
      text = text.replace('\n', '')
      text = text.replace('<br/>', '')
      text = text.replace('"', '')
      if text == "":
          continue
      results.append(text)
    complete_data = "\n".join(results[1:])
    sio = io.StringIO()
    sio.write(complete_data)
    sio.seek(0)
    df = pd.read_csv(sio, delimiter="|", names=["Vendor ID","Company Name","Contact Person","Mailing Address","City","State","Zip","Country","Email","Phone","HUB Eligibility","HUB Gender","Small Business","CMBL Status","HUB Status"])
    df.loc[pd.isna(df["Small Business"]), "Small Business"] = False
    df.loc[df["Small Business"] != False, "Small Business"] = True
    df.loc[df["CMBL Status"] == ' Yes ', "CMBL Status"] = True
    df.loc[df["CMBL Status"] == ' No ', "CMBL Status"] = False
    df['WOMEN_OWNED'] = df["HUB Gender"].apply(lambda x: True if x == ' F ' else False)
    df['State'] = df['State'].apply(lambda x: x.strip())
    client = spanner.Client()
    database = client.instance('cloud-cats-db').database('cloud-cat-db')

    with database.snapshot() as snapshot:
        results = snapshot.execute_sql("SELECT BUS_ID FROM BUSINESS_INFO ORDER BY BUS_ID desc LIMIT 1")

    next_ind = None
    for row in results:
        next_ind = row[0]
    next_ind += 1
    new_indicies = range(next_ind, next_ind+df.shape[0])
    df['index'] = new_indicies

    for tmp_df in np.array_split(df[['index', 'Company Name', 'State', 'Contact Person']].values,10):
      insert_BUSINESS_INFO(database, tmp_df)
    
    for tmp_df in np.array_split(df[['index', 'index', 'CMBL Status', 'Small Business', 'WOMEN_OWNED']].values,10):
      insert_BUSINESS_DIVERSITY_INFO(database, tmp_df)
    
    return f'Hello World!'

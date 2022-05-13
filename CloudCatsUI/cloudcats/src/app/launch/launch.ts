export interface IList {
    businessId: string;
	businessFEIN: string;
	businessName: string;
	businessState: string;
	businessOwners: string;
	businessDiversityInfo: diversityInfo;
  }

  export interface diversityInfo {
	businessDiversityId: string;
	minorityOwned: string;
	smallBusiness: string;
	womenOwned: string;
  }
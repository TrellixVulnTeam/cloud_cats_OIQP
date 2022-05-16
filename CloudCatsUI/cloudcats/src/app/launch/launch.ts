export interface BusinessDiversityInfo {
	pageNumber: number;
	pageSize: number;
	totalPages: number;
	totalElements:number;
	sortBy: string;
	businessInfoList: BusinessInfo[];
  }

  export interface BusinessInfo {
	businessId: number;
	businessFEIN: string;
	businessName: string;
	businessState: string;
	businessOwners: string;
	businessDiversityInfo: DiversityInfo[];
  }

  export interface DiversityInfo {
	businessId: number;
	businessDiversityId: number;
	minorityOwned: any;
	smallBusiness: any;
	womenOwned: any;
  }
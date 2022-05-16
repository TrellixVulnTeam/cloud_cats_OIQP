/**
 * 
 */
package cats.cloud.dataingestion.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cats.cloud.dataingestion.dto.CTBiz;
import cats.cloud.dataingestion.dto.NYCBiz;
import cats.cloud.dataingestion.entity.BizDiversityInfo;
import cats.cloud.dataingestion.entity.BizInfo;
import cats.cloud.dataingestion.entity.Sequences;
import cats.cloud.dataingestion.repo.BusinessInfoRepo;
import cats.cloud.dataingestion.repo.SequencesRepo;

/**
 * @author viral
 *
 */
@Service
public class DataIngestionServiceImpl implements DataIngestionService {
	
	@Autowired
	BusinessInfoRepo bizInfoRepo;
	
	@Autowired
	SequencesRepo seqRepo;
	
	@Override
	public void saveNYCData() {
		String url = "https://data.cityofnewyork.us/resource/ci93-uc8s.json";
		String clause = "?$select=vendor_formal_name, contact_name, ethnicity, state, certification";
		NYCBiz[] nycBusinesses = getDataset(url, clause, NYCBiz[].class);
		List<BizInfo> bizInfoList = new ArrayList<>();
		Integer seq = getSeqNextVal();
		for (NYCBiz biz : nycBusinesses) {
			BizInfo bizInfo = new BizInfo();
			bizInfo.setBusName(biz.getVendorFormalName());
			bizInfo.setOwners(biz.getContactName());
			bizInfo.setBusState(biz.getState());
			BizDiversityInfo bizDiversityInfo = new BizDiversityInfo();
			String certificate = biz.getCertification();
			if(certificate.contains("MBE")) {
				bizDiversityInfo.setIsMinorityOwned(Boolean.TRUE);
			}
			if(certificate.contains("WBE")) {
				bizDiversityInfo.setIsWomenOwned(Boolean.TRUE);
			}			
			if(certificate.contains("LBE") || certificate.contains("EBE")) {
				bizDiversityInfo.setIsSmallBusiness(Boolean.TRUE);
			}
			bizDiversityInfo.setBusId(seq);
			//Set the bus id as the bus div id too. Not sure what this means.
			bizDiversityInfo.setBusinessDiversityId(seq);
			bizInfo.setBizDiversityInfo(Collections.singletonList(bizDiversityInfo));
			bizInfo.setBusId(seq);
			bizInfoList.add(bizInfo);
			seq++;
		}
		
		bizInfoRepo.saveAll(bizInfoList);
		saveSeq(seq);
		System.out.println("OK");
	}
	
	@Override
	public void saveCTData() {
		String url = "https://data.ct.gov/resource/n7gp-d28j.json";
		String clause = "?status=Active&$select=name, state_or_territory_formation, "
				+ "woman_owned_organization, veteran_owned_organization, minority_owned_organization";
		CTBiz[] ctBusinesses = getDataset(url, clause, CTBiz[].class);
		List<BizInfo> bizInfoList = new ArrayList<>();
		Integer seq = getSeqNextVal();
		for (CTBiz biz : ctBusinesses) {
			BizInfo bizInfo = new BizInfo();
			bizInfo.setBusName(biz.getVendorName());
			bizInfo.setOwners(""); //Info not available in the dataset
			
			bizInfo.setBusState("Connecticut".equalsIgnoreCase(biz.getState()) ? "CT" : "");
			BizDiversityInfo bizDiversityInfo = new BizDiversityInfo();
			bizDiversityInfo.setIsMinorityOwned(biz.getIsMinorityOwned());
			bizDiversityInfo.setIsSmallBusiness(null); //Info is not available in the dataset
			bizDiversityInfo.setIsWomenOwned(biz.getIsWomenOwned());
			bizDiversityInfo.setBusId(seq);
			//Set the bus id as the bus div id too. Not sure what this means.
			bizDiversityInfo.setBusinessDiversityId(seq);
			bizInfo.setBizDiversityInfo(Collections.singletonList(bizDiversityInfo));
			bizInfo.setBusId(seq);
			bizInfoList.add(bizInfo);
			seq++;
		}
		
		bizInfoRepo.saveAll(bizInfoList);
		saveSeq(seq);
		System.out.println("OK");
	}
	
	public <T> T[] getDataset(String url, String clause, Class<T[]> clazz) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<T[]> responseEntity = restTemplate.getForEntity(url + clause, clazz);
		T[] businesses = responseEntity.getBody();
		return businesses;
	}
	
	private Integer getSeqNextVal() {
		return seqRepo.findById("Bus_Info_Seq").orElse(new Sequences()).getSeqNextValue();
	}
	
	private void saveSeq(Integer seqVal) {
		Sequences seq = new Sequences();
		seq.setSeqName("Bus_Info_Seq");
		seq.setSeqNextValue(seqVal);
		seqRepo.save(seq);
	}
}

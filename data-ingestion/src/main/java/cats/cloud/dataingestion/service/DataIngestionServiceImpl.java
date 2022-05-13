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

import cats.cloud.dataingestion.entity.BizDiversityInfo;
import cats.cloud.dataingestion.entity.BizInfo;
import cats.cloud.dataingestion.entity.NYCBiz;
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
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<NYCBiz[]> responseEntity = restTemplate.getForEntity(url + clause, NYCBiz[].class);
		NYCBiz[] nycBusinesses = responseEntity.getBody();
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
		//List<BizInfo> list = (List<BizInfo>) bizInfoRepo.findAll();
		//String name = list.get(0).getBusName();
		System.out.println("OK");
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

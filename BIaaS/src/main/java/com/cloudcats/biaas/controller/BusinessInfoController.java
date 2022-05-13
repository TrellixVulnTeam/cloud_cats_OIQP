package com.cloudcats.biaas.controller;

import com.cloudcats.biaas.dto.BusinessInfoPageRequest;
import com.cloudcats.biaas.service.IBusinessInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class BusinessInfoController {

    @Autowired
    private IBusinessInfoService businessInfoService;

    @GetMapping("/info/businesses")
    public ResponseEntity<?> getBusinessesInfo() {
        return new ResponseEntity<>(businessInfoService.getBusinessesInfo(), HttpStatus.OK);
    }

    @GetMapping("/info/businesses/{businessId}")
    public ResponseEntity<?> getBusinessInfo(@PathVariable int businessId) {
        return new ResponseEntity<>(businessInfoService.getBusinessInfo(businessId), HttpStatus.OK);
    }

    @PostMapping("/info/businesses")
    public ResponseEntity<?> getBusinessInfoPagination(@RequestBody(required = false) BusinessInfoPageRequest businessInfoPageRequest) {
        if (businessInfoPageRequest == null) {
            businessInfoPageRequest = new BusinessInfoPageRequest();
        }

        return new ResponseEntity<>(businessInfoService.getBusinessInfoPagination(businessInfoPageRequest), HttpStatus.OK);
    }
}

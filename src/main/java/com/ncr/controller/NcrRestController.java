package com.ncr.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ncr.delegate.NcrRestDelegate;
import com.ncr.model.Address;
import com.ncr.model.ApiRequest;
import com.ncr.service.ApiRequestService;

@RestController
@RequestMapping("/address")
public class NcrRestController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    ApiRequestService apiRequestService;

	@Autowired
	NcrRestDelegate delegate;
    
    @RequestMapping("/{latitude:.+}/{longitude:.+}")
    public @ResponseBody Address getAddress(@PathVariable("latitude") Double latitude, @PathVariable("longitude") Double longitude) {
    	if (logger.isDebugEnabled()) {
    		logger.debug("Latitude is " + latitude + " and longitude is " + longitude);	
    	}
    	ApiRequest apiRequest = new ApiRequest(new Date());
    	Address model = delegate.getAddress(latitude, longitude);
    	apiRequest.setLatitude(latitude);
    	apiRequest.setLongitude(longitude);
    	apiRequest.setAddress(model.getAddress());
    	
    	apiRequestService.create(apiRequest);
    	
    	return model;
    }
    
    @RequestMapping("/{id}")
    public @ResponseBody ApiRequest getAddressCacheById(@PathVariable("") Long id) {
    	if (logger.isDebugEnabled()) {
    		logger.debug("Getting api request for id {}", id);	
    	}
    	
    	ApiRequest apiRequest = apiRequestService.getApiRequestById(id);	
    	
    	if (null == apiRequest) {
    		apiRequest = new ApiRequest();
    	}
    	
		if (logger.isDebugEnabled()) {
			logger.debug(apiRequest.toString());
		}
    	
    	return apiRequest;
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody Collection<ApiRequest> getAddressCache() {
    	List<ApiRequest> apiReturnList = new ArrayList<ApiRequest>();
    	List<ApiRequest> apiRequestList = apiRequestService.getLastTenApiRequest();	
    	
    	Collections.sort(apiRequestList, new Comparator<ApiRequest>() {

			@Override
			public int compare(ApiRequest o1, ApiRequest o2) {
				return Double.compare(o1.getRequestTime().getTime(), o2.getRequestTime().getTime());
			}
		});
    	
    	Collections.reverse(apiRequestList);
    	int count = 0;

    	for (ApiRequest apiRequest: apiRequestList) {
    		if (logger.isDebugEnabled()) {
				logger.debug(apiRequest.toString());
			}
    		
    		if (count < 10) {
    			apiReturnList.add(apiRequest);
    			count++;
    		}
		}
    	
    	return apiReturnList;
    }
}
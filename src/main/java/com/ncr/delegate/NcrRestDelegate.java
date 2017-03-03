package com.ncr.delegate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.ncr.model.Address;

@Component
public class NcrRestDelegate {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static String GOOGLE_GEOCODING_KEY = "AIzaSyC7lM2c185vQmzdjfUiBaFKGJppxYhOEj4";
	
	public Address getAddress(Double latitude, Double longitude) {
		GeocodingResult[] results = null;
		
		Address model = new Address();
		
    	try {
    		GeoApiContext context = getGeoApiContext();
    		LatLng latlng = getGoogleLatLng(latitude, longitude);
    		results = makeGeocodingApiRequest(context, latlng);
    		
    		if (null != results) {
    			logger.debug(results[0].formattedAddress);
    			model.setAddress(results[0].formattedAddress);
    		}
    	}
    	catch (Exception e) {
    		logger.error("Exception getting address.", e);
    	}
    	
    	return model;
	}
	
	private GeoApiContext getGeoApiContext() {
		return new GeoApiContext().setApiKey(GOOGLE_GEOCODING_KEY);
	}
	
	private LatLng getGoogleLatLng(Double latitude, Double longitude) {
		return new LatLng(latitude, longitude);
	}
	
	private GeocodingResult[] makeGeocodingApiRequest(GeoApiContext context, LatLng latlng) throws Exception {
		return GeocodingApi.newRequest(context).latlng(latlng).await();
	}
}
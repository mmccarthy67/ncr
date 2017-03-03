package com.ncr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ncr.dao.ApiRequestDao;
import com.ncr.model.ApiRequest;

@Service
@Transactional
public class ApiRequestServiceImpl implements ApiRequestService {

    @Autowired
    private ApiRequestDao apiRequestDao;

    @Override
    public void create(ApiRequest apiRequest) {
        apiRequestDao.create(apiRequest);
    }

	@Override
	public void update(ApiRequest apiRequest) {
		apiRequestDao.update(apiRequest);
	}

	@Override
	public ApiRequest getApiRequestById(long id) {
		return apiRequestDao.getApiRequestById(id);
	}
	
	@Override
	public List<ApiRequest> getLastTenApiRequest() {
		return apiRequestDao.getLastTenApiRequest();
	}
	
	@Override
	public void delete(long id) {
		apiRequestDao.delete(id);
	}
}
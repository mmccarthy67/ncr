package com.ncr.service;

import java.util.List;

import com.ncr.model.ApiRequest;

public interface ApiRequestService {
    public void create(ApiRequest apiRequest);
    public void update(ApiRequest apiRequest);
    public ApiRequest getApiRequestById(long id);
    public List<ApiRequest> getLastTenApiRequest();
    public void delete(long id);
}
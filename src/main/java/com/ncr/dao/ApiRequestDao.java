package com.ncr.dao;

import java.util.List;

import com.ncr.model.ApiRequest;

public interface ApiRequestDao {
    public void create(ApiRequest apiRequest);
    public void update(ApiRequest apiRequest);
    public ApiRequest getApiRequestById(long id);
    public List<ApiRequest> getLastTenApiRequest();
    public void delete(long id);
}
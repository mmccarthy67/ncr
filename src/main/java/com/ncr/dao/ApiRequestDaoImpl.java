package com.ncr.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ncr.model.ApiRequest;

@Repository
public class ApiRequestDaoImpl implements ApiRequestDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(ApiRequest apiRequest) {
    	try {
    		entityManager.persist(apiRequest);
    	} catch (Exception e) {
    		logger.error("Exception persisting api request.", e);
    	}
    }

    @Override
    public void update(ApiRequest apiRequest) {
        entityManager.merge(apiRequest);
    }

    @Override
    public ApiRequest getApiRequestById(long id) {
        return entityManager.find(ApiRequest.class, id);
    }

	@Override
	public List<ApiRequest> getLastTenApiRequest() {
		try {
			return (List<ApiRequest>) entityManager.createQuery("SELECT e FROM ApiRequest e").getResultList(); 
		} catch (Exception e) {
			logger.error("Exception getting api request.", e);
		}
		 
		 return Collections.emptyList();
	}
	
    @Override
    public void delete(long id) {
        ApiRequest apiRequest = getApiRequestById(id);
        if (apiRequest != null) {
            entityManager.remove(apiRequest);
        }
    }
}
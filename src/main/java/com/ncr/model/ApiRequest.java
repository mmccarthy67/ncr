package com.ncr.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "api_request")
public class ApiRequest extends BaseModel implements Comparable<ApiRequest> {
	private static final long serialVersionUID = -1677682682855298844L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name="latitude")
    private Double latitude;

	@Column(name="longitude")
    private Double longitude;
	
	@Column(name="address")
    private String address;
	
    @Column(name="request_time")
    private Date requestTime;

    public ApiRequest() {
    }

    public ApiRequest(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

	@Override
	public int compareTo(ApiRequest apiRequest) {
		return getRequestTime().compareTo(apiRequest.getRequestTime());
	}
}
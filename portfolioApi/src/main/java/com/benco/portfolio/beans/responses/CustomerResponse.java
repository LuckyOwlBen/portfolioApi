package com.benco.portfolio.beans.responses;

import com.benco.portfolio.entities.CustomerEntity;

public class CustomerResponse {
	private boolean success;

	private String jobId;

	public CustomerResponse(boolean b, CustomerEntity customerEntity) {
		this.success = b;
		this.jobId = customerEntity.getJobId();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
}

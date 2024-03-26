package com.benco.portfolio.beans.responses;

public class CustomerResponse {

	private String jobId;

	private String token;

	public CustomerResponse(String jobId, String token) {
		this.jobId = jobId;
		this.token = token;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}

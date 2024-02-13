package com.benco.portfolio.beans.responses;

public class CustomerResponse {

	private boolean success;

	private String jobId;

	private String token;

	public CustomerResponse(boolean success, String jobId, String token) {
		this.success = success;
		this.jobId = jobId;
		this.token = token;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}

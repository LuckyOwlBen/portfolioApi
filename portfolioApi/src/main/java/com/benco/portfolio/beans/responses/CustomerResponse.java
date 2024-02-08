package com.benco.portfolio.beans.responses;

public class CustomerResponse {
	private boolean success;

	public CustomerResponse(boolean b) {
		this.success = b;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}

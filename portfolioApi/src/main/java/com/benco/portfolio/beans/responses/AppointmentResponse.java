package com.benco.portfolio.beans.responses;

public class AppointmentResponse {

	public AppointmentResponse(boolean success) {
		this.success = success;
	}

	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}

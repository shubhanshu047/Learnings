package com.example.demo.Services;

public class ApiResponse {
	private boolean success;
	private String token;
	private String message;
	
	public ApiResponse() {}
	
	public ApiResponse(boolean success, String message, String token) {
		this.success = success;
		this.message = message;
		this.token = token;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}

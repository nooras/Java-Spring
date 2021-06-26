package com.users.utility;

import java.util.List;

public class ErrorInfo {

	private String resMsg;
	private String userId;
	private List<ValidationErrorInfo> valError;
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<ValidationErrorInfo> getValError() {
		return valError;
	}
	public void setValError(List<ValidationErrorInfo> valError) {
		this.valError = valError;
	}

}

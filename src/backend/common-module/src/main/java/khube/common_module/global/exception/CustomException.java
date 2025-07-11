package khube.common_module.global.exception;

import khube.common_module.global.status.ErrorStatus;

public class CustomException extends RuntimeException {
	private final ErrorStatus errorCode;

	public CustomException(ErrorStatus errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public String getCode() {
		return errorCode.getCode();
	}

	public ErrorStatus getErrorCode() {
		return errorCode;
	}
}

package khube.common_module.global.exception;


import khube.common_module.global.response.CommonResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(CustomException.class)
	public ResponseEntity<CommonResponse<?>> handleCustomException(CustomException e) {
		ErrorCode errorCode = e.getErrorCode();
		CommonResponse<?> response = new CommonResponse<>(false, null, errorCode.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<CommonResponse<?>> handleGeneralException(Exception e) {
		CommonResponse<?> response = new CommonResponse<>(false, null, "예상치 못한 오류가 발생했습니다.");
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

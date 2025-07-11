package khube.common_module.global.exception;

import khube.common_module.global.status.ApiResponse;
import khube.common_module.global.status.ErrorStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ApiResponse<?>> handleCustomException(CustomException e) {
		ErrorStatus status = e.getErrorCode(); // 전체 status 객체 사용 가능
		log.warn("Handled custom exception: {} - {}", status.getCode(), status.name());

		ApiResponse<?> response = ApiResponse.onFailure(e.getCode(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception e) {
		log.error("general exception occurred", e);

		ApiResponse<Void> response = ApiResponse.onFailure(
				ErrorStatus.INTERNAL_SERVER_ERROR.getCode(),
				ErrorStatus.INTERNAL_SERVER_ERROR.getMessage()
		);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
}




package khube.common_module.global.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	/*
	 * 에러코드
	 * 400 : Bad Request
	 * 401 : Unauthorized
	 * 403 : Forbidden
	 * 404 : Not Found
	 * 409 : Conflict
	 */
	INVALID_REQUEST("COMMON400", "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
	FORBIDDEN("COMMON403", "권한이 부족합니다.", HttpStatus.FORBIDDEN);

	private final String errorCode;
	private final String message;
	private final HttpStatus status;
}

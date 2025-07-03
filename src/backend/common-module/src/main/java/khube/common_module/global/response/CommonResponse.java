package khube.common_module.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonResponse<T> {
	private boolean success;
	private T data;
	private String message;

	public static <T> CommonResponse<T> success(T data) {
		return new CommonResponse<>(true, data, "요청 성공");
	}

	public static <T> CommonResponse<T> fail(String message) {
		return new CommonResponse<>(false, null, message);
	}
}
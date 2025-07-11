package khube.common_module.global.status;

import lombok.Getter;

@Getter
public enum ErrorStatus {
    USER_NOT_FOUND("U001", "User not found"),
    INVALID_ORDER("O001", "Invalid order request"),
    INTERNAL_SERVER_ERROR("S001", "Something went wrong"),

    PRODUCT_NOT_FOUND("P001", "Product not found");
    private final String code;
    private final String message;

    ErrorStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

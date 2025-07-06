package khube.common_module.global.status;

public enum SuccessStatus {
    OK("200", "Successed"),
    CREATED("201", "Create user"),
    UPDATED("202", "Update stock");

    private final String code;
    private final String message;
    SuccessStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() { return code; }
    public String getMessage() { return message; }

}

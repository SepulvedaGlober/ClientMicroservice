package grandmasfood.client.utils;


import lombok.Getter;

@Getter
public enum ErrorCode {
    CLIENT_NOT_FOUND("E1001"),
    CLIENT_ALREADY_EXISTS("E1002"),
    INVALID_ADDRESS("E1003"),
    INVALID_DOCUMENT("E1004"),
    INVALID_PHONE("E1005"),
    DUPLICATE_DOCUMENT("E1006"),
    INVALID_EMAIL("E1007"),
    INTERNAL_SERVER_ERROR("E1008"),
    INVALID_DOCUMENT_CHANGE("E1009");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }
}

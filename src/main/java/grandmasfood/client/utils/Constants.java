package grandmasfood.client.utils;

public class Constants {
    public static final int MAX_DOCUMENT_LENGTH = 20;
    public static final int MAX_FULL_NAME_LENGTH = 255;
    public static final int MAX_EMAIL_LENGTH = 255;
    public static final int MAX_PHONE_LENGTH = 10;
    public static final int MAX_ADDRESS_LENGTH = 500;
    public static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9_+&*-]+(\\.[a-zA-Z0-9_+&*-]+)?@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,7}+(\\.[a-zA-Z]{2,7}+)?$";
    public static final String PHONE_VALIDATION_REGEX = "^[0-9]{10,10}$";
    public static final String DOCUMENT_VALIDATION_REGEX = "^(CC|CE|P)-[a-zA-Z0-9]{1,17}$";
}

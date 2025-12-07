package grandmasfood.client.infrastructure.exceptions;

public class DuplicateDocumentException extends RuntimeException {
    public DuplicateDocumentException(String message) {
        super(message);
    }
}

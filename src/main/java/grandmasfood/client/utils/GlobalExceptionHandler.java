package grandmasfood.client.utils;


import grandmasfood.client.domain.exceptions.*;
import grandmasfood.client.infrastructure.exceptions.DuplicateDocumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleClientAlreadyExistsException(ClientAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.CLIENT_ALREADY_EXISTS.getCode(),
                ErrorMessages.CLIENT_ALREADY_EXISTS,
                ex.getClass().getSimpleName());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClientNotFoundException(ClientNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.CLIENT_NOT_FOUND.getCode(),
                ErrorMessages.CLIENT_NOT_FOUND,
                ex.getClass().getSimpleName());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidAddressException.class)
    public ResponseEntity<ErrorResponse> handleInvalidAddressException(InvalidAddressException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.INVALID_ADDRESS.getCode(),
                ErrorMessages.INVALID_ADDRESS,
                ex.getClass().getSimpleName());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDocumentException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDocumentException(InvalidDocumentException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.INVALID_DOCUMENT.getCode(),
                ErrorMessages.INVALID_DOCUMENT,
                ex.getClass().getSimpleName());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEmailException(InvalidEmailException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.INVALID_EMAIL.getCode(),
                ErrorMessages.INVALID_EMAIL,
                ex.getClass().getSimpleName());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPhoneException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPhoneException(InvalidPhoneException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.INVALID_PHONE.getCode(),
                ErrorMessages.INVALID_PHONE,
                ex.getClass().getSimpleName());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateDocumentException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateDocumentException(DuplicateDocumentException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.DUPLICATE_DOCUMENT.getCode(),
                ErrorMessages.DUPLICATE_DOCUMENT,
                ex.getClass().getSimpleName());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.INTERNAL_SERVER_ERROR.getCode(),
                ErrorMessages.INTERNAL_SERVER_ERROR,
                ex.getClass().getSimpleName());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}

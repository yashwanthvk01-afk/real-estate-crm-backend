package crm.exception;
// ↑ exception package లో ఉంది

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
// ↑ Spring Boot annotations import చేస్తున్నాం

@RestControllerAdvice
// ↑ అన్ని Controllers లో వచ్చే exceptions
//   ఇక్కడ catch అవుతాయి
public class GlobalExceptionHandler {

    // CLIENT NOT FOUND
    @ExceptionHandler(ResourceNotFoundException.class)
    // ↑ ResourceNotFoundException వచ్చినప్పుడు
    //   ఈ method run అవుతుంది
    public ResponseEntity<ApiResponse<?>> handleNotFound(
            ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                // ↑ 404 status code
                .body(ApiResponse.error(ex.getMessage(), 404));
                // ↑ Clean error response పంపుతుంది
    }

    // DUPLICATE EMAIL
    @ExceptionHandler(IllegalArgumentException.class)
    // ↑ Email already exists అయినప్పుడు
    public ResponseEntity<ApiResponse<?>> handleIllegalArg(
            IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                // ↑ 400 status code
                .body(ApiResponse.error(ex.getMessage(), 400));
    }

    // ANY OTHER ERROR
    @ExceptionHandler(Exception.class)
    // ↑ ఏదైనా unexpected error వచ్చినప్పుడు
    public ResponseEntity<ApiResponse<?>> handleGeneral(
            Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                // ↑ 500 status code
                .body(ApiResponse.error(
                    "Something went wrong: " + ex.getMessage(), 500));
    }
}
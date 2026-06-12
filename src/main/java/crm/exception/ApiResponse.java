package crm.exception;
// ↑ ఈ file ఏ package లో ఉందో చెప్తుంది

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// ↑ Lombok annotations
//   getter, setter, constructor auto generate చేస్తాయి

@Data
// ↑ getter + setter automatically generate అవుతాయి
@AllArgsConstructor
// ↑ అన్ని fields తో constructor
@NoArgsConstructor
// ↑ empty constructor
@Builder
// ↑ builder pattern వాడవచ్చు
public class ApiResponse<T> {
// ↑ <T> = Generic type
//   T = ఏ type data అయినా పంపవచ్చు
//   Client, List<Client>, null అన్నీ పంపవచ్చు

    private boolean success;
    // ↑ true = success, false = error

    private String message;
    // ↑ "Client created successfully" లాంటి messages

    private T data;
    // ↑ actual data ఇక్కడ ఉంటుంది

    private int status;
    // ↑ HTTP status code (200, 404, 500...)

    // SUCCESS RESPONSE
    public static <T> ApiResponse<T> success(T data, String message) {
        // ↑ Success అయినప్పుడు call చేస్తాం
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .status(200)
                .build();
    }

    // ERROR RESPONSE
    public static <T> ApiResponse<T> error(String message, int status) {
        // ↑ Error వచ్చినప్పుడు call చేస్తాం
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .data(null)
                .status(status)
                .build();
    }
}   
package crm.exception;
// ↑ ఈ file exception package లో ఉంది

public class ResourceNotFoundException extends RuntimeException {
    // ↑ RuntimeException extend చేస్తున్నాం
    //   అంటే ఇది ఒక custom exception
    //   Client not found అయినప్పుడు ఈ exception throw చేస్తాం

    public ResourceNotFoundException(String message) {
        // ↑ Exception create చేసేటప్పుడు
        //   message పంపాలి
        //   Example: "Client not found: 99"
        super(message);
        // ↑ Parent class కి message పంపుతున్నాం
    }
}
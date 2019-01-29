package cl.ps.code.generator.exception;

public class QrGeneratorRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public QrGeneratorRuntimeException() {
        super();
    }

    public QrGeneratorRuntimeException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public QrGeneratorRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public QrGeneratorRuntimeException(String message) {
        super(message);
    }

    public QrGeneratorRuntimeException(Throwable cause) {
        super(cause);
    }
}

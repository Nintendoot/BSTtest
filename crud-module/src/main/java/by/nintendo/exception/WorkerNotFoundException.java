package by.nintendo.exception;

public class WorkerNotFoundException extends RuntimeException{
    public WorkerNotFoundException() {
        super();
    }

    public WorkerNotFoundException(String message) {
        super(message);
    }

    public WorkerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorkerNotFoundException(Throwable cause) {
        super(cause);
    }

    protected WorkerNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

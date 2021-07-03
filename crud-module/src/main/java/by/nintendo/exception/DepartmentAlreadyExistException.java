package by.nintendo.exception;

public class DepartmentAlreadyExistException extends RuntimeException{
    public DepartmentAlreadyExistException() {
        super();
    }

    public DepartmentAlreadyExistException(String message) {
        super(message);
    }

    public DepartmentAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentAlreadyExistException(Throwable cause) {
        super(cause);
    }

    protected DepartmentAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

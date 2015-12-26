package exceptions;

public class MoreThanOneItemException extends RuntimeException {

    String errorMessage = "You seemed to expect a single item but more than one item found. If you really need multiple items use selectByCriteria(...) instead of getXXX(...)";

    public MoreThanOneItemException() {
    }

    public String getMessage() {
        return errorMessage;
    }

    public String toString() {
        return errorMessage;
    }

}

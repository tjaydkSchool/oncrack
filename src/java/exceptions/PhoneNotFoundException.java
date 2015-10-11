package exceptions;

public class PhoneNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>PhoneNotFoundException</code> without
     * detail message.
     */
    public PhoneNotFoundException() {
    }

    /**
     * Constructs an instance of <code>PhoneNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PhoneNotFoundException(String msg) {
        super(msg);
    }
}

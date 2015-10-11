package exceptions;

public class AddressNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>AddressNotFoundeException</code> without
     * detail message.
     */
    public AddressNotFoundException() {
    }

    /**
     * Constructs an instance of <code>AddressNotFoundeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AddressNotFoundException(String msg) {
        super(msg);
    }
}

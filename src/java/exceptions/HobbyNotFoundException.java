package exceptions;

public class HobbyNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>HobbyNotFoundException</code> without
     * detail message.
     */
    public HobbyNotFoundException() {
    }

    /**
     * Constructs an instance of <code>HobbyNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public HobbyNotFoundException(String msg) {
        super(msg);
    }
}

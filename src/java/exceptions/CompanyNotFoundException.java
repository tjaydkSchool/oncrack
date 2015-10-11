package exceptions;

public class CompanyNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>CompanyNotFoundException</code> without
     * detail message.
     */
    public CompanyNotFoundException() {
    }

    /**
     * Constructs an instance of <code>CompanyNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CompanyNotFoundException(String msg) {
        super(msg);
    }
}

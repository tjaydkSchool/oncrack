package exceptions;

public class CityInfoNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>CityInfoNotFoundException</code> without
     * detail message.
     */
    public CityInfoNotFoundException() {
    }

    /**
     * Constructs an instance of <code>CityInfoNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CityInfoNotFoundException(String msg) {
        super(msg);
    }
}

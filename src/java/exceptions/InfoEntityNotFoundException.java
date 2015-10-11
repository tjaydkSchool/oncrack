/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Asnorrason
 */
public class InfoEntityNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>InfoEntityNotFoundException</code>
     * without detail message.
     */
    public InfoEntityNotFoundException() {
    }

    /**
     * Constructs an instance of <code>InfoEntityNotFoundException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InfoEntityNotFoundException(String msg) {
        super(msg);
    }
}

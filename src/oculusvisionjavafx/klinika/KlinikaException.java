/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.klinika;

/**
 *
 * @author Qendresa
 */
public class KlinikaException extends Exception {

    /**
     * Creates a new instance of <code>KlinikaException</code> without detail
     * message.
     */
    public KlinikaException() {
    }

    /**
     * Constructs an instance of <code>KlinikaException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public KlinikaException(String msg) {
        super(msg);
    }
}

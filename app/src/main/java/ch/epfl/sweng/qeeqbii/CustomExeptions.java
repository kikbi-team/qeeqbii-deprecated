package ch.epfl.sweng.qeeqbii;

/**
 * Created by adrien on 12/10/17.
 * 
 */

class NullInputException extends Exception {
    // Constructor that accepts a message
    NullInputException(String message) {
        super(message);
    }
}


class NotOpenFileException extends Exception {
    // Constructor that accepts a message
    NotOpenFileException(String message) {
        super(message);
    }
}


class ProductException extends Exception{
    // Constructor that accepts a message
    ProductException(String message) { super(message); }
}



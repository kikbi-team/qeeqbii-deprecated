package ch.epfl.sweng.qeeqbii.CustomExceptions;



public class NotOpenFileException extends Exception {
    // Constructor that accepts a message
    public NotOpenFileException(String message) {
        super(message);
    }
}
package it.juan.user.exception;


public class HotelNotFoundException extends RuntimeException {

    public HotelNotFoundException() {
        super();
    }

    public HotelNotFoundException(String message) {
        super(message);
    }

    public HotelNotFoundException(long id) {
        super("Product not found: " + id);
    }
}

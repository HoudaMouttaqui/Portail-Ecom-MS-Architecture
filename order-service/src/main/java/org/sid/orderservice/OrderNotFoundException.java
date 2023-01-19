package org.sid.orderservice;
import java.io.Serializable;
public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String message) {
        super(message);
    }
}

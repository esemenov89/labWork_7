package code.services;

import org.springframework.dao.DataAccessException;

/**
 * Created by admin on 08.05.2017.
 */
public class LibraryException extends DataAccessException {
    private String methodName;

    public LibraryException(String message, String methodName){
        super(message);
        this.methodName=methodName;
    }
}

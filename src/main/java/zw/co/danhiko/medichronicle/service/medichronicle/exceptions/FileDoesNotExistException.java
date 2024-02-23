package zw.co.danhiko.medichronicle.service.medichronicle.exceptions;

public class FileDoesNotExistException extends RuntimeException{
    public FileDoesNotExistException(String message) {
        super(message);
    }
}

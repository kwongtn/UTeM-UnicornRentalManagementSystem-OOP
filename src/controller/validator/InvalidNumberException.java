package controller.validator;

public class InvalidNumberException extends Exception{
    private static final long serialVersionUID = 1L;

    public InvalidNumberException(String field){
        super(field + " is not a number.");
    }
}
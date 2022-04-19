package exceptions;

public class InvalidEmails extends Exception {
    String malformedEmails;

    public InvalidEmails(String malformedEmails) {
        this.malformedEmails = malformedEmails;
    }
}
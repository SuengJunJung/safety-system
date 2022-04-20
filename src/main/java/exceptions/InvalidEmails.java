package exceptions;

public class InvalidEmails extends Exception {
    public InvalidEmails(String malformedEmails) {
        super("Invalid emails: \n" + malformedEmails);
    }
}
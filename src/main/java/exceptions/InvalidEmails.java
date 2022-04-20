package exceptions;

/*
Name(s): Arvinder Dhanoa, Suengjun Jung
Assignment: Group Project
Goals: Exception for an invalid email.
Inputs: invalid email.
Outputs: Crash if not handled. (Please handle it)
 */
public class InvalidEmails extends Exception {
    public InvalidEmails(String malformedEmails) {
        super("Invalid emails: \n" + malformedEmails);
    }
}
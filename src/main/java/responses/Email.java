package responses;

import responses.ResponseKind;

public class Email implements ResponseKind {
    String rawEmail;
    public Email(String rawEmail, String content){
        this.rawEmail = rawEmail;
    }

    @Override
    public void send() {

    }
}
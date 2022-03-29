package responses;

public class Email implements ResponseKind {
    String rawEmail;

    String content;

    public Email(String rawEmail) {
        this.rawEmail = rawEmail;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void send() {

    }
}
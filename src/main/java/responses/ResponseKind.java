package responses;

public interface ResponseKind {
    void send();

    void setContent(String content);
}
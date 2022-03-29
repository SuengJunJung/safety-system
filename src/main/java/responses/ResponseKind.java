package responses;

public interface ResponseKind {
    void send() throws Exception;

    void setContent(String content);
}
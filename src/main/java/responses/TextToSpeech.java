package responses;

public class TextToSpeech implements ResponseKind {
    String content;

    public TextToSpeech() {}

    @Override
    public void send() {

    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }
}
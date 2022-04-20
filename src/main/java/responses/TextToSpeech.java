package responses;

/*
Name(s): Arvinder Dhanoa, Suengjun Jung
Assignment: Group Project
Goals: An unused class (never finished) for text to speech!
Inputs: content
Outputs: audio
 */
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
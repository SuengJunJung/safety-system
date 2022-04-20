package responses;

/*
Name(s): Arvinder Dhanoa, Suengjun Jung
Assignment: Group Project
Goals: Log content to console.
Inputs: content
Outputs: console output.
 */
public class ConsoleLogger implements ResponseKind {
    String content;

    public ConsoleLogger() { content = "";}

    @Override
    public void send() {
        System.out.println(content);
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }
}
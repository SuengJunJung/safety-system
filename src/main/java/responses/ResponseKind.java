package responses;


/*
Name(s): Arvinder Dhanoa, Suengjun Jung
Assignment: Group Project
Goals: An interface used for program output like emails for a user.
Inputs: content
Outputs: Whatever you want.
 */
public interface ResponseKind {
    //----------------------------
    //Sends the response
    //----------------------------
    void send() throws Exception;

    //----------------------------
    //Sets the content for the response
    //----------------------------
    void setContent(String content);
}
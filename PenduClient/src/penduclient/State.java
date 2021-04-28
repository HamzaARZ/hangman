/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penduclient;

/**
 *
 * @author Lenovo
 */
public class State {
    
    private static final int DEFAULT_INITIAL_RETRIES_NB = 10;
    
    private String word;
    private int retries;
    private String serverResponse;

    public State(String word) {
        this.word = word;
        this.retries = DEFAULT_INITIAL_RETRIES_NB;
        this.serverResponse = "START";
    }
    
    

    public State(String word, int retries) {
        this.word = word;
        this.retries = retries;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    public String getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(String serverResponse) {
        this.serverResponse = serverResponse;
    }
    
    public void decrementRetries() {
        this.retries --;
    }
    
}

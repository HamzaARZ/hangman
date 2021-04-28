/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penduclient;

import java.io.BufferedReader;
import java.io.PrintStream;

/**
 *
 * @author Lenovo
 */
public class Game {
    //private final static int nombreEssais = 10;
    
    private String clientName;
    private PrintStream out;
    private BufferedReader in;
    
    private State state;

    public Game(String clientName, String word, PrintStream out, BufferedReader in) {
        this.clientName = clientName;
        this.out = out;
        this.in = in;
        this.state = new State(word);
    }


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    
    
    public void play(String character){
        try{
            out.println(character);
            String result = in.readLine();
            state.setServerResponse(result);
            
            if(result.equals("correct")){
                state.setWord(in.readLine());      
            }else if(!result.equals("already chosen")){
                state.decrementRetries();
            }

        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public boolean isRunning() {
        return state.getRetries()>0 && state.getWord().indexOf('-') != -1;
    }
    
    public boolean hasLost() {
        return state.getRetries() == 0;
    }
   
    
}

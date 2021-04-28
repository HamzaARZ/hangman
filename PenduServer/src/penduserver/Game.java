/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penduserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Game{
    
    private Socket clientSocket;
    private String clientName;
    private int score;
    private String word;
    private ArrayList<String> chosenCharacters;
    private BufferedReader in;
    private PrintStream out;
            
    public Game(Socket clientSocket, String clientName, String word) throws IOException {
        this.clientSocket = clientSocket;
        this.clientName = clientName;
        this.score = 7;
        this.word = word;
        this.chosenCharacters = new ArrayList<String>();
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.out = new PrintStream(clientSocket.getOutputStream());
    }
    
    
    public String hideCharacters(String word){
        char[] wordChars = word.toCharArray();

        //remplacer les lettres situés au milieu par des -
        for(int i=1; i<word.length()-1; i++){
            wordChars[i] = '-';
        }
        word = String.valueOf(wordChars);
        return word;
    }
    
    
    
    public void play() {
        try{
            
            String wordToClient = hideCharacters(this.word);
          
            
            out.println(wordToClient);
            System.out.println("word chosen for " + this.clientName + " : " + word);
           
            
            
            while(this.score>0 && wordToClient.indexOf('-')>=0){
                
                String character = in.readLine();
                String result = "incorrect";
                char[] wordChars = word.toCharArray();
                char[] wordToClientChars = wordToClient.toCharArray();
                
                
                //check if the character is already used
                if(chosenCharacters.contains(character)){
                    result = "already chosen";
                }else{
                    
                    chosenCharacters.add(character);
                    for(int i=1; i<this.word.length()-1; i++){
                     
                        if(Character.toString(wordChars[i]).equals(character)){
                            result = "correct";
                            wordToClientChars[i] = character.toCharArray()[0];
                        }
                    }
                }
                
                wordToClient = String.valueOf(wordToClientChars);

                out.println(result);
                
                if(result.equals("correct"))
                    out.println(wordToClient);
                else if(result.equals("incorrect"))
                    this.score -= 1;
            }
            
            if(this.score == 0)
                out.println(word);
            
            
            String choix = in.readLine();
            
            if(choix.equals("o")){
                Thread createGame = new Thread(new CreateGame(clientSocket, clientName));
                createGame.start();
            }else{
                System.out.println("deconnexion with " + this.clientName);
                in.close();
                out.close();
                clientSocket.close();
            }
                    
        }catch(Exception ex){
            System.err.println(ex);
        }
    }
         
}

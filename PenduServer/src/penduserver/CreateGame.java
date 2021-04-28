/*                                               
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penduserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Lenovo
 */
public class CreateGame implements Runnable{
    private Socket clientSocket;
    private String clientName;

    public CreateGame(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public CreateGame(Socket clientSocket, String clientName) {
        this.clientSocket = clientSocket;
        this.clientName = clientName;
    }
    
    
    @Override
    public void run() {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            //connect for the first time
            if(this.clientName == null){
                this.clientName = in.readLine();
                System.out.println("get connexion from " + this.clientName);
            }
            
            
            String word = new Word().getRandomWord();
            Game game = new Game(this.clientSocket, this.clientName, word);
            game.play();
            
        
        }catch(Exception e){
            System.err.println(e);
        }
        
    }
}

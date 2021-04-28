/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penduserver;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Lenovo
 */
public class PenduServer {

    /**
     * @param args the command line arguments
     */
    private final static int port = 60000;
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ServerSocket serverSocket = new ServerSocket(port);
        
        while(true){
            try{
                System.out.println("waiting for client...");
                Socket clientSocket = serverSocket.accept();
                
                Thread createGame = new Thread(new CreateGame(clientSocket));
                createGame.start();
                
            
            
            }catch(Exception e){
                System.err.println(e);
            }
            
        }
    }
    
}
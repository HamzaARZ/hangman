/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penduclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class PenduClient {

    /**
     * @param args the command line arguments
     */
    private static int port = 60000;
    private static String machine;
    
    public PenduClient() throws UnknownHostException {
        this.machine = InetAddress.getLocalHost().getHostAddress();
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner sc = new Scanner(System.in);
        System.out.print("enter your name : ");
        String clientName = sc.nextLine();
        
        
        Socket socket;
        try {
            socket = new Socket(machine, port);
        
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream());
            
            //send the name of the player to server.
            out.println(clientName);
            
           
            boolean exit = false;
            while(!exit){
                System.out.println("the game starts...");
                String word = in.readLine();

                Game game = new Game(clientName, word, out, in);
                
                while(game.isRunning()){
                    System.out.println("word : " + game.getState().getWord());
                    System.out.println("you have " + game.getState().getRetries() + " chances");
                    
                    
                    //check if the input is an alphabetic character
                    String character = "";
                    while(character.length() != 1 || !Character.isLetter(character.charAt(0))){
                        System.out.print("enter a character (a-z) : ");
                        character = sc.nextLine();
                        character = character.toLowerCase();
                    }

                    
                    game.play(character);
                    displayState(game.getState());
                }
                
                System.out.println("\ngame over");
                if(game.hasLost()){
                    System.out.println("you lost -_- PENDU !!");
                    System.out.println("the Word was : " + in.readLine());
                    
                }else{
                    System.out.println("you win :D, Your Score : " + game.getState().getRetries() + "\n");
                }
                
                
                
                
                String choix = "";
                while(!choix.equals("o") && !choix.equals("n")){
                    System.out.print("rejouer ?? (n/o) : ");
                    choix = sc.nextLine();
                    choix = choix.toLowerCase();
                }
                
                out.println(choix);
                
                
                if(choix.equals("n")){
                    System.out.println("Thank you for playing !!");
                    exit = true;
                }
                
                
            }
            
            in.close();
            out.close();
            socket.close();
            sc.close();
            
               
        }catch (IOException ex) {
            System.err.println(ex);
        }
                
    }
    
    private static void displayState(State state) {
        
        String response = state.getServerResponse();
        
        if(response.equals("already chosen")){
            System.out.println("this character is alreaady chosen");
            System.out.println("try again");

        }else if(response.equals("correct")){
            System.out.println("correct :D");


        }else{
            System.out.println("incorrect !!");
        }
    }
            
        
        
}

    
    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penduserver;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Lenovo
 */
public class Word {
    private final ArrayList<String> words = new ArrayList<String>(Arrays.asList("word1", "word2", "word3"));
    
    public String getRandomWord(){
        int min = 0;
        int max = words.size()-1;
        int randomInt = (int)(Math.random() * (max - min + 1) + min);
        
        String randomWord = words.get(randomInt);
        return randomWord;
    }
}

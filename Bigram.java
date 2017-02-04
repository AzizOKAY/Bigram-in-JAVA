/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aziz OKAY
 * @param <T>
 */
public interface Bigram <T>{
    
    public void readFile (String filename);
    public int numGrams();
    @Override
    public String toString();
    public int numOfGrams(T elm1, T elm2);
}

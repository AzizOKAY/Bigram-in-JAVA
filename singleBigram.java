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
public class singleBigram <T>{
    T first;
    T second;
    int numOfGrams;
    
    
    singleBigram() {
        numOfGrams = 0;
    }
    
    singleBigram(T elm1, T elm2){
        first = elm1;
        second = elm2;
        numOfGrams = 0;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public int getNumOfGrams() {
        return numOfGrams;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public void setNumOfGrams(int numOfGrams) {
        this.numOfGrams = numOfGrams;
    }
    
    public boolean isEqual(T elm1, T elm2) {
        return (this.first == elm1 && this.second == elm2);
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString()
    { 
        return "(" + first + ", " + second + ")"; 
    }
}

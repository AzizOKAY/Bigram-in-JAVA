

/**
 *
 * @author Aziz OKAY
 * @param <T>
 */

import java.nio.file.Files; 
import java.nio.file.Paths;
import java.io.IOException;

public class BigramDyn <T> implements Bigram<T>{
   int dType;
   int used;
   singleBigram<T>[] bigram;
    
    BigramDyn(int dataTyp) {
        dType = dataTyp;
        //bigram = new singleBigram[50];
        bigram =(singleBigram<T>[]) new singleBigram[50];
        
        used = 0;
    }
    
    /**
     * read file method
     * @param filename name of file
     */
    @Override
    public void readFile (String filename) {
        switch (dType) {
            case 1:
                readFileInt(filename);
                break;
            case 3:
                readFileDouble(filename);
                break;
            default:
                readFileString(filename);
                break;
        }    
    }
    
    /**
     *  read file method for integer
     * @param filename name of file
     */
    public void readFileInt (String filename) {
        int counter = 0;
        Integer temp1, temp2 = 0;
        
        try{
            for (String line : Files.readAllLines(Paths.get(filename))) {
                for (String part1 : line.split("\\s+")) {
                    temp1 = Integer.valueOf(part1);
                    if (counter > 0 ) {
                        fillMap((T)temp2, (T)temp1);
                    }
                    temp2 = temp1;
                    counter++;
                }
            }
        }catch(IOException e) {
            e.getMessage();
        }
    }
    
    /**
     * read file method for Double
     * @param filename name of file
     */
    public void readFileDouble (String filename) {
        int counter = 0;
        Double temp1, temp2 = 0.0;
        
        try{
            for (String line : Files.readAllLines(Paths.get(filename))) {
                for (String part1 : line.split("\\s+")) {
                    temp1 = Double.valueOf(part1);
                    if (counter > 0 ) {
                        fillMap((T)temp2, (T)temp1);
                    }
                    temp2 = temp1;
                    counter++;
                }
            }
        }catch(IOException e) {
            e.getMessage();
        }
    }
    
    /**
     * read file method for String 
     * @param filename name of file
     */
    public void readFileString (String filename) {
        int counter = 0;
        String temp1, temp2 = "";
        
        try{
            for (String line : Files.readAllLines(Paths.get(filename))) {
                for (String part1 : line.split("\\s+")) {
                    temp1 = part1;
                    if (counter > 0 ) {
                        fillMap((T)temp2, (T)temp1);
                    }
                    temp2 = temp1;
                    counter++;
                }
            }
        }catch(IOException e) {
            e.getMessage();
        }
    }
    
    /**
     * check if given pair is exist
     * @param elm1 first element of pair
     * @param elm2 second element of pair
     * @return index of given pair
     */
    public int isExist(T elm1, T elm2) {
        for (int i = 0; i < used; i++) {
            if(bigram[i].isEqual(elm1, elm2))
                return i;
        }
        return -1;
    }
    
    
    /**
     * fill map method
     * @param elm1 first element of pair
     * @param elm2 second element of pair
     */
    public void fillMap(T elm1, T elm2) {
        int isExist;
        
        isExist = isExist(elm1, elm2);
        bigram[used] = new singleBigram<>();
        if (isExist == -1) {
            bigram[used].setFirst(elm1);
            bigram[used].setSecond(elm2);
            bigram[used].setNumOfGrams(1);
            used++;
        }else {
            bigram[isExist].setNumOfGrams(bigram[isExist].numOfGrams + 1);
        }
    }
    
    /**
     * return number of given pair in map
     * @param elm1 first element of pair
     * @param elm2 second element of pair
     * @return a number
     */
   @Override
    public int numOfGrams(T elm1, T elm2) {
        int result = isExist(elm1, elm2);
        if (result != -1)
            return bigram[result].getNumOfGrams();
        else 
            return 0;
    }
    
    /**
     * number of all bigrams
     * @return  a number
     */
   @Override
    public int numGrams() {
        return used;
    }
    
    /**
     * to string 
     * @return a string
     */
    @Override
    public String toString() { 
        for (int i = 0; i < used; i++) {
            System.out.println("("+ bigram[i].getFirst() + "," + 
                    bigram[i].getSecond() + ") => " + bigram[i].getNumOfGrams());
        }    
        return "";
    }

    
    
    
}

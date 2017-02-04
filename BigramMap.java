/**
 *
 * @author Aziz OKAY
 */
import java.util.Map;
import java.util.Map.Entry;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 * @author jan
 * @param <T> 
 */
public class BigramMap <T> implements Bigram<T>{
    int dType;
    Map<Pair<T>, Integer> bigram;
    
    
    /**
     * 
     * @param dataTyp 
     */
    BigramMap(int dataTyp) {
        dType = dataTyp;
        bigram = new HashMap<>();
    }
    /**
     *
     * @param filename
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
     * 
     * @param filename 
     */
    public void readFileInt (String filename) {
        int counter = 0;
        Integer temp1, temp2 = 0;
        
        try{
            for (String line : Files.readAllLines(Paths.get(filename))) {
                System.out.println(line);
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
     * 
     * @param filename 
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
     * 
     * @param filename 
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
     * 
     * @param elm1
     * @param elm2 
     */
    public void fillMap(T elm1, T elm2) {
        boolean isExist = false;
        Map.Entry<Pair, Integer> entryRe = null;
        int valu = 0;
        Pair aPair = new Pair((T)elm1, (T)elm2);
        Iterator<Entry<Pair<T>,Integer>> iter = bigram.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Pair, Integer> entry = (Map.Entry)iter.next();
            if (entry.getKey().first == elm1 && entry.getKey().second == elm2) {
                isExist = true;
                entryRe = entry;
                valu = entry.getValue() + 1;
            }    
        }
        
        if (isExist == false) 
            bigram.put(aPair, 1);
        else {
            bigram.remove(entryRe.getKey());
            bigram.put(aPair, valu);
        }
    }
    
    
    /**
     * function that give number of given pair
     * @param elm1 first in pair
     * @param elm2 second in pair
     * @return number of pair if exist 0 is not
     */
    @Override
    public int numOfGrams(T elm1, T elm2) {
        Iterator<Entry<Pair<T>,Integer>> iter = bigram.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Pair, Integer> entry = (Map.Entry)iter.next();
            if (entry.getKey().first == elm1 && entry.getKey().second == elm2)
                return entry.getValue();
        }
        return 0;
    }
    
    /**
     * number of all bigrams in map
     * @return number of all bigrams in map
     */
    @Override
    public int numGrams() {
        return bigram.size();
    }
    
    /**
     * override toString
     * @return a string
     */
    @Override
    public String toString() { 
        StringBuilder sb = new StringBuilder();
        
        Iterator<Entry<Pair<T>,Integer>> iter = bigram.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Pair, Integer> entry = (Map.Entry)iter.next();
            sb.append(entry.getKey());
            sb.append('=').append('>');
            sb.append(entry.getValue());
            sb.append('\n');
        }
        return sb.toString();
    }
    
    /**
     * Generic Pair class
     * @param <T> 
     */
    public class Pair <T> {
    private T first;
    private T second;

    /**
     * two parameter constructor
     * @param first 
     * @param second 
     */
    public Pair(T first, T second) {
        super();
        this.first = first;
        this.second = second;
    }

    /**
     * to string for pair
     * @return  a string
     */
    @Override
    public String toString()
    { 
        return "(" + first + ", " + second + ")"; 
    }

    /**
     * check if given pair is equal to this
     * @param other given object
     * @return true if equal false otherwise
     */
    public boolean Equal(singleBigram other) {
        return (this.first == other.getFirst() && this.second == other.getSecond());
    }
    
    /**
     * getting first element of pair
     * @return first element
     */
    public T getFirst() {
        return first;
    }

    /**
     * setting first element
     * @param first value
     */
    public void setFirst(T first) {
        this.first = first;
    }

    /**
     * getting second element of pair
     * @return second element
     */
    public T getSecond() {
        return second;
    }

    /**
     * setting second element
     * @param second  value
     */
    public void setSecond(T second) {
        this.second = second;
    }
}
    
    
}




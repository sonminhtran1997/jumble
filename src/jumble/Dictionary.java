
package jumble;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Son Tran
 */
public class Dictionary {
    //declare all necessary variable
    File dictFile = null;
    Scanner fileScanner;
    String current;
    String scrambleWord = "";
    int length = 0;
    ArrayList<String> wordList;
    String[] wordArray;
    Random rand = new Random();
    JumbleSolver solver;
    
    final int RAND_TIMES_MAX = 24;
    public Dictionary()
    {
        //construct dictionary class
        wordList = new ArrayList();
    }
    public Dictionary(File file){
        ////construct dictionary class with param file
        this();
        setFile(file);
    }
    public void setFile(File file){
        //set the file of dictionary
        this.dictFile = file;
    }
    public boolean checkStatusSetup(){
        //check if the file is null
        if(dictFile == null)
            return false;
        try
        {
            fileScanner = new Scanner(dictFile);
        }
        catch(FileNotFoundException ex)
        {
            return false;
        }
        try
        {
            //read each line of the file and add dictionary word to array
            for(; fileScanner.hasNextLine(); wordList.add(current))
                current = fileScanner.nextLine().toUpperCase();
            fileScanner.close();
            length = wordList.size();
            wordArray = new String[length];
            wordArray = (String[])wordList.toArray(wordArray);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error", "Cannot read Dictionary", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public String getJumble(){
        String jumbleWord = "";
        boolean repeat = true;
        int randNum = 0;
        int randTimes = 0;
        String[] solutions;
        //get random number to get random word and scramble
        randNum = rand.nextInt(wordArray.length);
        randTimes =  rand.nextInt(RAND_TIMES_MAX) + 1;
        scrambleWord = wordArray[randNum];
        //if the word is in the list of solutions, scramble again
        while(repeat)
        {
            jumbleWord = scramble(scrambleWord, randTimes);
            solver = new JumbleSolver(jumbleWord,this);
            solutions = solver.getSolutions();
            for (int i = 0; i < solutions.length; i++) {
                if (jumbleWord.equals(solutions[i])) {
                    repeat = true;
                    break;
                }
                else
                {
                    repeat = false;
                }
            }
        }
        return jumbleWord;
    }
    public String scramble(String inputString, int times){
        String outputString = "";
        char[] workArray = inputString.toCharArray();
        //swap the character of the string
        outputString = swap(workArray, rand.nextInt(workArray.length-1), rand.nextInt(workArray.length-1));
        if (times < 1) 
        {
            return outputString;
        }
        else
        {
            outputString = scramble(outputString, times - 1);
        }
        return outputString;
    }
    public String swap(char[] swapedArray, int fromIndex, int toIndex)
    {
        //swap the string to create a new string
        StringBuilder outputString = new StringBuilder("");
        char temp = '\0';
        temp = swapedArray[fromIndex];
        swapedArray[fromIndex] = swapedArray[toIndex];
        swapedArray[toIndex] = temp;
        for (int i = 0; i < swapedArray.length; i++) {
            outputString.append(swapedArray[i]);
        }
        return outputString.toString();
    }
}

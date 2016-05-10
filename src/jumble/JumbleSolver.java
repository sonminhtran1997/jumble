package jumble;

import java.util.ArrayList;


public class JumbleSolver {
    //declare al necessary variables
    String jumble = "";
    Dictionary dictionary;
    PermutationGenerator permutationTool;
    final int NOT_FOUND = -1;
    
    public JumbleSolver(String jumble, Dictionary dictionary){
        //construct the Jumble Solver class
        this.jumble = jumble;
        this.dictionary = dictionary;
    }
    public String[] getSolutions(){
        ArrayList<String> solutions = new ArrayList<String>();
        String[] solutionsArray;
        permutationTool = new PermutationGenerator(jumble);
        String[] permutation = new String[permutationTool.getPermutation().size()];
        //create a list of permutation
        permutation = permutationTool.getPermutation().toArray(permutation);
        //look up in the dictionary if the word is in the dictionary or not
        for (int i = 0; i < permutation.length; i++) {
            if (binSearch(dictionary.wordArray, permutation[i], 0, dictionary.wordArray.length - 1) 
                    && (!solutions.contains(permutation[i]))) 
            {
                //add the word to solution array
                solutions.add(permutation[i]);
            }
        }
        solutionsArray = new String[solutions.size()];
        solutionsArray = solutions.toArray(solutionsArray);
        return solutionsArray;
    }
    public boolean binSearch(String[] data, String word, int fromIndex, int toIndex){
        //recursive binary search
        int mid = 0;
        if (fromIndex > toIndex) 
        {
            return false;
        }
        mid = (fromIndex + toIndex) / 2;
        if (word.equals(data[mid])) 
        {
            return true;
        }
        else if (word.compareTo(data[mid]) < 0)
        {
            return binSearch(data, word, fromIndex, mid - 1);
        }
        else
        {
            return binSearch(data, word, mid + 1, toIndex);
        }
    }
}

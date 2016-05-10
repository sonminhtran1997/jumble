/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumble;

import java.util.ArrayList;

/**
 *
 * @author Son Tran
 */
public class PermutationGenerator {
    String word;
    public PermutationGenerator(String inputString){
        //take the input string to construc the permation generator
        this.word = inputString;
    }
    public ArrayList<String> getPermutation()
    {
        ArrayList<String> output = new ArrayList<String>();
        if (word.length() == 0) {
            output.add(word);
        }
        else
        {
            for (int i = 0; i < word.length(); i++) {
                //cut the word into two part
                String shorter = word.substring(0,i) + word.substring(i+1);
                //take the shorter permutation of the first part
                ArrayList<String> shorterPermutations = new PermutationGenerator(shorter).getPermutation();
                for(String s :shorterPermutations)
                {
                    //combine two part to have the permutation
                    output.add(word.charAt(i) + s);
                }
            }
        }
        return output;
    }
}

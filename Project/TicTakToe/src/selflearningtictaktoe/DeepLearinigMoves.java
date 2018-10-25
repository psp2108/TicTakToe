/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selflearningtictaktoe;

import java.util.*;

/**
 *
 * @author Pratik
 */
public class DeepLearinigMoves {

    public static final int winPoint = 2;
    public static final int tiePoint = 1;
    public static final int losePoint = -1;
    public static int initialEmptySpaces;

    public String Position, BestMove;
    static char toMove;
    public int score;
    public boolean myMove;
    private DeepLearinigMoves deepLearinigMoves;
    static String[][] xoro = new String[3][3];
    static String arrayOfThree[] = new String[3];
    static int width = xoro.length;
    static int height = xoro[0].length;

    public DeepLearinigMoves(String Position, char toMove, boolean myMove) {
        this.Position = Position;
        this.toMove = toMove;
        this.myMove = !myMove;
        this.initialEmptySpaces = countCharcterInString(this.Position, '_');
    }
    static int count = 0;

    public DeepLearinigMoves(boolean myMove) {
        this.myMove = myMove;
        count++;
    }

    public String startAnalysis() {
        int emptySpaces = countCharcterInString(Position, '_');
        int tempMaxMin;
        String moves = "";
        
        Vector<DeepLearinigMoves> vector = new Vector<DeepLearinigMoves>();

        for (int i = 0; i < emptySpaces; i++) {
            vector.add(new DeepLearinigMoves(!myMove));
            vector.elementAt(i).calculateBestMoves(Position, i, vector.elementAt(i));
        }
        
        
        if(emptySpaces == 1){
            this.score = vector.elementAt(0).score;// + vector.elementAt(1).score;
        }
        else if(emptySpaces > 1){
            if(!myMove){
                //Its my move select best
                tempMaxMin = 0;
                for(int i=0; i<vector.size(); i++){
                    if(vector.elementAt(i).score > tempMaxMin){
                        tempMaxMin = vector.elementAt(i).score;
                    }
                }
                
            }
            else{
                //Its not my move select worst
                tempMaxMin = 99;
                for(int i=0; i<vector.size(); i++){
                    if(vector.elementAt(i).score < tempMaxMin){
                        tempMaxMin = vector.elementAt(i).score;
                    }
                }
            }
            this.score = tempMaxMin;
        }
        
            //System.out.println(printXO(Position) + " -> S: " + this.score + " -> M: " + this.BestMove);
       
        
        if(emptySpaces == initialEmptySpaces){
//            System.out.println();
//            System.out.println();
            
            String move, _position;
            int _score;
            
             tempMaxMin = -5;
            for(int i=0; i<vector.size(); i++){
               move = vector.elementAt(i).BestMove;
               _position = vector.elementAt(i).Position;
               _score = vector.elementAt(i).score;
               //System.out.println(printXO(_position) + " -> S: " + _score + " -> M: " + move);
               
               if(_score > tempMaxMin){
                   tempMaxMin = _score;
               }
            }
            
            
            for(int i=0; i<vector.size(); i++){
                move = vector.elementAt(i).BestMove;
               _score = vector.elementAt(i).score;
               if(_score == tempMaxMin){
                   moves += move;
               }
            }
            
            return getRandomCharFromString(moves);
            
        }
        else{
            return null;
        }
     
    }

    public static String getRandomCharFromString(String moves) {
        char[] temp = moves.toCharArray();
        int ranNum = 0;

        if ((temp.length-1) <= 0) {
            return moves;
        } else {

            Random r = new Random();
            ranNum = r.nextInt(temp.length - 1);
            
            return temp[ranNum] + "";
        }
    }
    
    ///////////////////
    public static String printXO(String s){
        String temp = "";
        
        temp = s.charAt(0) + " " +
                s.charAt(1) + " " + 
                s.charAt(2) + "\n" +
                s.charAt(3) + " " + 
                s.charAt(4) + " " +
                s.charAt(5) + "\n" +
                s.charAt(6) + " " +
                s.charAt(7) + " " +
                s.charAt(8) + "\n";
        return temp;
    }
    ///////////////////
    
    private void calculateBestMoves(String Position, int index, DeepLearinigMoves deepLearinigMoves) {
        this.BestMove = GetLocation.getNotation(getOccuranceIndex(Position, '_', index));
        
        if (myMove) {
            this.Position = replaceChar(Position, '_', toMove, index);
        } else {
            if (toMove == 'x') {
                this.Position = replaceChar(Position, '_', 'o', index);
            } else {
                this.Position = replaceChar(Position, '_', 'x', index);
            }
        }

        String pos = checkWinner(this.Position);

        if (pos == null) {
            this.startAnalysis();
        } else {
            

            /*if (this.countCharcterInString(this.Position, '_') > 0) {
                if (pos.equals("x")) {
                    if (this.toMove == 'x') {
                        this.score = this.winPoint * 2;
                    } else {
                        this.score = this.losePoint * 2;
                    }
                } else if (pos.equals("o")) {
                    if (this.toMove == 'o') {
                        this.score = this.winPoint * 2;
                    } else {
                        this.score = this.losePoint * 2;
                    }
                }
            } else {*/
                if (pos.equals("t")) {
                    this.score = this.tiePoint;
                } else if (pos.equals("x")) {
                    if (this.toMove == 'x') {
                        this.score = this.winPoint;
                    } else {
                        this.score = this.losePoint;
                    }
                } else if (pos.equals("o")) {
                    if (this.toMove == 'o') {
                        this.score = this.winPoint;
                    } else {
                        this.score = this.losePoint;
                    }
                }
            //}

        }

    }

    public static int countCharcterInString(String string, char character) {
        int counter = 0;
        if (string != null) {
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == character) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public static String replaceChar(String string, char original, char toReplace, int occuranceIndex) {
        int indexOfReplace = -1;
        char arr[] = string.toCharArray();

        for (int i = 0; i <= occuranceIndex; i++) {
            indexOfReplace++;
            while (arr[indexOfReplace] != original) {
                indexOfReplace++;
            }
        }

        arr[indexOfReplace] = toReplace;

        return toCharString(arr);
    }

    public static int getOccuranceIndex(String string, char character, int occurance){
         int indexOfOccurance = -1;
        char arr[] = string.toCharArray();

        for (int i = 0; i <= occurance; i++) {
            indexOfOccurance++;
            while (arr[indexOfOccurance] != character) {
                indexOfOccurance++;
            }
        }
        
        return indexOfOccurance;
    }
    
    public static String toCharString(char[] arr) {
        String temp = "";

        for (char a : arr) {
            temp += a;
        }
        return temp;
    }

    public static String checkWinner(String posStirng) {
        int strIndexing = 0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (posStirng.charAt(strIndexing) == '_') {
                    xoro[x][y] = "";
                } else {
                    xoro[x][y] = posStirng.charAt(strIndexing) + "";
                }
                strIndexing++;
            }
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                arrayOfThree[y] = xoro[x][y];
            }
            if (checkArrayEqual(arrayOfThree) == true) {
                return arrayOfThree[0];
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                arrayOfThree[x] = xoro[x][y];
            }
            if (checkArrayEqual(arrayOfThree) == true) {
                return arrayOfThree[0];
            }
        }

        for (int xy = 0; xy < width; xy++) {
            arrayOfThree[xy] = xoro[xy][xy];
        }
        if (checkArrayEqual(arrayOfThree) == true) {
            return arrayOfThree[0];
        }

        int y = 0;
        for (int x = width - 1; x >= 0; x--) {
            arrayOfThree[x] = xoro[x][y];
            y++;
        }
        if (checkArrayEqual(arrayOfThree) == true) {
            return arrayOfThree[0];
        }

        if (posStirng.contains("_")) {
            return null;
        } else {
            return "t";
        }
    }

    public static boolean checkArrayEqual(String[] array) {
        String temp = array[0];
        boolean tempFlag = true;

        for (int i = 1; i < array.length; i++) {
            if (!array[i].equals(temp)) {
                tempFlag = false;
            }
        }

        if (tempFlag) {
            if (temp.equals("")) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

}

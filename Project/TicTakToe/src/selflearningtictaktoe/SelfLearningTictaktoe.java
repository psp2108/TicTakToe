/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selflearningtictaktoe;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author PsP
 */
public class SelfLearningTictaktoe extends JFrame implements ActionListener {

    static Panel mainpane = new Panel();
    static Button[][] xoro = new Button[3][3];
    static boolean toggleXO = true;
    static String arrayOfThree[] = new String[3];
    static Label winner = new Label("Game in progress");
    static int width = xoro.length;
    static int height = xoro[0].length;
    static int count = 9;

    static Button playButton, computerButton;

    public static void main(String[] args) {
        SelfLearningTictaktoe mainFrame = new SelfLearningTictaktoe();
        mainFrame.setSize(400, 400);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainFrame.setLayout(new BorderLayout());
        mainpane.setLayout((new GridLayout(5, 3)));

        playButton = new Button("Play Again");
        computerButton = new Button("Ask Computer to Play");

        for (int i = 0; i < arrayOfThree.length; i++) {
            arrayOfThree[i] = "";
        }

        for (int j = 0; j < xoro.length; j++) {
            for (int i = 0; i < xoro[j].length; i++) {
                xoro[i][j] = new Button("");
                mainpane.add(xoro[i][j]);
                xoro[i][j].addActionListener(mainFrame);
            }
        }

        mainpane.add(new Label());
        mainpane.add(new Label());
        mainpane.add(new Label());

        mainpane.add(playButton);
        mainpane.add(new Label());
        mainpane.add(computerButton);

        mainFrame.add(winner, "North");
        mainFrame.add(mainpane, "Center");


       /* while (!winner.getText().equals("Winner is: X") && !winner.getText().equals("Winner is: O")) {
            placeNextMove();
              try{
                    Thread.sleep(1000);
                }
                catch(Exception e){

                }
            mainFrame.revalidate();
            if (!winner.getText().equals("Game in progress")) {
                System.out.println(winner.getText());
                resetBoard();
                
              
            }
        }*/
        
        
        playButton.addActionListener(mainFrame);
        computerButton.addActionListener(mainFrame);
    }

    public static void resetBoard() {
        for (int j = 0; j < xoro.length; j++) {
            for (int i = 0; i < xoro[j].length; i++) {
                xoro[i][j].setLabel("");
                xoro[i][j].setEnabled(true);
            }
        }
        winner.setText("Game in progress");
        for (int i = 0; i < arrayOfThree.length; i++) {
            arrayOfThree[i] = "";
        }
        count = 9;
        computerButton.setEnabled(true);
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

    public static String checkWinner() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                arrayOfThree[y] = xoro[x][y].getLabel();
            }
            if (checkArrayEqual(arrayOfThree) == true) {
                return arrayOfThree[0];
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                arrayOfThree[x] = xoro[x][y].getLabel();
            }
            if (checkArrayEqual(arrayOfThree) == true) {
                return arrayOfThree[0];
            }
        }

        for (int xy = 0; xy < width; xy++) {
            arrayOfThree[xy] = xoro[xy][xy].getLabel();
        }
        if (checkArrayEqual(arrayOfThree) == true) {
            return arrayOfThree[0];
        }

        int y = 0;
        for (int x = width - 1; x >= 0; x--) {
            arrayOfThree[x] = xoro[x][y].getLabel();
            y++;
        }
        if (checkArrayEqual(arrayOfThree) == true) {
            return arrayOfThree[0];
        }

        return null;
    }

    public static Button getNextMove() {
        Random r = new Random();
        int x = r.nextInt(3);
        int y = r.nextInt(3);
        return xoro[x][y];
    }

    public static void placeNextMove() {
        /*Button temp = null;
        while(!((temp = getNextMove()).getLabel().equals(""))){}
        updateAction(temp);*/

        String position = "", BestMove = "";
        char toMove;
        int xy[];
        String btnLabel;

        if (winner.getText().equals("Game in progress")) {

            for (int j = 0; j < xoro.length; j++) {
                for (int i = 0; i < xoro[j].length; i++) {
                    btnLabel = xoro[i][j].getLabel();

                    if (btnLabel.equals("")) {
                        position += "_";
                    } else {
                        position += xoro[i][j].getLabel();
                    }
                }
            }
            position = position.toLowerCase();

            if (toggleXO) {
                toMove = 'x';
            } else {
                toMove = 'o';
            }
            // System.out.println(position + "   -> " + toMove);
            DeepLearinigMoves l = new DeepLearinigMoves(position, toMove, true);
            BestMove = l.startAnalysis();
            xy = GetLocation.getXY(BestMove);
            updateAction(xoro[xy[0]][xy[1]]);
//        xoro[xy[0]][xy[1]].setLabel((toMove + "").toUpperCase());
        }

    }

    public static void updateAction(Button b) {
        //if(b.getLabel().equals("")){
        if (toggleXO) {
            b.setLabel("X");
        } else {
            b.setLabel("O");
        }
        toggleXO = !toggleXO;
        count--;

        if (count == 0) {
            winner.setText("Game tied");
            computerButton.setEnabled(false);
            for (int j = 0; j < xoro.length; j++) {
                for (int i = 0; i < xoro[j].length; i++) {
                    xoro[i][j].setEnabled(false);
                }
            }
            return;
        }

        //}
        String winnerSide = checkWinner();
        if (winnerSide != null) {
            if (!winnerSide.equals("")) {
                winner.setText("Winner is: " + winnerSide);
                computerButton.setEnabled(false);
                for (int j = 0; j < xoro.length; j++) {
                    for (int i = 0; i < xoro[j].length; i++) {
                        xoro[i][j].setEnabled(false);
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button b = (Button) e.getSource();

        if (b == playButton) {
            resetBoard();
        } else if (b == computerButton) {
            placeNextMove();
        } else {
            if (b.getLabel().equals("")) {
                updateAction(b);
                placeNextMove();
            }
        }
    }
}

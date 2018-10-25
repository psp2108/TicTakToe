/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selflearningtictaktoe;

/*
a   b   c
d   e   f
g   h   i
 */
public class GetLocation {

    private static int x, y;
    private static String a = "a";
    private static String b = "b";
    private static String c = "c";
    private static String d = "d";
    private static String e = "e";
    private static String f = "f";
    private static String g = "g";
    private static String h = "h";
    private static String i = "i";

    private static void FindXY(String notation) {
        if (notation.equalsIgnoreCase(a)) {
            x = 0;
            y = 0;
        } else if (notation.equalsIgnoreCase(b)) {
            x = 1;
            y = 0;
        } else if (notation.equalsIgnoreCase(c)) {
            x = 2;
            y = 0;
        } else if (notation.equalsIgnoreCase(d)) {
            x = 0;
            y = 1;
        } else if (notation.equalsIgnoreCase(e)) {
            x = 1;
            y = 1;
        } else if (notation.equalsIgnoreCase(f)) {
            x = 2;
            y = 1;
        } else if (notation.equalsIgnoreCase(g)) {
            x = 0;
            y = 2;
        } else if (notation.equalsIgnoreCase(h)) {
            x = 1;
            y = 2;
        } else if (notation.equalsIgnoreCase(i)) {
            x = 2;
            y = 2;
        } else {
            x = -1;
            y = -1;
        }
    }

    public static int[] getXY(String notation) {
        FindXY(notation);
        int[] temp = new int[2];
        temp[0] = x;
        temp[1] = y;
        return temp;
    }

    public static int getX(String notation) {
        FindXY(notation);
        return x;
    }

    public static int getY(String notation) {
        FindXY(notation);
        return y;
    }

    public static String getNotation(int position) {
        switch (position) {
            case 0:
                return a;
            case 1:
                return b;
            case 2:
                return c;
            case 3:
                return d;
            case 4:
                return e;
            case 5:
                return f;
            case 6:
                return g;
            case 7:
                return h;
            case 8:
                return i;
            default:
                return null;
        }
    }

    public static int getPosition(String Notation) {
        Notation = Notation.toLowerCase();

        switch (Notation) {
            case "a":
                return 0;
            case "b":
                return 1;
            case "c":
                return 2;
            case "d":
                return 3;
            case "e":
                return 4;
            case "f":
                return 5;
            case "g":
                return 6;
            case "h":
                return 7;
            case "i":
                return 8;
            default:
                return -1;
        }
    }

    public static String getNotation(int x, int y) {
        int temp = (x * 10) + y;

        switch (temp) {
            case 0:
                return a;
            case 1:
                return b;
            case 2:
                return c;
            case 10:
                return d;
            case 11:
                return e;
            case 12:
                return f;
            case 20:
                return g;
            case 21:
                return h;
            case 22:
                return i;
            default:
                return null;

        }
    }
}

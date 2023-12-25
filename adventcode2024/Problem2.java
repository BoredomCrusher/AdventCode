import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Problem2 {
    public static void main(String[] args) throws Exception {
        File file = new File("input2.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        int total1 = 0;
        int total2 = 0;

        Problem2 problem = new Problem2();

        HashMap<String, Integer> map1 = new HashMap<>();
        map1.put("red", 12);
        map1.put("green", 13);
        map1.put("blue", 14);

        HashMap<String, Integer> map2 = new HashMap<>();
        map2.put("red", 1);
        map2.put("green", 1);
        map2.put("blue", 1);

        // reads next like while there is is one
        while ((st = br.readLine()) != null) {
            System.out.println(st);

            total1 += problem.isPossibleGame(st, map1);
            total2 += problem.fewestNumber(st, map2);
        }
        System.out.println("total of possible games ID's: " + total1);
        System.out.println("sum of all powersets: " + total2);
    }

    private boolean roundPossibility(String st, Map<String, Integer> map) {
        // the amount of colors in the string is the num of ',' and ';'
        int commaCount = st.length() - st.replace(",", "").length() + st.replace(";", "").length();

        // the round is split into colors and the number of each color
        String[] valCheck = st.split(",", commaCount);

        for (String s : valCheck) {
            String[] getNum = s.split(" ", 3);
            int num = Integer.parseInt(getNum[1]);
            String color = getNum[2];
            if (num > map.get(color)) {
                System.out.println("\t game is not possible");
                System.out.println("\t invalid: " + color + map.get(color) + ": " + num);
                return false;
            }
        }
        return true;
    }

    // this method works backwards
    // - when a ';' is found, it's treated as the start to a new round
    // - if a ':' is found, the game checks the last round for possibility
    private int isPossibleGame(String st, Map<String, Integer> map) {
        for (int i = st.length() - 1; i >= 0; i--) {
            if (st.charAt(i) == ';') {
                if (roundPossibility(st.substring(i, st.length()), map)) {
                    st = st.substring(0, i);
                } else {
                    // the game is not possible, so return 0
                    return 0;
                }
            } else if (st.charAt(i) == ':') {
                if (roundPossibility(st.substring(i, st.length()), map)) {
                    st = st.substring(0, i);

                    // if the code has gotten this far, the game is possible
                    String[] gameNumber = st.split(" ", 2);
                    return Integer.parseInt(gameNumber[1]);
                } else {
                    // the game is not possible, so return 0
                    return 0;
                }
            }
        }
        // this should never happen
        return 0;
    }

    private Map<String, Integer> roundComparison(String st, Map<String, Integer> map) {
        // the amount of colors in the string is the num of ',' and ';'
        int commaCount = st.length() - st.replace(",", "").length() + st.replace(";", "").length();

        // the round is split into colors and the number of each color
        String[] valCheck = st.split(",", commaCount);

        for (String s : valCheck) {
            String[] getNum = s.split(" ", 3);
            int num = Integer.parseInt(getNum[1]);
            String color = getNum[2];
            if (num > map.get(color)) {
                System.out.println(map.get(color) + " replaced with " + num);
                map.put(color, num);
            }
        }
        return map;
    }

    private int fewestNumber(String st, Map<String, Integer> map) {
        for (int i = st.length() - 1; i >= 0; i--) {
            if (st.charAt(i) == ';') {
                map = roundComparison(st.substring(i, st.length()), map);
                st = st.substring(0, i);
            } else if (st.charAt(i) == ':') {
                map = roundComparison(st.substring(i, st.length()), map);

                // if the code has gotten this far, the game has been analyzed
                int powerSet = map.get("red") * map.get("green") * map.get("blue");

                // resetting values for next game
                map.put("red", 1);
                map.put("green", 1);
                map.put("blue", 1);
                return powerSet;
            }
        }
        // this should never happen
        return 0;
    }
}

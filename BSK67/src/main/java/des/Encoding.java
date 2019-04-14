package des;

import javafx.scene.control.Tab;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Encoding {
    private static String message;
    private static ArrayList<String> messageBlocks;
    private static ArrayList<String> permutedMessageBlocks;
    private static HashMap<String, String> hexMap = new HashMap<>();

    public static String sixToFour(String afterXOR) {
        StringBuilder sb = new StringBuilder();
        ArrayList<int[][]> listSboxes = Tables.getSBoxes();
        for (int i = 0; i < 8; i++) {
            StringBuilder sbTemp = new StringBuilder();
            sbTemp.append(Character.getNumericValue(afterXOR.charAt(6 * i))).append(Character.getNumericValue(afterXOR.charAt((6 * i) + 5)));
            int row = Integer.parseInt(sbTemp.toString(), 2);
            sbTemp = new StringBuilder();
            sbTemp.append(Character.getNumericValue(afterXOR.charAt((6 * i) + 1))).append(Character.getNumericValue(afterXOR.charAt((6 * i) + 2))).append(Character.getNumericValue(afterXOR.charAt((6 * i) + 3))).append(Character.getNumericValue(afterXOR.charAt((6 * i) + 4)));
            int column = Integer.parseInt(sbTemp.toString(), 2);
            sb.append(hexMap.get(Integer.toString(listSboxes.get(i)[row][column])));
        }
        return sb.toString();
    }

    public static void encrypt() {
        String[] keys = KeyGenerator.getKeysCombined();

        for (int i = 0; i < permutedMessageBlocks.size(); i++) {
            String leftMessage = permutedMessageBlocks.get(i).substring(0, 32);
            String rightMessage = permutedMessageBlocks.get(i).substring(32, 64);
            for (int j = 0; j < 1; j++) {
                String tmpRightMessage = rightMessage;
                rightMessage = permutationE(rightMessage);
                rightMessage = XOR.calculate(keys[0], rightMessage);
                rightMessage = sixToFour(rightMessage);

                // rightMessage = XOR.calculate(leftMessage,rightMessage);
                leftMessage = tmpRightMessage;
            }
            //System.out.println(leftMessage);
            System.out.println(rightMessage);
            System.out.println(permutedMessageBlocks.size());
        }
    }

    //wersja dla plikow binarnych
    public static void loadMessage() {
        StringBuilder sb = new StringBuilder();
        messageBlocks = new ArrayList();
        try {
            InputStream inputStream = new FileInputStream("test4.bin");

            int data = inputStream.read();

            while (data != -1) {
                if (data == 0) {
                    sb.append("00");
                } else {
                    String dataString = Integer.toString(data, 16);
                    if (dataString.length() == 1) {
                        sb.append("0" + dataString);
                    } else {
                        sb.append(dataString);
                    }
                }
                data = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        message = sb.toString();
    }

    //wersja dla plikow tekstowych
    public static void loadMessageTxt() {
        StringBuilder sb = new StringBuilder();
        messageBlocks = new ArrayList();
        try (Reader reader = Files.newBufferedReader(Paths.get("xd.txt"))) {
            int c;
            while ((c = reader.read()) != -1) {
                char ch = (char) c;
                int value = Character.getNumericValue(ch);
                if (value >= 0) {
                    sb.append(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        message = sb.toString();
    }

    //wersja dla plikow binarnych
    public static void divideToBlocks() {
        setMap();
        String tmpMessage = message;
        while (tmpMessage.length() > 15) {
            int block[] = new int[64];
            for (int i = 0; i < 16; i++) {
                String hexValue = String.valueOf(tmpMessage.charAt(i));
                block[4 * i] = Character.getNumericValue(hexMap.get(hexValue).charAt(0));
                block[(4 * i) + 1] = Character.getNumericValue(hexMap.get(hexValue).charAt(1));
                block[(4 * i) + 2] = Character.getNumericValue(hexMap.get(hexValue).charAt(2));
                block[(4 * i) + 3] = Character.getNumericValue(hexMap.get(hexValue).charAt(3));
            }
            messageBlocks.add(Arrays.toString(block).replaceAll("\\[|\\]|,|\\s", ""));
            tmpMessage = tmpMessage.substring(16);
        }
    }

    //wersja dla plikow tekstowych
    public static void divideToBlocksTxt() {
        setMap();
        String tmpMessage = message;
        System.out.println(tmpMessage.length());
        while (tmpMessage.length() > 63) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 64; i++) {
                sb.append(tmpMessage.charAt(i));
            }
            messageBlocks.add(sb.toString());
            tmpMessage = tmpMessage.substring(64);
        }
    }

    public static void permutationIP() {
        permutedMessageBlocks = new ArrayList();
        int ip[] = Tables.getIp();
        for (String block : messageBlocks) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ip.length; i++) {
                sb.append(block.charAt(ip[i] - 1));
            }
            permutedMessageBlocks.add(sb.toString());
            //System.out.println(block);
            //System.out.println(sb.toString());
        }
    }

    public static String permutationE(String messageRight) {
        StringBuilder sb = new StringBuilder();
        int e[] = Tables.geteTable();
        for (int i = 0; i < e.length; i++) {
            sb.append(messageRight.charAt(e[i] - 1));
        }
        return sb.toString();
    }

    private static void setMap() {
        hexMap.put("0", "0000");
        hexMap.put("1", "0001");
        hexMap.put("2", "0010");
        hexMap.put("3", "0011");
        hexMap.put("4", "0100");
        hexMap.put("5", "0101");
        hexMap.put("6", "0110");
        hexMap.put("7", "0111");
        hexMap.put("8", "1000");
        hexMap.put("9", "1001");
        hexMap.put("a", "1010");
        hexMap.put("b", "1011");
        hexMap.put("c", "1100");
        hexMap.put("d", "1101");
        hexMap.put("e", "1110");
        hexMap.put("f", "1111");
        hexMap.put("10", "1010");
        hexMap.put("11", "1011");
        hexMap.put("12", "1100");
        hexMap.put("13", "1101");
        hexMap.put("14", "1110");
        hexMap.put("15", "1111");

    }
}


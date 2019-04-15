package des;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Encoding {
    private static ArrayList<String> permutedMessageBlocks;
    private static HashMap<String, String> hexMap = new HashMap<>();

    public static String sixToFour(String afterXOR) {
        StringBuilder sb = new StringBuilder();
        ArrayList<int[][]> listSboxes = Tables.getSBoxes();
        for (int i = 0; i < listSboxes.size(); i++) {
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

    public static String coding(boolean encrypt) {
        String[] keys = KeyGenerator.getKeysCombined();
        StringBuilder result = new StringBuilder();
        int z = 0, helpCounter = 1;

        if (encrypt) {
            z = 15;
            helpCounter = -1;
        }

        for (int i = 0; i < permutedMessageBlocks.size(); i++) {
            String leftMessage = permutedMessageBlocks.get(i).substring(0, 32);
            String rightMessage = permutedMessageBlocks.get(i).substring(32, 64);
            int counter = z;
            for (int j = 0; j < 16; j++) {
                String tmpRightMessage = rightMessage;
                rightMessage = permutation(rightMessage,Tables.geteTable());
                rightMessage = XOR.calculate(keys[counter], rightMessage);
                counter += helpCounter;
                rightMessage = sixToFour(rightMessage);
                rightMessage = permutation(rightMessage,Tables.getpTable());

                rightMessage = XOR.calculate(leftMessage,rightMessage);
                leftMessage = tmpRightMessage;
            }

            rightMessage = permutation(rightMessage + leftMessage, Tables.getIpInverse());

            result.append(rightMessage);
        }

        return result.toString();
    }

    public static void saveEncoded(String result, String name) {
        try {
            OutputStream outputStream = new FileOutputStream(new File(name + ".bin"));
            List<Integer> bytes = divideString(result);
            for (int data : bytes) {
                outputStream.write(data);
            }

            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> divideString(String word) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < word.length(); i += 8) {
            list.add(Integer.parseInt(word.substring(i, Math.min(word.length(), i + 8)), 2));
        }

        return list;
    }

    //wersja dla plikow binarnych
    public static List<String> loadMessage(String name) {
        setMap();
        StringBuilder sb = new StringBuilder();
        try {
            InputStream inputStream = new FileInputStream(name + ".bin");

            int data = inputStream.read();

            while (data != -1) {
                if (data == 0) {
                    sb.append("00000000");
                } else {
                    String dataString = Integer.toString(data, 2);
                    while (dataString.length() < 8) {
                        dataString = "0" + dataString;
                    }
                    sb.append(dataString);
                }
                data = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return divideToBlocks(sb.toString());
    }

    //wersja dla plikow tekstowych
    public static List<String> divideToBlocks(String message) {
        List<String> messageBlocks = new ArrayList<>();
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

        return messageBlocks;
    }

    public static void permutationIP(List<String> messageBlocks) {
        permutedMessageBlocks = new ArrayList();
        int ip[] = Tables.getIp();
        for (String block : messageBlocks) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ip.length; i++) {
                sb.append(block.charAt(ip[i] - 1));
            }
            permutedMessageBlocks.add(sb.toString());
        }
    }

    public static String permutation(String messageRight, int table[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            sb.append(messageRight.charAt(table[i] - 1));
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


package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Use {

    private static String polynomial;
    private static List<Integer> seed;

    public static void setPolynomial(String polynomial) {
        Use.polynomial = polynomial;
    }

    public static void setSeed(List<Integer> seed) {
        Use.seed = seed;
    }

    public enum ACTIVITY {
        SSC,
        CACODING,
        CADECODING;
    }

    public static List<Integer> divideString(String word, int size) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < word.length(); i += size) {
            list.add(Integer.valueOf(word.substring(i, Math.min(word.length(), i + size))));
        }

        return list;
    }

    public static String joinString(List<Integer> word) {
        StringBuilder reply = new StringBuilder();
        for (int i : word) {
            reply.append(i);
        }
        return reply.toString();
    }


    public static void codeFile(File fileInput, String nameFileOutput, int activity) {

        try {
            InputStream inputStream = new FileInputStream(fileInput);

            int data = inputStream.read();

            OutputStream outputStream = new FileOutputStream(fileInput.getParentFile().getPath() + "\\" + nameFileOutput + ".bin");
            SSC ssc = new SSC();
            ssc.setSeed(seed);
            CA ca = new CA();
            ca.setSeed(seed);
            List<Integer> word;

            while (data != -1) {
                word = divideString(Integer.toString(data, 2), 1);
                while (word.size() < 8) {
                    word.add(0, 0);
                }

                if (activity == ACTIVITY.SSC.ordinal())
                    word = ssc.encryptAndDecipher(Polynomial.read(polynomial), word);
                else if (activity == ACTIVITY.CACODING.ordinal())
                    word = ca.encrypt(Polynomial.read(polynomial), word);
                else if (activity == ACTIVITY.CADECODING.ordinal())
                    word = ca.decipher(Polynomial.read(polynomial), word);

                int outputData = Integer.parseInt(joinString(word), 2);
                outputStream.write(outputData);
                data = inputStream.read();
            }

            inputStream.close();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(File file) {
        StringBuilder reply = new StringBuilder();

        try {
            InputStream inputStream = new FileInputStream(file);

            int data = inputStream.read();

            while (data != -1) {
                if (data == 0) {
                    reply.append("00 ");
                } else {
                    String dataString = Integer.toString(data, 16);
                    if (dataString.length() == 1) {
                        reply.append("\b" + "0" + dataString + " ");
                    } else {
                        reply.append("\b" + dataString + " ");
                    }
                }
                data = inputStream.read();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply.toString();
    }
}

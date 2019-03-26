package sample;

import com.google.inject.internal.util.Lists;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static String polynomial = "x^5+x^2+x+1";
    private static List<Integer> seed = Lists.newArrayList(0, 1, 1, 1, 0);

    public enum ACTIVITY {
        SSC,
        CACODING,
        CADECODING;
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Start SSC coding......");
        codeFile("test3", "test3_outputSSC", ACTIVITY.SSC.ordinal());
        System.out.println("End");
        System.out.println("Start SSC decoding......");
        codeFile("test3_outputSSC", "test3_decodingSSC", ACTIVITY.SSC.ordinal());
        System.out.println("End");
        System.out.println("Start CA coding......");
        codeFile("test3", "test3_outputCA", ACTIVITY.CACODING.ordinal());
        System.out.println("End");
        System.out.println("Start CA decoding......");
        codeFile("test3_outputCA", "test3_decodingCA", ACTIVITY.CADECODING.ordinal());
        System.out.println("End");
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


    public static void codeFile(String nameFile, String nameFileOutput, int activity) {

        try {
            InputStream inputStream = new FileInputStream(nameFile + ".bin");

            int data = inputStream.read();

            OutputStream outputStream = new FileOutputStream(nameFileOutput + ".bin");
            SSC ssc = new SSC();
            CA ca = new CA();
            List<Integer> word;

            while (data != -1) {
                word = divideString(Integer.toString(data, 2), 1);
                while (word.size() < 8) {
                    word.add(0, 0);
                }

                if (activity == ACTIVITY.SSC.ordinal())
                    word = ssc.encryptAndDecipher(Polynomial.read(polynomial), seed, word);
                else if (activity == ACTIVITY.CACODING.ordinal())
                    word = ca.encrypt(Polynomial.read(polynomial), seed, word);
                else if (activity == ACTIVITY.CADECODING.ordinal())
                    word = ca.decipher(Polynomial.read(polynomial), seed, word);

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
}

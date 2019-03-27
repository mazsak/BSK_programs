package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static String polynomial;
    private static List<Integer> seed;
    private static String nameFileInput;

    public enum ACTIVITY {
        SSC,
        CACODING,
        CADECODING;
    }

    public static void load() {
        File data = new File("data.txt");

        Scanner in = null;
        try {
            in = new Scanner(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        polynomial = in.nextLine();
        String seedString = in.nextLine();
        seed = Arrays.asList(seedString.split(", ")).stream().map(Integer::parseInt).collect(Collectors.toList());
        nameFileInput = in.nextLine();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Load data from file...");
        load();
        System.out.println("End loading");

        LFSR lfsr = new LFSR();
        System.out.println(lfsr.calculate(Polynomial.read(polynomial), seed));

        System.out.println("Start SSC coding......");
        codeFile(nameFileInput, nameFileInput + "_outputSSC", ACTIVITY.SSC.ordinal());
        System.out.println("End");
        System.out.println("Start SSC decoding......");
        codeFile(nameFileInput + "_outputSSC", nameFileInput + "_decodingSSC", ACTIVITY.SSC.ordinal());
        System.out.println("End");
        System.out.println("Start CA coding......");
        codeFile(nameFileInput, nameFileInput + "_outputCA", ACTIVITY.CACODING.ordinal());
        System.out.println("End");
        System.out.println("Start CA decoding......");
        codeFile(nameFileInput, nameFileInput + "_decodingCA", ACTIVITY.CADECODING.ordinal());
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
}

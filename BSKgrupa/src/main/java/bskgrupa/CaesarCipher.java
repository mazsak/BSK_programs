package bskgrupa;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CaesarCipher {

    public static String encryptA(String word, int key) {
        StringBuilder reply = new StringBuilder();
        List<Character> tab = new ArrayList<>();
        word = word.replaceAll("\\s", "");

        for (int i = 0; i < word.length(); i++) {
            tab.add(word.charAt(i));
        }

        for (char letter : tab) {
            if (letter >= 65 && letter <= 90)
                reply.append((char) ((((letter - 65) + key) % 26) + 65));
            else
                reply.append((char) ((((letter - 97) + key) % 26) + 97));
        }

        return reply.toString();
    }

    public static String decryptA(String word, int key) {
        StringBuilder reply = new StringBuilder();
        List<Character> tab = new ArrayList<>();
        word = word.replaceAll("\\s", "");

        for (int i = 0; i < word.length(); i++) {
            tab.add(word.charAt(i));
        }

        for (char letter : tab) {
            if (letter >= 65 && letter <= 90)
                reply.append((char) ((((letter - 65) + (26 - key)) % 26) + 65));
            else
                reply.append((char) ((((letter - 97) + (26 - key)) % 26) + 97));
        }

        return reply.toString();
    }

    public static String encryptB(String word, int keyA, int keyB) {
        StringBuilder reply = new StringBuilder();
        List<Character> tab = new ArrayList<>();
        word = word.replaceAll("\\s", "");

        for (int i = 0; i < word.length(); i++) {
            tab.add(word.charAt(i));
        }

        for (char letter : tab) {
            if (letter >= 65 && letter <= 90) {
                reply.append((char)((((BigInteger.valueOf((letter - 65)).multiply(BigInteger.valueOf(keyB)).add(BigInteger.valueOf(keyA))).mod(BigInteger.valueOf(26))).add(BigInteger.valueOf(65)))).intValue());
            }else {
                reply.append((char)((((BigInteger.valueOf((letter - 97)).multiply(BigInteger.valueOf(keyB)).add(BigInteger.valueOf(keyA))).mod(BigInteger.valueOf(26))).add(BigInteger.valueOf(97)))).intValue());
            }
        }

        return reply.toString();
    }

    public static String decryptB(String word, int keyA, int keyB) {
        StringBuilder reply = new StringBuilder();
        List<Character> tab = new ArrayList<>();

        word = word.replaceAll("\\s", "");

        for (int i = 0; i < word.length(); i++) {
            tab.add(word.charAt(i));
        }

        for (char letter : tab) {
            if (letter >= 65 && letter <= 90) {
                reply.append((char) ((((((BigInteger.valueOf(letter).subtract(BigInteger.valueOf(65)))).add((BigInteger.valueOf(26).subtract(BigInteger.valueOf(keyA))))).multiply(BigInteger.valueOf(keyB).pow(11))).mod(BigInteger.valueOf(26))).add(BigInteger.valueOf(65))).intValue());
            }else{
                reply.append((char) ((((((BigInteger.valueOf(letter).subtract(BigInteger.valueOf(97)))).add((BigInteger.valueOf(26).subtract(BigInteger.valueOf(keyA))))).multiply(BigInteger.valueOf(keyB).pow(11))).mod(BigInteger.valueOf(26))).add(BigInteger.valueOf(97))).intValue());
            }
        }
        return reply.toString();
    }

}

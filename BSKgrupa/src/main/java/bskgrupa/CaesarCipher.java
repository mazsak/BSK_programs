package bskgrupa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
                System.out.println( ((((letter - 65) * keyA + keyB) % 26) + 65));
                reply.append((char) ((((letter - 65) * keyA + keyB) % 26) + 65));
            }else {
                reply.append((char) ((((letter - 97) * keyA + keyB) % 26) + 97));
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
                reply.append((char) (((((letter - 65) + (26 - keyA)) * Math.pow(keyB, 11)) % 26) + 65));
            }else{
                reply.append((char) (((((letter - 97) + (26 - keyA)) * Math.pow(keyB, 20)) % 26) + 97));
            }
        }

        return reply.toString();
    }

}

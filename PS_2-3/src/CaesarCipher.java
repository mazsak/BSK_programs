import java.util.ArrayList;
import java.util.List;

public class CaesarCipher {

    public static String encryptA(String word, int key){
        StringBuilder reply = new StringBuilder();
        List<Character> tab = new ArrayList<>();

        for (int i = 0; i < word.length(); i ++) {
            tab.add(word.charAt(i));
        }

        for (char letter : tab){
            reply.append((char) ((((letter - 65) + key)%26) + 65));
        }

        return reply.toString();
    }

    public static String decryptA(String word, int key){
        StringBuilder reply = new StringBuilder();
        List<Character> tab = new ArrayList<>();

        for (int i = 0; i < word.length(); i ++) {
            tab.add(word.charAt(i));
        }

        for (char letter : tab){
            reply.append((char) ((((letter - 65) + (26 - key))%26) + 65));
        }

        return reply.toString();
    }

    public static String encryptB(String word, int keyA, int keyB){
        StringBuilder reply = new StringBuilder();
        List<Character> tab = new ArrayList<>();

        for (int i = 0; i < word.length(); i ++) {
            tab.add(word.charAt(i));
        }

        for (char letter : tab){
            reply.append((char) ((((letter - 65)*keyA + keyB)%21) + 65));
        }

        return reply.toString();
    }

}

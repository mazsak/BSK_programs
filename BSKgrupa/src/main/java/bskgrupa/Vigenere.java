package bskgrupa;

public class Vigenere {

    public String encrypt(String word, String key) {
        StringBuilder reply = new StringBuilder();

        word = word.replaceAll("\\s", "");
        word = word.toUpperCase();
        key = preparationKey(key, word.length());

        for (int i = 0; i < word.length(); i++) {
            reply.append((char) ((((word.charAt(i) - 65) + (key.charAt(i) - 65)) % 26) + 65));
        }

        return reply.toString();
    }

    public String decipher(String word, String key) {
        StringBuilder reply = new StringBuilder();

        word = word.toUpperCase();
        key = preparationKey(key, word.length());

        for (int i = 0; i < word.length(); i++) {
            reply.append((char) (((((word.charAt(i) - 65) - (key.charAt(i) - 65)) + 26) % 26) + 65));
        }

        return reply.toString();
    }

    public String preparationKey(String key, int size) {
        if (size > key.length()) {
            while (size != key.length()) {
                key += key;
                if (key.length() > size) {
                    key = key.substring(0, size);
                }
            }
        } else if (size < key.length()) {
            key = key.substring(0, size);
        }
        return key.toUpperCase();
    }

}

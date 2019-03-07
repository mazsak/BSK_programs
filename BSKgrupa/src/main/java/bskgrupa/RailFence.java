package bskgrupa;

public class RailFence {

    public String encrypt(String word, int level) {
        StringBuilder reply = new StringBuilder();
        char[][] tab = new char[level][word.length()];
        int currentLevel = 0, numberLetter = 0;
        boolean changeOfDirection = false;

        while (word.length() != numberLetter) {

            tab[currentLevel][numberLetter] = word.charAt(numberLetter);

            if (changeOfDirection) {
                currentLevel--;
            } else {
                currentLevel++;
            }

            if (currentLevel == 0) {
                changeOfDirection = false;
            } else if (currentLevel == level - 1) {
                changeOfDirection = true;
            }

            numberLetter++;
        }

        for (int i = 0; i < level; i++) {
            for (int z = 0; z < word.length(); z++) {
                if (tab[i][z] != 0) {
                    reply.append(tab[i][z]);
                }
            }
        }

        return reply.toString();
    }

    public String decipher(String word, int level) {
        StringBuilder reply = new StringBuilder();
        ;
        char[][] tab = new char[level][word.length()];
        boolean changeOfDirection = false;
        int j = 0;
        for (int i = 0; i < word.length(); i++) {
            if (j == 0 || j == level - 1)
                changeOfDirection = !changeOfDirection;

            tab[j][i] = '*';
            if (changeOfDirection) j++;
            else j--;

        }

        int index = 0;
        for (int i = 0; i < level; i++) {
            for (int k = 0; k < word.length(); k++) {
                if (tab[i][k] == '*' && index < word.length()) {
                    tab[i][k] = word.charAt(index++);
                }
            }


        }

        for (int k = 0; k < word.length(); k++) {
            for (int i = 0; i < level; i++) {
                if (tab[i][k] != 0)
                    reply.append(tab[i][k]);
            }
        }
        return reply.toString();
    }

}

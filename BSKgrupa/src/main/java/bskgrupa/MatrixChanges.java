package bskgrupa;

import java.util.*;
import java.util.stream.Collectors;

public class MatrixChanges {

    public String encryptA(String word, String key) {
        StringBuilder reply = new StringBuilder();
        List<Integer> orderOfLevel = preparationOrderOfLevel(key);
        int maxLevel = orderOfLevel.size();
        List<String> matrix;

        word = word.replaceAll("\\s", "");

        matrix = divideString(word, maxLevel);

        for (String part : matrix) {
            for (int i = 0; i < maxLevel; i++) {
                if (part.length() >= orderOfLevel.get(i)) {
                    reply.append(part.charAt(orderOfLevel.get(i) - 1));
                }
            }
        }

        return reply.toString();
    }

    public String decipherA(String word, String key) {
        StringBuilder reply = new StringBuilder();
        List<Integer> orderOfLevel = preparationOrderOfLevel(key);
        int maxLevel = orderOfLevel.size();
        List<String> matrix;
        char[] tab = new char[word.length()];

        matrix = divideString(word, 1);

        int i = 0, z = 0;
        while (i < matrix.size()) {
            for (int index : orderOfLevel) {
                if (word.length() > index + z - 1) {
                    tab[index + z - 1] = matrix.get(i).charAt(0);
                    i++;
                }
            }
            z += maxLevel;
        }

        reply.append(tab);

        return reply.toString();
    }

    public List<Integer> preparationOrderOfLevel(String key) {
        return Arrays.stream(key.split("-")).map(Integer::parseInt).collect(Collectors.toList());
    }

    public String encryptB(String word, String key) {
        StringBuilder reply = new StringBuilder();
        List<String> matrix;
        List<Integer> orderOfLevel;
        word = word.replaceAll("\\s", "");

        matrix = divideString(word, key.length());

        orderOfLevel = preparationOfOrder(word, key);

        return resultOfEncryption(matrix, orderOfLevel);
    }

    public String decipherB(String word, String key) {
        StringBuilder reply = new StringBuilder();
        List<Integer> orderOfLevel;

        orderOfLevel = preparationOfOrder(word, key);
        String[] matrix = new String[orderOfLevel.size()];

        int moduleLetter = word.length() % orderOfLevel.size();
        int sizeWord = (word.length() - moduleLetter) / orderOfLevel.size();

        int startIndex = 0;
        for (int i = 0; i < orderOfLevel.size(); i++) {
            if (orderOfLevel.indexOf(i) < moduleLetter) {
                matrix[orderOfLevel.indexOf(i)] = word.substring(startIndex, startIndex + sizeWord + 1);
                startIndex += 2;
            } else {
                matrix[orderOfLevel.indexOf(i)] = word.substring(startIndex, startIndex + sizeWord);
                startIndex++;
            }
        }

        for (int i = 0; i < matrix[0].length(); i++) {
            for (String wordHelp : matrix) {
                if (wordHelp.length() > i)
                    reply.append(wordHelp.charAt(i));
            }
        }

        return reply.toString();
    }

    public String encryptC(String word, String key) {
        List<String> matrix;
        List<Integer> orderOfLevel;

        word = word.replaceAll("\\s", "");
        orderOfLevel = preparationOfOrder(word, key);
        matrix = divideStringTermsOfLength(word, orderOfLevel);
        return resultOfEncryption(matrix, orderOfLevel);
    }


    public String decryptC(String word, String key) {
        StringBuilder reply = new StringBuilder();
        List<Integer> orderOfLevel;
        Map<Integer, Integer> mapSizeRow = new HashMap<>();
        word = word.replaceAll("\\s", "");

        orderOfLevel = preparationOfOrder(word, key);
        int loopS = (((1 + orderOfLevel.size()) * orderOfLevel.size()) / 2);
        int sizeWord = word.length();
        int level = 0;

        for (int i = 0; i < orderOfLevel.size(); i++) {
            mapSizeRow.put(i, orderOfLevel.indexOf(i));
            if (sizeWord > 0)
                level++;
            sizeWord -= (orderOfLevel.indexOf(i) + 1);
        }
        int tmp = 0;
        int maxLevel = 0;
        while (sizeWord > 0) {
            maxLevel++;
            if (tmp >= orderOfLevel.size())
                tmp = 0;
            sizeWord -= (mapSizeRow.get(tmp) + 1);
            tmp++;
        }
        char[][] tab = new char[orderOfLevel.size()][maxLevel];
        sizeWord = word.length();

        int index = 0;
        while (sizeWord > 0) {
            for (int i = 0; i < orderOfLevel.size(); i++) {
                for (int z = 0; z < level; z++) {
                    if (mapSizeRow.get(z) >= orderOfLevel.indexOf(i)) {
                        tab[i][z] = word.charAt(index);
                        index++;
                    }
                }
            }
            sizeWord -= loopS;
        }

        for (int i = 0; i < level; i++) {
            for (int z = 0; z < orderOfLevel.size(); z++) {
                if (tab[orderOfLevel.get(z)][i] != 0)
                    reply.append(tab[orderOfLevel.get(z)][i]);
            }
        }

        return reply.toString();

    }

    List<String> divideString(String word, int size) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < word.length(); i += size) {
            list.add(word.substring(i, Math.min(word.length(), i + size)));
        }

        return list;
    }

    List<String> divideStringDecipher(String word, int size, int moduleLetter) {
        List<String> list = new ArrayList<>();

        int i = 0;
        while (i < word.length()) {
            int sizeNow = size;
            if (moduleLetter != 0) {
                sizeNow++;
                moduleLetter--;
            }
            list.add(word.substring(i, Math.min(word.length(), i + sizeNow)));
            i += sizeNow;
        }

        return list;
    }

    List<String> divideStringTermsOfLength(String word, List<Integer> orderOfLevel) {
        List<String> list = new ArrayList<>();
        int indexStart = 0;
        for (int i = 0; indexStart < word.length(); i++) {
            if (i >= orderOfLevel.size())
                i = 0;
            int size = orderOfLevel.lastIndexOf(i);
            list.add(word.substring(indexStart, Math.min(word.length(), indexStart + size + 1)));
            indexStart += size + 1;
        }
        return list;
    }

    List<Integer> preparationOfOrder(String word, String key) {
        List<String> helpKey = divideString(key, 1);
        List<Integer> orderOfLevel = new ArrayList<>();
        Map<Integer, String> mapKey = new HashMap<>();
        Collections.sort(helpKey);

        for (int i = 0; i < helpKey.size(); i++) {
            mapKey.put(i, helpKey.get(i));
        }

        for (int i = 0; i < key.length(); i++) {
            for (Map.Entry<Integer, String> entry : mapKey.entrySet()) {
                if (entry.getValue().equals(String.valueOf(key.charAt(i)))) {
                    orderOfLevel.add(entry.getKey());
                    mapKey.remove(entry.getKey());
                    break;
                }
            }
        }
        return orderOfLevel;
    }

    String resultOfEncryption(List<String> matrix, List<Integer> orderOfLevel) {
        StringBuilder reply = new StringBuilder();
        int z = matrix.size() / orderOfLevel.size();
        if (matrix.size() % orderOfLevel.size() != 0)
            z++;

        for (int a = 0; a < z; a++) {
            for (int i = 0; i < orderOfLevel.size(); i++) {
                for (int j = 0 + (a * orderOfLevel.size()); j < matrix.size(); j++) {
                    if (j < orderOfLevel.size() * (a + 1))
                        if (matrix.get(j).length() > orderOfLevel.lastIndexOf(i)) {
                            reply.append(matrix.get(j).charAt(orderOfLevel.lastIndexOf(i)));
                        }
                }
            }
        }

        return reply.toString();
    }

}

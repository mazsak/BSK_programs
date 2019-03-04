import java.util.*;
import java.util.stream.Collectors;

public class MatrixChanges {

    public String encryptA(String word, String key) {
        StringBuilder reply = new StringBuilder();
        List<Integer> orderOfLevel = Arrays.stream(key.split("-")).map(Integer::parseInt).collect(Collectors.toList());
        List<String> matrix;
        int maxLevel = 0;
        try {
            maxLevel = orderOfLevel.stream().mapToInt(x -> x).max().orElseThrow(NoSuchFieldException::new);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

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

    public String encryptB(String word, String key) {
        StringBuilder reply = new StringBuilder();
        List<String> matrix;
        List<Integer> orderOfLevel;
        word = word.replaceAll("\\s", "");

        matrix = divideString(word, key.length());

        orderOfLevel = preparationOfOrder(word, key);

        return resultOfEncryption(matrix, orderOfLevel);
    }

    public String encryptC(String word, String key){
        List<String> matrix;
        List<Integer> orderOfLevel;

        word = word.replaceAll("\\s", "");
        orderOfLevel = preparationOfOrder(word, key);

        matrix = divideStringTermsOfLength(word, orderOfLevel);

        return resultOfEncryption(matrix, orderOfLevel);
    }

    List<String> divideString(String word, int size) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < word.length(); i += size) {
            list.add(word.substring(i, Math.min(word.length(), i + size)));
        }

        return list;
    }

    List<String> divideStringTermsOfLength(String word, List<Integer> orderOfLevel) {
        List<String> list = new ArrayList<>();
        int indexStart = 0;
        for (int i = 0; indexStart < word.length(); i++) {
            int size = orderOfLevel.lastIndexOf(i);
            list.add(word.substring(indexStart, Math.min(word.length(), indexStart + size + 1)));
            indexStart += size + 1;
        }

        return list;
    }

    List<Integer> preparationOfOrder(String word, String key){
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

    String resultOfEncryption(List<String> matrix, List<Integer> orderOfLevel){
        StringBuilder reply = new StringBuilder();

        for (int i = 0; i < orderOfLevel.size(); i++) {
            for (String part : matrix) {
                if (part.length() > orderOfLevel.lastIndexOf(i)) {
                    reply.append(part.charAt(orderOfLevel.lastIndexOf(i)));
                }
            }
        }

        return reply.toString();
    }

}

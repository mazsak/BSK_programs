package des;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class KeyGenerator {
    private static String key;
    private static String[] keysCombined;

    public static void loadKey() {
        StringBuilder sb = new StringBuilder();
        try (Reader reader = Files.newBufferedReader(Paths.get("key.txt"))) {
            int c;
            while ((c = reader.read()) != -1) {
                char ch = (char) c;
                int value = Character.getNumericValue(ch);
                if (value >= 0) {
                    sb.append(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        key = sb.toString();
    }

    public static void permutationPC1(){
        StringBuilder sb = new StringBuilder();
        int pc1[] = Tables.getPc1();
        for(int i = 0; i<pc1.length; i++){
            sb.append(key.charAt(pc1[i]-1));
        }
        key = sb.toString();
    }

    public static String permutationPC2(String keyPC){
        StringBuilder sb = new StringBuilder();
        int pc2[] = Tables.getPc2();
        for(int i = 0; i<pc2.length; i++){
            sb.append(keyPC.charAt(pc2[i]-1));
        }
        return sb.toString();
    }

    public static void generateKeys(){
        keysCombined = new String[16];
        String leftKey = key.substring(0,28);
        String rightKey = key.substring(28,56);String keyCombined;
        int[] leftShiftOrder = Tables.getLeftShiftOrder();
        for(int i = 0; i<16; i++){
            leftKey = leftKey.substring(leftShiftOrder[i],28) + leftKey.substring(0,leftShiftOrder[i]);
            rightKey = rightKey.substring(leftShiftOrder[i],28) + rightKey.substring(0,leftShiftOrder[i]);
            keyCombined = leftKey + rightKey;
            keyCombined = permutationPC2(keyCombined);
            keysCombined[i] = keyCombined;
        }
    }

    public static String getKey() {
        return key;
    }
}

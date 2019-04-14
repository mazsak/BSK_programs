package des;

public class XOR {

    public static String calculate(String key, String part) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<48; i++){
            if(key.charAt(i)==part.charAt(i)){
                sb.append(0);
            }else{
                sb.append(1);
            }
        }
        return sb.toString();
    }

}

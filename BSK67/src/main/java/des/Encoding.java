package des;

import java.io.*;

public class Encoding {
    private static String message;

    //tu wczytalo caly plik i zapisalo bez spacji
    public static void loadMessage() {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream inputStream = new FileInputStream("test.bin");

            int data = inputStream.read();

            while (data != -1) {
                if (data == 0) {
                    sb.append("00");
                } else {
                    String dataString = Integer.toString(data, 16);
                    if (dataString.length() == 1) {
                        sb.append("0" + dataString);
                    } else {
                        sb.append(dataString);
                    }
                }
                data = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        message = sb.toString();
    }

    //trzeba pozmieniac, zeby kodowalo wszystkie 64-bitowe bloki messaga
    public static void permutationIP(){
        StringBuilder sb = new StringBuilder();
        int ip[] = Tables.getIp();
        for(int i = 0; i<ip.length; i++){
            sb.append(message.charAt(ip[i]-1));
        }
        message = sb.toString();
    }

}


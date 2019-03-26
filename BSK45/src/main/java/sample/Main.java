package sample;

import com.google.inject.internal.util.Lists;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        LFSR cos = new LFSR();
        System.out.println(cos.calculate(Polynomial.read("x^4+x^2+1"), Lists.newArrayList(0, 0, 0, 1)));

        cipherSSC("test3.bin", "outputSSC.bin");
        cipherSSC("outputSSC.bin", "outputdecipherSSC.bin");

        cipherCA("test3.bin", "outputCA.bin");
        cipherCA("outputCA.bin", "outputdecipherCA.bin");
    }

    private static void cipherCA(String file1, String file2) throws FileNotFoundException, IOException {
        CA cos2 = new CA();

        InputStream inputstream = new FileInputStream(file1);
        int data = inputstream.read();
        OutputStream output = new FileOutputStream(file2);
        while (data != -1) {
            String dataAsBinary = String.format("%8s", Integer.toBinaryString(data)).replace(' ', '0');
            List input = new ArrayList();
            List cipher;
            if (dataAsBinary.equals("00000000")) {
                cipher = cos2.encrypt(Polynomial.read("x^5+x^2+x+1"), Lists.newArrayList(0, 1, 1, 1, 0), Lists.newArrayList(0, 0, 0, 0, 0, 0, 0, 0));
            } else {
                for (int i = 0; i < 8; i++) {
                    input.add(Character.getNumericValue(dataAsBinary.charAt(i)));
                }
                cipher = cos2.decipher(Polynomial.read("x^5+x^2+x+1"), Lists.newArrayList(0, 1, 1, 1, 0), input);
            }
            int wynik = 0;
            if ((int) cipher.get(0) == 1) {
                wynik = -128;
            }
            for (int i = 1; i < 8; i++) {
                if((int)cipher.get(i) == 1){
                    wynik += 128 / Math.pow(2, i);
                }
            }
            output.write(wynik);
            data = inputstream.read();
        }
        inputstream.close();
        output.close();
    }
    
    private static void cipherSSC(String file1, String file2) throws FileNotFoundException, IOException {
        SSC cos1 = new SSC();

        InputStream inputstream = new FileInputStream(file1);
        int data = inputstream.read();
        OutputStream output = new FileOutputStream(file2);
        while (data != -1) {
            String dataAsBinary = String.format("%8s", Integer.toBinaryString(data)).replace(' ', '0');
            List input = new ArrayList();
            List cipher;
            if (dataAsBinary.equals("00000000")) {
                cipher = cos1.encryptAndDecipher(Polynomial.read("x^4+x+1"), Lists.newArrayList(1, 1, 0, 0), Lists.newArrayList(0, 0, 0, 0, 0, 0, 0, 0));
            } else {
                for (int i = 0; i < 8; i++) {
                    input.add(Character.getNumericValue(dataAsBinary.charAt(i)));
                }
                cipher = cos1.encryptAndDecipher(Polynomial.read("x^4+x+1"), Lists.newArrayList(1, 1, 0, 0), input);
            }
            int wynik = 0;
            if ((int) cipher.get(0) == 1) {
                wynik = -128;
            }
            for (int i = 1; i < 8; i++) {
                if((int)cipher.get(i) == 1){
                    wynik += 128 / Math.pow(2, i);
                }
            }
            output.write(wynik);
            data = inputstream.read();
        }
        inputstream.close();
        output.close();
    }
}

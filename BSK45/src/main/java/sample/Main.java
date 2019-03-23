package sample;

import com.google.inject.internal.util.Lists;

public class Main {

    public static void main(String[] args) {
        LFSR cos = new LFSR();
        System.out.println(cos.calculate(Polynomial.read("x^4+x^2+1"), Lists.newArrayList(0, 0, 0, 1)));
        SSC cos1 = new SSC();
        System.out.println(cos1.encryptAndDecipher(Polynomial.read("x^5+x^4+x+1"), Lists.newArrayList(1, 1, 0, 0, 1), Lists.newArrayList(1, 0, 0, 1, 1, 0)));
        System.out.println(cos1.encryptAndDecipher(Polynomial.read("x^5+x^4+x+1"), Lists.newArrayList(1, 1, 0, 0, 1), Lists.newArrayList(1, 0, 1, 0, 1, 0)));
        CA cos2 = new CA();
        System.out.println(cos2.encrypt(Polynomial.read("x^4+x^2+1"), Lists.newArrayList(1, 0, 1, 1), Lists.newArrayList(1, 1, 0, 0, 1)));
        System.out.println(cos2.decipher(Polynomial.read("x^4+x^2+1"), Lists.newArrayList(1, 0, 1, 1), Lists.newArrayList(0, 1, 0, 0, 1)));

    }
}

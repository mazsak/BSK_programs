package bskgrupa;

public class Main {

    public static void main(String[] args) {
        RailFence rf = new RailFence();
        String wordToRF = "CRYPTOGRAPHY";
        int levelRF = 3;

        String replyRFEncrypt = rf.encrypt(wordToRF, levelRF);
        System.out.println(replyRFEncrypt);
        String replyRFDecipher = rf.decipher(replyRFEncrypt , levelRF);
        System.out.println(replyRFDecipher);


        MatrixChanges mc = new MatrixChanges();
        String wordToMCA = "CRYPTOGRAPHYOSA";
        String keyMCA = "3-1-4-2";
        String wordToMCBC = "HERE IS A SECRET MESSAGE ENCIPHERED BY TRANSPOSITION";
        String keyMCBC = "CONVENIENCE";

        String replyMCEncryptA = mc.encryptA(wordToMCA, keyMCA);
        System.out.println(replyMCEncryptA);
        String replyMCDecipherA = null;
        System.out.println(replyMCDecipherA);

        String replyMCEncryptB = mc.encryptB(wordToMCBC, keyMCBC);
        System.out.println(replyMCEncryptB);
        String replyMCDecipherB = mc.decipherB(replyMCEncryptB, keyMCBC);
        System.out.println(replyMCDecipherB);

        String replyMCEncryptC = mc.encryptC(wordToMCBC, keyMCBC);
        System.out.println(replyMCEncryptC);
        String replyMCDecipherC = null;
        System.out.println(replyMCDecipherC);


        CaesarCipher cc = new CaesarCipher();
        String wordToCC = "CRYPTOGRAPHY";
        int keyCC = 3;

        String replyCCEnctyptA = cc.encryptA(wordToCC, keyCC);
        System.out.println(replyCCEnctyptA);

    }
}

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
//        operation = "enc";
//        String textToEncrypt = "welcome to hyperskill!";
//        int key = 5;
//        output of encrypted text: |jqhtrj%yt%m~ujwxpnqq&

        Scanner sc = new Scanner(System.in);

        //operation can be: enc or dec
        String operation = sc.nextLine();
        String textToBeProcessed = sc.nextLine();
        int key = sc.nextInt();

        String result = encryptDecryptString(operation, textToBeProcessed, key);

        System.out.println(textToBeProcessed);
        System.out.println(result);
    }

    static String encryptDecryptString(String operation, String textToBeProcessed, int key) {
        //accepted alphabet characters:  !"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_`abcdefghijklmnopqrstuvwxyz{|}~
        String alphabet = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";

        //ex.: if key is 5 encoded alphabet looks like this: "%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_`abcdefghijklmnopqrstuvwxyz{|}~ !"#$"
        String encodedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

//        System.out.println(alphabet);
//        System.out.println(encodedAlphabet);

        StringBuilder result = new StringBuilder();

        if (operation.equals("enc")) {
            for (char c : textToBeProcessed.toCharArray()) {
                int idxOfCharInAlphabet = alphabet.indexOf(c);

                // add encodedChar to the result:
                result.append(encodedAlphabet.charAt(idxOfCharInAlphabet));
            }
        } else if (operation.equals("dec")) {
            for (char c : textToBeProcessed.toCharArray()) {
                int idxOfCharInEncodedAlphabet = encodedAlphabet.indexOf(c);

                // add decodedChar to the result:
                result.append(alphabet.charAt(idxOfCharInEncodedAlphabet));
            }

        } else {
            throw new IllegalArgumentException("Illegal operation type!");
        }
        return String.valueOf(result);
    }
}

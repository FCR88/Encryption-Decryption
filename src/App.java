import java.util.Scanner;

public class App {
    public static void main(String[] args) {
//        String textToEncrypt = "welcome to hyperskill";
//        int key = 5;
        //output of encrypted text: bjqhtrj yt mdujwxpnqq

        Scanner sc = new Scanner(System.in);

        String textToEncrypt = sc.nextLine();
        int key = sc.nextInt();

        String encryptedText = encryptString(textToEncrypt, key);

        System.out.println(textToEncrypt);
        System.out.println(encryptedText);
    }

    static String encryptString(String textToEncrypt, int key) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        // if key is 5 encoded alphabet looks like this: "fghijklmnopqrstuvwxyzabcde"
        String encodedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

        StringBuilder encryptedText = new StringBuilder();

        for (int idx = 0; idx < textToEncrypt.length(); idx++) {
            char ch = textToEncrypt.charAt(idx);
            char encodedChar;

            //we check for other characters besides those in alphabet and
            // if found add them directly as they are to the encoded string:
            if (alphabet.contains(String.valueOf(ch))) {
                int charAlphabetIdx = alphabet.indexOf(ch);
                encodedChar = encodedAlphabet.charAt(charAlphabetIdx);
            } else {
                encodedChar = ch;
            }
            encryptedText.append(encodedChar);
        }
        return String.valueOf(encryptedText);
    }
}

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EncDec {
    public static void main(String[] args) {
//        operation = "enc";
//        String textToEncrypt = "welcome to hyperskill!";
//        int key = 5;
//        output of encrypted text: |jqhtrj%yt%m~ujwxpnqq&

        //get parameters from scanner:
//        Scanner sc = new Scanner(System.in);
//
//        //operation can be: enc or dec
//        String operation = sc.nextLine();
//        String textToBeProcessed = sc.nextLine();
//        int key = sc.nextInt();



        //get parameters from cli:
//        List<String> arguments = Arrays.asList(args);

//        String operation = arguments.contains("-mode") ? arguments.get(arguments.indexOf("-mode") + 1) : "enc";
//        int key = arguments.contains("-key") ? Integer.parseInt(arguments.get(arguments.indexOf("-key") + 1)) : 0;
//        String textToBeProcessed = arguments.contains("-key") ? arguments.get(arguments.indexOf("-data") + 1) : "";

        //get parameters from cli without Arrays and List imports:

        String operation = "enc";
        int key = 0;
        String textToBeProcessed = "";

        String inFilePath = "";
        String outFilePath = "";

        //get parameters from cli:
        for (int i = 0; i < args.length; i++) {


            if ("-mode".equals(args[i])) {
                operation = args[i + 1];
            }
            if ("-key".equals(args[i])) {
                key = Integer.parseInt(args[i + 1]);
            }
            if ("-data".equals(args[i])) {
                textToBeProcessed = args[i + 1];
            }
            if ("-in".equals(args[i])) {
                inFilePath = args[i + 1];
            }
            if ("-out".equals(args[i])) {
                outFilePath = args[i+1];
            }

        }

        String result = encryptDecryptString(operation, textToBeProcessed, key, inFilePath);

//        System.out.println(textToBeProcessed);
//        System.out.println(result);
        processResult(result, outFilePath);
    }

    static String encryptDecryptString(String operation, String textToBeProcessed, int key, String inFilePath) {
        //possible characters:  !"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_`abcdefghijklmnopqrstuvwxyz{|}~
        String alphabet = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";

        //ex.: if key is 5 encoded alphabet looks like this: "%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_`abcdefghijklmnopqrstuvwxyz{|}~ !"#$"
        String encodedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

//        System.out.println(alphabet);
//        System.out.println(encodedAlphabet);

        if(textToBeProcessed.length()==0){
            try {
                textToBeProcessed = new String(Files.readAllBytes(Paths.get(inFilePath)));
            } catch (IOException e) {
//                e.printStackTrace();
                System.out.println("Error: input file " + inFilePath + " does not exists or can't be read!");
            }
        }

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
            throw new IllegalArgumentException("Error: Illegal operation type!");
        }
        return String.valueOf(result);
    }

    static void processResult(String result, String outFilePath){
        if(outFilePath.length() ==0) {
            System.out.println(result);
        }else {
            try(FileWriter writer = new FileWriter(new File(outFilePath), false)){
                writer.write(result);
            }catch (IOException e){
//                System.out.println(e.getMessage());
                System.out.println("Error: output file " + outFilePath + " could not be written");
            }
        }
    }
}

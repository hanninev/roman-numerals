package roomalaisetnumerot;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        RomanNumerals roman = new RomanNumerals();
        Scanner reader = new Scanner(System.in);
        
        System.out.println("Merkki 'q' lopettaa.");
        while (true) {
            System.out.print("Anna roomalainen numero: ");
            String romanNumber = reader.nextLine();
            if (romanNumber.equals("q")) {
                break;
            }
            System.out.println(roman.getNumber(romanNumber));
        }

    }
    
        
}

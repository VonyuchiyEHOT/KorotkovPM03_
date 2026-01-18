package calc;

import java.util.Scanner;

public class calc {

    public static void main(String[] args) {
        System.out.println("Vvedite operatsiyu: ");
        System.out.println("1. Slozhenie");
        System.out.println("2. Vychitanie");
        System.out.println("3. Umnozhenie");
        System.out.println("4. Delenie");

        Scanner scanner = new Scanner(System.in);
        int operation = scanner.nextInt();

        System.out.println("Vvedite pervoe chislo: ");
        int x = scanner.nextInt();
        System.out.println("Vvedite vtoroe chislo: ");
        int y = scanner.nextInt();

        int result = 0;

        if (operation == 1) {
            result = x + y;
        } else if (operation == 2) {
            result = x - y;
        } else if (operation == 3) {
            result = x * y;
        } else if (operation == 4) {
            result = x / y;
        }
        
        System.out.println("Rezultat = " + result);
        System.out.println("Kortkov");
    }
}
    
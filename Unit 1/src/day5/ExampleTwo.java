package day5;

import java.util.Scanner;

public class ExampleTwo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Please enter two integers: ");
        int num1 = in.nextInt();
        int num2 = in.nextInt();
        
        

        int sum = num1 + num2;
        System.out.println(num1 + " + " + num2 + " = " + sum);
        
    }
    
}

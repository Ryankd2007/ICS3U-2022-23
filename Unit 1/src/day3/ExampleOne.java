package day3;

public class ExampleOne {
    public static void main(String[] args) {
        int x = 17;

        System.out.println("Reminder: " + (x % 5));
        System.out.println("Division: " + (x / 5));


        int number = 85014;
        int digit1 = number / 10000; //5
        int digit2 = number / 1000 % 10;
        int digit3 = number / 100 % 10;
        int digit4 = number / 10 % 10; 
        int digit5 = number % 10; // 6

        int sum = digit1 + digit2 + digit3 + digit4 + digit5;
        System.out.println(sum);



    }
    
}

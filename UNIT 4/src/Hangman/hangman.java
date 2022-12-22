package Hangman;

import java.util.Scanner;

public class hangman {

   static Scanner in = new Scanner(System.in);
   static final String VALID_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
   private static final int MAX_BODY_PARTS = 7;

   /*
    * Add the following:
    * 1. The ability to choose a letter or guess the answer
    * 2. if they guess wrong add 1 to numWrong
    * 3. if they get it write say they win
    * 
    * 
    */

   public static void main(String[] args) {
      String phrase = getPhrase();
      String guessedLetters = "";
      int numWrong = 0;

      while (numWrong < MAX_BODY_PARTS) {
         displayDecodedMessage(phrase, guessedLetters);

         String letter = getLetter(guessedLetters);
         guessedLetters += letter;

         if (phrase.indexOf(letter) < 0) {
            numWrong++;
            drawHangman(numWrong);
         }
      }

   }

   private static String getLetter(String guessedLetters) {

      while (true) {
         System.out.print("Please enter a letter: ");
         String letter = in.nextLine().toUpperCase();

         if (VALID_LETTERS.indexOf(letter) < 0)
            System.out.println("Letters only please!");
         else if (guessedLetters.indexOf(letter) >= 0)
            System.out.println("You already used a " + letter);
         else
            return letter;
      }

   }

   private static void displayDecodedMessage(String phrase, String guessedLetters) {
      String result = "";
      for (int i = 0; i < phrase.length(); i++) {
         String letter = phrase.substring(i, i + 1);
         if (letter.equals(" "))
            result += "/ ";
         else if (guessedLetters.indexOf(letter) >= 0)
            result += letter + " ";
         else
            result += "_ ";

      }

      System.out.println(result);

   }

   private static String getPhrase() {
      boolean validInput = false;
      String phrase = "";
      while (!validInput) {
         System.out.println("Please enter a phrase (letters and spaces only)");
         phrase = in.nextLine().toUpperCase();
         validInput = true;
         for (int i = 0; i < phrase.length(); i++) {
            String letter = phrase.substring(i, i + 1);
            if ((VALID_LETTERS + " ").indexOf(letter) < 0)
               validInput = false;
         }
      }

      return phrase;

   }

   private static void drawHangman(int numBodyParts) {
      if (numBodyParts == 0) {
         System.out.println("__________");
         System.out.println("|        |");
         System.out.println("|");
         System.out.println("|");
         System.out.println("|");
         System.out.println("|");
         System.out.println("|");
         System.out.println("---------------");
      } else if (numBodyParts == 1) {
         System.out.println("__________");
         System.out.println("|        |");
         System.out.println("|        o");
         System.out.println("|");
         System.out.println("|");
         System.out.println("|");
         System.out.println("|");
         System.out.println("---------------");
      } else if (numBodyParts == 2) {
         System.out.println("__________");
         System.out.println("|        |");
         System.out.println("|        o");
         System.out.println("|        |");
         System.out.println("|");
         System.out.println("|");
         System.out.println("|");
         System.out.println("---------------");
      } else if (numBodyParts == 3) {
         System.out.println("__________");
         System.out.println("|        |");
         System.out.println("|        o");
         System.out.println("|       /|");
         System.out.println("|");
         System.out.println("|");
         System.out.println("|");
         System.out.println("---------------");
      } else if (numBodyParts == 4) {
         System.out.println("__________");
         System.out.println("|        |");
         System.out.println("|        o");
         System.out.println("|       /|\\");
         System.out.println("|");
         System.out.println("|");
         System.out.println("|");
         System.out.println("---------------");
      } else if (numBodyParts == 5) {
         System.out.println("__________");
         System.out.println("|        |");
         System.out.println("|        o");
         System.out.println("|       /|\\");
         System.out.println("|        |");
         System.out.println("|");
         System.out.println("|");
         System.out.println("---------------");
      } else if (numBodyParts == 6) {
         System.out.println("__________");
         System.out.println("|        |");
         System.out.println("|        o");
         System.out.println("|       /|\\");
         System.out.println("|        |");
         System.out.println("|      _/");
         System.out.println("|");
         System.out.println("---------------");
      } else if (numBodyParts == 7) {
         System.out.println("__________");
         System.out.println("|        |");
         System.out.println("|        o");
         System.out.println("|       /|\\");
         System.out.println("|        |");
         System.out.println("|      _/ \\_");
         System.out.println("|");
         System.out.println("---------------");
      }

   }
}
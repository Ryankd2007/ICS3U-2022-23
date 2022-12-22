package Assignment;

import java.util.Scanner;

public class GoFish {
    static Scanner in = new Scanner(System.in);
//setting all variables
    private static final int player = 1;
    private static final int computer1 = 2;
    private static final int computer2 = 3;
    private static final int computer3 = 4;
    static boolean gameEnd = false;
    static String playerHand = newHand();
    static String computerHand1 = newHand();
    static String computerHand2 = newHand();
    static String computerHand3 = newHand();
    static int playerScore = 0;
    static int computerScore1 = 0;
    static int computerScore2 = 0;
    static int computerScore3 = 0;

    //main method
    public static void main(String[] args) {
        gameEnd = false;
        //check to see if anybody's initial hand has pairs
        checkPairs(playerHand, player);
        checkPairs(computerHand1, computer1);
        checkPairs(computerHand2, computer2);
        checkPairs(computerHand3, computer3);

        while(gameEnd == false) {
            showScore();
            showHand();
            playerTurn();
            computerTurn(computer1);
            computerTurn(computer2);
            computerTurn(computer3);
        }
    }

    //deal 5 cards to each player
    private static String newHand() {
        return getCard() + getCard() + getCard() + getCard() + getCard();
    }
    //get a random card
    private static String getCard() {
        return getNumber() + getSuit();
    }
    //generate the number value for the random card
    private static String getNumber() {
        int number = (int) (Math.random() * 13 + 1);
        if(number == 1)
            return "A";
        else if(number == 10)
            return "X";
        else if(number == 11)
            return "J";
        else if(number == 12)
            return "Q";
        else if(number == 13)
            return "K";
        else return "" + number;
    }
    //generate the suit for the random card
    private static String getSuit() {
        int suit = (int) Math.round(Math.random() * 4 + 1);
        if (suit == 1)
            return "S";
        if (suit == 2)
            return "C";
        if (suit == 3)
            return "D";
        return "H"; 
    }
//players turn
    private static void playerTurn() {
            int user = choosePlayer(); //choose the target
         checkHand(user, player);
            dealCards();
        checkScore();
    }
//choose which player to take a card from
    private static int choosePlayer() {

        while(true){
        System.out.println("Choose a player to take a card from:");
        System.out.println("Computer 1 (1) Computer 2 (2) computer 3 (3) ");
            try {
                int user = Integer.parseInt(in.nextLine()); //using parseInt so that the input is an integer
            if(user > 3 || user < 1){
                System.out.println("Invalid input");
                choosePlayer();
            }
            else
                return user + 1;
        } catch(NumberFormatException e) {
            System.out.println("Invalid input");
        
    }
}
    }

    //computer's turn
    private static void computerTurn(int user) {
        if (gameEnd = false) {
            int playerChosen = 0;
            while(true) {
                playerChosen = (int) (Math.random()*4) + 1; // randomly choose the player that the computer wants to take a card from
                if(playerChosen != user); //if the random player chosen is not the chooser, break the loop, if not, repeat the loop
                    break;
            }
            checkHand(playerChosen, user); 
            dealCards();
            checkScore();
        }
    }
    //checks hand to see if the card that the player requests is available to be taken
    private static void checkHand(int playerChosen, int chooser) {
        String card = "";
        if(chooser == player) card = playerChooseCard(); // player picks the card they want
        else card = computerChooseCard(chooser);

        String hand = card + getSuit();
        if(playerChosen == player) hand += playerHand;
        if(playerChosen == computer1) hand += computerHand1;
        if(playerChosen == computer2) hand += computerHand2;
        if(playerChosen == computer3) hand += computerHand3;
        
        boolean hasRequested = false; //boolean that sees if the requested card is available
        for (int i = 0; i < hand.length() -1; i+=2) {

            String a = hand.charAt(i) + ""; //this variable shows number/face of every card in the hand 
            String request = hand.substring(hand.indexOf(a) + 2);
            if(request.contains(a)) {
                hasRequested = true; //the card that the player requested is in the target's hand
                hand = hand.substring(0, i) + request.substring(0, request.indexOf(a)) + request.substring(request.indexOf(a) + 2);
                break; 
            }
        }
        if(!hasRequested) //if that requested card waasn't in the target's hand
            hand = hand.substring(2);

        // if player is the target, the hand to be checked is the player's hand, if computer1 is the target their hand is checked, etc.
        if(playerChosen == player) {
            playerHand = hand;
        } if(playerChosen == computer1) {
            computerHand1 = hand;
        } if(playerChosen == computer2) {
            computerHand2 = hand;
        } if(playerChosen == computer3) {
            computerHand3 = hand;
        }

        if(hasRequested) { //if the card that the chooser requested is in the targets hand
            System.out.println("Target has " + card + "!");

            //if this person gets the card they wanted check to see if it made a pair
            if(chooser == player) checkPairs(playerHand + card + getSuit(), player);
            if(chooser == computer1) checkPairs(computerHand1 + card + getSuit(), computer1);
            if(chooser == computer2) checkPairs(computerHand2 + card + getSuit(), computer2);
            if(chooser == computer3) checkPairs(computerHand3 + card + getSuit(), computer3);
        } else { //if the target doesnt have the card
            System.out.println("Go fish!");
            //go fish! a random card is added to the chooser's hand from the deck, and then the new hand is checked for pairs
            if (chooser == player) {
                playerHand += getCard();
                checkPairs(playerHand, player);
            } if (chooser == computer1) {
                computerHand1 += getCard();
                checkPairs(computerHand1, computer1);
            } if (chooser == computer2) {
                computerHand2 += getCard();
                checkPairs(computerHand2, computer2);
            } if (chooser == computer3) {
                computerHand3 += getCard();
                checkPairs(computerHand3, computer3);
            }
        }
    }

//computer chooses which card to ask for
    private static String computerChooseCard(int user) {
        String hand = "";
        //since theres three computers, we need to check to see which one is choosing the card
        if(user == computer1)
            hand = computerHand1;
        if(user == computer2)
            hand = computerHand2;
        if(user == computer3)
            hand = computerHand3;

        int random = (int) (Math.random() * hand.length());
        if(random % 2 != 0)//if its odd, subtract one to make it even
            random--; 

        return hand.charAt(random) + ""; //return the character at this index
    }
//player chooses which card to ask for
    private static String playerChooseCard() {
        String request = "";
        for (int i = 0; i < playerHand.length() -1; i += 2) {
            if(i != 0)
                request +=", ";
            request += playerHand.charAt(i) + " (" + (i/2 + 1) + ")";
        }

        while(true) {
            System.out.println("Which card do you want to request? " + request + ": ");

            try {
                int card = Integer.parseInt(in.nextLine()); //parseInt again so that its an integer
                if(card < 1 || card > (playerHand.length() / 2))
                    System.out.println("Input invalid");
                else
                    return playerHand.charAt((card-1)*2) + "";
                } catch(NumberFormatException e) {
                    System.out.println("Please enter a valid option.");   
            }
        }
        }
    //card dealing method when somebody's hand has no cards left
    private static void dealCards() {
        if(playerHand.length() == 0 || computerHand1.length() == 0 || computerHand2.length() == 0 || computerHand3.length() == 0) { 
            //if any hand has no cards left, give them 5 cards and then see if this new hand has any pairs
            System.out.println("Drawing new cards...");
            if(playerHand.length() == 0) {
                playerHand = newHand();
                checkPairs(playerHand, player);
            } if (computerHand1.length() == 0) {
                computerHand1 = newHand();
                checkPairs(computerHand1, computer1);
            } if (computerHand2.length() == 0) {
                computerHand2 = newHand();
                checkPairs(computerHand2, computer2);
            } if (computerHand3.length() == 0) {
                computerHand3 = newHand();
                checkPairs(computerHand3, computer3);
            }
        }
    }
     //shows all scores 
     private static void showScore() {
        displayScore(player); displayScore(computer1); displayScore(computer2); displayScore(computer3);
    }
    //shows individual player scores
    private static void displayScore(int user) {
        if(user == player)
        System.out.println("Player Score: " + playerScore);
    else if(user == computer1)
        System.out.println("Computer 1 Score: " + computerScore1);
    else if(user == computer2)
        System.out.println("Computer 2 Score: " + computerScore2);
    else if(user == computer3)
        System.out.println("Computer 3 Score: " + computerScore3);
    }
    //shows all hands
    private static void showHand() {
        displayHand(player); displayHand(computer1); displayHand(computer2); displayHand(computer3);
    }
    //shows individual player hands
    private static void displayHand(int user) {
        if(user == player)
        System.out.println(playerHand);
    else if(user == computer1)
        System.out.println(computerHand1);
    else if(user == computer2)
        System.out.println(computerHand2);
    else if(user == computer3)
        System.out.println(computerHand3);
    }
    //hardest part of the assignment, if the hand of this user has pairs, remove them and add one point per pair removed
    private static void checkPairs(String hand, int user) {
        int count = 0; // create a count for the amount of points gained 
        for (int i = 0; i < hand.length()-1; i+=2) {
            String a = hand.charAt(i) + "";
            String request = hand.substring(hand.indexOf(a) + 2);
            if(request.contains(a)) {
                count++;
                hand = hand.substring(0, i) + request.substring(0, request.indexOf(a)) + request.substring(request.indexOf(a) + 2);
                System.out.println("Pair found!");
                i = -2; // resets loop
            }
        }
//sees which user gets the score
        if(user == player) {
            playerScore += count;
            playerHand = hand;
        } else if(user == computer1) {
            computerScore1 += count;
            computerHand1 = hand;
        } else if(user == computer2) {
            computerScore2 += count;
            computerHand2 = hand;
        } else if(user == computer3) {
            computerScore3 += count;
            computerHand3 = hand;
        }


    }
    //seeing if anybody has 10 points or more, if they do then winscreen triggers
    private static void checkScore() {
        if (playerScore >= 10){
            System.out.println("Player wins!");
            showScore();
            gameEnd = true;
        } else if(computerScore1 >= 10){
            System.out.println("Computer 1 wins!");
            showScore();
            gameEnd = true; 
        } else if(computerScore2 >= 10){
            System.out.println("Computer 2 wins!");
            showScore();
            gameEnd = true;
        }else if(computerScore3 >= 10){
            System.out.println("Computer 3 wins!");
            showScore();
            gameEnd = true;
    }
    }
}
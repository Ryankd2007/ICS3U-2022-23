public class DieGame {
    public static void main(String[] args) {
        Die die1 = new Die();
        Die die2 = new Die(); 

        int numPairs = 0;
        for(int i=0; i < 10; i++){
            die1.roll();
            die2.roll();

            //System.out.println("Die1: " + die1);
            //System.out.println("Die2: " + die2);

            if (die1.getTopSide() == die2.getTopSide())
                numPairs++;
        }

        System.out.println("Number of pairs: " + numPairs);
    }
}

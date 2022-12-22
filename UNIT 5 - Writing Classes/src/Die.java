public class Die {
    private int numSides;
    private int topSide;

     public int getTopSide() {
        return topSide;
    }

    public Die(int numSides) {
        this.numSides = numSides;
        this.roll();
    }

     public Die() {
    this.numSides = 6;
    roll();
    } 

    public void roll() {
        this.topSide = (int) (Math.random() * this.numSides) +1;
    }

    public String toString() {
        return "" + this.topSide;
    }

}

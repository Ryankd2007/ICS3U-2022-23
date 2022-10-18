package BayviewGlenPoolsAssignment;

import java.util.Scanner;

public class BayviewGlenPools {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Please enter the length of pool in meters: ");
        int poolLength = in.nextInt();

        System.out.println("Please enter the width of the pool in meters: ");
        int poolWidth = in.nextInt();

        System.out.println("Please enter the depth of the shallow end in meters: ");
        int depthOfShallowEnd = in.nextInt();

        System.out.println("Please enter the depth of the deep end in meters: ");
        int depthOfDeepEnd = in.nextInt();

        System.out.println("Please enter the length of the transition in meters: ");
        int transitionLength = in.nextInt();

        System.out.println("Please enter the length of shallow end in meters: ");
        int lengthOfShallowEnd = in.nextInt();

        System.out.println("Please enter the price of liner per m^2: ");
        int price = in.nextInt();
        
        //volume of pool
        double heightOfTransition = (double) depthOfDeepEnd-depthOfShallowEnd;
        double lengthOfTransition = Math.sqrt(Math.pow(transitionLength,2) - Math.pow(heightOfTransition, 2));
        double lengthOfDeepEnd = (double)poolLength - lengthOfTransition - lengthOfShallowEnd;

        double volume = (int)(lengthOfDeepEnd*depthOfDeepEnd*poolWidth) + (lengthOfShallowEnd*depthOfShallowEnd*poolWidth) 
        + (depthOfShallowEnd*poolWidth*lengthOfTransition) + (0.5*heightOfTransition*lengthOfTransition*poolWidth);

        double litres = (int)(volume*0.9);

        System.out.println( ("The amount of water you need to keep the pool at 90% is: " + (int)litres + "L"));

        //Surface area  
        double SurfaceArea = ((int)(depthOfDeepEnd*poolWidth) + (2*(lengthOfDeepEnd*depthOfDeepEnd)) + (lengthOfDeepEnd*poolWidth)) 
        + ((depthOfShallowEnd*poolWidth) + (2*(lengthOfShallowEnd*depthOfShallowEnd)) + (lengthOfShallowEnd*poolWidth)) + 
        ((transitionLength*poolWidth) + (2*(heightOfTransition*lengthOfTransition)/2) + (2*(lengthOfTransition*depthOfShallowEnd))); 

        double SurfaceAreaRounded = Math.round(SurfaceArea*100)/100;

        System.out.println("the amount of lining needed is: " + SurfaceAreaRounded + "m^2");

        //Cost of liner
        double liner = SurfaceAreaRounded*price;
        
        System.out.println("the price of lining is: " + liner + "$");

        in.close();

    }
}
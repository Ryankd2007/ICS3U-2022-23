package BayviewGlenPools;

import java.util.Scanner;

public class BayviewGlenPools {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        
        System.out.println("Please enter the length of pool in meters: ");
        double length = in.nextDouble();

        System.out.println("Please enter the length of the shallow end in meters: ");
        double shallowLength = in.nextDouble();

        System.out.println("Please enter the length of the transition in meters: ");
        double transition = in.nextDouble();

        System.out.println("Please enter the height of the shallow end in meters: ");
        double shallowHeight = in.nextDouble();

        System.out.println("Please enter the depth of the deep end in meters: ");
        double deepHeight = in.nextDouble();

        System.out.println("Please enter the width of the pool in meters: ");
        double width = in.nextDouble();

        System.out.println("Please enter the price of liner per m^2: ");
        double price = in.nextDouble();
        
        //volume of pool
        double lengthOfDeepEnd = (double)length - transition - shallowLength;
        double heightOfTransition = (double) deepHeight-shallowHeight;
        double lengthOfTransition = Math.sqrt(Math.pow(transition,2) - Math.pow(heightOfTransition, 2));
        

        double volume = (lengthOfDeepEnd*deepHeight*width) + (shallowLength*shallowHeight*width) 
        + (shallowHeight*width*lengthOfTransition) + (0.5*heightOfTransition*lengthOfTransition*width);
        //calculates the voluem of the pool

        double lNeeded = 1000*(volume*0.9);
        double lNeededRounded = Math.round(lNeeded*100)/100.0;
        //rounds the volume to 2 decimal points

        System.out.println("The amount of water you need to keep the pool at 90% is: " + lNeededRounded + "L");

        //surface area 
        double SurfaceArea = ((deepHeight*width) + (2*(lengthOfDeepEnd*deepHeight)) + (lengthOfDeepEnd*width)) 
        + ((shallowHeight*width) + (2*(shallowLength*shallowHeight)) + (shallowLength*width)) + 
        ((transition*width) + (2*(heightOfTransition*lengthOfTransition)/2) + (2*(lengthOfTransition*shallowHeight))); 

        //calculates surface area

        double SurfaceAreaRounded = Math.round(SurfaceArea*100)/100.0;
        //rounds the surface area to two decimal points

        System.out.println("the amount of lining needed is: " + SurfaceAreaRounded + "m^2");

        //Cost of liner
        double liner = SurfaceAreaRounded*price;
        //calculates the price of liner
        
        System.out.println("the price of lining is: " + liner + "$");

        in.close();

    }
}
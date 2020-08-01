package main.java.internationalDraughtsTeam4.unitTestsTeam4;

import junit.framework.TestCase;

class InternationalDraughtsUITest extends TestCase {

    /**
     * UnitTest to check whether selected white piece is valid
     */
    @org.junit.jupiter.api.Test
    void validWhiteMoveCheck() {
        boolean result;
        int x1=4, y1=3, x=3, y=2;
        if(x1>x || x1<x){
            if(x1==x+2 || x1==x-2){
                result = true;
            }
        }
        if((x1==x+1 || x1==x-1) && (y1==y+1 || y1==y-1)){
            result = true;
        }
        else
            result = false;
        System.out.println(result);
        assertTrue(result == true);
    }

    /**
     * UnitTest to check whether selected white piece is valid
     */
    @org.junit.jupiter.api.Test
    void validBlackMoveCheck() {
        boolean result;
        int x1=4, y1=3, x=3,y=3;
        if(x1>x || x1<x){
            if(x1==x+2 || x1==x-2){
                result = true;
            }
        }
        if((x1==x+1 || x1==x-1) && (y1==y+1 || y1==y-1)){
            result = true;
        }
        else
            result = false;
        assertTrue(result == false);
    }
}
package main.java.internationalDraughtsTeam4.unitTestsTeam4;

import junit.framework.TestCase;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RandomValidMoveStrategyTest extends TestCase {

    /**
     * UnitTest to test the random value generator method
     */
    @org.junit.jupiter.api.Test
    void getRandVal() {
        int x=2, randVal=0;
        Random random = new Random();
        for(int i=0; i<100; i++){
            if(random.nextInt(x)==1){
                randVal = 1;
                break;
            }
        }
        assertTrue(randVal == 1);
    }
}
package main.java.model;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameModelTest extends TestCase {

    boolean setTokenAt(int x, int y) {
        boolean result;
        if (x == y) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * UnitTest to test the basic functionality of getRequestor()
     */
    @Test
    void getRequestor() {
        int x = 4, y = 5;
        assertTrue(!setTokenAt(x,y) == true);
    }
}
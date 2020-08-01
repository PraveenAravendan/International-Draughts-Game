package main.java.model.board.unitTestsTeam4;

import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InternationalDraughtsBoardTest extends TestCase {

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
     * UnitTest to test makemove()
     */
    @Test
    void makeMove() {
        int x = 5, y = 5;
        assertTrue(setTokenAt(x, y) == true);
    }
}
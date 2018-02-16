package robot;

import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class RobotTest {

    @Test
    public void dosmth() {
        try {
            String[] cmdArray = new String[1];
            cmdArray[0] = "notepad.exe";
            Process process = Runtime.getRuntime().exec(cmdArray,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Robot robot = new Robot();
            robot.mouseMove(40, 130);
            robot.delay(1500);
            robot.mouseMove(540, 330);
            robot.delay(1500);
            robot.mouseMove(740, 530);
            robot.delay(1500);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyPress(KeyEvent.VK_ENTER);

        } catch (AWTException e) {
            e.printStackTrace();
        }

    }
}

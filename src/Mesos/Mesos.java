package Mesos;

import java.awt.Robot;
import java.awt.AWTException;
import javax.swing.JPanel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Mesos{
  public static final long TELE_DELAY = 333;
  private static long randoKishDelay;
  private static Robot controller;
  private static long lastTeleport;
  private static long lastKishin;
  private static int corralTele;
  private static int teleKey = KeyEvent.VK_CONTROL;
  private static int kishinKey = KeyEvent.VK_6;
  private static int scorralKey = KeyEvent.VK_F;

  public static void main(String[] args) {
    try {
      controller = new Robot();
    } catch (AWTException e) {
      System.out.println("You fucked up.");
    }
    lastTeleport = System.currentTimeMillis();
    lastKishin = System.currentTimeMillis();
    randoKishDelay = 30000;
    corralTele = 0;
    
    
    controller.delay(2000);
    controller.keyPress(kishinKey);
    controller.keyRelease(kishinKey);
    while (true) {
        if ((System.currentTimeMillis() - lastKishin) > randoKishDelay) {
          controller.delay(300);
          controller.keyPress(kishinKey);
          controller.keyRelease(kishinKey);
          controller.delay(100);
          controller.keyPress(kishinKey);
          controller.keyRelease(kishinKey);
          controller.delay(100);
          controller.keyPress(kishinKey);
          controller.keyRelease(kishinKey);
          controller.delay(100);
          controller.keyPress(kishinKey);
          controller.keyRelease(kishinKey);
          controller.delay(100);
          lastKishin = System.currentTimeMillis();
          randoKishDelay = (long) ((Math.random() * (30000)) + 30000);
        } 
        if ((System.currentTimeMillis() - lastTeleport) > TELE_DELAY) {
          if (corralTele > 15) {
            spiritCorral();
            corralTele = 0;
            continue;
          }
          controller.keyPress(teleKey);
          controller.keyRelease(teleKey);
          corralTele++;
          lastTeleport = System.currentTimeMillis();
        }
    } // end of while loop
    
  }
  
  public static void spiritCorral() {
    controller.keyPress(scorralKey);
    controller.keyPress(teleKey);
    controller.keyRelease(scorralKey);
    controller.keyRelease(teleKey);
  }
  
}
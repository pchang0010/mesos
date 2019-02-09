package Telefarm;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;

public class TelefarmGUI {
  
  private static final String VERSION_NUMBER = "2.3";
  private static final int SLEEP_DELAY = 1000;
  private static final int CLICK_DELAY = 100;
  private static final int START_DELAY = 3000;
  private static final int KISH_MIN_COUNTER = 90000;
  private static final int KISH_MAX_COUNTER = 120000;
  private static final int KISH_DELAY = 100;
  
  
  private static final int KISH_KEY = KeyEvent.VK_X;
  private static final int TP_KEY = KeyEvent.VK_C;
  private static final int SHIKI_KEY = KeyEvent.VK_S;
  
  private JFrame frame;
  private static boolean toggleOn = false;

  public static void main(String[] args) throws AWTException{
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          TelefarmGUI window = new TelefarmGUI();
          window.frame.setTitle("Kanna Telefarm v" + VERSION_NUMBER);
          window.frame.setIconImage(new ImageIcon(TelefarmGUI.class.getResource("/Telefarm/mesobag.png")).getImage());
          window.frame.addKeyListener(new KeyListener() {
            boolean pressed = false;
            @Override
            public void keyPressed(KeyEvent e) {
              if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                pressed = true;
              }
            }
            @Override
            public void keyTyped(KeyEvent e) {
              //Unused
            }
            @Override
            public void keyReleased(KeyEvent e) {
              if ((e.getKeyCode() == KeyEvent.VK_SPACE) && pressed) {
                toggleOn = false;
              }
            }
          });
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }    
    });
    
    Robot robot = new Robot();
    int kishCountdown = START_DELAY;
    while (true) {
      
      if (toggleOn) {
        if (kishCountdown <= 0) {
          pressKishin(robot);
          kishCountdown = randoKishCount();
        } else {
          pressShikiTP(robot);
//          pressShiki(robot);
//          try { Thread.sleep(CLICK_DELAY); } catch (Exception e) {e.printStackTrace(); return; } 
//          pressTP(robot);
//          try { Thread.sleep(CLICK_DELAY); } catch (Exception e) {e.printStackTrace(); return; }
          kishCountdown = kishCountdown - (CLICK_DELAY * 2);
        }
      }
      else {
        try { Thread.sleep(SLEEP_DELAY); } catch (Exception e) {e.printStackTrace(); return; } 
        kishCountdown = START_DELAY;
      }

    }
    
  }

  public TelefarmGUI() throws AWTException{
    initialize();
  }

  public void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 800, 513);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JPanel mainPanel = new JPanel();
    frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
    mainPanel.setLayout(null);
    
    JLabel mainLabel = new JLabel("Kanna Telefarm");
    mainLabel.setOpaque(true);
    mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
    mainLabel.setBackground(Color.RED);
    mainLabel.setForeground(Color.BLACK);
    mainLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 55));
    mainLabel.setBounds(0, 0, 782, 118);
    mainPanel.add(mainLabel);
    
    JButton toggleButton = new JButton("");
    toggleButton.setIcon(new ImageIcon(TelefarmGUI.class.getResource("/Telefarm/mesobag.png")));
    toggleButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
    toggleButton.setToolTipText("You can also press spacebar instead of this button!");
    toggleButton.setBounds(516, 173, 114, 109);
    mainPanel.add(toggleButton);
    
    JLabel reminderTitle = new JLabel("REMEMBER TO USE:");
    reminderTitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
    reminderTitle.setBounds(139, 151, 252, 34);
    mainPanel.add(reminderTitle);
    
    JLabel reminder0 = new JLabel("Blackhearted Curse");
    reminder0.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
    reminder0.setVerticalAlignment(SwingConstants.TOP);
    reminder0.setHorizontalAlignment(SwingConstants.CENTER);
    reminder0.setBounds(117, 189, 282, 24);
    mainPanel.add(reminder0);
    
    JLabel reminder1 = new JLabel("Eye of Time Familiar");
    reminder1.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
    reminder1.setHorizontalAlignment(SwingConstants.CENTER);
    reminder1.setVerticalAlignment(SwingConstants.TOP);
    reminder1.setBounds(117, 214, 282, 24);
    mainPanel.add(reminder1);
    
    JLabel reminder2 = new JLabel("Legion Wealth Coupons");
    reminder2.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
    reminder2.setVerticalAlignment(SwingConstants.TOP);
    reminder2.setHorizontalAlignment(SwingConstants.CENTER);
    reminder2.setBounds(109, 237, 282, 24);
    mainPanel.add(reminder2);
    
    JLabel reminder3 = new JLabel("Wealth Acquisition Potion");
    reminder3.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
    reminder3.setVerticalAlignment(SwingConstants.TOP);
    reminder3.setHorizontalAlignment(SwingConstants.CENTER);
    reminder3.setBounds(117, 262, 282, 24);
    mainPanel.add(reminder3);
    
    JLabel feature_title = new JLabel("What's new in version " + VERSION_NUMBER + ": ");
    feature_title.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
    feature_title.setBounds(59, 331, 658, 34);
    mainPanel.add(feature_title);
    
    JLabel feature1 = new JLabel("A flaw with the kishin delay was fixed; kishin should be pressed once every 90-120 seconds now");
    feature1.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
    feature1.setVerticalAlignment(SwingConstants.TOP);
    feature1.setHorizontalAlignment(SwingConstants.LEFT);
    feature1.setBounds(59, 369, 658, 24);
    mainPanel.add(feature1);
    
    JLabel feature2 = new JLabel("You can now press spacebar to toggle the farmer on and off");
    feature2.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
    feature2.setVerticalAlignment(SwingConstants.TOP);
    feature2.setHorizontalAlignment(SwingConstants.LEFT);
    feature2.setBounds(59, 394, 658, 24);
    mainPanel.add(feature2);
    
    JLabel feature3 = new JLabel("Faster animation cancel for keypresses");
    feature3.setVerticalAlignment(SwingConstants.TOP);
    feature3.setHorizontalAlignment(SwingConstants.LEFT);
    feature3.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
    feature3.setBounds(59, 419, 658, 24);
    mainPanel.add(feature3);
    
    toggleButton.addActionListener( new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == toggleButton) {
          Color color = mainLabel.getBackground();
          if (color == Color.RED) {
            mainLabel.setBackground(Color.GREEN);
            toggleOn = true;
          } else if (color == Color.GREEN) {
            mainLabel.setBackground(Color.RED);
            toggleOn = false;
          }
        }
      }
    });
  }
  
  private static void pressShikiTP(Robot robot) {
    robot.keyPress(TP_KEY);
    try { Thread.sleep(CLICK_DELAY); } catch (Exception e) {e.printStackTrace(); return; } 
    robot.keyPress(SHIKI_KEY);
    robot.keyRelease(SHIKI_KEY);
    try { Thread.sleep(CLICK_DELAY); } catch (Exception e) {e.printStackTrace(); return; } 
    robot.keyRelease(TP_KEY);
  }
  
  // Spam kish key 10 times in case of server lag
  private static void pressKishin(Robot robot) {
    int kishcount = 10;
    while (kishcount > 0) {
      kishcount--;
      robot.keyPress(KISH_KEY);
      robot.keyRelease(KISH_KEY);
      try { Thread.sleep(KISH_DELAY); } catch (Exception e) {e.printStackTrace(); return; } 
    }
  }
  
  private static void pressTP(Robot robot) {
    robot.keyPress(TP_KEY);
    robot.keyRelease(TP_KEY);
  }
  
  private static void pressShiki(Robot robot) {
    robot.keyPress(SHIKI_KEY);
    robot.keyRelease(SHIKI_KEY);
  }
  
  private static int randoKishCount() {
    Random r = new Random();
    return r.nextInt(KISH_MAX_COUNTER - KISH_MIN_COUNTER + 1) + KISH_MIN_COUNTER;
  }
}

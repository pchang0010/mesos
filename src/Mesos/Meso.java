package Mesos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Meso {

  private JFrame frmKishfarm;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Meso window = new Meso();
          window.frmKishfarm.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public Meso() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmKishfarm = new JFrame();
    frmKishfarm.setTitle("KishFarm");
    frmKishfarm.setBounds(100, 100, 450, 300);
    frmKishfarm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JTextArea txtrMakeMoarMesos = new JTextArea();
    txtrMakeMoarMesos.setText("MAKE MOAR MESOS");
    frmKishfarm.getContentPane().add(txtrMakeMoarMesos, BorderLayout.NORTH);
    
    JButton btnFarm = new JButton("==== FARM TIME ====");
    frmKishfarm.getContentPane().add(btnFarm, BorderLayout.WEST);
    
    JButton btnSuicideTime = new JButton("==== SUICIDE TIME ====");
    frmKishfarm.getContentPane().add(btnSuicideTime, BorderLayout.EAST);
  }

}

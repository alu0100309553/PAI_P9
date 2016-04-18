/**
 * Author: Rubén Labrador Páez.
 * Email: alu0100309553@ull.edu.es
 * Tit: Grado Ingeniería Informática - Universidad de La Laguna
 * Course: 3 - Computación
 * Subject: Programación de aplicaciones interactivas.
 * Practice: 9
 * Class/Program: Random Walk
 * File: GUI.java
 * Description: This is a program that reproduce the two dimension random walk.
 * @author Rubén Labrador Páez
 * @version 1.0.0 18/04/2016
 **/

package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GUI extends JFrame {
  protected JFrame frame = new JFrame();
  protected GraphPanel graphPanel;

  GUI() {
    graphPanel = new GraphPanel();
    frame.setLayout(new BorderLayout());
    frame.setTitle("Random Walk");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(graphPanel, BorderLayout.CENTER);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setSize(screenSize);
    frame.setVisible(true);
  }

  public JFrame getFrame() {
    return frame;
  }
  
  public GraphPanel getGraphPanel() {
    return graphPanel;
  }
}

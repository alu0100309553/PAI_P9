/**
 * Author: Rubén Labrador Páez.
 * Email: alu0100309553@ull.edu.es
 * Tit: Grado Ingeniería Informática - Universidad de La Laguna
 * Course: 3 - Computación
 * Subject: Programación de aplicaciones interactivas.
 * Practice: 9
 * Class/Program: Random Walk
 * File: ControlPanel.java
 * Description: This is a program that reproduce the two dimension random walk.
 * @author Rubén Labrador Páez
 * @version 1.0.0 18/04/2016
 **/

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import event.NewDirectionEventListener;
import event.NewDirectionEventObject;

public class ControlPanel extends GUI implements NewDirectionEventListener {
  private JButton start = new JButton("Start");
  private JButton stop = new JButton("Stop");
  private JButton color = new JButton("Color");
  private JButton reset = new JButton("Reset");
  private JSlider speed = new JSlider();
  private JSlider vDensity = new JSlider();
  private JSlider hDensity = new JSlider();
  private Timer timer = new Timer(100, new MyActionListener());
  private JTextField tVdensity = new JTextField();
  private JTextField tHdensity = new JTextField();
  private JTextField tSpeed = new JTextField();
  private Dimension controlPanelDim = new Dimension(200, getHeight());
  private Color[] colors = { Color.RED, Color.BLUE, Color.GREEN, Color.CYAN,
      Color.YELLOW, Color.MAGENTA, Color.ORANGE, Color.WHITE };
  private ArrayList<NewDirectionEventListener> listeners = new ArrayList<NewDirectionEventListener>();
  private int vLimitsControl;
  private int hLimitsControl;
  private boolean limitExceed = false;
  private boolean setColor = true;
  private Random colorR = new Random();
  private final Dimension JTFDIM = new Dimension(70, 20);
  private final Dimension BTDIM = new Dimension(80, 30);
  private final int LHBOFFSET = 40;
  private final int LVBOFFSET = 30;
  
  //Getters and setters
  private Timer getTimer() {
    return timer;
  }

  private ArrayList<NewDirectionEventListener> getListeners() {
    return listeners;
  }

  public void addNewDirectionEventListener(NewDirectionEventListener e) {
    getListeners().add(e);
  }

  private boolean isLimitExceed() {
    return limitExceed;
  }

  private void setLimitExceed(boolean value) {
    limitExceed = value;
  }

  private JSlider gethDensity() {
    return hDensity;
  }

  private JSlider getvDensity() {
    return vDensity;
  }

  private int getvLimitsControl() {
    return vLimitsControl;
  }

  private void setvLimitsControl(int value) {
    vLimitsControl = value;
  }

  private int gethLimitsControl() {
    return hLimitsControl;
  }

  private void sethLimitsControl(int value) {
    hLimitsControl = value;
  }

  private boolean isSetColor() {
    return setColor;
  }

  private void setSetColor(boolean value) {
    setColor = value;
  }

  private JButton getStart() {
    return start;
  }

  private JButton getStop() {
    return stop;
  }

  private JButton getColor() {
    return color;
  }

  private JButton getReset() {
    return reset;
  }

  private JSlider getSpeed() {
    return speed;
  }

  private JTextField gettSpeed() {
    return tSpeed;
  }

  private JTextField gettVdensity() {
    return tVdensity;
  }

  private JTextField gettHdensity() {
    return tHdensity;
  }

  private Color[] getColors() {
    return colors;
  }

  private Random getColorR() {
    return colorR;
  }

  public ControlPanel() {
    // Panel for controls
    JPanel opPanel = new JPanel();

    // Adding Listeners
    start.addActionListener(new MyActionListener());
    stop.addActionListener(new MyActionListener());
    reset.addActionListener(new MyActionListener());
    color.addActionListener(new MyActionListener());
    vDensity.addChangeListener(new SliderListener());
    tVdensity.addActionListener(new MyActionListener());
    hDensity.addChangeListener(new SliderListener());
    tHdensity.addActionListener(new MyActionListener());
    speed.addChangeListener(new SliderListener());
    tSpeed.addActionListener(new MyActionListener());

    // Setting dimensions
    opPanel.setPreferredSize(controlPanelDim);
    start.setPreferredSize(BTDIM);
    stop.setPreferredSize(BTDIM);
    reset.setPreferredSize(BTDIM);
    color.setPreferredSize(BTDIM);
    tVdensity.setPreferredSize(JTFDIM);
    tHdensity.setPreferredSize(JTFDIM);
    tSpeed.setPreferredSize(JTFDIM);

    // Setting sliders values and limits
    vDensity.setMinimum(2);
    vDensity.setValue(10);
    vDensity.setMaximum(1000);
    hDensity.setMinimum(2);
    hDensity.setValue(10);
    hDensity.setMaximum(1400);
    speed.setMinimum(1);
    speed.setMaximum(1000);
    speed.setValue(500);

    // Setting panel layout
    opPanel.setLayout(new FlowLayout(FlowLayout.CENTER, LHBOFFSET, LVBOFFSET));
    // opPanel.setLayout(new GridLayout(13,1,1,1));

    // Adding elements to panel
    opPanel.add(start);
    opPanel.add(stop);
    opPanel.add(reset);
    opPanel.add(color);
    opPanel.add(new JLabel("Vertical Cels n:"));
    opPanel.add(vDensity);
    opPanel.add(tVdensity);
    opPanel.add(new JLabel("Horizontal Cels n:"));
    opPanel.add(hDensity);
    opPanel.add(tHdensity);
    opPanel.add(new JLabel("Period (ms):"));
    opPanel.add(speed);
    opPanel.add(tSpeed);

    // Adding opPanel to frame
    getFrame().add(opPanel, BorderLayout.EAST);

    // Setting graphic panel default settings
    getGraphPanel().setGrid(gethDensity().getValue(), getvDensity().getValue());
    getGraphPanel().reset();
    getGraphPanel().updateUI();

    // Setting member variables
    setvLimitsControl(getvDensity().getValue() / 2);
    sethLimitsControl(gethDensity().getValue() / 2);
  }

  public void run() {
    if (!isLimitExceed()) {
      getTimer().start();
    }
  }

  public void stop() {
    getTimer().stop();
  }

  public void step(int direction) {
    int hCels = gethDensity().getValue();
    if (hCels % 2 != 0) {
      hCels -= 1;
    }
    int vCels = getvDensity().getValue();
    if (vCels % 2 != 0) {
      vCels -= 1;
    }
    switch (direction) {
    case 0:
      if (--vLimitsControl >= 0) {
        getGraphPanel().step(direction);
        if (getvLimitsControl() == 0) {
          stop();
          setLimitExceed(true);
        }
      } else {
        getGraphPanel().step(direction);
        stop();
        setLimitExceed(true);
      }
      break;
    case 1:
      if (++vLimitsControl <= vCels) {
        getGraphPanel().step(direction);
        if (getvLimitsControl() == vCels) {
          stop();
          setLimitExceed(true);
        }
      } else {
        stop();
        setLimitExceed(true);
      }
      break;
    case 2:
      if (--hLimitsControl >= 0) {
        getGraphPanel().step(direction);
        if (gethLimitsControl() == 0) {
          stop();
          setLimitExceed(true);
        }
      } else {
        stop();
        setLimitExceed(true);
      }
      break;
    case 3:
      if (++hLimitsControl <= hCels) {
        getGraphPanel().step(direction);
        if (gethLimitsControl() == hCels) {
          stop();
          setLimitExceed(true);
        }
      } else {
        stop();
        setLimitExceed(true);
      }
      break;
    }
  }

  private void resetAction() {
    stop();
    setSetColor(true);
    setLimitExceed(false);
    getGraphPanel().reset();
    setvLimitsControl(getvDensity().getValue() / 2);
    sethLimitsControl(gethDensity().getValue() / 2);
  }

  // Internal class for slider events
  protected class SliderListener implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
      if ((e.getSource() == gethDensity()) | (e.getSource() == getvDensity())) {
        resetAction();
        gettHdensity().setText("" + gethDensity().getValue());
        gettVdensity().setText("" + getvDensity().getValue());
        getGraphPanel().setGrid(gethDensity().getValue(),
            getvDensity().getValue());
      } else if (e.getSource() == getSpeed()) {
        getTimer().setDelay(getSpeed().getValue());
        gettSpeed().setText("" + getSpeed().getValue());
      } else {
      }
    }
  }

  // Internal class for buttons and jtextfields events
  protected class MyActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == getTimer()) {
        ListIterator<NewDirectionEventListener> li = getListeners()
            .listIterator();
        while (li.hasNext()) {
          NewDirectionEventListener listener = li.next();
          NewDirectionEventObject evObject = new NewDirectionEventObject(this);
          listener.NewDirection(evObject);
        }
      } else if (e.getSource() == getStart()) {
        setSetColor(false);
        run();
      } else if (e.getSource() == getStop()) {
        stop();
      } else if (e.getSource() == getReset()) {
        resetAction();
      } else if (e.getSource() == getColor()) {
        if (isSetColor()) {
          getGraphPanel().setColor(getColors()[getColorR().nextInt(7)]);
        }
      } else if (e.getSource() == gettVdensity()) {
        getvDensity().setValue(Integer.parseInt(gettVdensity().getText()));
      } else if (e.getSource() == gettHdensity()) {
        gethDensity().setValue(Integer.parseInt(gettHdensity().getText()));
      } else if (e.getSource() == gettSpeed()) {
        getSpeed().setValue(Integer.parseInt(gettSpeed().getText()));
      }
    }
  }

  // Event listener interface method
  @Override
  public void NewDirection(NewDirectionEventObject e) {
  }
}

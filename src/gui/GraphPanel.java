/**
 * Author: Rubén Labrador Páez.
 * Email: alu0100309553@ull.edu.es
 * Tit: Grado Ingeniería Informática - Universidad de La Laguna
 * Course: 3 - Computación
 * Subject: Programación de aplicaciones interactivas.
 * Practice: 9
 * Class/Program: Random Walk
 * File: GraphPanel.java
 * Description: This is a program that reproduce the two dimension random walk.
 * @author Rubén Labrador Páez
 * @version 1.0.0 18/04/2016
 **/

package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GraphPanel extends JPanel {
  private final int MIN_CELS = 10;
  private int xcoordinate;
  private int ycoordinate;
  private int stepsize;
  private int vCels = MIN_CELS;
  private int hCels = MIN_CELS;
  private int xOffset;
  private int yOffset;
  private Color lineColor = Color.RED;
  
  //Getters and setters
  private int getXcoordinate() {
    return xcoordinate;
  }

  private void setXcoordinate(int value) {
    xcoordinate = value;
  }

  private int getYcoordinate() {
    return ycoordinate;
  }

  private void setYcoordinate(int value) {
    ycoordinate = value;
  }

  private int getStepsize() {
    return stepsize;
  }

  private void setStepsize(int value) {
    stepsize = value;
  }

  private int getvCels() {
    return vCels;
  }

  private void setvCels(int value) {
    vCels = value;
  }

  private int gethCels() {
    return hCels;
  }

  private void sethCels(int value) {
    hCels = value;
  }

  private int getxOffset() {
    return xOffset;
  }

  private void setxOffset(int value) {
    xOffset = value;
  }

  private int getyOffset() {
    return yOffset;
  }

  private void setyOffset(int value) {
    yOffset = value;
  }

  private Color getLineColor() {
    return lineColor;
  }

  public void setColor(Color color) {
    lineColor = color;
  }

  GraphPanel() {
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, getWidth(), getHeight());
    g.setColor(Color.GRAY);
    init();
    draw(g);
  }

  public void step(int direction) {
    Graphics g = this.getGraphics();
    switch (direction) {
    case 0:
      north(g);
      break;
    case 1:
      south(g);
      break;
    case 2:
      west(g);
      break;
    case 3:
      east(g);
      break;
    }
  }

  public void setGrid(int h, int v) {
    sethCels(h);
    if (gethCels() % 2 != 0) {
      sethCels(gethCels() - 1);
    }
    setvCels(v);
    if (getvCels() % 2 != 0) {
      setvCels(getvCels() - 1);
    }
    reset();
    repaint();
  }

  public void init() {
    setXcoordinate(getWidth() / 2);
    setYcoordinate(getHeight() / 2);
    setStepsize(Math.min(getHeight() / getvCels(), getWidth() / gethCels()));
    setxOffset((getWidth() - (getStepsize() * gethCels())) / 2);
    setyOffset((getHeight() - (getStepsize() * getvCels())) / 2);
  }

  public void reset() {
    init();
    repaint();
  }

  private void north(Graphics g) {
    g.setColor(getLineColor());
    g.drawLine(getXcoordinate(), getYcoordinate(), getXcoordinate(),
        getYcoordinate() - getStepsize());
    g.drawLine(getXcoordinate() + 1, getYcoordinate(), getXcoordinate() + 1,
        getYcoordinate() - getStepsize());
    g.drawLine(getXcoordinate() - 1, getYcoordinate(), getXcoordinate() - 1,
        getYcoordinate() - getStepsize());
    setYcoordinate(getYcoordinate() - getStepsize());

  }

  private void south(Graphics g) {
    g.setColor(getLineColor());
    g.drawLine(getXcoordinate(), getYcoordinate(), getXcoordinate(),
        getYcoordinate() + getStepsize());
    g.drawLine(getXcoordinate() - 1, getYcoordinate(), getXcoordinate() - 1,
        getYcoordinate() + getStepsize());
    g.drawLine(getXcoordinate() + 1, getYcoordinate(), getXcoordinate() + 1,
        getYcoordinate() + getStepsize());
    setYcoordinate(getYcoordinate() + getStepsize());
  }

  private void west(Graphics g) {
    g.setColor(getLineColor());
    g.drawLine(getXcoordinate(), getYcoordinate(),
        getXcoordinate() - getStepsize(), getYcoordinate());
    g.drawLine(getXcoordinate(), getYcoordinate() - 1,
        getXcoordinate() - getStepsize(), getYcoordinate() - 1);
    g.drawLine(getXcoordinate(), getYcoordinate() + 1,
        getXcoordinate() - getStepsize(), getYcoordinate() + 1);
    setXcoordinate(getXcoordinate() - getStepsize());
  }

  private void east(Graphics g) {
    g.setColor(getLineColor());
    g.drawLine(getXcoordinate(), getYcoordinate(),
        getXcoordinate() + getStepsize(), getYcoordinate());
    g.drawLine(getXcoordinate(), getYcoordinate() - 1,
        getXcoordinate() + getStepsize(), getYcoordinate() - 1);
    g.drawLine(getXcoordinate(), getYcoordinate() + 1,
        getXcoordinate() + getStepsize(), getYcoordinate() + 1);
    setXcoordinate(getXcoordinate() + getStepsize());
  }

  private void draw(Graphics g) {
    for (int i = 0; i <= getvCels(); i++) {
      g.drawLine(getxOffset(), getyOffset() + (i * getStepsize()),
          this.getWidth() - getxOffset(), getyOffset() + (i * getStepsize()));
    }
    for (int i = 0; i <= gethCels(); i++) {
      g.drawLine(getxOffset() + (i * getStepsize()), getyOffset(),
          getxOffset() + (i * getStepsize()), this.getHeight() - getyOffset());
    }
  }
}

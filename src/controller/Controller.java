/**
 * Author: Rubén Labrador Páez.
 * Email: alu0100309553@ull.edu.es
 * Tit: Grado Ingeniería Informática - Universidad de La Laguna
 * Course: 3 - Computación
 * Subject: Programación de aplicaciones interactivas.
 * Practice: 9
 * Class/Program: Random Walk
 * File: Controller.java
 * Description: This is a program that reproduce the two dimension random walk.
 * @author Rubén Labrador Páez
 * @version 1.0.0 18/04/2016
 **/

package controller;

import event.NewDirectionEventListener;
import event.NewDirectionEventObject;
import gui.ControlPanel;
import model.Model;

public class Controller {
  public ControlPanel window = new ControlPanel();
  public Model model = new Model();

  private ControlPanel getWindow() {
    return window;
  }

  private Model getModel() {
    return model;
  }

  Controller() {
    getWindow().addNewDirectionEventListener(new MyEventHandler());
  }

  class MyEventHandler implements NewDirectionEventListener {
    @Override
    public void NewDirection(NewDirectionEventObject e) {
      getWindow().step(getModel().getValue());
      // TODO Auto-generated method stub

    }

  }

}

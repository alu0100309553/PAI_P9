package model;

import java.util.Random;

public class Model {
  Random directions;

  public Model() {
    directions = new Random();
  }

  public int getValue() {
    return directions.nextInt(4);
  }

}

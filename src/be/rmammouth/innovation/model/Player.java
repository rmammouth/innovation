package be.rmammouth.innovation.model;

import java.util.*;

public class Player
{
  private String name;
  private List<Achievement> achievements=new ArrayList<>();
  private List<Card> hand=new ArrayList<>();
  private EnumMap<Color, CardsPile> zone=new EnumMap<>(Color.class);
}

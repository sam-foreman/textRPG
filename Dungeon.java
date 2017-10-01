import java.util.*;

public class Dungeon {
//field(s)---------------------------------------------------------------------------------
  private Puzzle puzzle;
  private Room room1;
  private Room room2;
  private Room room3;
  private Room room4;
  private Room room5;
  private String name;
  private int roomsLeft;
  private boolean complete;
  private int bossKilled;
  private Boss boss;
//constructor(s)---------------------------------------------------------------------------
  Dungeon()
  {
    puzzle = new Puzzle();
    name = createName();
    room1 = new Room(puzzle);
    room2 = new Room(puzzle);
    room3 = new Room(puzzle);
    room4 = new Room(puzzle);
    room5 = new Room(puzzle);
    roomsLeft = 25;
  }
  Dungeon(int i)
  {
    puzzle = new Puzzle(6);
    name = createName();
    room1 = new Room(puzzle);
    room2 = new Room(puzzle);
    room3 = new Room(puzzle);
    room4 = new Room(puzzle);
    room5 = new Room(puzzle);
    roomsLeft = 25;
  }
  Dungeon(int i, String s)
  {
    puzzle = new Puzzle(6);
    name = s;
    room1 = new Room(puzzle);
    room2 = new Room(puzzle);
    room3 = new Room(puzzle);
    room4 = new Room(puzzle);
    room5 = new Room(puzzle);
    roomsLeft = 25;
  }
  Dungeon(String s)
  {
    puzzle = new Puzzle();
    name = s;
    room1 = new Room(puzzle);
    room2 = new Room(puzzle);
    room3 = new Room(puzzle);
    room4 = new Room(puzzle);
    room5 = new Room(puzzle);
    roomsLeft = 25;
  }
  
//getter(s)--------------------------------------------------------------------------------
  public Puzzle getPuzzle()
  {
    return puzzle;
  }
  public Room getRoom1()
  {
    return room1;
  }
  public Room getRoom2()
  {
    return room2;
  }
  public Room getRoom3()
  {
    return room3;
  }
  public Room getRoom4()
  {
    return room4;
  }
  public Room getRoom5()
  {
    return room5;
  }
  public String getName()
  {
    return name;
  }
  public int getRoomsLeft()
  {
    return roomsLeft;
  }
  public boolean getComplete()
  {
    return complete;
  }
  public int getBossKilled()
  {
    return bossKilled;
  }
  public Boss getBoss()
  {
    return boss;
  }
  //setter(s)------------------------------------------------------------------------------
  public void setPuzzle(Puzzle p)
  {
    puzzle = p;
  }
  public void setRoom1(Room r)
  {
    room1 = r;
  }
  public void setRoom2(Room r)
  {
    room2 = r;
  }
  public void setRoom3(Room r)
  {
    room3 = r;
  }
  public void setRoom4(Room r)
  {
    room4 = r;
  }
  public void setRoom5(Room r)
  {
    room5 = r;
  }
  public void setName(String s)
  {
    name = s;
  }
  public void setRoomsLeft(int i)
  {
    roomsLeft = i;
  }
  public void incrementRoomsLeft(int i)
  {
    roomsLeft+= i;
  }
  public void setComplete(boolean b)
  {
    complete = b;
  }
  public void setBossKilled(int b)
  {
    bossKilled = b;
  }
  public void incrementBossKilled(int i)
  {
    bossKilled += i;
  }
  public void setBoss(Player p)
  {
    boss = new Boss(p);
  }
  public void resetBoss()
  {
    boss.setHealthC(boss.getHealthT());
  }
//method(s)----------------------------------------------------------------------------------
  public String createName()
  {
    Random rand = new Random();
    String[] head = {"black", "dark", "death", "grey", "gray", "cross", "dar", "dam",
             "fire", "mire", "mor", "mar", "kin", "bar", "bale", "tin", "silver", "copper",
            "old", "edge", "ray", "gloom", "chill", "raven"};
    String[] tail = {"burn", "cave", "fell", "hold", "rock", "gate", "wall", "stale", "hole", "well",
             "cavern", "wood", "grotto", "hollow", "skull", "wallow", "gleam", "glow", "hill"};
    return (head[rand.nextInt(head.length-1)] + tail[rand.nextInt(tail.length-1)]);
  }
  public String toString()
  {
    return getName();
  }
  
}

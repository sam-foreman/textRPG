package textGameOne;
import java.util.*;
public class Room
{
//field(s)---------------------------------------------------------------------------------
  private ArrayList<String> contents;
  private int percentage;
  private String name;
  private boolean blockade;
  private boolean magic;
  private boolean shop;
  private Town roomShop;
  private boolean finish;
  
//constructor(s)-------------------------------------------------------------------------------
  Room()
  {
    percentage = 0;
    contents.add("monster");
    name = "test";
    blockade = false;
    magic = false;
    shop = false;
    finish = false;
  }
  Room(Puzzle puzzle)
  {
    percentage = 0;
    contents = createContents();
    name = createName();
    blockade = false;
    magic = false;
    shop = false;
    finish = false;
  }
  
//getter(s)----------------------------------------------------------------------------------
  public ArrayList<String> getContents()
  {
    return contents;
  }
  public int getPercentage()
  {
    calculatePercentage();
    return percentage;
  }
  public String getName()
  {
    return name;
  }
  public boolean getBlockade()
  {
    return blockade;
  }
  public boolean getMagic()
  {
    return magic;
  }
  public boolean getShop()
  {
    return shop;
  }
  public Town getRoomShop()
  {
    return roomShop;
  }
  public boolean getFinish()
  {
    return finish;
  }
  
//setter(s)----------------------------------------------------------------------------------
  public void setContents(ArrayList<String> s)
  {
    contents = s;
  }
  public void setPercentage(int p)
  {
    percentage = p;
  }
  public void setName(String s)
  {
    name = s;
  }
  public void setBlockade(boolean b)
  {
    blockade = b;
  }
  public void setMagic(boolean b)
  {
    magic = b;
  }
  public void setShop(boolean b)
  {
    shop = b;
  }
  public void setFinish(boolean b)
  {
    finish = b;
  }
  
  
//method(s)----------------------------------------------------------------------------------
  public ArrayList<String> createContents()
  {
    ArrayList<String> newContents = new ArrayList<String>();
    String[] first = {"blockade", "magic"};
    String[] next = {"monster", "strong", "health", "money", "exit", "shop", "number"};
    Random r = new Random();
    int n;
    //1
    if (r.nextInt(5) == 4)
      newContents.add(first[r.nextInt(2)]);
    else
    {
      if (r.nextInt(10) < 7)
        newContents.add(next[0]);
      else
      {
        n = r.nextInt(7);
        newContents.add(next[n]);
        if (n > 1)
          next[n] = "monster";
      }
    }
    
    for (int k = 0; k < 3; k++)
    {
      if (r.nextInt(10) < 7)
        newContents.add(next[0]);
      else
      {
        n = r.nextInt(7);
        newContents.add(next[n]);
        if (n > 1)
          next[n] = "monster";
      }
    }
    newContents.add("number");
    //System.out.println(newContents);
    return newContents;
  }
  public void calculatePercentage()
  {
    percentage = 100 - (contents.size() * 20);
  }
  public void createShop()
  {
    roomShop = new Town(false);
  }
  
  public String createName()
  {
    Random rand = new Random();
    String[] head = {"dark", "wet", "greasy", "black", "cavernous", "hallowed", "death", "whispering",
      "crying", "calloused", "shrieking", "frozen", "corrupted", "new", "old", "dusty",
      "ancient", "dreaded", "rogue", "blood", "bloody"};
    String[] tail = {"hall", "hall", "hallway", "room", "room", "room", "cavern", "hole", "cave", "clearing", "dungeon",
      "cell", "walkway", "path", "road", "field", "badland", "prison", "fortress", "encampment"};
    return (head[rand.nextInt(head.length-1)] + " " + tail[rand.nextInt(tail.length-1)]);
  }
}

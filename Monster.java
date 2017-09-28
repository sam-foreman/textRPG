package textGameOne;
import java.util.*;

public class Monster {
  //field(s)-------------------------------------------------------------
  private int healthC;
  private int healthT;
  private int attack;
  private int money;
  private int experience;
  private String name;
  
  //constructor(s)--------------------------------------------------------
  Monster(Player player)
  {
    int l = player.getLevel();
    int a = player.getAttack();
    Random r = new Random();
    name = createNameWeak();
    if (l == 1)
    {
      healthC = 4;
      healthT = 4;
      attack = 1;
      money = r.nextInt(6) + 2;
      experience = r.nextInt(5) + 3;
    }
    else
    {
      healthT = (l*3) + r.nextInt(2 + l) + 3;
      healthC = healthT;
      attack = (int)((a/4) + l - 4) + r.nextInt(2);
      if (attack < 1) attack = 1;
      money = l + (attack / l) + r.nextInt(8) + 5;
      experience = l + r.nextInt(6);
      if (l < 3)
      {
        healthT -= 3;
        healthC -= 3;
      }
    }
  }
  Monster(Player player, Boolean b)
  {
    int l = player.getLevel();
    int a = player.getAttack();
    Random r = new Random();
    name = createNameBoss();
    
    if (l == 1)
    {
      healthC = 4;
      healthT = 4;
      attack = 1;
      money = r.nextInt(8) + 1;
      experience = r.nextInt(5) + 3;
    }
    else
    {
      healthT = (l*3) + r.nextInt(6) + 2;
      healthC = healthT;
      attack = (int)((a/4) + l - 3) + r.nextInt(2);
      if (attack < 1) attack = 1;
      money = l + attack + r.nextInt(5) + 3;
      experience = l + r.nextInt(6) + 4;
      if (l < 3)
      {
        healthT -= 3;
        healthC -= 3;
      }
    }
  }
  
  //getter(s)--------------------------------------------------------
  public int getHealthC()
  {
    return healthC;
  }
  public int getHealthT()
  {
    return healthT;
  }
  public int getAttack()
  {
    return attack;
  }
  public int getMoney()
  {
    return money;
  }
  public int getExperience()
  {
    return experience;
  }
  public String getName()
  {
    return name;
  }
  
  //setter(s)--------------------------------------------------------
  public void setHealthC(int h)
  {
    healthC = h;
    if (healthC > healthT)
    {
      healthC = healthT;
    }
    
  }
  public void incrementHealthC(int h)
  {
    healthC += h;
    if (healthC > healthT)
    {
      healthC = healthT;
    }
  }
  public void setHealthT(int h)
  {
    healthT = h;
  }
  public void incrementHealthT(int h)
  {
    healthT += h;
  }
  public void setHealth(int h)
  {
    healthC = h;
    healthT = h;
  }
  public void incrementHealth(int h)
  {
    healthC += h;
    healthT += h;
  }
  public void setAttack(int a)
  {
    attack = a;
  }
  public void incrementAttack(int a)
  {
    attack += a;
  }
  public void setMoney(int m)
  {
    money = m;
  }
  public void incrementMoney(int m)
  {
    money += m;
  }
  public void setExperience(int e)
  {
    experience = e;
  }
  public void incrementExperience(int e)
  {
    experience += e;
  }
  public void setName(String s)
  {
    name = s;
  }
  
  //checker(s)--------------------------------------------------------
  public boolean checkHealth()
  {
    if (healthC <= 0)
    {
      return true;
    }
    return false;
  }
  public String createNameWeak()
  {
    Random rand = new Random();
    String[] head = {"slimy", "weak", "small", "lowly", "tired", "slow", "tiny",
      "dire", "gross", "lifeless", "feeble", "frail", "shakey", "sickly", "sluggish",
      "wobbly", "flaccid", "delicate", "puny", "rotten", "senile", "vile",
    "foul", "baneful", "heinous", "hideous", "corrupt", "bile"};
    String[] tail = {"monster", "imp", "beast", "horror", "villain", "brute",
    "fiend", "hellion", "miscreant", "mutant", "creeper", "degenerate", "drug addict", "demon",
    "devil", "fanatic", "manic", "addict", "corpse", "maggot"};
    return (head[rand.nextInt(head.length-1)] + " " + tail[rand.nextInt(tail.length-1)]);
  }
  public String createNameBoss()
  {
    Random rand = new Random();
    String[] head = {"Doran", "Otis", "Damon", "Alexander", "Samuel", "Prothero", "Rostam",
    "Michael", "Yanni", "Calchas", "Hermes", "Zeus", "Padme", "Lucas", "Cthulu", "Ziggy", "Stevenson", "Sullivan",
    "Rudolf", "George", "Lucifer", "Mephistopheles"};
    String[] tail = {"Powerful", "Mighty", "Reckless", "Courageous", "Potent", "Robust",
    "Capable", "Dominant", "Great", "Forceful", "Persuasive", "Influential", "Vigorous",
    "Able", "Competent", "Convincing", "Omnipotent", "Prevailing", "Sovereign"};
    return (head[rand.nextInt(head.length-1)] + " the " + tail[rand.nextInt(tail.length-1)]);
  }
}
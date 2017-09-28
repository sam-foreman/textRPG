package textGameOne;
import java.util.*;

public class Boss {
  //field(s)-------------------------------------------------------------
  private int healthC;
  private int healthT;
  private int attack;
  private int money;
  private int experience;
  private String name;
  
  private boolean charge;
  private boolean sap;
  private boolean stun;
  private boolean steal;
  //private boolean rage;
  
  private int chargeCooldown;
  private int sapCooldown;
  private int stunCooldown;
  private int stealCooldown;
  
  private int chargeUse;
  private int sapUse;
  private int stunUse;
  private int stealUse;
  //private int rageUse;
  
  private String[] movesList;
  
  //constructor(s)--------------------------------------------------------
  Boss(Player player)
  {
    int l = player.getLevel();
   // rage = true;
    int a = player.getAttack();
    Random r = new Random();
    name = createName();
    setMoves(l);
    healthT = 4;
    healthC = healthT;
    attack = 1;
    
    //-----------
    //-----------
    
    if (l == 1)
    {
      healthC = 8;
      healthT = 8;
      attack = 3;
      money = r.nextInt(6) + 2;
      experience = r.nextInt(5) + 3;
    }
    else
    {
      healthT = (l*5) + r.nextInt(4 + l) + 5;
      healthC = healthT;
      attack = (int)((a/3) + l - 2) + r.nextInt(3);
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
  public boolean getCharge()
  {
    return charge;
  }
  public boolean getStun()
  {
    return stun;
  }
  public boolean getSap()
  {
    return sap;
  }
  public boolean getSteal()
  {
    return steal;
  }
  public int getChargeUse()
  {
    return chargeUse;
  }
  public int getStunUse()
  {
    return stunUse;
  }
  public int getSapUse()
  {
    return sapUse;
  }
  public int getStealUse()
  {
    return stealUse;
  }
  public int getChargeCooldown()
  {
    return chargeCooldown;
  }
  public int getStunCooldown()
  {
    return stunCooldown;
  }
  public int getSapCooldown()
  {
    return sapCooldown;
  }
  public int getStealCooldown()
  {
    return stealCooldown;
  }
  public String[] getMovesList()
  {
    return movesList;
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
  public void setChargeUse(int b)
  {
    chargeUse = b;
  }
  public void setStunUse(int b)
  {
    stunUse = b;
  }
  public void setSapUse(int b)
  {
    sapUse = b;
  }
  public void setStealUse(int b)
  {
    stealUse = b;
  }
  public void setCharge(boolean b)
  {
    charge = b;
  }
  public void setStun(boolean b)
  {
    stun = b;
  }
  public void setSap(boolean b)
  {
    sap = b;
  }
  public void setSteal(boolean b)
  {
    steal = b;
  }
  public void setChargeCooldown(int i)
  {
    chargeCooldown = i;
  }
  public void setStunCooldown(int i)
  {
    stunCooldown = i;
  }
  public void setSapCooldown(int i)
  {
    sapCooldown = i;
  }
  public void setStealCooldown(int i)
  {
    stealCooldown = i;
  }
  public void incrementChargeCooldown(int i)
  {
    chargeCooldown +=i;
  }
  public void incrementStunCooldown(int i)
  {
    stunCooldown +=i;
  }
  public void incrementSapCooldown(int i)
  {
    sapCooldown +=i;
  }
  public void incrementStealCooldown(int i)
  {
    stealCooldown +=i;
  }
  public void incrementChargeUse(int i)
  {
    chargeUse +=i;
  }
  public void incrementStunUse(int i)
  {
    stunUse +=i;
  }
  public void incrementSapUse(int i)
  {
    sapUse +=i;
  }
  public void incrementStealUse(int i)
  {
    stealUse +=i;
  }
  public void setMovesList(String[] s)
  {
    movesList = s;
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
  
  public String createName()
  {
    Random rand = new Random();
    String[] head = {"Doran", "Otis", "Damon", "Alexander", "Samuel", "Prothero", "Rostam",
      "Michael", "Yanni", "Calchas", "Hermes", "Zeus", "Padme", "Lucas", "Cthulu", "Ziggy", "Stevenson", "Sullivan",
      "Rudolf"};
    String[] tail = {"Powerful", "Mighty", "Reckless", "Courageous", "Potent", "Robust",
      "Capable", "Dominant", "Great", "Forceful", "Persuasive", "Influential", "Vigorous",
      "Able", "Competent", "Convincing", "Omnipotent", "Prevailing", "Sovereign"};
    return (head[rand.nextInt(head.length-1)] + " the " + tail[rand.nextInt(tail.length-1)]);
  }
  
  public void setMoves(int l)
  {
    Random r = new Random();
    ArrayList<String> mL = new ArrayList<String>();
    mL.add("charge");
    mL.add("sap");
    mL.add("steal");
    mL.add("stun");
    if (l < 4)
    {
      for (int i = 0; i < l; i++)
      {
        int num = r.nextInt(mL.size());
        String move = mL.get(num);
        if (move.equals("charge"))
        {
          charge = true;
        }
        else if (move.equals("sap"))
        {
          sap = true;
        }
        else if (move.equals("steal"))
        {
          steal = true;
        }
        else
        {
          stun = true;
        }
        mL.remove(num);
        
      } 
    }
    else
    {
      charge = true;
      sap = true;
      steal = true;
      stun = true;
    }
  }
  //move(s)--------------------------------------------------------------------------
  
  
}

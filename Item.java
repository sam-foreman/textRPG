package textGameOne;
public class Item {
  
  //field(s)---------------------------------------------------------------------------------
  
//  private int healthUpgrade;
//  private int attackUpgrade;
//  private int guardUpgrade;
//  private int experienceUpgrade;
  private int healthPotion;
  private int attackPotion;
  private int experiencePotion;
  private int moneyPotion;
  private int townMap;
  private int dungeonMap;
  private boolean compass;
  private boolean binoculars;
  private boolean puzzle;
  private boolean charm;
  
  private boolean healthPotionUse;
  private boolean attackPotionUse;
  private boolean experiencePotionUse;
  private boolean moneyPotionUse;
  
  //constructor(s)-------------------------------------------------------------------------------
  
  Item()
  {
  }
  Item(boolean t)
  {
//    healthUpgrade = 5;
//    attackUpgrade = 5;
//    guardUpgrade = 5;
//    experienceUpgrade = 5;
    healthPotion = 5;
    attackPotion = 5;
    experiencePotion = 5;
    moneyPotion = 5;
    townMap = 5;
    dungeonMap = 5;
    compass = true;
    binoculars = true;
    puzzle = true;
    charm = true;
  }
  
  //getter(s)----------------------------------------------------------------------------------
  
//  public int getHealthUpgrade()
//  {
//    return healthUpgrade;
//  }
//  public int getAttackUpgrade()
//  {
//    return attackUpgrade;
//  }
//  public int getGuardUpgrade()
//  {
//    return guardUpgrade;
//  }
//  public int getExperienceUpgrade()
//  {
//    return experienceUpgrade;
//  }
  public int getHealthPotion()
  {
    return healthPotion;
  }
  public int getAttackPotion()
  {
    return attackPotion;
  }
  public int getExperiencePotion()
  {
    return experiencePotion;
  }
  public int getMoneyPotion()
  {
    return moneyPotion;
  }
  public int getTownMap()
  {
    return townMap;
  }
  public int getDungeonMap()
  {
    return dungeonMap;
  }
  public boolean getCompass()
  {
    return compass;
  }
  public boolean getBinoculars()
  {
    return binoculars;
  }
  public boolean getPuzzle()
  {
    return puzzle;
  }
  public boolean getCharm()
  {
    return charm;
  }
  public boolean getHealthPotionUse()
  {
    return healthPotionUse;
  }
  public boolean getAttackPotionUse()
  {
    return attackPotionUse;
  }
  public boolean getExperiencePotionUse()
  {
    return experiencePotionUse;
  }
  public boolean getMoneyPotionUse()
  {
    return moneyPotionUse;
  }
  //setter(s)----------------------------------------------------------------------------------
  
//  public void setHealthUpgrade(int h)
//  {
//    healthUpgrade = h;
//  }
//  public void incrementHealthUpgrade(int h)
//  {
//    healthUpgrade += h;
//  }
//  public void setGuardUpgrade(int g)
//  {
//    guardUpgrade = g;
//  }
//  public void incrementGuardUpgrade(int g)
//  {
//    guardUpgrade += g;
//  }
//  public void setExperienceUpgrade(int e)
//  {
//    experienceUpgrade = e;
//  }
//  public void incrementExperienceUpgrade(int e)
//  {
//    experienceUpgrade += e;
//  }
  public void setHealthPotion(int h)
  {
    healthPotion = h;
  }
  public void incrementHealthPotion(int h)
  {
    healthPotion += h;
  }
  public void setAttackPotion(int e)
  {
    attackPotion = e;
  }
  public void incrementAttackPotion(int e)
  {
    attackPotion += e;
  }
  public void setExperiencePotion(int e)
  {
    experiencePotion = e;
  }
  public void incrementExperiencePotion(int e)
  {
    experiencePotion += e;
  }
  public void setMoneyPotion(int m)
  {
    moneyPotion = m;
  }
  public void incrementMoneyPotion(int m)
  {
    moneyPotion += m;
  }
  public void setTownMap(int m)
  {
    townMap = m;
  }
  public void incrementTownMap(int m)
  {
    townMap += m;
  }
  public void setDungeonMap(int m)
  {
    dungeonMap = m;
  }
  public void incrementDungeonMap(int m)
  {
    dungeonMap += m;
  }
  public void setCompass(boolean m)
  {
    compass = m;
  }
  public void setBinoculars(boolean b)
  {
    binoculars = b;
  }
  public void setPuzzle(boolean p)
  {
    puzzle = p;
  }
  public void setCharm(boolean c)
  {
    charm = c;
  }
  public void setHealthPotionUse(boolean b)
  {
     healthPotionUse = b;
  }
  public void setAttackPotionUse(boolean b)
  {
     attackPotionUse = b;
  }
  public void setExperiencePotionUse(boolean b)
  {
     experiencePotionUse = b;
  }
  public void setMoneyPotionUse(boolean b)
  {
     moneyPotionUse = b;
  }
  //method(s)----------------------------------------------------------------------------------
    public boolean checkConsumable()
    {
      if (healthPotion > 0 || attackPotion > 0 || experiencePotion > 0 || moneyPotion > 0)
        return true;
      return false;
    }
    public boolean checkConsumableAndMap()
    {
      if (healthPotion > 0 || attackPotion > 0 || experiencePotion > 0 || moneyPotion > 0 || townMap > 0
            || dungeonMap > 0)
        return true;
      return false;
    }
    public boolean checkEquipable()
    {
      if (compass || binoculars || puzzle || charm)
        return true;
      return false;
    }
}
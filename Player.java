package textGameOne;
import java.util.*;
public class Player {
  //field(s)-------------------------------------------------------------
  private String name;
  private int healthC;
  private int healthT;
  private int level;
  private int attack;
  private int money;
  private int experience;
  private int guard;
  private boolean guardCheck;
  private ArrayList<Town> townList = new ArrayList<Town>();
  private ArrayList<Dungeon> dungeonList = new ArrayList<Dungeon>();
  private ArrayList<String> otherList = new ArrayList<String>();
  private Item item;
  private Move move;
  private int monstersKilled;
  private ArrayList<String> bossesKilled = new ArrayList<String>();
  private ArrayList<String> responses = new ArrayList<String>();
  private int moneySpent;
  private int bankMoney;
  private int death;
  private int altarCounter;
  private int potionCount;
  private boolean attackPotion;
  private int townLimit;
  private int movesUsed;
  private int upgrades;
  private int upgradesUsed;
  private int oldAttack;
  private boolean altarUpgrade;
  private boolean bankUpgrade;
  
  private int isSapped;
  private int isStunned;
  private boolean canSap;
  // private int guardAmount;
  
  //constructor(s)--------------------------------------------------------
  Player(String n)
  {
    name = n;
    healthC = 10;
    healthT = 10;
    level = 1;
    attack = 2;
    money = 0;
    experience = 0;
    guard = 2;
    guardCheck = false;
    townLimit = 2;
    //   guardAmount = 0;
    item = new Item();
    move = new Move();
    setAllResponses();
    for (int i = 0; i < 1; i++)
      townList.add(new Town(10));
  }
  Player(Boolean t)
  {
    name = "debug";
    healthC = 100;
    healthT = 100;
    level = 4;
    attack = 2;
    money = 500;
    experience = 50;
    guard = 2;
    townLimit = 10;
    upgrades = 5;
    guardCheck = false;
    for (int i = 0; i < 5; i ++)
      townList.add(new Town(10));
    for (int i = 0; i < 5; i++)
    {
      Dungeon d = new Dungeon(1);
      d.setBoss(this);
      dungeonList.add(d);
    }
    item = new Item();
    move = new Move("debug");
    item.setHealthPotion(1);
    item.setExperiencePotion(1);
    item.setMoneyPotion(5);
    item.setAttackPotion(1);
    item.setCompass(true);
    item.setCharm(true);
    item.setBinoculars(true);
    item.setPuzzle(true);
    item.incrementTownMap(1);
    item.incrementDungeonMap(1);
    otherList.add("altar");
    otherList.add("bank");
    setAllResponses();
  }
  
  Player (int i)
  {
    name = "test";
    healthC = 30;
    healthT = 30;
    level = 1;
    attack = 8;
    money = 100;
    experience = 0;
    guard = 2;
    townLimit = 5;
    guardCheck = false;
    //   guardAmount = 0;
    item = new Item();
    move = new Move();
    otherList.add("altar");
    otherList.add("bank");
    for (int j = 0; j < 2; j ++)
      townList.add(new Town(10));
    for (int k = 0; k < 1; k++)
    {
      Dungeon d = new Dungeon();
      d.setBoss(this);
      dungeonList.add(d);
    }
    setAllResponses();
  }
  //getters---------------------------------------------------------------
  public String getName()
  {
    return name;
  }
  public int getHealthC()
  {
    return healthC;
  }
  public int getHealthT()
  {
    return healthT;
  }
  public int getHealthDifference()
  {
    return healthT - healthC;
  }
  public int getLevel()
  {
    return level;
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
  public int getGuard()
  {
    return guard;
  }
  public boolean getGuardCheck()
  {
    return guardCheck;
  }
//  public int getGuardAmount()
//  {
//    return guardAmount;
//  }
  public Town getTown(int g)
  {
    return townList.get(g-1);
  }
  public int getTownListSize()
  {
    return townList.size();
  }
  public Item getItem()
  {
    return item;
  }
  public Move getMove()
  {
    return move;
  }
  public Dungeon getDungeon(int g)
  {
    return dungeonList.get(g-1);
  }
  public int getDungeonListSize()
  {
    return dungeonList.size();
  }
  public String getOther(int g)
  {
    return otherList.get(g-1);
  }
  public int getOtherListSize()
  {
    return otherList.size();
  }
  public int getMonstersKilled()
  {
    return monstersKilled;
  }
  public ArrayList<String> getBossesKilled()
  {
    return bossesKilled;
  }
  public int getMoneySpent()
  {
    return moneySpent;
  }
  public int getBankMoney()
  {
    return bankMoney;
  }
  public int getDeath()
  {
    return death;
  }  
  public int getAltarCounter()
  {
    return altarCounter;
  }
  public boolean getAttackPotion()
  {
    return attackPotion;
  }
  public int getPotionCount()
  {
    return potionCount;
  }
  public ArrayList<String> getOtherList()
  {
    return otherList;
  }
  public ArrayList<Town> getTownList()
  {
    return townList;
  }
  public ArrayList<Dungeon> getDungeonList()
  {
    return dungeonList;
  }
  public int getTownLimit()
  {
    return townLimit;
  }
  public int getMovesUsed()
  {
    return movesUsed;
  }
  public int getUpgrades()
  {
    return upgrades;
  }
  public int getUpgradesUsed()
  {
    return upgradesUsed;
  }
  public boolean getAltarUpgrade()
  {
    return altarUpgrade;
  }
  public boolean getBankUpgrade()
  {
    return bankUpgrade;
  }
  public int getIsSapped()
  {
    return isSapped;
  }
  public int getIsStunned()
  {
    return isStunned;
  }
  public boolean getCanSap()
  {
    return canSap;
  }

  //setters---------------------------------------------------------------
  public void setHealthC(int h)
  {
    healthC = h;
    if (healthC > healthT)
    {
      healthC = healthT;
    }
    if (checkHealth())
    {
      die();
    }
  }
  public void incrementHealthC(int h)
  {
    healthC += h;
    if (healthC > healthT)
    {
      healthC = healthT;
    }
    if (checkHealth())
    {
      die();
    }
  }
  public void setHealthT(int h)
  {
    healthT = h;
    if (checkHealth())
    {
      die();
    }
  }
  public void incrementHealthT(int h)
  {
    healthT += h;
    if (checkHealth())
    {
      die();
    }
  }
  public void setHealth(int h)
  {
    healthC = h;
    healthT = h;
    if (checkHealth())
    {
      die();
    }
  }
  public void incrementHealth(int h)
  {
    healthC += h;
    healthT += h;
  }
  public void setLevel(int l)
  {
    level = l;
  }
  public void incrementLevel(int l)
  {
    level += l;
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
    if (checkExperience()) levelUp();
  }
  public void setGuard(int g)
  {
    guard = g;
  }
  public void incrementGuard(int g)
  {
    guard += g;
  }
//  public void setGuardAmount(int g)
//  {
//    guardAmount = g;
//  }
//  public void incrementGuardAmount(int g)
//  {
//    guardAmount += g;
//  }
  public void setGuardCheck(Boolean b)
  {
    guardCheck = b;
  }
  public void setMonstersKilled(int i)
  {
    monstersKilled = i;
  }
  public void incrementMonstersKilled(int i)
  {
    monstersKilled += i;
  }
  public void setBossesKilled(ArrayList<String> s)
  {
    bossesKilled = s;
  }
  public void incrementBossesKilled(String s)
  {
    bossesKilled.add(s);
  }
  public void setMoneySpent(int i)
  {
    moneySpent = i;
  }
  public void incrementMoneySpent(int i)
  {
    moneySpent += i;
  }
  public void setBankMoney(int i)
  {
    bankMoney = i;
  }
  public void incrementBankMoney(int i)
  {
    bankMoney += i;
  }
  public void setDeath(int i)
  {
    death = i;
  }
  public void incrementDeath(int i)
  {
    death += i;
  }
  public void setAltarCounter(int i)
  {
    altarCounter = i;
  }
  public void incrementAltarCounter(int i)
  {
    altarCounter += i;
    if (altarCounter < 0)
      altarCounter = 0;
  }
  public void setAttackPotion(boolean b)
  {
    attackPotion = b;
  }
  public void setPotionCount(int i)
  {
    potionCount = i;
  }
  public void incrementPotionCount(int i)
  {
    potionCount += i;
  }
  public void setTownLimit(int i)
  {
    townLimit = i;
  }
  public void incrementTownLimit(int i)
  {
    townLimit += i;
  }
  public void setMovesUsed(int i)
  {
    movesUsed = i;
  }
  public void incrementMovesUsed(int i)
  {
    movesUsed += i;
  }
  public void setUpgrades(int i)
  {
    upgrades = i;
  }
  public void incrementUpgrades(int i)
  {
    upgrades += i;
  }
  public void setUpgradesUsed(int i)
  {
    upgradesUsed = i;
  }
  public void incrementUpgradesUsed(int i)
  {
    upgradesUsed += i;
  }
  public void setIsSapped(int i)
  {
    isSapped = i;
  }
  public void incrementIsSapped(int i)
  {
    isSapped += i;
  }
  public void setIsStunned(int i)
  {
    isStunned = i;
  }
  public void incrementIsStunned(int i)
  {
    isStunned += i;
  }
  public void setCanSap(boolean b)
  {
    canSap = b;
  }
  //checker(s)---------------------------------------------------------------
  public boolean checkMoney(int m)
  {
    if ((money += m) < 0)
    {
      return false;
    }
    return true;
  }
  public boolean checkExperience()
  {
    if (experience >= 100)
    {
      return true;
    }
    return false;
  }
  public boolean checkHealth()
  {
    if (healthC <= 0)
    {
      return true;
    }
    return false;
  }
  public boolean checkTownList()
  {
    if (townList.size() == 0)
    {
      return false;
    }
    return true;
  }
  public boolean checkDungeonList()
  {
    if (dungeonList.size() == 0)
    {
      return false;
    }
    return true;
  }
  public boolean checkOtherList()
  {
    if (otherList.size() == 0)
    {
      return false;
    }
    return true;
  }
    public void setAltarUpgrade(boolean b)
  {
    altarUpgrade = b;
  }
  public void setBankUpgrade(boolean b)
  {
    bankUpgrade = b;
  }
  //menu--------------------------------------------------------------------------
  public void stats()
  {
    System.out.println();
    System.out.println("name: " + name);
    System.out.println("level: " + level);
    System.out.printf("experience: %d/100\n", experience);
    System.out.printf("health: %d/%d\n", healthC, healthT);
    System.out.println("attack: " + attack);
    System.out.println("guard: " + guard);
    System.out.println("money: " + money);
    System.out.println("upgrades: " + upgrades);
    System.out.println();    
  }
  public void locations()
  {
    System.out.println();
    if (getTownListSize() == 0)
      System.out.println("you have not discovered any towns.");
    else
    {
      showTownList();
    }
    //check dungeons
  }
  
  //other method(s)--------------------------------------------------------------
  public void levelUp()
  {
    System.out.println("you have leveled up.");
    System.out.printf("you gain %d health.\n", 5 + level);
    System.out.println("you gain 1 attack.");
    System.out.println("you gain 1 upgrade.");
    upgrades++;
    experience -= 100;
    level++;
    incrementHealth(5 + level);
    attack++;
    if (level % 2 == 0)
    {
      guard++;
      System.out.println("you gain 1 guard.");
    }
    if (level % 3 == 0)
      townLimit++;
    if (checkExperience()) levelUp();
  }
  
  public void die()
  {
    System.out.println("you have died.");
    move.setChargeUse(0);
    move.setStunUse(0);
    move.setSapUse(0);
    move.setStealUse(0);

    death++;
    if (experience <= 50)
    {
      System.out.printf("you lose %d experience.\n", experience);
      experience = 0;
    }
    else
    {
      System.out.println("you lose 50 experience.");
      experience -= 50;
    }
    int m = (int) (money * .6);
    System.out.printf("you lose %d money.\n", m);
    money -= m;
    healthC = 10;
    System.out.println("returning to the world...");
    System.out.println();
    Game.start(this, new Scanner(System.in));
  }
  
  public void showTownList()
  {
    System.out.println("known towns:");
    for (int i = 0; i < townList.size(); i++)
    {
      System.out.printf("%d. %s \n", i+1, townList.get(i));
    }
  }
  public void showDungeonList()
  {
    System.out.println("known dungeons:");
    for (int i = 0; i < dungeonList.size(); i++)
    {
      System.out.printf("%d. %s \n", i+1, dungeonList.get(i));
    }
  }
  public void showOtherList()
  {
    System.out.println("known other locations:");
    for (int i = 0; i < otherList.size(); i++)
    {
      System.out.printf("%d. %s \n", i+1, otherList.get(i));
    }
  }
  //item method(s)---------------------------------------------------------------
  public void useHealthPotion()
  {
    if (item.getHealthPotion() == 0)
    {
      System.out.println("you are out of health potions.");
    }
    else if (getHealthDifference() == 0)
    {
      System.out.println("you are already at full health.");
    }
    else
    {
      incrementPotionCount(1);
      incrementHealthC(10);
      System.out.println("you restored some health.");
      item.incrementHealthPotion(-1);
    }
  }
  public void useExperiencePotion(Monster m)
  {
    if (item.getExperiencePotion() == 0)
    {
      System.out.println("you are out of experience potions.");
    }
    else
    {
      if (item.getExperiencePotionUse())
        System.out.println("you are already using an experience potion.");
      else
      {
        incrementPotionCount(1);
        item.setExperiencePotionUse(true);
        m.incrementExperience(m.getExperience()+ 10);
        System.out.println("this monster will give you more experience.");
        item.incrementExperiencePotion(-1);
      }
    }
  }
  public void useAttackPotion()
  {
    if (item.getAttackPotion() == 0)
      System.err.println("you are out of attack potions.");
    else
    {
      if (item.getAttackPotionUse())
        System.out.println("you are already using an attack potion.");
      else
      {
        oldAttack = attack;
        System.out.println("you have increased attack for this battle.");
        incrementPotionCount(1);
        attack += (int) (level + attack * .5);
        item.setAttackPotionUse(true);
        item.incrementAttackPotion(-1);
      }
    }
  }
  public void useMoneyPotion(Monster m)
  {
    if (item.getMoneyPotion() == 0)
    {
      System.out.println("you are out of money potions.");
    }
    else
    {
      if (item.getMoneyPotionUse())
        System.out.println("you are already using a money potion.");
      else
      {
        incrementPotionCount(1);
        m.incrementMoney(m.getMoney()+ 15);
        System.out.println("this monster will give you more money.");
        item.incrementMoneyPotion(-1);
        item.setMoneyPotionUse(true);
      }
    }
  }
  public void useTownMap()
  {
    if (item.getTownMap() == 0)
    {
      System.out.println("you are out of maps.");
    }
    else
    {
      item.incrementTownMap(-1);
      townList.add(new Town(healthT));
      System.out.println("you have discovered the town " + getTown(townList.size()));
    }
  }
  public void useDungeonMap()
  {
    if (item.getDungeonMap() == 0)
    {
      System.out.println("you are out of maps.");
    }
    else
    {
      Dungeon d;
      item.incrementDungeonMap(-1);
      if (getItem().getPuzzle())
        d = new Dungeon(4);
      else 
        d = new Dungeon();
      d.setBoss(this);
      dungeonList.add(d);
      System.out.println("you have discovered the dungeon " + getDungeon(dungeonList.size()));
    }
  }
  
  public void useItem(String item, Monster m)
  {
    if (item.equals("health potion"))
      useHealthPotion();
    else if (item.equals("experience potion"))
      useExperiencePotion(m);
    else if (item.equals("attack potion"))
      useAttackPotion();
    else if (item.equals("money potion"))
      useMoneyPotion(m);
  }
  
  //move method(s)---------------------------------------------------------------------------
  
  public void useCharge()
  {
    if (getMove().getCharge())
    {
      if (getMove().getChargeCooldown() == 0)
      {
        System.out.println("use charge.");
      }
      else
      {
        System.out.printf("charge is on cooldown for %d more turns.\n", getMove().getChargeCooldown());
      }
    }
    else
    {
      System.out.println("you don't have this move yet.");
    }
  }
  public void useSap()
  {
    if (getMove().getSap())
    {
      if (getMove().getSapCooldown() == 0)
      {
        System.out.println("use sap.");
      }
      else
      {
        System.out.printf("sap is on cooldown for %d more turns.\n", getMove().getSapCooldown());
      }
    }
    else
    {
      System.out.println("you don't have this move yet.");
    }
  }
  public void useSteal()
  {
    if (getMove().getSteal())
    {
      if (getMove().getStealCooldown() == 0)
      {
        System.out.println("use steal.");
      }
      else
      {
        System.out.printf("steal is on cooldown for %d more turns.\n", getMove().getStealCooldown());
      }
    }
    else
    {
      System.out.println("you don't have this move yet.");
    }
  }
  public void useStun()
  {
    if (getMove().getStun())
    {
      if (getMove().getStunCooldown() == 0)
      {
        System.out.println("use stun.");
      }
      else
      {
        System.out.printf("stun is on cooldown for %d more turns.\n", getMove().getStunCooldown());
      }
    }
    else
    {
      System.out.println("you don't have this move yet.");
    }
  }
  
  public void reset()
  {
    if (move.getChargeUse() == 1)
      incrementAttack(-5 - level);
    move.setChargeUse(0);
    move.setSapUse(0);
    move.setStealUse(0);
    move.setStunUse(0);
    item.setMoneyPotionUse(false);
    item.setExperiencePotionUse(false);
    if (item.getAttackPotionUse())
      attack = oldAttack;
    item.setAttackPotionUse(false);
  }
  
//responses--------------------------------------------------------------------------------------------
    public void setAllResponses()
  {                
    responses.add("with death comes resurrection, but at a price: you will lose 50 experience and \n40% of your money.");
    responses.add("rumour has it there's a mysterious bank somewhere in the world that will store \nyour money for you in case you die.");
    responses.add("rumour has it there's a mysterious altar somewhere in the world where you can \nsacrifice your stats for wealth.");
    responses.add("your total health determines how much health a town can heal at most.");
    responses.add("the dungeon boss will not get stronger as you level, but the normal dungeon \nmonsters will.");
    responses.add("new locations can be found by purchasing a map, or by exploring the world.");
    responses.add("the puzzle is solved when every box and line contain 1-4 without any repeats. \nit's basically sudoku. if you've never played sudoku, look it up.");
    responses.add("towns cannot heal multiple times in a row, you must explore the world and wait \nfor their health to replenish.");
    responses.add("exploring a dungeon will not reduce your town's heal cooldown. killing the \ndungeon boss, however, will.");
    responses.add("use guard to reduce incoming damage for the next turn.");
    responses.add("the charm item gives you a 20% discount at all shops.");
    responses.add("the puzzle item fills in more of the puzzle solution when a new dungeon is \ndiscovered.");
    responses.add("the compass item increases the chances of discovering a new location in the \nworld.");
    responses.add("the binoculars item reveals the enemy's health. that name doesn't really make \nsense but i don't give a fuck, i'm not a writer.");
    responses.add("items can be found randomly throughout the world or from townspeople like me. \nbut not exactly me. i'm not giving you shit.");
    responses.add("moves can be learned after defeating a dungeon boss.");
    responses.add("money stored in the bank will remain there even if you die.");
    responses.add("the stun move prevents the enemy from attacking for several turns.");
    responses.add("the steal move drains health from the enemy, and replenishes your own health.");
    responses.add("the sap move will drain health from the enemy over several turns.");
    responses.add("the charge move lets you store energy for a turn, then unleash it for massive \namounts of damage.");
    responses.add("potions can only be used during battle.");
    responses.add("you can use your maps to discover new locations in the 'items' section within \nthe menu.");
    responses.add("make your name #debug to start the game with all the moves/items and some free \nlocations. you didn't hear it from me...");
    responses.add("you earn upgrades from leveling up, or fully exploring a dungeon.");
    responses.add("upgrades can be used to restock a town's shop inventory, increase a town's \nhealing capacity, or repopulate a dungeon with stronger monsters.");
    responses.add("you can upgrade a town's shop once you've bought all the items in that town.");
    responses.add("you can repopulate a dungeon with new rooms once you've defeated the boss.");
    responses.add("you can only use a health potion when fighting a boss.");
    responses.add("you can use moves against a boss, however they will be less effective.");
  }
    public String getResponse(int i)
    {
      String r = responses.get(i);
      responses.remove(i);
      return r;
    }
    public int getResponseSize()
    {
      return responses.size();
    }
      
}


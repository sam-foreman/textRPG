import java.util.*;
public class Town
{
  //field(s)-------------------------------------------------------------
  private String name;
  private int healAmount;
  private int healCooldown;
  private int healPrice;
  private String[] shopItems;
  private boolean realTown;
  private int shopItem1Price;
  private int shopItem1Amount;
  private int shopItem2Price;
  private String response1;
  private String response2;
  
  private boolean showHealAmount;
  //private boolean showHealPrice;
  private int showShopItem1;
  private int showShopItem2;
  //0: not seen, 1: seen, 2: bought
  
  //constructor(s)-------------------------------------------------------- 
  
  Town(String n, int h)
  {
    name = n;
    healAmount = h;
    healCooldown = 0;
    healPrice = 1;
    shopItems = createShopItems();
    setShopItemPrices();
    realTown = true;
    response1 = "not-set";
    response2 = "not-set";
  }
  Town(int h)
  {
    name = createName();
    healAmount = h;
    healCooldown = 0;
    healPrice = 1;
    shopItems = createShopItems();
    setShopItemPrices();
    realTown = true;
    response1 = "not-set";
    response2 = "not-set";
  }
  Town (boolean b)
  {
    name = "dungeon shop";
    healAmount = 0;
    healCooldown = 0;
    healPrice = 1;
    shopItems = createShopItems();
    setShopItemPrices();
    realTown = false;
    response1 = "not-set";
    response2 = "not-set";
  }
  
  
  //getter(s)--------------------------------------------------------------
  public String getName()
  {
    return name;
  }
  public int getHealAmount()
  {
    return healAmount;
  }
  public int getHealCooldown()
  {
    return healCooldown;
  }
  public int getHealPrice()
  {
    return healPrice;
  }
  public String[] getShopItems()
  {
    return shopItems;
  }
  public String getShopItem1()
  {
    return shopItems[0];
  }
  public String getShopItem2()
  {
    return shopItems[1];
  }
  public boolean getRealTown()
  {
    return realTown;
  }
  public int getShopItem1Price()
  {
    return shopItem1Price;
  }
  public int getShopItem2Price()
  {
    return shopItem2Price;
  }
  public int getShopItem1Amount()
  {
    return shopItem1Amount;
  }
  public int getShowShopItem1()
  {
    return showShopItem1;
  }
  public int getShowShopItem2()
  {
    return showShopItem2;
  }
  public boolean getShowHealAmount()
  {
    return showHealAmount;
  }
  public String getResponse1()
  {
    return response1;
  }
  public String getResponse2()
  {
    return response2;
  }
  
  //setter(s)---------------------------------------------------------------
  public void setHealAmount(int h)
  {
    healAmount = h;
  }
  public void incrementHealAmount(int h)
  {
    healAmount += h;
    if (healAmount < 1)
    {
      healAmount = 1;
    }
  }
  public void setHealCooldown(int h)
  {
    healCooldown = h;
  }
  public void incrementHealCooldown(int h)
  {
    healCooldown += h;
    if (healCooldown < 0)
    {
      healCooldown = 0;
    }
  }
  public void setHealPrice(int h)
  {
    healPrice = h;
  }
  public void incrementHealPrice(int h)
  {
    healPrice += h;
  }
  public void setRealTown(boolean b)
  {
    realTown = b;
  }
  public void setShowShopItem1(int i)
  {
    showShopItem1 = i;
  }
  public void setShowShopItem2(int i)
  {
    showShopItem2 = i;
  }
  public void setShowHealAmount(boolean b)
  {
    showHealAmount = b;
  }
  public void setResponse1(String s)
  {
    response1 = s;
  }
  public void setResponse2(String s)
  {
    response2 = s;
  }
  public void setShopItems(String[] s)
  {
    shopItems = s;
  }
  
  //method(s)----------------------------------------------------------------
  public String createName()
  {
    Random rand = new Random();
    String[] head = {"green", "blue", "end", "low", "upper", "jan", "ray",
      "dor", "dire", "dist", "rhey", "zhey", "fez", "cor", "kor",
      "high", "west", "east", "south", "north", "shade", "sun", "star",
      "bright", "red", "wood", "brick", "wilde", "ost", "court", "violet"};
    String[] tail = {"burg", "berg", "town", "bell", "stead", "lake", "wall", "mill", "marsh",
      "land", "field", "dale", "ton", "fell", "keep", "beach", "hollow", "haven",
      "shore", "fair", "wald", "hall", "fort", "ford"};
    return (head[rand.nextInt(head.length-1)] + tail[rand.nextInt(tail.length-1)]);
  }
  public String createResponses(Player player)
  {
    Random r = new Random();
    if (response1.equals("not-set"))
    {
      if (r.nextInt(10) == 1)
      {
        response1 = "set";
        return "item";
      }
      else
      {
        response1 = "set";
        if (player.getResponseSize() == 0) 
          return "the people have nothing to say...";
        else
        {
          String re = player.getResponse(r.nextInt(player.getResponseSize()));
          System.out.println("you meet several townspeople who spread some of their wisdom to you: ");
          return re;
        }
      }
    }
    else if (response2.equals("not-set"))
    {
      if (r.nextInt(10) == 1)
      {
        response2 = "set";
        return "item";
      }
      else
      {
        response2 = "set";
        if (player.getResponseSize() == 0) return "the people have nothing to say...";
        else
        {
          String re = player.getResponse(r.nextInt(player.getResponseSize()));
          System.out.println("you meet several townspeople who spread some of their wisdom to you: ");
          return re;
        }
      }
    }
    else return "the people have nothing to say...";
  }
  public String toString()
  {
    return getName();
  }
  public String[] createShopItems()
  {
    Random r = new Random();
    String[] a = new String[2];
    String[] item1 = {"health upgrade", "experience upgrade", "guard upgrade", "attack upgrade"};
    String[] item2 = {"health potion", "attack potion", "experience potion", "money potion", "health potion", "attack potion",
      "experience potion", "money potion", "town map", "dungeon map"};
    a[0] = item1[r.nextInt(item1.length)];
    a[1] = item2[r.nextInt(item2.length)];
    if (a[1].equals("health potion")) shopItem2Price = 50;
    else if (a[1].equals("attack potion")) shopItem2Price = 30;
    else if (a[1].equals("experience potion")) shopItem2Price = 50;
    else if (a[1].equals("money potion")) shopItem2Price = 50;
    else if (a[1].equals("town map")) shopItem2Price = 100;
    else if (a[1].equals("dungeon map")) shopItem2Price = 150;
    return a; 
  }
  public void setShopItemPrices()
  {
    Random r = new Random();
    if (getShopItem1().equals("health upgrade"))
    {
      double uA = healAmount * .2;
      shopItem1Amount = (int) Math.ceil(uA);
      if (shopItem1Amount < 2)
        shopItem1Amount = 2;
      shopItem1Price = (shopItem1Amount * 10) + r.nextInt(5) + 8;
    }
    else if (getShopItem1().equals("experience upgrade"))
    {
      shopItem1Amount = r.nextInt(85) + 5;
      shopItem1Price = ((shopItem1Amount * 2) - (int)(8 * Math.ceil(healAmount * .1) - r.nextInt(6)));
      if (shopItem1Price < 5) shopItem1Price = 5;
    }
    else if (getShopItem1().equals("guard upgrade"))
    {
      shopItem1Price = 100;
      shopItem1Amount = 1;
    }
    else if (getShopItem1().equals("attack upgrade"))
    {
      shopItem1Amount = (int)(Math.ceil(healAmount * .1) - r.nextInt(3));
      if (shopItem1Amount < 1)
        shopItem1Amount = 1;
      shopItem1Price = (shopItem1Amount * 30) + r.nextInt(20) + 5;
    }
  }
}

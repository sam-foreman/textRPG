//text rpg v. 3
// created by sam foreman
// i regret nothing
package textGameOne;
import java.util.*;
public class Game 
{
  
	
	// add in boss battle in the field "this is where you'd fight..."
	// is guard 100 or 150? it's 100 in menu but 150 in shop
	//puzzle spacing is broken on red
//gamestart-------------------------------------------------------------------------------
  static boolean color = true;
  //main method
  public static void main(String[] args)
  {
    System.out.println();
    Scanner keyboard = new Scanner(System.in);
    System.err.println("what color is this sentence?");
    System.out.println("1. red.");
    System.out.println("2. black.");
    System.out.print("enter command: ");
    String command = keyboard.nextLine();
    System.out.println("********************************************************************************");
    while (!(command.equals("1") || command.equals("2")))
    {
      System.err.println("error. invalid input.");
      System.out.print("enter command: ");
      command = keyboard.nextLine();
      System.out.println("********************************************************************************");
    }
    if (command.equals("1"))
      System.out.println("error text set to red.");
    else
    {
      System.out.println("error text set to black.");
      color = false;
    }
    System.out.print("enter your name: ");
    String name = keyboard.nextLine();
    System.out.println("********************************************************************************");
    Player player;
    if (name.equals("#debug"))
      player = new Player(true);
    else if (name.equals("test"))
      player = new Player(1);
    else
      player = new Player(name);
    start(player, keyboard);
  }
  
  public static void start(Player player, Scanner keyboard)
  {
    System.out.println();
    System.out.println("you are in the world. what do you want to do?");
    System.out.println("1. explore.");
    System.out.println("2. travel.");
    System.out.println("3. menu.");
    System.out.print("enter command: ");
    String command = keyboard.nextLine();
    System.out.println("********************************************************************************");
    if (command.equals("1"))
    {
      explore(player, keyboard);
      start(player, keyboard);
    }
    else if (command.equals("2"))
    {
      travelController(player, keyboard);
    }
    else if (command.equals("3"))
    {
      menu(player, keyboard);
      start(player, keyboard);
    }
    else
    {
      System.err.println("invalid input.");
      start(player, keyboard);
    }
  }
  
  public static void explore(Player player, Scanner keyboard)
  {
    /*
     int m = 0;
     int upgrade = 0;
     int location = 0;
     int it = 0;
     int town = 0;
     int m2 = 0;
     int boss = 0;
     int nothing = 0;
     */
    // for (int a = 0; a < 200; a++)
    // {
  //  System.out.println();
    Random r = new Random();
    for (int i = 1; i <= player.getTownListSize(); i++)
      player.getTown(i).incrementHealCooldown(-1);
    player.incrementAltarCounter(-1);
    // 0: monster, 1: upgrade, 2: location, 3: item, 4: town, 5: boss
    int[] explore = {65, 35, 20, 10, 30, 10};
    if (player.getLevel() == 2 && player.getTownListSize() < 2)
    {
      Town t = new Town(player.getHealthT());
      System.out.printf("you have discovered the town %s.\n", t.getName());
      player.getTownList().add(t);
      player.incrementTownLimit(-1);
      start(player, keyboard);
    }
    if (player.getLevel() == 3 && player.getDungeonListSize() == 0)
    {
      Dungeon d;
      if (player.getItem().getPuzzle())
        d = new Dungeon(1);
      else
        d = new Dungeon();
      d.setBoss(player);
      System.out.println("you have discovered the dungeon " + d.getName() + ".");
      player.getDungeonList().add(d);
      start(player, keyboard);
    }
    if (player.getItem().getCompass())
      explore[2] = 40;
    if (r.nextInt(100) < explore[0])
    {
      //m++;
      Monster monster = new Monster(player);
      System.out.printf("you encounter a %s. prepare for battle.\n", monster.getName());
      battle(player, monster, keyboard, true);
    }
    else if (r.nextInt(100) < explore[1])
    {
      //upgrade++;
      //health, money, experience
      int num = r.nextInt(3);
      if (num == 0)
      {
        int health = r.nextInt(5) + 1;
        System.out.printf("you find %d health.\n", health);
        player.incrementHealthC(health);
      }
      else if (num == 1)
      {
        int money = r.nextInt(15) + 5;
        System.out.printf("you find %d money.\n", money);
        player.incrementMoney(money);
      }
      else
      {
        int exp = r.nextInt(10) + 3;
        System.out.printf("you find %d experience.\n", exp);
        player.incrementExperience(exp);
      }
    }
    else if (r.nextInt(100) < explore[2])
    {
      //location++;
      int num = r.nextInt(10);
      if (num == 0 || num == 1)
      {
        if (!(player.getOtherList().contains("bank")))
        {
          System.out.println("you have discovered the world bank.");
          player.getOtherList().add("bank");
        }
        else if (!(player.getOtherList().contains("altar")))
        {
          System.out.println("you have discovered the altar.");
          player.getOtherList().add("altar");
        }
        else
        {
          if (player.getTownLimit() > 0)
          {
            Town t = new Town(player.getHealthT());
            System.out.printf("you have discovered the town %s.\n", t.getName());
            player.getTownList().add(t);
            player.incrementTownLimit(-1);
          }
          else
          {
            Monster monster = new Monster(player);
            System.out.printf("you encounter a %s. prepare for battle.\n", monster.getName());
            battle(player, monster, keyboard, true);
          }
        }
      }
      else if (num == 2 || num == 3 || num == 4 || num == 5)
      {
        if (player.getTownListSize() > 0)
        {
          Dungeon d;
          if (player.getItem().getPuzzle())
            d = new Dungeon(1);
          else
            d = new Dungeon();
          d.setBoss(player);
          System.out.println("you have discovered the dungeon " + d.getName() + ".");
          player.getDungeonList().add(d);
        }
        else
        {
          if (player.getTownLimit() > 0)
          {
            Town t = new Town(player.getHealthT());
            System.out.printf("you have discovered the town %s.\n", t.getName());
            player.getTownList().add(t);
            player.incrementTownLimit(-1);
          }
        }
      }
      else
      {
        if (player.getTownLimit() > 0)
        {
          Town t = new Town(player.getHealthT());
          System.out.printf("you have discovered the town %s.\n", t.getName());
          player.getTownList().add(t);
          player.incrementTownLimit(-1);
        }
        else
          System.out.println("you found nothing..");
      }
    }
    else if (r.nextInt(100) < explore[3])
    {
      // it++;
      ArrayList<String> items = new ArrayList<String>();
      if (!(player.getItem().getCompass()))
        items.add("compass");
      if (!(player.getItem().getBinoculars()))
        items.add("binoculars");
      if (!(player.getItem().getPuzzle()))
        items.add("puzzle");
      if (!(player.getItem().getCharm()))
        items.add("charm");
      
      if (items.size() == 0)
      {
        int num = r.nextInt(4);
        if (num == 0)
        {
          System.out.println("you found a health potion.");
          player.getItem().incrementHealthPotion(1);
        }
        else if (num == 1)
        {
          System.out.println("you found an experience potion.");
          player.getItem().incrementExperiencePotion(1);
        }
        else if (num == 2)
        {
          System.out.println("you found a money potion.");
          player.getItem().incrementMoneyPotion(1);
        }
        else
        {
          System.out.println("you found an attack potion.");
          player.getItem().incrementAttackPotion(1);
        }
      }
      else
      {
        String item = items.get(r.nextInt(items.size()));
        if (item.equals("compass"))
        {
          System.out.println("you found the compass item.");
          player.getItem().setCompass(true);
        }
        else if (item.equals("binoculars"))
        {
          System.out.println("you found the binoculars item.");
          player.getItem().setBinoculars(true);
        }
        else if (item.equals("puzzle"))
        {
          System.out.println("you found the puzzle helper item.");
          player.getItem().setPuzzle(true);
        }
        else
        {
          System.out.println("you found the charm item.");
          player.getItem().setCharm(true);
        }
      }
    }
    else if (r.nextInt(100) < explore[4])
    {
      // town++;
      int size = player.getResponseSize();
      if (size == 0)
      {
        if (player.getTownLimit() > 0)
        {
          Town t = new Town(player.getHealthT());
          System.out.printf("you have discovered the town %s.\n", t.getName());
          player.getTownList().add(t);
          player.incrementTownLimit(-1);
        }
        else
          System.out.println("you found nothing..");
      }
      else
      {
        if (r.nextInt(100) < 80)
        {
          System.out.println("you find a fellow explorer who shares some wisdom with you: ");
          String re = player.getResponse(r.nextInt(size));
          System.out.println(re);
        }
        else
        {
          //m++;
          Monster monster = new Monster(player);
          System.out.printf("you encounter a %s. prepare for battle.\n", monster.getName());
          battle(player, monster, keyboard, true);     
        }
      }
    }
    else if (r.nextInt(100) < explore[5])
    {
      // boss++;
      if (player.getLevel() > 1)
      {
        Monster monster = new Monster(player, true);
        System.out.printf("this is the part where you'd fight %s.", monster.getName());
        System.out.println("ok you won now.");
        player.getMove().setChargeUse(0);
        player.getMove().setSapUse(0);
        player.getMove().setStealUse(0);
        player.getMove().setStunUse(0);
        if (player.getMove().getChargeUse() == 1)
          player.incrementAttack(-5 - player.getLevel());;
        System.out.println(monster.getName());
        player.incrementBossesKilled(monster.getName());
      }
      else
      {
        Monster monster = new Monster(player);
        System.out.printf("you encounter a %s. prepare for battle.\n", monster.getName());
        battle(player, monster, keyboard, true);
      }
    }
    else if (r.nextInt(100) < 75)
    {
      // m2++;
      Monster monster = new Monster(player);
      System.out.printf("you encounter a %s. prepare for battle.\n", monster.getName());
      battle(player, monster, keyboard, true);
    }
    else
    {
      // nothing++;
      System.out.println("you found nothing...");
    }
    
    /*
     System.out.println("monster: " + m);
     System.out.println("upgrade: " + upgrade);
     System.out.println("location: " + location);
     System.out.println("item: " + it);
     System.out.println("town: " + town);
     System.out.println("boss: " + boss);
     System.out.println("monster2: " + m2);
     System.out.println("nothing: " + nothing);*/
    start(player, keyboard);
  }
  
  public static void travelController(Player player, Scanner keyboard)
  {
    System.out.println();
    System.out.println("select a location");
    System.out.println("1. town.");
    System.out.println("2. dungeon.");
    System.out.println("3. other.");
    System.out.println("4. menu.");
    System.out.println("5. nevermind.");
    System.out.print("enter command: ");
    String command = keyboard.nextLine();
    System.out.println("********************************************************************************");
    if (command.equals("1"))
    {
      if (!(player.checkTownList()))
      {
        System.out.println("you know of no towns to go...");
      }
      else townTravel(player, keyboard);
    }
    else if (command.equals("2"))
    {
      if (!(player.checkDungeonList()))
      {
        System.out.println("you know of no dungeons to go...");
      }
      else dungeonTravel(player, keyboard);
    }
    else if (command.equals("3"))
    {
      if (!(player.checkOtherList()))
      {
        System.out.println("you know of no other places to go...");
      }
      else otherTravel(player, keyboard);
    }
    else if (command.equals("4"))
    {
      menu(player, keyboard);
      travelController(player, keyboard);
    }
    else if (command.equals("5"))
    {
      System.out.println("returning...");
      start(player, keyboard);
    }
    else
    {
      System.err.println("error. invalid input.");
      travelController(player, keyboard);
    }
    travelController(player, keyboard);
  }
  
//menu----------------------------------------------------------------------------------------
  public static void menu(Player player, Scanner keyboard)
  {
    System.out.println();
    System.out.println("1. stats.");
    System.out.println("2. locations.");
    System.out.println("3. items.");
    System.out.println("4. moves.");
    System.out.println("5. achievements.");
    System.out.println("6. nevermind.");
    System.out.print("enter command: ");
    String command = keyboard.nextLine();
    System.out.println("********************************************************************************");
    if (command.equals("1"))
    {
      player.stats();
      menu(player, keyboard);
    }
    else if (command.equals("2"))
    {
      if (!(player.checkTownList())) System.out.println("you know of no towns.");
      else
      {
        System.out.println("known towns: ");
        for (int i = 1; i <= player.getTownListSize(); i++)
        {
          Town town = player.getTown(i);
          int i1 = town.getShowShopItem1();
          int i2 = town.getShowShopItem2();
          System.out.printf("%d. %s \n", i, town);
          if (i1 != 0)
          {
            if (i1 == 1)
            {
              if (player.getItem().getCharm()) System.out.printf("%s for sale for %d money.\n", town.getShopItem1(), (int)(town.getShopItem1Price()*.8));
              else System.out.printf("%s for sale for %d money.\n", town.getShopItem1(), town.getShopItem1Price());
            }
            else System.out.printf("%s already purchased.\n", town.getShopItem1());
            if (i2 == 1)
            {
              if (player.getItem().getCharm()) System.out.printf("%s for sale for %d money.\n", town.getShopItem2(), (int)(town.getShopItem2Price()*.8));
              else System.out.printf("%s for sale for %d money.\n", town.getShopItem2(), town.getShopItem2Price());
            }
            else System.out.printf("%s already purchased.\n", town.getShopItem2());
          }
          else System.out.println("visit the town shop for more information.");
          if (town.getShowHealAmount())
          {
            if (town.getHealCooldown() > 0)
            {
              System.out.printf("this town is out of medical supplies for %d more turns.\n", town.getHealCooldown());
            }
            else
              System.out.printf("this town will heal up to %d health at %d per unit.\n", town.getHealAmount(), town.getHealPrice());
          }
          else System.out.println("visit the town health center for more information.");
          if (town.getResponse1().equals("set") && town.getResponse2().equals("set"))
            System.out.println("fully explored.");
          System.out.println();
        }
      }
      System.out.println();
      
      if (!(player.checkDungeonList())) System.out.println("you know of no dungeons.");
      else
      {
        System.out.println("known dungeons: ");
        for (int i = 1; i <= player.getDungeonListSize(); i++)
        {
          System.out.printf("%d. %s \n", i, player.getDungeon(i));
          Dungeon dungeon = player.getDungeon(i);
          if (dungeon.getPuzzle().checkComplete())
            System.out.println("puzzle completed.");
          else System.out.println("puzzle incomplete.");
          int complete = (int)((dungeon.getRoom1().getPercentage() + dungeon.getRoom2().getPercentage() + dungeon.getRoom3().getPercentage()
                                  + dungeon.getRoom4().getPercentage() + dungeon.getRoom5().getPercentage()) / 5);
          System.out.printf("%d%% explored.\n", complete);
          if (dungeon.getRoom1().getShop()) System.out.println("shop in room 1.");
          if (dungeon.getRoom2().getShop()) System.out.println("shop in room 2.");
          if (dungeon.getRoom3().getShop()) System.out.println("shop in room 3.");
          if (dungeon.getRoom4().getShop()) System.out.println("shop in room 4.");
          if (dungeon.getRoom5().getShop()) System.out.println("shop in room 5.");
          if (dungeon.getBossKilled() == 0)
            System.out.println("boss unknown.");
          else if (dungeon.getBossKilled() == 1)
            System.out.printf("%s at large.\n", dungeon.getBoss().getName());
          else
            System.out.printf("%s defeated.\n", dungeon.getBoss().getName());
          System.out.println();
        }
      }
      System.out.println();
      
      if (!(player.checkOtherList())) System.out.println("you know of no other locations.");
      else
      {
        System.out.println("known other locations: ");
        for (int i = 1; i <= player.getOtherListSize(); i++)
        {
          System.out.printf("%d. %s \n", i, player.getOther(i));
          if (player.getOther(i).equals("bank"))
            System.out.printf("money in bank: %d.\n", player.getBankMoney());
          else if (player.getOther(i).equals("altar"))
          {
            if (player.getAltarCounter() == 0)
              System.out.println("ready for sacrifice...");
            else
              System.out.println("not ready for sacrifice...");
          }
          System.out.println();
        }
      }
      System.out.println();
      menu(player, keyboard);
    }
    else if (command.equals("3"))
    {
      System.out.println();
      if (player.getItem().checkEquipable())
      {
        System.out.println("currently equipped items:");
        if (player.getItem().getBinoculars())
          System.out.println("binoculars: see enemy health.");
        if (player.getItem().getPuzzle())
          System.out.println("puzzle helper: finish dungeon puzzles faster.");
        if (player.getItem().getCharm())
          System.out.println("charm: discount on all shop items.");
        if (player.getItem().getCompass())
          System.out.println("compass: find locations more often.");
      }
      else
      {
        System.out.println("you have no items equipped.");
      }
      System.out.println();
      if (player.getItem().checkConsumableAndMap())
      {
        System.out.println("consumables: ");
        System.out.println("health potions: " + player.getItem().getHealthPotion());
        System.out.println("experience potions: " + player.getItem().getExperiencePotion());
        System.out.println("money potions: " + player.getItem().getMoneyPotion());
        System.out.println("attack potions: " + player.getItem().getAttackPotion());
        System.out.println("town maps: " + player.getItem().getTownMap());
        System.out.println("dungeon maps: " + player.getItem().getDungeonMap());
        System.out.println();
        System.out.printf("1. use town map.\n");
        System.out.printf("2. use dungeon map.\n");
        System.out.printf("3. nevermind.\n");
        System.out.print("enter command: ");
        String itemCommand = keyboard.nextLine();
        System.out.println("********************************************************************************");
        
        if (itemCommand.equals("1"))
        {
          if (player.getTownLimit() > 0)
          {
            player.useTownMap();
            player.incrementTownLimit(-1);
          }
          else
          {
            System.out.println("there are no new towns to discover right now. try again later...");
          }
        }
        else if (itemCommand.equals("2"))
          player.useDungeonMap();
        else if (itemCommand.equals("3"))
        {}
        else
          System.err.println("error. invalid input.");
        
      }
      else
      {
        System.out.println("you have no consumables.");
      }
      menu(player, keyboard);  
    }
    else if (command.equals("4"))
    {
      if (player.getMove().checkMove())
      {
        if (player.getMove().getCharge())
        {
          System.out.print("charge: ");
          if (player.getMove().getChargeCooldown() > 0)
            System.out.printf(player.getMove().getChargeCooldown() + " turns before ready.\n");
          else
            System.out.println("ready to use.");
        }
        if (player.getMove().getSap())
        {
          System.out.print("sap: ");
          if (player.getMove().getSapCooldown() > 0)
            System.out.printf(player.getMove().getSapCooldown() + " turns before ready.\n");
          else
            System.out.println("ready to use.");
        }
        if (player.getMove().getSteal())
        {
          System.out.print("steal: ");
          if (player.getMove().getStealCooldown() > 0)
            System.out.printf(player.getMove().getStealCooldown() + " turns before ready.\n");
          else
            System.out.println("ready to use.");
        }
        if (player.getMove().getStun())
        {
          System.out.print("stun: ");
          if (player.getMove().getStunCooldown() > 0)
            System.out.printf(player.getMove().getStunCooldown() + " turns before ready.\n");
          else
            System.out.println("ready to use.");
        }
      }
      else
        System.out.println("you have no moves.");
      menu(player, keyboard);
    }
    else if (command.equals("5"))
    {
      System.out.println("achievements: ");
      System.out.printf("money spent: %d.\n", player.getMoneySpent());
      System.out.printf("potions used: %d.\n", player.getPotionCount());
      System.out.printf("moves used: %d.\n", player.getMovesUsed());
      System.out.printf("upgrades made: %d.\n", player.getUpgradesUsed());
      System.out.printf("times died: %d.\n", player.getDeath());
      System.out.printf("monsters defeated: %d.\n", player.getMonstersKilled());
      ArrayList<String> b = player.getBossesKilled();
      if (b.size() == 0)
        System.out.println("no bosses defeated.");
      else
      {
        System.out.println("bosses defeated: ");
        for (String element : b)
          System.out.println(element);
      }
      menu(player, keyboard);
    }
    else if (command.equals("6"))
    {
      System.out.println("returning...");
      
    }
    else
    {
      System.err.println("error. invalid input.");
      menu(player, keyboard);
    }
    
  }
  
//battle--------------------------------------------------------------------------------------
  
  public static void battle(Player player, Monster monster, Scanner keyboard, Boolean turn)
  {
    Random rand = new Random();
    int mAttack;
    System.out.println();
    if (turn)
    {
      player.setGuardCheck(false);
      System.out.println("it is your turn. what do you want to do?");
      System.out.printf("current health: %d/%d.\n", player.getHealthC(), player.getHealthT());
      if (player.getItem().getBinoculars())
        System.out.printf("enemy health: %d/%d.\n", monster.getHealthC(), monster.getHealthT());
      System.out.println("1. attack.");
      System.out.println("2. guard.");
      System.out.println("3. run.");
      System.out.println("4. inventory.");
      System.out.println("5. moves.");
      System.out.println("6. menu.");
      System.out.print("enter command: ");
      String command = keyboard.nextLine();
      System.out.println("********************************************************************************");
      if (command.equals("1"))
      {
        attack(player, monster, keyboard);
      }
      else if (command.equals("2"))
      {
        if (player.getMove().getChargeUse() > 0)
          System.out.println("you can't do that right now.");
        else
        {
          player.setGuardCheck(true);
          turn = false;
        }
      }
      else if (command.equals("3"))
      {
        if (player.getMove().getChargeUse() > 0)
          System.out.println("you can't do that right now.");
        else
        {
          if (rand.nextInt(2) == 1)
          {
            System.out.println("you ran away.");
            player.reset();
            start(player, keyboard);
            
          }
          else
          {
            System.out.println("you fail to run away.");
            turn = false;
          }
        }
      }
      else if (command.equals("4"))  
      {
        System.out.println();
        if (player.getItem().checkEquipable())
        {
          System.out.println("currently equipped items:");
          if (player.getItem().getBinoculars())
            System.out.println("binoculars.");
          if (player.getItem().getPuzzle())
            System.out.println("puzzle.");
          if (player.getItem().getCharm())
            System.out.println("charm.");
          if (player.getItem().getCompass())
            System.out.println("compass.");
        }
        else
        {
          System.out.println("you have no items equipped.");
        }
        int countC = 1;
       // int countE = 1;
        System.out.println();
        if (player.getItem().checkConsumable())
        {
          ArrayList<String> itemList = new ArrayList<String>();
          System.out.println("select item to use:");
          System.out.println();
          if (player.getItem().getHealthPotion() > 0)
          {
            System.out.printf("%d. health potion (%d left).\n", countC, player.getItem().getHealthPotion());
            countC++;
            itemList.add("health potion");
          }
          if (player.getItem().getExperiencePotion() > 0)
          {
            System.out.printf("%d. experience potion (%d left).\n", countC, player.getItem().getExperiencePotion());
            countC++;
            itemList.add("experience potion");
          }
          if (player.getItem().getAttackPotion() > 0)
          {
            System.out.printf("%d. attack potion (%d left).\n", countC, player.getItem().getAttackPotion());
            countC++;
            itemList.add("attack potion");
          }
          if (player.getItem().getMoneyPotion() > 0)
          {
            System.out.printf("%d. money potion (%d left).\n", countC, player.getItem().getMoneyPotion());
            countC++;
            itemList.add("money potion");
          }
          System.out.printf("%d. nevermind.\n", countC);
          System.out.print("enter command: ");
          String c = keyboard.nextLine();
          System.out.println("********************************************************************************");
          int i;
          for ( i = 1; i < countC; i++)
          {
            if (Integer.toString(i).equals(c))
            {
              player.useItem(itemList.get(i-1), monster);
              battle(player, monster, keyboard, turn);
            }
          }
          if (Integer.toString(i).equals(c))
          {
            System.out.println("returning...");
            battle(player, monster, keyboard, turn);
          }
          else
          {
            System.err.println("error. invalid input.");
            battle(player, monster, keyboard, turn);
          }
          
        }
        else
        {
          System.out.println("you have no consumables in your inventory.");
        }
      }
      else if (command.equals("5"))
      {
        if (player.getMove().checkMove())
        {
          boolean[] canUse = {false, false, false, false};
          if (player.getMove().getCharge())
          {
            System.out.print("1. charge: ");
            if (player.getMove().getChargeCooldown() > 0)
              System.out.printf(player.getMove().getChargeCooldown() + " turns before ready.\n");
            else
            {
              canUse[0] = true;
              System.out.println("ready to use.");
            }
          }
          else 
          {
            System.out.print("1. "); 
            System.err.println("move not learned yet.");
          }
          if (player.getMove().getSap())
          {
            System.out.print("2. sap: ");
            if (player.getMove().getSapCooldown() > 0)
              System.out.printf(player.getMove().getSapCooldown() + " turns before ready.\n");
            else
            {
              canUse[1] = true;
              System.out.println("ready to use.");
            }
          }
          else 
          {
            System.out.print("2. "); 
            System.err.println("move not learned yet.");
          }
          if (player.getMove().getSteal())
          {
            System.out.print("3. steal: ");
            if (player.getMove().getStealCooldown() > 0)
              System.out.printf(player.getMove().getStealCooldown() + " turns before ready.\n");
            else
            {
              canUse[2] = true;
              System.out.println("ready to use.");
            }
          }
          else
          {
            System.out.print("3. "); 
            System.err.println("move not learned yet.");
          }
          if (player.getMove().getStun())
          {
            System.out.print("4. stun: ");
            if (player.getMove().getStunCooldown() > 0)
              System.out.printf(player.getMove().getStunCooldown() + " turns before ready.\n");
            else
            {
              canUse[3] = true;
              System.out.println("ready to use.");
            }
          }
          else 
          {
            System.out.print("4. ");
            System.err.println("move not learned yet.");
          }
          System.out.println("5. nevermind.");
          System.out.print("enter command: ");
          String c = keyboard.nextLine();
          System.out.println("********************************************************************************");
          if (c.equals("1"))
          {
            if (player.getMove().getCharge())
            {
              if (canUse[0] == true)
              {
                if (player.getMove().getChargeUse() > 0)
                  System.out.println("you are already using a move.");
                else
                {
                  player.getMove().setChargeUse(2);
                  player.getMove().setChargeCooldown(12);
                  attack(player, monster, keyboard);
                }
              }
              else
                System.out.println("this move is still in cooldown.");
            }
            else System.err.println("you don't know this move yet.");
          }
          else if (c.equals("2"))
          {
            if (player.getMove().getSap())
            {
              if (canUse[1] == true)
              {
                if (player.getMove().getChargeUse() > 0)
                  System.out.println("you are already using a move.");
                else
                {
                  player.getMove().setSapUse(5);
                  player.getMove().setSapCooldown(8);
                  attack(player, monster, keyboard);
                }
              }
              else
                System.out.println("this move is still in cooldown.");
            }
            else System.err.println("you don't know this move yet.");
          }
          else if (c.equals("3"))
          {
            if (player.getMove().getSteal())
            {
              if (canUse[2] == true)
              {
                if (player.getMove().getChargeUse() > 0)
                  System.out.println("you are already using a move.");
                else
                {
                  player.getMove().setStealUse(1);
                  player.getMove().setStealCooldown(10);
                  attack(player, monster, keyboard);
                }
              }
              else
                System.out.println("this move is still in cooldown.");
            }
            else System.err.println("you don't know this move yet.");
          }
          else if (c.equals("4"))
          {
            if (player.getMove().getStun())
            {
              if (canUse[3] == true)
              {
                if (player.getMove().getChargeUse() > 0)
                  System.out.println("you are already using a move.");
                else
                {
                  player.getMove().setStunUse(4);
                  player.getMove().setStunCooldown(14);
                  attack(player, monster, keyboard);
                }
              }
              else
                System.out.println("this move is still in cooldown.");
            }
            else System.err.println("you don't know this move yet.");
          }
          else if (c.equals("5"))
          {
            System.out.println("returning...");
            battle(player, monster, keyboard, turn);
          }
          else
          {
            System.err.println("error. invalid input.");
            battle(player, monster, keyboard, turn);
          }
          
          
        }
        else
          System.out.println("you have no moves.");
      }
      else if (command.equals("6"))
      {
        menu(player, keyboard);
        battle(player, monster, keyboard, turn);
      }
      else
      {
        System.err.println("error. invalid input.");
      }
      if (!(turn))
      {
        System.out.println("switching turns...");
      }
      battle(player, monster, keyboard, turn);
    }
    else
    {
      player.getMove().incrementCooldown();
      System.out.printf("it is the %s's turn.\n", monster.getName());
      if (player.getGuardCheck())
      {
        mAttack = monster.getAttack() - player.getGuard();
        if (mAttack < 0)
          mAttack = 0;
      }
      else
        mAttack = monster.getAttack();
      if (player.getMove().getSapUse() > 0)
      {                    
        System.out.printf("the %s is sapped of it's energy, losing %d health.\n", monster.getName(), player.getAttack() / 2);
        player.getMove().incrementSapUse(-1);
        monster.incrementHealthC(-(player.getAttack()/2));
        if (monster.checkHealth())
        {
          System.out.printf("you have killed the %s.\n", monster.getName());
          player.reset();
          player.incrementMonstersKilled(1);
          player.getMove().incrementCooldown();
          player.incrementMoney(monster.getMoney());
          System.out.println("you have gained " + monster.getMoney() + " money.");
          System.out.println("you have gained " + monster.getExperience() + " experience.");
          player.incrementExperience(monster.getExperience());
          start(player, keyboard);
          
        }
      }
      if (player.getMove().getStunUse() > 0)
      {
        System.out.printf("the %s is stunned and cannot attack.\n", monster.getName());
        player.getMove().incrementStunUse(-1);
      }
      else
      {
        System.out.printf("the %s attacks for %d damage.\n", monster.getName(), mAttack);
        player.incrementHealthC(-mAttack);
      }
      System.out.println("switching turns...");
      turn = true;
      battle(player, monster, keyboard, turn);
    }
    
  }
  
  public static void attack(Player player, Monster monster, Scanner keyboard)
  {
    System.out.println();
    int charge = player.getMove().getChargeUse();
    int sap = player.getMove().getSapUse();
    int steal = player.getMove().getStealUse();
    int stun = player.getMove().getStunUse();
    if (charge > 0)
    {
      if (charge == 2)
      {
        System.out.println("you charge up your energy.");
        player.incrementAttack(5 + player.getLevel());
        player.getMove().incrementChargeUse(-1);
      }
      else
      {
        player.incrementMovesUsed(1);
        System.out.printf("you unleash a powerful attack on the %s, dealing %d damage.\n", monster.getName(), player.getAttack());
        monster.incrementHealthC(-player.getAttack());
        player.incrementAttack(-5 - player.getLevel());
        player.getMove().incrementChargeUse(-1);
      }      
    }
    else if (sap == 5)
    {
      player.incrementMovesUsed(1);
      System.out.printf("you begin sapping the energy out of the %s.\n", monster.getName());
      player.getMove().incrementSapUse(-1);
    }
    else if (steal == 1)
    {
      int a = player.getAttack();
      while (monster.getHealthC() - a < 0)
        a--;
      player.incrementMovesUsed(1);
      System.out.printf("you steal some energy from the %s, dealing %d damage and gaining \n%d health.\n", monster.getName(), player.getAttack(), a/2);
      player.incrementHealthC(a/2);
      player.getMove().incrementStealUse(-1);
      monster.incrementHealthC(-player.getAttack());
    }
    else if (stun == 4)
    {
      player.incrementMovesUsed(1);
      System.out.printf("you stun the %s.\n", monster.getName());
      player.getMove().incrementStunUse(-1);
    }
    else
    {
      System.out.printf("you attack the %s dealing %d damage.\n", monster.getName(), player.getAttack());
      monster.incrementHealthC(-player.getAttack());
    }
    
    if (monster.checkHealth())
    {
      System.out.printf("you have killed the %s.\n", monster.getName());
      player.reset();
      player.incrementMonstersKilled(1);
      player.getMove().incrementCooldown();
      player.incrementMoney(monster.getMoney());
      System.out.println("you have gained " + monster.getMoney() + " money.");
      System.out.println("you have gained " + monster.getExperience() + " experience.");
      player.incrementExperience(monster.getExperience());
      start(player, keyboard);
      
    }
    else
    {
      battle(player, monster, keyboard, false);
    }
  }
//town---------------------------------------------------------------------------------------------
  
  //travel to town
  public static void townTravel(Player player, Scanner keyboard)
  {
    System.out.println();
    player.showTownList();
    System.out.printf("%d. nevermind.\n", player.getTownListSize()+1);
    System.out.print("enter command: ");
    String command = keyboard.nextLine();
    System.out.println("********************************************************************************");
    for (int i = 1; i <= player.getTownListSize(); i++)
    {
      if (Integer.toString(i).equals(command))
      {
        town(player, keyboard, player.getTown(i));
      }
    }
    if (Integer.toString(player.getTownListSize()+1).equals(command))
    {
      System.out.println("returning...");
      start(player, keyboard);
    }
    else
    {
      System.err.println("error. invalid input.");
      townTravel(player, keyboard);
    }
  }
  //in town
  public static void town(Player player, Scanner keyboard, Town town)
  {
    System.out.println();
    System.out.println("you are in " + town + ".");
    System.out.println("1. explore.");
    System.out.println("2. heal.");
    System.out.println("3. shop.");
    System.out.println("4. menu.");
    System.out.println("5. leave.");
    System.out.print("enter command: ");
    String command = keyboard.nextLine();
    System.out.println("********************************************************************************");
    
    if (command.equals("1"))
    {
      townExplore(player, keyboard, town);
    }
    else if (command.equals("2"))
    {
      townHeal(player, keyboard, town);
    }
    else if (command.equals("3"))
    {
      townShop(player, keyboard, town);
    }
    else if (command.equals("4"))
    {
      menu(player, keyboard);
      town(player, keyboard, town);
    }
    else if (command.equals("5"))
    {
      System.out.println("returning...");
      start(player, keyboard);
    }
    else
    {
      System.err
        .println("invalid input.");
    }
    town(player, keyboard, town);
  }
  
  //town sub-methods:
  
  //heal
  public static void townHeal(Player player, Scanner keyboard, Town town)
  {
    System.out.println();
    town.setShowHealAmount(true);
    if (town.getHealCooldown() > 0)
    {                    
      System.out.printf("this town is out of medical supplies for %d more turns.\n", town.getHealCooldown());
    }
    else
    {
      int maxHealth = player.getHealthDifference();
      if (maxHealth > town.getHealAmount())
      {
        maxHealth = town.getHealAmount();
      }
      int maxHealthPrice = maxHealth * town.getHealPrice();
      System.out.printf("%s can heal up to %d health at %d per unit.\n", town.getName(), town.getHealAmount(), town.getHealPrice());
      System.out.printf("health: %d/%d\n", player.getHealthC(), player.getHealthT());
      System.out.printf("money: %d\n", player.getMoney());
      System.out.printf("upgrades: %d\n", player.getUpgrades());
      System.out.printf("1. heal max (%d health for %d money).\n", maxHealth, maxHealthPrice);
      System.out.printf("2. heal amount.\n");
      if (player.getUpgrades() > 0)
      {
        System.out.printf("3. upgrade heal amount.\n");
        System.out.println("4. menu.");
        System.out.println("5. nevermind.");
      }
      else
      {
        System.out.println("3. menu.");
        System.out.println("4. nevermind.");
      }
      System.out.print("enter command: ");
      String command = keyboard.nextLine();
      System.out.println("********************************************************************************");
      if (command.equals("1"))
      {
        if (player.getHealthC() == player.getHealthT())
        {
          System.out.println("you are already at full health.");
          System.out.println("returning...");
          town(player, keyboard, town);
        }
        else
        {
          System.out.printf("%d health restored for %d money.\n", maxHealth, maxHealthPrice);
          player.incrementHealthC(maxHealth);
          if (player.getTownListSize() == 1) town.setHealCooldown(3);
          else if (player.getTownListSize() == 2) town.setHealCooldown(5);
          else town.setHealCooldown(7);
          player.incrementMoney(-maxHealthPrice);
          System.out.println("returning...");
          town(player, keyboard, town);
        }
      }
      else if (command.equals("2"))
      {
        if (player.getHealthC() == player.getHealthT())
        {
          System.out.println("you are already at full health.");
          System.out.println("returning...");
          town(player, keyboard, town);
        }
        else
        {
          System.out.printf("enter an amount of health (up to %d): ", maxHealth);
          String hAmount = keyboard.nextLine();
          System.out.println("********************************************************************************");
          for (int i = 1; i <= maxHealth; i++)
          {
            if (Integer.toString(i).equals(hAmount))
            {
              System.out.printf("%d health restored for %d money.\n", i, i * town.getHealPrice());
              player.incrementHealthC(i);
              if (player.getTownListSize() == 1) town.setHealCooldown(3);
              else if (player.getTownListSize() == 2) town.setHealCooldown(5);
              else town.setHealCooldown(7);
              player.incrementMoney(-(i * town.getHealPrice()));
              System.out.println("returning...");
              town(player, keyboard, town);
            }
          }   
          System.err.println("invalid input.");
          townHeal(player, keyboard, town);
        }
      }
      else if (command.equals("3"))
      {
        if (player.getUpgrades() > 0)
        {
          int upgradeH = 10 + player.getLevel();
          System.out.printf("you may spend 1 upgrade to increase this town's healing capacity \nby %d.\n", upgradeH);
          System.out.println("1. upgrade.");
          System.out.println("2. nevermind.");
          System.out.printf("currently healing capacity: %d.\n", town.getHealAmount());
          System.out.printf("upgrades: %d.\n[", player.getUpgrades());
          System.out.print("enter command: ");
          String upgradeC = keyboard.nextLine();
          System.out.println("********************************************************************************");
          while (!(upgradeC.equals("1") || upgradeC.equals("2")))
          {
            System.err.println("error. invalid input.");
            System.out.print("enter command: ");
            upgradeC = keyboard.nextLine();
            System.out.println("********************************************************************************");
          }
          if (upgradeC.equals("1"))
          {
            System.out.printf("town healing capacity increased by %d.\n", upgradeH);
            player.incrementUpgrades(-1);
            player.incrementUpgradesUsed(1);
            
            town.incrementHealAmount(upgradeH);
            townHeal(player, keyboard, town);
          }
          else
          {
            System.out.println("returning...");
            townHeal(player, keyboard, town);
          }
        }
        else
        {
          menu(player, keyboard);
          townHeal(player, keyboard, town);
        }
      }
      else if (command.equals("4"))
      {
        if (player.getUpgrades() > 0)
        {
          menu(player, keyboard);
          townHeal(player, keyboard, town);
        }
        else
        {
          System.out.println("returning...");
          town(player, keyboard, town);
        }
      }
      else if (command.equals("5"))
      {
        if (player.getUpgrades() > 0)
        {
          System.out.println("returning...");
          town(player, keyboard, town);
        }
        else
        {
          System.err.println("invalid input.");
          townHeal(player, keyboard, town);
        }
      }
      else
      {
        System.err.println("invalid input.");
        townHeal(player, keyboard, town);
      }
    }
  }
  
  //shop
  public static void townShopOut(Player player, Scanner keyboard, Town town)
  {
    System.out.printf("%s is out of stock.\n", town.getName());
    System.out.println("1. restock (requires 1 upgrade).");
    System.out.println("2. menu.");
    System.out.println("3. nevermind.");
    System.out.printf("upgrades: %d.\n", player.getUpgrades());
    System.out.print("enter command: ");
    String command = keyboard.nextLine();
    System.out.println("********************************************************************************");
    if (command.equals("1"))
    {
      if (player.getUpgrades() <= 0)
      {
        System.out.println("you need an upgrade to restock the shop.");
        townShopOut(player, keyboard, town);
      }
      else
      {
        System.out.println("town restocked.");
        player.incrementUpgrades(-1);
        player.incrementUpgradesUsed(1);
        town.setShopItems(town.createShopItems());
        town.setShopItemPrices();
        town.setShowShopItem1(0);
        town.setShowShopItem2(0);
        
      }
    }
    else if (command.equals("2"))
    {
      menu(player, keyboard);
      townShopOut(player, keyboard, town);
    }
    else if (command.equals("3"))
    {
      System.out.println("returning...");
      town(player, keyboard, town);
    }
    else
    {
      System.err.println("error. invalid input.");
      townShopOut(player, keyboard, town);
    }
  }
  
  public static void townShop(Player player, Scanner keyboard, Town town)
  {
    if (town.getShowShopItem1() == 0)
    {
      town.setShowShopItem1(1);
      town.setShowShopItem2(1);
    }
    if (town.getShowShopItem1() == 2 && town.getShowShopItem2() == 2)
    {
      if (town.getRealTown())
        townShopOut(player, keyboard, town);
      else
      {
        System.out.println("this shop has no more items. dungeon shops cannot be restocked.");
        System.out.println("returning...");
        return;
      }
    }
    //Random r = new Random();
    System.out.println();
    System.out.printf("%s is selling the following items. enter number for more information: \n", town.getName());
    if (town.getShowShopItem1() == 2)
      System.out.printf("1. %s (purchased).\n", town.getShopItem1());
    else
      System.out.println("1. " + town.getShopItem1());
    if (town.getShowShopItem2() == 2)
      System.out.printf("2. %s (purchased).\n", town.getShopItem2());
    else
      System.out.println("2. " + town.getShopItem2());
    System.out.printf("3. menu.\n");
    System.out.println("4. nevermind.");
    System.out.print("enter command: ");
    String command = keyboard.nextLine();
    System.out.println("********************************************************************************");
    if (command.equals("1"))
    {
      if (town.getShowShopItem1()== 2)
      {
        System.out.println("you already bought this item.");
        townShop(player, keyboard, town);
      }
      else if (town.getShopItem1().equals("health upgrade"))
      {
        int amount = town.getShopItem1Amount();
        int price = town.getShopItem1Price();
        if (player.getItem().getCharm()) price -= (.2 * amount);
        System.out.println("the health upgrade will permanently increase your max health.");
        System.out.printf("this shop is offering %d health for %d money.\n", amount, price);
        System.out.println("1. buy item.");
        System.out.println("2. nevermind.");
        System.out.printf("money: %d.\n", player.getMoney());
        System.out.printf("health: %d/%d.\n", player.getHealthC(), player.getHealthT());
        System.out.print("enter command: ");
        String c = keyboard.nextLine();
        System.out.println("********************************************************************************");
        while (!(c.equals("1") || c.equals("2")))
        {
          System.err.println("invalid input.");
          System.out.print("enter command: ");
          c = keyboard.nextLine();
          System.out.println("********************************************************************************");
        }
        if (c.equals("1"))
        {
          if (player.getMoney() >= price)
          {
            System.out.println("health upgrade purchased.");
            player.incrementMoneySpent(price);
            player.incrementHealth(amount);
            player.incrementMoney(-price);
            town.setShowShopItem1(2);
          }
          
          else
            System.out.println("you don't have enough money.");
          townShop(player, keyboard, town);
        }
        else if (c.equals("2"))
          townShop(player, keyboard, town);    
      }
      else if (town.getShopItem1().equals("experience upgrade"))
      {
        int amount = town.getShopItem1Amount();
        int price = town.getShopItem1Price();
        if (player.getItem().getCharm()) price -= (.2 * amount);
        System.out.println("the experience upgrade will give you free experience.");
        System.out.printf("this shop is offering %d experience for %d money.\n", amount, price);
        System.out.println("1. buy item.");
        System.out.println("2. nevermind.");
        System.out.printf("money: %d.\n", player.getMoney());
        System.out.printf("experience: %d/100.\n", player.getExperience());
        System.out.print("enter command: ");
        String c = keyboard.nextLine();
        System.out.println("********************************************************************************");
        while (!(c.equals("1") || c.equals("2")))
        {
          System.err.println("invalid input.");
          System.out.print("enter command: ");
          c = keyboard.nextLine();
          System.out.println("********************************************************************************");
        }
        if (c.equals("1"))
        {
          if (player.getMoney() >= price)
          {
            System.out.println("experience upgrade purchased.");
            player.incrementMoneySpent(price);
            player.incrementExperience(amount);
            player.incrementMoney(-price);
            town.setShowShopItem1(2);
          }
          
          else
            System.out.println("you don't have enough money.");
          townShop(player, keyboard, town);
        }
        else if (c.equals("2"))
          townShop(player, keyboard, town);    
      }
      else if (town.getShopItem1().equals("guard upgrade"))
      {
        int price = 150;
        if (player.getItem().getCharm()) price = 120;
        System.out.print("the guard upgrade will permanently increase how much damage you can block \n"); 
        System.out.println("when you use the guard ability in battle.");
        System.out.printf("this shop is offering 1 guard for %d money.\n", price);
        System.out.println("1. buy item.");
        System.out.println("2. nevermind.");
        System.out.printf("money: %d.\n", player.getMoney());
        System.out.printf("guard: %d.\n", player.getGuard());
        System.out.print("enter command: ");
        String c = keyboard.nextLine();
        System.out.println("********************************************************************************");
        while (!(c.equals("1") || c.equals("2")))
        {
          System.err.println("invalid input.");
          System.out.print("enter command: ");
          c = keyboard.nextLine();
          System.out.println("********************************************************************************");
        }
        if (c.equals("1"))
        {
          if (player.getMoney() >= price)
          {
            System.out.println("guard upgrade purchased.");
            player.incrementMoneySpent(price);
            player.incrementGuard(1);
            player.incrementMoney(-price);
            town.setShowShopItem1(2);
          }
          else
            System.out.println("you don't have enough money.");
          townShop(player, keyboard, town);
        }
        else if (c.equals("2"))
          townShop(player, keyboard, town);    
      }
      else if (town.getShopItem1().equals("attack upgrade"))
      {
        int amount = town.getShopItem1Amount();
        int price = town.getShopItem1Price();
        if (player.getItem().getCharm()) price -= (.2 * amount);
        System.out.println("the attack upgrade will permanently increase the amount of damage you can deal.");
        System.out.printf("this shop is offering %d attack for %d money.\n", amount, price);
        System.out.println("1. buy item.");
        System.out.println("2. nevermind.");
        System.out.printf("money: %d.\n", player.getMoney());
        System.out.printf("attack: %d.\n", player.getAttack());
        System.out.print("enter command: ");
        String c = keyboard.nextLine();
        System.out.println("********************************************************************************");
        while (!(c.equals("1") || c.equals("2")))
        {
          System.err.println("invalid input.");
          System.out.print("enter command: ");
          c = keyboard.nextLine();
          System.out.println("********************************************************************************");
        }
        if (c.equals("1"))
        {
          if (player.getMoney() >= price)
          {
            System.out.println("attack upgrade purchased.");
            player.incrementMoneySpent(price);
            player.incrementAttack(amount);
            player.incrementMoney(-price);
            town.setShowShopItem1(2);
          }
          else
            System.out.println("you don't have enough money.");
          townShop(player, keyboard, town);
        }
        else if (c.equals("2"))
          townShop(player, keyboard, town); 
      }
    }
    else if (command.equals("2"))
    {
      if (town.getShowShopItem2()== 2)
      {
        System.out.println("you already bought this item.");
        townShop(player, keyboard, town);
      }
      else if (town.getShopItem2().equals("health potion"))
      {
        int price = 50;
        if (player.getItem().getCharm()) price = 40;
        System.out.println("the health potion allows you to restore some of your health away from a town's \nmedical facility.");
        System.out.printf("this shop is offering 3 health potions for %d money.\n", price);
        System.out.println("1. buy item.");
        System.out.println("2. nevermind.");
        System.out.printf("money: %d.\n", player.getMoney());
        System.out.printf("health potions owned: %d.\n", player.getItem().getHealthPotion());
        System.out.print("enter command: ");
        String c = keyboard.nextLine();
        System.out.println("********************************************************************************");
        while (!(c.equals("1") || c.equals("2")))
        {
          System.err.println("invalid input.");
          System.out.print("enter command: ");
          c = keyboard.nextLine();
          System.out.println("********************************************************************************");
        }
        if (c.equals("1"))
        {
          if (player.getMoney() >= price)
          {
            System.out.println("health potions purchased.");
            player.incrementMoneySpent(price);
            player.getItem().incrementHealthPotion(3);
            player.incrementMoney(-price);
            town.setShowShopItem2(2);
          }
          else
            System.out.println("you don't have enough money.");
          townShop(player, keyboard, town);
        }
        else if (c.equals("2"))
          townShop(player, keyboard, town);    
      }
      else if (town.getShopItem2().equals("attack potion"))
      {
        int price = 30;
        if (player.getItem().getCharm()) price = 24;
        System.out.println("the attack potion increases your attack power for one battle.");
        System.out.printf("this shop is offering 1 attack potion for %d money.\n", price);
        System.out.println("1. buy item.");
        System.out.println("2. nevermind.");
        System.out.printf("money: %d.\n", player.getMoney());
        System.out.printf("attack potions owned: %d.\n", player.getItem().getAttackPotion());
        System.out.print("enter command: ");
        String c = keyboard.nextLine();
        System.out.println("********************************************************************************");
        while (!(c.equals("1") || c.equals("2")))
        {
          System.err.println("invalid input.");
          System.out.print("enter command: ");
          c = keyboard.nextLine();
          System.out.println("********************************************************************************");
        }
        if (c.equals("1"))
        {
          if (player.getMoney() >= price)
          {
            System.out.println("attack potion purchased.");
            player.incrementMoneySpent(price);
            player.getItem().incrementAttackPotion(1);
            player.incrementMoney(-price);
            town.setShowShopItem2(2);
          }
          else
            System.out.println("you don't have enough money.");
          townShop(player, keyboard, town);
        }
        else if (c.equals("2"))
          townShop(player, keyboard, town);    
      }
      else if (town.getShopItem2().equals("experience potion"))
      {
        int price = 50;
        if (player.getItem().getCharm()) price = 40;
        System.out.println("the experience potion will grant you a temporary experience bonus in battle.");
        System.out.printf("this shop is offering 2 experience potions for %d money.\n", price);
        System.out.println("1. buy item.");
        System.out.println("2. nevermind.");
        System.out.printf("money: %d.\n", player.getMoney());
        System.out.printf("experience potions owned: %d.\n", player.getItem().getExperiencePotion());
        System.out.print("enter command: ");
        String c = keyboard.nextLine();
        System.out.println("********************************************************************************");
        while (!(c.equals("1") || c.equals("2")))
        {
          System.err.println("invalid input.");
          System.out.print("enter command: ");
          c = keyboard.nextLine();
          System.out.println("********************************************************************************");
        }
        if (c.equals("1"))
        {
          if (player.getMoney() >= price)
          {
            System.out.println("experience potions purchased.");
            player.incrementMoneySpent(price);
            player.getItem().incrementExperiencePotion(2);
            player.incrementMoney(-price);
            town.setShowShopItem2(2);
          }
          else
            System.out.println("you don't have enough money.");
          townShop(player, keyboard, town);
        }
        else if (c.equals("2"))
          townShop(player, keyboard, town);    
      }
      else if (town.getShopItem2().equals("money potion"))
      {
        int price = 50;
        if (player.getItem().getCharm()) price = 40;
        System.out.println("the money potion will grant you a temporary money bonus in battle.");
        System.out.printf("this shop is offering 2 money potions for %d money.\n", price);
        System.out.println("1. buy item.");
        System.out.println("2. nevermind.");
        System.out.printf("money: %d.\n", player.getMoney());
        System.out.printf("attack potions owned: %d.\n", player.getItem().getMoneyPotion());
        System.out.print("enter command: ");
        String c = keyboard.nextLine();
        System.out.println("********************************************************************************");
        while (!(c.equals("1") || c.equals("2")))
        {
          System.err.println("invalid input.");
          System.out.print("enter command: ");
          c = keyboard.nextLine();
          System.out.println("********************************************************************************");
        }
        if (c.equals("1"))
        {
          if (player.getMoney() >= price)
          {
            System.out.println("money potions purchased.");
            player.incrementMoneySpent(price);
            player.getItem().incrementMoneyPotion(2);
            player.incrementMoney(-price);
            town.setShowShopItem2(2);
          }
          else
            System.out.println("you don't have enough money.");
          townShop(player, keyboard, town);
        }
        else if (c.equals("2"))
          townShop(player, keyboard, town);    
      }
      else if (town.getShopItem2().equals("town map"))
      {
        int price = 100;
        if (player.getItem().getCharm()) price = 80;
        System.out.println("use a town map to reveal the location of a town.");
        System.out.printf("this shop is offering 1 town map for %d money.\n", price);
        System.out.println("1. buy item.");
        System.out.println("2. nevermind.");
        System.out.printf("money: %d.\n", player.getMoney());
        System.out.printf("town maps owned: %d.\n", player.getItem().getTownMap());
        System.out.print("enter command: ");
        String c = keyboard.nextLine();
        System.out.println("********************************************************************************");
        while (!(c.equals("1") || c.equals("2")))
        {
          System.err.println("invalid input.");
          System.out.print("enter command: ");
          c = keyboard.nextLine();
          System.out.println("********************************************************************************");
        }
        if (c.equals("1"))
        {
          if (player.getMoney() >= price)
          {
            System.out.println("town map purchased.");
            player.incrementMoneySpent(price);
            player.getItem().incrementTownMap(1);
            player.incrementMoney(-price);
            town.setShowShopItem2(2);
          }
          else
            System.out.println("you don't have enough money.");
          townShop(player, keyboard, town);
        }
        else if (c.equals("2"))
          townShop(player, keyboard, town);    
      }
      else if (town.getShopItem2().equals("dungeon map"))
      {
        int price = 150;
        if (player.getItem().getCharm()) price = 120;
        System.out.println("use a dungeon map to reveal the location of a dungeon.");
        System.out.printf("this shop is offering 1 dungeon map for %d money.\n", price);
        System.out.println("1. buy item.");
        System.out.println("2. nevermind.");
        System.out.printf("money: %d.\n", player.getMoney());
        System.out.printf("town maps owned: %d.\n", player.getItem().getDungeonMap());
        System.out.print("enter command: ");
        String c = keyboard.nextLine();
        System.out.println("********************************************************************************");
        while (!(c.equals("1") || c.equals("2")))
        {
          System.err.println("invalid input.");
          System.out.print("enter command: ");
          c = keyboard.nextLine();
          System.out.println("********************************************************************************");
        }
        if (c.equals("1"))
        {
          if (player.getMoney() >= price)
          {
            System.out.println("dungeon map purchased.");
            player.incrementMoneySpent(price);
            player.getItem().incrementDungeonMap(1);
            player.incrementMoney(-price);
            town.setShowShopItem2(2);
          }
          else
            System.out.println("you don't have enough money.");
          townShop(player, keyboard, town);
        }
        else if (c.equals("2"))
          townShop(player, keyboard, town);    
      }
    }
    else if (command.equals("3"))
    {
      menu(player, keyboard);
      townShop(player, keyboard, town);
    }
    else if (command.equals("4"))
    {
      System.out.println("returning...");
      if (town.getRealTown()) town(player, keyboard, town);
    }
    else
    {
      System.err.println("invalid input.");
      townShop(player, keyboard, town);
    }
  }
  
  //explore
  public static void townExplore(Player player, Scanner keyboard, Town town)
  {
    System.out.println();
    Random r = new Random();
    String re = town.createResponses(player);
    if (re.equals("item"))
    {
      System.out.println("the townspeople give you a gift.");
      ArrayList<String> items = new ArrayList<String>();
      if (!(player.getItem().getBinoculars()))
        items.add("binoculars");
      if (!(player.getItem().getPuzzle()))
        items.add("puzzle");
      if (!(player.getItem().getCharm()))
        items.add("charm");
      if (!(player.getItem().getCompass()))
        items.add("compass");
      
      if (items.size() == 0)
      {
        System.out.println("you get 100 money."); 
        player.incrementMoney(100);
      }
      else
      {
        String gift = items.get(r.nextInt(items.size()));
        if (gift.equals("binoculars"))
        {
          System.out.println("you get the item: binoculars.");
          player.getItem().setBinoculars(true);
        }
        else if (gift.equals("puzzle"))
        {
          System.out.println("you get the item: puzzle.");
          player.getItem().setPuzzle(true);
        }
        else if (gift.equals("charm"))
        {
          System.out.println("you get the item: charm.");
          player.getItem().setCharm(true);
        }
        else if (gift.equals("compass"))
        {
          System.out.println("you get the item: compass.");
          player.getItem().setCompass(true);
        }
      }
    }
    else
      System.out.println(re);
    town(player, keyboard, town);
  }
  
//dungeon----------------------------------------------------------------------------------
  public static void dungeonTravel(Player player, Scanner keyboard)
  {
    System.out.println();
    player.showDungeonList();
    System.out.printf("%d. nevermind.\n", player.getDungeonListSize()+1);
    System.out.print("enter command: ");
    String command = keyboard.nextLine();
    System.out.println("********************************************************************************");
    for (int i = 1; i <= player.getDungeonListSize(); i++)
    {
      if (Integer.toString(i).equals(command))
      {
        dungeon(player, keyboard, player.getDungeon(i));
      }
    }
    if (Integer.toString(player.getDungeonListSize()+1).equals(command))
    {
      System.out.println("returning...");
      start(player, keyboard);
    }
    else
    {
      System.err.println("error. invalid input.");
      dungeonTravel(player, keyboard);
    }
  }
  
  public static void upgradeDungeon(Player player, Scanner keyboard, Dungeon dungeon)
  {
    System.out.println();
    if (player.getUpgrades() == 0)
    {
      System.out.println("you need 1 upgrade to reset the dungeon.");
      System.out.println("returning...");
      dungeon(player, keyboard, dungeon);
    }
    else           
      System.out.println("resetting the dungeon will allow you to explore it with all new rooms and a new \nboss.");
    int complete = (int)((dungeon.getRoom1().getPercentage() + dungeon.getRoom2().getPercentage() + dungeon.getRoom3().getPercentage()
                            + dungeon.getRoom4().getPercentage() + dungeon.getRoom5().getPercentage()) / 5);
    if (complete < 100)
      System.err.println("note: any progress made on completing the dungeon will also be reset.");
    System.out.println("1. reset.");
    System.out.println("2. menu.");
    System.out.println("3. nevermind.");
    System.out.printf("upgrades: %d.\n", player.getUpgrades());
    System.out.print("enter command: ");
    String command = keyboard.nextLine();
    System.out.println("********************************************************************************");
    if (command.equals("1"))
    {
      System.out.println("dungeon reset.");
      player.incrementUpgrades(-1);
      player.incrementUpgradesUsed(1);
      for (int i = 1; i <= player.getDungeonListSize(); i++)
      {
        if (player.getDungeon(i).getName().equals(dungeon.getName()))
        {
          if (player.getItem().getPuzzle())
            dungeon = new Dungeon(6, dungeon.getName());
          else
            dungeon = new Dungeon(dungeon.getName());
          dungeon.setBoss(player);
          player.getDungeonList().set(i-1, dungeon);
        }
      }
      dungeon(player, keyboard, dungeon);
    }
    else if (command.equals("2"))
    {
      menu(player, keyboard);
      upgradeDungeon(player, keyboard, dungeon);
    }
    else if (command.equals("3"))
    {
      System.out.println("returning...");
      dungeon(player, keyboard, dungeon);
    }
    else
    {
      System.err.println("error. invalid input.");
      upgradeDungeon(player, keyboard, dungeon);
    }
  }
  
  
  public static void dungeon(Player player, Scanner keyboard, Dungeon dungeon)
  {
    System.out.println();
    System.out.printf("you are in %s.\n", dungeon.getName());
    int complete = (int)((dungeon.getRoom1().getPercentage() + dungeon.getRoom2().getPercentage() + dungeon.getRoom3().getPercentage()
                            + dungeon.getRoom4().getPercentage() + dungeon.getRoom5().getPercentage()) / 5);
    if (complete == 100 && !(dungeon.getComplete()))
    {
      System.out.println("you get 1 upgrade for fully exploring the dungeon.");
      player.incrementUpgrades(1);
      dungeon.setComplete(true);
    }
    
    System.out.printf("%d%% explored.\n", complete);
    System.out.printf("1. enter room 1. (%d%% completed.)", dungeon.getRoom1().getPercentage());
    if (dungeon.getRoom1().getShop()) System.out.println(" has shop.");
    else System.out.println();
    System.out.printf("2. enter room 2. (%d%% completed.)", dungeon.getRoom2().getPercentage());
    if (dungeon.getRoom2().getShop()) System.out.println(" has shop.");
    else System.out.println();
    System.out.printf("3. enter room 3. (%d%% completed.)", dungeon.getRoom3().getPercentage());
    if (dungeon.getRoom3().getShop()) System.out.println(" has shop.");
    else System.out.println();
    System.out.printf("4. enter room 4. (%d%% completed.)", dungeon.getRoom4().getPercentage());
    if (dungeon.getRoom4().getShop()) System.out.println(" has shop.");
    else System.out.println();
    System.out.printf("5. enter room 5. (%d%% completed.)", dungeon.getRoom5().getPercentage());
    if (dungeon.getRoom5().getShop()) System.out.println(" has shop.");
    else System.out.println();
    System.out.println("6. check puzzle.");
    System.out.println("7. enter boss room.");
    if (dungeon.getBossKilled() == 2)
    {
      System.out.println("8. reset dungeon (requires 1 upgrade).");
      System.out.println("9. menu.");
      System.out.println("10. leave.");
      System.out.printf("upgrades: %d.\n", player.getUpgrades());
    }
    else
    {
      System.out.println("8. menu.");
      System.out.println("9. leave.");
    }
    System.out.print("enter command: ");
    String command = keyboard.nextLine();
    System.out.println("********************************************************************************");
    if (command.equals("1"))
      dungeonExplore(player, keyboard, dungeon, dungeon.getRoom1());
    else if (command.equals("2"))
      dungeonExplore(player, keyboard, dungeon, dungeon.getRoom2());
    else if (command.equals("3"))
      dungeonExplore(player, keyboard, dungeon, dungeon.getRoom3());
    else if (command.equals("4"))
      dungeonExplore(player, keyboard, dungeon, dungeon.getRoom4());
    else if (command.equals("5"))
      dungeonExplore(player, keyboard, dungeon, dungeon.getRoom5());
    else if (command.equals("6"))
      puzzleRoom(player, keyboard, dungeon, dungeon.getPuzzle());
    else if (command.equals("7"))
    {
      if (dungeon.getPuzzle().checkComplete()) bossRoom(player, keyboard, dungeon);
      else System.out.println("this room is locked...");
    }
    else if (command.equals("8"))
    {
      if (dungeon.getBossKilled() == 2)
        upgradeDungeon(player, keyboard, dungeon);
      else
        menu(player, keyboard);
    }
    else if (command.equals("9"))
    {
      if (dungeon.getBossKilled() == 2)
      {
        menu(player, keyboard);
      }
      else
      {
        System.out.println("returning...");
        start(player, keyboard);
      }
    }
    else if (command.equals("10"))
    {
      if (dungeon.getBossKilled() == 2)
      {
        System.out.println("returning...");
        start(player, keyboard);
      }
      else
      {
        System.err.println("error. invalid input.");
      }
    }
    else
      System.err.println("invalid input.");
    dungeon(player, keyboard, dungeon);
    
    
    
  }
  public static void dungeonExplore(Player player, Scanner keyboard, Dungeon dungeon, Room room)
  {
    if (room.getPercentage() == 100 && !(room.getFinish()))
    {
      for (int i = 1; i <= player.getTownListSize(); i++)
        player.getTown(i).incrementHealCooldown(-1);
      room.setFinish(true);
    }
    
    System.out.println();
    System.out.printf("you are in the %s.\n", room.getName());
    System.out.printf("%d%% completed.\n", room.getPercentage());
    System.out.println("1. explore.");
    System.out.println("2. menu.");
    System.out.println("3. go back to main room.");
    if (room.getShop())
      System.out.println("4. enter shop.");
    System.out.print("enter command: ");
    String command = keyboard.nextLine();
    System.out.println("********************************************************************************");
    Random r = new Random();
    if (command.equals("1"))
    {
      if (room.getContents().size() == 0)
        System.out.println("this room is fully explored.");
      else
      {
        if (room.getContents().get(0).equals("monster"))
        {
          Monster monster = new Monster(player);
          System.out.printf("you encounter a %s. prepare for battle.\n", monster.getName());
          dungeonBattle(player, monster, keyboard, true, dungeon, room);
        }
        else if (room.getContents().get(0).equals("blockade"))
        {
          System.out.println("there is a blockade. you can't leave.");
          room.setBlockade(true);
        }
        else if (room.getContents().get(0).equals("magic"))
        {
          System.out.println("this room is magic. you get better loot.");
          room.setMagic(true);
        }
        else if (room.getContents().get(0).equals("strong"))
        {
          Monster monster = new Monster(player);
          System.out.printf("you encounter a %s. prepare for battle.\n", monster.getName());
          dungeonBattle(player, monster, keyboard, true, dungeon, room);
        }
        else if (room.getContents().get(0).equals("health"))
        {
          System.out.println("you feel yourself rejuvenated. all health restored.");
          player.setHealthC(player.getHealthT());
        }
        else if (room.getContents().get(0).equals("money"))
        {
          int loot = r.nextInt(40) + 5;
          System.out.printf("you find %d money.\n", loot);
          player.incrementMoney(loot);
        }
        else if (room.getContents().get(0).equals("exit"))
        {
          System.out.println("you find an upgrade.");
          int a = r.nextInt(3);
          if (a == 0)
          {
            System.out.println("health increased by 2.");
            player.incrementHealth(2);
          }
          else if (a == 1)
          {
            System.out.println("attack increased by 1.");
            player.incrementAttack(1);
          }
          else
          {
            System.out.println("experience increased by 20.");
            player.incrementExperience(20);
          }
        }
        else if (room.getContents().get(0).equals("shop"))
        {
          System.out.println("you find a map leading to a shop at the start of the room. because why not?");
          room.setShop(true);
          room.createShop();
        }
        else if (room.getContents().get(0).equals("number"))
        {
          String num = dungeon.getPuzzle().getNumber();
          if (num.equals("false"))
            System.out.println("you find nothing.");
          else
            System.out.println("you find the number " + num + ".");
        }
        if (room.getMagic())
        {
          System.out.println("the room's magical properties reward you with extra loot.");
          int lootReward = r.nextInt(3);
          if (lootReward == 2 && player.getHealthDifference() == 0)
            lootReward = 1;
          if (lootReward == 0)
          {
            int exp = r.nextInt(10) + 5;
            System.out.printf("you find %d experience.\n", exp);
            player.incrementExperience(exp);
          }
          else if (lootReward == 1)
          {
            int money = r.nextInt(25) + 3;
            System.out.printf("you find %d money.\n", money);
            player.incrementMoney(money);
          }
          else
          {
            int health = r.nextInt(player.getHealthDifference()) + 1;
            System.out.printf("you restore %d health.\n", health);
            player.incrementHealthC(health);
          }
          
        }
        //get more loot
        room.getContents().remove(0);
        dungeon.incrementRoomsLeft(-1);
        if (dungeon.getRoomsLeft() == dungeon.getPuzzle().getList().size())
        {
          String num = dungeon.getPuzzle().getNumber();
          if (!(num.equals("false")))
            System.out.println("you find the number " + num + ".");
        }
        else if (dungeon.getRoomsLeft() < dungeon.getPuzzle().getList().size())
          System.err.println("something fucked up. puzzle incomplete");
        else
        {
          if (r.nextInt(10) <= 4)
          {
            String num = dungeon.getPuzzle().getNumber();
            if (!(num.equals("false")))
              System.out.println("you find the number " + num + ".");
          }
        }
      }         
      
    }
    else if (command.equals("2"))
    {
      menu(player, keyboard);
    }
    else if (command.equals("3"))
    {
      if (room.getBlockade())
        System.out.println("there is a blockade. you can't leave.");
      else
      {
        System.out.println("returning...");
        dungeon(player, keyboard, dungeon);
      }
    }
    else if (command.equals("4") && room.getShop())
      townShop(player, keyboard, room.getRoomShop());
    else
    {
      System.err.println("invalid input.");
    }
    if (room.getBlockade())
    {
      if (room.getPercentage() == 100)
      {
        System.out.println("you find a back exit to the main room.");
        room.setBlockade(false);
      }
    }
    dungeonExplore(player, keyboard, dungeon, room);
  }
  public static void puzzleRoom(Player player, Scanner keyboard, Dungeon dungeon, Puzzle puzzle)
  {
    System.out.println();
    System.out.println("you are in the puzzle room.");
    puzzle.printPuzzle();
    System.out.println("1. solve puzzle.");
    System.out.println("2. set space text color.");
    System.out.println("3. menu.");
    System.out.println("4. go back to main room.");
    if (puzzle.getNumbers().size() > 0)
    {
      System.out.print("available numbers: ");
      for (String element : puzzle.getNumbers())
        System.out.print(element + " ");
      System.out.println();
    }
    else
      System.out.println("you have no available numbers.");
    puzzle.getNumbers();
    System.out.print("enter command: ");
    String command = keyboard.nextLine();
    System.out.println("********************************************************************************");
    if (command.equals("1"))
    {
      // check if puzzle's complete
      if (puzzle.checkComplete())
        System.out.println("puzzle completed.");
      else
      {
        // check if no numbers
        System.out.println();
        if (puzzle.getNumbers().size() == 0)
          System.err.println("you have no available numbers.");
        else
        {
          // pick number
          System.out.println("select number to add. (or -1 to cancel).");
          System.out.print("available numbers: ");
          for (int indexToAdd = 0; indexToAdd < puzzle.getNumbers().size(); indexToAdd++)
          {
            System.out.print(puzzle.getNumbers().get(indexToAdd) + " ");
          }
          System.out.println();
          System.out.print("enter command: ");
          String inputNumToAdd = keyboard.nextLine();
          System.out.println("********************************************************************************");
          if (inputNumToAdd.equals("-1"))
            System.out.println("returning...");
          else
          {
            // check if player wants to go back
            if (puzzle.getNumbers().indexOf(inputNumToAdd) == -1)
              System.err.println("error. invalid input.");
            else
            {
              // pick space
              if (Game.color)
                System.out.printf("enter space to add (available spaces in red or -1 to cancel)\n");
              else     
                System.out.printf("enter space to add (available spaces to the right of each row or -1 to cancel)\n");
              ArrayList<String> availableSpaces = puzzle.printSelectSpace();
              System.out.print("enter command: ");
              String inputSpaceToAdd = keyboard.nextLine();
              System.out.println("********************************************************************************");
              if (inputSpaceToAdd.equals("-1"))
                System.out.println("returning...");
              else {
                if (!(availableSpaces.contains(inputSpaceToAdd)))
                  System.err.println("error. invalid input.");
                else
                {
                  // check if valid (inputNumToAdd & inputSpaceToAdd)
                  int spaceToAdd = Integer.parseInt(inputSpaceToAdd);
                  int scan = 0;
                  while (spaceToAdd > 0)
                  {
                    if (puzzle.getPlayerSolution()[scan].equals("0"))
                      spaceToAdd--;
                    scan++;
                  }
                  while (!(puzzle.getPlayerSolution()[scan].equals("0"))) scan++;
                  if (!(puzzle.getSolution()[scan].equals(inputNumToAdd)))
                  {
                    System.err.println("this is not the correct solution.");
                    System.out.println("you are ambushed by a monster.");
                    Monster monster = new Monster(player);
                    dungeonBattle(player, monster, keyboard, false, dungeon, null);
                  }
                  else
                  {
                    // add space & remove number
                    System.out.println("adding " + inputNumToAdd + " at space " + inputSpaceToAdd);
                    puzzle.setPlayerSolution(inputNumToAdd, scan);
                    puzzle.getNumbers().remove(inputNumToAdd);
                    if (puzzle.checkComplete())
                      System.out.println("puzzle completed. the boss room is now unlocked.");
                    System.out.println();
                  }
                }
              }
            }
          }
        }
      }
    }
    
    else if (command.equals("2"))
    {
      System.out.print("current space color: ");
      if (Game.color == true)
        System.out.println("red.");
      else
        System.out.println("black.");
      System.out.println("1. change color.");
      System.out.println("2. nevermind.");
      System.out.print("enter command: ");
      String colorCommand = keyboard.nextLine();
      System.out.println("********************************************************************************");
      while (!(colorCommand.equals("1") || colorCommand.equals("2")))
      {
        System.err.println("error. invalid input.");
        System.out.print("enter command: ");
        colorCommand = keyboard.nextLine();
        System.out.println("********************************************************************************");
      }
      if (colorCommand.equals("1"))
      {
        System.out.println("color preference changed.");
        if (Game.color == true)
          Game.color = false;
        else Game.color = true;
      }
      System.out.println("returning...");
      puzzleRoom(player, keyboard, dungeon, puzzle);
    }
    else if (command.equals("3"))
      menu(player, keyboard);
    else if (command.equals("4"))
      dungeon(player, keyboard, dungeon);
    else
      System.err.println("invalid input.");
    puzzleRoom(player, keyboard, dungeon, puzzle);
    
  }
//dungeonBattle--------------------------------------------------------------------------------------
  
  public static void dungeonBattle(Player player, Monster monster, Scanner keyboard, Boolean turn, Dungeon dungeon, Room room)
  {
    Random rand = new Random();
    int mAttack;
    System.out.println();
    if (turn)
    {
      player.setGuardCheck(false);
      System.out.println("it is your turn. what do you want to do?");
      System.out.printf("current health: %d/%d.\n", player.getHealthC(), player.getHealthT());
      if (player.getItem().getBinoculars())
        System.out.printf("enemy health: %d/%d.\n", monster.getHealthC(), monster.getHealthT());
      System.out.println("1. attack.");
      System.out.println("2. guard.");
      System.out.println("3. run.");
      System.out.println("4. inventory.");
      System.out.println("5. moves.");
      System.out.println("6. menu.");
      System.out.print("enter command: ");
      String command = keyboard.nextLine();
      System.out.println("********************************************************************************");
      if (command.equals("1"))
      {
        dungeonAttack(player, monster, keyboard, dungeon, room);
      }
      else if (command.equals("2"))
      {
        if (player.getMove().getChargeUse() > 0)
          System.out.println("you can't do that right now.");
        else
        {
          player.setGuardCheck(true);
          turn = false;
        }
      }
      else if (command.equals("3"))
      {
        /*
         if (player.getMove().getChargeUse() > 0)
         System.out.println("you can't do that right now.");
         else
         {
         if (rand.nextInt(2) == 1)
         {
         System.out.println("you ran away.");
         dungeonExplore(player, keyboard, dungeon, room);
         
         }
         else
         {
         System.out.println("you fail to run away.");
         turn = false;
         }
         }
         */
        System.out.println("you can't run away in a dungeon.");
      }
      else if (command.equals("4"))  
      {
        System.out.println();
        if (player.getItem().checkEquipable())
        {
          System.out.println("currently equipped items:");
          if (player.getItem().getBinoculars())
            System.out.println("binoculars.");
          if (player.getItem().getPuzzle())
            System.out.println("puzzle.");
          if (player.getItem().getCharm())
            System.out.println("charm.");
          if (player.getItem().getCompass())
            System.out.println("compass.");
        }
        else
        {
          System.out.println("you have no items equipped.");
        }
        int countC = 1;
        // int countE = 1;
        System.out.println();
        if (player.getItem().checkConsumable())
        {
          ArrayList<String> itemList = new ArrayList<String>();
          System.out.println("select item to use:");
          System.out.println();
          if (player.getItem().getHealthPotion() > 0)
          {
            System.out.printf("%d. health potion (%d left).\n", countC, player.getItem().getHealthPotion());
            countC++;
            itemList.add("health potion");
          }
          if (player.getItem().getExperiencePotion() > 0)
          {
            System.out.printf("%d. experience potion (%d left).\n", countC, player.getItem().getExperiencePotion());
            countC++;
            itemList.add("experience potion");
          }
          if (player.getItem().getAttackPotion() > 0)
          {
            System.out.printf("%d. attack potion (%d left).\n", countC, player.getItem().getAttackPotion());
            countC++;
            itemList.add("attack potion");
          }
          if (player.getItem().getMoneyPotion() > 0)
          {
            System.out.printf("%d. money potion (%d left).\n", countC, player.getItem().getMoneyPotion());
            countC++;
            itemList.add("money potion");
          }
          System.out.printf("%d. nevermind.\n", countC);
          System.out.print("enter command: ");
          String c = keyboard.nextLine();
          System.out.println("********************************************************************************");
          int i;
          for ( i = 1; i < countC; i++)
          {
            if (Integer.toString(i).equals(c))
            {
              player.useItem(itemList.get(i-1), monster);
              dungeonBattle(player, monster, keyboard, turn, dungeon, room);
            }
          }
          if (Integer.toString(i).equals(c))
          {
            System.out.println("returning...");
            dungeonBattle(player, monster, keyboard, turn, dungeon, room);
          }
          else
          {
            System.err.println("error. invalid input.");
            dungeonBattle(player, monster, keyboard, turn, dungeon, room);
          }
          
        }
        else
        {
          System.out.println("you have no consumables in your inventory.");
        }
      }
      else if (command.equals("5"))
      {
        if (player.getMove().checkMove())
        {
          boolean[] canUse = {false, false, false, false};
          if (player.getMove().getCharge())
          {
            System.out.print("1. charge: ");
            if (player.getMove().getChargeCooldown() > 0)
              System.out.printf(player.getMove().getChargeCooldown() + " turns before ready.\n");
            else
            {
              canUse[0] = true;
              System.out.println("ready to use.");
            }
          }
          else 
          {
            System.out.print("1. ");
            System.err.println("move not learned yet.");
          }
          if (player.getMove().getSap())
          {
            System.out.print("2. sap   : ");
            if (player.getMove().getSapCooldown() > 0)
              System.out.printf(player.getMove().getSapCooldown() + " turns before ready.\n");
            else
            {
              canUse[1] = true;
              System.out.println("ready to use.");
            }
          }
          else
          {
            System.out.print("2. "); 
            System.err.println("move not learned yet.");
          }
          if (player.getMove().getSteal())
          {
            System.out.print("3. steal : ");
            if (player.getMove().getStealCooldown() > 0)
              System.out.printf(player.getMove().getStealCooldown() + " turns before ready.\n");
            else
            {
              canUse[2] = true;
              System.out.println("ready to use.");
            }
          }
          else
          {
            System.out.print("3. ");
            System.err.println("move not learned yet.");
          }
          if (player.getMove().getStun())
          {
            System.out.print("4. stun  : ");
            if (player.getMove().getStunCooldown() > 0)
              System.out.printf(player.getMove().getStunCooldown() + " turns before ready.\n");
            else
            {
              canUse[3] = true;
              System.out.println("ready to use.");
            }
          }
          else 
          {
            System.out.print("4. ");
            System.err.println("move not learned yet.");
          }
          System.out.println("5. nevermind.");
          System.out.print("enter command: ");
          String c = keyboard.nextLine();
          System.out.println("********************************************************************************");
          if (c.equals("1"))
          {
            if (player.getMove().getCharge())
            {
              if (canUse[0] == true)
              {
                if (player.getMove().getChargeUse() > 0)
                  System.out.println("you are already using a move.");
                else
                {
                  player.getMove().setChargeUse(2);
                  player.getMove().setChargeCooldown(12);
                  dungeonAttack(player, monster, keyboard, dungeon, room);
                }
              }
              else
              {
                System.out.println("this move is still in cooldown.");
              }
            }
            else System.err.println("you don't know this move yet.");
          }
          else if (c.equals("2"))
          {
            if (player.getMove().getSap())
            {
              if (canUse[1] == true)
              {
                if (player.getMove().getChargeUse() > 0)
                  System.out.println("you are already using a move.");
                else
                {
                  player.getMove().setSapUse(5);
                  player.getMove().setSapCooldown(8);
                  dungeonAttack(player, monster, keyboard, dungeon, room);
                }
              }
              else
                System.out.println("this move is still in cooldown.");
            }
            else 
            {
              System.err.println("you don't know this move yet.");
            }
          }
          else if (c.equals("3"))
          {
            if (player.getMove().getSteal())
            {
              if (canUse[2] == true)
              {
                if (player.getMove().getChargeUse() > 0)
                  System.out.println("you are already using a move.");
                else
                {
                  player.getMove().setStealUse(1);
                  player.getMove().setStealCooldown(10);
                  dungeonAttack(player, monster, keyboard, dungeon, room);
                }
              }
              else
                System.out.println("this move is still in cooldown.");
            }
            else 
            {
              System.err.println("you don't know this move yet.");
            }
          }
          else if (c.equals("4"))
          {
            if (player.getMove().getStun())
            {
              if (canUse[3] == true)
              {
                if (player.getMove().getChargeUse() > 0)
                  System.out.println("you are already using a move.");
                else
                {
                  player.getMove().setStunUse(4);
                  player.getMove().setStunCooldown(14);
                  dungeonAttack(player, monster, keyboard, dungeon, room);
                }
              }
              else
                System.out.println("this move is still in cooldown.");
            }
            else
            {
              System.err.println("you don't know this move yet.");
            }
          }
          else if (c.equals("5"))
          {
            System.out.println("returning...");
            dungeonBattle(player, monster, keyboard, turn, dungeon, room);
          }
          else
          {
            System.err.println("error. invalid input.");
            dungeonBattle(player, monster, keyboard, turn, dungeon, room);
          }
          
          
        }
        else
          System.out.println("you have no moves.");
      }
      else if (command.equals("6"))
      {
        menu(player, keyboard);
        dungeonBattle(player, monster, keyboard, turn, dungeon, room);
      }
      else
      {
        System.err.println("error. invalid input.");
      }
      if (!(turn))
      {
        System.out.println("switching turns...");
      }
      dungeonBattle(player, monster, keyboard, turn, dungeon, room);
    }
    else
    {
      player.getMove().incrementCooldown();
      System.out.printf("it is the %s's turn.\n", monster.getName());
      if (player.getGuardCheck())
      {
        mAttack = monster.getAttack() - player.getGuard();
        if (mAttack < 0)
          mAttack = 0;
      }
      else
        mAttack = monster.getAttack();
      if (player.getMove().getSapUse() > 0)
      {
        System.out.printf("the %s is sapped of it's energy, losing %d health.\n", monster.getName(), player.getAttack() / 2);
        player.getMove().incrementSapUse(-1);
        monster.incrementHealthC(-(player.getAttack()/2));
        if (monster.checkHealth())
        {
          System.out.printf("you have killed the %s.\n", monster.getName());
          player.reset();
          player.incrementMonstersKilled(1);
          player.getMove().incrementCooldown();
          if (room != null)
          {
            player.incrementMoney(monster.getMoney());
            System.out.println("you have gained " + monster.getMoney() + " money.");
            System.out.println("you have gained " + monster.getExperience() + " experience.");
            player.incrementExperience(monster.getExperience());
            room.getContents().remove(0);
            dungeon.incrementRoomsLeft(-1);
            if (dungeon.getRoomsLeft() == dungeon.getPuzzle().getList().size())
            {
              String num = dungeon.getPuzzle().getNumber();
              if (!(num.equals("false")))
                System.out.println("you find the number " + num + ".");
            }
            else if (dungeon.getRoomsLeft() < dungeon.getPuzzle().getList().size())
              System.err.println("something fucked up. puzzle incomplete");
            else
            {
              if (rand.nextInt(10) <= 4)
              {
                String num = dungeon.getPuzzle().getNumber();
                if (!(num.equals("false")))
                  System.out.println("you find the number " + num + ".");
              }
            }
            dungeonExplore(player, keyboard, dungeon, room);
          }
          else              
          {
            System.out.println("fearing a counterattack, you run away before you can collect the loot.");
            dungeon(player, keyboard, dungeon);
          }
          
        }
      }
      if (player.getMove().getStunUse() > 0)
      {
        System.out.printf("the %s is stunned and cannot attack.\n", monster.getName());
        player.getMove().incrementStunUse(-1);
      }
      else
      {
        System.out.printf("the %s attacks for %d damage.\n", monster.getName(), mAttack);
        player.incrementHealthC(-mAttack);
      }
      System.out.println("switching turns...");
      turn = true;
      dungeonBattle(player, monster, keyboard, turn, dungeon, room);
    }
    
  }
  
  public static void dungeonAttack(Player player, Monster monster, Scanner keyboard, Dungeon dungeon, Room room)
  {
    System.out.println();
    Random r = new Random();
    int charge = player.getMove().getChargeUse();
    int sap = player.getMove().getSapUse();
    int steal = player.getMove().getStealUse();
    int stun = player.getMove().getStunUse();
    if (charge > 0)
    {
      if (charge == 2)
      {
        System.out.println("you charge up your energy.");
        player.incrementAttack(5 + player.getLevel());
        player.getMove().incrementChargeUse(-1);
      }
      else
      {
        player.incrementMovesUsed(1);
        System.out.printf("you unleash a powerful attack on the %s, dealing %d damage.\n", monster.getName(), player.getAttack());
        monster.incrementHealthC(-player.getAttack());
        player.incrementAttack(-5 - player.getLevel());
        player.getMove().incrementChargeUse(-1);
      }      
    }
    else if (sap == 5)
    {
      player.incrementMovesUsed(1);
      System.out.printf("you begin sapping the energy out of the %s.", monster.getName());
      player.getMove().incrementSapUse(-1);
    }
    else if (steal == 1)
    {
      int a = player.getAttack();
      while (monster.getHealthC() - a < 0)
        a--;
      player.incrementMovesUsed(1);
      System.out.printf("you steal some energy from the %s, dealing %d damage and gaining \n%d health.\n", monster.getName(), player.getAttack(), a/2);
      player.incrementHealthC(a/2);
      player.getMove().incrementStealUse(-1);
      monster.incrementHealthC(-player.getAttack());
    }
    else if (stun == 4)
    {
      player.incrementMovesUsed(1);
      System.out.printf("you stun the %s.\n", monster.getName());
      player.getMove().incrementStunUse(-1);
    }
    else
    {
      System.out.printf("you attack the %s dealing %d damage.\n", monster.getName(), player.getAttack());
      monster.incrementHealthC(-player.getAttack());
    }
    
    if (monster.checkHealth())
    {
      System.out.printf("you have killed the %s.\n", monster.getName());
      player.reset();
      player.incrementMonstersKilled(1);
      player.getMove().incrementCooldown();
      if (room != null)
      {
        player.incrementMoney(monster.getMoney());
        System.out.println("you have gained " + monster.getMoney() + " money.");
        System.out.println("you have gained " + monster.getExperience() + " experience.");
        player.incrementExperience(monster.getExperience());
        room.getContents().remove(0);
        dungeon.incrementRoomsLeft(-1);
        if (dungeon.getRoomsLeft() == dungeon.getPuzzle().getList().size())
        {
          String num = dungeon.getPuzzle().getNumber();
          if (!(num.equals("false")))
            System.out.println("you find the number " + num + ".");
        }
        else if (dungeon.getRoomsLeft() < dungeon.getPuzzle().getList().size())
          System.err.println("something fucked up. puzzle incomplete");
        else
        {
          if (r.nextInt(10) <= 4)
          {
            String num = dungeon.getPuzzle().getNumber();
            if (!(num.equals("false")))
              System.out.println("you find the number " + num + ".");
          }
        }
        dungeonExplore(player, keyboard, dungeon, room);
      }
      else
      {
        System.out.println("you run away before you can collect the loot.");
        dungeon(player, keyboard, dungeon);
      }
    }
    else
    {
      dungeonBattle(player, monster, keyboard, false, dungeon, room);
    }
    
  }
  public static void bossRoom(Player player, Scanner keyboard, Dungeon dungeon)
  {
    System.out.println();
    if (dungeon.getBossKilled() == 2)
    {
      System.out.println("you already defeated the boss.");
      System.out.println("returning...");
      dungeon(player, keyboard, dungeon);
    }
    else
    {
      System.out.println();
    //  Random r = new Random();
      Boss monster = dungeon.getBoss();
      System.out.printf("you encounter %s.\nprepare to fight.\n", monster.getName());
      dungeon.setBossKilled(1);
      bossBattle(player, monster, keyboard, true, dungeon);
    }
  }
  
  public static void bossWin(Player player, Scanner keyboard, Dungeon dungeon)
  {
    Random r = new Random();
    System.out.println();
    dungeon.setBossKilled(2);
    player.incrementTownLimit(1);
    for (int i = 1; i < player.getTownListSize(); i++)
      player.getTown(i).incrementHealCooldown(-3);
    int experience = player.getLevel() + r.nextInt(10) + 15;
    System.out.printf("you get %d experience.\n", experience);
    player.incrementExperience(experience);
    int money = player.getLevel() * 20 + r.nextInt(8);
    System.out.printf("you get %d money.\n", money);
    player.incrementMoney(money);
    ArrayList<String> moves = new ArrayList<String>();
    if (player.getMove().getCharge() == false)
      moves.add("charge");
    if (player.getMove().getSap() == false)
      moves.add("sap");
    if (player.getMove().getSteal() == false)
      moves.add("steal");
    if (player.getMove().getStun() == false)
      moves.add("stun");
    
    if (moves.size() == 0)
    {
      System.out.println("you found a town map.");
      player.getItem().incrementTownMap(1);
    }
    else
    {
      String move = moves.get(r.nextInt(moves.size()));
      if (move.equals("charge"))
      {
        System.out.println("you have discovered the power of charge.");
        player.getMove().setCharge(true);
      }
      else if (move.equals("sap"))
      {
        System.out.println("you have discovered the power of sap.");
        player.getMove().setSap(true);
      }
      else if (move.equals("steal"))
      {
        System.out.println("you have discovered the power of steal.");
        player.getMove().setSteal(true);
      }
      else if (move.equals("stun"))
      {
        System.out.println("you have discovered the power of stun.");
        player.getMove().setStun(true);
      }
    }
    dungeon(player, keyboard, dungeon);
  }    
  
  public static void bossBattle(Player player, Boss monster, Scanner keyboard, Boolean turn, Dungeon dungeon)
  {
    System.out.println();
    Random rand = new Random();
    int mAttack;
    //System.out.println();
    if (turn)
    {
      if (player.getIsSapped() > 0 && player.getCanSap())
      {
        int sapAmount = monster.getAttack() / 3;
        if (sapAmount > player.getHealthC())
          sapAmount = player.getHealthC();
        if (sapAmount < 1)
          sapAmount = 1;
        System.out.printf("%s saps you of your energy. you lose %d health.\n", monster.getName(), sapAmount);
        if (player.getHealthC() -sapAmount <= 0)
          monster.setHealthC(monster.getHealthT());
        player.incrementHealthC(-sapAmount);
        player.incrementIsSapped(-1);
        player.setCanSap(false);
        if (player.getIsSapped() == 0)
          System.out.println("you break free from the sap.");
      }
      player.setGuardCheck(false);
      if (player.getIsStunned() > 0)
      {
        if (player.getMove().getChargeUse() > 0)
        {
          player.incrementAttack(-2 - player.getLevel());
          player.getMove().setChargeUse(0);
        }
        System.out.println("you are stunned, and unable to attack.");
        System.out.println("it is your turn. what do you want to do?");
        if (player.getItem().getBinoculars())
          System.out.printf("enemy health: %d/%d.\n", monster.getHealthC(), monster.getHealthT());
        System.out.println("1. guard.");
        System.out.print("enter command: ");
        String command = keyboard.nextLine();
        System.out.println("********************************************************************************");
        while (!(command.equals("1")))
        {
          System.err.println("error. invalid input.");
          System.out.print("enter command: ");
          command = keyboard.nextLine();
        }
        player.setGuardCheck(true);
        turn = false;
        
        player.incrementIsStunned(-1);
        if (player.getIsStunned() == 0)
          System.out.println("you break free from the stun.");
        bossBattle(player, monster, keyboard, turn, dungeon);
      }
      else
      {
        System.out.println("it is your turn. what do you want to do?");
        System.out.printf("current health: %d/%d.\n", player.getHealthC(), player.getHealthT());
        if (player.getItem().getBinoculars())
          System.out.printf("enemy health: %d/%d.\n", monster.getHealthC(), monster.getHealthT());
        System.out.println("1. attack.");
        System.out.println("2. guard.");
        System.out.println("3. run.");
        System.out.println("4. inventory.");
        System.out.println("5. moves.");
        System.out.println("6. menu.");
        System.out.print("enter command: ");
        String command = keyboard.nextLine();
        System.out.println("********************************************************************************");
        /*while (!(command.equals("1") || command.equals("2") || command.equals("3") || command.equals("4") || command.equals("5") || command.equals("6")))
        {
          System.err.println("error. invalid input.");
          System.out.print("enter command: ");
          command = keyboard.nextLine();
        }*/
        if (command.equals("1"))
        {
          bossAttack(player, monster, keyboard, dungeon);
        }
        else if (command.equals("2"))
        {
          if (player.getMove().getChargeUse() > 0)
            System.out.println("you can't do that right now.");
          else
          {
            player.setGuardCheck(true);
            turn = false;
          }
        }
        else if (command.equals("3"))
        {
          /*
           if (player.getMove().getChargeUse() > 0)
           System.out.println("you can't do that right now.");
           else
           {
           if (rand.nextInt(2) == 1)
           {
           System.out.println("you ran away.");
           dungeonExplore(player, keyboard, dungeon, room);
           
           }
           else
           {
           System.out.println("you fail to run away.");
           turn = false;
           }
           }
           */
          System.out.println("you can't run away from this fight.");
        }
        else if (command.equals("4"))  
        {
          System.out.println();
          if (player.getItem().checkEquipable())
          {
            System.out.println("currently equipped items:");
            if (player.getItem().getBinoculars())
              System.out.println("binoculars.");
            if (player.getItem().getPuzzle())
              System.out.println("puzzle.");
            if (player.getItem().getCharm())
              System.out.println("charm.");
            if (player.getItem().getCompass())
              System.out.println("compass.");
          }
          else
          {
            System.out.println("you have no items equipped.");
          }
          int countC = 1;
         //int countE = 1;
          System.out.println();
          if (player.getItem().checkConsumable())
          {
            ArrayList<String> itemList = new ArrayList<String>();
            System.out.println("select item to use:");
            System.out.println();
            if (player.getItem().getHealthPotion() > 0)
            {
              System.out.printf("%d. health potion (%d left).\n", countC, player.getItem().getHealthPotion());
              countC++;
              itemList.add("health potion");
            }
            if (player.getItem().getExperiencePotion() > 0)
            {
              System.out.printf("%d. experience potion (%d left).\n", countC, player.getItem().getExperiencePotion());
              countC++;
              itemList.add("experience potion");
            }
            if (player.getItem().getAttackPotion() > 0)
            {
              System.out.printf("%d. attack potion (%d left).\n", countC, player.getItem().getAttackPotion());
              countC++;
              itemList.add("attack potion");
            }
            if (player.getItem().getMoneyPotion() > 0)
            {
              System.out.printf("%d. money potion (%d left).\n", countC, player.getItem().getMoneyPotion());
              countC++;
              itemList.add("money potion");
            }
            System.out.printf("%d. nevermind.\n", countC);
            System.out.print("enter command: ");
            String c = keyboard.nextLine();
            System.out.println("********************************************************************************");
            int i;
            for ( i = 1; i < countC; i++)
            {
              if (Integer.toString(i).equals(c))
              {
                if (itemList.get(i-1).equals("health potion"))
                  player.useHealthPotion();
                else
                  System.out.println("you can't use this item during a boss fight...");
                bossBattle(player, monster, keyboard, turn, dungeon);
              }
            }
            if (Integer.toString(i).equals(c))
            {
              System.out.println("returning...");
              bossBattle(player, monster, keyboard, turn, dungeon);
            }
            else
            {
              System.err.println("error. invalid input.");
              bossBattle(player, monster, keyboard, turn, dungeon);
            }
            
          }
          else
          {
            System.out.println("you have no consumables in your inventory.");
          }
        }
        else if (command.equals("5"))
        {
          if (player.getMove().checkMove())
          {
            boolean[] canUse = {false, false, false, false};
            if (player.getMove().getCharge())
            {
              System.out.print("1. charge: ");
              if (player.getMove().getChargeCooldown() > 0)
                System.out.printf(player.getMove().getChargeCooldown() + " turns before ready.\n");
              else
              {
                canUse[0] = true;
                System.out.println("ready to use.");
              }
            }
            else 
            {
              System.out.print("1. ");
              System.err.println("move not learned yet.");
            }
            if (player.getMove().getSap())
            {
              System.out.print("2. sap   : ");
              if (player.getMove().getSapCooldown() > 0)
                System.out.printf(player.getMove().getSapCooldown() + " turns before ready.\n");
              else
              {
                canUse[1] = true;
                System.out.println("ready to use.");
              }
            }
            else
            {
              System.out.print("2. "); 
              System.err.println("move not learned yet.");
            }
            if (player.getMove().getSteal())
            {
              System.out.print("3. steal : ");
              if (player.getMove().getStealCooldown() > 0)
                System.out.printf(player.getMove().getStealCooldown() + " turns before ready.\n");
              else
              {
                canUse[2] = true;
                System.out.println("ready to use.");
              }
            }
            else
            {
              System.out.print("3. ");
              System.err.println("move not learned yet.");
            }
            if (player.getMove().getStun())
            {
              System.out.print("4. stun  : ");
              if (player.getMove().getStunCooldown() > 0)
                System.out.printf(player.getMove().getStunCooldown() + " turns before ready.\n");
              else
              {
                canUse[3] = true;
                System.out.println("ready to use.");
              }
            }
            else 
            {
              System.out.print("4. ");
              System.err.println("move not learned yet.");
            }
            System.out.println("5. nevermind.");
            System.out.print("enter command: ");
            String c = keyboard.nextLine();
            System.out.println("********************************************************************************");
            if (c.equals("1"))
            {
              if (player.getMove().getCharge())
              {
                if (canUse[0] == true)
                {
                  if (player.getMove().getChargeUse() > 0)
                    System.out.println("you are already using a move.");
                  else
                  {
                    player.getMove().setChargeUse(2);
                    player.getMove().setChargeCooldown(12);
                    bossAttack(player, monster, keyboard, dungeon);
                  }
                }
                else
                {
                  System.out.println("this move is still in cooldown.");
                }
              }
              else System.err.println("you don't know this move yet.");
            }
            else if (c.equals("2"))
            {
              if (player.getMove().getSap())
              {
                if (canUse[1] == true)
                {
                  if (player.getMove().getChargeUse() > 0)
                    System.out.println("you are already using a move.");
                  else
                  {
                    player.getMove().setSapUse(3);
                    player.getMove().setSapCooldown(8);
                    bossAttack(player, monster, keyboard, dungeon);
                  }
                }
                else
                  System.out.println("this move is still in cooldown.");
              }
              else 
              {
                System.err.println("you don't know this move yet.");
              }
            }
            else if (c.equals("3"))
            {
              if (player.getMove().getSteal())
              {
                if (canUse[2] == true)
                {
                  if (player.getMove().getChargeUse() > 0)
                    System.out.println("you are already using a move.");
                  else
                  {
                    player.getMove().setStealUse(1);
                    player.getMove().setStealCooldown(10);
                    bossAttack(player, monster, keyboard, dungeon);
                  }
                }
                else
                  System.out.println("this move is still in cooldown.");
              }
              else 
              {
                System.err.println("you don't know this move yet.");
              }
            }
            else if (c.equals("4"))
            {
              if (player.getMove().getStun())
              {
                if (canUse[3] == true)
                {
                  if (player.getMove().getChargeUse() > 0)
                    System.out.println("you are already using a move.");
                  else
                  {
                    player.getMove().setStunUse(2);
                    player.getMove().setStunCooldown(14);
                    bossAttack(player, monster, keyboard, dungeon);
                  }
                }
                else
                  System.out.println("this move is still in cooldown.");
              }
              else
              {
                System.err.println("you don't know this move yet.");
              }
            }
            else if (c.equals("5"))
            {
              System.out.println("returning...");
              bossBattle(player, monster, keyboard, turn, dungeon);
            }
            else
            {
              System.err.println("error. invalid input.");
              bossBattle(player, monster, keyboard, turn, dungeon);
            }
            
            
          }
          else
            System.out.println("you have no moves.");
        }
        else if (command.equals("6"))
        {
          menu(player, keyboard);
          bossBattle(player, monster, keyboard, turn, dungeon);
        }
        else
        {
          System.err.println("error. invalid input.");
        }
        if (!(turn))
        {
          System.out.println("switching turns...");
        }
        bossBattle(player, monster, keyboard, turn, dungeon);
      }
    }
    else
    {
      player.setCanSap(true);
      player.getMove().incrementCooldown();
      System.out.printf("it is %s's turn.\n", monster.getName());
      if (player.getGuardCheck())
      {
        mAttack = monster.getAttack() - player.getGuard();
        if (mAttack < 0)
          mAttack = 0;
      }
      else
        mAttack = monster.getAttack();
      if (player.getMove().getSapUse() > 0)
      {
        int pAttack = player.getAttack() / 2;
        if (pAttack < 1)
          pAttack = 1;
        System.out.printf("%s is sapped of it's energy, losing %d health.\n", monster.getName(), pAttack);
        player.getMove().incrementSapUse(-1);
        monster.incrementHealthC(-(pAttack));
        if (monster.checkHealth())
        {
          System.out.printf("you have killed %s.\n", monster.getName());
          player.reset();
          player.incrementMonstersKilled(1);
          player.getMove().incrementCooldown(); 
          player.incrementBossesKilled(monster.getName());
          bossWin(player, keyboard, dungeon);
        }
      }
      if (player.getMove().getStunUse() > 0)
      {
        System.out.printf("%s is stunned and cannot attack.\n", monster.getName());
        monster.setChargeUse(0);
        player.getMove().incrementStunUse(-1);
      }
      else
      {
        if (monster.getChargeUse() == 1)
        {
          System.out.printf("%s unleashes a powerful attack on you, dealing %d damage.\n", monster.getName(), mAttack);
          monster.incrementAttack(-5 - player.getLevel());
          monster.setChargeUse(0);
          player.incrementHealthC(-mAttack);
        }
        else
        {
        int makeMove = rand.nextInt(100);
        if (makeMove < 70 || !(monster.getCharge() || monster.getSap() || monster.getStun() || monster.getSteal()))
        {
          System.out.printf("%s attacks for %d damage.\n", monster.getName(), mAttack);
          if (player.getHealthC() - mAttack <= 0)
            monster.setHealthC(monster.getHealthT());
          player.incrementHealthC(-mAttack);
        }
        else
        {

          ArrayList<String> movesList = new ArrayList<String>();
          if (monster.getCharge()) movesList.add("charge");
          if (monster.getSap()) movesList.add("sap");
          if (monster.getStun()) movesList.add("stun");
          if (monster.getSteal()) movesList.add("steal");
          String move = movesList.get(rand.nextInt(movesList.size() - 1));
          if (move.equals("charge"))
          {
            System.out.printf("%s is charging up its energy.\n", monster.getName());
            monster.incrementAttack(5 + player.getLevel());
            monster.setChargeUse(1);
            
          }
          else if (move.equals("sap"))
          {
            System.out.printf("%s uses sap.\n", monster.getName());
            player.setIsSapped(2);
          }
          else if (move.equals("stun"))
          {
            System.out.printf("%s uses stun. you can't move.\n", monster.getName());
            player.setIsStunned(2);
          }
          else
          {
            int stealDamage = monster.getAttack()/3;
            if (stealDamage < 1 && mAttack > 1)
              stealDamage = 1;
            System.out.printf("%s steals some energy from you, dealing %d damage and gaining %d health.\n", monster.getName(), mAttack,stealDamage);
            monster.incrementHealthC(stealDamage);
            if (player.getHealthC() - stealDamage <= 0)
              monster.setHealthC(monster.getHealthT());
            player.incrementHealthC(-mAttack);
          } 
        }
        }
      }
      System.out.println("switching turns...");
      turn = true;
      bossBattle(player, monster, keyboard, turn, dungeon);
    }
    
  }
  
  public static void bossAttack(Player player, Boss monster, Scanner keyboard, Dungeon dungeon)
  {
    System.out.println();
    //Random r = new Random();
    int charge = player.getMove().getChargeUse();
    int sap = player.getMove().getSapUse();
    int steal = player.getMove().getStealUse();
    int stun = player.getMove().getStunUse();
    if (charge > 0)
    {
      if (charge == 2)
      {
        System.out.println("you charge up your energy.");
        player.incrementAttack(2 + player.getLevel());
        player.getMove().incrementChargeUse(-1);
      }
      else
      {
        player.incrementMovesUsed(1);
        System.out.printf("you unleash a powerful attack on %s, dealing %d damage.\n", monster.getName(), player.getAttack());
        monster.incrementHealthC(-player.getAttack());
        player.incrementAttack(-2 - player.getLevel());
        player.getMove().incrementChargeUse(-1);
      }      
    }
    else if (sap == 3)
    {
      player.incrementMovesUsed(1);
      System.out.printf("you begin sapping the energy out of %s.", monster.getName());
      player.getMove().incrementSapUse(-1);
    }
    else if (steal == 1)
    {
      int a = player.getAttack();
      while (monster.getHealthC() - a < 0)
        a--;
      player.incrementMovesUsed(1);
      int hGain = a/3;
      if (hGain < 1)
        hGain = 1;
      System.out.printf("you steal some energy from %s, dealing %d damage and gaining \n%d health.\n", monster.getName(), player.getAttack(), hGain);
      player.incrementHealthC(hGain);
      player.getMove().incrementStealUse(-1);
      monster.incrementHealthC(-player.getAttack());
    }
    else if (stun == 2)
    {
      player.incrementMovesUsed(1);
      System.out.printf("you stun %s.\n", monster.getName());
      player.getMove().incrementStunUse(-1);
    }
    else
    {
      System.out.printf("you attack %s dealing %d damage.\n", monster.getName(), player.getAttack());
      monster.incrementHealthC(-player.getAttack());
    }
    
    if (monster.checkHealth())
    {
      System.out.printf("you have killed %s.\n", monster.getName());
      player.reset();
      player.incrementMonstersKilled(1);
      player.getMove().incrementCooldown();
      player.incrementBossesKilled(monster.getName());
      bossWin(player, keyboard, dungeon);
    }
    else
    {
      bossBattle(player, monster, keyboard, false, dungeon);
    }
  }
//other----------------------------------------------------------------------------------
  public static void otherTravel(Player player, Scanner keyboard)
  {
    System.out.println();
    player.showOtherList();
    System.out.printf("%d. nevermind.\n", player.getOtherListSize()+1);
    System.out.print("enter command: ");
    String command = keyboard.nextLine();
    System.out.println("********************************************************************************");
    for (int i = 1; i <= player.getOtherListSize(); i++)
    {
      if (Integer.toString(i).equals(command))
      {
        otherController(player, keyboard, player.getOther(i));
      }
    }
    if (Integer.toString(player.getOtherListSize()+1).equals(command))
    {
      System.out.println("returning...");
      start(player, keyboard);
    }
    else
    {
      System.err.println("error. invalid input.");
      otherTravel(player, keyboard);
    }
  }
  
  public static void otherController(Player player, Scanner keyboard, String location)
  {
    if (location.equals("altar"))
      altar(player, keyboard);
    else if (location.equals("bank"))
      bank(player, keyboard);
  }
  
  public static void altarUpgrade(Player player, Scanner keyboard)
  {
    if (player.getUpgrades() < 5)
    {
      System.out.println("you need more upgrades.");
      System.out.println("returning...");
      altar(player, keyboard);
    }
    else
    {                    
      System.out.println("upgrading the altar will allow you to see the requested sacrifice before you \nmake it.");
      System.out.println("1. do it. (requires 5 upgrades)");
      System.out.println("2. menu.");
      System.out.println("3. nevermind.");
      System.out.print("enter command: ");
      String command = keyboard.nextLine();
      System.out.println("********************************************************************************");
      if (command.equals("1"))
      {
        System.out.println("altar upgraded.");
        player.incrementUpgrades(-5);
        player.incrementUpgradesUsed(5);
        player.setAltarUpgrade(true);
        altar(player, keyboard);
      }
      else if (command.equals("2"))
      {
        menu(player, keyboard);
        altarUpgrade(player, keyboard);
      }
      else if (command.equals("3"))
      {
        System.out.println("returning...");
        altar(player, keyboard);
      }
      else
      {
        System.err.println("error. invalid input.");
        altarUpgrade(player, keyboard);
      }
    }
  }
  public static void altar(Player player, Scanner keyboard)
  {
    Random r = new Random();
    System.out.println();
    System.out.println("you find an altar sitting in a clearing.");
    System.out.println("as you approach, you hear a voice whisper in your ear.");
    ArrayList<String> choices = new ArrayList<String>();
    if (player.getHealthT() > 15)
      choices.add("health");
    if (player.getExperience() > 50)
      choices.add("experience");
    if (player.getGuard() > 2)
      choices.add("guard");
    if (player.getAttack() > 5)
      choices.add("attack");
    
    if (choices.size() == 0)
    {             
      System.out.println("'you have nothing to offer us at this moment...', the voice says.");
      start(player, keyboard);
    }
    else
    {
      if (player.getAltarCounter() > 0)
      {          
        System.out.println("'we have no need for your sacrifice at this moment...', the voice says.");
        start(player, keyboard);
      }
      else
      {
        int amount = 0;
        int reward = 0;
        String sacrifice = choices.get(r.nextInt(choices.size()));
        if (sacrifice.equals("health"))
        {
          amount = r.nextInt(5) + 1;
          reward = (amount * 10) + r.nextInt(7);
        }
        else if (sacrifice.equals("experience"))
        {
          amount = r.nextInt(30) + 20;
          reward = (amount * 2) - (8 * player.getLevel()) - 8;
        }
        else if (sacrifice.equals("guard"))
        {
          amount = 1;
          reward = 98;
        }
        else
        {
          amount = r.nextInt(2) + 1;
          reward = (amount * 30) + 8;
        }
        System.out.println("'we request a sacrifice...', the voice says.");
        if (player.getAltarUpgrade())
          System.out.printf("sacrifice: you will lose %d %s for %d money.\n", amount, sacrifice, reward);
        System.out.println("1. do it.");
        System.out.println("2. menu.");
        if (!(player.getAltarUpgrade()))
        {
          System.out.println("3. upgrade altar. (requires 5 upgrades).");
          System.out.println("4. nevermind.");
        }
        else
          System.out.println("3. nevermind.");
        System.out.printf("upgrades: %d.\n", player.getUpgrades());
        System.out.print("enter command: ");
        String command = keyboard.nextLine();
        System.out.println("********************************************************************************");
        if (!(player.getAltarUpgrade()))
        {
          while (!(command.equals("1") || command.equals("2") || command.equals("3") || command.equals("4")))
          {
            System.err.println("error. invalid input.");
            System.out.print("enter command: ");
            command = keyboard.nextLine();
            System.out.println("********************************************************************************");
          }
        }
        else
        {
          while (!(command.equals("1") || command.equals("2") || command.equals("3")))
          {
            System.err.println("error. invalid input.");
            System.out.print("enter command: ");
            command = keyboard.nextLine();
            System.out.println("********************************************************************************");
          }
        }
        if (command.equals("1"))
        {
          System.out.println("'we thank you for your sacrifice...', the voice says.");
          System.out.println();
          player.setAltarCounter(5);
          if (sacrifice.equals("health"))
          {
            System.out.printf("you lose %d health.\n", amount);
            System.out.printf("you are rewarded with %d money.\n", reward);
            player.incrementHealth(-amount);
            while (player.getHealthC() < 0)
              player.incrementHealthC(1);
            player.incrementMoney(reward);
          }
          else if (sacrifice.equals("experience"))
          {
            System.out.printf("you lose %d experience.\n", amount);
            System.out.printf("you are rewarded with %d money.\n", reward);
            player.incrementExperience(-amount);
            player.incrementMoney(reward);
          }
          else if (sacrifice.equals("guard"))
          {
            System.out.printf("you lose 1 guard.\n");
            System.out.printf("you are rewarded with %d money.\n", reward);
            player.incrementGuard(-1);
            player.incrementMoney(98);
          }
          else
          {
            System.out.printf("you lose %d attack.\n", amount);
            System.out.printf("you are rewarded with %d money.\n", reward);
            player.incrementAttack(-amount);
            player.incrementMoney(reward);
          }
          System.out.println();
          System.out.println("returning...");
          start(player, keyboard);
        }
        else if (command.equals("2"))
        {
          menu(player, keyboard);
          altar(player, keyboard);
        }
        else if (command.equals("3"))
        {
          if (player.getAltarUpgrade())
          {
            System.out.println("returning...");
            start(player, keyboard);
          }
          else
          {
            altarUpgrade(player, keyboard);
          }
        }
        else
        {
          System.out.println("returning...");
          start(player, keyboard);
        }
      }
    }
  }
  
  public static void bankUpgrade(Player player, Scanner keyboard)
  {
    if (player.getUpgrades() < 5)
    {
      System.out.println("you need more upgrades.");
      System.out.println("returning...");
      bank(player, keyboard);
    }
    else
    {
      System.out.println("upgrading the bank will eliminate the deposit fee.");
      System.out.println("1. upgrade bank. (requires 5 upgrades)");
      System.out.println("2. menu.");
      System.out.println("3. nevermind.");
      System.out.printf("upgrades: %d.\n", player.getUpgrades());
      System.out.print("enter command: ");
      String command = keyboard.nextLine();
      System.out.println("********************************************************************************");
      if (command.equals("1"))
      {
        player.incrementUpgrades(-5);
        player.incrementUpgradesUsed(5);
        player.setBankUpgrade(true);
        System.out.println("bank upgraded.");
        bank(player, keyboard);
      }
      else if (command.equals("2"))
      {
        menu(player, keyboard);
        bankUpgrade(player, keyboard);
      }
      else if (command.equals("3"))
      {
        System.out.println("returning...");
        bank(player, keyboard);
      }
      else
      {
        System.err.println("error. invalid input.");
        bankUpgrade(player, keyboard);
      }
    }
  }
  
  public static void bank(Player player, Scanner keyboard)
  {
    System.out.println();
    System.out.println("welcome to the world bank.");
    System.out.printf("you have %d money in the bank.\n", player.getBankMoney());
    System.out.printf("you have %d money in your pocket.\n", player.getMoney());
    System.out.println("1. deposit money.");
    System.out.println("2. withdraw money.");
    System.out.println("3. menu.");
    if (!(player.getBankUpgrade()))
    {
      System.out.println("4. upgrade bank. (requires 5 upgrades).");
      System.out.println("5. nevermind.");
      System.out.printf("upgrades: %d.\n", player.getUpgrades());
    }
    else
      System.out.println("4. nevermind.");
    System.out.print("enter command: ");
    String command = keyboard.nextLine();
    System.out.println("********************************************************************************");
    if (command.equals("1"))
    {
      if (player.getMoney() < 10)
      {
        System.out.println("there is a 10 money minimum for deposits.");
        bank(player, keyboard);
      }       
      System.out.print("enter amount of money to deposit in increments of ten (or -1 to cancel): ");
      String toDeposit = keyboard.nextLine();
      System.out.println("********************************************************************************");
      if (toDeposit.equals("-1"))
      {
        System.out.println("returning...");
        bank(player, keyboard);
      }
      else
      {
        boolean goodInput = false;
        int toAdd;
        while (!(goodInput))
        {
          if (toDeposit.equals("-1"))
            break;
          try 
          {
            toAdd = Integer.parseInt(toDeposit);
            if (toAdd % 10 == 0 && toAdd >= 10)
              goodInput = true;
            else
            {
              System.err.println("error. invalid input.");
              System.out.print("enter amount of money to deposit in increments of ten (or -1 to cancel): ");
              toDeposit = keyboard.nextLine();
              System.out.println("********************************************************************************");
            }
          }
          catch (NumberFormatException e)
          {
            System.err.println("error. invalid input.");
            System.out.print("enter amount of money to deposit in increments of ten (or -1 to cancel): ");
            toDeposit = keyboard.nextLine();
            System.out.println("********************************************************************************");
          }
        }
        if (toDeposit.equals("-1"))
        {
          System.out.println("returning...");
          bank(player, keyboard);
        }
        else
        {
          toAdd = Integer.parseInt(toDeposit);
          if (toAdd > player.getMoney())
          {
            System.err.println("you don't have this much money to add.");
            bank(player, keyboard);
          }
          else
          {
            if (!(player.getBankUpgrade()))
            {
              int money = (int) (toAdd * .9);
              System.out.println("there is a 10% deposit fee. is this ok?");
              System.out.printf("1. yes (store %d money).\n", money);
              System.out.println("2. no (return to bank).");
              System.out.print("enter command: ");
              String canAdd = keyboard.nextLine();
              System.out.println("********************************************************************************");
              while (!(canAdd.equals("1") || canAdd.equals("2")))
              {
                System.err.println("error. invalid input.");
                System.out.print("enter command: ");
                canAdd = keyboard.nextLine();
                System.out.println("********************************************************************************");
              }
              if (canAdd.equals("1"))
              {
                System.out.printf("%d money added to the bank.\n", money);
                player.incrementMoney(-toAdd);
                player.incrementBankMoney(money);
                bank(player, keyboard);
              }
              else
              {
                System.out.println("returning...");
                bank(player, keyboard);
              }
            }
            else
            {
              System.out.printf("%d money added to the bank.\n", toAdd);
              player.incrementMoney(-toAdd);
              player.incrementBankMoney(toAdd);
              bank(player, keyboard);
            }  
          }
        }
      }
    }
    else if (command.equals("2"))
    {
      int money = player.getBankMoney();
      if (money == 0)
      {
        System.out.println("you have no money to take out.");
        bank(player, keyboard);
      }
      else if (money < 10)
      {
        System.out.println("there is a 10 money withdraw minimum.");
        bank(player, keyboard);
      }
      System.out.print("enter withdrawal amount or -1 to cancel (10 money withdraw minimum): ");
      String toWithdraw = keyboard.nextLine();
      System.out.println("********************************************************************************");
      
      boolean goodInput = false;
      int toAdd;
      while (!(goodInput))
      {
        if (toWithdraw.equals("-1"))
          break;
        try 
        {
          toAdd = Integer.parseInt(toWithdraw);
          if (toAdd >= 10 && toAdd <= money)
            goodInput = true;
          else
          {
            System.err.println("error. invalid input.");
            System.out.print("enter withdrawal amount or -1 to cancel (10 money withdraw minimum): ");
            toWithdraw = keyboard.nextLine();
            System.out.println("********************************************************************************");
          }
        }
        catch (NumberFormatException e)
        {
          System.err.println("error. invalid input.");
          System.out.print("enter withdrawal amount or -1 to cancel (10 money withdraw minimum): ");
          toWithdraw = keyboard.nextLine();
          System.out.println("********************************************************************************");
        }
      }
      if (toWithdraw.equals("-1"))
      {
        System.out.println("returning...");
        bank(player, keyboard);
      }
      else
      {
        toAdd = Integer.parseInt(toWithdraw);
        System.out.printf("%d money taken from the bank.\n", toAdd);
        player.incrementBankMoney(-toAdd);
        player.incrementMoney(toAdd);
        bank(player, keyboard);
      }
    }
    else if (command.equals("3"))
    {
      menu(player, keyboard);
      bank(player, keyboard);
    }
    else if (command.equals("4"))
    {
      if (!(player.getBankUpgrade()))
        bankUpgrade(player, keyboard);
      else
      {
        System.out.println("returning...");
        start(player, keyboard);
      }
    }
    else if (command.equals("5"))
    {
      if (!(player.getBankUpgrade()))
      {
        System.out.println("returning...");
        start(player, keyboard);
      }
      else
      {
        System.err.println("error. invalid input.");
        bank(player, keyboard);
      }
    }
    else
    {
      System.err.println("error. invalid input.");
      bank(player, keyboard);
    }
  }
}
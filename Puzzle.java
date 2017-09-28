package textGameOne;
import java.util.*;
public class Puzzle {
  
  //field(s)-----------------------------------------------------
  private String[] solution;
  private String[] playerSolution = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
  private ArrayList<String> numbers = new ArrayList<String>();
  
  
  //constructor(s)--------------------------------------------------------
  Puzzle()
  {
    solution = createSolution();
    startSolution();
  }
  Puzzle(int i)
  {
    solution = createSolution();
    startSolution(16);
  }
  
  //getter(s)--------------------------------------------------------------
  public String[] getSolution()
  {
    return solution;
  }
  public ArrayList<String> getNumbers()
  {
    return numbers;
  }
  public String[] getPlayerSolution()
  {
    return playerSolution;
  }
  public ArrayList<String> getSpaces()
  {
    ArrayList<String> spaces = new ArrayList<>();
    for (int i = 0; i < playerSolution.length; i++)
    {
      if (playerSolution[i] == "0")
      {
        spaces.add(Integer.toString(i+1));
      }
    }
    return spaces;
  }
  //setter(s)--------------------------------------------------------------
  public void setSolution(String[] s)
  {
    solution = s;
  }
  public void setPlayerSolution(String num, int pos)
  {
    playerSolution[pos] = num;
  }
  //method(s)--------------------------------------------------------
  
  public void printPuzzle(String[] answers)
  {
    System.out.println("-------------------");
    if (answers[0] == "0")
      System.out.printf("|   ");
    else 
      System.out.printf("| %s ", answers[0]);
    if (answers[1] == "0")
      System.out.printf("|   |");
    else 
      System.out.printf("| %s |", answers[1]);
    if (answers[2] == "0")
      System.out.printf("|   ");
    else 
      System.out.printf("| %s ", answers[2]);
    if (answers[3] == "0")
      System.out.printf("|   |\n");
    else 
      System.out.printf("| %s |\n", answers[3]);
    if (answers[4] == "0")
      System.out.printf("|   ");
    else 
      System.out.printf("| %s ", answers[4]);
    if (answers[5] == "0")
      System.out.printf("|   |");
    else 
      System.out.printf("| %s |", answers[5]);
    if (answers[6] == "0")
      System.out.printf("|   ");
    else 
      System.out.printf("| %s ", answers[6]);
    if (answers[7] == "0")
      System.out.printf("|   |\n");
    else 
      System.out.printf("| %s |\n", answers[7]);
    System.out.println("-------------------");
    if (answers[8] == "0")
      System.out.printf("|   ");
    else 
      System.out.printf("| %s ", answers[8]);
    if (answers[9] == "0")
      System.out.printf("|   |");
    else 
      System.out.printf("| %s |", answers[9]);
    if (answers[10] == "0")
      System.out.printf("|   ");
    else 
      System.out.printf("| %s ", answers[10]);
    if (answers[11] == "0")
      System.out.printf("|   |\n");
    else 
      System.out.printf("| %s |\n", answers[11]);
    if (answers[12] == "0")
      System.out.printf("|   ");
    else 
      System.out.printf("| %s ", answers[12]);
    if (answers[13] == "0")
      System.out.printf("|   |");
    else 
      System.out.printf("| %s |", answers[13]);
    if (answers[14] == "0")
      System.out.printf("|   ");
    else 
      System.out.printf("| %s ", answers[14]);
    if (answers[15] == "0")
      System.out.printf("|   |\n");
    else 
      System.out.printf("| %s |\n", answers[15]);
    System.out.println("-------------------");
  }
  public void printPuzzle()
  {
    printPuzzle(playerSolution);
  }
  public String[] createSolution()
  {
    String[][] solutions = {
     {"4", "2", "3", "1", "3", "1", "4", "2", "2", "3", "1", "4", "1", "4", "2", "3"},
     {"1", "3", "4", "2", "4", "2", "1", "3", "2", "4", "3", "1", "3", "1", "2", "4"},
     {"1", "4", "3", "2", "3", "2", "1", "4", "2", "3", "4", "1", "4", "1", "2", "3"},
     {"1", "2", "3", "4", "4", "3", "2", "1", "2", "1", "4", "3", "3", "4", "1", "2"},
     {"3", "4", "2", "1", "1", "2", "3", "4", "2", "1", "4", "3", "4", "3", "1", "2"},
     {"4", "1", "3", "2", "3", "2", "4", "1", "1", "3", "2", "4", "2", "4", "1", "3"},
     {"2", "3", "4", "1", "4", "1", "2", "3", "3", "4", "1", "2", "1", "2", "3", "4"},
     {"4", "1", "3", "2", "2", "3", "1", "4", "1", "4", "2", "3", "3", "2", "4", "1"},
     {"2", "4", "1", "3", "1", "3", "4", "2", "3", "1", "2", "4", "4", "2", "3", "1"}
    };
    int x = solutions.length;
    Random r = new Random();
    return solutions[r.nextInt(x)];
  }
  public String getNumber()
  {
    ArrayList<String> num = getList();
    
    if (num.size() < 1) return "false";
    else 
    {
      Random r = new Random();
      String n = num.get(r.nextInt(num.size()));
      numbers.add(n);
      return n;
    }
  }
  public void startSolution(int n)
  {
    ArrayList<String> a = new ArrayList<String>();
    for (int i = 0; i < 16; i++)
      a.add(Integer.toString(i));
    Collections.shuffle(a);
    for (int j = 0; j < n; j++)
      playerSolution[Integer.parseInt(a.get(j))] = solution[Integer.parseInt(a.get(j))];
  }
  public void startSolution()
  {
    startSolution(4);
  }
  public ArrayList<String> getList()
  {
    ArrayList<String> num = new ArrayList<String>();
    for (int a = 0; a < solution.length; a++)
    { num.add(solution[a]);
    }
    for (int c = 0; c < playerSolution.length; c++)
    { if (num.contains(playerSolution[c])) num.remove(playerSolution[c]);
    } 
    for (int e = 0; e < numbers.size(); e++)
      if (num.contains(numbers.get(e))) num.remove(numbers.get(e));
    return num;
  }
  public void printExamplePuzzle()
  {
    System.out.println();
    System.out.println("space guide: ");
    System.out.println("-------------------");
    System.out.println("| 1 | 2 || 3 | 4 |");
    System.out.println("| 5 | 6 || 7 | 8 |");
    System.out.println("-------------------");
    System.out.println("| 9 | 10|| 11| 12|");
    System.out.println("| 13| 14|| 15| 16|");
    System.out.println("-------------------");
  }
  public ArrayList<String> printSelectSpace(String[] answers)
  {
    boolean color = Game.color;
    ArrayList<String> list = new ArrayList<>();
    int count = 0;
    ArrayList<Integer> row1 = new ArrayList<>();
    ArrayList<Integer> row2 = new ArrayList<>();
    ArrayList<Integer> row3 = new ArrayList<>();
    ArrayList<Integer> row4 = new ArrayList<>();
    System.out.println("-------------------");
    if (answers[0] == "0")
    {
      list.add(Integer.toString(count));
      System.out.printf("|");
      if (count < 10)
      {
        if (color)
          System.err.printf(" %d ", count);
        else
        {
          System.out.printf("   ");
          row1.add(count);
        }
      }
      else
      {
        if (color)
          System.err.printf("%d ", count);
        else
        {
          System.out.printf("   ");
          row1.add(count);
        }
      }
      count++;
    }
    else 
      System.out.printf("| %s ", answers[0]);
    if (answers[1] == "0")
    {
      list.add(Integer.toString(count));
      System.out.printf("|");
      if (count < 10)
      {
        if (color)
          System.err.printf(" %d ", count);
        else
        {
          System.out.printf("   ");
          row1.add(count);
        }
      }
      else
      {
        if (color)
          System.err.printf("%d ", count);
        else
        {
          System.out.printf("   ");
          row1.add(count);
        }
      }
      count++;
      System.out.printf("|");
    }
    else 
      System.out.printf("| %s |", answers[1]);
    if (answers[2] == "0")
    {
      list.add(Integer.toString(count));
      System.out.printf("|");
      if (count < 10)
      {
        if (color)
          System.err.printf(" %d ", count);
        else
        {
          System.out.printf("   ");
          row1.add(count);
        }
      }
      else
      {
        if (color)
          System.err.printf("%d ", count);
        else
        {
          System.out.printf("   ");
          row1.add(count);
        }
        
      }
      count++;
    }
    else 
      System.out.printf("| %s ", answers[2]);
    if (answers[3] == "0")
    {
      list.add(Integer.toString(count));
      System.out.printf("|");
      if (count < 10)
      {
        if (color)
          System.err.printf(" %d ", count);
        else
        {
          System.out.printf("   ");
          row1.add(count);
        }
      }
      else
      {
        if (color)
          System.err.printf("%d ", count);
        else
        {
          System.out.printf("   ");
          row1.add(count);
        }
      }
      count++;
      System.out.printf("|");
    }
    else 
      System.out.printf("| %s |", answers[3]);
    if (!(color) && row1.size() > 0)
    {
      for (int element = 0; element < row1.size()-1; element++)
        System.out.print(row1.get(element) + ", ");
      System.out.println(row1.get(row1.size()-1));
    }
    else
      System.out.println();
    if (answers[4] == "0")
    {
      list.add(Integer.toString(count));
      System.out.printf("|");
      if (count < 10)
      {
        if (color)
          System.err.printf(" %d ", count);
        else
        {
          System.out.printf("   ");
          row2.add(count);
        }
      }
      else
      {
        if (color)
          System.err.printf("%d ", count);
        else
        {
          System.out.printf("   ");
          row2.add(count);
        }
      }
      count++;
    }
    else 
      System.out.printf("| %s ", answers[4]);
    if (answers[5] == "0")
    {
      list.add(Integer.toString(count));
      System.out.printf("|");
      if (count < 10)
      {
        if (color)
          System.err.printf(" %d ", count);
        else
        {
          System.out.printf("   ");
          row2.add(count);
        }
      }
      else
      {
        if (color)
          System.err.printf("%d ", count);
        else
        { 
          System.out.printf("   ");
          row2.add(count);  
        }
      }
      count++;
      System.out.printf("|");
    }
    else 
      System.out.printf("| %s |", answers[5]);
    if (answers[6] == "0")
    {
      list.add(Integer.toString(count));
      System.out.printf("|");
      if (count < 10)
      {
        if (color)
          System.err.printf(" %d ", count);
        else
        {
          System.out.printf("   ");
          row2.add(count);
        }
      }
      else
      {
        if (color)
          System.err.printf("%d ", count);
        else
        {
          System.out.printf("   ");
          row2.add(count);
        }
      }
      count++;
    }
    else 
      System.out.printf("| %s ", answers[6]);
    if (answers[7] == "0")
    {
      list.add(Integer.toString(count));
      System.out.printf("|");
      if (count < 10)
      {
        if (color)
          System.err.printf(" %d ", count);
        else
        {
          System.out.printf("   ");
          row2.add(count);
        }
      }
      else
      {
        if (color)
          System.err.printf("%d ", count);
        else
        {
          System.out.printf("   ");
          row2.add(count);
        }
      }
      count++;
      System.out.printf("|");
    }
    else 
      System.out.printf("| %s |", answers[7]);
    if (!(color) && row2.size() > 0)
    {
      for (int element = 0; element < row2.size()-1; element++)
        System.out.print(row2.get(element) + ", ");
      System.out.println(row2.get(row2.size()-1));
    }
    else
      System.out.println();
    System.out.println("-------------------");
    if (answers[8] == "0")
    {
      list.add(Integer.toString(count));
      System.out.printf("|");
      if (count < 10)
      {
        if (color)
          System.err.printf(" %d ", count);
        else
        {
          System.out.printf("   ");
          row3.add(count);
        }
      }
      else
      {
        if (color)
          System.err.printf("%d ", count);
        else
        {
          System.out.printf("   ");
          row3.add(count);
        }
      }
      count++;
    }
    else 
      System.out.printf("| %s ", answers[8]);
    if (answers[9] == "0")
    {
      list.add(Integer.toString(count));
      System.out.printf("|");
      if (count < 10)
      {
        if (color)
          System.err.printf(" %d ", count);
        else
        {
          System.out.printf("   ");
          row3.add(count);
        }
      }
      else
      {
        if (color)
          System.err.printf("%d ", count);
        else
        {
          System.out.printf("   ");
          row3.add(count);
        }
      }
      count++;
      System.out.printf("|");
    }
    else 
      System.out.printf("| %s |", answers[9]);
    if (answers[10] == "0")
    {
      list.add(Integer.toString(count));
      System.out.printf("|");
      if (count < 10)
      {
        if (color)
          System.err.printf(" %d ", count);
        else
        {
          System.out.printf("   ");
          row3.add(count);
        }
      }
      else
      {
        if (color)
          System.err.printf("%d ", count);
        else
        {
          System.out.printf("   ");
          row3.add(count);
        }
      }
      count++;
    }
    else 
      System.out.printf("| %s ", answers[10]);
    if (answers[11] == "0")
    {
      list.add(Integer.toString(count));
      System.out.printf("|");
      if (count < 10)
      {
        if (color)
          System.err.printf(" %d ", count);
        else
        { 
          System.out.printf("   ");
          row3.add(count); 
        }
      }
      else
      {
        if (color)
          System.err.printf("%d ", count);
        else
        {
          System.out.printf("   ");
          row3.add(count);
        }
      }
      count++;
      System.out.printf("|");
    }
    else 
      System.out.printf("| %s |", answers[11]);
    if (!(color) && row3.size() > 0)
    {
      for (int element = 0; element < row3.size()-1; element++)
        System.out.print(row3.get(element) + ", ");
      System.out.println(row3.get(row3.size()-1));
    }
    else
      System.out.println();
    if (answers[12] == "0")
    {
      list.add(Integer.toString(count));
      System.out.printf("|");
      if (count < 10)
      {
        if (color)
          System.err.printf(" %d ", count);
        else
        {
          System.out.printf("   ");
          row4.add(count);
        }
      }
      else
      {
        if (color)
          System.err.printf("%d ", count);
        else
        {
          System.out.printf("   ");
          row4.add(count);
        }
      }
      count++;
    }
    else 
      System.out.printf("| %s ", answers[12]);
    if (answers[13] == "0")
    {
      list.add(Integer.toString(count));
      System.out.printf("|");
      if (count < 10)
      {
        if (color)
          System.err.printf(" %d ", count);
        else
        {
          System.out.printf("   ");
          row4.add(count);
        }
      }
      else
      {
        if (color)
          System.err.printf("%d ", count);
        else
        {
          System.out.printf("   ");
          row4.add(count);
        }
      }
      count++;
      System.out.printf("|");
    }
    else 
      System.out.printf("| %s |", answers[13]);
    if (answers[14] == "0")
    {
      list.add(Integer.toString(count));
      System.out.printf("|");
      if (count < 10)
      {
        if (color)
          System.err.printf(" %d ", count);
        else
        {
          System.out.printf("   ");
          row4.add(count);
        }
      }
      else
      {
        if (color)
          System.err.printf("%d ", count);
        else
        {
          System.out.printf("   ");
          row4.add(count);
        }
      }
      count++;
    }
    else 
      System.out.printf("| %s ", answers[14]);
    if (answers[15] == "0")
    {
      list.add(Integer.toString(count));
      System.out.printf("|");
      if (count < 10)
      {
        if (color)
          System.err.printf(" %d ", count);
        else
        {
          System.out.printf("   ");
          row4.add(count);
        }
      }
      else
      {
        if (color)
          System.err.printf("%d ", count);
        else
        {
          System.out.printf("   ");
          row4.add(count); 
        }
      }
      count++;
      System.out.printf("|");
    }
    else 
      System.out.printf("| %s |", answers[15]);
    if (!(color) && row4.size() > 0)
    {
      for (int element = 0; element < row4.size()-1; element++)
        System.out.print(row4.get(element) + ", ");
      System.out.println(row4.get(row4.size()-1));
    }
    else
      System.out.println();
    System.out.println("-------------------");
    return list;
  }
  public ArrayList<String> printSelectSpace()
  {
    return printSelectSpace(playerSolution);
    
  }
  public boolean checkComplete()
  {
    boolean check = true;
    for (String element : playerSolution)
      if (element.equals("0")) check = false;
    return check;
  }
  
  
}
package textGameOne;
public class Other
{
  //field(s)-------------------------------------------------------------
  private boolean altar;
  private boolean bank;
  private int bankFunds;
  private int altarCounter;
  
  //constructor(s)--------------------------------------------------------
  Other()
  {
  }
  Other(boolean b)
  {
    altar = true;
    bank = true;
  }
  
  //getters---------------------------------------------------------------
  public boolean getAltar()
  {
    return altar;
  }
  public boolean getBank()
  {
    return bank;
  }
  public int getBankFunds()
  {
    return bankFunds;
  }
  public int getAltarCounter()
  {
    return altarCounter;
  }
  //setters---------------------------------------------------------------
  public void setAltar(boolean b)
  {
    altar = b;
  }
  public void setBank(boolean b)
  {
    bank = b;
  }
  public void setBankFunds(int i)
  {
    bankFunds = i;
  }
  public void incrementBankFunds(int i)
  {
    bankFunds += i;
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
  
}
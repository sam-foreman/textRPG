public class Move {
  
  //field(s)---------------------------------------------------------------------------------
  private boolean charge;
  private boolean sap;
  private boolean steal;
  private boolean stun;
  private int chargeCooldown;
  private int sapCooldown;
  private int stealCooldown;
  private int stunCooldown;
  private int chargeUse;
  private int sapUse;
  private int stealUse;
  private int stunUse;
  
  //constructor(s)-------------------------------------------------------------------------------
  
  Move()
  {
  }
  
  Move(String debug)
  {
    charge = true;
    sap = true;
    steal = true;
    stun = true;
   // sapCooldown = 4;
   // stunCooldown = 2;
  }
  
  //getter(s)----------------------------------------------------------------------------------
  
  public boolean getCharge()
  {
    return charge;
  }
  public boolean getSap()
  {
    return sap;
  }
  public boolean getSteal()
  {
    return steal;
  }
  public boolean getStun()
  {
    return stun;
  }
  public int getChargeCooldown()
  {
    return chargeCooldown;
  }
  public int getSapCooldown()
  {
    return sapCooldown;
  }
  public int getStealCooldown()
  {
    return stealCooldown;
  }
  public int getStunCooldown()
  {
    return stunCooldown;
  }
  public int getChargeUse()
  {
    return chargeUse;
  }
  public int getSapUse()
  {
    return sapUse;
  }
  public int getStealUse()
  {
    return stealUse;
  }
  public int getStunUse()
  {
    return stunUse;
  }
  
  
  //setter(s)----------------------------------------------------------------------------------
  
  public void setCharge(boolean b)
  {
    charge = b;
  }
  public void setSap(boolean b)
  {
    sap = b;
  }
  public void setSteal(boolean b)
  {
    steal = b;
  }
  public void setStun(boolean b)
  {
    stun = b;
  }
  public void setChargeCooldown(int c)
  {
    chargeCooldown = c;
  }
  public void setSapCooldown(int c)
  {
    sapCooldown = c;
  }
  public void setStealCooldown(int c)
  {
    stealCooldown = c;
  }
  public void setStunCooldown(int c)
  {
    stunCooldown = c;
  }
  public void incrementChargeCooldown(int c)
  {
    chargeCooldown += c;
    if (chargeCooldown < 0)
      chargeCooldown = 0;
  }
  public void incrementSapCooldown(int c)
  {
    sapCooldown += c;
    if (sapCooldown < 0)
      sapCooldown = 0;
  }
  public void incrementStealCooldown(int c)
  {
    stealCooldown += c;
    if (stealCooldown < 0)
      stealCooldown = 0;
  }
  public void incrementStunCooldown(int c)
  {
    stunCooldown += c;
    if (stunCooldown < 0)
      stunCooldown = 0;
  }
  public void setChargeUse(int i)
  {
    chargeUse = i;
  }
  public void setSapUse(int i)
  {
    sapUse = i;
  }
  public void setStealUse(int i)
  {
    stealUse = i;
  }
  public void setStunUse(int i)
  {
    stunUse = i;
  }
  public void incrementChargeUse(int i)
  {
    chargeUse += i;
    if (chargeUse < 0)
      chargeUse = 0;
  }
  public void incrementSapUse(int i)
  {
    sapUse += i;
    if (sapUse < 0)
      sapUse = 0;
  }
  public void incrementStealUse(int i)
  {
    stealUse += i;
    if (stealUse < 0)
      stealUse = 0;
  }
  public void incrementStunUse(int i)
  {
    stunUse += i;
    if (stunUse < 0)
      stunUse = 0;
  }
  //method(s)----------------------------------------------------------------------------------
  public void incrementCooldown()
  {
    incrementChargeCooldown(-1);
    incrementSapCooldown(-1);
    incrementStealCooldown(-1);
    incrementStunCooldown(-1);
  }
  public boolean checkMove()
  {
    if (!charge && !sap && !steal && !stun)
      return false;
    return true;
  }
}

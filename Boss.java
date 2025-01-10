public class Boss extends Adventurer{
  private int coreEnergy, coreEnergyMax, moltenLava;
  public Boss(String name, int hp){
    super(name,hp);
    coreEnergyMax = 25;
    coreEnergy = coreEnergyMax/2;
  }
  public Boss(String name){
    this(name,50);
  }
  public Boss(){
    this("Crater Dave");
  }

  public String getSpecialName() {
    return "Core Energy";
  }
  public int setSpecial() {
    return coreEnergy;
  }
  public void setSpecial(int n){
      coreEnergy = n;
  }
  public int getSpecialMax(){
    return coreEnergyMax;
  }
  public int getSpecial(){
    return coreEnergy;
  }
  public String getResourceName(){
    return "Molten Lava";
  }
  public void setResource(int n){
    if (n >= 0){
      moltenLava = n;
    }
  }
  public String attack (Adventurer other){
    int choice = (int)(Math.random() * 4);
    if (choice == 0){
      other.applyDamage(3);
      return this + " throws space junk at " + other + ", dealing 3 points of damage.";
    } else if(choice == 1){
      other.applyDamage(3);
      return this + " throws a rock at " + other + ", dealing 3 points of damage.";
    } else if(choice == 2){
      other.applyDamage(3);
      return this + " breathes on " + other + ", dealing 3 points of damage.";
    } else{
      other.applyDamage(0);
      return this + " tries to attack " + other + ", but misses.";
    }
  }
  public String specialAttack(Adventurer other){
    if (coreEnergy > 5){
      other.applyDamage(6);
      cosmicEnergy -= 5;
      return this + " uses 5 Core Energy and attacks " + other + ", calling upon the asteroid spirits for a meteor shower, dealing 6 damage.";
    } else{
      return this + " is too tired and does not have enough Core Energy to attack."
    }
  }
  public String support(Adventurer other){
    return "";
  }
  public String support(){
    return "";
  }
}

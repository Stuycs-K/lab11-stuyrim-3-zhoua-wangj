public class Boss extends Adventurer{
  private int coreEnergy, coreEnergyMax, moltenLava;
  public Boss(String name, int hp){
    super(name,hp);
    coreEnergyMax = 25;
    coreEnergy = coreEnergyMax/2;
    moltenLava = 15;
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
  public void setSpecial(int n){
    if (n >= 0 && n <= getSpecialMax()) {
      coreEnergy = n;
    }
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
    if (n >= 0) {
      moltenLava = n;
    }
  }
  public int getResource() {
    return moltenLava;
  }
  public String attack (Adventurer other){
    double choice = Math.random();
    if (choice < 0.5) {
      other.applyDamage(5);
      return this + " breaks off a piece of itself and throws it at " + other + " dealing 5 points of damage";
    }
    else if (choice < 0.7) {
      other.applyDamage(7);
      return this + " grazes " + other + " and burns them, dealing 7 points of damage.";
    }
    else {
      return this + " tries to attack " + other + ", but misses.";
    }
  }
  public String specialAttackOctopus(Adventurer other){
    if (coreEnergy > 5){
      other.applyDamage(6);
      this.setSpecial(this.getSpecial()-5);
      int slicedLegs = (int)(Math.random()*Math.max((other.getResource()-3),1));
      other.setResource(other.getResource()-slicedLegs);
      return this + " uses 5 Core Energy and attacks " + other + ", calling upon the asteroid spirits for a meteor shower, dealing 6 damage."
        + " They also damaged " + other + " by slicing off " +  slicedLegs + " legs. Ouch!";
    } else{
      return this + " is too tired and does not have enough Core Energy to attack.";
    }
  }
  public String specialAttack(Adventurer other){
    if (coreEnergy > 5){
      other.applyDamage(6);
      this.setSpecial(this.getSpecial()-5);
      other.makeWeakened();
      other.setResource(other.getResource()-2);
      return this + " uses 5 Core Energy and attacks " + other + ", calling upon the asteroid spirits for a meteor shower, dealing 6 damage."
        + " They also weakened " + other + " for their next attack and stole 2 " + other.getSpecialName() + " from " + other;
    } else{
      return this + " is too tired and does not have enough Core Energy to attack.";
    }
  }
  public String support(Adventurer other){
    if (other.equals(this)) {
      return support();
    }
    if (moltenLava > 0){
      other.setHP(other.getHP() + 3);
      this.setResource(this.getResource()-1);
      return this + " supported " + other + " by throwing magical molten lava on " + other + ", restoring its HP by 3.";
    } else{
      return this + " has angered the spirits. The spirits have refused to enchant more molten lava. " + this + " has no more molten lava to use.";
  }
}

  public String support(){
    if (moltenLava > 0){
      this.setHP(this.getHP() + 5);
      this.setResource(this.getResource()-1);
      return this + " supported itself by drinking some of the magical molten lava, restoring 5 HP.";
    } else{
      return this + " was greedy and drank all of the lava." + this + " does not have enough lava to support itself.";
    }
  }
}

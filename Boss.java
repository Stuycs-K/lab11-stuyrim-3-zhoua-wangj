public class Boss extends Adventurer{
  private int coreEnergy, coreEnergyMax, moltenLava, accelerator, acceleratorEnergy;
  public String type = "boss";
  public Boss(String name, int hp){
    super(name,hp);
    coreEnergyMax = 25;
    coreEnergy = coreEnergyMax/2;
    moltenLava = 5;
    accelerator = 1;
  }
  public Boss(String name){
    this(name,50);
  }
  public Boss(){
    this("Crater Dave");
  }
  public String getType() {
    return type;
  }
  public int getHealer() {
    return accelerator;
  }
  public String getHealerName() {
    return "Particle Accelerator";
  }
  public String getSpecialName() {
    return "Core Energy";
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
<<<<<<< HEAD
  //public int getNanoBots() {
    //return nanoBots;
  //}
=======
>>>>>>> a5312e783290adf342650cd056578858ad9d1177
  public void setResource(int n){
    if (n >= 0){
      moltenLava = n;
    }
  }
  public int getResource() {
    return moltenLava;
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
  public String specialAttack(Octopus other){
    if (coreEnergy > 5){
      other.applyDamage(6);
      this.setSpecial(this.getSpecial()-5);
      other.setResource(other.getResource()-1);
      return this + " uses 5 Core Energy and attacks " + other + ", calling upon the asteroid spirits for a meteor shower, dealing 6 damage."
        + " They also weaked " + other + " by slicing off one leg. Ouch!";
    } else{
      return this + " is too tired and does not have enough Core Energy to attack.";
    }
  }
  public String specialAttack(Adventurer other){
    if (coreEnergy > 5){
      other.applyDamage(6);
      this.setSpecial(this.getSpecial()-5);
      other.makeWeakened();
      return this + " uses 5 Core Energy and attacks " + other + ", calling upon the asteroid spirits for a meteor shower, dealing 6 damage."
        + " They also weakened" + other + " for their next attack";
    } else{
      return this + " is too tired and does not have enough Core Energy to attack.";
    }
  }
  public String support(Adventurer other){
    if (moltenLava > 0){
      other.setHP(other.getHP() + 3);
      moltenLava--;
      return this + " supported " + other + " by throwing magical molten lava on " + other + ", restoring its HP by 3.";
    } else{
      return this + " has angered the spirits. The spirits have refused to enchant more molten lava. " + this + " has no more molten lava to use.";
  }
}

  public String support(){
    if (moltenLava > 0){
      this.setHP(this.getHP() + 5);
      moltenLava -= 2;
      return this + " supported itself by drinking the magical molten lava, restoring 5 HP.";
    } else{
      return this + " was greedy and drank all of the lava." + this + " does not have enough lava to support itself.";
    }
  }

  public String recharge() {
    return "";
  }
}

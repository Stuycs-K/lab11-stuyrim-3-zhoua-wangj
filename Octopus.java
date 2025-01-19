public class Octopus extends Adventurer {
  private int venom, venomMax, legs, legsMax, regenPotion;
  public String type = "octopus";
  public Octopus(String name, int hp) {
    super(name,hp);
    venomMax = 25;
    venom = venomMax/2;
    legsMax = 8;
    legs = legsMax;
    regenPotion = 3;
  }
  public Octopus(String name) {
    this(name,25);
  }
  public Octopus() {
    this("Rachel");
  }
  public String getType() {
    return type;
  }
  public String getSpecialName() {
    return "Toxic Venom";
  }
  public int setSpecial() {
    return venom;
  }
  public int getLegs() {
    return legs;
  }
  public void setLegs(int n) {
    legs = getLegs() + 1;
  }
  public void setSpecial(int n) {
    if (n > 0) {
      venom = n;
    }
  }
  public int getSpecialMax() {
    return venomMax;
  }
  public int getSpecial() {
    return venom;
  }
  public int getResource() {
    return legs;
  }
  public String getResourceName() {
    return "Legs";
  }
  public void setResource(int n) {
    legs  = n;
  }
  public int getResourceMax(int n) {
    return legsMax;
  }
  public int getHealer() {
    return regenPotion;
  }
  public String getHealerName() {
    return "Regen Potion";
  }
  public void setHealer(int n) {
    regenPotion = n;
  }
  public String attack (Adventurer other) {
    if (legs == 0) {
      return this + " cannot attack " + other + " because it has no more legs.";
    }
    int damage = (int)(Math.random()*legs)+1;
    if (Math.random() > 0.9) {
      return this + " missed their attack! They dealt 0 HP of damage.";
    }
    other.applyDamage(damage);
    return this + " kicked " + other + " with " + damage + " legs, dealing " + damage + " points of damage";
  }
  public String specialAttack(Adventurer other) {
    if (getSpecial() < 10) {
      return this + " doesn't have enough venom to attack";
    }
    int damage = 8;
    int resource = other.getResource();
    return this + " used their " + this.getSpecialName() + ", dealing 10 HP damage and poisoned " + other + "'s" + other.getResourceName()
      + other + " is now temporarily blinded, skipping" + other + "'s turn.";
  }
  public String suffocate(Adventurer other) {
    if (this.getResource() < 4) {
      return this + " cannot suffocate " + other + " because " + this + " only has " + this.getResource() + " legs.";
    }
    if (this.getHP() < 6) {
      return this + " cannot suffocate " + other + " because " + this + " only has " + this.getHP() + " HP";
    }
    int damage = getResource();
    other.applyDamage(damage);
    other.makeWeakened();
    return this + " used " + damage + " legs to suffocate " + other + ", dealing " + damage + " points of damage." + this + " also weakened " + other + " for their next attack.";
  }
  public String support(Adventurer other) {
    return this + " supported " + other + " by giving ";
  }
  public String support() {
    int heal = (int)(Math.random()*3)+3;
    return this + " swims away to heal themself. " + this + " restores " + heal + " HP.";
  }
  public String regen() {
    if (this.getResource() < 8) {
      regenPotion = regenPotion-1;
      return this + " uses a regeneration potion, regrowing one leg.";
    }
    return this + " already has eight legs";
  }
}

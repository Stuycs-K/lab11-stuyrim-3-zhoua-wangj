public class Octopus extends Adventurer {
  private int venom, venomMax, legs, legsMax, regenPotion;
  public String type = "octopus";
  public Octopus(String name, int hp) {
    super(name,hp);
    venomMax = 30;
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
  public String attack (Adventurer other) {
    if (legs == 0) {
      return this + " cannot attack " + other + " because it has no more legs.";
    }
    int damage = (int)(Math.random()*legs)+1;
    if (Math.random() > 0.9) {
      return this + " missed their attack! They dealt 0 HP of damage.";
    }
    if (this.getResource()>4 && Math.random()>0.7) {
      return suffocate(other);
    }
    other.applyDamage(damage);
    return this + " kicked " + other + " with " + damage + " legs, dealing " + damage + " points of damage";
  }
  public String specialAttack(Adventurer other) {
    if (this.getSpecial() >= 10 && Math.random() > 0.5) {
      return this.venom(other);
    }
    else if (this.getResource() >= 4) {
      return this.suffocate(other);
    }
    else {
      return this + " does not have enough " + getSpecialName() + " or " + getResourceName() + " to special attack " + other + " instead, " + this.attack(other);
    }
  }
  public String venom(Adventurer other) {
    if (getSpecial() < 10) {
      return this + " doesn't have enough venom to attack";
    }
    int damage = 10;
    other.applyDamage(damage);
    return this + " used their " + this.getSpecialName() + ", dealing 10 HP damage and poisoned " + other + " is then blinded for a turn and " + this.attack(other);
  }
  public String suffocate(Adventurer other) {
    if (this.getResource() < 4) {
      return this + " cannot suffocate " + other + " because " + this + " only has " + this.getResource() + " legs.";
    }
    int damage = getResource();
    other.applyDamage(damage);
    other.makeWeakened();
    return this + " used " + damage + " legs to suffocate " + other + ", dealing " + damage + " points of damage." + this + " also weakened " + other + " for their next attack.";
  }
  public String support(Adventurer other) {
    int heal = (int)(Math.random()*3)+3;
    return this + " supported " + other + " by shielding them, allowing " + other + "to heal " + heal + " HP.";
  }
  public String support() {
    int heal = (int)(Math.random()*3)+3;
    return this + " swims away to heal themself. " + this + " restores " + heal + " HP.";
  }
}

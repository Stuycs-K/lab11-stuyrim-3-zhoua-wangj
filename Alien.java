public class Alien extends Adventurer{
  private int cosmicEnergy, cosmicEnergyMax, microbes;
  public String type = "alien";

  public Alien(String name, int hp){
    super(name, hp);
    cosmicEnergyMax = 25;
    cosmicEnergy = cosmicEnergyMax/2;
    microbes = 7;
  }
  public Alien(String name){
    this(name, 20);
  }
  public Alien(){
    this("Zarg");
  }
  public String getType() {
    return type;
  }
  public String getSpecialName(){
    return "Cosmic Energy";
  }
  public int getSpecial(){
    return cosmicEnergy;
  }
  public int getSpecialMax() {
    return cosmicEnergyMax;
  }
  public void setSpecial(int n){
    if (n <= this.getSpecialMax() && n >= 0) {
      cosmicEnergy = n;
    }
  }
  public String getResourceName(){
    return "Microbes";
  }
  public void setResource(int n){
    if (n>=0){
      microbes = n;
    }
  }
  public int getResource() {
    return microbes;
  }
  public String attack(Adventurer other){
    //kicking deals 4 points, punching deals 3 points, missing deals zero, 1/3 chance each
    int choice = (int)(Math.random()*3);
    int weaker = 0;
    //if weakened, 70% chance of missing instead of 33%. reduce attack HP by 1
    if (this.getWeakened() == true) {
      if (Math.random()<0.7) {
        choice = 0;
        weaker = 1;
        this.resetWeakened();
      }
    }
    if (choice == 0) {
      int damage = 3-weaker;
      other.applyDamage(3);
      return this + " attacked " +  other + " by kicking, dealing " + damage + " points of damage";
    }
    if (choice == 1) {
      int damage = 2-weaker;
      other.applyDamage(2);
      return this + " attacked " + other + " by punching, dealing " + damage + " points of damage";
    }
    else {
      return this + " attacked " + other + " and missed, dealing zero damage.";
    }
  }
  public String specialAttack(Adventurer other){
    if (this.getWeakened() == true){
      return this + " is weakened and cannot conduct a special attack. Instead, " + this.attack(other);
    }
    if (this.getSpecial() >= 5){
      other.applyDamage(4);
      this.setSpecial(this.getSpecial()-5);
      return this + " used Cosmic Blast on " + other + ", blasting cosmic energy from its eye and dealing 4 points of damage and consuming 5 cosmic energy.";
    } else{
      return this + " does not have enough cosmic energy for a special attack. Instead, " + this.attack(other);
    }
  }
  public String support(){
    if (microbes > 0){
      this.setHP(this.getHP() + 4);
      this.setSpecial(this.getSpecial() + 3);
      this.setResource(this.getResource()-1);
      return this + " consumed microbes, restoring 4 HP and 3 cosmic energy.";
    } else{
      return this + " has no microbes left to consume.";
    }
  }
  public String support(Adventurer other){
    if (other.equals(this)) {
      return support();
    }
    if (microbes > 0){
      other.setHP(other.getHP()+5);
      this.setResource(this.getResource()-1);
      return this + " supported " + other + " by feeding them microbes, restoring 5 HP and " + other.restoreSpecial(3) + other.getSpecialName();
    } else {
      return this + " has no microbes left to feed.";
    }
  }
}

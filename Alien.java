public class Alien extends Adventurer{
  private int cosmicEnergy, cosmicEnergyMax, microbes;

  public Alien(String name, int hp){
    super(name, hp);
    cosmicEnergyMax = 25;
    cosmicEnergy = cosmicEnergyMax/2;
    microbes = 5;
  }
  public Alien(String name){
    this(name, 20);
  }
  public Alien(){
    this("Zarg");
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
    cosmicEnergy = n;
  }
  public String getResourceName(){
    return "microbes";
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
    int choice = (int)(Math.random() * 3);
    if (choice == 0){
      other.applyDamage(3);
      return this + " uses telekinesis and throws a rock at " + other + ", dealing 3 points of damage.";
    } else if (choice == 1){
      other.applyDamage(2);
      return this + " punched " + other + ", dealing 2 points of damage.";
    } else{
      return this + " attacked " + other + " and missed, dealing zero damage.";
    }
  }
  public String specialAttack(Adventurer other){
    if (cosmicEnergy >= 5){
      other.applyDamage(4);
      this.setSpecial(this.getSpecial()-5);
      return this + " used Cosmic Blast on " + other + ", blasting cosmic energy from its eye and dealing 4 points of damage and consuming 5 cosmic energy.";
    } else{
      return this + " does not have enough cosmic energy for a special attack. Instead, " + this.attack(other);
    }
  }
  public String support(){
    if (microbes > 0){
      this.setHP(this.getHP() + 5);
      this.setSpecial(this.getSpecial() + 5);
      microbes--;
      return this + " consumed microbes, restoring 5 HP and 5 cosmic energy.";
    } else{
      return this + " has no microbes left to consume.";
    }
  }
  public String support(Adventurer other){
    if (microbes > 0){
      other.setHP(other.getHP() + 5);
      int restoredEnergy = other.restoreSpecial(5);
      microbes--;
      return this + " supported " + other + " by feeding them microbes, restoring 5 HP and " + restoredEnergy + " cosmic energy.";
    } else{
      return this + " has no microbes left to feed.";
    }
  }
  public String steal(Alien other) {
    //can steal microbes from another astronaut, but the other will fight back
    //give this option when steak is low
    int stolen = (int)(Math.random()*2);
    if (other.getResource()-stolen < 0) {
      stolen = other.getResource();
    }
    this.setResource(this.getResource() + stolen);
    other.setResource(other.getResource()-stolen);
    return this + " stole " + stolen + " microbes from" + " other, making " + other.attack(this);
  }
  public String tradeEV(Astronaut other, int eV) {
    //can give some eV of cosmic energy to the other alien in return for steak, must go through checks
    if (eV < 0) {
      //must enter pos eV
      return "Cannot trade negative eV of cosmic energy";
    }
    if (this.getSpecial() < eV) {
      //cannot cause self to have negative eV
      return this + " cannot trade with " + other + " because " + this + " does not have " + eV + " eV of cosmic energy";
    }
    int newMicrobes = (int)(Math.random()*3)+eV;
    if (other.getResource()-newMicrobes<0) {
      //cannot cause other to have positive eV
      if (other.getResource() <= 0) {
        return other + " cannot trade with " + this + " because " + other + " has no more microbes left.";
      }
      newMicrobes = other.getResource();
    }
    //switches inv
    this.setResource(this.getResource() + newMicrobes);
    other.setResource(other.getResource() - newMicrobes);
    this.setSpecial(this.getSpecial() - eV);
    other.setSpecial(other.getSpecial() + eV);
    return this + " traded " + eV + " eV of cosmic energy for " + newMicrobes + " microbes with " + other + ".";
  }
  //following method is the same, but with steak in return for laser light
  public String tradeMicrobes(Alien other, int add_microbes) {
    if (add_microbes < 0) {
      return "Cannot trade negative amounts of microbes";
    }
    if (this.getResource() < add_microbes) {
      return this + " cannot trade with " + other + " because " + this + " does not have " + add_microbes + " microbes.";
    }
    int eV = (int)(Math.random()*3)+add_microbes;
    if (other.getSpecial()-eV<0) {
      if (other.getSpecial() <= 0) {
        return other + " cannot trade with " + this + " because " + other + " has no more eV of cosmic energy left.";
      }
      eV = other.getSpecial();
    }
    this.setSpecial(this.getSpecial() + eV);
    other.setSpecial(other.getSpecial() - eV);
    this.setResource(this.getResource() - add_microbes);
    other.setResource(other.getResource() + add_microbes);
    return this + " traded " + add_microbes + " microbes for " + eV + " eV of cosmic energy with " + other + ".";
  }
}

//i added 3 abstract methods: public int getResource, public String getResourceName(), andd public void setResource(int n), make sure to add them
public class Alien extends Adventurer{
  private int cosmicEnergy, cosmicEnergyMax, microbes;
  
  public Alien(String name, int hp){
    super(name, hp);
    cosmicEnergyMax = 25
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
  public String specialAttack(Adventurer other) {
    if (cosmicEnergy >= 5){
      other.applyDamage(4);
      cosmicEnergy -= 5;
      return this + " used Cosmic Blast on " + other + ", blasting cosmic energy from its eye and dealing 4 points of damage and consuming 5 cosmic energy.";
    } else{
      return this + " does not have enough cosmic energy for a special attack.";
    }
  }


}

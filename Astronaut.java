import java.util.ArrayList;
public class Astronaut extends Adventurer {
  private int laser, laserMax, steak;
  public Astronaut(String name, int hp) {
    super(name,hp);
    laserMax = 25;
    laser = laserMax/2;
    steak = 8;
  }

  public Astronaut(String name) {
    this(name,25);
  }
  public Astronaut() {
    this("Alan");
  }
  public String getSpecialName() {
    return "Gamma Laser";
  }
  public int setSpecial() {
    return laser;
  }
  public void setSpecial(int n) {
    if (n <= this.getSpecialMax() && n >= 0) {
      laser = n;
    }
  }
  public int getSpecialMax() {
    return laserMax;
  }
  public int getSpecial() {
    return laser;
  }
  public int getResource() {
    return steak;
  }
  public void setResource(int n) {
    if (n >= 0) {
      //if negative, don't change steak value
      this.steak = n;
    }
  }
  public String getResourceName() {
    return "Steak";
  }
  public String attack (Adventurer other) {
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
  //overload specialAttack method so another astronaut has to cover this astronaut,
  //  or the astronaut faces a higher amount of damage
  public String specialAttack(Adventurer other) {
    if (getSpecial() >= 5) {
      int damage = (int)(Math.random()*5)+3;
      other.applyDamage(damage);
      setSpecial(getSpecial()-5);
      if (this.getWeakened() == true) {
        return this + " has been weakened for this turn, and cannot use their special gamma ray. Instead, " + this.attack(other);
      }
      else {
        return this + " used their special gamma ray and sliced " + other + ", dealing " + damage + " points of damage, using 5 eV of laser light.";
      }
    }
    else {
      //needs 5 eV to attack
      return this + "'s laser doesn't have enough energy.";
    }
  }
  public String support(Adventurer a) {
    //gives a regeneration potion to the octopus or heals octopus
    if (a.equals(this)) {
      return support();
    }
    int giveSteak = (int)(Math.random()*(Math.min(3,(a.getResource())))) + 1;
    if (this.getResource() < 1){
      return this + " does not have enough steak to support " + a + ".";
    }
    else {
      this.setResource(this.getResource()-giveSteak);
      a.setHP(a.getHP()+4*giveSteak);
      a.setSpecial(a.getSpecial() + giveSteak*2);
      return this + " fed " + a + " " + giveSteak + " pieces of steak. They healed " + 4 * giveSteak + " HP and restored " + 2 * giveSteak + " " + a.getSpecialName();
    }
  }
  public String support() {
    //supports by eating one piece of steak
    if (steak >= 2) {
      this.setHP(this.getHP() + 4);
      this.setSpecial(this.getSpecial() + 3);
      this.setResource(this.getResource()-2);
      return this + " eats two pieces of steak. They restore their health by 4 HP and charge their laser by 3 eV";
    }
    else {
      return this + " does not have enough steak to support itself.";
    }
  }
}

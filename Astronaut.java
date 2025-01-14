import java.util.ArrayList;
public class Astronaut extends Adventurer {
  private int laser, laserMax, steak, regenPotion;
  public Astronaut(String name, int hp) {
    super(name,hp);
    laserMax = 25;
    laser = laserMax/2;
    steak = 8;
    regenPotion = 5;
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
    laser = n;
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
  public int getHealer() {
    return regenPotion;
  }
  public String getHealerName() {
    return "Regen potion";
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
      return this + " attacked " + other + " by punching, dealing + " + damage + " points of damage";
    }
    else {
      return this + " attacked " + other + " and missed, dealing zero damage.";
    }
  }
  public String specialAttack(Adventurer other) {
    return specialAttack(other,this);
  }
  //overload specialAttack method so another astronaut has to cover this astronaut,
  //  or the astronaut faces a higher amount of damage
  public String specialAttack(Adventurer other, Astronaut cover) {
    if (getSpecial() >= 5) {
      int damage = (int)(Math.random()*5)+3;
      other.applyDamage(damage);
      setSpecial(getSpecial()-5);
      int coverDamage = (int)(Math.random()*2)+1;
      if (this.getWeakened() == true) {
        return this + " has been weakened for this turn, and cannot use their special gamma ray. Instead, " + this.attack(other);
      }
      if (cover.equals(this)) {
        return this + " used their special gamma ray and sliced " + other + ", dealing " + damage + " points of damage, but lost " + coverDamage+1 + " while exposed.";
      }
      else {
        return this + " used their special gamma ray and sliced " + other + ", dealing " + damage + " points of damage." + cover + " covered " + this + " during the attack, losing" + coverDamage + "HP.";
      }
    }
    else {
      //needs 5 eV to attack
      return this + "'s laser doesn't have enough energy.";
    }
  }
  public String specialAttack(ArrayList<Astronaut> group, Adventurer other) {
    ArrayList<Astronaut> abled = new ArrayList<Astronaut>();
    ArrayList<Astronaut> weak = new ArrayList<Astronaut>();
    ArrayList<Astronaut> notEnough = new ArrayList<Astronaut>();
    String result = "";
    for (int i = 0; i < group.size(); i++) {
      if (this.getWeakened() == true) {
        weak.add(this);
      }
      else if (this.getSpecial() <= 5) {
        notEnough.add(this);
      }
      else {
        abled.add(this);
      }
    }
    for (Alien a : abled) {
      result = result + a.specialAttack(other);
    }
    if (weak.size() > 0 || notEnough.size() > 0) {
      result = result + " However, ";
      for (Astronaut a : weak) {
        result = result + a.specialAttack(other);
      }
      for (Astronaut a : notEnough) {
        result = result + a.specialAttack(other);
      }
    }
    return result;
  }
  public String support(Adventurer other) {
    //gives a regeneration potion to increase HP by 5 and special by 5 (kind of OP)
    if (regenPotion > 0) {
      other.setHP(this.getHP() + 5);
      return this + " gave a regeneration potion to " + other + "and restores " + other.getSpecialName() + " by " + other.restoreSpecial(5) + " and restores" + other + "'s health" + " by 5 hp.";
    }
    else {
      return this + " ran out of regeneration potions. ";
    }
  }
  public String support() {
    //supports by eating one piece of steak
    if (steak > 0) {
      this.setHP(this.getHP() + 5);
      this.setHP(this.getHP() + 3);
      return this + " eats one steak. They restore their health by 5 HP and charge their laser by 3 eV";
    }
    else {
      return this + " ran out of steak.";
    }
  }
  public String steal(Astronaut other) {
    //can steal steak from another astronaut, but the other will fight back
    //give this option when steak is low
    int stolen = (int)(Math.random()*2);
    if (other.getResource()-stolen < 0) {
      stolen = other.getResource();
    }
    this.setResource(this.getResource() + stolen);
    other.setResource(other.getResource()-stolen);
    return this + " stole " + stolen + " steak from" + " other, making " + other.attack(this);
  }
  public String tradeEV(Astronaut other, int eV) {
    //can give some eV of laser to the other astronaut in return for steak, must go through checks
    if (eV < 0) {
      //must enter pos eV
      return "Cannot trade negative eV of laser light";
    }
    if (this.getSpecial() < eV) {
      //cannot cause self to have negative eV
      return this + " cannot trade with " + other + " because " + this + " does not have " + eV + " eV of laser light";
    }
    int newSteak = (int)(Math.random()*3)+eV;
    if (other.getResource()-newSteak<0) {
      //cannot caus eother to have positive eV
      if (other.getResource() <= 0) {
        return other + " cannot trade with " + this + " because " + other + " has no more steak left.";
      }
      newSteak = other.getResource();
    }
    //switches inv
    this.setResource(this.getResource() + newSteak);
    other.setResource(other.getResource() - newSteak);
    this.setSpecial(this.getSpecial() - eV);
    other.setSpecial(other.getSpecial() + eV);
    return this + " traded " + eV + " eV of laser light for " + newSteak + " pieces of steak with " + other + ".";
  }
  //following method is the same, but with steak in return for laser light
  public String tradeSteak(Astronaut other, int add_steak) {
    if (add_steak < 0) {
      return "Cannot trade negative amounts of steak";
    }
    if (this.getResource() < add_steak) {
      return this + " cannot trade with " + other + " because " + this + " does not have " + steak + " pieces of steak.";
    }
    int eV = (int)(Math.random()*3)+add_steak;
    if (other.getSpecial()-eV<0) {
      if (other.getSpecial() <= 0) {
        return other + " cannot trade with " + this + " because " + other + " has no more eV of laser light left.";
      }
      eV = other.getSpecial();
    }
    this.setSpecial(this.getSpecial() + eV);
    other.setSpecial(other.getSpecial() - eV);
    this.setResource(this.getResource() - add_steak);
    other.setResource(other.getResource() + add_steak);
    return this + " traded " + add_steak + " pieces of steak for " + eV + " eV of laser light with " + other + ".";
  }
}

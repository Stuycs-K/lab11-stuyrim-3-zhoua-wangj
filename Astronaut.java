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
    return "gamma ray laser";
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
  public int getSteak() {
    return steak;
  }
  public void setSteak(int n) {
    if (n >= 0) {
      //if negative, don't change steak value
      this.steak = n;
    }
  }
  public String attack (Adventurer other) {
    //kicking deals 3 points, punching deals 2 points, missing deals zero, 1/3 chance each
    int choice = (int)(Math.random()*3);
    if (choice == 0) {
      other.applyDamage(3);
      return this + " attacked " +  other + " by kicking, dealing 3 points of damage";
    }
    if (choice == 1) {
      other.applyDamage(2);
      return this + " attacked " + other + " by punching, dealing 2 points of damage";
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
      return this + " eats one steak  and restores their health by 5 HP";
    }
    else {
      return this + " ran out of steak.";
    }
  }
  public String steal(Astronaut other) {
    //can steal steak from another astronaut, but the other will fight back
    //give this option when steak is low
    int stolen = (int)(Math.random()*2);
    if (other.getSteak()-stolen < 0) {
      stolen = other.getSteak();
    }
    this.setSteak(this.getSteak() + stolen);
    other.setSteak(other.getSteak()-stolen);
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
    if (other.getSteak()-newSteak<0) {
      //cannot caus eother to have positive eV
      if (other.getSteak() <= 0) {
        return other + " cannot trade with " + this + " because " + other + " has no more steak left.";
      }
      newSteak = other.getSteak();
    }
    //switches inv
    this.setSteak(this.getSteak() + newSteak);
    other.setSteak(other.getSteak() - newSteak);
    this.setSpecial(this.getSpecial() - eV);
    other.setSpecial(other.getSpecial() + eV);
    return this + " traded " + eV + " eV of laser light for " + newSteak + " pieces of steak with " + other + ".";
  }
  //following method is the same, but with steak in return for laser light
  public String tradeSteak(Astronaut other, int add_steak) {
    if (add_steak < 0) {
      return "Cannot trade negative amounts of steak";
    }
    if (this.getSteak() < add_steak) {
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
    this.setSteak(this.getSteak() - add_steak);
    other.setSteak(other.getSteak() + add_steak);
    return this + " traded " + add_steak + " pieces of steak for " + eV + " eV of laser light with " + other + ".";
  }
}

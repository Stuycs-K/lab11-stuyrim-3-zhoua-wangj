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
    this.(name,25);
  }
  public Astronaut() {
    this.Astronaut("Alan");
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
  public String attack (Adventurer other) {
    int choice == (int)(Math.random()*3);
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
  public String specialAttack(Adventurer other) {
    if (getSpecial() >= 5) {
      int damage = (int)(Math.random()*5)+3;
      other.applyDamage(damage);
      setSpecial(getSpecial()-5);
      return this + " used their special gamma ray and sliced " + other " + dealing " + damage + " points of damage.";
   }
   else {
      return this + "'s laser doesn't have enough energy.";
  }
  public String support(Adventurer other) {
    if (regenPotions > 0) {
      other.setHP(this.getHP() + 5));
      return this + " gave a regeneration potion to " + other + "and restores " + other.restoreSpecial(5) + " " + other.getSpecialName() + "and restores" + other + "'s health" + " by 5 hp.";
    }
    else {
      return this + " ran out of regeneration potions. ";
}
  public String support() {
    if (steak > 0) {
      this.setHP(this.getHP() + 5));
      return this " eats one steak  and restores their health by 5 HP";
    }
    else {
      return this + " ran out of steak.";
    }
}


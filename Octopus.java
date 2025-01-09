public class Octopus extends Adventurer {
  private int venom, venomMax;
  public Octopus(String name, int hp) {
    super(name,hp);
    venomMax = 25;
    venom = venomMax/2;
  }
  public Octopus(String name) {
    this(name,25);
  }
  public Octopus() {
    this("Rachel");
  }

  public String getSpecialName() {
    return "poisonous venom";
  }
  public int setSpecial() {
    return venom;
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
  public String attack (Adventurer other) {
    return "";
  }
  public String specialAttack(Adventurer other) {
    return "";
  }
  public String support(Adventurer other) {
    return "";
  }
  public String support() {
    return "";
  }
}

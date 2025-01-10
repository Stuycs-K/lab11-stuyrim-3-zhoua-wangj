public class Boss extends Adventurer{
  private int coreEnergy, coreEnergyMax;
  public Boss(String name, int hp){
    super(name,hp);
    coreEnergyMax = 25;
    coreEnergy = coreEnergyMax/2;
  }
  public Boss(String name){
    this(name,50);
  }
  public Boss(){
    this("Crater Dave");
  }

  public String getSpecialName() {
    return "Core Energy";
  }
  public int setSpecial() {
    return coreEnergy;
  }
  public void setSpecial(int n){
      coreEnergy = n;
  }
  public int getSpecialMax(){
    return coreEnergyMax;
  }
  public int getSpecial(){
    return coreEnergy;
  }
  public String attack (Adventurer other){
    return "";
  }
  public String specialAttack(Adventurer other){
    return "";
  }
  public String support(Adventurer other){
    return "";
  }
  public String support(){
    return "";
  }
}
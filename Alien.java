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


}

import java.util.*;
public class Game{
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

  public static void main(String[] args) {
    run();
  }

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    String text = Text.colorize(" ", BORDER_BACKGROUND);
    for (int i = 0; i < 28; i+=27) {
      for (int j = 0; j < 80; j++) {
        drawText(text, 1+i, 1+j);
      }
    }
    for (int i = 0; i < 26; i++) {
      drawText(text, 2+i, 1);
    }
    for (int i = 0; i < 26; i++) {
      drawText(text, 2+i, 40);
    }
    for (int i = 0; i < 26; i++) {
      drawText(text, 2+i, 80);
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    Text.go(startRow,startCol);
    System.out.print(Text.colorize(s, Text.WHITE));
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }

  /*Use this method to place text on the screen at a particular location.
  *When the length of the text exceeds width, continue on the next line
  *for up to height lines.
  *All remaining locations in the text box should be written with spaces to
  *clear previously written text.
  *@param row the row to start the top left corner of the text box.
  *@param col the column to start the top left corner of the text box.
  *@param width the number of characters per row
  *@param height the number of rows
  */
  public static void TextBox(int row, int col, int width, int height, String text){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    ArrayList<String> lines = new ArrayList<String>();
    for (int i = 0; i < height; i ++) {
      if (text.length() > 0) {
        int length = Math.min(width,text.length());
        String substr = text.substring(0,length);
        text = text.substring(length);
        while (substr.length() < width) {
          substr += " ";
        }
        lines.add(substr);
      } else {
        String newStr = "";
        for (int a = 0; a < width; a ++) {
            newStr = newStr + " ";
        }
        lines.add(newStr);
      }
    }
    for (int i = 0; i < height; i++) {
      drawText(lines.get(i), row+i, col);
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }




    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
    public static Adventurer createRandomAdventurer(){
      int choice = (int)(Math.random()*4);
      if (choice == 0) return new Astronaut("Cody",(int)(Math.random()*25)+20);
      if (choice == 1) return new Alien("Chceced",(int)(Math.random()*25)+20);
      if (choice == 2) return new Boss("Philip",(int)(Math.random()*20)+80);
      return new Octopus ("Michelle",(int)(Math.random()*25)+40);
    }

    /*Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
    *Should include Name HP and Special on 3 separate lines.
    *Note there is one blank row reserved for your use if you choose.
    *Format:
    *Bob          Amy        Jun
    *HP: 10       HP: 15     HP:19
    *Caffeine: 20 Mana: 10   Snark: 1
    * ***THIS ROW INTENTIONALLY LEFT BLANK***
    */
    public static void drawParty(ArrayList<Adventurer> party,int startRow, int startCol){
      /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
      //YOUR CODE HERE
      int enemyCol = WIDTH/2 + 5;
      for (int i = 0; i < party.size(); i ++) {
        Adventurer temp = party.get(i);
        int e = i % 2;
        int j = 0;
        if (i == 2 || i == 3) j = 6;
        TextBox(startRow+j, startCol+e*18, 18, 1, temp.getName());
        TextBox(startRow+1+j, startCol+e*18, 18, 1, colorByPercent(temp.getHP(), temp.getmaxHP()));
        TextBox(startRow+2+j, startCol+e*18, 18, 1, temp.getSpecialName() + ": " + temp.getSpecial());
        TextBox(startRow+3+j, startCol+e*18, 18, 1, temp.getResourceName() + ": " + temp.getResource());
      }
      /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
    }




  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    //COLORIZE THE OUTPUT IF HIGH/LOW:
    // under 25% : red
    // under 75% : yellow
    // otherwise : white
    if ((double)hp/maxHP < 0.25) return Text.colorize(output,Text.RED);
    if ((double)hp/maxHP < 0.75) return Text.colorize(output,Text.YELLOW);
    return Text.colorize(output, Text.WHITE);
  }





  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer> party, ArrayList<Adventurer> enemies){

    drawBackground();
    drawText("Team Humans", 2,4);
    drawText("Team Extraterrestrial", 2,44);

    //draw player party
    drawParty(party, 4,4);

    //draw enemy party
    drawParty(enemies, 4,44);

    TextBox(HEIGHT - 4, 2, 35, 1, "");
  }

  public static String userInput(Scanner in, int cursorRow, int cursorCol){
      //Move cursor to prompt location
      Text.go(cursorRow, cursorCol);

      //show cursor
      Text.showCursor();
      System.out.print("> ");
      if (in.hasNextLine()) {
        String input = in.nextLine();

        //clear the text that was written
        TextBox(HEIGHT - 4, 2, 35, 1, "");

        return input;
      } else {
        return "";
    }
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(HEIGHT+2,1);
  }
  public static void run(){
    //Clear and initialize
    Text.hideCursor();
    Text.clear();
    Scanner in = new Scanner(System.in);

    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.
    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    enemies.add(new Boss("Meteor", 75));
    enemies.add(new Alien("Bob", 20));
    enemies.add(new Alien("Jim", 20));
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    party.add(new Octopus("Octopus", 40));
    party.add(new Astronaut("Rachel", 25));
    party.add(new Astronaut("Alan", 25));
    party.add(new Astronaut("Cody", 25));
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    drawScreen(party, enemies);
    //Main loop

    //display this prompt at the start of the game.
    //String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";

    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //You can add parameters to draw screen!
      //example debug statment
      //TextBox(24,2,40,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );
      int cursorRow = 15;
      int cursorCol = turn * 40 + 4;
      List<String> partyActionResults = new ArrayList<>();
      List<String> enemyActionResults = new ArrayList<>();
      //Read user input
      //display event based on last turn's input
	  partyActionResults.clear();
      enemyActionResults.clear();
	  
      if(partyTurn){
        Adventurer attacker = party.get(whichPlayer);;
        TextBox(cursorRow, cursorCol, 35, 2, "Enter command for " + attacker.getName() + ":");
        input = userInput(in, cursorRow+1, cursorCol+28);
        String action = "";
        Adventurer target = enemies.get(whichOpponent);
        String output = "";

        //Process user input for the last Adventurer:
        output = "";
        if(input.startsWith("attack") || input.startsWith("a")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          TextBox(cursorRow, cursorCol, 35, 2, "Enter which enemy to attack: 0/1/2");
          int opp = Integer.parseInt(userInput(in, cursorRow+1, cursorCol+28));
          if (opp >= enemies.size()) {
            output = attacker.attack(enemies.get(0));
            target = enemies.get(0);
          }
          else {
            output = attacker.attack(enemies.get(opp));
            target = enemies.get(opp);
          }
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("special") || input.startsWith("sp")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          TextBox(cursorRow, cursorCol, 35, 2, "Enter which enemy to special attack: 0/1/2");
          int opp = Integer.parseInt(userInput(in, cursorRow+1, cursorCol+8));
          if (opp >= enemies.size()) {
            output = attacker.specialAttack(enemies.get(0));
          }
          else {
            output = attacker.specialAttack(enemies.get(opp));
            target = enemies.get(opp);
          }
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("su") || input.startsWith("support")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          TextBox(cursorRow, cursorCol, 35, 2, "Enter which teammate to support: 0/1/2/3");
          int su = Integer.parseInt(userInput(in, cursorRow+1, cursorCol+28));
          if (su >= enemies.size()) {
            output = attacker.support(party.get(0));
            target = party.get(0);
          }
          else {
            output = attacker.support(party.get(su));
            target = party.get(su);
          }
        }
        else {
          TextBox(cursorRow, cursorCol, 35, 3, "Incorrect input, please enter (a)ttack/(sp)ecial attack/(su)pport/(q)uit");
          input = userInput(in, cursorRow+2, cursorCol+3);
        }
        TextBox(cursorRow+4, cursorCol, 35, 5, output);

          if (target.getHP() <= 0) {
            enemies.remove(whichOpponent);
            whichOpponent = Math.max(0, whichOpponent-1);
            TextBox(cursorRow, cursorCol, 35, 2, target + "has been defeated by " + attacker);
          }
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

        //You should decide when you want to re-ask for user input
        //If no errors:
        TextBox(cursorRow, cursorCol, 35, 4, output);
        whichPlayer++;
        if(whichPlayer < party.size()){
          //This is a player turn.
          //Decide where to draw the following prompt:
          TextBox(cursorRow, cursorCol, 35, 2, "Enter command for " + party.get(whichPlayer).getName() + ":");
        } else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
          whichOpponent = 0;
          whichPlayer = 0;
          String prompt = "Press enter to see enemy's turn";
          TextBox(cursorRow, cursorCol,35,2,prompt);
          partyTurn = false;
          userInput(in, cursorRow+1, cursorCol+2);
        }
      } if(!partyTurn) {
        if (enemies.isEmpty()) {
          drawText("Victory for the worldly beings!", HEIGHT/2, WIDTH/4);
        }
        //done with one party members
        //not the party turn!


        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        //YOUR CODE HERE
		TextBox(cursorRow + 4, cursorCol, 35, 4, "");
        Adventurer target = party.get((int) (Math.random() * party.size()));  // Random player target
        Adventurer enemyAttacker = enemies.get((int) (Math.random() * enemies.size()));
        String enemyActionOutput = "";
        if (Math.random() < 0.5) {
          enemyActionOutput = enemyAttacker.attack(target);
        } else {
          enemyActionOutput = enemyAttacker.specialAttack(target);
        }
        TextBox(cursorRow+4, cursorCol+39, 35, 5, enemyActionOutput);
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

          //Decide where to draw the following prompt:
        if (target.getHP() <= 0) {
          party.remove(target);
          if (party.isEmpty()) {
            drawText("Game over! Extraterrestrial beings have won.", HEIGHT / 2, WIDTH / 4);
          }
        }
        whichOpponent++;
        if (whichOpponent > enemies.size()) {
          partyTurn = true;
          TextBox(HEIGHT-4, 1, WIDTH, 1, " Press enter to see player's turn");
          userInput(in, HEIGHT-4, 1);
        }
      }
        //end of one enemy.

      //modify this if statement.
       if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        //whichPlayer = 0;
        whichOpponent = 0;
        //turn++;
        partyTurn=true;
        //display this prompt before player's turn
        String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
        TextBox(HEIGHT - 4, 1, WIDTH, 1, prompt);
      }
      for (String result:partyActionResults) {
        TextBox(cursorRow+4, cursorCol, 35, 4, result);
      }
	  partyActionResults.clear();
      for (String result:enemyActionResults) {
        TextBox(cursorRow+4, cursorCol+39, 35, 4, result);
      }
	  enemyActionResults.clear();

      //display the updated screen after input has been processed.
      if (enemies.isEmpty()) {
        drawText("Victory!", HEIGHT/2, WIDTH/4);
      }
      drawScreen(party,enemies);
    }
      //end of main game loop
    //After quit reset things:
  quit();
}
}

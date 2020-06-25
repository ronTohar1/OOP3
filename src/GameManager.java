import sun.misc.GC;

import java.util.*;
import java.util.function.Supplier;

public class GameManager {

    private GameController gameController;//Controls all of the actions of the game and is responsible of the logic between the tiles and the board.
    Player[] players;//All of the available players.
    Map<Character,Supplier<Enemy>> enemies;//All of the available enemies.
    List<List<String>> levels;//All of the available levels.(each levels is represented by a list of strings where each string is a row)).

    public GameManager(List<List<String>> levels) {

        this.levels=levels;

        //Players

        //Warriors
        Player JonSnow = new Warrior(300, 30, 4, "Jon Snow", 3);
        Player TheHound = new Warrior(400, 20, 6, "The Hound", 5);
        //Mages
        Player Melisandre = new Mage(100, 5, 1, "Melisandre", 300, 30, 15, 5, 6);
        Player ThorosOfMyr = new Mage(250, 25, 4, "Thoros of Myr", 150, 20, 20, 3, 4);
        //Rogue
        Player AryaStark = new Rogue(150, 40, 2, "Arya Stark", 20);
        Player Bronn = new Rogue(250, 35, 3, "Bronn", 50);
        this.players = new Player[]{JonSnow, TheHound, Melisandre, ThorosOfMyr, AryaStark, Bronn};

        //Enemies

        Map<Character, Supplier<Enemy>> enemies = new HashMap<Character,Supplier<Enemy>>();
        //Monsters
        enemies.put('s',()->new Monster('s', 80, 8, 3, "Lannister Soldier", 3, 25));
        enemies.put('k',()-> new Monster('k', 200, 14, 8, "Lannister Knight", 4, 50));
        enemies.put('q',()-> new Monster('q', 400, 20, 15, "Queen's Guard", 5, 100));
        enemies.put('z',()-> new Monster('z', 600, 30, 15, "Wright", 3, 100));
        enemies.put('b',()-> new Monster('b', 1000, 75, 30, "Bear-Wright", 4, 250));
        enemies.put('g',()-> new Monster('g', 1500, 100, 40, "Giant-Wright", 5, 500));
        enemies.put('w',()-> new Monster('w', 2000, 150, 50, "White Walker", 6, 1000));
        enemies.put('M',()-> new Monster('M', 1000, 60, 25, "The Mountain", 6, 500));
        enemies.put('C',()-> new Monster('C', 100, 10, 10, "Queen Cersei", 1, 1000));
        enemies.put('K',()->new Monster('K', 5000, 300, 150, "Night's King", 8, 5000));
        //Traps
        enemies.put('B',()-> new Trap('B', 1, 1, 1, "Bonus Trap", 250, 1, 5));
        enemies.put('Q',()-> new Trap('Q', 250, 50, 10, "Queen's Trap", 100, 3, 7));
        enemies.put('D',()-> new Trap('D', 500, 100, 20, "Death Trap", 250, 1, 10));
        this.enemies=enemies;
    }

    public void Start() {
            Scanner myScanner = new Scanner(System.in);
            for (int i = 0; i < players.length; i++) {
                HandleMessage(i + 1 + ". " + players[i].Describe());
                HandleMessage("");

            }

            HandleMessage("Choose a player");
            String scan = myScanner.next();

            while(!isPosInteger(scan)|| Integer.parseInt(scan)> players.length ){
                HandleMessage("Please enter a valid input");
                scan=myScanner.next();

            }
            int num=Integer.parseInt(scan)-1;
            Player chosenPlayer=players[num];
            //Creating a new Game controller
            this.gameController= new GameController(chosenPlayer,enemies,levels);

        while (gameController.isGameInProgress()) {
            String input=myScanner.next();
            while(input.length()!=1){
                HandleMessage("Please enter a valid character");
                input=myScanner.next();
            }
            char inputCharacter=input.charAt(0);
            boolean goodInput=true;
            while(goodInput){
                goodInput=false;
                switch (inputCharacter){

                    case 'w':MoveUp();break;
                    case 's':MoveDown();break;
                    case 'd':MoveRight();break;
                    case 'a':MoveLeft();break;
                    case 'q':DoNothing();break;
                    case 'e':CastAbility();break;
                }
            }

        }
    }

    //Checks if a string represents a positive integer
    private boolean isPosInteger(String s){
        for(int i=0;i<s.length();i++){
            char c= s.charAt(0);
            if(c<'0' || c>'9')
                return false;
        }
        return true;
    }
    private void HandleMessage(String msg) {
        MessageHandler msger = MessageHandler.GetInstance();
        msger.HandleMessage(msg);
    }

    public void MoveUp() {
        gameController.MoveUp();
    }

    public void MoveDown() {
        gameController.MoveDown();
    }

    public void MoveLeft() {
        gameController.MoveLeft();
    }

    public void MoveRight() {
        gameController.MoveRight();
    }

    public void CastAbility() {
        gameController.CastAbility();
    }

    public void DoNothing() {
        gameController.DoNothing();
    }

}

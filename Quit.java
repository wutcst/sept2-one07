package ZuulCommands;

import Game.absGame;
import Commands.absCommand;
import Character.Player;

public class Quit extends absCommand
{
    public Quit(String firstWord, String secondWord, String thirdWord)
    {
        super(firstWord, secondWord, thirdWord);
    }
    
    public Quit() {}
    
    /**
     * this method execute the command
     */
    @Override
    public boolean execute(Player player)
    {       
        if (hasSecondWord())
        {
            absGame._out.println(absGame._messages.getString("quitWhat"));
            return false;
        }
        return true;
    }
}

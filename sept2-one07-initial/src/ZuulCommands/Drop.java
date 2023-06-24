package ZuulCommands;

import Commands.absCommand;
import Character.Player;
import Game.absGame;

public class Drop extends absCommand
{
    
    public Drop(String firstWord, String secondWord, String thirdWord)
    {
        super(firstWord, secondWord, thirdWord);
    }
    
    public Drop() {}
   
    /**
     * this method execute the command
     */
    @Override
    public boolean execute(Player player)
    {
        if (!hasSecondWord())
        {
            // if there is no second word, we don't know what to drop...
            absGame._out.println(absGame._messages.getString("dropWhat"));
            return false;
        }
        String desc = getSecondWord();
        player.dropItem(desc);
        return false;
    }
}

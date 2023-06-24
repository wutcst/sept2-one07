package ZuulCommands;

import Game.absGame;
import Commands.absCommand;
import Character.Player;

public class Take extends absCommand
{
    public Take(String firstWord, String secondWord, String thirdWord)
    {
        super(firstWord, secondWord, thirdWord);
    }
    
    public Take() {}
    
    /**
     * this method execute the command
     */
    @Override
    public boolean execute(Player player)
    {
        if (!hasSecondWord())
        {
            // if there is no second word, we don't know what to take...
            absGame._out.println(absGame._messages.getString("takeWhat"));
            return false;
        }
        String desc = getSecondWord();
        player.take(desc);
        return false;
    }
}

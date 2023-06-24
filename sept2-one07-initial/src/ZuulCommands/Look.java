package ZuulCommands;

import Commands.ACommand;
import Game.absGame;
import Character.Player;

public class Look extends ACommand
{
    public Look(String firstWord, String secondWord, String thirdWord)
    {
        super(firstWord, secondWord, thirdWord);
    }
    
    public Look() {}
    
    /**
     * this method execute the command
     */
    @Override
    public boolean execute(Player player)
    {
        if (hasSecondWord())
        {
            absGame._out.println(absGame._messages.getString("lookWhat"));
            return false;
        }
        player.look();
        return false;
    }
}

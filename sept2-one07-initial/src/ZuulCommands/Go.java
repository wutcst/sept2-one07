package ZuulCommands;

import Commands.absCommand;
import Game.absGame;
import Character.Player;

public class Go extends absCommand
{
	public Go(String firstWord, String secondWord, String thirdWord)
	{
        super(firstWord, secondWord, thirdWord);
    }
    
    public Go() {}
    
    /**
     * this method execute the command
     */
    @Override
    public boolean execute(Player player)
    {
        if (!hasSecondWord())
        {
            absGame._out.println(absGame._messages.getString("goWhere"));
            return false;
        }
        String direction = getSecondWord();
        player.goRoom(direction);
        return false;
    }
}

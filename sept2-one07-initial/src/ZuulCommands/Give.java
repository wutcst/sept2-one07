package ZuulCommands;

import Character.Player;
import Commands.ACommand;
import Game.AGame;

public class Give extends ACommand
{
	public Give(String firstWord, String secondWord, String thirdWord)
	{
        super(firstWord, secondWord, thirdWord);
    }
    
    public Give() {}
    
    /**
     * this method execute the command
     */
    @Override
    public boolean execute(Player player)
    {
        if (!hasSecondWord())
        {
            AGame._out.println(AGame._messages.getString("giveWhat"));
            return false;
        }
        if (!hasThirdWord())
        {
        	AGame._out.println(AGame._messages.getString("giveWho"));
            return false;
        }
        String desc = getSecondWord();
        String whom = getThirdWord();
        player.giveItem(desc, whom);
        return false;
    }
}

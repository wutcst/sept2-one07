package ZuulCommands;

import Commands.absCommand;
import Character.Player;
import Game.absGame;

public class UnknownCommand extends absCommand
{
    public UnknownCommand(String firstWord, String secondWord, String thirdWord)
    {
        super(firstWord, secondWord, thirdWord);
    }
    
    public UnknownCommand() {}
    
    /**
     * this method execute an unknow command
     */
    @Override
    public boolean execute(Player player)
    {
        absGame._out.println(absGame._messages.getString("unknown"));
        return false;
    }
}

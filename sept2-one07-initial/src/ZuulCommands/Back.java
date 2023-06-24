package ZuulCommands;

import Commands.ACommand;
import Character.Player;

public class Back extends ACommand {
    public Back(String firstWord, String secondWord, String thirdWord)
    {
        super(firstWord, secondWord, thirdWord);
    }

    public Back() {}

    @Override
    public boolean execute(Player player)
    {
        player.back();
        return false;
    }
}

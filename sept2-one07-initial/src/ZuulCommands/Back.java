package ZuulCommands;

import Commands.absCommand;
import Character.Player;

public class Back extends absCommand {
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

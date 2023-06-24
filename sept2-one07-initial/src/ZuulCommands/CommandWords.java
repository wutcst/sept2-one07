package ZuulCommands;

import Commands.ACommandWords;

public class CommandWords extends ACommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = 
    	{
        	"go",
        	"quit",
        	"help",
        	"look",
        	"take",
        	"drop",
        	"give",
            "back"
        };

    /**
     * Constructor
     */
    public CommandWords() {}
    
    /** 
     * @return the validCommands
     * */
    @Override
    public  String[] getValidCommands()
    {
    	return validCommands;
    }

}

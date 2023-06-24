package ZuulCommands;

import Commands.absCommandWords;

public class CommandWords extends absCommandWords
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

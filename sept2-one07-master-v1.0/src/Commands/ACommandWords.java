package Commands;

import Game.AGame;

public abstract class ACommandWords 
{
    public ACommandWords() { }

    public abstract String[] getValidCommands();
    
    // 检查给定的字符串是否是有效的命令词，否则返回 false
    public boolean isCommand(String aString)
    {
        for(String cmd : getValidCommands())
        {
            if (cmd.equals(AGame._messages.getString(aString)))
                return true;
        }
        return false;
    }
}

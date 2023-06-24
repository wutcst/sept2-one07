/**
 * 该类是“World-of-Zuul”应用程序的指令词类。.
 *
 * 该类通过存储一个命令词汇字符串数组来定义游戏中所有有效的命令。
 * 用户通过输入合法的指令词与电脑持续互动，并导致游戏的进行。
 *
 * @author  Dian Tu
 */
package Commands;

import Game.absGame;

public abstract class absCommandWords
{
    public absCommandWords() { }

    public abstract String[] getValidCommands();
    
    // 检查给定的字符串是否是有效的命令词，否则返回 false
    public boolean isCommand(String aString)
    {
        for(String cmd : getValidCommands())
        {
            if (cmd.equals(absGame._messages.getString(aString)))
                return true;
        }
        return false;
    }
}

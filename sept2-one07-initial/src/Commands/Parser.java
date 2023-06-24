/**
 * 该类是“World-of-Zuul”应用程序的指令词类。.
 *
 * 用户通过输入合法的指令词与电脑持续互动，并导致游戏的进行。
 *
 * @author  Dian Tu
 */
package Commands;

import java.util.MissingResourceException;
import java.util.Scanner;

import Game.absGame;
import ZuulCommands.*;

public class Parser 
{
    private final absCommandWords _commands;  // 保存所有有效的命令词
    private final Scanner _reader;         // 命令输入源
    private final String _MYPACKAGE; //包含特定于游戏的类的包的名称

    public Parser(String pkg)
    {
        this._commands = absGame._commands;
        this._reader = new Scanner(absGame._in.in);
        this._MYPACKAGE = pkg + '.';
    }

    /**
     * @return 下一个命令关键词
     */
    public absCommand getCommand()
    {
        String inputLine;   // 将保存完整的输入行
        String word1 = null;
        String word2 = null;
        String word3 = null;

        absGame._out.print("> ");     // 打印提示

        inputLine = this._reader.nextLine();
        //在线上最多查找两个单词
        try (Scanner tokenizer = new Scanner(inputLine)) {
                if(tokenizer.hasNext()) {
                    word1 = tokenizer.next();      // get first word
                    if(tokenizer.hasNext()) {
                        word2 = tokenizer.next();      // get second word
                    }
                    if(tokenizer.hasNext()) {
                        word3 = tokenizer.next();      // get second word
                    }
                }
        }

        // 现在检查这个词是否已知。如果是，创建一个命令
        // 有了它。如果没有，创建一个“null”命令。
        try
        {
            word1 = absGame._messages.getString(word1); // translate it
        }
        catch (MissingResourceException | NullPointerException e)
        {
            return new UnknownCommand(word1, word2, word3);
        }
        if(this._commands.isCommand(word1))
        {
        	String cmdString = this._MYPACKAGE + word1.substring(0, 1).toUpperCase() + word1.substring(1) + "Command";
            try
            {
                absCommand cmd = (absCommand) Class.forName(cmdString).newInstance();
                cmd.addWords(word1, word2, word3);
                //Could use the Constructor class but this is easier
                return cmd;
            }
            catch (ClassNotFoundException  
                   | InstantiationException 
                   | IllegalAccessException 
                   | SecurityException  
                   | IllegalArgumentException 
                   e ) 
            { 
                return new UnknownCommand(word1, word2, word3);
            } 
        }
        else
        {
            return new UnknownCommand(word1, word2, word3); 
        }
    }
    
    public absCommand getCommandGUI(String str)
    {
        String inputLine;   // will hold the full input line
        String word1 = null;
        String word2 = null;
        String word3 = null;

        inputLine = str;

        try (Scanner tokenizer = new Scanner(inputLine)) {
            if(tokenizer.hasNext()) {
                word1 = tokenizer.next();      // get first word
                if(tokenizer.hasNext()) {
                    word2 = tokenizer.next();      // get second word
                }
                if(tokenizer.hasNext()) {
                    word3 = tokenizer.next();      // get second word
                }
            }
        }

        // 现在检查这个词是否已知。如果是，创建一个命令
        // 有了它。如果没有，创建一个“null”命令。
        try
        {
            word1 = absGame._messages.getString(word1); // translate it
        }
        catch (MissingResourceException | NullPointerException e)
        {
            return new UnknownCommand(word1, word2, word3);
        }
        if(this._commands.isCommand(word1))
        {
            String cmdString = this._MYPACKAGE + word1.substring(0, 1).toUpperCase() + word1.substring(1) + "Command";
            try
            {
                absCommand cmd = (absCommand) Class.forName(cmdString).newInstance();
                cmd.addWords(word1, word2, word3);
                //Could use the Constructor class but this is easier
                return cmd;
            }
            catch (ClassNotFoundException
                    | InstantiationException
                    | IllegalAccessException
                    | SecurityException
                    | IllegalArgumentException
                    e )
            {
                return new UnknownCommand(word1, word2, word3);
            }
        }
        else
        {
            return new UnknownCommand(word1, word2, word3);
        }
    }
}

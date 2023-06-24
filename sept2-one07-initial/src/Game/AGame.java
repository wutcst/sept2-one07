/**
 * 该类是“World-of-Zuul”应用程序的主类。
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 *
 * 如果想开始执行这个游戏，用户需要创建Game类的一个实例并调用“play”方法。
 *
 * Game类的实例将创建并初始化所有其他类:它创建所有房间，并将它们连接成迷宫；它创建解析器
 * 接收用户输入，并将用户输入转换成命令后开始运行游戏。
 *
 * @author  Dian Tu
 */
package Game;

import java.util.ResourceBundle;
import java.util.List;

import Commands.ACommand;
import Commands.ACommandWords;
import Commands.Parser;
import Character.Player;
import ZuulInputOutput.*;

public abstract class AGame
{
	public static final Out _out = new Out();
	public static final In _in = new In();
	public static ResourceBundle _messages;
	public static ACommandWords _commands;
	final private Parser _parser;
	protected Player _player;
	protected List<Room> _rooms;
	
	/**
	 * 构造函数

	 * @param commands
	 */
	public AGame(ACommandWords commands)
	{

		AGame._messages = ResourceBundle.getBundle("ZuulCommands.MessagesBundle");
		AGame._commands = commands;
		this._parser = new Parser("ZuulCommands");
		createRooms();
	}
	
	/**
	 * 游戏开始
	 */
	public void play()
	{
		boolean finished = false;
		
		printWelcome();
		while (!finished)
		{
			ACommand command = this._parser.getCommand();
			finished = processCommand(command);
		}
		AGame._out.println(AGame._messages.getString("goodbye"));
	}
	
	/**
	 *
	 */
	private void printWelcome()
	{
		getWelcomeStrings().stream().forEach((str) -> { AGame._out.println(str); });
	}
	
	/**
	 * 发送给玩家以执行命令
	 * @param command
	 * @return
	 */
	private boolean processCommand(ACommand command)
	{
		return command.execute(this._player);
	}
	
	/**
	 * 游玩者加入游戏
	 * @param player
	 */
	protected void setPlayer(Player player)
	{
		this._player = player;
	}
	
	/**
	 *
	 * @return
	 */
	protected Player getPlayer()
	{
		return this._player;
	}
	
	/**
	 * set rooms
	 * @param rooms
	 */
	protected void setRooms(List<Room> rooms)
	{
		this._rooms = rooms;
	}
	
	protected abstract List<String> getWelcomeStrings();
	protected abstract void createRooms();
}

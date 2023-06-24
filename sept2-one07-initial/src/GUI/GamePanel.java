/**
 * 此类是包含游戏视图的 Jpanel 类
 * 游戏GUI
 * @author Dian Tu
 *
 */
package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import Character.Player;
import Commands.Parser;
import Game.Room;

public class GamePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	static final int width = 450;
	static final int height = 600;
	Player _player;
	Parser _parser;
	private Container window;
	private ImageLoader loader = new ImageLoader();
	
	/**
	 * 带设置参数的构造函数
	 * @param cwindow
	 * @param player
	 * @param parser
	 */
	public GamePanel(Container cwindow, Player player, Parser parser)
	{
		super();
		
		this._player = player;
		this._parser = parser;
		this.window = cwindow;
		this.setPreferredSize(new Dimension(width,height));
		this.setMaximumSize(getPreferredSize());
		this.setBackground(Color.GRAY);
		this.setVisible(true);
		window.add(this);
	}
	
	/**
	 * 在 Jpanel 窗口中绘制元件的方法
	 * 具有某些操作的更新调用此方法
	 * @param graphic
	 */
	public void drawComponents(Graphics graphic)
	{
		graphic.clearRect(0, 0, getWidth(), getHeight());
		loader.loadImage("SolInside.jpg");
		graphic.drawImage(loader.getImage(), 0,0, 416,572,null);
		//DrawWalls
		drawWalls(graphic);
		//DrawDoors and Player
		drawDoors((Graphics2D)graphic);
		drawPlayer((Graphics2D)graphic);
	}
	
	/**
	 * overide 对组件绘画
	 */
	public void paintComponent(Graphics graphic)
	{
		graphic.clearRect(0, 0, getWidth(), getHeight());
		loader.loadImage("SolInside.jpg");
		graphic.drawImage(loader.getImage(), 0,0, 416,572,null);
		drawComponents(graphic);
	}
	
	/**
	 * 添加入玩家以及角色
	 *
	 * @param g2
	 */
	public void drawPlayer(Graphics2D g2)
	{
		loader.loadImage("player.png");
		g2.drawImage(loader.getImage(), 416 / 2 - 22, 572 / 2 - 27, 45,55,null);
		//如果有其他charcater粘贴
		if (this._player.getCurrentRoom().getListCharacters().size() > 0)
		{
			loader.loadImage("gandalf.png");
			g2.drawImage(loader.getImage(), 416 / 2 + 40, 572 / 2 - 37, 72,62,null);
		}
		//如果有物品粘贴宝箱图片
		if (this._player.getCurrentRoom().getAllItems().size() > 0)
		{
			loader.loadImage("treasure.png");
			g2.drawImage(loader.getImage(), 416 / 2 - 35, 572 / 2 + 35, 70,60,null);
		}
	}
	
	/**
	 * 此方法在地图上绘制墙壁
	 * @param graphic
	 */
	public void drawWalls(Graphics graphic)
	{

		// drawWalls Left and right
		loader.loadImage("WallSide.jpg");
		graphic.drawImage(loader.getImage(), 0,0, 23,572,null);
		loader.loadImage("WallSide.jpg");
		graphic.drawImage(loader.getImage(), 416 - 23,0, 23,572,null);
		// drawWalls top and bot
		loader.loadImage("WallTop.jpg");
		graphic.drawImage(loader.getImage(), 0,0, 572,23,null);
		loader.loadImage("WallTop.jpg");
		graphic.drawImage(loader.getImage(), 0,572 - 23, 416,23,null);


	}
	
	/**
	 * 此方法在有门时绘制门
	 * @param g2
	 */
	public void drawDoors(Graphics2D g2)
	{
		Room checkNorth = this._player.getCurrentRoom().getExit("north");
		Room checkSouth = this._player.getCurrentRoom().getExit("south");
		Room checkEast = this._player.getCurrentRoom().getExit("east");
		Room checkWest = this._player.getCurrentRoom().getExit("west");
		
		if (checkNorth != null)
		{
			loader.loadImage("door.png");
			g2.drawImage(loader.getImage(), (416 / 2) - 23, 0, 45, 60, null);
		}
		if (checkSouth != null)
		{

			loader.loadImage("door.png");
			g2.drawImage(loader.getImage(),(416 / 2) - 23, (572 - 60), 45, 60, null);
		}
		if (checkEast != null)
		{
			loader.loadImage("door.png");
			g2.drawImage(loader.getImage(),(416 - 60) ,(572 / 2) - 23, 45, 60, null);
		}
		if (checkWest != null)
		{
			loader.loadImage("door.png");
			g2.drawImage(loader.getImage(), 0, (572 / 2) - 23, 45, 60, null);
		}
		
	}
}

/**
 * 此类用于将控制面板打印到 GUI 中
 * @author Dian Tu
 *
 */

package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;

import Character.Player;
import Commands.ACommand;
import Commands.Parser;
import Game.Room;

public class ControlsPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private Player _player;
	private Parser _parser;
	private JButton buttonblank = new JButton("");
	private JButton buttonLook = new JButton("查看宝箱");
	private JButton buttonTake = new JButton("拾取道具");
	private JButton buttonDrop = new JButton("丢弃道具");
	private JButton buttonGive = new JButton("赠与道具");
	private JButton buttonBack = new JButton("返回上一步");
	private JButton buttonNorth = new JButton("");
	private JButton buttonInventory = new JButton("查看背包");
	private JButton buttonWest = new JButton("");
	private JButton buttonSouth = new JButton("");
	private JButton buttonEast = new JButton("");
	private Container window;
	private DefaultListModel model = new DefaultListModel();
	private JList list = new JList(model);
	private Graphics2D g2;
	private GamePanel _gamePanel;
	private JPanel inventory = new JPanel();

	/**
	 * Constructor
	 * @param cwindow
	 * @param player
	 * @param parser
	 * @param gamePanel
	 */
	public ControlsPanel(Container cwindow, Player player, Parser parser, GamePanel gamePanel)
	{
		super();
		this._gamePanel = gamePanel;
		this.window = cwindow;
		this._player = player;
		this._parser = parser;
		//纵向排列
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setVisible(true);
		this.setMinimumSize(new Dimension(440,600));
		window.add(this);
		drawComponents();
	}
	
	/**
	 * @return 控制面板对象
	 */
	public ControlsPanel getControlsPanel()
	{
		return this;
	}
	
	/**
	 * Update 执行操作时的方法
	 */
	public void updateGamePanel()
	{
		this._gamePanel.drawComponents(this._gamePanel.getGraphics());
		addInInventory();
		checkButtonsEnabled();
	}
	
	/**
	 * 绘制控件面板组件的方法
	 */
	public void drawComponents()
	{
		drawInventory();
		drawControlsTable();
		addInInventory();
		checkButtonsEnabled();
	}
	
	/**
	 * 检查按钮是否可以启用的方法
	 */
	public void checkButtonsEnabled()
	{
		Room checkNorth = this._player.getCurrentRoom().getExit("north");
		Room checkSouth = this._player.getCurrentRoom().getExit("south");
		Room checkEast = this._player.getCurrentRoom().getExit("east");
		Room checkWest = this._player.getCurrentRoom().getExit("west");
		
		//
		if (checkNorth == null)
			this.buttonNorth.setEnabled(false);
		else
			this.buttonNorth.setEnabled(true);
		if (checkSouth == null)
			this.buttonSouth.setEnabled(false);
		else
			this.buttonSouth.setEnabled(true);
		if (checkEast == null)
			this.buttonEast.setEnabled(false);
		else
			this.buttonEast.setEnabled(true);
		if (checkWest == null)
			this.buttonWest.setEnabled(false);
		else
			this.buttonWest.setEnabled(true);
		// 检查玩家是否可以拿起物品并打印按钮（getAllItems()前提为房间内有物体且总重量不超重）
		if (this._player.getCurrentRoom().getAllItems().size() <= 0)
			this.buttonTake.setEnabled(false);
		else
			this.buttonTake.setEnabled(true);
		// 检查玩家是否可以丢弃物品并打印按钮
		if (this._player.getInventory().size() <= 0)
			this.buttonDrop.setEnabled(false);
		else
			this.buttonDrop.setEnabled(true);
		// 检查玩家是否可以给予物品并打印按钮
		if ((this._player.getInventory().size() <= 0) || (this._player.getCurrentRoom().getListCharacters().size() <= 0))
			this.buttonGive.setEnabled(false);
		else
			this.buttonGive.setEnabled(true);
		// 检查玩家是否取完了所有物品并打印按钮
		if (this._player.getInventory().isEmpty())
			this.buttonInventory.setEnabled(false);
		else
			this.buttonInventory.setEnabled(true);
		//检查玩家是否可以执行back命令，避免游戏开始时或是TProom结束后back情况的复杂性
		if(this._player.get_lastdirection()==null)
			this.buttonBack.setEnabled(false);
		else
			this.buttonBack.setEnabled(true);
	}
	
	/**
	 * 将物品添加到 GUI 的方法
	 */
	public void addInInventory()
	{
		model.clear();
		for (String mapKey : this._player.getCurrentRoom().getAllItems().keySet())
		{
			//if (mapKey=='')
			model.addElement(mapKey);
		}
	}
	
	/**
	 * 使用命令按钮绘制列表的方法
	 */
	public void drawInventory()
	{
		//纵向
		inventory.setLayout(new BoxLayout(inventory, BoxLayout.Y_AXIS));
		inventory.setBackground(Color.GRAY.brighter().brighter());
		//滚动条面板
		JScrollPane scrollList = new JScrollPane(list);
		DefaultListCellRenderer renderer = null;
		renderer = (DefaultListCellRenderer)list.getCellRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);
		Border empty = BorderFactory.createEmptyBorder();
		TitledBorder title = BorderFactory.createTitledBorder(empty, "当前房间内道具");
		title.setTitleJustification(TitledBorder.CENTER);
		scrollList.setBorder(title);
		inventory.add(scrollList);
		
        //空的刚性区域，用于button划分开来
		inventory.add(Box.createRigidArea(new Dimension(10, 10)));
		// 添加新的按钮
		JPanel toolBar = new JPanel();
		//横向容器
		toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));
		toolBar.setVisible(true);
		// 使用操作侦听器设置工具栏中的按钮
		//		添加look按钮
		buttonLook.addActionListener(this);
		toolBar.add(buttonLook);

		//		添加背包按钮
		buttonInventory.addActionListener(this);
		toolBar.add(buttonInventory);

		JPanel toolBar1 = new JPanel();
		toolBar1.setLayout(new BoxLayout(toolBar1, BoxLayout.X_AXIS));
		toolBar1.setVisible(true);
		//		添加back按钮
		buttonBack.addActionListener(this);
		toolBar1.add(buttonBack);

		JPanel toolbox= new JPanel();
		toolbox.setLayout(new BoxLayout(toolbox, BoxLayout.X_AXIS));
		toolbox.setVisible(true);
		// 		添加take按钮
		buttonTake.addActionListener(this);
		toolbox.add(buttonTake);
		//		 添加drop按钮
		buttonDrop.addActionListener(this);
		toolbox.add(buttonDrop);
		// 		添加give按钮

		buttonGive.addActionListener(this);
		toolbox.add(buttonGive);
		// 		在清单中添加工具栏，在控制面板中添加清单
		inventory.add(toolBar);
		inventory.add(Box.createRigidArea(new Dimension(10, 10)));
		inventory.add(toolBar1);
		inventory.add(Box.createRigidArea(new Dimension(10, 10)));
		inventory.add(toolbox);
		this.add(inventory);
		//空的刚性区域，用于button划分开来
		inventory.add(Box.createRigidArea(new Dimension(10, 10)));
		// add the control panel in the window
		window.add(this);
	}
	
	/**
	 * 绘制控制器表的方法
	 */
	public void drawControlsTable()
	{
		// 添加面板以包含控件移动操作
		JPanel controlerTable = new JPanel();
		controlerTable.setBackground(Color.GRAY.brighter().brighter());

		
		// 从控件移动设置按钮

		// 		设置北向按钮
		ImageIcon imageIcon1 = new ImageIcon("src\\Assets\\上.png");
		Image image1 = imageIcon1.getImage();
		Image smallImage1 = image1.getScaledInstance(70, 70, Image.SCALE_FAST);
		ImageIcon smallIcon1 = new ImageIcon(smallImage1);
		this.buttonNorth.setIcon(smallIcon1);
		this.buttonNorth.setBackground(Color.GRAY.brighter().brighter());
		this.buttonNorth.addActionListener(this);
		controlerTable.add(this.buttonNorth, BorderLayout.PAGE_START);


		JPanel controlerTable1 = new JPanel();
		controlerTable1.setBackground(Color.GRAY.brighter().brighter());
        // 		设置西向按钮
		ImageIcon imageIcon2 = new ImageIcon("src\\Assets\\左.png");
		Image image2 = imageIcon2.getImage();
		Image smallImage2 = image2.getScaledInstance(70, 70, Image.SCALE_FAST);
		ImageIcon smallIcon2 = new ImageIcon(smallImage2);
        this.buttonWest.setIcon(smallIcon2);
		this.buttonWest.setBackground(Color.GRAY.brighter().brighter());
		this.buttonWest.addActionListener(this);
        controlerTable1.add(this.buttonWest, BorderLayout.LINE_START);

		JPanel controlerTable2 = new JPanel();
		controlerTable2.setBackground(Color.GRAY.brighter().brighter());
        // 		设置南向按钮
		ImageIcon imageIcon3 = new ImageIcon("src\\Assets\\下.png");
		Image image3 = imageIcon3.getImage();
		Image smallImage3 = image3.getScaledInstance(70, 70, Image.SCALE_FAST);
		ImageIcon smallIcon3 = new ImageIcon(smallImage3);
		this.buttonSouth.setIcon(smallIcon3);
		this.buttonSouth.setBackground(Color.GRAY.brighter().brighter());
        this.buttonSouth.addActionListener(this);
        controlerTable2.add(this.buttonSouth, BorderLayout.PAGE_END);

		controlerTable1.add(Box.createRigidArea(new Dimension(80, 30)));

        // 		添加东向按钮
		ImageIcon imageIcon4 = new ImageIcon("src\\Assets\\右.png");
		Image image4 = imageIcon4.getImage();
		Image smallImage4 = image4.getScaledInstance(70, 70, Image.SCALE_FAST);
		ImageIcon smallIcon4 = new ImageIcon(smallImage4);
		this.buttonEast.setIcon(smallIcon4);
		this.buttonEast.setBackground(Color.GRAY.brighter().brighter());
        this.buttonEast.addActionListener(this);
        controlerTable1.add(this.buttonEast, BorderLayout.LINE_END);
        // 		将控制器表添加到控制器组件面板
        this.add(controlerTable);
		this.add(controlerTable1);
		this.add(controlerTable2);
		//空的刚性区域，用于button划分开来
        this.add(Box.createRigidArea(new Dimension(10, 10)));
        // 添加按钮
        window.add(this);
	}
	
	/**
	 * 按钮事件监听方法
	 */
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		updateGamePanel();
		// look按钮
		if(source == this.buttonLook)
		{
			if (this._player.getCurrentRoom().getAllItems().size() > 0)
			{

				int i = 0;
				String[] tab = new String[this._player.getCurrentRoom().getAllItems().size()];
				for (String mapKey : this._player.getCurrentRoom().getAllItems().keySet())
				{
					tab[i] = mapKey;
					++i;
				}
				//下拉菜单，获得属性值，生成take item形式的command并执行相应逻辑方法
				String item = (String)JOptionPane.showInputDialog(
						this.window,
						"宝箱内的道具:",
						"查看宝箱",
						JOptionPane.PLAIN_MESSAGE,
						null,
						tab,
						tab[0]);

				ACommand command = this._parser.getCommandGUI("look" + " " + item);
				command.execute(this._player);
			}
		}
		if(source == this.buttonBack)
		{
			ACommand command = this._parser.getCommandGUI("back");
			command.execute(this._player);
		}
		// Button Take
		else if (source == this.buttonTake)
		{
			if (this._player.getCurrentRoom().getAllItems().size() > 0)
			{

				int i = 0;
				String[] tab = new String[this._player.getCurrentRoom().getAllItems().size()];
				for (String mapKey : this._player.getCurrentRoom().getAllItems().keySet())
				{
					tab[i] = mapKey;
					++i;
				}
				//下拉菜单，获得属性值，生成take item形式的command并执行相应逻辑方法
				String item = (String)JOptionPane.showInputDialog(
	                    this.window,
	                    "选择需要获取的道具",
	                    "拾取",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    tab,
	                    tab[0]);
				
				ACommand command = this._parser.getCommandGUI("take" + " " + item);
				command.execute(this._player);
			}
		}
		// Drop按钮
		else if(source == this.buttonDrop)
		{
			//是否已经take了某些物品
			if (this._player.getInventory().size() > 0)
			{
				// 下拉菜单，选择后生成drop item 形式的command并执行相应逻辑方法
				int i = 0;
				String[] tab = new String[this._player.getInventory().size()];
				for (String mapKey : this._player.getInventory().keySet())
				{
					tab[i] = mapKey;
					++i;
				}
				String item = (String)JOptionPane.showInputDialog(
	                    this.window,
	                    "选择需要丢弃的道具",
	                    "丢弃",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    tab,
	                    tab[0]);
				ACommand command = this._parser.getCommandGUI("drop" + " " + item);
				command.execute(this._player);
			}
		}
		// Give按钮
		else if(source == this.buttonGive)
		{
			//前提条件当前玩家已经take了某些物品
			if (this._player.getInventory().size() > 0)
			{
				//
				int i = 0;
				String[] tab = new String[this._player.getInventory().size()];
				for (String mapKey : this._player.getInventory().keySet())
				{
					tab[i] = mapKey;
					++i;
				}
				//通过下拉菜单选择item
				String item = (String)JOptionPane.showInputDialog(
	                    this.window,
	                    "选择需要赠与的道具",
	                    "赠与",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    tab,
	                    tab[0]);
				//如果当前房间存在其他角色
				if (this._player.getCurrentRoom().getListCharacters().size() > 0)
				{
					// 选择基于那个角色
					int j = 0;
					String[] tab2 = new String[this._player.getCurrentRoom().getListCharacters().size()];
					for (String mapKey2 : this._player.getCurrentRoom().getListCharacters().keySet())
					{
						tab2[j] = mapKey2;
						++j;
					}
					//下拉菜单选择give哪个角色
					String who = (String)JOptionPane.showInputDialog(
		                    this.window,
		                    "选择需要赠与的角色",
		                    "赠与",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    tab2,
		                    tab2[0]);
					// 形成形式类似于give item charcater的command语句并执行逻辑方法
					ACommand command = this._parser.getCommandGUI("give" + " " + item + " " + who);
					command.execute(this._player);
				}
			}
		}
		// 北向North
		else if(source == this.buttonNorth)
		{
			ACommand command = this._parser.getCommandGUI("go north");
			command.execute(this._player);
		}
		// Button Inventory
		else if(source == this.buttonInventory)
		{
			if (this._player.getInventory().size() > 0)
			{
				int i = 0;
				String[] tab = new String[this._player.getInventory().size()];
				for (String mapKey : this._player.getInventory().keySet())
				{
					tab[i] = mapKey;
					++i;
				}
				//查看已经获得物品清单以及总重量
				String info = "背包容量 : " + this._player.getTotalWeight() + " / " + this._player.getMaxWeight()+"\n背包内物品清单 :";
				String info1 = "" ;
				String item = (String)JOptionPane.showInputDialog(
	                    this.window,
	                    info,
	                    "背包",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    tab,
	                    tab[0]);
			}
		}
		// Button West
		else if(source == this.buttonWest) 
		{
			ACommand command = this._parser.getCommandGUI("go west");
			command.execute(this._player);
		}
		// Button South
		else if(source == this.buttonSouth)
		{
			ACommand command = this._parser.getCommandGUI("go south");
			command.execute(this._player);
		}
		// Button East
		else if(source == this.buttonEast)
		{
			ACommand command = this._parser.getCommandGUI("go east");
			command.execute(this._player);
		}
		//添加物品到清单，以及设置button是否仍然可用
		updateGamePanel();
	}
}

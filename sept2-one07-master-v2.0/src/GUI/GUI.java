package GUI;

import java.awt.*;

import javax.swing.*;

import Character.Player;
import Commands.Parser;

/**
 * 此类用于创建一个包含整个 GUI 内容的 GUI
 *
 * @author duoduo
 *
 */
public class GUI
{
	static final int width = 900;
	static final int height = 600;

	/**
	 * in this Constructor we declare the GUI in thread safe
	 * @param player
	 * @param parser
	 */
	public GUI(Player player, Parser parser)
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run()
			{
				WindowFrame window = new WindowFrame(width, height);
				GamePanel gamePanel = new GamePanel(window.getContentPane(), player, parser);
				window.add(Box.createRigidArea(new Dimension(10, 10)));
			    ControlsPanel controlsPanel = new ControlsPanel(window.getContentPane(), player, parser, gamePanel);
			    window.add(Box.createRigidArea(new Dimension(10, 10)));
			}
		});
	}
}

package monga;

//import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;




public class Grid {

	// Declare attributes
	public int[][] displayCells;
	JPanel gridLayout;
	public int cellSize;
	private int maxWidth, maxHeight;
	
	// Initialize values
	public Grid()
	{
		// Top right and bottom left corners are inverted when displayed
		displayCells = new int[][]
				{
			        {9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
			        {9, 0, 0, 0, 0, 0, 0, 0, 0, 9},
			        {9, 0, 0, 0, 9, 0, 0, 0, 0, 9},
			        {9, 0, 0, 0, 9, 9, 9, 0, 0, 9},
			        {9, 0, 0, 0, 0, 0, 0, 0, 0, 9},
			        {9, 0, 0, 0, 0, 0, 0, 0, 0, 9},
			        {9, 0, 2, 9, 0, 0, 0, 0, 0, 9},
			        {9, 0, 9, 9, 0, 0, 0, 0, 0, 9},
			        {9, 0, 0, 0, 0, 0, 0, 0, 0, 9},
			        {9, 9, 9, 9, 9, 9, 9, 9, 9, 9}
		
				};
		maxWidth = 10;
		maxHeight = 10;
		cellSize = 30;
	}
	
	// Checks whether the position is valid to move
	public void moveMongaUp(int[][] moveUp)
	{
		displayCells = moveUp;
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(displayCells[i][j] == 1 && displayCells[i][j - 1] == 0)
				{
					displayCells[i][j - 1] = 1;
				}
			}
		}
	}
	
	public void moveMongaDown(int[][] moveDown)
	{
		displayCells = moveDown;
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(displayCells[i][j] == 1 && displayCells[i][j + 1] == 0)
				{
					displayCells[i][j + 1] = 1;
				}
			}
		}
	}
	
	public void	moveMongaLeft(int[][] moveLeft)
	{
		displayCells = moveLeft;
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(displayCells[i][j] == 1 && displayCells[i - 1][j] == 0)
				{
					displayCells[i - 1][j] = 1;
				}
			}
		}
	}
	
	public void moveMongaRight(int[][] moveRight)
	{
		displayCells = moveRight;
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(displayCells[i][j] == 1 && displayCells[i + 1][j] == 0)
				{
					displayCells[i + 1][j] = 1;
				}
			}
		}
	}
	
	/* Display grid, monsters, player and power-ups
	 * and initialize the player character in the window
	 */
	public JPanel displayMongaMap()
	{
        gridLayout = new JPanel(null);
        gridLayout.setPreferredSize(new Dimension(500,250));
        
        gridLayout.setFocusable(true);
        gridLayout.requestFocusInWindow();
        
        initializeMonga(5, 5);
        
        initializeMongaMap();
        
        gridLayout.revalidate();
        gridLayout.repaint();
		return gridLayout;
	}
	
	// Set the position of player character
	public void initializeMonga(int mongaPosX, int mongaPosY)
	{
		if(displayCells[mongaPosX][mongaPosY] != 0)
		{
			System.out.println("Monga cannot be placed on the position");
		}
		else
		{
			displayCells[mongaPosX][mongaPosY] = 1;
		}
	}
	
	// set image icons and the size of the icons
	public void initializeMongaMap()
	{
        gridLayout.removeAll();
        JLabel display = new JLabel();
        for(int i = 0;i<maxWidth;i++)
        {
            for(int j = 0;j<maxHeight;j++)
            {
                switch(displayCells[i][j])
                {
                	case 1:
                        ImageIcon mongaMan = scaleIcon("iconImages/pacman.png");
                        display = new JLabel(mongaMan);
                        display.setBounds(i*cellSize,j*cellSize,cellSize*3,cellSize*3);
                        gridLayout.add(display);
                		break;
                	case 2:
                        ImageIcon powerUp_1 = scaleIcon("iconImages/star.png");
                        display = new JLabel(powerUp_1);
                        display.setBounds(i*cellSize,j*cellSize,cellSize*3,cellSize*3);
                        gridLayout.add(display);
                		break;
                	
                    case 9:
                        ImageIcon brickLayout = scaleIcon("iconImages/brick.png");
                        display = new JLabel(brickLayout);
                        display.setBounds(i*cellSize,j*cellSize,cellSize*3,cellSize*3);
                        gridLayout.add(display);
                        break;
                }   
            }
        }
        gridLayout.revalidate();
        gridLayout.repaint();
    }
	
	// Generate actual icons
	public ImageIcon scaleIcon(String path)
	{
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage(); 
        Image newimg = image.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newimg);  
        return icon;
    }
	
	
    public static void main(String[] args) 
    {
    	// Utilize Swing class, runnable interface
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run() 
            {
                JFrame frame = new JFrame("MONGA");
                frame.getContentPane().add(new Grid().displayMongaMap());
              
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int screenHeight = screenSize.height / 4 * 3;
                int screenWidth = screenSize.width / 4;
                frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setLocation((screenSize.width - screenWidth) / 2, 10);
                frame.setVisible(true);
            }
        });
    }
}

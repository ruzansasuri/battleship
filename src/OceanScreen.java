import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Ruzan on 11/20/2016.
 */
public class OceanScreen extends JPanel
{
    Controller c;
    List<Rectangle> cells;
    int xOffset,yOffset;
    OceanListener ol;
    public OceanScreen(Controller c, JLabel turn)
    {
        ol = new OceanListener(this, c, turn);
        addMouseListener(ol);
        Border raisedBevel = BorderFactory.createRaisedBevelBorder();
        Border loweredBevel = BorderFactory.createLoweredBevelBorder();
        Border compound = BorderFactory.createCompoundBorder
                (raisedBevel, loweredBevel);
        setBorder(compound);
        this.c = c;
        xOffset = 0;
        yOffset = 0;
    }
    public void wake(boolean t)
    {
        ol.setAwake(t);
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int height = getHeight();
        int width = getWidth();
        int cellWidth = width / Game.MAX_COLUMN;
        int cellHeight = height / Game.MAX_ROW;
        cells = new ArrayList<>(Game.MAX_COLUMN * Game.MAX_ROW);;

        int xOffset = (width - (Game.MAX_COLUMN * cellWidth)) / 2;
        int yOffset = (height - (Game.MAX_ROW * cellHeight)) / 2;
        for (int row = 0; row < Game.MAX_ROW; row++) {
            for (int col = 0; col < Game.MAX_COLUMN; col++) {
                Rectangle cell = new Rectangle(
                        xOffset + (col * cellWidth),
                        yOffset + (row * cellHeight),
                        cellWidth,
                        cellHeight);
                cells.add(cell);
            }
        }
        char [][]board = c.getOBoard();
        for(int i = 1; i < board.length - 1; i++)
        {
            for(int j = 1; j < board[i].length - 1; j++)
            {
                int index = j - 1 + ((i -1) * Game.MAX_COLUMN);
                Rectangle cell = cells.get(index);
                if(board[i][j] == 'H')
                {
                    g2d.setColor(Color.RED);
                }
                else if(board[i][j] == 'M')
                {
                    g2d.setColor(Color.GREEN);
                }
                else
                {
                    g2d.setColor(Color.decode("#C2DFFF"));
                }
                g2d.fill(cell);
            }
        }

        g2d.setColor(Color.DARK_GRAY);
        for(Rectangle cell : cells)
        {
            g2d.draw(cell);
        }
    }
}

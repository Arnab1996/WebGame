import java.awt.Color;
import java.awt.Graphics;

public class GravUp extends Item
{
	public GravUp(int x) 
	{
		super(x);
	}
	@Override
	public void performAction(Ball b) 
	{
		// TODO Auto-generated method stub
		b.setGrav(b.getGrav()+3);
	}
	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.RED);
		super.paint(g);
	}
}

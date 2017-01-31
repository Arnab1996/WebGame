import java.awt.Color;
import java.awt.Graphics;

public class GravDown extends Item
{
	public GravDown(int x) 
	{
		super(x);
	}
	@Override
	public void performAction(Ball b) 
	{
		// TODO Auto-generated method stub
		if(b.getGrav()>3)
		{
			b.setGrav(b.getGrav()-3);
			if(b.getGrav()<3)
			{
				b.setGrav(3);
			}
		}
	}
	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.GREEN);
		super.paint(g);
	}
}


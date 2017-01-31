import java.awt.Color;
import java.awt.Graphics;

public class AgilDown extends Item
{
	public AgilDown(int x) 
	{
		super(x);
	}
	@Override
	public void performAction(Ball b) 
	{
		// TODO Auto-generated method stub
		if(b.getAgility()>=2)
		{
		b.setAgility(b.getAgility()-1);
		}
	}
	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.RED);
		super.paint(g);
	}
}

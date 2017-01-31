import java.awt.Graphics;
import java.util.Random;

public class Item 
{
	private int x,y,dx,r;
	private StartingPoint sp;
	private boolean createNew=false;
	public boolean isCreateNew() {
		return createNew;
	}
	public void setCreateNew(boolean createNew) {
		this.createNew = createNew;
	}
	public Item(int x)
	{
		this.x=x;
		Random ra=new Random();
		y=ra.nextInt(400)+r;
		dx=-2;
		r=10;
	}
	public int getY()
	{
		return y;
	}
	public int getX()
	{
		return x;
	}
	public void setY(int y) 
	{
		this.y = y;
	}
	public void setX(int x) 
	{
		this.x = x;
	}
	public void update(StartingPoint sp, Ball b)
	{
		x+=dx;
		this.sp=sp;
		checkForCollection(b);
		if(x<0-r)
		{
			createNew=true;
		}
	}
	private void checkForCollection(Ball b) 
	{
		int ballX=b.getX();
		int ballY=b.getY();
		int ballR=b.getR();
		int a=x-ballX;
		int bb=y-ballY;
		int collide=r+ballR;
		double c=Math.sqrt((double)a*a+(double)bb*bb);
		if(c<collide)
		{
			performAction(b);
			x=0;
			y=sp.getHeight()+100;
			createNew=true;
		}
	}
	public void performAction(Ball b) 
	{
		
	}
	public void paint(Graphics g)
	{
		g.fillOval(x-r,y-r,2*r,2*r);
	}
}

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Random;

public class Platform 
{
	private int dx;
	private int x,y,w,h;
	Image plat;
	URL url;
	float frame=0;
	public Platform() 
	{
		dx=-1;
		x=300;
		y=300;
		w=116;
		h=38;
	}
	public Platform(int x,int y)
	{
		this.x=x;
		this.y=y;
		w=116;
		h=38;
		dx=-1;
		plat=Pictures.platform;
	}
	public void update(StartingPoint sp, Ball b)
	{
		int tester=(int)(frame+.1);
		if(tester<3)
		{
			frame+=.1;
		}
		else
		{
			frame=0;
		}
		x+=-(Pictures.level);
		checkForCollection(b);
		if(x<0-w)
		{
			Random r=new Random();
			y=sp.getHeight()-40-r.nextInt(400);
		}
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	private void checkForCollection(Ball b) 
	{
		// TODO Auto-generated method stub
		int ballX=b.getX();
		int ballY=b.getY();
		int r=b.getR();
		if (ballY+r>y && ballY+r<y+h)
		{
			if(ballX>x && ballX<x+w)
			{
				double newDy=b.getGameDy();
				b.setY(y-r);;
				b.setDy(newDy);
				Pictures.bounce.play();
			}
		}
	}
	public void paint(Graphics g)
	{
		g.drawImage(plat,x,y,x+w,y+h,0,38*(int)frame,116,38*(int)frame+38,Pictures.sp);
	}
}

import java.awt.Color;
import java.awt.Graphics;

public class Ball 
{
	private int x=400;
	private int y=25;
	private int r=20;
	private double dx=0;
	private double dy=0;
	private double gameDy=-80;
	private double grav=15;
	private double dt=.2;
	private double xf=0.95;
	private int agility=3;
	private int maxSpeed=20;
	private boolean gameOver=false;
	public Ball() 
	{
		
	}
	public double getGameDy() 
	{
		return gameDy;
	}
	public Ball(int i, int j)
	{
		x=i;
		y=j;
	}
	public int getX() 
	{
		return x;
	}
	public void setX(int x) 
	{
		this.x = x;
	}
	public int getR()
	{
		return r;
	}
	public int getY() 
	{
		return y;
	}
	public double getDx() 
	{
		return dx;
	}
	public double getDy() 
	{
		return dy;
	}
	public void setDy(double dy)
	{
		this.dy = dy;
	}
	public void setDx(double dx) 
	{
		this.dx = dx;
	}
	public void setY(int y) 
	{
		this.y = y;
	}
	public double getGrav() 
	{
		return grav;
	}
	public void setGrav(double grav) 
	{
		this.grav = grav;
	}
	public void moveRight()
	{
		if(dx+agility<maxSpeed)
		{
			dx+=agility;
		}
	}
	public int getMaxSpeed()
	{
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) 
	{
		this.maxSpeed = maxSpeed;
	}
	public int getAgility() {
		return agility;
	}
	public void setAgility(int agility) {
		this.agility = agility;
	}
	public void moveLeft()
	{
		if(dx-agility>-maxSpeed)
		{
			dx-=agility;
		}
	}
	public void update(StartingPoint sp)
	{
		if(x+dx>sp.getWidth()-r-1)
		{
			x=sp.getWidth()-r-1;
			dx=-dx;
		}
		else if(x+dx<0+r)
		{
			x=0+r;
			dx=-dx;
		}
		else
		{
			x+=dx;
		}
		if(y==sp.getHeight()-r-1)
		{
			dx*=xf;
			if(Math.abs(dx)<0.8)
			{
				dx=0;
			}
		}
		if(y-200>sp.getHeight()-r-1)
		{
			gameOver=true;
		}
		else
		{
			dy=dy+grav*dt;
			y+=dy*dt+.5*grav*dt*dt;
		}
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.GREEN);
		g.fillOval(x-r,y-r,2*r,2*r);
	}
	public boolean getGameOver() 
	{
		return gameOver;
	}
}

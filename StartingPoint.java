import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Random;
public class StartingPoint extends Applet implements Runnable,KeyListener,MouseMotionListener,MouseListener
{
	private Image i;
	private Graphics dg;
	Ball b;
	Platform p[]=new Platform[7];
	Item it[]=new Item[3];
	private long score;
	double cityX=0;
	double cityDx=.5;
	URL url;
	Image city;
	int levelcheck=0;
	boolean gameOver=false;
	boolean mouseclick=false;
	public long getScore() 
	{
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
	@Override
	public void init() 
	{
		setSize(800,600);
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		try
		{
			url=getDocumentBase();
		}
		catch(Exception e){}
		city=getImage(url, "images/Bg.jpg");
		new Pictures(this);
		Pictures.wind.play();
		Pictures.music.loop();
	}
	@Override
	public void start() 
	{
		score=0;
		b=new Ball();
		Random r=new Random();
		for(int i=0;i<p.length;i++)
		{
			p[i]=new Platform(i*116,300);
		}
		for(int i=0;i<it.length;i++)
		{
			switch(r.nextInt(5))
			{
			case 0:
				it[i]=new GravUp(getWidth()+2000*i);
				break;
			case 1:
				it[i]=new GravDown(getWidth()+2000*i);
				break;
			case 2:
				it[i]=new AgilUp(getWidth()+2000*i);
				break;
			case 3:
				it[i]=new AgilDown(getWidth()+2000*i);
				break;
			case 4:
				it[i]=new ScorePlus(getWidth()+2000*i,this);
				break;
			}
		}
		Thread thread=new Thread(this);
		thread.start();
	}
	@Override
	public void stop() 
	{
		
	}
	@Override
	public void destroy() 
	{
		
	}
	@Override
	public void update(Graphics g) 
	{
		if(i==null)
		{
			i=createImage(this.getSize().width,this.getSize().height);
			dg=i.getGraphics();
		}
		dg.setColor(getBackground());
		dg.fillRect(0, 0,this.getSize().width,this.getSize().height);
		dg.setColor(getForeground());
		paint(dg);
		g.drawImage(i,0 , 0, this);
	}
	@Override
	public void paint(Graphics g) 
	{
		g.setColor(new Color(15,77,147));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(city, (int)cityX, 0, this);
		g.drawImage(city, (int)cityX+getWidth(), 0, this);
		b.paint(g);
		for(int i=0;i<p.length;i++)
			p[i].paint(g);
		for(int i=0;i<it.length;i++)
			it[i].paint(g);
		String s=Long.toString(score);
		Font font=new Font("Serif",Font.BOLD,32);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(s,getWidth()-150+2,50+2);
		g.setColor(new Color(218,226,255));
		g.drawString(s,getWidth()-150,50);
		if(gameOver)
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("Serif",Font.BOLD,35));
			g.drawString("GAME OVER", 300, 300);
			if(mouseclick)
			{
				g.setColor(Color.RED);
				g.setFont(new Font("Serif",Font.BOLD,30));
				g.drawString("PLAY AGAIN???", 290, 350);
			}
			else
			{	
				g.setColor(Color.WHITE);
				g.setFont(new Font("Serif",Font.BOLD,24));
				g.drawString("PLAY AGAIN???", 300, 340);
			}
		}
	}
	@Override
	public void run() 
	{
		while(true)
		{
			for(int i=0;i<p.length;i++)
			{
				Random r=new Random();
				int testX=p[i].getX();
				int fakei=i;
				if(i==0)
				{
					fakei=p.length;
				}
				if(testX<0-p[i].getW())
				{
					p[i].setX(p[fakei-1].getX()+p[i].getW()+Pictures.level*10+r.nextInt(25));
				}
			}
			gameOver=b.getGameOver();
			if(levelcheck>500)
			{
				Pictures.level++;
				levelcheck=0;
			}
			levelcheck++;
			if(cityX>getWidth()*-1)
			{
				cityX-=cityDx;
			}
			else
			{
				cityX=0;
			}
			if(!gameOver)
				score++;
			Random r=new Random();
		for(int i=0;i<it.length;i++)
		{
			if(it[i].isCreateNew())
			{
				it[i]=null;
				switch(r.nextInt(5))
				{
				case 0:
					it[i]=new GravUp(getWidth()+10*r.nextInt(500));
					break;
				case 1:
					it[i]=new GravDown(getWidth()+10*r.nextInt(500));
					break;
				case 2:
					it[i]=new AgilUp(getWidth()+10*r.nextInt(500));
					break;
				case 3:
					it[i]=new AgilDown(getWidth()+10*r.nextInt(500));
					break;
				case 4:
					it[i]=new ScorePlus(getWidth()+10*r.nextInt(500),this);
					break;
				}
				it[i].setCreateNew(false);
			}
		}
			b.update(this);
			for(int i=0;i<p.length;i++)
				p[i].update(this,b);
			for(int i=0;i<it.length;i++)
				it[i].update(this,b);
			repaint();
			try 
			{
				Thread.sleep(17);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			b.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			b.moveRight();
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) 
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseDragged(MouseEvent arg0) 
	{
		
	}
	@Override
	public void mouseMoved(MouseEvent e) 
	{
		if(gameOver)
		{
		if(e.getX()>240 && e.getX()<430)
		{
			if(e.getY()>300 && e.getY()<340)
			{
				mouseclick=true;
			}
		}
		if(e.getX()<240 || e.getX()>430)
		{
			mouseclick=false;
		}
		if(e.getY()<300 || e.getY()>340)
		{
			mouseclick=false;
		}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(mouseclick)
		{
			b=null;
			b=new Ball();
			Pictures.wind.play();
			Pictures.music.loop();
			score=0;
			Pictures.level=1;
			for(int i=0;i<p.length;i++)
			{
				p[i]=new Platform(i*116,300);
			}
			for(int i=0;i<it.length;i++)
			{
				Random r=new Random();
				switch(r.nextInt(5))
				{
				case 0:
					it[i]=new GravUp(getWidth()+2000*i);
					break;
				case 1:
					it[i]=new GravDown(getWidth()+2000*i);
					break;
				case 2:
					it[i]=new AgilUp(getWidth()+2000*i);
					break;
				case 3:
					it[i]=new AgilDown(getWidth()+2000*i);
					break;
				case 4:
					it[i]=new ScorePlus(getWidth()+2000*i,this);
					break;
				}
			}
			mouseclick=false;
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
}

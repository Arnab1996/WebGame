import java.applet.AudioClip;
import java.awt.Image;
import java.net.URL;

public class Pictures 
{
	static Image platform,ball;
	URL url;
	static StartingPoint sp;
	static AudioClip music,wind,bounce;
	static int level =1;
	public Pictures(StartingPoint sp) 
	{
		try
		{
			url=sp.getDocumentBase();
		}
		catch(Exception e){}
		music=sp.getAudioClip(url,"Music/mu.AU");
		bounce=sp.getAudioClip(url,"Music/bounce.au");
		wind=sp.getAudioClip(url,"Music/wind.au");
		platform=sp.getImage(url,"images/platform.png");
		this.sp=sp;
	}
}

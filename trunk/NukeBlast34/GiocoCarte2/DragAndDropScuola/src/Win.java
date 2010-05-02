import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;



public class Win extends Panel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6558334949488660639L;
	int ms;
	Timer timer;
	Image img;
	Image buffer;
	Graphics offscr;
	
	float now=(float) 0;
	int xPos=10;
	int xMove=1;
	int yPos=0;
	int imgH=215;
	int imgW=250;
	int h;
	
	public Win(int ms){
		img=Toolkit.getDefaultToolkit().getImage("bin/smile.gif");
		//System.out.println("IMMAGINE "+img);
		timer= new Timer(ms, this);
		timer.start();
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		now+=(float)0.1;
		if(now>3)
			now=(float) 0;
		
		h= getSize().height-imgH;
		double gap=Math.sin(now)*h;
		yPos=(int)(h-gap);
		if(yPos==-1)
			yPos=h;
		xPos+=xMove;
		if(xPos > (getSize().width-imgW))
			xMove*=-1;
		if(xPos<1)
			xMove*=-1;
		
		repaint();
	}
	
	public void paint(Graphics scr){
		buffer=createImage(500,400);
		offscr=buffer.getGraphics();
		Graphics2D scr2D=(Graphics2D)scr;
		offscr.setColor(Color.white);
		offscr.fillRect(0,0,getSize().width,getSize().height);
		
		offscr.drawImage(img,(int) xPos,(int)yPos,this);
		scr2D.drawImage(buffer,0,0,this);
		offscr.dispose();
	}
	public void update(Graphics scr){
		paint(scr);
	}
}
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.applet.*;
import java.net.*;

public class Hit extends JFrame implements ActionListener,MouseMotionListener,MouseListener
{
	Container c;
	JButton bot,mute,music;
	JLabel lab1,lab2;
	Cursor cursorHamer;
	//�]�w�@�Ϊ��ܼƻP���O
	java.util.Random rg;
	int[] x=new int[3];
	int[] y=new int[3];
	int mode1,mode2,ms,isMute;//ms����
	double mt;//�ɶ�
	javax.swing.Timer t;
	Image hole = java.awt.Toolkit.getDefaultToolkit().getImage("hole.jpg");
	private	AudioClip playMusic, hitMouseMusic;
	
	public Hit(){ //�غc���A�W�٧�@��
		super("���a��");
		
		//��l�Ʀ@���ܼ�
		x[0]=200;
		x[1]=350;
		x[2]=500;
		y[0]=150;
		y[1]=300;
		y[2]=450;
		mode1=0;
		mode2=0;
		isMute=0;
		rg=new Random();
		c=getContentPane();//���oContentPane
		setBackground(); //�եέI��method
		
		//���ܷƹ��ϥ�
		Image hamer = java.awt.Toolkit.getDefaultToolkit().getImage("hammer.png");
		cursorHamer = this.getToolkit().createCustomCursor(hamer,new Point(0,0),"MyCursor");
		this.setCursor( cursorHamer );
		
		//���ֳ]�w
		String bgMusicFile = "bgmusic.wav",hitMusicFile = "hit.wav";
		try { 
			URL musicURL = new URL("file:" + System.getProperty("user.dir") + "/" + bgMusicFile);
			URL hitMusicURL = new URL("file:" + System.getProperty("user.dir") + "/" + hitMusicFile);
			playMusic = Applet.newAudioClip(musicURL);
			hitMouseMusic = Applet.newAudioClip(hitMusicURL);
		}
		catch ( MalformedURLException e ) { }
		playMusic.loop();
		
		//�]�w�����]�w
		c.setLayout(new FlowLayout());
		
		//��l��UI����
		bot=new JButton("�}�l");
		mute=new JButton("Mute");
		music=new JButton("Music");
		lab1=new JLabel("�ѤU:60��");
		lab1.setFont(new java.awt.Font("Dialog", 1, 20));
		lab1.setForeground(Color.yellow);
		lab2=new JLabel("���Z:0");
		lab2.setFont(new java.awt.Font("Dialog", 1, 20));
		lab2.setForeground(Color.yellow);

		//�NUI����[�JContentPane
		c.add(lab1);
		c.add(bot);
		c.add(lab2);
		c.add(mute);
		c.add(music);
		
		//�]�wUI����P�ƹ����ƥ�Ĳ�o��ť��
		bot.addActionListener(this);
		mute.addActionListener(this);
		music.addActionListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		t=new javax.swing.Timer(500,this);
		setSize(740,580);//�]�wsize�A��ܥX�h
		setVisible(true);
	}
	public void setBackground(){
		//Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		((JPanel)this.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("grass.jpg");
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
	}
	public void paint(Graphics g) {
		super.paint(g);//�e�X����
		g.drawImage(hole, x[0]-50,y[0]-50,100,100, this);
		g.drawImage(hole, x[0]-50,y[1]-50,100,100, this);
		g.drawImage(hole, x[0]-50,y[2]-50,100,100, this);
		g.drawImage(hole, x[1]-50,y[0]-50,100,100, this);
		g.drawImage(hole, x[1]-50,y[1]-50,100,100, this);
		g.drawImage(hole, x[1]-50,y[2]-50,100,100, this);
		g.drawImage(hole, x[2]-50,y[0]-50,100,100, this);
		g.drawImage(hole, x[2]-50,y[1]-50,100,100, this);
		g.drawImage(hole, x[2]-50,y[2]-50,100,100, this);
		//�B�~���e�ϵ{���g�b�o��
		//detectMode2(g);
		detectMode1(g);
	}
	/*public void detectMode2(Graphics g){
		Image rock = java.awt.Toolkit.getDefaultToolkit().getImage("rock.jpg");
		if(mode2==1)
			g.drawImage(rock, x[0]-50,y[0]-50,100,100, this);
		else
			g.drawImage(hole, x[0]-50,y[0]-50,100,100, this);
		if(mode2==2)
			g.drawImage(rock, x[0]-50,y[1]-50,100,100, this);
		else
			g.drawImage(hole, x[0]-50,y[1]-50,100,100, this);
		if(mode2==3)
			g.drawImage(rock, x[0]-50,y[2]-50,100,100, this);
		else
			g.drawImage(hole, x[0]-50,y[2]-50,100,100, this);
		if(mode2==4)
			g.drawImage(rock, x[1]-50,y[0]-50,100,100, this);
		else
			g.drawImage(hole, x[1]-50,y[0]-50,100,100, this);
		if(mode2==5)
			g.drawImage(rock, x[1]-50,y[1]-50,100,100, this);
		else
			g.drawImage(hole, x[1]-50,y[1]-50,100,100, this);
		if(mode2==6)
			g.drawImage(rock, x[1]-50,y[2]-50,100,100, this);
		else
			g.drawImage(hole, x[1]-50,y[2]-50,100,100, this);
		if(mode2==7)
			g.drawImage(rock, x[2]-50,y[0]-50,100,100, this);
		else
			g.drawImage(hole, x[2]-50,y[0]-50,100,100, this);
		if(mode2==8)
			g.drawImage(rock, x[2]-50,y[1]-50,100,100, this);
		else
			g.drawImage(hole, x[2]-50,y[1]-50,100,100, this);
		if(mode2==9)
			g.drawImage(rock, x[2]-50,y[2]-50,100,100, this);
		else
			g.drawImage(hole, x[2]-50,y[2]-50,100,100, this);
	};*/
	public void detectMode1(Graphics g){
		Image mouse = java.awt.Toolkit.getDefaultToolkit().getImage("mouse.jpg");
		if(mode1==1)
			g.drawImage(mouse, x[0]-50,y[0]-50,100,100, this);
		else
			g.drawImage(hole, x[0]-50,y[0]-50,100,100, this);
		if(mode1==2)
			g.drawImage(mouse, x[0]-50,y[1]-50,100,100, this);
		else
			g.drawImage(hole, x[0]-50,y[1]-50,100,100, this);
		if(mode1==3)
			g.drawImage(mouse, x[0]-50,y[2]-50,100,100, this);
		else
			g.drawImage(hole, x[0]-50,y[2]-50,100,100, this);
		if(mode1==4)
			g.drawImage(mouse, x[1]-50,y[0]-50,100,100, this);
		else
			g.drawImage(hole, x[1]-50,y[0]-50,100,100, this);
		if(mode1==5)
			g.drawImage(mouse, x[1]-50,y[1]-50,100,100, this);
		else
			g.drawImage(hole, x[1]-50,y[1]-50,100,100, this);
		if(mode1==6)
			g.drawImage(mouse, x[1]-50,y[2]-50,100,100, this);
		else
			g.drawImage(hole, x[1]-50,y[2]-50,100,100, this);
		if(mode1==7)
			g.drawImage(mouse, x[2]-50,y[0]-50,100,100, this);
		else
			g.drawImage(hole, x[2]-50,y[0]-50,100,100, this);
		if(mode1==8)
			g.drawImage(mouse, x[2]-50,y[1]-50,100,100, this);
		else
			g.drawImage(hole, x[2]-50,y[1]-50,100,100, this);
		if(mode1==9)
			g.drawImage(mouse, x[2]-50,y[2]-50,100,100, this);
		else
			g.drawImage(hole, x[2]-50,y[2]-50,100,100, this);
	};
	public void mouseDragged(MouseEvent e) { };
	public void mouseMoved(MouseEvent e){ };
	//UI����ƥ�B�z���O�g�b�o��
	public void actionPerformed(ActionEvent e){
		if (e.getSource()==bot){
			mt=60;
			ms=0;
			lab2.setText("���Z:"+ms);
			t.start();
		}
		else if(e.getSource()==mute){
			playMusic.stop();
			isMute=1;
		}
		else if(e.getSource()==music){
			playMusic.loop();
			isMute=0;
		}
		else if (e.getSource()==t){
			if(mt>0){
				mt=mt-0.5;
				mode1=rg.nextInt(9)+1;
				mode2=rg.nextInt(9)+1;
				lab1.setText("�ѤU:"+mt+"��");
				repaint();
			}
			else{
				mode1=0;
				mode2=0;
			}
		}
	}
	public boolean mousePressedArea(int x, int y, int mx, int my){
		if (( x - mx )*( x - mx )+( y - my )*( y - my ) < 2500 )
			return true;
		return false;
	}
	public void addScore(){
		if(isMute==0)
			hitMouseMusic.play();
		ms=ms+1;
		lab2.setText("���Z:"+ms);
		mode1=0;
		mode2=0;
		repaint();
	}
	public void scoreEvent(int mode, int mx, int my){
		switch(mode){
			case 1:
				if ( mousePressedArea( x[0], y[0], mx, my ))
					addScore();
				break;
			case 2:
				if ( mousePressedArea( x[0], y[1], mx, my ))
					addScore();
				break;
			case 3:
				if ( mousePressedArea( x[0], y[2], mx, my ))
					addScore();
				break;
			case 4:
				if ( mousePressedArea( x[1], y[0], mx, my ))
					addScore();
				break;
			case 5:
				if ( mousePressedArea( x[1], y[1], mx, my ))
					addScore();
				break;
			case 6:
				if ( mousePressedArea( x[1], y[2], mx, my ))
					addScore();
				break;
			case 7:
				if ( mousePressedArea( x[2], y[0], mx, my ))
					addScore();
				break;
			case 8:
				if ( mousePressedArea( x[2], y[1], mx, my ))
					addScore();
				break;
			case 9:
				if ( mousePressedArea( x[2], y[2], mx, my ))
					addScore();
				break;
		}
	}
	public void mousePressed(MouseEvent e){
		int mx,my;
		mx=e.getX();
		my=e.getY();
		scoreEvent( mode1, mx, my );
		scoreEvent( mode2, mx, my );
	};
	public void mouseEntered(MouseEvent e){ }; 
	public void mouseExited(MouseEvent e){ }; 
	public void mouseReleased(MouseEvent e){ };
	public void mouseClicked(MouseEvent e){ };

	
	/***�D�{��***/
	public static void main(String args[]){
		Hit app=new Hit();
		app.addWindowListener(new WindowAdapter(){ 
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
}
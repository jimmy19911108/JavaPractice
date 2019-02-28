import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class SimplePaint extends JFrame implements ActionListener
{
	private JRadioButtonMenuItem red, black, blue;
	private JMenuItem line,rec,circle;
	private Container c;
	Color color=Color.black;
	int x1,y1,x2,y2,draw;
	public SimplePaint(){
		super ("SimplePaint");
		c = getContentPane();
		c.setBackground(Color.white);
		
		JMenuBar jmb = new JMenuBar();
		setJMenuBar(jmb);
		
		JMenu draw = new JMenu("Draw(D)");
		draw.setMnemonic(KeyEvent.VK_D);
		draw.add(line = new JMenuItem("Line(L)",KeyEvent.VK_L));
		line.addActionListener(this);
		draw.add(rec = new JMenuItem("Rectangle(R)",KeyEvent.VK_R));
		rec.addActionListener(this);
		draw.add(circle = new JMenuItem("Circle(C)",KeyEvent.VK_C));
		circle.addActionListener(this);
		jmb.add(draw);
		
		JMenu mColor = new JMenu("Color(C)");
		mColor.setMnemonic(KeyEvent.VK_C);
		ButtonGroup buttongroup = new ButtonGroup();
		red = new JRadioButtonMenuItem("Red");
		mColor.add(red);    buttongroup.add(red);
		red.addActionListener(this);
		black = new JRadioButtonMenuItem("Black");
		mColor.add(black);  buttongroup.add(black);
		black.addActionListener(this);
		blue = new JRadioButtonMenuItem("Blue");
		mColor.add(blue);   buttongroup.add(blue);
		blue.addActionListener(this);
		jmb.add(mColor);
	}
	
   public static void main(String args[])
   {
      SimplePaint app = new SimplePaint();
      app.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      app.setSize(500,500); 
      app.setVisible(true);
   }
   public void actionPerformed(ActionEvent evt)
   {
		if ( evt.getSource() == line ){
			draw=1;
			line();
		}
		else if ( evt.getSource() == rec ){
			draw=2;
			rectangle();
		}
		else if ( evt.getSource() == circle ){
			draw=3;
			circle();
		}
		else if ( evt.getSource() == red )
			color=Color.red;
		else if ( evt.getSource() == black )
			color=(Color.black);
		else if ( evt.getSource() == blue )
			color=(Color.blue);
	}
	public void line(){
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
			   x1 = evt.getX(); y1 = evt.getY(); 
			}
			public void mouseReleased(MouseEvent evt) {
				x2 = evt.getX(); y2 = evt.getY();
				Graphics g = getGraphics();
				g.setColor(color);
				if(draw==1)
					g.drawLine(x1, y1, x2, y2);
			}
		});
	}
	public void rectangle(){
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
			   x1 = evt.getX(); y1 = evt.getY(); 
			}
			public void mouseReleased(MouseEvent evt) {
				x2 = evt.getX(); y2 = evt.getY();
				if(x1>x2){
					int temp = x1;
					x1=x2;
					x2=temp;
				}
				if(y1>y2){
					int temp = y1;
					y1=y2;
					y2=temp;
				}
				Graphics g = getGraphics();
				g.setColor(color);
				if(draw==2)
					g.drawRect(x1, y1, x2-x1, y2-y1);
			}
		});
	}
	public void circle(){
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
			   x1 = evt.getX(); y1 = evt.getY(); 
			}
			public void mouseReleased(MouseEvent evt) {
				x2 = evt.getX(); y2 = evt.getY();
				if(x1>x2){
					int temp = x1;
					x1=x2;
					x2=temp;
				}
				if(y1>y2){
					int temp = y1;
					y1=y2;
					y2=temp;
				}
				Graphics g = getGraphics();
				g.setColor(color);
				if(draw==3)
					g.drawOval(x1, y1, x2-x1, y2-y1);
			}
		});
	}
}

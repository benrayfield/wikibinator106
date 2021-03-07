package wikibinator106.plugins.voxel_y11_x11_h11_w11_symbol8_color12;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.function.Consumer;

import javax.swing.JFrame;
import javax.swing.JPanel;

/** UPDATE: symbol8 is 0 for rect/pixel, 1 for oval, -128..-1 for that thickness of line, else ascii,
and while I do plan to add unicode support later, this is a very basic UI for lambdas
that just doesnt have enough bits in a long for that and I'll design a more complex UI later.
Its max 2048x2048 resolution of pixels in a window that can be stretched, and 12 bit color.
<br><br>
OLD...
<br><br>
symbol10 has 1024 possible values that choose what to draw
in a max 2048x1024 resolution stretchable window, of 12 bit color:
-- single pixel (this could just be an optimization of rectangle)
-- rectangle (not tilted, with some position width and height)
-- font (1 byte? char? utf20? theres not enough bits) (not tilted, with some position width and height)
-- oval (not tilted, with some position width and height)
-- line (of some thickness)
<br><br>
Start with just 1-byte utf8, and later come up with some more advanced graphics system
that can handle full unicode utf20, triangle polygons, etc,
but for now I just need a way to put some simple rectangles, circles, and the chars on my keyboard on screen,
that fit in a long (64 bits).
For example, the graphics of a button that says "click me" could be a long[12]
as these 12 things: a rectangle the size of the button, 4 rectangles for its borders, and 7 fonts,
each with a color and position and width and height. A different long[12] is when the button is down vs up.
Or a checkbox is 5 rectangles for the main square and its borders and 2 diagonal lines or not, so long[7].
*/
public class UI extends JPanel implements Consumer<long[]>{
	
	//public int visiblePixelsY0To1023
	//public int visiblePixelsX0To1023 //range in pixels[(y<<10)|x], derived from getWidth() but not equal to it more of an inverse etc
	
	/** variable size. can replace this array */
	public long[] voxels;
	
	/** 2048x2048 */
	public final int[] pixelsARGB = new int[1<<22];
	
	int mouseY, mouseX;
	
	public UI(){
		addMouseMotionListener(new MouseMotionListener(){
			public void mouseMoved(MouseEvent e){
				mouseY = e.getY();
				mouseX = e.getX();
				repaint();
			}
			public void mouseDragged(MouseEvent e){
				mouseMoved(e);
			}
		});
	}
	
	public static int colorObjectTo12Bits(Color c){
		return color32ToColor12(c.getRGB());
	}
	
	/** 11111111abcd0000efgh0000ijkl0000 -> abcdefghijkl */
	public static int color32ToColor12(int color32){
		return ((color32>>>12)&0xf00)|((color32>>>8)&0xf0)|((color32>>>4)&0xf);
	}
	
	public static int color12ToColor32(int color12){
		int x = ((color12&0xf00)<<12)|((color12&0xf0)<<8)|((color12&0xf)<<4);
		x |= (x>>>4); //copy high bits of color to low bits so each color ranges 00 to ff instead of 00 to f0.
		return 0xff000000|x;
	}
	
	/** 0 to 2047 */
	public static int ya(long voxel){
		return ((int)(voxel>>>53))&0x7ff;
	}
	
	/** 0 to 2047 */
	public static int xa(long voxel){
		return ((int)(voxel>>>42))&0x7ff;
	}
	
	/** 0 to 2047 */
	public static int yb(long voxel){
		return ((int)(voxel>>>31))&0x7ff;
	}
	
	/** 0 to 2047 */
	public static int xb(long voxel){
		return ((int)(voxel>>>20))&0x7ff;
	}
	
	public static byte symbol(long voxel){
		return (byte)(voxel>>>12);
	}
	
	public static int color12(long voxel){
		return ((int)voxel)&0xfff;
	}
	
	public static int color32(long voxel){
		return color12ToColor32(color12(voxel));
	}
	
	public static byte symbolOfLineThickness(int thickness){
		return (byte)-Math.max(1, Math.min(thickness, 128));
	}
	
	public static final byte symbolOfSolidRectangleOrPixel = 0;
	
	public static final byte symbolOfCircle = 1;
	
	public static final byte symbolOfChar(char c){
		if(c < 2 || 127 < c) throw new RuntimeException("for now its ASCII only, didnt have enough bits per voxel for char (int)"+c+" aka "+c);
		return (byte)c;
	}
	
	/** only ascii, for now. will support unicode in more complex graphics system later TODO */
	public static final long[] textAt(int y, int x, int charHeight, int charWidth, int color12, String text){
		long[] ret = new long[text.length()];
		for(int i=0; i<ret.length; i++){ //FIXME above vs below (y,x)?
			ret[i] = voxel(y, x+charWidth*i, y+charHeight, x+charWidth*(i+1), symbolOfChar(text.charAt(i)), color12);
		}
		return ret;
	}
	
	public static long pixel(Point p, Color c){
		return pixel(p.x, p.y, colorObjectTo12Bits(c));
	}
	
	/** 1 pixel, in 2048x2048, with 12 bit color (such as 0xf00 is red, 0x0f0 is green, and 0x00f is blue) */
	public static long pixel(int y, int x, int color12){
		return voxel(y, x, y, x, symbolOfSolidRectangleOrPixel, color12);
	}
	
	public static long voxel(int ya, int xa, int yb, int xb, byte symbol, int color12){
		long Ya = ya&0x7ff;
		long Xa = xa&0x7ff;
		long Yb = yb&0x7ff;
		long Xb = xb&0x7ff;
		//pixelsThick = Math.max(1, Math.min(pixelsThick, 128));
		long Symbol = symbol&0xffL;
		long Color12 = color12&0xfff;
		long voxel = (Ya<<53)|(Xa<<42)|(Yb<<31)|(Xb<<20)|(Symbol<<12)|Color12;
		//System.out.println("create voxel ya"+ya+" xa"+xa+" yb"+yb+" xb"+xb+" sym"+symbol+" color"+color12);
		return voxel;
	}
	
	//public static long voxel(Rectangle rect, Color color)
	
	public static long[] checkbox(Rectangle rect, int borderThick, boolean checked){
		int backgroundColor12 = 0xbbb;
		int brightBorderColor12 = 0xddd;
		int darkBorderColor12 = 0x999;
		int xColor = 0x444;
		int ya = rect.y, xa = rect.x, yb = ya+rect.height, xb = xa+rect.width;
		int d = borderThick*2;
		return new long[]{
			voxel(ya, xa, yb, xb, symbolOfSolidRectangleOrPixel, backgroundColor12), //background
			voxel(ya, xa, ya+borderThick, xb, symbolOfSolidRectangleOrPixel, darkBorderColor12), //top border
			voxel(yb-borderThick, xa, yb, xb, symbolOfSolidRectangleOrPixel, brightBorderColor12), //bottom border
			voxel(ya, xa, yb, xa+borderThick, symbolOfSolidRectangleOrPixel, darkBorderColor12), //left border
			voxel(ya, xb-borderThick, yb, xb, symbolOfSolidRectangleOrPixel, brightBorderColor12), //right border
			
			//FIXME only if checked
			voxel(ya+d, xa+d, yb-d, xb-d, symbolOfLineThickness(borderThick), xColor), //downright line of X
			voxel(yb-d, xa+d, ya+d, xb-d, symbolOfLineThickness(borderThick), xColor), //upright line of X
		};
	}
	
	public void paint(Graphics g){
		//paint(cast to Graphics2D else what.. g, voxels);
		int h = getHeight(), w = getWidth();
		double mouseYFraction = (double)mouseY/h, mouseXFraction = (double)mouseX/w; //not always fractions cuz can go outside window a little
		g.setColor(Color.black);
		g.fillRect(0, 0, w, h);
		g.setColor(Color.blue);
		int end = 3000;
		for(int i=0; i<end; i++){
			double angle = i*.01*(.5+3*mouseYFraction);
			double radiusFraction = (double)i/end+Math.sin(angle*mouseXFraction);
			int x = (int)(w/2 + Math.sin(angle)*radiusFraction*w/2);
			int y = (int)(h/2 + Math.cos(angle)*radiusFraction*w/2);
			int r = (int)((1-radiusFraction)*8+1);
			g.fillOval(x-r, y-r, r*2, r*2);
		}
		
		paint(g, voxels);
		
		repaint();
	}
	
	/** TODO what sizes? 1..2047? */
	private static final Font[] fontOfSize;
	public static Font fontOfSize(int size){
		return fontOfSize[size];
	}
	
	/** 1..128 */
	private static final Stroke[] strokeOfThickness;
	public static Stroke strokeOfThickness(int thickness1To128){
		return strokeOfThickness[thickness1To128];
	}
	
	private static final Color[] color12ToColorObject;
	static{
		color12ToColorObject = new Color[1<<12];
		for(int color12=0; color12<color12ToColorObject.length; color12++){
			color12ToColorObject[color12] = new Color(color12ToColor32(color12));
		}
		strokeOfThickness = new Stroke[128];
		for(int i=1; i<strokeOfThickness.length; i++) strokeOfThickness[i] = new BasicStroke(i);
		fontOfSize = new Font[1<<11];
		for(int i=1; i<fontOfSize.length; i++) fontOfSize[i] = new Font(Font.MONOSPACED, 0, i);
		
	}
	
	/** reuses same 4096 Color objects */
	public static Color color12ToColorObject(int color12){
		return color12ToColorObject[color12];
	}
	
	public static void paint(Graphics g, long... voxels){
		Graphics2D G = (Graphics2D)g;
		for(long voxel : voxels){
			int ya=ya(voxel), xa=xa(voxel), yb=yb(voxel), xb=xb(voxel), color12=color12(voxel);
			
			//TODO faster way to detect if its just 1 pixel,
			//like put symbol as high byte and if its negative its a single pixel,
			//or give up a bit (so 2048x1024 instead of 2048x2048) of height andOr yStart and use that as isSinglePixel.
			//But for now just check if its size 1...
			if(ya==yb && xa==xb){
				//TODO use the int array and BufferedImage and paint them all at once,
				//but what about order of painting? A separate Graphics call for each is slow.
				//((Graphics2D)g).get
				g.setColor(color12ToColorObject(color12)); //slow for a single pixel
				g.fillRect(xa, ya, 1, 1); //slow for a single pixel
			}else{
				byte symbol = symbol(voxel);
				//System.out.println("paint voxel ya"+ya+" xa"+xa+" yb"+yb+" xb"+xb+" sym"+symbol+" color"+color12);
				g.setColor(color12ToColorObject(color12));
				if(symbol < 0){ //line
					int linePixelsThick = -symbol; //1..128
					//TODO use linePixelsThick
					G.setStroke(strokeOfThickness(linePixelsThick));
					g.drawLine(xa, ya, xb, yb);
				}else if(symbol == symbolOfSolidRectangleOrPixel){
					g.fillRect(xa, ya, xb-xa, yb-ya);
				}else if(symbol ==  symbolOfCircle){
					g.fillOval(xa, ya, xb-xa, yb-ya);
				}else{ //char
					//TODO use (xa, ya, xb, yb) rect instead of just the xa ya position and default font size
					char c = (char)symbol;
					int fontSize = Math.max(1, Math.min(yb-ya, 2047)); //TODO optimize: is this max/min needed?
					g.setFont(fontOfSize(fontSize));
					g.drawString(""+c, xa, (ya+yb)/2); //FIXME alignment should fit exactly in the given rectangle
				}
			}
		}
		
		
		
		/*
		for(long voxel : voxels){
			//If every pixels color is chosen individually, a 1024x1024 12 bit color image is 8mB.
			//If you want a solid color across it all, thats 256 squares of 64x64 pixels each as a long each so 2kB.
			//If you want a solid color background with 1000 colored circles between 1..64 radius each, thats 10kB.
			//If you also want 1000 chars of text on screen, thats 18kB.
			
			//TODO between 1x1 and 1x64 and 64x1 and 64x64, per voxel.
			//TODO theres a single 12 bit color, of (unicode20Value==0 ? perfectRectangle : a unicode text symbol/codepoint).
			int high = (int)(voxel>>>32), low = (int)voxel;
			if(high == 0){
				single pixel defined by y10x10 and colorRGB12 in low pixel
			}else{
				multi pixel
				int shape20 = TODO; //solid color if 0, circle/oval if 1, else unicode symbol 2..(2^20-1)
				int colorRGB24 = TODO; //expand 12 bit color to 24 bits
				int height = TODO; //1..64
				int width = TODO; //1..64
				int y10x10 = TODO; //low&0xfffff; 0..2^20-1, yStart=0..1023, xStart=0..1023
				
				setcolor from Color[4096]
				
				g.drawRect(int,int,int,int);
				
				or
				
				g.drawOval(int, int, int, int);
				
				or
				
				scale first
				g.drawString(unicodeStringIs1To2Chars[int20], int, int);
			}
			pixels[low&0xfffff TODO] = TODO;
		}
		*/
	}
	
	public void accept(long[] voxels){
		this.voxels = voxels;
		repaint();
	}
	
	public static long[] cat(long[]... a){
		long len = 0;
		for(long[] arr : a) len += arr.length;
		if(len > Integer.MAX_VALUE) throw new RuntimeException("Total too big: long["+len+"]");
		long[] ret = new long[(int)len];
		int offset = 0;
		for(long[] arr : a){
			System.arraycopy(arr, 0, ret, offset, arr.length);
			offset += arr.length;
		}
		return ret;
	}
	
	public static void main(String[] args){
		JFrame window = new JFrame();
		UI ui = new UI();
		long[] individualPixels = new long[1<<16];
		for(int i=0; i<individualPixels.length; i++){
			individualPixels[i] = pixel(i&0xff, i>>8, i);
		}
		ui.accept(cat(
			individualPixels,
			checkbox(new Rectangle(44,355,20,20), 2, true),
			textAt(355,64,24,12,0x57d,"hello"),
			checkbox(new Rectangle(134,355,20,20), 2, true)
		));
		window.add(ui);
		window.setSize(600,600);
		window.setVisible(true);
	}
	
	/*for(i=0; i<16; i++) document.body.innerHTML += "<div style='background-color:#0000"+"0123456789abcdef"[i]+"0'>xxx</div>";
	
	y11 x11 h11 w11 text8 color12
	
	
	y10 x12 h7 w7 codepoint16 color12

	y11 x11 h7 w7 codepoint16 color12

	y9 x9 h9 w9 codepoint16 color12

	pixel
		x y color

	line
		x y x y color

	symbol
		x y w h codepoint color

	oval
		x y w h color

	rectangle
		x y w h color


	too big: triangle
		x y x y x y color
	*/
	
	
	/*TODO use Graphics object to display smooth fonts in 4kx2k, 2kx1k etc,
	even though per-pixel graphics is always 1024x1024 stretched
	so in higher resolution the per pixel graphics will be slightly more blocky
	but text will always be as smooth as in any other program.
	In other graphics systems you can have 4kx2k or more graphics per-pixel,
	but this is just a basic kind of graphics for a prototype,
	that lambdas can generate these efficiently.
	
	TODO stretch to width or height, whichever is smaller,
	and the other hangs off the right or bottom side.
	*/
	
	/** range in pixels[(y<<10)|x], derived from getHeight() but not equal to it more of an inverse etc,
	so if getHeight()==1783 and getWidth()==3977
	it will stretch and display smooth text and slightly blocky per-pixel voxels,
	without changing the aspect-ratio of 1024x1024 as part of that will hang off whenever its not square.
	*/

}

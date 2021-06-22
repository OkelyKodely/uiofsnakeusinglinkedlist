import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class ui {
boolean zero = true;
        JPanel f = new JPanel();
     JScrollPane sp = new JScrollPane();
    JList pointsBoard = null ; 
    Vector vecto = new Vector();
    int pts = 0;

    boolean quit = false;
    boolean quitn = false;
    
    JFrame j = new JFrame();
    JPanel p = new JPanel();
    Graphics g = null;

    boolean thKeyPressed = false;
    String direction = "RIGHT";

    int i = 1;
    int x = 6;
    int y = 5;        

    LinkedList ll = null;
    
    public  ui () 
    
    {
       
        ll = new LinkedList(this);
        
        j.setLayout(null);
        p.setLayout(null);
        j.setBounds(0, 0, 1280, 800);
        f.setBounds(1000, 0, 280, 800);
        j.add(f);
        f.setBackground(Color. red) ;
        JLabel tit = new JLabel("Tit:");
        tit.setBounds(4, 10, 280, 90);
        f.add(tit);
        f.setLayout(null );
        vecto.add("100");
        vecto.add("100");
        vecto.add("100");
        vecto.add("100");
        vecto.add("100");
        vecto.add("120");
        pointsBoard = new JList(vecto);
        sp = new JScrollPane(pointsBoard);
        JScrollPane jpmorgan = sp;
        f.add(jpmorgan);
        jpmorgan.setBounds(0, 110, 280, 500); 
        j.setBackground(Color .blue ); 
        p.setBounds(0, 70, 1000, 730);
        j.add(p);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setVisible(true);

        JMenu menu;  
        JMenuItem i1, i2;  
        JMenuBar mb=new JMenuBar();  
        mb.setBackground(Color. blue) ; 
        menu=new JMenu("File");  
        
        menu. setForeground(Color. WHITE );
        i1=new JMenuItem("New");
        i1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                quitn = true;
            }
        });
        
        j.setTitle(" UCLA 7x7=49;");
        i2=new JMenuItem("Escape");
        i2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                quit = true;
                System.exit(0);
            }
        });
        menu.add(i1); menu.add(i2);
        mb.add(menu);  
        j.setJMenuBar(mb);          

        g = p.getGraphics();
        j.requestFocus();
                    
        this.move();
    }
int escaped = 0;
boolean lost = false;
float douvle = 0;
int yoy = 0;
    public void move() {
        
        
        j.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {

                thKeyPressed = true;

                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    direction = "UP";
                    y--;
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    direction = "DOWN";
                    y++;
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    direction = "LEFT";
                    x--;
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    direction = "RIGHT";
                    x++;
                }
            }
            public void keyReleased(KeyEvent e) {
            }
            public void keyTyped(KeyEvent e) {
            }
        });

        Thread t = new Thread() {
            public void run() {
                while(true) {
                    if(quit)
                        System.exit(0);
                    //Clear Screen
                    g.setColor(Color.ORANGE);
                    g.fillRect(0, 0, 1000, 800);;
                    System.out.println("SMARTASS");
                    if(yoy < 4) {
                        //Operateion .
                        lost = false;
                        escaped = 1;
                        quitn = false;
                        System.out.println("YOYOYOYO");
                        int sz = ll.sizeOfLinkedList(ll.node, 1);
                        System.out.println("xcvn,,xcv"+sz);
                        i=1;ll.deleteNode(sz-2);
                        ll.addNode(0, "" + (x) + "," + (y) + "");
                        ll.traverseLinkedList(ll.node);
                        drawBag();
                        //Put a space inBetween traversals
                        System.out.println("");
                        try {
                            Thread.sleep(500);
                            if(thKeyPressed==false)
                            {
                                if(direction.equals("UP"))
                                {
                                    y--;
                                }
                                if(direction.equals("DOWN"))
                                {
                                    y++;
                                }
                                if(direction.equals("LEFT"))
                                {
                                    x--;
                                }
                                if(direction.equals("RIGHT"))
                                {
                                    x++;
                                }
                            } else
                            {
                                thKeyPressed = false;
                            }
                            if(x >= 33) {
                                quitn = true;
                                escaped = 1;
                                lost = true;
                            }
                            else if(x <= 0) {
                                quitn = true;
                                escaped = 1;
                                lost = true;
                            }
                            else if(y >= 27) {
                                quitn = true;
                                escaped = 1;
                                lost = true;
                            }
                            else if(y <= 0) {
                                quitn = true;
                                escaped = 1;
                                lost = true;
                            }
                        } catch(Exception e) {}
                    }
                }
            }
        };

        t.start();
    }

    int aaw = 0;
    
    public void deleteAllMinusOneNode(LinkedList.Node node) {
        
        if(node.node != null) {
            aaw++;
            deleteAllMinusOneNode(node.node);
            node.node = null;
        }
        if(aaw == 1)
            node = null;
    }

    public void drawBag()

    {

        if(ll.bag.balls.isEmpty()) {
            ll.bag = ll.new Bag();
        }
        

        for(int i=0; i<ll.bag.balls.size(); i++) {

            if(ll.bag.balls.get(i).x == Integer.parseInt(ll.node.node.obj.toString().substring(0,ll.node.node.obj.toString().indexOf(","))) &&
                ll.bag.balls.get(i).y == Integer.parseInt(ll.node.node.obj.toString().substring(ll.node.node.obj.toString().indexOf(",")+1,ll.node.node.obj.toString().length()))) {
                int sz = sizeOfLinkedList(ll.node, 1);
                aa = 1;
                LinkedList.Node n = ll.getNode(ll.node, sz);
                StringTokenizer st = new StringTokenizer(n.obj.toString(), ",");
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                if(direction.equals("UP"))
                    w -= 1;
                else if(direction.equals("UP"))
                    w += 1;
                else if(direction.equals("LEFT"))
                    v -= 1;
                else if(direction.equals("RIGHT"))
                    v += 1;
                System.out.println("v,w:"+v+","+w);
                ll.addNode(sz-1, "" + v + "," + w);
                ll.bag.balls.remove(ll.bag.balls.get(i));
                pts += 15;
            } else {
                g.setColor(new Color(255, 220, 255));
                g.fillRect(ll.bag.balls.get(i).x*30, ll.bag.balls.get(i).y*30, 30, 30);
            }

        }

    }

    public int sizeOfLinkedList(LinkedList.Node n, int size)
    {
        if(n.node != null) {
            int sz = sizeOfLinkedList(n.node, size + 1);

            return sz;
        }

        return size;
    }

    int aa = 1;
    public LinkedList.Node getNode(LinkedList.Node n, int mth)
    {
        if(mth > 1) {

            System.out.println("a:" + (aa++));
            
            LinkedList.Node a = getNode(n.node, mth - 1);

            return a;
        }

        return n;
    }

    public static void main(String[] args) {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                ui ui = new ui();
            }
        };
        
        try {
            SwingUtilities.invokeAndWait(r);
        } catch(InterruptedException ie) {
            ie.printStackTrace();
        } catch(InvocationTargetException ite) {
            ite.printStackTrace();
        }
    }
}
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ui {
    
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
        j.setBounds(0, 0, 1000, 800);
        p.setBounds(j.getBounds());
        j.add(p);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setVisible(true);

        g = p.getGraphics();
        
        this.move();
    }

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
                    //Clear Screen
                    g.setColor(Color.ORANGE);
                    g.fillRect(0, 0, 1000, 800);;
                    //Operateion .
                    aa = 1;
                    int sz = ll.sizeOfLinkedList(ll.node, 1);
                    LinkedList.Node n = ll.getNode(ll.node, sz);
                    StringTokenizer st = new StringTokenizer(n.obj.toString(), ",");
                    int v = Integer.parseInt(st.nextToken());
                    int w = Integer.parseInt(st.nextToken());
                    System.out.println("v,w:"+v+","+w);
                    i=1;ll.deleteNode(sz-1);
                    i=1;ll.addNode(0, "" + (x) + "," + (y) + "");
                    ll.traverseLinkedList(ll.node);
                    drawBag();
                    //Put a space inBetween traversals
                    System.out.println("");
                    try {
                        Thread.sleep(1000);
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
                    } catch(Exception e) {}
                }
            }
        };

        t.start();
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
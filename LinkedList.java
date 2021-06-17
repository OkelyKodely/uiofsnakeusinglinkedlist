import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class LinkedList
{
    public ui ui = null;
    
    public Bag bag = new Bag();

    public Node node;
    public Node readOnlyCursor = new Node();
    public Node cur = new Node();
    public Node cursor = new Node();

    public class Ball {
        int x;
        int y;
    }

    public class Bag {
    
        public ArrayList<Ball> balls = new ArrayList<Ball>();
        
        public Bag() {
            Random r = new Random();
            for(int i=0; i<10; i++) {
    
                Ball ball = new Ball();
                ball.x = r.nextInt(32);
                ball.y = r.nextInt(26);
    
                balls.add(ball);
            }
        }
    }

    public  class Node
    {
        Node node;
        Object obj;
    }


    public  LinkedList (ui ui) 
    {
        this.ui = ui;
        
        node = new Node();
        node.obj = "" + 5 + ",5";
        cur = node;

        recurConstruct(1, node, 5);
    }

    public void recurConstruct(int i, Node node, int a) {
        
        if(i < a+1) {
            node.node = new Node();
            node.node.obj = "" + (6 - i) + ",5";
            recurConstruct(i + 1,node.node, a);
        }
    }

    public Node update(int iii, int ii, Node node) {
        
        if(iii < ii)
            return update(iii+1, ii, node.node);
        ui.i = iii;
        return node;
    }

    public Node updateDil(int iii, int ii, Node node) {
        
        if(iii < ii-1)
            return updateDil(iii+1, ii, node.node);
        ui.i = iii;
        return node;
    }

    public void deleteNode(int ii) {

        Node nBefore = node;
        cursor.node = updateDil(ui.i, ii, cur.node);
        if(ui.i-1 >= ii-1) {
            cursor.node = cursor.node.node;
            nBefore.node = null;
        } else if(ui.i-1 == ii - 2) {
            if(cursor.node.node.node != null) {
                cursor.node.node = cursor.node.node.node;
                cursor.node = null;
            } else {
                cursor.node.node = null;
            }
        }
    }

    public void addNode(int ii, String g) {

        Node nod = new Node();
        nod.obj = g;
        Node nBefore = node;
        if(ii == 0) {
            nod.node = cur.node;
            node.node = nod;
        }
        if(ii != 0) {
            cursor.node = update(ui.i, ii, cur.node);
            if(ui.i-1 >= ii) {
                nod.node = cursor.node;
                cursor = nod;
            } else if(ui.i-1 == ii - 1) {
                if(cursor.node.node != null) {
                    nod.node = cursor.node.node;
                    cursor.node.node = nod;
                } else {
                    nod.node = null;
                    cursor.node.node = nod;
                }
            }
        }
    }

    public void drawBag()
    {
        if(bag.balls.size() == 0)
            bag = new Bag();
        
        for(int i=0; i<bag.balls.size(); i++) {

            if(bag.balls.get(i).x == Integer.parseInt(node.node.obj.toString().substring(0,node.node.obj.toString().indexOf(","))) &&
                bag.balls.get(i).y == Integer.parseInt(node.node.obj.toString().substring(node.node.obj.toString().indexOf(",")+1,node.node.obj.toString().length()))) {
                int sz = sizeOfLinkedList(node, 1);
                aa = 1;
                Node n = getNode(node, sz);
                StringTokenizer st = new StringTokenizer(n.obj.toString(), ",");
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                if(ui.direction.equals("UP"))
                    w -= 1;
                else if(ui.direction.equals("UP"))
                    w += 1;
                else if(ui.direction.equals("LEFT"))
                    v -= 1;
                else if(ui.direction.equals("RIGHT"))
                    v += 1;
                System.out.println("v,w:"+v+","+w);
                addNode(sz-1, "" + v + "," + w);
                bag.balls.remove(bag.balls.get(i));
            } else {
                ui.g.setColor(new Color(255, 220, 255));
                ui.g.fillRect(bag.balls.get(i).x*30, bag.balls.get(i).y*30, 30, 30);
            }

        }

    }

    public Node traverseLinkedList(Node n)
    {

        if(n.node != null) {
            try
            {
                StringTokenizer s = new StringTokenizer(node.node.obj.toString(), ",");
                String z = s.nextToken();
                String x = s.nextToken();
                StringTokenizer st = new StringTokenizer(n.node.obj.toString(), ",");
                String a = st.nextToken();
                String b = st.nextToken();
                if(z == a && x == b)
                    System.exit(0);
                if(Integer.parseInt(a) >= 33)
                    System.exit(0);
                else if(Integer.parseInt(a) <= 0)
                    System.exit(0);
                else if(Integer.parseInt(b) >= 27)
                    System.exit(0);
                else if(Integer.parseInt(b) <= 0)
                    System.exit(0);
                Random rrr = new Random();
                int rr = rrr.nextInt(256);
                int rg = rrr.nextInt(256);
                int rb = rrr.nextInt(256);
                ui.g.setColor(new Color(rr, rg, rb));
                ui.g.fillRect(Integer.parseInt(a)*30, Integer.parseInt(b)*30, 30, 30);

                println(n.node);
            } catch(Exception e)
            {

            }

            Node ne = traverseLinkedList(n.node);

            return null;
        }

        return n;
    }

    public int sizeOfLinkedList(Node n, int size)
    {
        if(n.node != null) {
            int sz = sizeOfLinkedList(n.node, size + 1);

            return sz;
        }

        return size;
    }

    int aa = 1;
    public Node getNode(Node n, int mth)
    {
        if(mth > 1) {

            System.out.println("a:" + (aa++));
            
            Node a = getNode(n.node, mth - 1);

            return a;
        }

        return n;
    }

    public void println(Node cursor1) {

        System.out.println("Node (obj): " + 
            cursor1.obj.toString());
    }
}
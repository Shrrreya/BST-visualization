import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.util.*;

class Node 
{ 
    int data; 
    Node left, right; 
    public Node(int item) 
    { 
        data=item; 
        left=right=null; 
    } 
} 
class BinarySearchTree
{ 
    public Node root; 
    String s;

    public BinarySearchTree() 
    { 
        root=null; 
        s="";
    } 
    public void insert(int data) 
    { 
        root=insertRec(root,data); 
    } 
    public Node insertRec(Node root, int data) 
    { 
        try {
            if(root==null) 
            { 
                root=new Node(data); 
                return root; 
            } 
            if(data<root.data) 
            {
                root.left=insertRec(root.left,data); 
            }
            else if(data>root.data) 
            {
                root.right=insertRec(root.right,data); 
            }
            else
                throw new InputMismatchException();
            
        } catch (InputMismatchException e) {
            System.out.println("Must not be the same");
        }
        return root;
    } 
    public void inorder() 
    { 
        inorderRec(root); 
    } 
    public void clear()
    {
        s="";
    }
    public void inorderRec(Node root)
     { 
        if(root!=null) 
        { 
            
            inorderRec(root.left); 
            s=s+(Integer.toString(root.data)+" ");
            inorderRec(root.right); 
        } 
    } 
   
} 
public class BST_Traversal extends JFrame 
{ 

    JButton button=new JButton("ADD");
    JTextField tf=new JTextField();
    JTextArea ta=new JTextArea();
    JLabel l1=new JLabel("INORDER TRAVERSAL");
    JLabel l2=new JLabel("Donot enter 2 same values");
    JLabel l3=new JLabel("Input must be an Integer");

    mxGraphComponent graphComponent;
    mxGraph bst;
    BinarySearchTree tree=new BinarySearchTree(); 
    Object parent;
    void upd_graph(Node root, Object par, int x, int y)
    {
        if(root.left!=null && root.right!=null)
        {
            Object v1=bst.insertVertex(parent,null,Integer.toString(root.left.data),x-70,y+30,35,20);
            bst.insertEdge(par,null,"",par,v1);
            upd_graph(root.left,v1,x-70,y+30);
            Object v2=bst.insertVertex(parent,null,Integer.toString(root.right.data),x+50,y+30,35,20);
            bst.insertEdge(par,null,"",par,v2);
            upd_graph(root.right,v2,x+50,y+30);
        }
        else if(root.left!=null)
        {
            Object v1=bst.insertVertex(parent,null,Integer.toString(root.left.data),x-70,y+30,35,20);
            bst.insertEdge(par,null,"",par, v1);
            upd_graph(root.left,v1,x-70,y+30);
        }
        else if(root.right!=null)
        {
            Object v2=bst.insertVertex(parent,null,Integer.toString(root.right.data),x+50,y+30,35,20);
            bst.insertEdge(par,null,"",par,v2);
            upd_graph(root.right,v2,x+70,y+30);
        }
    }
    BST_Traversal()
    {
        bst=new mxGraph();
        l1.setBounds(50,20,200,20);
        l2.setBounds(50,45,200,10);
        button.setBounds(50,600,100,40);
        tf.setBounds(50,570,100,20);
        ta.setBounds(50,650,500,200);
        tf.setVisible(true);
        ta.setVisible(true);
        button.setVisible(true);
        button.addActionListener(new ActionListener()
        {  
            public void actionPerformed(ActionEvent e)
            {
                try {
                    if(tf.getText().equals(""))
                    {
                        return;
                    }

                    String s=tf.getText();
                    int val=Integer.valueOf(s);
                    l3.setBounds(0, 0, 0, 0);

                    tree.insert(val);
                    setSize(1000,1000);
                    parent=bst.getDefaultParent();
                    bst.getModel().beginUpdate();
                    if(tree.root!=null)
                    {
                        Object n1=bst.insertVertex(parent,null,Integer.toString(tree.root.data),500,30,35,20);
                        upd_graph(tree.root,n1,500,30);
                    }
                    bst.getModel().endUpdate();
                    tree.clear();
                    tree.inorderRec(tree.root);
                    ta.setText(tree.s);
                } catch (NumberFormatException me) {
                    System.out.println("--- Error!! Input must be an integer ---");
                    System.out.println("Please enter the value again ---> ");
                    l3.setBounds(50, 525, 200, 20);
                } 
                
            }  
        });
        graphComponent=new mxGraphComponent(bst);
        add(button);
        add(tf);
        add(ta);
        add(l1);
        add(l2);
        add(l3);
        add(graphComponent);
        pack();
    }
    public static void main(String[] args) 
    {
        BST_Traversal temp=new BST_Traversal();
        temp.setVisible(true);
        temp.setSize(1000,1000);
        temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
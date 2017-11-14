/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory_mangment;

import static inventory_mangment.login.Type;
import static inventory_mangment.login.conn;
import java.awt.Dimension;
import java.awt.List;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author Hamza Imtiaz
 */
public class add_category extends javax.swing.JFrame {
private static JTree tree;
public static ArrayList<String[]> data = new ArrayList<String[]>();

     public void populateArrayList(ArrayList<String[]> data) throws SQLException, ClassNotFoundException
      {
          
          String arr[]= null;
          PreparedStatement all_categories = conn.prepareStatement("Select * from category");
      
       ResultSet category_rows = all_categories.executeQuery();  
      
       
       while(category_rows.next()) 
       {
           arr= new String[3];
           arr[0]=category_rows.getString(1);
           
       arr[1]=category_rows.getString(2);
       arr[2]=category_rows.getString(3);
       if(arr[2].equals("-60"))
       {
           arr[2]=null;
       }
           
         data.add(arr);
       }
      
            
      }
    Collection<Element> getElementTreeFromPlainList() {
        // builds a map of elements object returned from store
        Map<String, Element> values = new HashMap<String, Element>();
        for (String[] s : data) {
            values.put(s[0], new Element(s[2], s[1]));
        }

        // creates a result list
        Collection<Element> result = new ArrayList<Element>();

        // for each element in the result list that has a parent, put it into it
        // otherwise it is added to the result list
        for (Element e : values.values()) {
            if (e.parent != null) {
                values.get(e.parent).getChildren().add(e);
            } else {
                result.add(e);
            }
        }

        return result;
    }

    void createTreeNodesForElement(final DefaultMutableTreeNode dmtn, final Collection<Element> elements) {
        // for each element a tree node is created
        for (Element child : elements) {
            DefaultMutableTreeNode created = new DefaultMutableTreeNode(child.getName());
            dmtn.add(created);
            createTreeNodesForElement(created, child.getChildren());
        }
    }

    public static class Element {
        private final String parent;
        private final String name;
        private final Collection<Element> children = new ArrayList<Element>();

        public Element(final String parent, final String name) {
            super();
            this.parent = parent;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Collection<Element> getChildren() {
            return children;
        }

    }

    /*
    Code to expand tree
    */
    public void expandAllNodes(JTree tree, int startingIndex, int rowCount){
    for(int i=startingIndex;i<rowCount;++i){
        tree.expandRow(i);
    }

    if(tree.getRowCount()!=rowCount){
        expandAllNodes(tree, rowCount, tree.getRowCount());
    }
}
    /**
     * Creates new form add_category
     */
    public add_category() throws SQLException, ClassNotFoundException {
        initComponents();
        /////////////////////////////////////////////////////
        /*
        method to populate List
        */
        this.setAlwaysOnTop (true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        tree=jTree1;
        populateArrayList(data);
        /*
        add data to Jtree
        */
         DefaultMutableTreeNode root = new DefaultMutableTreeNode("Found Nodes");

        createTreeNodesForElement(root, getElementTreeFromPlainList());

        jTree1.setModel(new DefaultTreeModel(root));
       jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
       // add(tree);
        
        
        
        
        
        
        
        ////////////////////////////////////////////////////
         
         expandAllNodes(jTree1, 0, jTree1.getRowCount());
       
         
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        category_name = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);

        jLabel3.setText("Select Parent");

        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
        );

        jLabel2.setText("Name");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel2)
                .addGap(32, 32, 32)
                .addComponent(category_name, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(146, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(category_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel1.setText("ADD CATEGORY");

        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //Where the tree is initialized:
        /*
        get value of parent
        */
        String selection = gettreevalue(jTree1);
        
    try {
        category_insertion(selection);
    } catch (SQLException ex) {
        Logger.getLogger(add_category.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(add_category.class.getName()).log(Level.SEVERE, null, ex);
    }
        



}
    /*
    Function to insert Category
    */
    public void category_insertion(String parentname) throws SQLException, ClassNotFoundException
    {
        String category_name=this.category_name.getText().toString();
        String categroy_parent=null;
        if(parentname.equals("Main"))
        {
            categroy_parent="";
        }
        else
        {
            categroy_parent=parentname;
        }
          /*
           Check ID of the Parent_name
           */ 
          int Parent_int_id=1;
          if(!categroy_parent.equals(""))
          {
        PreparedStatement parent_name = null;
        try {
            parent_name = login.conn.prepareStatement("select ID from  category where Name  =? ");
            parent_name.setString(1,categroy_parent);
             
            
             
        } catch (SQLException ex) {
            Logger.getLogger(add_category.class.getName()).log(Level.SEVERE, null, ex);
        }
          ResultSet Parent_id=null;
        
        try {
            Parent_id=parent_name.executeQuery();
            Parent_id.next();
            Parent_int_id=Integer.parseInt(Parent_id.getString(1));
        } catch (SQLException ex1) {
            Logger.getLogger(add_category.class.getName()).log(Level.SEVERE, null, ex1);
        }
          
        /*
        Code to check wether name already exists or not
        */
        PreparedStatement Name_check = null;
        try {
            Name_check = login.conn.prepareStatement("select ID from  category where Name  =? ");
            Name_check.setString(1,category_name);
             
            
             
        } catch (SQLException ex) {
            Logger.getLogger(add_category.class.getName()).log(Level.SEVERE, null, ex);
        }
          ResultSet Names_resultset=null;
        
        try {
            Names_resultset=Name_check.executeQuery();
            Names_resultset.next();
        } catch (SQLException ex) {
            Logger.getLogger(add_category.class.getName()).log(Level.SEVERE, null, ex);
        }
         String check=null;
        try {
            if(Names_resultset.next())
            {
                try {
                    check = Names_resultset.getString(1);
                } catch (SQLException ex) {
                    Logger.getLogger(add_category.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(add_category.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        /*
        check if ID exist then it will return something in check1 else 0
        */
        int check1=0;
       try
       {// if is number
              check1= Integer.parseInt(check);
       } 
       catch (NumberFormatException e)
       {
                check1=0;
    // else then do blah
       }
         
         
       
       
       if(check1>0  )
       {
           if(check1>0)
           JOptionPane.showMessageDialog(null,"Category Name Already exists");
        
               
       }
        else
       {
           if(categroy_parent.toString().equals(""))
            {
                PreparedStatement Parent_zero = null;
        try {
            Parent_zero = login.conn.prepareStatement("INSERT INTO  category(Name,Parent_id )\n" +
"VALUES (?,?)");
              Parent_zero.setString(1, this.category_name.getText().toString());
              Parent_zero.setInt(2,1 );
              
              
                Parent_zero.execute();
        } catch (SQLException ex) {
            Logger.getLogger(add_category.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
           else
           {
           PreparedStatement Parent_nonzero = null;
        try {
            Parent_nonzero = login.conn.prepareStatement("INSERT INTO  category(Name,Parent_id )\n" +
"VALUES (?,?)");
              Parent_nonzero.setString(1, this.category_name.getText().toString());
              Parent_nonzero.setInt(2,Parent_int_id );
              
              
                Parent_nonzero.execute();
        } catch (SQLException ex) {
            Logger.getLogger(add_category.class.getName()).log(Level.SEVERE, null, ex);
        }
           }
       }
       JOptionPane optionPane = new JOptionPane("Category inserted");
JDialog dialog = optionPane.createDialog("Alert");
dialog.setAlwaysOnTop(true);
dialog.setVisible(true);

       
       add_category obj=new add_category();
       obj.setVisible(true);
       this.setAlwaysOnTop(false);
       this.setVisible(false);
    }
    }

    /*
    Function to check value of selected node of Jtree
    */
    public static String gettreevalue(JTree tree ) {
    //Returns the last path element of the selection.
    //This method is useful only when the selection model allows a single selection.
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
String selected=null;
    if (node == null)
    //Nothing is selected.  
    return null;

    Object nodeInfo = node.getUserObject();

    
         selected =  (String)nodeInfo;
       // displayURL(book.bookURL);
    
    return selected;


    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(add_category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(add_category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(add_category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(add_category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
Class.forName("com.mysql.jdbc.Driver");
conn =(Connection) DriverManager.getConnection("jdbc:mysql://localhost/inventory","root","");   

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new add_category().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(add_category.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(add_category.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField category_name;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}

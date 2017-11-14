/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory_mangment;

import static inventory_mangment.login.conn;
import java.awt.Dimension;
import java.awt.Toolkit;
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
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author Hamza Imtiaz
 */
public class add_item extends javax.swing.JFrame {
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

    /**
     * Creates new form add_category
     */
    public add_item() throws SQLException, ClassNotFoundException {
        initComponents();
        /////////////////////////////////////////////////////
        /*
        method to populate List
        */
        this.setAlwaysOnTop (true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        tree=jTree1;
       // tree.getSelectionModel().setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
        populateArrayList(data);
        /*
        add data to Jtree
        */
         DefaultMutableTreeNode root = new DefaultMutableTreeNode("Categories");

        createTreeNodesForElement(root, getElementTreeFromPlainList());

        jTree1.setModel(new DefaultTreeModel(root));
       jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
       // add(tree);
        
        new add_category().expandAllNodes(jTree1, 0, jTree1.getRowCount());
        
        
    }
    
    /*
    Function to check Parent ID
    */
    public void item_insertion(String[] parentname) throws SQLException, ClassNotFoundException
    {
        String item_name=this.item_name.getText().toString();
        if(item_name.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please Enter Item Name");
        }
        else
        {
        String categroy_parent[]=new String[parentname.length];
       
        
            for(int i=0;i<parentname.length;i++)
            {
                
            categroy_parent[i]=parentname[i];
            }
        
          /*
           Check ID of the Parent_name
           */ 
          int Parent_int_id[]=new int[parentname.length];
          
           for(int i=1;i<parentname.length;i++)
              {
                  if(!categroy_parent[i].equals("Main"))
                  {
                      PreparedStatement parent_name = null;
                      try {
                          parent_name = login.conn.prepareStatement("select ID from  Category where Name  =? ");
                          parent_name.setString(1, categroy_parent[i]);

                      } catch (SQLException ex) {
                          Logger.getLogger(add_item.class.getName()).log(Level.SEVERE, null, ex);
                      }

                      ResultSet Parent_id = null;

                      try {
                          Parent_id = parent_name.executeQuery();
                          Parent_id.next();
                          Parent_int_id[i] = Integer.parseInt(Parent_id.getString(1));
                      } catch (SQLException ex) {
                          Logger.getLogger(add_item.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  }
                  else {
                      Parent_int_id[i] = -60;
                  }
          }
              
          
        
        /*
          code to insert data in pivot
          */
        int count=1;
        for(int i=0;i<parentname.length;i++)
       {
           
           PreparedStatement Parent_nonzero = null;
        try {
            Parent_nonzero = login.conn.prepareStatement("INSERT INTO  pivot(ID,Name,Parent )\n" +
"VALUES (?,?,?)");
            Parent_nonzero.setInt(1,count);
              Parent_nonzero.setString(2, this.item_name.getText().toString());
              Parent_nonzero.setInt(3,Parent_int_id[i] );
              
              
                Parent_nonzero.execute();
        } catch (SQLException ex) {
            Logger.getLogger(add_item.class.getName()).log(Level.SEVERE, null, ex);
        }
           }
        count++;
            insert_in_item_table(Parent_int_id[Parent_int_id.length-1]);
       JOptionPane optionPane = new JOptionPane("Item inserted");
JDialog dialog = optionPane.createDialog("Alert");
dialog.setAlwaysOnTop(true);
dialog.setVisible(true);

      
       add_category obj=new add_category();
       obj.setVisible(true);
       this.setVisible(false);
        }
    }
    /*
    Function to insert in item table
    */
    public void insert_in_item_table(int parentid)
    {
        PreparedStatement Parent_nonzero = null;
        try {
            Parent_nonzero = login.conn.prepareStatement("INSERT INTO  item(Name,Parent_id )\n" +
"VALUES (?,?)");
           
              Parent_nonzero.setString(1, this.item_name.getText().toString());
              Parent_nonzero.setInt(2,parentid );
              
              
                Parent_nonzero.execute();
        } catch (SQLException ex) {
            Logger.getLogger(add_item.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    Function to check value of selected node of Jtree
    */
    public static String[] valueChanged() {
    //Returns the last path element of the selection.
    //This method is useful only when the selection model allows a single selection.
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
String selected=null;
    if (node == null)
    //Nothing is selected.  
    return null;
    String[] jTreeVarSelectedPath = null;
    if(!node.isLeaf())
    {
        JOptionPane.showMessageDialog(null, "Please Select Leaf Node");
    }
    else
    {

    Object[] paths = tree.getSelectionPath().getPath();
    jTreeVarSelectedPath = new String[paths.length];
    for (int i=0; i<paths.length; i++) {
        jTreeVarSelectedPath[i] = paths[i].toString();
    }
        
    }
   

    
    return jTreeVarSelectedPath;


    }                                        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        item_name = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Add Item");

        jLabel2.setText("Item Name");

        item_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_nameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel2)
                .addGap(59, 59, 59)
                .addComponent(item_name, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(item_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButton1.setText("OK");
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
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
                                                 
        // TODO add your handling code here:
        //Where the tree is initialized:
        /*
        get value of parent
        */
        String selection[] = valueChanged();
        if(selection==null)
        {
           JOptionPane.showMessageDialog(null, "No Value Inserted");
        }
        else
        {
            try {
                item_insertion(selection);
            } catch (SQLException ex) {
                Logger.getLogger(add_item.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(add_item.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        




    }//GEN-LAST:event_jButton1ActionPerformed

    private void item_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_item_nameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(add_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(add_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(add_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(add_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new add_item().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(add_item.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(add_item.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField item_name;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}


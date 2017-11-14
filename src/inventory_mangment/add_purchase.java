/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory_mangment;

import static inventory_mangment.add_category.gettreevalue;
import static inventory_mangment.add_item.data;
import static inventory_mangment.login.conn;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
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
public class add_purchase extends javax.swing.JFrame {

    /*
    code for Jtree
    */
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
     * Creates new form add_purchase
     */
    public add_purchase() throws SQLException, ClassNotFoundException {
        initComponents();
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
       new add_category().expandAllNodes(jTree1, 0, jTree1.getRowCount());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        quantity = new javax.swing.JTextField();
        amount = new javax.swing.JTextField();
        rate = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        save_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Add Purchase");

        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel2.setText("Quantity:");

        jLabel3.setText("Amount:");

        jLabel4.setText("Rate:");

        quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityActionPerformed(evt);
            }
        });

        amount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                amountMouseClicked(evt);
            }
        });
        amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountActionPerformed(evt);
            }
        });

        rate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rateMouseClicked(evt);
            }
        });
        rate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rateActionPerformed(evt);
            }
        });

        jLabel5.setText("Date");

        jLabel6.setText("Item");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(30, 30, 30))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rate, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(102, 102, 102))
        );

        save_button.setText("Save");
        save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(153, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(save_button, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(save_button, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
    FUNCTION TO GET REMAINIG STOCK 
    */
    public int getremainingstock(String selection,String item) throws SQLException
    {
      //  int  stock_rate[]= new int [2];
          PreparedStatement remaining_stock = conn.prepareStatement("Select stock from purchases where Categroy=? and Item=? ");
      remaining_stock.setString(1, selection);
      remaining_stock.setString(2, item);
       ResultSet remaining_stock_rows = remaining_stock.executeQuery();  
      
       int sum=0,current=0;
       while(remaining_stock_rows.next()) 
       {
           sum=Integer.parseInt(remaining_stock_rows.getString(1));
          
           
       }
       return sum;
    }
    
    /*
    FUNCTION TO GET LATEST RATE
    */
    public float getlatestrate(String selection,String item) throws SQLException
    {
       // int  stock_rate[]= new int [2];
          PreparedStatement remaining_stock = conn.prepareStatement("Select latest_rate from purchases where Categroy=? and Item=? ");
      remaining_stock.setString(1, selection);
      remaining_stock.setString(2, item);
       ResultSet remaining_stock_rows = remaining_stock.executeQuery();  
      
       float rate=0;
       while(remaining_stock_rows.next()) 
       {
          
          rate=Float.parseFloat(remaining_stock_rows.getString(1));
           
       }
       return rate;
    }
    
    String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }
    private void save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_buttonActionPerformed
        // TODO add your handling code here:
        /*
        get tree value. Function from add category
        */
         String selection = gettreevalue(this.jTree1);
         String quantity=this.quantity.getText().toString();
         String amount= this.amount.getText().toString();
         String rate= this.rate.getText().toString();
         Date date= this.jDateChooser1.getDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        
        String year=Integer.toString(cal.get(Calendar.YEAR));

        String entry_month = getMonthForInt(month);

         
         String item=jComboBox1.getSelectedItem().toString();
         int check=0;
         if(quantity.equals("")||amount.equals("")||rate.equals("")||date.equals("")||selection==null)
               {
                   if(quantity.equals("")||quantity.equals("0"))
                    JOptionPane.showMessageDialog(null, "Quantity can not be empty or 0");
                   else if(amount.equals("")||amount.equals("0"))
                         JOptionPane.showMessageDialog(null, "Amount can not be empty or 0");
                   else if(rate.equals("")||rate.equals("0"))
                        JOptionPane.showMessageDialog(null, "Rate can not be empty or 0");
                   else if(date.toString().equals(""))
                       JOptionPane.showMessageDialog(null, "Date can not be empty");
                   else if(selection==null)
                       JOptionPane.showMessageDialog(null, "Please Select category");
                       
                       
               }
         else
         {
         int remaining_stock=0;
         float latest_rate=0;
             try {
                  remaining_stock=  getremainingstock(selection,item);
                  latest_rate=getlatestrate(selection,item);
             } catch (SQLException ex) {
                 Logger.getLogger(add_purchase.class.getName()).log(Level.SEVERE, null, ex);
             }
         DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
         
         int quantity_int= Integer.parseInt(quantity);
         float amount_float= Float.parseFloat(amount);
         float rate_float=Float.parseFloat(rate);
         
         int updated_stock=quantity_int+remaining_stock;
         float updated_rate=0;
         if(latest_rate!=0)
          updated_rate=(rate_float+latest_rate)/2;
         else
             updated_rate=rate_float;
         Date entry_date=date;
       
        
        try {
            insertValuesinPurchase(selection,quantity_int,amount_float,rate_float,entry_date,item,updated_stock,updated_rate,entry_month,year);
        } catch (SQLException ex) {
            Logger.getLogger(add_purchase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(add_purchase.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
        
    }//GEN-LAST:event_save_buttonActionPerformed
    /*
    function to add value in purcahses
    */ 
    public void insertValuesinPurchase(String selection,int quantity,float amount,float rate, Date date,String item,int remaining_stock,float updated_rate,String month, String year) throws SQLException, ClassNotFoundException
      {
        PreparedStatement add_purchase = null;
        try {
            add_purchase = login.conn.prepareStatement("INSERT INTO  Purchases(Categroy	,Quantity,Amount,Rate,Date,Item,stock,latest_rate,Month,Year)\n"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)");
            add_purchase.setString(1, selection);
            add_purchase.setInt(2, quantity);
            add_purchase.setFloat(3, amount);
            add_purchase.setFloat(4, rate);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
            add_purchase.setDate(5, sqlDate);
            add_purchase.setString(6, item);
            add_purchase.setInt(7, remaining_stock);
            add_purchase.setFloat(8, updated_rate);
            add_purchase.setString(9, month);
            add_purchase.setString(10, year);

            add_purchase.execute();
        } catch (SQLException ex) {
            Logger.getLogger(add_purchase.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane optionPane = new JOptionPane("Purchase inserted");
JDialog dialog = optionPane.createDialog("Alert");

dialog.setAlwaysOnTop(true);
dialog.setVisible(true);

      
        add_purchase obj=new add_purchase();
       obj.setVisible(true);
       this.setVisible(false);
    }
      
    private void quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityActionPerformed

    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged
        // TODO add your handling code here:
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
        int id=0;
        if(node.isLeaf())
    {
        Object nodeInfo = node.getUserObject();

    
        String selected = (String) nodeInfo;

        PreparedStatement parent_name = null;
        try {
            parent_name = login.conn.prepareStatement("select ID from  Category where Name  =? ");
            parent_name.setString(1, selected);

        } catch (SQLException ex) {
            Logger.getLogger(add_item.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet Parent_id = null;

        try {
            Parent_id = parent_name.executeQuery();
            Parent_id.next();
            id = Integer.parseInt(Parent_id.getString(1));
        } catch (SQLException ex) {
            Logger.getLogger(add_item.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
                      CODE TO POPULATE JCOMBO BOX
         */

        Statement st = null;
        ArrayList<String> groupNames = new ArrayList<String>();
        //System.out.println(Vehicle_Category.selected_item);
        PreparedStatement statement = null;
        try {
            statement = login.conn.prepareStatement("SELECT Name  FROM  item where Parent_id=? ");
        } catch (SQLException ex) {
            Logger.getLogger(add_purchase.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            statement.setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(add_purchase.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet rs1 = null;
        try {
            rs1 = statement.executeQuery();
            // companies.setSize(500, j.getPreferredSize().height);;
        } catch (SQLException ex) {
            Logger.getLogger(add_purchase.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (rs1.next()) {
                //System.out.println(rs1.getString(2));
                String groupName = rs1.getString(1);
                // add group names to the array list
                groupNames.add(groupName);
                //  companies.setPrototypeDisplayValue(rs1.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(add_purchase.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(add_purchase.class.getName()).log(Level.SEVERE, null, ex);
        }

// Populate the combo box
        DefaultComboBoxModel model = new DefaultComboBoxModel(groupNames.toArray());
        jComboBox1.setModel(model); 
    }
        
        
    }//GEN-LAST:event_jTree1ValueChanged

    private void amountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_amountMouseClicked
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_amountMouseClicked

    private void rateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rateMouseClicked
        // TODO add your handling code here:
        String quantity=this.quantity.getText().toString();
         String amount= this.amount.getText().toString();
         int quantity_int= Integer.parseInt(quantity);
         float amount_float= Float.parseFloat(amount);
         
         float rate=amount_float/quantity_int;
         
         this.rate.setText(Float.toString(rate));
    }//GEN-LAST:event_rateMouseClicked

    private void rateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rateActionPerformed

    private void amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountActionPerformed

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
            java.util.logging.Logger.getLogger(add_purchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(add_purchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(add_purchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(add_purchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new add_purchase().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(add_purchase.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(add_purchase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amount;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JTextField quantity;
    private javax.swing.JTextField rate;
    private javax.swing.JButton save_button;
    // End of variables declaration//GEN-END:variables
}

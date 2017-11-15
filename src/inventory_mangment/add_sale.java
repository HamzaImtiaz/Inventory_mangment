/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory_mangment;

import com.sun.javafx.geom.AreaOp;
import static inventory_mangment.add_category.gettreevalue;
import static inventory_mangment.add_purchase.data;
import  inventory_mangment.add_purchase;
import static inventory_mangment.add_purchase.data;
import static inventory_mangment.login.conn;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
public class add_sale extends javax.swing.JFrame {

    /**
     * Creates new form add_sale
     */
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

    String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }
    
    
    public add_sale() throws SQLException, ClassNotFoundException {
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        quantity = new javax.swing.JTextField();
        amount = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        quantity_left = new javax.swing.JTextField();
        save_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 297, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel1.setText("Add Sale");

        jLabel2.setText("Quantity:");

        jLabel3.setText("Amount:");

        quantity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quantityMouseClicked(evt);
            }
        });
        quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityActionPerformed(evt);
            }
        });

        amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountActionPerformed(evt);
            }
        });

        jLabel5.setText("Date");

        jLabel6.setText("Item");

        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 51));
        jLabel7.setText("<html> <b>Quantity Left</b> </html>");

        quantity_left.setFont(new java.awt.Font("Perpetua Titling MT", 1, 14)); // NOI18N
        quantity_left.setForeground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(quantity_left, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                            .addComponent(jLabel3))
                        .addGap(243, 243, 243))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantity_left, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(167, 167, 167))
        );

        save_button.setText("SAVE");
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
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(save_button, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(save_button, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged
        // TODO add your handling code here:
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
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
                Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
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

    private void quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityActionPerformed
        // TODO add your handling code here:
     
    }//GEN-LAST:event_quantityActionPerformed

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
      
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void quantityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quantityMouseClicked
        // TODO add your handling code here:
          // TODO add your handling code here:
        String selection = gettreevalue(this.jTree1);
      String item=jComboBox1.getSelectedItem().toString();
      
      int remaining_quantity=0;
       // int  stock_rate[]= new int [2];
          PreparedStatement remaining_stock = null;
        try {
            remaining_stock = conn.prepareStatement("Select stock from purchases where Categroy=? and Item=? ");
        } catch (SQLException ex) {
            Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            remaining_stock.setString(1, selection);
              remaining_stock.setString(2, item);
        } catch (SQLException ex) {
            Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
        }
    
       ResultSet remaining_stock_rows=null;  
        try {
            remaining_stock_rows = remaining_stock.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       
        try {
            while(remaining_stock_rows.next())
            {
                
                remaining_quantity=Integer.parseInt(remaining_stock_rows.getString(1));
                
            }} catch (SQLException ex) {
            Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
        }
       this.quantity_left.setText(Integer.toString(remaining_quantity));
    
    
    }//GEN-LAST:event_quantityMouseClicked
    public void insertValuesincost_of_sale(String selection, String item, int quantity_int,Date date,String month,String Year) throws SQLException, ClassNotFoundException
    {
        float latest_rate= new add_purchase().getlatestrate(selection,item);
        
       float value=latest_rate*quantity_int;
       
       PreparedStatement add_sales = null;
        java.sql.Date sqlDate=null;
        try {
            add_sales = login.conn.prepareStatement("INSERT INTO  cost_of_sales(Quantity,rate,value,Date,Month,Year)\n"
                    + "VALUES (?,?,?,?,?,?)");
            add_sales.setInt(1,quantity_int);
            add_sales.setFloat(2, latest_rate);
            add_sales.setFloat(3, value);
            add_sales.setDate(4,new java.sql.Date(date.getTime()));
            add_sales.setString(5, month);
            add_sales.setString(6, Year);
           
            

            add_sales.execute();
        } catch (SQLException ex) {
            Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    private void save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_buttonActionPerformed
        // TODO add your handling code here:
         String selection = gettreevalue(this.jTree1);
         String quantity=this.quantity.getText().toString();
         String remaining_quantity=quantity_left.getText().toString();
         String amount= this.amount.getText().toString();
         float amount_float=Float.parseFloat(amount);
         Date date= this.jDateChooser1.getDate();
         
          Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        
        String year=Integer.toString(cal.get(Calendar.YEAR));

        String entry_month = getMonthForInt(month);
         String item=jComboBox1.getSelectedItem().toString();
         int check=0;
         if(quantity.equals("")||amount.equals("")||date.equals("")||selection==null)
               {
                   if(quantity.equals("")||quantity.equals("0"))
                    JOptionPane.showMessageDialog(null, "Quantity can not be empty or 0");
                   else if(amount.equals("")||amount.equals("0"))
                         JOptionPane.showMessageDialog(null, "Amount can not be empty or 0");
                   else if(date.toString().equals(""))
                       JOptionPane.showMessageDialog(null, "Date can not be empty");
                   else if(selection==null)
                       JOptionPane.showMessageDialog(null, "Please Select category");
                       
                       
               }
         else
         {
             int quantity_int=Integer.parseInt(quantity);
             int remaining_qunatity_int=Integer.parseInt(remaining_quantity);
             if(quantity_int>remaining_qunatity_int)
             {
                 JOptionPane.showMessageDialog(null, "Quantity is greater than stock available");
             }
             else
             {
                  
                 try {
                     insertValuesincost_of_sale(selection,item,quantity_int,date,entry_month,year);
                     insertValuesinsales(selection, quantity_int, amount_float, date, item,entry_month,year);
                 } catch (SQLException ex) {
                     Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (ClassNotFoundException ex) {
                     Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
             }
        
            
         }
        
    }//GEN-LAST:event_save_buttonActionPerformed

    private void amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed
public void insertValuesinsales(String selection,int quantity,float amount, Date date,String item,String month,String Year) throws SQLException, ClassNotFoundException
{
        PreparedStatement add_sales = null;
        java.sql.Date sqlDate=null;
        try {
            add_sales = login.conn.prepareStatement("INSERT INTO  sales(Categroy,Quantity,Amount,Date,Item,Month,Year)\n"
                    + "VALUES (?,?,?,?,?,?,?)");
            add_sales.setString(1, selection);
            add_sales.setInt(2, quantity);
            add_sales.setFloat(3, amount);
            sqlDate = new java.sql.Date(date.getTime()); 
            add_sales.setDate(4, sqlDate);
            add_sales.setString(5, item);
            add_sales.setString(6, month);
            add_sales.setString(7, Year);
            

            add_sales.execute();
        } catch (SQLException ex) {
            Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int id=getIDlastsale(selection,item,sqlDate);
        int purchase_id=getQuantity(selection,item)[0];
        int update_quantity=getQuantity(selection,item)[1];
        
        int final_quantity=update_quantity-quantity;
        updatesales(purchase_id,id,final_quantity);
        JOptionPane optionPane = new JOptionPane("Sale inserted");
JDialog dialog = optionPane.createDialog("Alert");
dialog.setAlwaysOnTop(true);
dialog.setVisible(true);

      
        add_sale obj=new add_sale();
       obj.setVisible(true);
       this.setVisible(false);
    }
public int[] getQuantity(String selection, String item)
{
    PreparedStatement remaining_stock = null;
    int remaining_quantity[]=new int [2];
        try {
            remaining_stock = conn.prepareStatement("Select ID,stock from purchases where Categroy=? and Item=? ");
        } catch (SQLException ex) {
            Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            remaining_stock.setString(1, selection);
              remaining_stock.setString(2, item);
        } catch (SQLException ex) {
            Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
        }
    
       ResultSet remaining_stock_rows=null;  
        try {
            remaining_stock_rows = remaining_stock.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       
        try {
            while(remaining_stock_rows.next())
            {
                
                remaining_quantity[0]=Integer.parseInt(remaining_stock_rows.getString(1));
                 remaining_quantity[1]=Integer.parseInt(remaining_stock_rows.getString(2));
                
            }} catch (SQLException ex) {
            Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
        }
       return remaining_quantity;
    
}
    public int getIDlastsale(String selection,String item,java.sql.Date sqlDate)
    {
        PreparedStatement last_id = null;
        try {
            last_id = conn.prepareStatement("Select ID from sales where Categroy=? and Item=? and Date=?");
        } catch (SQLException ex) {
            Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            last_id.setString(1, selection);
              last_id.setString(2, item);
              last_id.setDate(3, sqlDate);
        } catch (SQLException ex) {
            Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
        }
    
       ResultSet last_id_rows=null;  
        try {
            last_id_rows = last_id.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       int id=0;
        try {
            while(last_id_rows.next())
            {
                
                id=Integer.parseInt(last_id_rows.getString(1));
                
            }} catch (SQLException ex) {
            Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
        }
       return id;
        
    }
    public void updatesales(int purchase_id,int id,int quantity)
    {
        PreparedStatement update_purchase = null;
        try {
            update_purchase = conn.prepareStatement("update purchases set update_sale_id=?,stock=? where ID=? ");
        } catch (SQLException ex) {
            Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            update_purchase.setInt(1, id);
              update_purchase.setInt(2, quantity);
              update_purchase.setInt(3, purchase_id);
             
        } catch (SQLException ex) {
            Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
        }
    
     
        try {
            update_purchase.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       
    }
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
            java.util.logging.Logger.getLogger(add_sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(add_sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(add_sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(add_sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new add_sale().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(add_sale.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JTextField quantity;
    private javax.swing.JTextField quantity_left;
    private javax.swing.JButton save_button;
    // End of variables declaration//GEN-END:variables
}

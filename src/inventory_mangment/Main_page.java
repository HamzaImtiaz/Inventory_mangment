/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory_mangment;

import com.toedter.calendar.JDateChooser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hamza Imtiaz
 */
public class Main_page extends javax.swing.JFrame {

    /**
     * Creates new form Main_page
     */
    int filtered=0;
    public Main_page() throws SQLException {
        initComponents();
       
           jDateChooser1.setVisible(true);
            jMonthChooser1.setVisible(false);
            jYearChooser1.setVisible(false);
        populate_sales(); //Code to Populate Sales Tab
        populate_purchase();//Code to Populate Purchase
        populate_inventory();//Code to Populate Purchase
          
            
        
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
    public void populate_sales() throws SQLException
    {
        
       ResultSet rs1=null;
           PreparedStatement pst = null;
        
            if(filtered==1)
            {
            pst = login.conn.prepareStatement("Select \"Select ID , Quantity ,Categroy, Amount from sales where Date=?");
             pst.setDate(1,  new java.sql.Date(jDateChooser1.getDate().getTime()));
              
            }
            else if(filtered==2)
            {
                pst = login.conn.prepareStatement("Select \"Select ID , Quantity ,Categroy, Amount from sales where Month=?");
             pst.setString(1,  getMonthForInt(jMonthChooser1.getMonth()));
             
            }
            else if(filtered==3)
            {
                
                 pst = login.conn.prepareStatement("Select ID , Quantity ,Categroy, Amount from sales where Year=? ");
                 pst.setString(1,  Integer.toString(jYearChooser1.getYear()));
            }
            else
            {
                pst = login.conn.prepareStatement("Select ID , Quantity ,Categroy, Amount from sales  ");
            }
            
       
      
        try {
           
             rs1 = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(sales_table.getRowCount() > 0) 
        {
            ((DefaultTableModel) sales_table.getModel()).removeRow(0);
        }
       int columns=0;
        try {
            columns = rs1.getMetaData().getColumnCount();
        } catch (SQLException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs1.next())
            {
                Object[] row = new Object[columns];
                for (int i = 1; i <= columns; i++)
                {
                    row[i - 1] = rs1.getObject(i);
                }
                ((DefaultTableModel) sales_table.getModel()).insertRow(rs1.getRow()-1,row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        }
     
         
    }
    
    public void populate_purchase() throws SQLException
    {
        {
       ResultSet rs1=null;
           PreparedStatement pst = null;
        
            /*if(check.equals("Date"))
            {
            pst = login.conn.prepareStatement("Select Date,Tyre_name ,Tyre_size,Quantity,Price_per_tyre,total_price,invoice_no,Detail,invoice_detail,companies_list.payment_due from stock join companies_list on stock.company_name=companies_list.Company_name where check_date=? and stock.company_name=?");
             pst.setString(1,  Date);
              pst.setString(2,  company_name);
            }
            else if(check.equals("Between Two Dates"))
            {
                String Date1=Date.substring(0, 10);
                String Date2=Date.substring(11, 21);
                
                System.out.println(Date1);
                System.out.println(Date2);
                pst = login.conn.prepareStatement("Select Date,Tyre_name ,Tyre_size,Quantity,Price_per_tyre,total_price,invoice_no,Detail,invoice_detail,companies_list.payment_due from stock join companies_list on stock.company_name=companies_list.Company_name where check_date between ? and ? and stock.company_name=?");
             pst.setString(1,  Date1);
              pst.setString(2,  Date2);
              pst.setString(3,  company_name);
             
            }
            else
            {*/
                
                 pst = login.conn.prepareStatement("Select ID ,Categroy, Quantity , Amount from purchases ");
            
            
       
      
        try {
           
             rs1 = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(purchase_table.getRowCount() > 0) 
        {
            ((DefaultTableModel) purchase_table.getModel()).removeRow(0);
        }
       int columns=0;
        try {
            columns = rs1.getMetaData().getColumnCount();
        } catch (SQLException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs1.next())
            {
                Object[] row = new Object[columns];
                for (int i = 1; i <= columns; i++)
                {
                    row[i - 1] = rs1.getObject(i);
                }
                ((DefaultTableModel) purchase_table.getModel()).insertRow(rs1.getRow()-1,row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        }
     
         
    }
    }
    
    public void populate_inventory() throws SQLException
    {
        {
        {
       ResultSet rs1=null;
           PreparedStatement pst = null;
        
            /*if(check.equals("Date"))
            {
            pst = login.conn.prepareStatement("Select Date,Tyre_name ,Tyre_size,Quantity,Price_per_tyre,total_price,invoice_no,Detail,invoice_detail,companies_list.payment_due from stock join companies_list on stock.company_name=companies_list.Company_name where check_date=? and stock.company_name=?");
             pst.setString(1,  Date);
              pst.setString(2,  company_name);
            }
            else if(check.equals("Between Two Dates"))
            {
                String Date1=Date.substring(0, 10);
                String Date2=Date.substring(11, 21);
                
                System.out.println(Date1);
                System.out.println(Date2);
                pst = login.conn.prepareStatement("Select Date,Tyre_name ,Tyre_size,Quantity,Price_per_tyre,total_price,invoice_no,Detail,invoice_detail,companies_list.payment_due from stock join companies_list on stock.company_name=companies_list.Company_name where check_date between ? and ? and stock.company_name=?");
             pst.setString(1,  Date1);
              pst.setString(2,  Date2);
              pst.setString(3,  company_name);
             
            }
            else
            {*/
                
                 pst = login.conn.prepareStatement("select Item,Categroy,stock\n" +
"from purchases\n" +
"WHERE ID= \n" +
"(\n" +
"    select max(ID)\n" +
"from purchases\n" +
"group by Item,Categroy\n" +
")\n" +
"group by Item,Categroy ");
            
            
       
      
        try {
           
             rs1 = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(inventory_table.getRowCount() > 0) 
        {
            ((DefaultTableModel) inventory_table.getModel()).removeRow(0);
        }
       int columns=0;
        try {
            columns = rs1.getMetaData().getColumnCount();
        } catch (SQLException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs1.next())
            {
                Object[] row = new Object[columns];
                for (int i = 1; i <= columns; i++)
                {
                    row[i - 1] = rs1.getObject(i);
                }
                ((DefaultTableModel) inventory_table.getModel()).insertRow(rs1.getRow()-1,row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        }
     
         
    }
    }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Sales_tab = new javax.swing.JTabbedPane();
        sales_tab = new javax.swing.JScrollPane();
        sales_table = new javax.swing.JTable();
        Purchase_tab = new javax.swing.JScrollPane();
        purchase_table = new javax.swing.JTable();
        Inventory = new javax.swing.JScrollPane();
        inventory_table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        filter = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fruit Managment System");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 0, 153));
        setBounds(new java.awt.Rectangle(0, 0, 1650, 1080));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Baskerville Old Face", 1, 24)); // NOI18N
        setForeground(new java.awt.Color(255, 0, 153));

        Sales_tab.setBackground(new java.awt.Color(0, 0, 0));
        Sales_tab.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Sales_tab.setForeground(new java.awt.Color(194, 185, 185));

        sales_tab.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        sales_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Quantity", "Category", "Amount"
            }
        ));
        sales_tab.setViewportView(sales_table);

        Sales_tab.addTab("Sales", sales_tab);

        purchase_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Category", "Quantity", "Amount"
            }
        ));
        Purchase_tab.setViewportView(purchase_table);

        Sales_tab.addTab("Purchase", Purchase_tab);

        inventory_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Category", "Item", "Stock"
            }
        ));
        Inventory.setViewportView(inventory_table);

        Sales_tab.addTab("Inventory", Inventory);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("<html> <u>Add Category</u> </html>");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("<html> <u>Add Item</u> </html>");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("<html> <u>Add Purchase</u> </html>");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("<html> <u>Add Sale</u> </html>");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jLabel5.setText("Select Filter");

        filter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Date", "Month", "Year" }));
        filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jLabel6.setText("Purchase Cost");

        jLabel7.setText("Sales Cost");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Sales_tab)
                .addGap(117, 117, 117)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(1014, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(787, 787, 787))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addComponent(Sales_tab, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        add_category obj=null;
        try {
            obj = new add_category();
        } catch (SQLException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
      //  this.setVisible(false);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        
        add_item obj=null;
         try {
            obj = new add_item();
        } catch (SQLException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
        //this.setVisible(false);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
         add_purchase obj=null;
         
        
        try {
            obj= new add_purchase();
        } catch (SQLException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        }
          obj.setVisible(true);
      //  this.setVisible(false);
        
        
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
         add_sale obj=null;
         
        
        try {
            obj= new add_sale();
        } catch (SQLException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        }
          obj.setVisible(true);
       // this.setVisible(false);
        
    }//GEN-LAST:event_jLabel4MouseClicked

    public void fillsalecost(int filtered) throws SQLException
    {
         ResultSet rs1=null;
           PreparedStatement pst = null;
        
            if(filtered==1)
            {
            pst = login.conn.prepareStatement("SELECT SUM(Amount) as sale FROM sales where Date=?");
             pst.setDate(1,  new java.sql.Date(jDateChooser1.getDate().getTime()));
              
            }
            else if(filtered==2)
            {
                pst = login.conn.prepareStatement("SELECT SUM(Amount) as sale FROM sales where Month=?");
             pst.setString(1,  getMonthForInt(jMonthChooser1.getMonth()));
             
            }
            else if(filtered==3)
            {
                
                 pst = login.conn.prepareStatement("SELECT SUM(Amount) as sale FROM sales where Year=?");
                 pst.setString(1,  Integer.toString(jYearChooser1.getYear()));
            }
            
            
       
      
        try {
           
             rs1 = pst.executeQuery();
             while(rs1.next())
            {
              jTextField2.setText(rs1.getString(1)); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
     public void fillpurchasecost(int filtered) throws SQLException
    {
         ResultSet rs1=null;
           PreparedStatement pst = null;
        
            if(filtered==1)
            {
            pst = login.conn.prepareStatement("SELECT SUM(Amount) as sale FROM purchases where Date=?");
             pst.setDate(1,  new java.sql.Date(jDateChooser1.getDate().getTime()));
              
            }
            else if(filtered==2)
            {
                pst = login.conn.prepareStatement("SELECT SUM(Amount) as sale FROM purchases where Month=?");
             pst.setString(1,  getMonthForInt(jMonthChooser1.getMonth()));
             
            }
            else if(filtered==3)
            {
                
                 pst = login.conn.prepareStatement("SELECT SUM(Amount) as sale FROM purchases where Year=?");
                 pst.setString(1,  Integer.toString(jYearChooser1.getYear()));
            }
            
            
       
      
        try {
           
             rs1 = pst.executeQuery();
              while(rs1.next())
            {
             jTextField1.setText(rs1.getString(1));
            }
               
        } catch (SQLException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    private void filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterActionPerformed
        
        
        if(filter.getSelectedItem().toString().equals("Date"))
        {
            filtered=1;
            
            jDateChooser1.setVisible(true);
            jMonthChooser1.setVisible(false);
            jYearChooser1.setVisible(false);
        }
        else if(filter.getSelectedItem().toString().equals("Month"))
        {
            filtered=2;
            jMonthChooser1.setVisible(true);
            jDateChooser1.setVisible(false);
            jYearChooser1.setVisible(false);
        }
        else
        {
            filtered=3;
            jYearChooser1.setVisible(true);
            jMonthChooser1.setVisible(false);
             jDateChooser1.setVisible(false);
        }
    }//GEN-LAST:event_filterActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:

            fillsalecost(filtered);
            fillpurchasecost(filtered);
        } catch (SQLException ex) {
            Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Main_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main_page().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Main_page.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Inventory;
    private javax.swing.JScrollPane Purchase_tab;
    private javax.swing.JTabbedPane Sales_tab;
    private javax.swing.JComboBox<String> filter;
    private javax.swing.JTable inventory_table;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JTable purchase_table;
    private javax.swing.JScrollPane sales_tab;
    private javax.swing.JTable sales_table;
    // End of variables declaration//GEN-END:variables
}

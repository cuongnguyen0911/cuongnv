/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongnv.views;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import cuongnv.dtos.UserDTO;
import cuongnv.dtos.UserData;

/**
 *
 * @author redra
 */
public class UI extends javax.swing.JFrame {

    List<UserDTO> list;
    boolean addNew = false;

    UserData data = new UserData();

    public UI() {
        initComponents();
    }

    public void loadData() {
        DefaultTableModel model = (DefaultTableModel) tblUser.getModel();
        while (model.getRowCount() != 0) {
            model.setRowCount(0);
        }
        list = data.getAll();

        if (cbbSort.getSelectedItem().equals("Ascending")) {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i).getFullName().compareTo(list.get(j).getFullName()) > 0) {
                        UserDTO temp = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, temp);
                    }
                }
            }
        } else if (cbbSort.getSelectedItem().equals("Decending")) {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(j).getFullName().compareTo(list.get(i).getFullName()) > 0) {
                        UserDTO temp = list.get(j);
                        list.set(j, list.get(i));
                        list.set(i, temp);
                    }
                }
            }
        }

        if (list != null) {
            for (UserDTO dto : list) {
                model.addRow(new Object[]{
                    dto.getRegistrationID(),
                    dto.getFullName(),
                    dto.getAge(),
                    (dto.isGender() ? "Male" : "Female"),
                    dto.getPhone(),
                    dto.getAddress()
                });
            }
        } else {
            JOptionPane.showMessageDialog(this, "Data loading...");
        }
    }

    public boolean inputValidation(String id, String name, String age, String email, String phone, String numOfChildren, String numOfAdult) {
        String msg = "An input Error has occur...";
        Pattern checkID = Pattern.compile("[^$@%#&/?!~-]");
        if (id.length() == 0 || id.length() > 10 || !checkID.matcher(id).find()) {
            msg += "\nId must not be null, under 10 chars and does not contain special char";
        }
        Pattern checkName = Pattern.compile("[^$@%#&*?!~_-]");
        if (name.length() > 50 || !checkName.matcher(name).find()) {
            msg += "\nFull Name must not exceed 50 chars";
        }
        Pattern checkAge = Pattern.compile("^(0?[1-9]|[1-9][0-9]|[1][1-9][1-9]|100)$");
        if(!checkAge.matcher(age).find()){
            msg += "\nAge must be an integer number and under 100";
        }
        Pattern checkEmail = Pattern.compile("^(.+)@(.+)$");
        if (email.length() > 30 || !checkEmail.matcher(email).find()) {
            msg += "\nEmail must not exceed 30 chars and follow the email fomat";
        }
        Pattern checkPhone = Pattern.compile("[0-9]+");
        if (phone.length() > 15 || !checkPhone.matcher(phone).find()) {
            msg += "\nPhone number must not exceed 15 chars and contain only number";
        }
        try {
            int i = Integer.parseInt(numOfChildren);
            if (i < 0) {
                msg += "\nNumber of Children must not be negative";
            }
        } catch (Exception e) {
            msg += "\nNumber of Children must be an integer number";
        }
        try {
            int i = Integer.parseInt(numOfAdult);
            if (i < 0) {
                msg += "\nNumber of Adult must not be negative";
            }
        } catch (Exception e) {
            msg += "\nNumber of Adult must be a integer number";
        }
        if (!msg.equals("An input Error has occur...")) {
            JOptionPane.showMessageDialog(this, msg);
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupSex = new javax.swing.ButtonGroup();
        pnlMainPart = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        lblSort = new javax.swing.JLabel();
        cbbSort = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnGetData = new javax.swing.JButton();
        pnlInfos = new javax.swing.JPanel();
        lblID = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtID = new javax.swing.JTextField();
        txtFullName = new javax.swing.JTextField();
        txtAge = new javax.swing.JTextField();
        lblGender = new javax.swing.JLabel();
        rbtnMale = new javax.swing.JRadioButton();
        rbtnFemale = new javax.swing.JRadioButton();
        txtEmail = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        txtNumMember = new javax.swing.JTextField();
        txtNumChildren = new javax.swing.JTextField();
        txtNumAdult = new javax.swing.JTextField();
        btnIDSearch = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlMainPart.setBorder(javax.swing.BorderFactory.createTitledBorder("Main Part"));

        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Full Name", "Age", "Gender", "Phone", "Address"
            }
        ));
        tblUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUser);

        lblSort.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblSort.setText("Sort by name: ");

        cbbSort.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cbbSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Ascending", "Decending" }));

        txtSearch.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        btnSearch.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnSearch.setText("Search by Name");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnGetData.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnGetData.setText("Get All Data");
        btnGetData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMainPartLayout = new javax.swing.GroupLayout(pnlMainPart);
        pnlMainPart.setLayout(pnlMainPartLayout);
        pnlMainPartLayout.setHorizontalGroup(
            pnlMainPartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainPartLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainPartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pnlMainPartLayout.createSequentialGroup()
                        .addComponent(lblSort)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainPartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSearch)
                            .addComponent(cbbSort, 0, 135, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch)
                        .addGap(0, 26, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnlMainPartLayout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(btnGetData, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMainPartLayout.setVerticalGroup(
            pnlMainPartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainPartLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(pnlMainPartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSort)
                    .addComponent(cbbSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainPartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(18, 18, 18)
                .addComponent(btnGetData)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pnlInfos.setBorder(javax.swing.BorderFactory.createTitledBorder("Information"));

        lblID.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblID.setText("Registration ID:");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("Full Name:");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setText("Age:");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel5.setText("Email:");

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel6.setText("Phone:");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel7.setText("Address:");

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel8.setText("Number of Member:");

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel9.setText("Include:");

        btnAdd.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnAdd.setText("Add New");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        txtID.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        txtFullName.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        txtAge.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        lblGender.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblGender.setText("Gender:");

        btnGroupSex.add(rbtnMale);
        rbtnMale.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        rbtnMale.setText("Male");

        btnGroupSex.add(rbtnFemale);
        rbtnFemale.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        rbtnFemale.setText("Female");

        txtEmail.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        txtPhone.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        txtAddress.setColumns(20);
        txtAddress.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtAddress.setRows(5);
        jScrollPane2.setViewportView(txtAddress);

        txtNumMember.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        txtNumChildren.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        txtNumAdult.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        btnIDSearch.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnIDSearch.setText("Search");
        btnIDSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIDSearchActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Children(s)");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("Adult(s)");

        javax.swing.GroupLayout pnlInfosLayout = new javax.swing.GroupLayout(pnlInfos);
        pnlInfos.setLayout(pnlInfosLayout);
        pnlInfosLayout.setHorizontalGroup(
            pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfosLayout.createSequentialGroup()
                .addGroup(pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInfosLayout.createSequentialGroup()
                        .addGroup(pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlInfosLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblID)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(27, 27, 27))
                            .addGroup(pnlInfosLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInfosLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(pnlInfosLayout.createSequentialGroup()
                                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnIDSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(pnlInfosLayout.createSequentialGroup()
                                            .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblGender)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(rbtnMale)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(rbtnFemale))
                                        .addComponent(txtEmail)
                                        .addComponent(txtFullName)
                                        .addComponent(txtPhone))
                                    .addGroup(pnlInfosLayout.createSequentialGroup()
                                        .addGroup(pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtNumChildren, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNumMember, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNumAdult, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel2))))
                            .addGroup(pnlInfosLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlInfosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        pnlInfosLayout.setVerticalGroup(
            pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblID)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIDSearch))
                .addGap(22, 22, 22)
                .addGroup(pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGender)
                    .addComponent(rbtnMale)
                    .addComponent(rbtnFemale))
                .addGap(22, 22, 22)
                .addGroup(pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(28, 28, 28)
                .addGroup(pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNumMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtNumChildren, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumAdult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlInfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMainPart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlInfos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlInfos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMainPart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGetDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetDataActionPerformed
        loadData();
    }//GEN-LAST:event_btnGetDataActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String search = txtSearch.getText();
        if (search.equals("")) {
            loadData();
        } else {
            List<UserDTO> searchedList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getFullName().contains(search)) {
                    searchedList.add(list.get(i));
                }
            }
            DefaultTableModel model = (DefaultTableModel) tblUser.getModel();
            while (model.getRowCount() != 0) {
                model.setRowCount(0);
            }
            if (cbbSort.getSelectedItem().equals("Ascending")) {
                for (int i = 0; i < searchedList.size(); i++) {
                    for (int j = i + 1; j < searchedList.size(); j++) {
                        if (searchedList.get(i).getFullName().compareTo(searchedList.get(j).getFullName()) > 0) {
                            UserDTO temp = searchedList.get(i);
                            searchedList.set(i, searchedList.get(j));
                            searchedList.set(j, temp);
                        }
                    }
                }
            } else if (cbbSort.getSelectedItem().equals("Decending")) {
                for (int i = 0; i < searchedList.size(); i++) {
                    for (int j = i + 1; j < searchedList.size(); j++) {
                        if (searchedList.get(j).getFullName().compareTo(searchedList.get(i).getFullName()) > 0) {
                            UserDTO temp = searchedList.get(j);
                            searchedList.set(j, searchedList.get(i));
                            searchedList.set(i, temp);
                        }
                    }
                }
            }

            if (!searchedList.isEmpty()) {
                for (UserDTO dto : searchedList) {
                    model.addRow(new Object[]{
                        dto.getRegistrationID(),
                        dto.getFullName(),
                        dto.getAge(),
                        (dto.isGender() ? "Male" : "Female"),
                        dto.getPhone(),
                        dto.getAddress()
                    });
                }
            } else {
                JOptionPane.showMessageDialog(this, "Data loading...");
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tblUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserMouseClicked
        String id = tblUser.getValueAt(tblUser.getSelectedRow(), 0).toString();
        UserDTO dto = data.findUserById(id);
        txtID.setText(dto.getRegistrationID());
        txtID.setEditable(false);
        txtFullName.setText(dto.getFullName());
        txtAddress.setText(dto.getAddress());
        txtAge.setText("" + dto.getAge());
        if (dto.isGender()) {
            rbtnMale.setSelected(true);
        } else {
            rbtnFemale.setSelected(true);
        }
        txtEmail.setText(dto.getEmail());
        txtPhone.setText(dto.getPhone());
        txtNumMember.setText("" + dto.getNumOfMember());
        txtNumAdult.setText("" + dto.getNumOfAdult());
        txtNumChildren.setText("" + dto.getNumOfChildren());
    }//GEN-LAST:event_tblUserMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        txtID.setText("");
        txtID.setEditable(true);
        txtID.requestFocus();
        txtFullName.setText("");
        txtAddress.setText("");
        txtAge.setText("");
        rbtnMale.setSelected(false);
        rbtnFemale.setSelected(false);
        txtEmail.setText("");
        txtPhone.setText("");
        txtNumMember.setText("");
        txtNumAdult.setText("");
        txtNumChildren.setText("");
        addNew = true;
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            String id = txtID.getText();
            int msg = JOptionPane.showConfirmDialog(this, "Do you want to delete this user ?");
            if (msg == 0) {
                list.remove(id);
                data.deleteUser(id);
                JOptionPane.showMessageDialog(this, "A User has been deleted.");
            }
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please choose what you wish to delete on the table...");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (addNew) {
            try {
                if (inputValidation(txtID.getText(), txtFullName.getText(), txtAge.getText(), txtEmail.getText(), txtPhone.getText(), txtNumChildren.getText(), txtNumAdult.getText())) {
                    boolean gender = rbtnMale.isSelected();
                    UserDTO newDTO = new UserDTO(txtID.getText(), txtFullName.getText(),
                            txtEmail.getText(), txtPhone.getText(), txtAddress.getText(),
                            gender, Integer.parseInt(txtAge.getText()), Integer.parseInt(txtNumMember.getText()),
                            Integer.parseInt(txtNumAdult.getText()), Integer.parseInt(txtNumChildren.getText()));
                    UserDTO dto = data.findUserById(txtID.getText());
                    if (dto == null) {
                        data.createUser(newDTO);
                        JOptionPane.showMessageDialog(this, "A new User has been added.");
                        addNew = false;
                    } else {
                        JOptionPane.showMessageDialog(this, "This ID is already exsit...");
                    }
                    loadData();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Un Error has occur...");
            }
        } else {
            try {
                String id = tblUser.getValueAt(tblUser.getSelectedRow(), 0).toString();
                if (inputValidation(id, txtFullName.getText(), txtAge.getText(), txtEmail.getText(), txtPhone.getText(), txtNumChildren.getText(), txtNumAdult.getText())) {
                    boolean gender = rbtnMale.isSelected();
                    data.updateUser(new UserDTO(txtID.getText(), txtFullName.getText(),
                            txtEmail.getText(), txtPhone.getText(), txtAddress.getText(),
                            gender, Integer.parseInt(txtAge.getText()), Integer.parseInt(txtNumMember.getText()),
                            Integer.parseInt(txtNumAdult.getText()), Integer.parseInt(txtNumChildren.getText())));
                    JOptionPane.showMessageDialog(this, "A use has been updated.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Update user failed.");
            }
            loadData();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnIDSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIDSearchActionPerformed
        String currentID = txtID.getText();
        UserDTO dto = data.findUserById(currentID);
        if (dto != null) {
            txtID.setText(dto.getRegistrationID());
            txtID.setEditable(false);
            txtFullName.setText(dto.getFullName());
            txtAddress.setText(dto.getAddress());
            txtAge.setText("" + dto.getAge());
            if (dto.isGender()) {
                rbtnMale.setSelected(true);
            } else {
                rbtnFemale.setSelected(true);
            }
            txtEmail.setText(dto.getEmail());
            txtPhone.setText(dto.getPhone());
            txtNumMember.setText("" + dto.getNumOfMember());
            txtNumAdult.setText("" + dto.getNumOfAdult());
            txtNumChildren.setText("" + dto.getNumOfChildren());
        } else {
            JOptionPane.showMessageDialog(this, "User with id: " + currentID + " not found...");
        }
    }//GEN-LAST:event_btnIDSearchActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnGetData;
    private javax.swing.ButtonGroup btnGroupSex;
    private javax.swing.JButton btnIDSearch;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbbSort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblSort;
    private javax.swing.JPanel pnlInfos;
    private javax.swing.JPanel pnlMainPart;
    private javax.swing.JRadioButton rbtnFemale;
    private javax.swing.JRadioButton rbtnMale;
    private javax.swing.JTable tblUser;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNumAdult;
    private javax.swing.JTextField txtNumChildren;
    private javax.swing.JTextField txtNumMember;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

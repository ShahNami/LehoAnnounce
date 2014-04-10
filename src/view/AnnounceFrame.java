/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import model.Config;
import model.LehoAnnounceHandler;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author Nami
 */
public class AnnounceFrame extends javax.swing.JFrame {
    
    /**
     * Creates new form AnnounceFrame
     */
    private static Config config;
    
    public AnnounceFrame(Config config) {
        initComponents();
        this.config = config;
        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Determine the new location of the window
        int w = getSize().width;
        int h = getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        // Move the window
        setLocation(x, y);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPanelSubject = new javax.swing.JScrollPane();
        lstSubject = new javax.swing.JList();
        scollPanelAnnouncement = new javax.swing.JScrollPane();
        lstAnnouncementDates = new javax.swing.JList();
        lblSubject = new javax.swing.JLabel();
        lblAnnouncementDate = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblAnnouncement = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        htmlAnnouncement = new javax.swing.JTextArea();
        lnkLink = new view.Link();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Leho Announce");

        lstSubject.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstSubject.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstSubjectValueChanged(evt);
            }
        });
        scrollPanelSubject.setViewportView(lstSubject);

        lstAnnouncementDates.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstAnnouncementDates.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstAnnouncementDatesValueChanged(evt);
            }
        });
        scollPanelAnnouncement.setViewportView(lstAnnouncementDates);

        lblSubject.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblSubject.setText("Subject");

        lblAnnouncementDate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAnnouncementDate.setText("Date of announcement");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblAnnouncement.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAnnouncement.setText("Announcement");

        htmlAnnouncement.setEditable(false);
        htmlAnnouncement.setColumns(20);
        htmlAnnouncement.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        htmlAnnouncement.setLineWrap(true);
        htmlAnnouncement.setRows(5);
        htmlAnnouncement.setWrapStyleWord(true);
        htmlAnnouncement.setMargin(new java.awt.Insets(15, 15, 15, 15));
        jScrollPane1.setViewportView(htmlAnnouncement);

        lnkLink.setText("Link");
        lnkLink.setURL("http://leho.howest.be");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblSubject, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAnnouncementDate, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPanelSubject, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(scollPanelAnnouncement))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAnnouncement)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lnkLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSubject)
                    .addComponent(lblAnnouncement)
                    .addComponent(lnkLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollPanelSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblAnnouncementDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scollPanelAnnouncement, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String normalise(String selected){
        if(selected.contains("*")){
            return selected.replace("*", "");
        }
        return selected;
    }
    private void lstSubjectValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstSubjectValueChanged
        try {
            try {
                if(getCode(normalise(lstSubject.getSelectedValue().toString())) != null){
                    LehoAnnounceHandler.getInstance(config).getAnnouncementDates(getCode(normalise(lstSubject.getSelectedValue().toString())));
                    lnkLink.setURL("http://leho.howest.be/main/announcements/announcements.php?cidReq="+getCode(normalise(lstSubject.getSelectedValue().toString())));
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(AnnounceFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(AnnounceFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lstSubjectValueChanged
    
    private String getCode(String value){
        for (Map.Entry<String, String> entry : config.getSubjects().entrySet()) {
            if(entry.getValue().equalsIgnoreCase(value)){
                return entry.getKey();
            }
        }
        return null;
    }

    private void lstAnnouncementDatesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstAnnouncementDatesValueChanged
        try {
            try {
                if(getCode(normalise(lstSubject.getSelectedValue().toString())) != null && lstAnnouncementDates.getSelectedIndex() > -1){
                    LehoAnnounceHandler.getInstance(config).getAnnouncement(getCode(normalise(lstSubject.getSelectedValue().toString())), lstAnnouncementDates.getSelectedIndex());
                    LehoAnnounceHandler.getInstance(config).getAnnouncementData(getCode(normalise(lstSubject.getSelectedValue().toString())), lstAnnouncementDates.getSelectedIndex());
                } else{
                    htmlAnnouncement.setText("");
                    lblAnnouncement.setText("Announcement");
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(AnnounceFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(AnnounceFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lstAnnouncementDatesValueChanged
    
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AnnounceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AnnounceFrame(config).setVisible(true);
            }
        });
    }
    
    private int getCountFromConfig(String subject, Properties prop) throws FileNotFoundException, IOException{
        int count = 0;
        if(prop.getProperty(subject) != null){
            count = Integer.parseInt(prop.getProperty(subject));
        }
        return count;
    }
    
    public Document setConnection(String path) throws IOException{
        Connection con = Jsoup.connect(path);
        con.cookies(config.getCookies());
        return con.get();
    }
    
    private int getCountNow(String subject) throws InterruptedException, IOException{
        Document doc = setConnection("http://leho.howest.be/main/announcements/announcements.php?cidReq="+subject);
        Element announceTable = doc.select("#agenda_list").first();
        return announceTable.getElementsByClass("announcements_datum").size(); 
    }
    
    public void setSubjects(Object[] subjects) throws InterruptedException, IOException{
        DefaultListModel model = new DefaultListModel();
        Properties prop = new Properties();
        InputStream input = new FileInputStream("subjects.properties");
        prop.load(input);
        for(int i=0;i<subjects.length;i++){
            if(getCountNow(getCode(subjects[i].toString())) > getCountFromConfig(getCode(subjects[i].toString()), prop)){
                model.addElement("*"+subjects[i]);
            } else {
                model.addElement(subjects[i]);
            }
        }
        input.close();
        lstSubject.setModel(model);
    }
    
    public void setAnnouncementDates(Object[] dates) {
        DefaultListModel model = new DefaultListModel();
        for(int i=0;i<dates.length;i++){
            model.addElement(dates[i]);
        }
        lstAnnouncementDates.setModel(model);
    }
    
    public void setAnnouncement(String text) throws IOException{
        htmlAnnouncement.setText(text);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea htmlAnnouncement;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAnnouncement;
    private javax.swing.JLabel lblAnnouncementDate;
    private javax.swing.JLabel lblSubject;
    private view.Link lnkLink;
    private javax.swing.JList lstAnnouncementDates;
    private javax.swing.JList lstSubject;
    private javax.swing.JScrollPane scollPanelAnnouncement;
    private javax.swing.JScrollPane scrollPanelSubject;
    // End of variables declaration//GEN-END:variables
    
    public void setAnnouncementData(String announcement) {
        lblAnnouncement.setText(announcement);
    }
    
}

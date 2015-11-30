/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioninscription;

import java.io.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.*;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Quentin
 */
public class FenGestSession extends javax.swing.JFrame {
     String pilote = "org.gjt.mm.mysql.Driver";
    String url = "jdbc:mysql://localhost/formarmor";	
    Connection conn;
    Statement stmt;
    /**
     * Creates new form FenGestSession
     */
    public FenGestSession() {
        initComponents();
        try
	{
            
            Class.forName(pilote);           			            
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();  
            GregorianCalendar cal = new GregorianCalendar();
            int i=0;
            int annee = cal.get(Calendar.YEAR);
            int mois =  cal.get(Calendar.MONTH)+1;
            int jour = cal.get(Calendar.DAY_OF_MONTH);
            String req = "select * from session_form where datedebut > '"+annee+"-"+mois+"-"+jour+"' order by datedebut";
            ResultSet rs = stmt.executeQuery(req);
            while (rs.next())
            {
                jTable1.setValueAt(rs.getInt("numero"), i, 0);
                jTable1.setValueAt(rs.getString("libelleform"), i, 1);
                jTable1.setValueAt(rs.getString("niveauform"), i, 2);
                jTable1.setValueAt(rs.getDate("datedebut"), i, 3);
                jTable1.setValueAt(rs.getInt("nb_places"), i, 4);
                jTable1.setValueAt(rs.getInt("nb_inscrits"), i, 5);
                if(rs.getInt("close")==1)
                {
                    jTable1.setValueAt("Fermée", i, 6);
                }else{
                    jTable1.setValueAt("Ouverte", i, 6);
                }
                
                i+= 1;
                
            }
            stmt.close();
            conn.close();      
        }			        
	catch (SQLException sqle)
	{
            System.out.println(sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println(cnfe.getMessage());
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

        lblGestRenta = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 600));

        lblGestRenta.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        lblGestRenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGestRenta.setText("GESTION DE LA RENTABILITE");
        lblGestRenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTable1.setModel(new ModeleJTableGestSession());
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jTable1InputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGestRenta, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGestRenta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        Document document=new Document();
        String libelle = jTable1.getValueAt(jTable1.getSelectedRow(),1).toString();
        String niveau = jTable1.getValueAt(jTable1.getSelectedRow(),2).toString();
        String date = jTable1.getValueAt(jTable1.getSelectedRow(),3).toString();
        try 
        {
            PdfWriter.getInstance(document,new FileOutputStream("./src/gestioninscription/PDF/"+libelle+niveau+date+".pdf"));
            document.open();
            PdfPTable table=new PdfPTable(2);
            
            PdfPCell cellTitre = new PdfPCell (new Paragraph (libelle+" "+niveau+" "+date));
                cellTitre.setColspan (2);
                cellTitre.setHorizontalAlignment (Element.ALIGN_CENTER);
                cellTitre.setBackgroundColor (new Color (128, 200, 128));
                cellTitre.setPadding (10.0f);
                table.addCell (cellTitre);
            PdfPCell cellNom = new PdfPCell (new Paragraph ("NOM"));
                cellNom.setHorizontalAlignment (Element.ALIGN_CENTER);
                cellNom.setBackgroundColor (new Color (255, 200, 0));
                cellNom.setPadding (10.0f);
                table.addCell (cellNom);
            PdfPCell cellSignature = new PdfPCell (new Paragraph ("SIGNATURE"));
                cellSignature.setHorizontalAlignment (Element.ALIGN_CENTER);
                cellSignature.setBackgroundColor (new Color (255, 200, 0));
                cellSignature.setPadding (10.0f);
                table.addCell (cellSignature);
            try
            {
                Class.forName(pilote);           			            
                conn = DriverManager.getConnection(url,"root","");
                stmt = conn.createStatement();  
                int i=0;
                
                String req = "select nom from client c, inscription i, session_form s";
                req += " where c.matricule = i.matricule";
                req += " and i.num_session = s.numero";
                req += " and s.libelleform ='"+libelle+"'";
                req += " and s.niveauform ='"+niveau+"'";
                ResultSet rs = stmt.executeQuery(req);
                while (rs.next())
                {
                    table.addCell(rs.getString(1));
                    table.addCell("");
                    i+=1;
                }
                stmt.close();
                conn.close();      
            }			        
            catch (SQLException sqle)
            {
                System.out.println(sqle.getMessage());
            }
            catch (ClassNotFoundException cnfe)
            {
                System.out.println(cnfe.getMessage());
            }
            document.add(table);
            document.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FenGestSession.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(FenGestSession.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTable1InputMethodTextChanged
        
    }//GEN-LAST:event_jTable1InputMethodTextChanged

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblGestRenta;
    // End of variables declaration//GEN-END:variables
}
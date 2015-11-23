/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioninscription;

import java.awt.Color;
import java.awt.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author papa
 */
public class Couleur extends DefaultTableCellRenderer
{
    public Component getTableCellRendererComponent
                     (JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){
          Component cell = super.getTableCellRendererComponent  
                      (table,value,isSelected,hasFocus,row,column);
          if (isSelected)
          {
              cell.setBackground(Color.yellow);
          }
          else
          {
              cell.setBackground(Color.white);
          }    
          if (column == 7)
          {
            Float cellule = (Float)value;
            if ((value != null) && (cellule < 0))
            { 
                cell.setBackground(Color.red);
            }
          }
          return cell;
    }
}

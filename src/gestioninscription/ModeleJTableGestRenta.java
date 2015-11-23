/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioninscription;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Quentin
 */
public class ModeleJTableGestRenta extends AbstractTableModel
{
    private String[] columnNames = {"Numéro","Libellé","Niveau","Date début","Nombre d'inscrits","Taux de remplissage"};
	private Object[][] data=new Object[10][8];
	public int getColumnCount()
	{
		return columnNames.length;
	}
	public int getRowCount()
	{
		return data.length;
	}
	public String getColumnName(int col)
	{
		return columnNames[col];
	}
	public Object getValueAt(int row, int col)
	{
		return data[row][col];
	}
	
	public boolean isCellEditable(int row, int col)
	{
		return (col < 2);
	}
	public void setValueAt(Object value, int row, int col)
	{
		data[row][col] = value;
		fireTableCellUpdated(row,col);
		//fireTableDataChanged();
	}
}

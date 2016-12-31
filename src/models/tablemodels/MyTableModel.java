/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package models.tablemodels;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rodrigo
 */
public class MyTableModel extends AbstractTableModel {

    private String[] colunas;
    private List<Object> linhas;

    public MyTableModel() {
        colunas = new String[]{};
        linhas = new ArrayList<Object>();
    }

    public MyTableModel(String[] colunas, List<Object> linhas) {
        this.colunas = colunas;
        this.linhas = linhas;
    }
    
    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object linha[] = (Object[]) linhas.get(rowIndex);
        return linha[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        return (String) colunas[columnIndex];
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public void setLinhas(List<Object> linhas) {
        this.linhas = linhas;
    }
    
    public boolean addRow(Object[] objects){
        return linhas.add(objects);
    }

    
}//fim classe

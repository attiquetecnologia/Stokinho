/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package models.tablemodels;

import java.text.NumberFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Servico;

/**
 *
 * @author Rodrigo
 */
public class ServicoTableModel extends AbstractTableModel {
    
    private String[] colunas;
    private List<Servico> servicos;

    public ServicoTableModel() {
        super();
        colunas = new String[]{"ID","Nome"};
    }

    public ServicoTableModel(List<Servico> servicos) {
        super();
        this.servicos = servicos;
        colunas = new String[]{"ID","Nome","Valor","Ativo"};
    }

    @Override
    public int getRowCount() {
        return servicos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Servico c = servicos.get(rowIndex);
        switch(columnIndex){
            case 0: return c.getId();
            case 1: return c.getNome();
            case 2: return NumberFormat.getCurrencyInstance().format(c.getValor().doubleValue());
            case 3: return c.isAtivo() == true ? "SIM" : "NÃO";
            default:  break;
        }//fim switch
        return null;
    }//fim

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public Servico getServico(int index){
        return getServicos().get(index);
    }
    
    public String[] getColunas() {
        return colunas;
    }
    
}//fim classe

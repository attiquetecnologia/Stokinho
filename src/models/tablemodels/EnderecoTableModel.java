/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package models.tablemodels;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Endereco;

/**
 *
 * @author Rodrigo
 */
public class EnderecoTableModel extends AbstractTableModel {
    
    private String[] colunas;
    private List<Endereco> enderecos;

    public EnderecoTableModel() {
        super();
        colunas = new String[]{"Logradouro","Bairro","CEP","Cidade","Estado","ID"};
    }

    public EnderecoTableModel(List<Endereco> enderecos) {
        super();
        this.enderecos = enderecos;
        colunas = new String[]{"Logradouro","Bairro","CEP","Cidade","Estado","ID"};
    }

    @Override
    public int getRowCount() {
        return enderecos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Endereco e = enderecos.get(rowIndex);
        switch(columnIndex){
            case 0: return e.getLogradouro();
            case 1: return e.getBairro();
            case 2: return e.getCep();
            case 3: return e.getCidade();
            case 4: return e.getUf();
            case 5: return e.getId();    
            default:  break;
        }//fim switch
        return null;
    }//fim

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public Endereco getEndereco(int index){
        return getEnderecos().get(index);
    }
    
    public String[] getColunas() {
        return colunas;
    }
    
}//fim classe

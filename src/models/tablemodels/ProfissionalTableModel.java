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
import models.Pessoa;
import models.Profissional;
import utils.DataDefault;

/**
 *
 * @author Rodrigo
 */
public class ProfissionalTableModel extends AbstractTableModel {
    
    private String[] colunas;
    private List<Profissional> profissionals;

    public ProfissionalTableModel() {
        super();
        colunas = new String[]{"ID","Nome","Telefone","Celular","Email","Nascimento","Bairro","CEP","Cidade","Estado"};
    }

    public ProfissionalTableModel(List<Profissional> profissionals) {
        super();
        this.profissionals = profissionals;
        colunas = new String[]{"ID","Nome","Telefone","Celular","Email","Nascimento","Bairro","CEP","Cidade","Estado"};
    }

    @Override
    public int getRowCount() {
        return profissionals.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Profissional pr = profissionals.get(rowIndex);
        Pessoa p = pr.getPessoa();
        Endereco e = p.getEndereco();
        String i = colunas[0];
        switch(columnIndex){
            case 0: return p.getId();
            case 1: return p.getNome();
            case 2: return p.getTelefone();
            case 3: return p.getCelular();
            case 4: return p.getEmail();
            case 5: return DataDefault.parseDataBrasil(p.getNascimento());
            case 6: return e.getLogradouro();
            case 7: return e.getBairro();
            case 8: return e.getCep();
            case 9: return e.getCidade();
            case 10: return e.getUf();
            case 11: return e.getId();    
            default:  break;
        }//fim switch
        return null;
    }//fim

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public List<Profissional> getProfissionals() {
        return profissionals;
    }

    public Profissional getProfissional(int index){
        return getProfissionals().get(index);
    }
    
    public String[] getColunas() {
        return colunas;
    }
    
}//fim classe
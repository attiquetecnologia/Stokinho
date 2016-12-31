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
import models.Endereco;
import models.OrdemServico;
import models.OrdemServico;
import utils.DataDefault;

/**
 *
 * @author Rodrigo
 */
public class OrdemServicoTableModel extends AbstractTableModel {
    
    private String[] colunas = new String[]{"ID","Abertura","Cliente (id)","Profissional","Descrição","Status","Valor"};
    private List<OrdemServico> ordemServicos;

    public OrdemServicoTableModel() {
        super();
        
    }

    public OrdemServicoTableModel(List<OrdemServico> ordemServicos) {
        super();
        this.ordemServicos = ordemServicos;

    }

    @Override
    public int getRowCount() {
        return ordemServicos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        OrdemServico p = ordemServicos.get(rowIndex);
        
        switch(columnIndex){
            case 0: return p.getId();
            case 1: return DataDefault.parseDataBrasil(p.getAbertura());
            case 2: return p.getCliente().getPessoa().getNome() + " (" + p.getCliente().getPessoa().getId() + ")";
            case 3: return p.getProfissional().getPessoa().getNome();
            case 4: return p.getDescricaoOcorrencia();
            case 5: return p.getStatus();
            case 6: 
                if (p.getValor() != null)
                    return NumberFormat.getCurrencyInstance().format(p.getValor());
                else return "";
            default:  break;
        }//fim switch
        return null;
    }//fim

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public List<OrdemServico> getOrdemServicos() {
        return ordemServicos;
    }

    public OrdemServico getOrdemServico(int index){
        return getOrdemServicos().get(index);
    }
    
    public String[] getColunas() {
        return colunas;
    }
    
}//fim classe

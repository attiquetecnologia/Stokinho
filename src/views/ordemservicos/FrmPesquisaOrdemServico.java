/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package views.ordemservicos;

import controllers.OrdemServicoController;
import java.awt.Frame;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import models.OrdemServico;
import models.enuns.Comparadores;
import models.tablemodels.OrdemServicoTableModel;
import utils.ShowMessage;

/**
 *
 * @author Rodrigo
 */
public class FrmPesquisaOrdemServico extends views.FrmPesquisaPadrao {

    
    private final OrdemServicoController controller = new OrdemServicoController();
    private OrdemServico ordemServico;

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }
    
    public FrmPesquisaOrdemServico(Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Pesquisa de Ordem de Servicos");
        setLocationRelativeTo(null);
        loadFitros();
        search();
        
    }

    @Override
    public void search() {
        String filtro = "", comp = getComparador();
        
//        "Número","Cliente nome","Cliente id","Profissional Nome","Data Abertura"
        switch(getCmbFiltro().getSelectedIndex()){
            case 0:
                filtro = "cliente.pessoa.nome";
                break;
            case 1:
                filtro = "id";
                break;
            case 2:
                filtro = "cliente.pessoa.id";
                break;
            case 3:
                filtro = "profissional.pessoa.nome";
                break;
            case 4:
                filtro = "abertura";
                break;
            default: break;
        }
        
        String query = "select p from OrdemServico p where "
                + "upper(p.{filtro}) {comp} UPPER(?1) {ativo} "
                + "ORDER BY p.{filtro} asc";
        query = query.replace("{filtro}", filtro);
        query = query.replace("{ativo}", !"".equals(getAtivo()) ? "and p.ativo = " + getAtivo() : getAtivo());
        query = query.replace("{comp}", comp);
                
        
        System.out.println(query);
        setDataTable(
                new OrdemServicoTableModel(
                        controller.findByParameters(query, "%"+getPesquisa()+"%")));
        getTblResultado().getColumnModel().getColumn(0).setPreferredWidth(50);
        getTblResultado().getColumnModel().getColumn(1).setPreferredWidth(250);
        getTblResultado().getColumnModel().getColumn(2).setPreferredWidth(150);
        getTblResultado().getColumnModel().getColumn(3).setPreferredWidth(150);
        getTblResultado().getColumnModel().getColumn(4).setPreferredWidth(150);
     
        
    }

    @Override
    public void selecionar() {
        int index = getTblResultado().getRowSorter().convertRowIndexToModel(getTblResultado().getSelectedRow());
        if (index >=0){
            OrdemServicoTableModel model = (OrdemServicoTableModel) getTblResultado().getModel();
            ordemServico = model.getOrdemServico(index);
            dispose();
        } else {
            ShowMessage.warningMessage("Selecione ao menos um registro na tabela!");
        }
    }
    
    private void loadFitros() {
        getCmbFiltro().setModel(new DefaultComboBoxModel(new Object[]{
            "Cliente nome","Número","Cliente id","Profissional nome","Data Abertura"
        }));
        getCmbQueEh().setSelectedItem(Comparadores.CONTEM);
        getCmbQueEh().setEnabled(false);
        
    }
    
    public static void main(String[] args) {
        new FrmPesquisaOrdemServico(null, true).setVisible(true);
    }

}//fim classe

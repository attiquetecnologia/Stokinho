/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o sonsentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package views.servicos;

import controllers.ServicoController;
import java.awt.Frame;
import javax.swing.DefaultComboBoxModel;
import models.Servico;
import models.enuns.Comparadores;
import models.tablemodels.ServicoTableModel;
import utils.ShowMessage;

/**
 *
 * @author Rodrigo
 */
public class FrmPesquisaServico extends views.FrmPesquisaPadrao {

    
    private final ServicoController controller = new ServicoController();
    private Servico servico;

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
    
    public FrmPesquisaServico(Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Pesquisa de Serviços");
        setLocationRelativeTo(null);
        loadFitros();
        search();
        
    }

    @Override
    public void search() {
        String query = "select s from Servico s where "
                + "upper(s.{filtro}) {comp} UPPER(?1) {ativo} "
                + "ORDER BY s.{filtro} asc";
        query = query.replace("{filtro}", getFiltro());
        query = query.replace("{ativo}", !"".equals(getAtivo()) ? "and s.ativo = " + getAtivo() : getAtivo());
        query = query.replace("{comp}", getComparador());
                ;
        
        System.out.println(query);
        setDataTable(
                new ServicoTableModel(
                        controller.findByParameters(query, "%"+getPesquisa()+"%")
//                        (controller.findByParameters(getCmbFiltro()
//                                .getSelectedItem().toString()
//                                , getPesquisa(), getComparadores()))
                ));
        
        getTblResultado().getColumnModel().getColumn(0).setPreferredWidth(50);
        getTblResultado().getColumnModel().getColumn(1).setPreferredWidth(250);
        
    }

    @Override
    public void selecionar() {
        int index = getTblResultado().getRowSorter().convertRowIndexToModel(getTblResultado().getSelectedRow());
        if (index >=0){
            ServicoTableModel model = (ServicoTableModel) getTblResultado().getModel();
            servico = model.getServico(index);
            dispose();
        } else {
            ShowMessage.warningMessage("Selecione ao menos um registro na tabela!");
        }
    }
    
    private void loadFitros() {
        getCmbFiltro().setModel(new DefaultComboBoxModel(new Object[]{
            "nome","ID"
        }));
        getCmbQueEh().setSelectedItem(Comparadores.CONTEM);
        getCmbQueEh().setEnabled(false);
        
    }
    
    public static void main(String[] args) {
        new FrmPesquisaServico(null, true).setVisible(true);
    }

}//fim classe

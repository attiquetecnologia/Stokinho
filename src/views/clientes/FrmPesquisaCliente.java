/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package views.clientes;

import controllers.ClienteController;
import java.awt.Frame;
import javax.swing.DefaultComboBoxModel;
import models.Cliente;
import models.enuns.Comparadores;
import models.tablemodels.ClienteTableModel;
import utils.ShowMessage;

/**
 *
 * @author Rodrigo
 */
public class FrmPesquisaCliente extends views.FrmPesquisaPadrao {

    
    private final ClienteController controller = new ClienteController();
    private Cliente pessoa;

    public Cliente getCliente() {
        return pessoa;
    }

    public void setCliente(Cliente pessoa) {
        this.pessoa = pessoa;
    }
    
    public FrmPesquisaCliente(Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Pesquisa de Clientes");
        setLocationRelativeTo(null);
        loadFitros();
        search();
        
    }

    @Override
    public void search() {
        String query = "select p from Cliente p where "
                + "upper(p.pessoa.{filtro}) {comp} UPPER(?1) {ativo} "
                + "ORDER BY p.pessoa.{filtro} asc";
        query = query.replace("{filtro}", getFiltro());
        query = query.replace("{ativo}", !"".equals(getAtivo()) ? "and p.ativo = " + getAtivo() : getAtivo());
        query = query.replace("{comp}", getComparador());
                ;
        
        System.out.println(query);
        setDataTable(
                new ClienteTableModel(
                        controller.findByParameters(query, "%"+getPesquisa()+"%")
//                        (controller.findByParameters(getCmbFiltro()
//                                .getSelectedItem().toString()
//                                , getPesquisa(), getComparadores()))
                ));
        getTblResultado().getColumnModel().getColumn(0).setPreferredWidth(50);
        getTblResultado().getColumnModel().getColumn(1).setPreferredWidth(250);
        getTblResultado().getColumnModel().getColumn(2).setPreferredWidth(150);
        getTblResultado().getColumnModel().getColumn(3).setPreferredWidth(150);
        getTblResultado().getColumnModel().getColumn(4).setPreferredWidth(150);
        getTblResultado().getColumnModel().getColumn(5).setPreferredWidth(150);
        
    }

    @Override
    public void selecionar() {
        int index = getTblResultado().getRowSorter().convertRowIndexToModel(getTblResultado().getSelectedRow());
        if (index >=0){
            ClienteTableModel model = (ClienteTableModel) getTblResultado().getModel();
            pessoa = model.getCliente(index);
            dispose();
        } else {
            ShowMessage.warningMessage("Selecione ao menos um registro na tabela!");
        }
    }
    
    private void loadFitros() {
        getCmbFiltro().setModel(new DefaultComboBoxModel(new Object[]{
            "nome","email","ID"
        }));
        getCmbQueEh().setSelectedItem(Comparadores.CONTEM);
        getCmbQueEh().setEnabled(false);
        
    }
    
    public static void main(String[] args) {
        new FrmPesquisaCliente(null, true).setVisible(true);
    }

}//fim classe

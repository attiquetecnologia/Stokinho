/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package views;

import controllers.PessoaController;
import java.awt.Frame;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import models.Pessoa;
import models.enuns.Comparadores;
import models.tablemodels.PessoaTableModel;
import utils.ShowMessage;

/**
 *
 * @author Rodrigo
 */
public class FrmPesquisaPessoa extends views.FrmPesquisaPadrao {

    
    private final PessoaController controller = new PessoaController();
    private Pessoa pessoa;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public FrmPesquisaPessoa(Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Pesquisa de Pessoas");
        setLocationRelativeTo(null);
        loadFitros();
        search();
        
    }

    @Override
    public void search() {
        
        setDataTable(
                new PessoaTableModel(
                        (List<Pessoa>) (controller.findByParameters(getCmbFiltro()
                                .getSelectedItem().toString()
                                , getPesquisa(), getComparadores()))));
        
        getTblResultado().getColumnModel().getColumn(0).setPreferredWidth(220);
        getTblResultado().getColumnModel().getColumn(1).setPreferredWidth(150);
        getTblResultado().getColumnModel().getColumn(2).setPreferredWidth(150);
        getTblResultado().getColumnModel().getColumn(3).setPreferredWidth(150);
        getTblResultado().getColumnModel().getColumn(4).setPreferredWidth(150);
        getTblResultado().getColumnModel().getColumn(5).setPreferredWidth(150);
        
    }

    @Override
    public void selecionar() {
        int index = getTblResultado().getRowSorter().convertRowIndexToModel(getTblResultado().getSelectedRow());
        if (index >=0){
            PessoaTableModel model = (PessoaTableModel) getTblResultado().getModel();
            pessoa = model.getPessoa(index);
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
        new FrmPesquisaPessoa(null, true).setVisible(true);
    }

}//fim classe

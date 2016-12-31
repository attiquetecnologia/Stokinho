/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package views.enderecos;

import controllers.EnderecoController;
import java.awt.Frame;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import models.Endereco;
import models.enuns.Comparadores;
import models.tablemodels.EnderecoTableModel;
import utils.ShowMessage;

/**
 *
 * @author Rodrigo
 */
public class FrmPesquisaEndereco extends views.FrmPesquisaPadrao {

    private Endereco endereco;
    private final EnderecoController controller = new EnderecoController();

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public FrmPesquisaEndereco(Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Pesquisa de Endereços");
        setLocationRelativeTo(null);
        loadFitros();
        search();
        
    }

    @Override
    public void search() {
        
        setDataTable(
                new EnderecoTableModel(
                        (List<Endereco>) controller.findByParameters(getCmbFiltro()
                                .getSelectedItem().toString()
                                , getPesquisa(), getComparadores())));
        
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
            EnderecoTableModel model = (EnderecoTableModel) getTblResultado().getModel();
            endereco = model.getEndereco(index);
            dispose();
        } else {
            ShowMessage.warningMessage("Selecione ao menos um registro na tabela!");
        }
    }
    
    private void loadFitros() {
        getCmbFiltro().setModel(new DefaultComboBoxModel(new Object[]{
            "logradouro","cep","bairro","cidade","uf"
        }));
        getCmbQueEh().setSelectedItem(Comparadores.CONTEM);
        
    }
    
    public static void main(String[] args) {
        new FrmPesquisaEndereco(null, true).setVisible(true);
    }
}//fim classe

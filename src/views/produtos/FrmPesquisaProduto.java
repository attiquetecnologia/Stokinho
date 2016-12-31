/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package views.produtos;

import controllers.ProdutoController;
import java.awt.Frame;
import javax.swing.DefaultComboBoxModel;
import models.Produto;
import models.enuns.Comparadores;
import models.tablemodels.ProdutoTableModel;
import utils.ShowMessage;

/**
 *
 * @author Rodrigo
 */
public class FrmPesquisaProduto extends views.FrmPesquisaPadrao {

    
    private final ProdutoController controller = new ProdutoController();
    private Produto produto;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public FrmPesquisaProduto(Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Pesquisa de Produtos");
        setLocationRelativeTo(null);
        loadFitros();
        search();
        
    }

    @Override
    public void search() {
        String query = "select p from Produto p where "
                + "upper(p.{filtro}) {comp} UPPER(?1) {ativo} "
                + "ORDER BY p.{filtro} asc";
        query = query.replace("{filtro}", getFiltro());
        query = query.replace("{ativo}", !"".equals(getAtivo()) ? "and p.ativo = " + getAtivo() : getAtivo());
        query = query.replace("{comp}", getComparador());
                ;
        
        System.out.println(query);
        setDataTable(new ProdutoTableModel(
                          controller.findByParameters(query, "%"+getPesquisa()+"%")
//                        controller.findByParameters(getCmbFiltro()
//                                .getSelectedItem().toString()
//                                , getPesquisa(), getComparadores())
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
            ProdutoTableModel model = (ProdutoTableModel) getTblResultado().getModel();
            produto = model.getProduto(index);
            dispose();
        } else {
            ShowMessage.warningMessage("Selecione ao menos um registro na tabela!");
        }
    }
    
    private void loadFitros() {
        getCmbFiltro().setModel(new DefaultComboBoxModel(new Object[]{
            "descricao","barcode","unidade","ID"
        }));
        getCmbQueEh().setSelectedItem(Comparadores.CONTEM);
        getCmbQueEh().setEnabled(false);
        
    }
    
    public static void main(String[] args) {
        new FrmPesquisaProduto(null, true).setVisible(true);
    }

}//fim classe

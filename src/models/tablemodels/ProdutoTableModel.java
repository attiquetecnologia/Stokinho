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
import models.Produto;

/**
 *
 * @author Rodrigo
 */
public class ProdutoTableModel extends AbstractTableModel {
    
    private String[] colunas = new String[]{"ID","Descrição","Preço","Quantidade Estoque","Estoque Minimo","Unidade","Ativo"};
    private List<Produto> produtos;

    public ProdutoTableModel() {
        super();
        
    }

    public ProdutoTableModel(List<Produto> produtos) {
        super();
        this.produtos = produtos;
        
    }

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Produto p = produtos.get(rowIndex);
        
        switch(columnIndex){
            case 0: return p.getId();
            case 1: return p.getDescricao();
            case 2: return NumberFormat.getCurrencyInstance().format(p.getPrecoVenda());
            case 3: return p.getQuantidadeEstoque();
            case 4: return p.getEstoqueMinimo();
            case 5: return p.getUnidade();
            case 6: return p.isAtivo() == true ? "SIM" : "NÃO";
            default:  break;
        }//fim switch
        return null;
    }//fim

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public Produto getProduto(int index){
        return getProdutos().get(index);
    }
    
    public String[] getColunas() {
        return colunas;
    }
    
}//fim classe

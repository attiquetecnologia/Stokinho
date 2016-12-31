/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author rodrigo
 * @version 15/04/2016
 */
@Entity
@Table(name = "produtos")
@NamedQueries({
    @NamedQuery(name = "Produto.findAll"
            , query = "SELECT p FROM Produto p ORDER BY p.descricao ASC")
    ,@NamedQuery(name = "Produto.findByDescricao"
            , query = "SELECT p FROM Produto p WHERE UPPER(p.descricao) LIKE UPPER(:nome) ORDER BY p.descricao ASC")
    ,@NamedQuery(name = "Produto.findByBarcode"
        , query = "SELECT p FROM Produto p WHERE UPPER(p.barcode) LIKE UPPER(:email) ORDER BY p.barcode ASC")
})
public class Produto implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    private int id;
    
    @Column(name = "pro_barcode")
    private String barcode;
    
    @Column(name = "pro_descricao")
    private String descricao;

    @Column(name = "pro_preco_compra",precision = 2,scale = 3)
    private BigDecimal precoCompra;
   
    @Column(name = "pro_preco_venda",precision = 2)
    private BigDecimal precoVenda;
    
    @Column(name = "pro_quantidade_estoque",precision = 2)
    private BigDecimal quantidadeEstoque;
    
    @Column(name = "pro_estoque_minimo",precision = 2)
    private BigDecimal estoqueMinimo;
    
    @Column(name = "pro_unidade")
    private String unidade;
    
    @Column(name = "pro_foto")
    private String foto;
    
    @Column(name = "ativo")
    private boolean ativo = true;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_cadastro")
    private Date dataCadastro;
    
    public Produto() {
        id = 0;
        dataCadastro = new Date();
        estoqueMinimo = new BigDecimal(BigInteger.ZERO);
        quantidadeEstoque = new BigDecimal(BigInteger.ZERO);
        precoCompra = new BigDecimal(BigInteger.ZERO);
        precoVenda = new BigDecimal(BigInteger.ZERO);
        unidade = "UNIDADE";
    }

    public Produto(Integer id) {
        this.id = id;
        dataCadastro = new Date();
        estoqueMinimo = new BigDecimal(BigInteger.ZERO);
        quantidadeEstoque = new BigDecimal(BigInteger.ZERO);
        precoCompra = new BigDecimal(BigInteger.ZERO);
        precoVenda = new BigDecimal(BigInteger.ZERO);
        unidade = "UNIDADE";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barCode) {
        this.barcode = barCode;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(String precoCompra) {
        this.precoCompra = new BigDecimal(precoCompra);
    }

    public void setPrecoCompra(BigDecimal precoCompra) {
        this.precoCompra = precoCompra;
    }
    
    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public void setPrecoVenda(String precoVenda) {
        this.precoVenda = new BigDecimal(precoVenda);
    }

    public BigDecimal getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(BigDecimal quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void setQuantidadeEstoque(String quantidadeEstoque) {
        this.quantidadeEstoque = new BigDecimal(quantidadeEstoque);
    }
    
    public BigDecimal getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(BigDecimal estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }
    
    public void setEstoqueMinimo(String estoqueMinimo) {
        this.estoqueMinimo = new BigDecimal(estoqueMinimo);
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
    
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
}//fim Meidco

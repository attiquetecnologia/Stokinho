/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Rodrigo
 */
@Entity
@NamedQueries(
        @NamedQuery(name = "FindById", query = "SELECT os FROM OrdemServico os WHERE os.id = :id")
)
public class OrdemServico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordem_id")
    private Integer id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date abertura;
    @Temporal(TemporalType.TIMESTAMP)
    private Date encerramento;
    private int tempoGarantia; // 1, 30, 60...
    private String quantidadeTempoGarantia; //dias meses anos
    /**
     * ORCAMENTO - Antes de iniciar a ordem fica como orçado apenas
     * AGUARDANDO - Significa que a ordem não pode esta mais como orçamento
     * EXECUCAO - Exeção do serviço
     * CONCLUIDO - Ordem encerrada libera pra receber o dinheiro
     * CANCELADO - Ordem foi cancelada não recebe dinheiro??! e desativa a ordem
     * ###########
     * Sequencias
     * ORCAMENTO -> AGUARDANDO
     * ORCAMENTO -> CANCELADO
     * ################
     * AGUARDANDO -> EXECUCAO
     * AGUARDANDO -> CANCELADO
     * ###########
     * EXECUCAO -> CONCLUIDO
     * CONCLUIDO -> FATURAMENTO
     * 
     */
    private String status;  //Aguardando, CANCELADO, FINALIZADO..
    /**
     * Quando criada recebe status ativo im
    */
    private boolean ativo;
    @Column(length = 1000)
    private String descricaoOcorrencia;
    
    @Column(precision = 2)
    private BigDecimal desconto;
    @Column(precision = 2)
    private BigDecimal valor;
    private boolean faturado;
    
    @JoinColumn(name = "cliente_id")
    @ManyToOne
    private Cliente cliente;
    
    @JoinColumn(name = "profissional_id")
    @ManyToOne
    private Profissional profissional;
    
    //simula os produtos
    @ManyToMany
    private List<Servico> servicos;

    //simula os produtos
    @ManyToMany
    private List<Produto> produtos;
    
    public OrdemServico() {
        id = 0;
        cliente = new Cliente();
        profissional = new Profissional();
        valor = new BigDecimal(BigInteger.ZERO);
        status = "ORCAMENTO";
        desconto = new BigDecimal(BigInteger.ZERO);
        faturado = false;
        ativo = true;
        servicos = new ArrayList<>();
        produtos = new ArrayList<>();
        abertura = new Date();
        encerramento = new Date();
    }

    public OrdemServico(Integer id) {
        this.id = id;
        cliente = new Cliente();
        profissional = new Profissional();
        valor = new BigDecimal(BigInteger.ZERO);
        status = "ORCAMENTO";
        desconto = new BigDecimal(BigInteger.ZERO);
        faturado = false;
        ativo = true;
        servicos = new ArrayList<>();
        produtos = new ArrayList<>();
        abertura = new Date();
        encerramento = new Date();
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAbertura() {
        return abertura;
    }

    public void setAbertura(Date abertura) {
        this.abertura = abertura;
    }

    public Date getEncerramento() {
        return encerramento;
    }

    public void setEncerramento(Date encerramento) {
        this.encerramento = encerramento;
    }

    public int getTempoGarantia() {
        return tempoGarantia;
    }

    public void setTempoGarantia(int tempoGarantia) {
        this.tempoGarantia = tempoGarantia;
    }

    public String getQuantidadeTempoGarantia() {
        return quantidadeTempoGarantia;
    }

    public void setQuantidadeTempoGarantia(String quantidadeTempoGarantia) {
        this.quantidadeTempoGarantia = quantidadeTempoGarantia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getDescricaoOcorrencia() {
        return descricaoOcorrencia;
    }

    public void setDescricaoOcorrencia(String descricaoOcorrencia) {
        this.descricaoOcorrencia = descricaoOcorrencia;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public void setDesconto(String desconto) {
        this.desconto = new BigDecimal(desconto);
    }
    
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setValor(String valor) {
        this.valor = new BigDecimal(valor);
    }
    
    public boolean isFaturado() {
        return faturado;
    }

    public void setFaturado(boolean faturado) {
        this.faturado = faturado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    
}//fim classe

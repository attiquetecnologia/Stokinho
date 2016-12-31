/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author sergio
 * @version 15/04/2016
 */
@Entity
@Table(name = "pacientes")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll"
            , query = "SELECT c FROM Cliente c ORDER BY c.pessoa.nome ASC")
    ,@NamedQuery(name = "Cliente.findByNome"
            , query = "SELECT c FROM Cliente c WHERE UPPER(c.pessoa.nome) LIKE UPPER(:nome) ORDER BY c.pessoa.nome ASC")
    ,@NamedQuery(name = "Cliente.findByEmail"
        , query = "SELECT c FROM Cliente c WHERE UPPER(c.pessoa.email) LIKE UPPER(:email) ORDER BY c.pessoa.email ASC")
        ,@NamedQuery(name = "Cliente.cadastrados"
        , query = "SELECT p FROM Cliente p WHERE p.pessoa.dataCadastro BETWEEN :inicio AND :fim ORDER BY p.pessoa.dataCadastro ASC")
})
public class Cliente implements Serializable {
    
    @Id
    @JoinColumn(name = "pes_id",nullable = false)
    @ManyToOne(cascade = CascadeType.MERGE)
    private Pessoa pessoa;
    @Column(name = "pac_profissao")
    private String profissao;
    @Column(name = "pac_indicacao")
    private String indicacao;
    @Column(name = "ativo")
    private boolean ativo = true;
    
//    @OneToMany(mappedBy = "paciente", orphanRemoval = true,cascade =CascadeType.MERGE)
//    private List<Tratamento> tratamentos;
    
    public Cliente() {
        pessoa = new Pessoa();
//        tratamentos = new ArrayList<>();
    }

    public Cliente(Pessoa pessoa, String profissao, String indicacao) {
        this.pessoa = pessoa;
        this.profissao = profissao;
        this.indicacao = indicacao;
//        tratamentos = new ArrayList<>();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getIndicacao() {
        return indicacao;
    }

    public void setIndicacao(String indicacao) {
        this.indicacao = indicacao;
    }

//    public List<Tratamento> getTratamentos() {
//        return tratamentos;
//    }

//    public void setTratamentos(List<Tratamento> tratamentos) {
//        this.tratamentos = tratamentos;
//    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    
}// fim da classe

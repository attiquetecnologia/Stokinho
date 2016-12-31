/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author rodrigo
 * @version 15/04/2016
 */
@Entity
@Table(name = "profissionais")
@NamedQueries({
    @NamedQuery(name = "Profissional.findAll", query = "SELECT p FROM Profissional p ORDER BY p.pessoa ASC")
    ,@NamedQuery(name = "Profissional.findByNome", query = "SELECT pr FROM Profissional pr WHERE UPPER(pr.pessoa.nome) LIKE UPPER(:nome) ORDER BY pr.pessoa.nome")
    ,@NamedQuery(name = "Profissional.findByEmail", query = "SELECT pr FROM Profissional pr WHERE UPPER(pr.pessoa.email) LIKE UPPER(:email) ORDER BY pr.pessoa.email")
})
public class Profissional implements Serializable {
    
    @Id
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;
    
    @Column(name = "prof_profissao")
    private String profissao;
    
    @Column(name = "ativo")
    private boolean ativo = true;

    public Profissional() {
        this.pessoa = new Pessoa();
    }

    public Profissional(Pessoa pessoa) {
        this.pessoa = pessoa;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.pessoa);
        hash = 53 * hash + Objects.hashCode(this.profissao);
        hash = 53 * hash + (this.ativo ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profissional other = (Profissional) obj;
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        if (!Objects.equals(this.profissao, other.profissao)) {
            return false;
        }
        if (this.ativo != other.ativo) {
            return false;
        }
        return true;
    }
    
    
} // fim fisioterapeuta


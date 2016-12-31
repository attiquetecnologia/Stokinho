/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "pessoas")
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT e FROM Pessoa e"),
    @NamedQuery(name = "Pessoa.findById", query = "SELECT e FROM Pessoa e WHERE e.id like :id"),
    @NamedQuery(name = "Pessoa.findByNome", query = "SELECT e FROM Pessoa e WHERE UPPER(e.nome) like UPPER(:nome)"),
    @NamedQuery(name = "Pessoa.findByEmail", query = "SELECT e FROM Pessoa e WHERE UPPER(e.email) like UPPER(:email)"),
    @NamedQuery(name = "Pessoa.findByCPF", query = "SELECT e FROM Pessoa e WHERE UPPER(e.cpf) like UPPER(:cpf)"),
    @NamedQuery(name = "Pessoa.aniversariantes"
            , query = "SELECT p FROM Pessoa p "
                    + "WHERE "
                    + "p.nascimento "
                    + "BETWEEN :inicio "
                    + "AND :fim "
                    + "ORDER BY p.nascimento ASC"),
//    @NamedQuery(name = "Pessoa.findLikeCep", query = "SELECT e FROM Pessoa e WHERE e.cep like :cep"),
//    @NamedQuery(name = "Pessoa.findByCidade", query = "SELECT e FROM Pessoa e WHERE UPPER(e.cidade) like UPPER(:cidade)"),
//    @NamedQuery(name = "Pessoa.findByLogradouro", query = "SELECT e FROM Pessoa e WHERE UPPER(e.logradouro) like UPPER(:logradouro)"),
//    @NamedQuery(name = "Pessoa.findByUf", query = "SELECT e FROM Pessoa e WHERE e.uf like UPPER(:uf)")
})

//@SequenceGenerator(name = "pessoas_id_seq", sequenceName = "pessoas_id_seq", allocationSize = 1, initialValue = 1)
public class Pessoa implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pes_id")
    private Integer id;
    
    @Column(name = "pes_nome", nullable = false)
    private String nome;
    
    @Column(name = "pes_telefone")
    private String telefone;
    
    @Column(name = "pes_celular")
    private String celular;
    
    @Column(name = "pes_outro", length = 500)
    private String outro;
    
    @Column(name = "pes_email")
    private String email;
    
    @Column(name = "pes_site")
    private String site;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "pes_nascimento")
    private Date nascimento;
    
    @Column(name = "pes_rg")
    private String rg;
    
    @Column(name = "pes_cpf")
    private String cpf;
    
    @Column(name = "pes_foto")
    private String foto;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "pes_data_cadastro")
    private Date dataCadastro;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    
    @Column(name = "pes_numero_endereco")
    private String numeroEndereco; //número do endereço
    
    @Column(name = "pes_complemento_endereco")
    private String complementoEndereco;
    
    
    @Column(name = "pes_anotacoes", length = 2000)
    private String anotacoes;
    
    
    
    public Pessoa() {
        dataCadastro = new Date();
        endereco = new Endereco();
        id = 0;
    }

    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        dataCadastro = new Date();
        endereco = new Endereco();

    }
    
    

    public Pessoa(Integer id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        dataCadastro = new Date();
        endereco = new Endereco();
        
    }

    public Pessoa(Integer id, String nome, String telefone, String celular, String outro, String email, String site, Date nascimento, String rg, String cpf, String foto, Date dataCadastro, Endereco endereco, String numeroEndereco, String complementoEndereco, String anotacoes) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.celular = celular;
        this.outro = outro;
        this.email = email;
        this.site = site;
        this.nascimento = nascimento;
        this.rg = rg;
        this.cpf = cpf;
        this.foto = foto;
        this.dataCadastro = dataCadastro;
        this.endereco = endereco;
        this.numeroEndereco = numeroEndereco;
        this.complementoEndereco = complementoEndereco;
        
        this.anotacoes = anotacoes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getOutro() {
        return outro;
    }

    public void setOutro(String outro) {
        this.outro = outro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getComplementoEndereco() {
        return complementoEndereco;
    }

    public void setComplementoEndereco(String complementoEndereco) {
        this.complementoEndereco = complementoEndereco;
    }

    public String getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.nome);
        hash = 71 * hash + Objects.hashCode(this.telefone);
        hash = 71 * hash + Objects.hashCode(this.celular);
        hash = 71 * hash + Objects.hashCode(this.outro);
        hash = 71 * hash + Objects.hashCode(this.email);
        hash = 71 * hash + Objects.hashCode(this.site);
        hash = 71 * hash + Objects.hashCode(this.nascimento);
        hash = 71 * hash + Objects.hashCode(this.rg);
        hash = 71 * hash + Objects.hashCode(this.cpf);
        hash = 71 * hash + Objects.hashCode(this.foto);
        hash = 71 * hash + Objects.hashCode(this.dataCadastro);
        hash = 71 * hash + Objects.hashCode(this.endereco);
        hash = 71 * hash + Objects.hashCode(this.numeroEndereco);
        hash = 71 * hash + Objects.hashCode(this.complementoEndereco);
        hash = 71 * hash + Objects.hashCode(this.anotacoes);
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.celular, other.celular)) {
            return false;
        }
        if (!Objects.equals(this.outro, other.outro)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.site, other.site)) {
            return false;
        }
        if (!Objects.equals(this.nascimento, other.nascimento)) {
            return false;
        }
        if (!Objects.equals(this.rg, other.rg)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.foto, other.foto)) {
            return false;
        }
        if (!Objects.equals(this.dataCadastro, other.dataCadastro)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.numeroEndereco, other.numeroEndereco)) {
            return false;
        }
        if (!Objects.equals(this.complementoEndereco, other.complementoEndereco)) {
            return false;
        }
        if (!Objects.equals(this.anotacoes, other.anotacoes)) {
            return false;
        }
        return true;
    }
    
    
}//fim Pessoa
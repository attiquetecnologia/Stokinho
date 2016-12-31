/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Rodrigo
 * @version 15/04/2016
 */
@Entity
@Table(name = "tratamentos")
@NamedQueries({
    @NamedQuery(name = "Tratamento.findAll"
            , query = "SELECT t FROM Tratamento t ORDER BY t.id ASC")
})
public class Tratamento implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tra_id")
    private Integer id;
    
    @Column(name = "tra_hma", length = 2000)
    private String hma;
    @Column(name = "tra_diag_clinico", length = 2000)
    private String diagClinico;
    @Column(name = "tra_diag_fisioterapeutico", length = 2000)
    private String diagFisioterapeutico;
    @Column(name = "tra_conduta", length = 2000)
    private String conduta;
    @Column(name = "tra_outros", length = 2000)
    private String outros;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_diagnostico", nullable = false)
    private Date dataDiagnostico;
    
    @OneToOne
    @JoinColumn(name = "med_id", nullable = false)
    private Produto medico;
    
    @OneToOne
    @JoinColumn(name = "pro_id", nullable = false)
    private Profissional profissional;
    
    @OneToOne
    @JoinColumn(name = "con_id", nullable = false)
    private Servico convenio;
    
    @ManyToOne()
    @JoinColumn(name = "pac_id", nullable = false)
    private Cliente paciente;

    @OneToMany(mappedBy = "tratamento", orphanRemoval = true,cascade =CascadeType.MERGE)
    private List<Documento> documentos;
    
    public Tratamento() {
        paciente = new Cliente();
        medico = new Produto();
        profissional = new Profissional();
        convenio = new Servico();
        documentos = new ArrayList<>();
    }

    public Tratamento(Cliente paciente) {
        this.paciente = paciente;
        medico = new Produto();
        profissional = new Profissional();
        convenio = new Servico();
        documentos = new ArrayList<>();
    }
    
    public Tratamento(Integer id, String hma, String diagClinico, String diagFisioterapeutico, String conduta, Produto medico, Profissional profissional, Servico convenio) {
        this.id = id;
        this.hma = hma;
        this.diagClinico = diagClinico;
        this.diagFisioterapeutico = diagFisioterapeutico;
        this.conduta = conduta;
        this.medico = medico;
        this.profissional = profissional;
        this.convenio = convenio;
        documentos = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHma() {
        return hma;
    }

    public void setHma(String hma) {
        this.hma = hma;
    }

    public String getDiagClinico() {
        return diagClinico;
    }

    public void setDiagClinico(String diagClinico) {
        this.diagClinico = diagClinico;
    }

    public String getDiagFisioterapeutico() {
        return diagFisioterapeutico;
    }

    public void setDiagFisioterapeutico(String diagFisioterapeutico) {
        this.diagFisioterapeutico = diagFisioterapeutico;
    }

    public String getConduta() {
        return conduta;
    }

    public void setConduta(String conduta) {
        this.conduta = conduta;
    }

    public Produto getMedico() {
        return medico;
    }

    public void setMedico(Produto medico) {
        this.medico = medico;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Servico getConvenio() {
        return convenio;
    }

    public void setConvenio(Servico convenio) {
        this.convenio = convenio;
    }

    public Cliente getPaciente() {
        return paciente;
    }

    public void setPaciente(Cliente paciente) {
        this.paciente = paciente;
    }

    public String getOutros() {
        return outros;
    }

    public void setOutros(String outros) {
        this.outros = outros;
    }

    public Date getDataDiagnostico() {
        return dataDiagnostico;
    }

    public void setDataDiagnostico(Date dataDiagnostico) {
        this.dataDiagnostico = dataDiagnostico;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    }//~fim Tratamento
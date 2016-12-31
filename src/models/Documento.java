/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Esta classe especifica um arquivo no banco de dados, como imagens
 * documentos de textos entre outros.
 * Tipos permitidos: doc, docx, jpg, jpeg, png, bmp, txt, odt, pdf
 * @author Rodrigo
 * @version 15/04/16
 */
@Entity
@Table(name = "documentos")
public class Documento implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "tra_id", nullable = false)
    private Tratamento tratamento;
    
    @Column(name = "doc_tipo")
    private String tipo;
    
    @Column(name = "doc_url")
    private String url;

    @Column(name = "doc_nome")
    private String nome;
    
    public Documento() {
        tratamento = new Tratamento();
    }

    public Documento(Tratamento tratamento,String nome, String tipo, String url) {
        this.tratamento = tratamento;
        this.nome = nome;
        this.tipo = tipo;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tratamento getTratamento() {
        return tratamento;
    }

    public void setTratamento(Tratamento tratamento) {
        this.tratamento = tratamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}//fim Documentoho
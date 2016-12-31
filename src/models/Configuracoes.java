/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package models;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import models.enuns.InformacoesSistema;
import persistence.TextFile;

/**
 *
 * @author Rodrigo
 * @version 15/04/16
 */
@Entity
@Table(name = "configuracoes")
@NamedQueries({
    @NamedQuery(name = "Configuracoes.findDefault", 
            query = "SELECT c FROM Configuracoes c")
})
public class Configuracoes implements Serializable {
    @Id
    @Column(name = "conf_cnpj")
    private String confCNPJ;
    @Column(name = "conf_nome_fantasia")
    private String confNomeFantasia;
    @Column(name = "conf_razao_social")
    private String confRazaoSocial;
    
    @Column(name = "conf_logomarca")
    private String confLogomarca;
    
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    
    @Column(name = "conf_telefone")
    private String telefone;
    
    @Column(name = "conf_email")
    private String email;
    @Column(name = "conf_site")
    private String site;
    
    @Column(name = "conf_numero_endereco")
    private String numeroEndereco; //número do endereço
    @Column(name = "conf_complemento_endereco")
    private String complementoEndereco;
    
    @Column(name = "conf_anotacoes", length = 2000)
    private String anotacoes;
    @Column(name = "conf_log_erros")
    private String pathLogErros;
    @Column(name = "conf_path_backup")
    private String pathBackup;
    @Column(name = "conf_arquivos_impressao")
    private String pathArquivosImpressao;
    @Column(name = "conf_dados")
    private String pathDados;
    @Column(name = "conf_documentos")
    private String pathDocumentos;
    
    /** Encontra o caminho absoluto */
    public static String pathAbsoluto = TextFile.getINSTANCE().getPathAbsoluto();
    
    
    public Configuracoes() {
        confLogomarca = null;
        
        /*Por default o diretório de instalação é o padrão de todas as pastas */
        pathArquivosImpressao = "/impressao/";
        pathBackup = "/backup/";
        pathDocumentos = "/documentos/";
        pathDados = InformacoesSistema.PATH_SISTEMA + "/dados/";
        pathLogErros = "/logs_erro/";
    }

    public String getConfCNPJ() {
        return confCNPJ;
    }

    public void setConfCNPJ(String confCNPJ) {
        this.confCNPJ = confCNPJ;
    }

    public String getConfNomeFantasia() {
        return confNomeFantasia;
    }

    public void setConfNomeFantasia(String confNomeFantasia) {
        this.confNomeFantasia = confNomeFantasia;
    }

    public String getConfRazaoSocial() {
        return confRazaoSocial;
    }

    public void setConfRazaoSocial(String confRazaoSocial) {
        this.confRazaoSocial = confRazaoSocial;
    }

    public String getConfLogomarca() {
        return confLogomarca;
    }

    public void setConfLogomarca(String confLogomarca) {
        this.confLogomarca = confLogomarca;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public String getPathLogErros() {
        return pathLogErros;
    }

    public void setPathLogErros(String pathLogErros) {
        this.pathLogErros = pathLogErros;
    }

    public String getPathBackup() {
        return pathBackup;
    }

    public void setPathBackup(String pathBackup) {
        this.pathBackup = pathBackup;
    }

    public String getPathArquivosImpressao() {
        return pathArquivosImpressao;
    }

    public void setPathArquivosImpressao(String pathArquivosImpressao) {
        this.pathArquivosImpressao = pathArquivosImpressao;
    }

    public String getPathFotos() {
        return pathDados;
    }

    public void setPathFotos(String pathFotos) {
        this.pathDados = pathFotos;
    }

    public String getPathDocumentos() {
        return pathDocumentos;
    }

    public void setPathDocumentos(String pathDocumentos) {
        this.pathDocumentos = pathDocumentos;
    }

    @Override
    public String toString() {
        return "Configuracoes{" + "confCNPJ=" + confCNPJ + ", confNomeFantasia=" + confNomeFantasia + ", confRazaoSocial=" + confRazaoSocial + ", confLogomarca=" + confLogomarca + ", endereco=" + endereco + ", telefone=" + telefone + ", email=" + email + ", site=" + site + ", numeroEndereco=" + numeroEndereco + ", complementoEndereco=" + complementoEndereco + ", anotacoes=" + anotacoes + ", pathLogErros=" + pathLogErros + ", pathBackup=" + pathBackup + ", pathArquivosImpressao=" + pathArquivosImpressao + ", pathFotos=" + pathDados + ", pathDocumentos=" + pathDocumentos + '}';
    }
    
}//fim classe
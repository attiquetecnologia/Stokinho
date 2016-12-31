/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package models.logs;

import java.io.Serializable;
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
import models.usuarios.Sessao;

/**
 * Logs de ação são como triggers para registrar os eventos feitos
 * pelo usuário no sistema.
 * @author Rodrigo
 */
@Entity
@Table(name = "logs_acao")
@NamedQueries({
    @NamedQuery(name = "LogAcao.findAll"
            , query = "SELECT l FROM LogAcao l WHERE "
                    + "l.dataAcao BETWEEN :inicio AND :fim "
                    + "AND "
                    + "l.usuario IN :usuarios "
                    + "ORDER BY l.dataAcao DESC"
    )
})
public class LogAcao implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "data_acao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAcao;
    
    @Column(name = "acao")
    private String acao;
    
    @Column(name= "usuario")
    private String usuario;
    
    @Column(name = "ip_addr")
    private String ipAddress;
    
    @Column(name = "host_name")
    private String hostName;
    
    @Column(name = "mac_addr")
    private String macAddres;
    
    @Column(name = "os_user")
    private String osUser;
    
    @Column(name = "os_name")
    private String osName;

    public LogAcao() {
        dataAcao = new Date();
        usuario = Sessao.getInstance().getUsuario().getLogin();
        ipAddress = Sessao.getInstance().getInformacoesSistema().getIpAddress();
        hostName = Sessao.getInstance().getInformacoesSistema().getHostName();
        macAddres = Sessao.getInstance().getInformacoesSistema().getMacAddress();
        osUser = Sessao.getInstance().getInformacoesSistema().getUserName();
        osName = Sessao.getInstance().getInformacoesSistema().getOsName();
    }

    public LogAcao(String acao,String usuario){
        this.usuario = usuario;
        this.acao = acao;
        dataAcao = new Date();
        ipAddress = Sessao.getInstance().getInformacoesSistema().getIpAddress();
        hostName = Sessao.getInstance().getInformacoesSistema().getHostName();
        macAddres = Sessao.getInstance().getInformacoesSistema().getMacAddress();
        osUser = Sessao.getInstance().getInformacoesSistema().getUserName();
        osName = Sessao.getInstance().getInformacoesSistema().getOsName();
    }
    
    public LogAcao(String usuario){
        this.usuario = usuario;
        dataAcao = new Date();
        ipAddress = Sessao.getInstance().getInformacoesSistema().getIpAddress();
        hostName = Sessao.getInstance().getInformacoesSistema().getHostName();
        macAddres = Sessao.getInstance().getInformacoesSistema().getMacAddress();
        osUser = Sessao.getInstance().getInformacoesSistema().getUserName();
        osName = Sessao.getInstance().getInformacoesSistema().getOsName();
    }
    public LogAcao(int ID, Date dataAcao, String acao, String usuario, String ipAddress, String hostName, String macAddres, String osUser, String osName) {
        this.ID = ID;
        this.dataAcao = dataAcao;
        this.acao = acao;
        this.usuario = usuario;
        this.ipAddress = ipAddress;
        this.hostName = hostName;
        this.macAddres = macAddres;
        this.osUser = osUser;
        this.osName = osName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getDataAcao() {
        return dataAcao;
    }

    public void setDataAcao(Date dataAcao) {
        this.dataAcao = dataAcao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getMacAddres() {
        return macAddres;
    }

    public void setMacAddres(String macAddres) {
        this.macAddres = macAddres;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getOsUser() {
        return osUser;
    }

    public void setOsUser(String osUser) {
        this.osUser = osUser;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }
    
    
}//fim classe
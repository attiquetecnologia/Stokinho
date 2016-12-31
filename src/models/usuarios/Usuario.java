/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package models.usuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import models.interfaces.UsuarioInterface;

/**
 *
 * @author usuario
 * @version 15/04/16
 */
@Entity
@Table(name = "usuarios")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u ORDER BY u.login ASC"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.login = :param ORDER BY u.login ASC"),
    @NamedQuery(name = "Usuario.findByLogin", query = "SELECT u FROM Usuario u WHERE UPPER(u.login) like UPPER(:param) ORDER BY u.login ASC"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE UPPER(u.email) like UPPER(:param) ORDER BY u.login ASC"),
    @NamedQuery(name = "Usuario.login"
            , query = "SELECT u FROM Usuario u "
                    + "WHERE UPPER(u.login) = UPPER(:login) AND u.senha = :senha "
                    + "AND u.ativo = true")
})

public class Usuario implements Serializable, UsuarioInterface {
    
    @Id
    @Column(name = "login", nullable = false, length = 20)
    private String login;
    @Column(name = "senha", nullable = false, length = 128)
    private String senha;
    @Column(name = "ativo")
    private boolean ativo;
    @Column(name = "email")
    private String email;
    @Column(name = "notas")
    private String notas;
    
    
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<PermissaoUsuario> permissoesUsuario;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on")
    private Date createdOn;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_on")
    private Date modifiedOn;

    public Usuario() {
        createdOn = new Date();
        ativo = true;
        permissoesUsuario = new ArrayList<>();
        
    }

    public Usuario(String login, String senha, boolean ativo, String notas, List<PermissaoUsuario> permissoesUsuario, Date createdOn, Date modifiedOn) {
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
        this.notas = notas;
        this.permissoesUsuario = permissoesUsuario;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }

    public Usuario(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public List<PermissaoUsuario> getPermissoesUsuario() {
        return permissoesUsuario;
    }

    public void setPermissoesUsuario(List<PermissaoUsuario> permissoesUsuario) {
        this.permissoesUsuario = permissoesUsuario;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    /**OPERAÇÕES ADD */
    @Override
    public boolean addPermissao(PermissaoUsuario permissao){
//        for (PermissaoUsuario per : permissoesUsuario){
//            if (!per.getCasoUso().equals(permissao.getCasoUso()) 
//                    && !per.getPermissao().equals(permissao.getPermissao())){
                return permissoesUsuario.add(permissao);
//            }
//        }
//        return false;
    }
    
    /**
     * OPERAÇÕES REMOVE*/
    @Override
    public boolean removePermissao(PermissaoUsuario permissao){
        for (PermissaoUsuario per : permissoesUsuario){
            if (per.getCasoUso()==(permissao.getCasoUso()) 
                    && per.getPermissao()==(permissao.getPermissao())){
                return permissoesUsuario.remove(per);
            }
        }
        return false;
    }
    
    /** 
     * retorna uma lista contendo apennas as permissões de um caso de uso
     * @return 
     */
    @Override
    public ArrayList<Permissao> getPermissaos(CasoUso casoUso){
        ArrayList<Permissao> permissaos = new ArrayList<Permissao>();
        for (PermissaoUsuario per : permissoesUsuario){
            if (per.getCasoUso().equals(casoUso)){
                permissaos.add(per.getPermissao());
            }
        }
        return permissaos;
    }
    
    /**
     * Verifica se tal usuário tem ou nao permissão para entrar emm tal lugar
     * @param permissaoUsuario
     * @return 
     */
    @Override
    public boolean isAllowed(PermissaoUsuario permissao){
        for (PermissaoUsuario per : permissoesUsuario){
            if (per.getCasoUso()==(permissao.getCasoUso()) 
                    && per.getPermissao()==(permissao.getPermissao())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Esta função verifica se há ou não privilégios para tal caso de uso
     */
    @Override
    public boolean isAllowed(CasoUso casoUso){
        for (PermissaoUsuario per : permissoesUsuario){
            if (per.getCasoUso().equals(casoUso)){
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se tal usuário tem ou nao permissão para entrar emm tal lugar
     * @param permissaoUsuario
     * @return 
     */
    
    public boolean isAllowed(CasoUso caso, Permissao permissao){
        for (PermissaoUsuario per : permissoesUsuario){
            if (per.getCasoUso()==(caso) 
                    && per.getPermissao()==(permissao)){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean login(String usuario, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}//fim classe
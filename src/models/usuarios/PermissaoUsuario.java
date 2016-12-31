/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package models.usuarios;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Relaciona a permissão de usuário.
 * Classe Associativa
 * @author Rodrigo
 * @version 15/04/16
 */
@Entity
@Table(name = "permissoes_usuario")
@IdClass(PermissaoUsuarioPK.class)
public class PermissaoUsuario implements Serializable {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "casouso")
    private CasoUso casoUso;  //objeto de permissao
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "permissao")
    private Permissao permissao;

    public PermissaoUsuario() {}

    public PermissaoUsuario(Usuario usuario, CasoUso casoUso, Permissao permissao) {
        this.usuario = usuario;
        this.casoUso = casoUso;
        this.permissao = permissao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public CasoUso getCasoUso() {
        return casoUso;
    }

    public void setCasoUso(CasoUso casoUso) {
        this.casoUso = casoUso;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }
    
}//fim classe
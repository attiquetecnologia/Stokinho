/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package models.usuarios;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * Esta classe define a chave primária da classe Associativa
 * PermissaoUsuario.
 * @author Rodrigo
 * @version 15/04/2016
 */
@Embeddable
public class PermissaoUsuarioPK implements Serializable{
  
    private String usuario;
    private CasoUso casoUso;  //objeto de permissao
    private Permissao permissao;

    public PermissaoUsuarioPK() {
    }   

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public CasoUso getString() {
        return casoUso;
    }

    public void setString(CasoUso casoUso) {
        this.casoUso = casoUso;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final PermissaoUsuarioPK other = (PermissaoUsuarioPK) obj;
        return true;
    }
    
    
}//fim classe
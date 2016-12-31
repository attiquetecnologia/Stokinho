/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package models.interfaces;

import java.util.ArrayList;
import models.usuarios.CasoUso;
import models.usuarios.Permissao;
import models.usuarios.PermissaoUsuario;

/**
 *
 * @author Rodrigo
 */
public interface UsuarioInterface {
    
    /** 
     * retorna uma lista contendo apennas as permissões de um caso de uso
     * @return 
     */
    public ArrayList<Permissao> getPermissaos(CasoUso casoUso);
    /**OPERAÇÕES ADD */
    public boolean addPermissao(PermissaoUsuario permissao);
    /**
     * OPERAÇÕES REMOVE*/
    public boolean removePermissao(PermissaoUsuario permissao);
    /**
     * Verifica se tal usuário tem ou nao permissão para entrar emm tal lugar
     * @param permissaoUsuario
     * @return 
     */
    public boolean isAllowed(PermissaoUsuario permissao);
    /**
     * Esta função verifica se há ou não privilégios para tal caso de uso
     */
    public boolean isAllowed(CasoUso casoUso);

    /** Faz Login */
    public boolean login(String usuario, String senha);
}//fim classe

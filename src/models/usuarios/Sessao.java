/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package models.usuarios;

import models.enuns.InformacoesSistema;

/**
 *
 * @author Rodrigo
 */
public class Sessao {
    
    private Usuario usuario;
    private InformacoesSistema informacoesSistema;
    private static final Sessao INSTANCE = new Sessao();
    
    private Sessao() {
        informacoesSistema = InformacoesSistema.getInstance();
        usuario = new Usuario();
    }
    
    public static Sessao getInstance() {
        return INSTANCE;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public InformacoesSistema getInformacoesSistema() {
        return informacoesSistema;
    }

    public void setInformacoesSistema(InformacoesSistema informacoesSistema) {
        this.informacoesSistema = informacoesSistema;
    }
    
    
}//fim classe

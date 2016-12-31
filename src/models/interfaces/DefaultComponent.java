/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package models.interfaces;

/**
 * Estabelece padrões para todos os componentes do sistema, como verPermissao
 * @author Rodrigo
 */
public interface DefaultComponent {
   
    /**
     * Verifica as permissões, desabilitando ou escondendo controles
     */
    public void verPermissao();
}//
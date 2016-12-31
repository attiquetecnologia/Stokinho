package models.usuarios;

/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */

/**
 * Casos de uso do sistema para serem associados a uma permissão
 * @author Rodrigo
 */
public enum CasoUso {
    CONFIGURACOES_SISTEMA("Configuracoes do Sistema")  //QUEM CONFIGURA SISTEMA VISUALIZA LOGS
    ,MANUTENCAO_ENDERECOS("Manutenção de Endereços")
    ,MANUTENCAO_PRODUTOS("Manutenção de Produtos")
    ,MANUTENCAO_PROFISSIONAIS("Manutenção de Profissionais")
    ,MANUTENCAO_CLIENTES("Manutenção de Clientes")
    ,MANUTENCAO_ORDEMSERVICOS("Manutenção da Ordem de Serviços")
    ,TELA_PRINCIPAL("Acesso a tela principal")
    , MANUTENCAO_USUARIOS("Acesso a tela usuarios")
    , MANUTENCAO_SERVICOS("Acesso ao cadastro de Serviços")
    ;
    
    private String comment;
    public String getComment(){
        return comment;
    }
    CasoUso(String comment){
       this.comment = comment;
    }
}

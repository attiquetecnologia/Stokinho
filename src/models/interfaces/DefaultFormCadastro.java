/*
 * DESENVOLVEDOR: Rodrigo Attique Santana rodrigoatique@gmail.com
 * Todos os Direitos Reservados
 * DOS DADOS
 * O sistema é desenvolvido para a autoescola Impacto, sendo seus, 
 * os dados contidos no mesmo bem como a base de dados e sua estrutura, 
 * é de sua responsabilidade zelar pela integridade dos dados fazendo 
 * bom uso do sistema com backups, treinamento providos pelo DESENVOLVEDOR, 
 * atualizações entre outros.
 * DA DISTRIBUIÇÃO
 * Como se trata de um sistema customizado para contratado, o CONTRATADO 
 * recebe uma cópia da estrutura da base de dados e respectivas senhas de 
 * acesso, caso o DESENVOLVEDOR atual não seja mais capaz de continuar o 
 * desenvolvimento da versão customizada do sistema.
 * O framework, algoritmos, métodos são de autoria do DESENVOLVEDOR, não 
 * sendo permitida a distribuição do código sem o consentimento do mesmo a terceiros.
 * DOS ACESSOS
 * O sistema tem acesso simultâneo ilimitado à base de dados, podendo ser feitas 
 * quantas conexões forem necessárias, respeitando apenas os limites de conexão do 
 * servidor onde será instalada a base de dados.
 */
package models.interfaces;

/**
 *
 * @author usuario
 */
public interface DefaultFormCadastro {

    
    /**
     *
     * @return
     */
    public Object getDataForm();

    /**
     * Insere dados no formulário
     * @param objeto
     */
    public void setDataForm(Object objeto);

    /**
     * 
     */
    public void salvar();

    /**
     *
     */
    public void novo();
    
    /**
     * 
     */
    public void editar();
    
    /**
     * 
     */
    public void excluir();
    
    /**
     * 
     */
    public void localizar();

    /**
     * Controle para fechar janela
     */
    public void fechar();
    
    /**
     * Controles e campos do formulário
     */
    public void enableControls(boolean bool);
    
    /**
     * Campos do formulário
     */
    public void enableForm(boolean bool);
}//fim interface

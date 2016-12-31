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
 * Esta interface garante a implementação para as tabelas de pesquisa
 * @author usuario
 */
public interface DefaultTableResult {
    
    
    
    /**
     * Seleciona um objeto em uma linha
     */
    public void selecionar();
    /**
     * Abre um cadastro para inserir nova linha
     */
    public void novo();
    /**
     * Seta padrões para fechar janela
     */
    public void fechar();
    /**
     * Abre cadastro para edição de linha
     */
    public void editar();
    /**
     * Pesquisa objeto contido na lista ou banco de dados
     */
    public void search();
    /**
     * retorna um vetor contendo todos os dados da linha selecionada
     * @return 
     */
    public Object[] getDataRow();
    /**
     * Coloca os dados dentro da tabela.
     * @param rows
     * Matriz 2x2 com os valores das linhas
     * @param cols
     * Vetor contendo os nomes das colunas
     * @param sizeColumns 
     * Vetor contendo os tamanhos das colunas
     */
    public void setDataTable(Object rows[][],Object cols[],int sizeColumns[]);
}//fim
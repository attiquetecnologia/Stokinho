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

import java.util.Collection;

/**
 * Padrão de controller
 * Padroniza todos os controllers usados para fazer a comunicação com a DAO,
 * todos os controllers implementam este.
 * Aqui são providos elementos de inserção e busca de dados.
 * @author 
 * Rodrigo Attique Santana - Attique Tecnologia
 * rodrigoatique@gmail.com
 * @version
 * 1.0 02/02/2015
 */
public interface DefaultController {
    /**
     * Método para setar e verificar se realmente é um objeto de entidade
     * @param o
     * @return 
     * o - é o vetor de objetos que ele receberá de um form por exemplo
     * vetor é sequencial na ordem que esta disposta na classe e 
     * tabela do banco de dados.
     */
    @Deprecated
    public boolean setObject(Object[] o);
    
    public boolean setObject(Object object);
    /**
     * Transforma uma coleção de objetos em uma matriz para 
     * usar em um JTable
     * @param c
     * @return 
     */
    @Deprecated
    public Object[][] objectToArray(Collection c);
    /**
     * Salva o objeto de entidade no banco de dados
     * @return 
     */
 
    public boolean save();

    /**
     *
     * @return
     */
    public boolean update();

    /**
     *
     * @return
     */
    public boolean delete();
    /**
     * Faz uma pesquisa usando os parâmetros escolhidos
     * @param query
     * Query contendo consulta usando ? como em select * from table where x=?
     * @param params
     * Sequência de parâmetros ilimitada.
     * use um vetor do tipo Object[] ou passe por vírgula como em par1,par2,par3
     * @return 
     * Collection
     */
    public Collection findByParameters(String query, Object... params);
    /**
     * Faz uma pesquisa usando os parâmetros e retorna verdadeiro ou falso
     * @param object
     * Objeto de Classe a ser pesquisado
     * @param query
     * Query contendo consulta usando ? como em select * from table where x=?
     * @param params
     * Sequência de parâmetros ilimitada.
     * use um vetor do tipo Object[] ou passe por vírgula como em par1,par2,par3
     * @return 
     * True - encontrado
     * False - não encontrado
     */
    public boolean findParameters(Object object,String query, Object... params);
    /**
     * Faz uma pesquisa usando o Id do objeto expeficicado.
     * @param object
     * Classe de objeto
     * @return 
     * True - encontrado
     * False - não encontrado
     */
    public Object findById(Object id);
    
}//fim

package models.interfaces;


import java.util.Collection;
import javax.persistence.EntityManager;

/**
 * DefaultDAO
 * Esta interface tem por objetivo padronizar os DAOs utilizados
 * no sistema.
 * @author usuario
 */
public interface DefaultDAO {
    
    /**
     * Simples método de salvamento em geral
     * @param object
     * @return
     * @throws Exception 
     */
    public boolean save(Object object) throws Exception;
    /**
     * Executa uma operação de update, podendo até ser uma procedure
     * @param object
     * @return
     * @throws Exception 
     */
    public boolean update(Object object) throws Exception;
    /**
     * Deleta um objeto do banco de dados especificado por uma classe de entidade
     * @param object
     * @return
     * @throws Exception 
     */
    public boolean delete(Object object) throws Exception;
    /**
     * Faz uma busca customizada por parâmetros
     * @param query
     * Query de consulta como em select * from table_name where par1 = ? and par2 = ?;
     * @param params
     * Parâmetros de pesquisa passados de forma sequencial.
     * EX: findByParameter(query,par1,par2...par20);
     * @return
     * @throws Exception 
     */
    public Collection findByParameters(String query, Object... params) throws Exception;
    
    /**
     * Faz uma busca customizada por parâmetros
     * @param query
     * Query de consulta como em select * from table_name where par1 = ? and par2 = ?;
     * @param params
     * Parâmetros de pesquisa passados de forma sequencial.
     * EX: findByParameter(query,par1,par2...par20);
     * @return
     * @throws Exception 
     */
    public Collection findByParameters(String param, Object value) throws Exception;
    
    /**
     * Find Parameter não é FindByParameter
     * Retorna apenas um objeto .... ???????
     * @param object
     * Classe de entidade
     * @param query
     * Consulta customizada com em: select * from table_name where par = ?;
     * @param params
     * parametros de consutla
     * @return
     * @throws java.sql.Exception 
     */
    public boolean findParameter(Object object,String query, Object... params) throws Exception;
    
    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Object findById(Object id) throws Exception;
    /**
     * Insere log de ação no sistema
     */
    public boolean insertLog(EntityManager em, String mensagem);
}//fim DefaultDAO


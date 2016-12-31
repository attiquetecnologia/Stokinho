/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package persistence;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import models.Cliente;
import models.interfaces.DefaultDAO;
import models.logs.LogAcao;
import utils.Log;

/**
 *
 * @author Rodrigo
 */
public class ClienteBO extends GenericDAO<Cliente> implements DefaultDAO{

    private LogAcao logAcao;
    
    public ClienteBO(){
        logAcao = new LogAcao();
    }
    
    @Override
    public boolean save(Object object) throws Exception {
        Cliente cliente = (Cliente) object;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cliente);
            insertLog(em, String.format("Cliente adicionado %s (%d)", cliente.getPessoa().getNome(),cliente.getPessoa().getId()));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Log.log(ClienteBO.class.getName(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return false;
    }

    @Override
    public boolean update(Object object) throws Exception {
        Cliente cliente = (Cliente) object;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            object = em.merge(cliente);
            insertLog(em, String.format("Cliente alterado %s (%d)", cliente.getPessoa().getNome(),cliente.getPessoa().getId()));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            
            Log.log(ClienteBO.class.getName(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return false;
    }

    @Override
    public boolean delete(Object object) throws Exception {
        Cliente cliente = (Cliente) object;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cliente = em.merge(cliente);
            em.remove(cliente);
            insertLog(em, String.format("Cliente excluído %s (%d)", cliente.getPessoa().getNome(),cliente.getPessoa().getId()));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Log.log(ClienteBO.class.getName(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return false;
    }
 
    @Override
    public Object findById(Object id) throws Exception {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }
   

    @Override
    public Collection findByParameters(String query, Object... params) throws Exception {
        CriteriaQuery cq = getQuery();
        
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    @Override
    public boolean findParameter(Object object, String query, Object... params) throws Exception {
        Cliente cliente = (Cliente) object;
        CriteriaBuilder builder = null;
        try {
            builder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Cliente> queryb  = builder.createQuery(Cliente.class);
            Root<Cliente> from = queryb.from(Cliente.class);
            
            for (int i = 0; i < params.length; i++){
            
            }
        } catch(Exception ex){
            
        }
        
        return false;
    }
    
      @Override
    public boolean insertLog(EntityManager em, String mensagem) {
       
        try {
            logAcao = new LogAcao();
            logAcao.setAcao(mensagem);
            em.persist(logAcao);
            return true;
        }catch (Exception ex){    
            Log.log(ClienteBO.class.getName(), ex);
        } 
        return false;
    }
    
    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            //Cliente.class.get
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public Collection findByParameters(String param, Object value) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public CriteriaQuery createQuery(){
        return  getQuery();
    }
}//fim classe
/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.Cliente;
import models.enuns.Comparadores;
import persistence.ClienteBO;
import utils.Log;

/**
 *
 * @author Rodrigo
 */
public class ClienteController /*implements DefaultController*/ {

    private Cliente cliente;
    private ClienteBO pbo;
    
    public ClienteController(Cliente cliente) {
        this.cliente = cliente;
        pbo = new ClienteBO();
        
    }

    public ClienteController() {
        cliente = new Cliente();
        pbo = new ClienteBO();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public boolean setCliente(Cliente cliente) {
        this.cliente = cliente;
        return true;
    }
  
    public boolean setObject(Object object) {
        try {
            this.cliente = (Cliente) object;
            return true;
        } catch(Exception ex){
            Log.log(ClienteController.class.getName(), ex);
        }
        return false;
    }//fim 
    
//    @Override
    public Object[][] objectToArray(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public boolean save() {
        
        try {
            return pbo.save(cliente);
        } catch (Exception ex) {
            Log.log(ClienteController.class.getName(), ex);
        }
        return false;
        
    }//fim save
//
//    @Override
    public boolean update() {
        try {
            return pbo.update(cliente);
        } catch (Exception ex){
            Log.log(ClienteController.class.getName(), ex);
        }
        return false;
    }

//    @Override
    public boolean delete() {
        try {
            return pbo.delete(cliente);
        } catch (Exception ex){
            Log.log(ClienteController.class.getName(), ex);
        }
        return false;
    }

    
    public List<Cliente> findByParameters(String param, Object value, Comparadores comp) {
        /*
        1.Fazer cast do valor do textFile
        2.usar comparador selecionado
        3.Consultar pelo campo escolhido acima
        */
        EntityManager em = null;
        Query consulta = null;
        try {
            em = pbo.getEntityManager();
            switch(param){
                case "nome":
                    consulta = em.createNamedQuery("Cliente.findByNome", Cliente.class).setParameter("nome", "%"+value+"%");
                    break;
                case "email":
                    consulta = em.createNamedQuery("Cliente.findByEmail", Cliente.class).setParameter("email", "%"+value+"%");
                    break;
                default: 
                    em.createNamedQuery("Cliente.findAll");
                    break;
            }//fim switch param
            return consulta.getResultList();
        } catch (Exception ex) {
            Log.log(ClienteBO.class.getName(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }//fim finally
        return null;
    }//fim find

    //@Override
    public Object findParameters(String param, Object value, Comparadores comp) {
        /*
        1.Fazer cast do valor do textFile
        2.usar comparador selecionado
        3.Consultar pelo campo escolhido acima
        */
        EntityManager em = null;
        Query consulta = null;
        try {
            em = pbo.getEntityManager();
            switch(param){
                case "nome":
                    consulta = em.createNamedQuery("Cliente.findByNome", Cliente.class).setParameter("nome", "%"+value+"%");
                    break;
                case "email":
                    consulta = em.createNamedQuery("Cliente.findByEmail", Cliente.class).setParameter("nome", "%"+value+"%");
                    break;
                default: 
                    em.createNamedQuery("Cliente.findAll");
                    break;
            }//fim switch param
            List resultList = consulta.getResultList();
            if (!resultList.isEmpty() || resultList.size() == 1){
                return resultList.get(0);
            }
        } catch (Exception ex) {
            Log.log(ClienteBO.class.getName(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }//fim finally
        return null;
    }

    //@Override
    public Object findById(Object object) {
        try {
            return pbo.findById(object);
        } catch (Exception ex) {
            Log.log(ClienteBO.class.getName(), ex);
        }
        return null;
    }

    //@Override
    public List<Cliente> findByParameters(String query, Object... params) {
        /*
        1.Fazer cast do valor do textFile
        2.usar comparador selecionado
        3.Consultar pelo campo escolhido acima
        */
        EntityManager em = null;
        Query consulta = null;
        List<Cliente> lista = new ArrayList<>();
        try {
            em = pbo.getEntityManager();
            consulta = em.createQuery(query, Cliente.class);
            for (int i=0; i< params.length;i++) {
                
                consulta.setParameter(i+1, params[i]);
            }
            lista = consulta.getResultList();
        } catch (Exception ex) {
            Log.log(ClienteBO.class.getName(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }//fim finally
        return lista;
    }

    public List<Cliente> findCadastradosPeriodo(Date date, Date date0) {
        /*
        1.Fazer cast do valor do textFile
        2.usar comparador selecionado
        3.Consultar pelo campo escolhido acima
        */
        EntityManager em = null;
        Query consulta = null;
        List<Cliente> lista = new ArrayList<>();
        try {
            em = pbo.getEntityManager();
            consulta = em.createNamedQuery("Cliente.cadastrados", Cliente.class)
                    .setParameter("inicio", date)
                    .setParameter("fim", date0);
            lista = consulta.getResultList();
        } catch (Exception ex) {
            Log.log(ClienteBO.class.getName(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }//fim finally
        return lista;
    }

}//fim classe
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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.Servico;
import models.enuns.Comparadores;
import persistence.ServicoBO;
import utils.Log;

/**
 *
 * @author Rodrigo
 */
public class ServicoController /*implements DefaultController*/ {

    private Servico servico;
    private ServicoBO cbo;
    
    public ServicoController(Servico servico) {
        this.servico = servico;
        cbo = new ServicoBO();
        
    }

    public ServicoController() {
        servico = new Servico();
        cbo = new ServicoBO();
    }

    public Servico getServico() {
        return servico;
    }

    public boolean setServico(Servico servico) {
        this.servico = servico;
        return true;
    }
    
   
//    @Override
    public boolean setObject(Object o) {
        this.servico = (Servico) o;
        return true;
    }

//    @Override
    public Object[][] objectToArray(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public boolean save() {
        
        try {
            return cbo.save(servico);
        } catch (Exception ex) {
            Log.log(ServicoController.class.getName(), ex);
        }
        return false;
        
    }//fim save
//
//    @Override
    public boolean update() {
        try {
            return cbo.update(servico);
            
        } catch (Exception ex){
            Log.log(ServicoController.class.getName(), ex);
        }
        return false;
    }

//    @Override
    public boolean delete() {
        try {
            return cbo.delete(servico);
        } catch (Exception ex){
            Log.log(ServicoController.class.getName(), ex);
        }
        return false;
    }

    /**
     * pESQUISA POR NOME E POR ID
     * @param param
     * @param value
     * @param comp
     * @return 
     */
    public List<Servico> findByParameters(String param, Object value, Comparadores comp) {
        /*
        1.Fazer cast do valor do textFile
        2.usar comparador selecionado
        3.Consultar pelo campo escolhido acima
        */
        EntityManager em = null;
        Query consulta = null;
        try {
            em = cbo.getEntityManager();
            switch(param){
                case "nome": //pesquisa por logradouro
                    consulta = em.createNamedQuery("Servico.findByNome", Servico.class).setParameter("param", "%"+value+"%");
                    break;
                case "id": //pesquisa por cep
                    consulta = em.createNamedQuery("Servico.findById", Servico.class).setParameter("param", (int)value);
                    break;
                
                default: 
                    em.createNamedQuery("Servico.findAll");
                    break;
            }//fim switch param
        } catch (Exception ex) {
            Log.log(ServicoBO.class.getName(), ex);
        } 
        return consulta.getResultList();
    }//fim find

    //@Override
    public Servico findParameters(String param, Object value, Comparadores comp) {
        /*
        1.Fazer cast do valor do textFile
        2.usar comparador selecionado
        3.Consultar pelo campo escolhido acima
        */
        EntityManager em = null;
        Query consulta = null;
        try {
            em = cbo.getEntityManager();
            switch(param){
                case "nome": //pesquisa por logradouro
                    switch(comp){
                        case CONTEM:
                            consulta = em.createNamedQuery("Servico.findByNome", Servico.class).setParameter("param", "%"+value+"%");
                            break;
                        case IGUAL:
                            consulta = em.createNamedQuery("Servico.findByNome", Servico.class).setParameter("param", value);
                            break;    
                        default: break;
                    }
                    break;
                case "id": //pesquisa por cep
                    consulta = em.createNamedQuery("Servico.findById", Servico.class).setParameter("param", (int)value);
                    break;
                case "ativo": //pesquisa por bairro
                    consulta = em.createNamedQuery("Servico.findByStatus", Servico.class).setParameter("param", "%"+value+"%");
                    break;
                case "cidade": //pesquisa por cidade
                    consulta = em.createNamedQuery("Servico.findByCidade", Servico.class).setParameter("cidade", "%"+value+"%");
                    break;
                default: 
                    em.createNamedQuery("Servico.findAll");
                    break;
            }//fim switch param
        } catch (Exception ex) {
            Log.log(ServicoBO.class.getName(), ex);
        } 
        return (Servico) consulta.getSingleResult();
    }

    //@Override
    public Servico findById(Object object) {
        try {
            return (Servico) cbo.findById(object);
        } catch (Exception ex) {
            Log.log(ServicoBO.class.getName(), ex);
        }
        return null;
    }

    public List<Servico> findByParameters(String query, Object... params) {
        /*
        1.Fazer cast do valor do textFile
        2.usar comparador selecionado
        3.Consultar pelo campo escolhido acima
        */
        EntityManager em = null;
        Query consulta = null;
        try {
            em = cbo.getEntityManager();
            consulta = em.createQuery(query, Servico.class);
            for (int i=0;i<params.length;i++){
                consulta.setParameter(i+1, params[i]);
            }
            
            return consulta.getResultList();
        } catch (Exception ex) {
            Log.log(ServicoBO.class.getName(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }//fim finally
        return new ArrayList();
    }
    
    
}//fim classe
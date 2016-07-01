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
import models.Convenio;
import models.Convenio;
import models.enuns.Comparadores;
import persistence.ConvenioBO;
import utils.Log;

/**
 *
 * @author Rodrigo
 */
public class ConvenioController /*implements DefaultController*/ {

    private Convenio convenio;
    private ConvenioBO cbo;
    
    public ConvenioController(Convenio convenio) {
        this.convenio = convenio;
        cbo = new ConvenioBO();
        
    }

    public ConvenioController() {
        convenio = new Convenio();
        cbo = new ConvenioBO();
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public boolean setConvenio(Convenio convenio) {
        this.convenio = convenio;
        return true;
    }
    
   
//    @Override
    public boolean setObject(Object o) {
        this.convenio = (Convenio) o;
        return true;
    }

//    @Override
    public Object[][] objectToArray(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public boolean save() {
        
        try {
            return cbo.save(convenio);
        } catch (Exception ex) {
            Log.log(ConvenioController.class.getName(), ex);
        }
        return false;
        
    }//fim save
//
//    @Override
    public boolean update() {
        try {
            return cbo.update(convenio);
            
        } catch (Exception ex){
            Log.log(ConvenioController.class.getName(), ex);
        }
        return false;
    }

//    @Override
    public boolean delete() {
        try {
            return cbo.delete(convenio);
        } catch (Exception ex){
            Log.log(ConvenioController.class.getName(), ex);
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
    public List<Convenio> findByParameters(String param, Object value, Comparadores comp) {
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
                    consulta = em.createNamedQuery("Convenio.findByNome", Convenio.class).setParameter("param", "%"+value+"%");
                    break;
                case "id": //pesquisa por cep
                    consulta = em.createNamedQuery("Convenio.findById", Convenio.class).setParameter("param", (int)value);
                    break;
                
                default: 
                    em.createNamedQuery("Convenio.findAll");
                    break;
            }//fim switch param
        } catch (Exception ex) {
            Log.log(ConvenioBO.class.getName(), ex);
        } 
        return consulta.getResultList();
    }//fim find

    //@Override
    public Convenio findParameters(String param, Object value, Comparadores comp) {
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
                            consulta = em.createNamedQuery("Convenio.findByNome", Convenio.class).setParameter("param", "%"+value+"%");
                            break;
                        case IGUAL:
                            consulta = em.createNamedQuery("Convenio.findByNome", Convenio.class).setParameter("param", value);
                            break;    
                        default: break;
                    }
                    break;
                case "id": //pesquisa por cep
                    consulta = em.createNamedQuery("Convenio.findById", Convenio.class).setParameter("param", (int)value);
                    break;
                case "ativo": //pesquisa por bairro
                    consulta = em.createNamedQuery("Convenio.findByStatus", Convenio.class).setParameter("param", "%"+value+"%");
                    break;
                case "cidade": //pesquisa por cidade
                    consulta = em.createNamedQuery("Convenio.findByCidade", Convenio.class).setParameter("cidade", "%"+value+"%");
                    break;
                default: 
                    em.createNamedQuery("Convenio.findAll");
                    break;
            }//fim switch param
        } catch (Exception ex) {
            Log.log(ConvenioBO.class.getName(), ex);
        } 
        return (Convenio) consulta.getSingleResult();
    }

    //@Override
    public Convenio findById(Object object) {
        try {
            return (Convenio) cbo.findById(object);
        } catch (Exception ex) {
            Log.log(ConvenioBO.class.getName(), ex);
        }
        return null;
    }

    public List<Convenio> findByParameters(String query, Object... params) {
        /*
        1.Fazer cast do valor do textFile
        2.usar comparador selecionado
        3.Consultar pelo campo escolhido acima
        */
        EntityManager em = null;
        Query consulta = null;
        try {
            em = cbo.getEntityManager();
            consulta = em.createQuery(query, Convenio.class);
            for (int i=0;i<params.length;i++){
                consulta.setParameter(i+1, params[i]);
            }
            
            return consulta.getResultList();
        } catch (Exception ex) {
            Log.log(ConvenioBO.class.getName(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }//fim finally
        return new ArrayList();
    }
    
    
}//fim classe
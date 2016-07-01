/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package controllers;

import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.Tratamento;
import models.enuns.Comparadores;
import persistence.TratamentoBO;
import utils.Log;

/**
 *
 * @author Rodrigo
 */
public class TratamentoController /*implements DefaultController*/ {

    private Tratamento tratamento;
    private TratamentoBO cbo;
    
    public TratamentoController(Tratamento tratamento) {
        this.tratamento = tratamento;
        cbo = new TratamentoBO();
        
    }

    public TratamentoController() {
        tratamento = new Tratamento();
        cbo = new TratamentoBO();
    }

    public Tratamento getTratamento() {
        return tratamento;
    }

    public boolean setTratamento(Tratamento tratamento) {
        this.tratamento = tratamento;
        return true;
    }
    
   
//    @Override
    public boolean setObject(Object o) {
        this.tratamento = (Tratamento) o;
        return true;
    }

//    @Override
    public Object[][] objectToArray(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public boolean save() {
        
        try {
            return cbo.save(tratamento);
        } catch (Exception ex) {
            Log.log(TratamentoController.class.getName(), ex);
        }
        return false;
        
    }//fim save
//
//    @Override
    public boolean update() {
        try {
            return cbo.update(tratamento);
            
        } catch (Exception ex){
            Log.log(TratamentoController.class.getName(), ex);
        }
        return false;
    }

//    @Override
    public boolean delete() {
        try {
            return cbo.delete(tratamento);
        } catch (Exception ex){
            Log.log(TratamentoController.class.getName(), ex);
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
    public List<Tratamento> findByParameters(String param, Object value, Comparadores comp) {
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
                    consulta = em.createNamedQuery("Tratamento.findByNome", Tratamento.class).setParameter("param", "%"+value+"%");
                    break;
                case "id": //pesquisa por cep
                    consulta = em.createNamedQuery("Tratamento.findById", Tratamento.class).setParameter("param", (int)value);
                    break;
                
                default: 
                    em.createNamedQuery("Tratamento.findAll");
                    break;
            }//fim switch param
        } catch (Exception ex) {
            Log.log(TratamentoBO.class.getName(), ex);
        } 
        return consulta.getResultList();
    }//fim find

    //@Override
    public Tratamento findParameters(String param, Object value, Comparadores comp) {
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
                            consulta = em.createNamedQuery("Tratamento.findByNome", Tratamento.class).setParameter("param", "%"+value+"%");
                            break;
                        case IGUAL:
                            consulta = em.createNamedQuery("Tratamento.findByNome", Tratamento.class).setParameter("param", value);
                            break;    
                        default: break;
                    }
                    break;
                case "id": //pesquisa por cep
                    consulta = em.createNamedQuery("Tratamento.findById", Tratamento.class).setParameter("param", (int)value);
                    break;
                case "ativo": //pesquisa por bairro
                    consulta = em.createNamedQuery("Tratamento.findByStatus", Tratamento.class).setParameter("param", "%"+value+"%");
                    break;
                case "cidade": //pesquisa por cidade
                    consulta = em.createNamedQuery("Tratamento.findByCidade", Tratamento.class).setParameter("cidade", "%"+value+"%");
                    break;
                default: 
                    em.createNamedQuery("Tratamento.findAll");
                    break;
            }//fim switch param
        } catch (Exception ex) {
            Log.log(TratamentoBO.class.getName(), ex);
        } 
        return (Tratamento) consulta.getSingleResult();
    }

    //@Override
    public Tratamento findById(Object object) {
        try {
            return (Tratamento) cbo.findById(object);
        } catch (Exception ex) {
            Log.log(TratamentoBO.class.getName(), ex);
        }
        return null;
    }

    //@Override
    public Collection findByParameters(String query, Object... params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}//fim classe
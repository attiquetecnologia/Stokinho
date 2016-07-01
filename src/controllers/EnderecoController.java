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
import models.Endereco;
import models.enuns.Comparadores;
import persistence.EnderecoBO;
import utils.Log;

/**
 *
 * @author Rodrigo
 */
public class EnderecoController /*implements DefaultController*/ {

    private Endereco endereco;
    private EnderecoBO ebo;
    
    public EnderecoController(Endereco endereco) {
        this.endereco = endereco;
        ebo = new EnderecoBO();
        
    }

    public EnderecoController() {
        endereco = new Endereco();
        ebo = new EnderecoBO();
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public boolean setEndereco(Endereco endereco) {
        this.endereco = endereco;
        return true;
    }
    
   
//    @Override
    public boolean setObject(Object[] o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public Object[][] objectToArray(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public boolean save() {
        
        try {
            return ebo.save(endereco);
        } catch (Exception ex) {
            Log.log(EnderecoController.class.getName(), ex);
        }
        return false;
        
    }//fim save
//
//    @Override
    public boolean update() {
        try {
            return ebo.update(endereco);
            
        } catch (Exception ex){
            Log.log(EnderecoController.class.getName(), ex);
        }
        return false;
    }

//    @Override
    public boolean delete() {
        try {
            return ebo.delete(endereco);
        } catch (Exception ex){
            Log.log(EnderecoController.class.getName(), ex);
        }
        return false;
    }

    
    public Collection findByParameters(String param, Object value, Comparadores comp) {
        /*
        1.Fazer cast do valor do textFile
        2.usar comparador selecionado
        3.Consultar pelo campo escolhido acima
        */
        EntityManager em = null;
        Query consulta = null;
        try {
            em = ebo.getEntityManager();
            switch(param){
                case "logradouro": //pesquisa por logradouro
                    consulta = em.createNamedQuery("Endereco.findByLogradouro", Endereco.class).setParameter("logradouro", "%"+value+"%");
                    break;
                case "cep": //pesquisa por cep
                    switch(comp){
                        case CONTEM:
                            consulta = em.createNamedQuery("Endereco.findLikeCep", Endereco.class).setParameter("cep", "%"+value+"%");
                            break;
                        case IGUAL:
                            consulta = em.createNamedQuery("Endereco.findByCep", Endereco.class).setParameter("cep", value);
                            break;
                        default: break;
                    }//fim switch comp
                    break;
                case "bairro": //pesquisa por bairro
                    consulta = em.createNamedQuery("Endereco.findByBairro", Endereco.class).setParameter("bairro", "%"+value+"%");
                    break;
                case "cidade": //pesquisa por cidade
                    consulta = em.createNamedQuery("Endereco.findByCidade", Endereco.class).setParameter("cidade", "%"+value+"%");
                    break;
                case "uf": //pesquisa por uf
                    consulta = em.createNamedQuery("Endereco.findByUf", Endereco.class).setParameter("uf", "%"+value+"%");
                    break;
                default: 
                    em.createNamedQuery("Endereco.findAll");
                    break;
            }//fim switch param
            return consulta.getResultList();
        } catch (Exception ex) {
            Log.log(EnderecoBO.class.getName(), ex);
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
            em = ebo.getEntityManager();
            switch(param){
                case "logradouro": //pesquisa por logradouro
                    consulta = em.createNamedQuery("Endereco.findByLogradouro", Endereco.class).setParameter("logradouro", "%"+value+"%");
                    break;
                case "cep": //pesquisa por cep
                    switch(comp){
                        case CONTEM:
                            consulta = em.createNamedQuery("Endereco.findLikeCep", Endereco.class).setParameter("cep", "%"+value+"%");
                            break;
                        case IGUAL:
                            consulta = em.createNamedQuery("Endereco.findByCep", Endereco.class).setParameter("cep", value);
                            break;
                        default: break;
                    }//fim switch comp
                    break;
                case "bairro": //pesquisa por bairro
                    consulta = em.createNamedQuery("Endereco.findByBairro", Endereco.class).setParameter("bairro", "%"+value+"%");
                    break;
                case "cidade": //pesquisa por cidade
                    consulta = em.createNamedQuery("Endereco.findByCidade", Endereco.class).setParameter("cidade", "%"+value+"%");
                    break;
                case "uf": //pesquisa por uf
                    consulta = em.createNamedQuery("Endereco.findByUf", Endereco.class).setParameter("uf", "%"+value+"%");
                    break;
                default: 
                    em.createNamedQuery("Endereco.findAll");
                    break;
            }//fim switch param
            List resultList = consulta.getResultList();
            if (!resultList.isEmpty() || resultList.size() == 1){
                return resultList.get(0);
            }
        } catch (Exception ex) {
            Log.log(EnderecoBO.class.getName(), ex);
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
            return ebo.findById(object);
        } catch (Exception ex) {
            Log.log(EnderecoBO.class.getName(), ex);
        }
        return null;
    }

    //@Override
    public Collection findByParameters(String query, Object... params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Endereco findByCep(String cep) {
        return (Endereco) findParameters("cep", cep, Comparadores.IGUAL);
    }
    
}//fim classe
/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package controllers;

import controllers.EnderecoController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.Medico;
import models.enuns.Comparadores;
import models.interfaces.DefaultController;
import persistence.PessoaBO;
import persistence.MedicoBO;
import utils.Log;

/**
 *
 * @author Rodrigo
 */
public class MedicoController{
    private Medico medico;
    private MedicoBO pbo;

    public MedicoController() {
        pbo = new MedicoBO();
    }

    
    public MedicoController(Medico medico) {
        this.medico = medico;
        pbo = new MedicoBO();
    }

    
    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }


//    @Override
    public boolean setObject(Object object) {
        try {
            this.medico = (Medico) object;
            return true;
        } catch(Exception ex){
            Log.log(MedicoController.class.getName(), ex);
        }
        return false;
    }//fim 

    

//    @Override
    public boolean save() {
        try {
            return pbo.save(medico);
        } catch (Exception ex) {
            Log.log(EnderecoController.class.getName(), ex);
        }
        return false;
    }

//    @Override
    public boolean update() {
        try {
            return pbo.update(medico);
            
        } catch (Exception ex){
            Log.log(EnderecoController.class.getName(), ex);
        }
        return false;
    }

//    @Override
    public boolean delete() {
        try {
            return pbo.delete(medico);
            
        } catch (Exception ex){
            Log.log(EnderecoController.class.getName(), ex);
        }
        return false;
    }

    public List<Medico> findByParameters(String param, Object value, Comparadores comp) {
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
                    consulta = em.createNamedQuery("Medico.findByNome", Medico.class).setParameter("nome", "%"+value+"%");
                    break;
                case "email":
                    consulta = em.createNamedQuery("Medico.findByEmail", Medico.class).setParameter("email", "%"+value+"%");
                    break;
                default: 
                    em.createNamedQuery("Medico.findAll");
                    break;
            }//fim switch param
            return consulta.getResultList();
        } catch (Exception ex) {
            Log.log(MedicoBO.class.getName(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }//fim finally
        return null;
    }//fim find

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
                    consulta = em.createNamedQuery("Medico.findByNome", Medico.class).setParameter("nome", "%"+value+"%");
                    break;
                case "email":
                    consulta = em.createNamedQuery("Medico.findByEmail", Medico.class).setParameter("nome", "%"+value+"%");
                    break;
                default: 
                    em.createNamedQuery("Medico.findAll");
                    break;
            }//fim switch param
            List resultList = consulta.getResultList();
            if (!resultList.isEmpty() || resultList.size() == 1){
                return resultList.get(0);
            }
        } catch (Exception ex) {
            Log.log(MedicoBO.class.getName(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }//fim finally
        return null;
    }

        
//    @Override
    public List<Medico> findByParameters(String query, Object... params) {
        /*
        1.Fazer cast do valor do textFile
        2.usar comparador selecionado
        3.Consultar pelo campo escolhido acima
        */
        EntityManager em = null;
        Query consulta = null;
        try {
            em = pbo.getEntityManager();
            consulta = em.createQuery(query, Medico.class);
            for (int i=0;i<params.length;i++){
                consulta.setParameter(i+1, params[i]);
            }
            
            return consulta.getResultList();
        } catch (Exception ex) {
            Log.log(MedicoBO.class.getName(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }//fim finally
        return new ArrayList();
    }

//    @Override
    public boolean findParameters(Object object, String query, Object... params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public Object findById(Object id) {
        try {
            return pbo.findById(id);
        } catch (Exception ex) {
            Log.log(MedicoBO.class.getName(), ex);
        }
        return null;
    }

}//fim classe

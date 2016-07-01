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
import models.Medico;
import models.Paciente;
import models.enuns.Comparadores;
import persistence.PacienteBO;
import utils.Log;
import utils.ShowMessage;

/**
 *
 * @author Rodrigo
 */
public class PacienteController /*implements DefaultController*/ {

    private Paciente paciente;
    private PacienteBO pbo;
    
    public PacienteController(Paciente paciente) {
        this.paciente = paciente;
        pbo = new PacienteBO();
        
    }

    public PacienteController() {
        paciente = new Paciente();
        pbo = new PacienteBO();
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public boolean setPaciente(Paciente paciente) {
        this.paciente = paciente;
        return true;
    }
  
    public boolean setObject(Object object) {
        try {
            this.paciente = (Paciente) object;
            return true;
        } catch(Exception ex){
            Log.log(PacienteController.class.getName(), ex);
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
            return pbo.save(paciente);
        } catch (Exception ex) {
            Log.log(PacienteController.class.getName(), ex);
        }
        return false;
        
    }//fim save
//
//    @Override
    public boolean update() {
        try {
            return pbo.update(paciente);
        } catch (Exception ex){
            Log.log(PacienteController.class.getName(), ex);
        }
        return false;
    }

//    @Override
    public boolean delete() {
        try {
            return pbo.delete(paciente);
        } catch (Exception ex){
            Log.log(PacienteController.class.getName(), ex);
        }
        return false;
    }

    
    public List<Paciente> findByParameters(String param, Object value, Comparadores comp) {
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
                    consulta = em.createNamedQuery("Paciente.findByNome", Paciente.class).setParameter("nome", "%"+value+"%");
                    break;
                case "email":
                    consulta = em.createNamedQuery("Paciente.findByEmail", Paciente.class).setParameter("email", "%"+value+"%");
                    break;
                default: 
                    em.createNamedQuery("Paciente.findAll");
                    break;
            }//fim switch param
            return consulta.getResultList();
        } catch (Exception ex) {
            Log.log(PacienteBO.class.getName(), ex);
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
                    consulta = em.createNamedQuery("Paciente.findByNome", Paciente.class).setParameter("nome", "%"+value+"%");
                    break;
                case "email":
                    consulta = em.createNamedQuery("Paciente.findByEmail", Paciente.class).setParameter("nome", "%"+value+"%");
                    break;
                default: 
                    em.createNamedQuery("Paciente.findAll");
                    break;
            }//fim switch param
            List resultList = consulta.getResultList();
            if (!resultList.isEmpty() || resultList.size() == 1){
                return resultList.get(0);
            }
        } catch (Exception ex) {
            Log.log(PacienteBO.class.getName(), ex);
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
            Log.log(PacienteBO.class.getName(), ex);
        }
        return null;
    }

    //@Override
    public List<Paciente> findByParameters(String query, Object... params) {
        /*
        1.Fazer cast do valor do textFile
        2.usar comparador selecionado
        3.Consultar pelo campo escolhido acima
        */
        EntityManager em = null;
        Query consulta = null;
        List<Paciente> lista = new ArrayList<>();
        try {
            em = pbo.getEntityManager();
            consulta = em.createQuery(query, Paciente.class);
            for (int i=0; i< params.length;i++) {
                
                consulta.setParameter(i+1, params[i]);
            }
            lista = consulta.getResultList();
        } catch (Exception ex) {
            Log.log(PacienteBO.class.getName(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }//fim finally
        return lista;
    }

    public List<Paciente> findCadastradosPeriodo(Date date, Date date0) {
        /*
        1.Fazer cast do valor do textFile
        2.usar comparador selecionado
        3.Consultar pelo campo escolhido acima
        */
        EntityManager em = null;
        Query consulta = null;
        List<Paciente> lista = new ArrayList<>();
        try {
            em = pbo.getEntityManager();
            consulta = em.createNamedQuery("Paciente.cadastrados", Paciente.class)
                    .setParameter("inicio", date)
                    .setParameter("fim", date0);
            lista = consulta.getResultList();
        } catch (Exception ex) {
            Log.log(PacienteBO.class.getName(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }//fim finally
        return lista;
    }

}//fim classe
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
import models.Profissional;
import models.enuns.Comparadores;
import models.interfaces.DefaultController;
import persistence.ConvenioBO;
import persistence.ProfissionalBO;
import utils.Log;
import utils.ShowMessage;

/**
 *
 * @author Rodrigo
 */
public class ProfissionalController implements DefaultController {
    private Profissional profissional;
    private ProfissionalBO pbo;

    public ProfissionalController() {
        pbo = new ProfissionalBO();
    }

    
    public ProfissionalController(Profissional profissional) {
        this.profissional = profissional;
        pbo = new ProfissionalBO();
    }

    
    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    @Override
    public boolean setObject(Object[] o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean setObject(Object object) {
        try {
            this.profissional = (Profissional) object;
            return true;
        } catch(Exception ex){
            Log.log(ProfissionalController.class.getName(), ex);
        }
        return false;
    }//fim 

    @Override
    public Object[][] objectToArray(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save() {
        try {
            return pbo.save(profissional);
        } catch (Exception ex) {
            Log.log(EnderecoController.class.getName(), ex);
        }
        return false;
    }

    @Override
    public boolean update() {
        try {
            return pbo.update(profissional);
            
        } catch (Exception ex){
            ShowMessage.errorMessage("Problemas ao salvar o registro!");
            Log.log(EnderecoController.class.getName(), ex);
        }
        return false;
    }

    @Override
    public boolean delete() {
        try {
            return pbo.delete(profissional);
        } catch (Exception ex){
            ShowMessage.errorMessage("Problemas ao apagar o registro!");
            Log.log(PessoaController.class.getName(), ex);
        }
        return false;
    }

    public List findByParameters(String param, Object value, Comparadores comp) {
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
                    consulta = em.createNamedQuery("Profissional.findByNome", Profissional.class).setParameter("nome", "%"+value+"%");
                    break;
                case "email":
                    consulta = em.createNamedQuery("Profissional.findByEmail", Profissional.class).setParameter("email", "%"+value+"%");
                    break;
                default: 
                    em.createNamedQuery("Profissional.findAll");
                    break;
            }//fim switch param
            return consulta.getResultList();
        } catch (Exception ex) {
            Log.log(ProfissionalBO.class.getName(), ex);
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
                    consulta = em.createNamedQuery("Profissional.findByNome", Profissional.class).setParameter("nome", "%"+value+"%");
                    break;
                case "email":
                    consulta = em.createNamedQuery("Profissional.findByEmail", Profissional.class).setParameter("nome", "%"+value+"%");
                    break;
                default: 
                    em.createNamedQuery("Profissional.findAll");
                    break;
            }//fim switch param
            List resultList = consulta.getResultList();
            if (!resultList.isEmpty() || resultList.size() == 1){
                return resultList.get(0);
            }
        } catch (Exception ex) {
            Log.log(ProfissionalBO.class.getName(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }//fim finally
        return null;
    }

    @Override
    public Object findById(Object id) {
        try {
            return pbo.findById(id);
        } catch (Exception ex) {
            Log.log(ProfissionalBO.class.getName(), ex);
        }
        return null;
    }

    @Override
    public List<Profissional> findByParameters(String query, Object... params) {
        /*
        1.Fazer cast do valor do textFile
        2.usar comparador selecionado
        3.Consultar pelo campo escolhido acima
        */
        EntityManager em = null;
        Query consulta = null;
        try {
            em = pbo.getEntityManager();
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

    @Override
    public boolean findParameters(Object object, String query, Object... params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}//fim classe

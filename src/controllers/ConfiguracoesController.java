/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package controllers;

import models.Configuracoes;
import persistence.ConfiguracoesBO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.interfaces.DefaultController;
import utils.Log;
import utils.ShowMessage;

/**
 *
 * @author Rodrigo
 */
public class ConfiguracoesController implements DefaultController{

    private ConfiguracoesBO cbo;
    private Configuracoes cfg;
    

    public ConfiguracoesController (){
        cbo = new ConfiguracoesBO();
    
    }
    
    public ConfiguracoesController (Configuracoes cfg){
        this.cfg = cfg;
        cbo = new ConfiguracoesBO();
    }
    
    public Configuracoes getCfg() {
        return cfg;
    }

    public void setCfg(Configuracoes cfg) {
        this.cfg = cfg;
    }
    
    
    @Override
    public boolean setObject(Object o) {
        
        try {
            cfg = (Configuracoes) o;
            return true;
        } catch(NullPointerException ex){
            ShowMessage.errorMessage(ex.getMessage());
            return false;
        } 
    }//fim setObject

    @Override
    public Object[][] objectToArray(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save() {
        
        try {
            cbo.save(cfg);
            
            return true;
        } catch (SQLException ex) {
            Log.log(ConfiguracoesController.class.getName(), ex);
        }
        return false;
    }//fim modulo

    @Override
    public boolean update() {
        
        try {
            return cbo.update(cfg);
            
        } catch (SQLException ex) {
            Log.log(ConfiguracoesController.class.getName(), ex);
        }
        return false;
    }

    @Override
    public boolean delete() {
        
        try {
            cbo.delete(cfg);
           
            return true;
        } catch (SQLException ex) {
            Log.log(ConfiguracoesController.class.getName(), ex);
        }
        return false;
    }

    @Override
    public Collection findByParameters(String query, Object... params) {
        
        try {
            return cbo.findByParameters(query, params);
        } catch (SQLException ex) {
            Log.log(ConfiguracoesController.class.getName(), ex);
        }
        return new ArrayList();
    }

    @Override
    public boolean findParameters(Object object, String query, Object... params) {
        
        try {
            return cbo.findParameter(object, query, params);
        } catch (SQLException ex) {
            Log.log(ConfiguracoesController.class.getName(), ex);
        }
        return false;
    }

    @Override
    public Configuracoes findById(Object object) {
        
        try {
            return (Configuracoes) cbo.findById(object);
            
        } catch (SQLException ex) {
            Log.log(ConfiguracoesController.class.getName(), ex);
        }
        return null;
    }
    
    public Collection findAll(){
        return findByParameters("select * from configuracoes");
    }

    public Configuracoes findCfgDefault() {
        EntityManager em = null;
        Query consulta = null;
        
        try {
            consulta = cbo.getEntityManager().createNamedQuery("Configuracoes.findDefault",Configuracoes.class);
            if (consulta.getResultList().size() == 1){
                return (Configuracoes) consulta.getSingleResult();
            }
        }catch (Exception ex){
            Log.log(Configuracoes.class.getName(), ex);
        } finally {
            if (em != null)
                em.close();
        }
        return null;
    }
    
    
    /**
     * Is Work Day.
     * Esta função recebe uma data como dia e verifica se é ou não dia trabalhado.
     * @param day
     * @return true
     */
    @Override
    public boolean setObject(Object[] o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}//fim classe

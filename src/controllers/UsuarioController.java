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
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.usuarios.Usuario;
import models.enuns.Comparadores;
import models.interfaces.DefaultController;
import models.logs.LogAcao;
import models.usuarios.Sessao;
import persistence.LogAcaoDAO;
import persistence.UsuarioBO;
import utils.Log;

/**
 *
 * @author Rodrigo
 */
public class UsuarioController implements DefaultController {

    private Usuario usuario;
    private UsuarioBO ebo;
    
    public UsuarioController(Usuario usuario) {
        this.usuario = usuario;
        ebo = new UsuarioBO();
        
    }

    public UsuarioController() {
        usuario = new Usuario();
        ebo = new UsuarioBO();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public boolean setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return true;
    }
    
   
    @Override
    public boolean setObject(Object[] o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[][] objectToArray(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save() {
        
        try {
            return ebo.save(usuario);
        } catch (Exception ex) {
            Log.log(UsuarioController.class.getName(), ex);
        }
        return false;
        
    }//fim save
//
    @Override
    public boolean update() {
        try {
            return ebo.update(usuario);
            
        } catch (Exception ex){
            Log.log(UsuarioController.class.getName(), ex);
        }
        return false;
    }

    @Override
    public boolean delete() {
        try {
            return ebo.delete(usuario);
        } catch (Exception ex){
            Log.log(UsuarioController.class.getName(), ex);
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
                case "login": //pesquisa por cep
                    switch(comp){
                        case CONTEM:
                            consulta = em.createNamedQuery("Usuario.findByLogin", Usuario.class).setParameter("login", "%"+value+"%");
                            break;
                        case IGUAL:
                            consulta = em.createNamedQuery("Usuario.findById", Usuario.class).setParameter("login", value);
                            break;
                        default: break;
                    }//fim switch comp
                    break;
                case "email": //pesquisa por bairro
                    switch(comp){
                        case CONTEM:
                            consulta = em.createNamedQuery("Usuario.findByEmail", Usuario.class).setParameter("email", "%"+value+"%");
                            break;
                        case IGUAL:
                            consulta = em.createNamedQuery("Usuario.findByEmail", Usuario.class).setParameter("email", value);
                            break;
                        default: break;
                    }//fim switch comp
                    break;
                default: 
                    em.createNamedQuery("Usuario.findAll");
                    break;
            }//fim switch param
            return consulta.getResultList();
        } catch (Exception ex) {
            Log.log(UsuarioBO.class.getName(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }//fim finally
        return null;
    }//fim find

    @Override
    public boolean findParameters(Object object, String query, Object... params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object findById(Object id) {
        try {
            return ebo.findById(id);
        } catch (Exception ex) {
            Log.log(UsuarioBO.class.getName(), ex);
        }
        return null;
    }

   
    @Override
    public List<Usuario> findByParameters(String query, Object... params) {
        EntityManager em = null;
        Query consulta = null;
        try {
            em = ebo.getEntityManager();
            consulta = em.createQuery(query, Usuario.class).setParameter("param", params[0]);
        }catch(Exception ex){
            Log.log(UsuarioController.class.getName(), ex);
        }
        return consulta.getResultList();
    }

    @Override
    public boolean setObject(Object object) {
        try {
            this.usuario = (Usuario) object;
            return true;
        }catch(Exception ex){
            Log.log(UsuarioController.class.getName(), ex);
            return false;   
        }
    }

    public boolean alteraSenha(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Usuario login(String usuario, String senha){
        Usuario u = null;
        EntityManager em = null;
        Query consulta = null;
        try {
            em = ebo.getEntityManager();
            consulta = em.createNamedQuery("Usuario.login",Usuario.class)
                    .setParameter("login", usuario).setParameter("senha", senha);
            
            if (!consulta.getResultList().isEmpty() || consulta.getResultList().size() == 1){
                u = (Usuario) consulta.getResultList().get(0);
                Sessao.getInstance().setUsuario(u);   //coloca na sessão
                new LogAcaoDAO().save(new LogAcao("Usuário logado com sucesso!",u.getLogin()));
            }
        }catch(Exception ex){
            Log.log(UsuarioController.class.getName(), ex);
        }
        
        return u;
    }    
    
    /**
     * Retorna todos os logins cadastrados no sistema
     * ativos ou não
     * @return 
     */
    public List<String> findLogins(){
        List<String> logins = new ArrayList<>();
        EntityManager em = null;
        Query consulta = null;
        try {
            em = ebo.getEntityManager();
            consulta = em.createNamedQuery("Usuario.findAll",Usuario.class);
            for (Iterator it = consulta.getResultList().iterator(); it.hasNext();) {
                Usuario u = (Usuario) it.next();
                logins.add(u.getLogin());
            }
        }catch(Exception ex){
            Log.log(UsuarioController.class.getName(), ex);
        }
        return logins;
    }
    
}//fim classe
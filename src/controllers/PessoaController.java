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
import javax.persistence.TemporalType;
import models.Pessoa;
import models.enuns.Comparadores;
import persistence.PessoaBO;
import utils.Log;

/**
 *
 * @author Rodrigo
 */
public class PessoaController /*implements DefaultController*/ {

    private Pessoa pessoa;
    private PessoaBO pbo;
    
    public PessoaController(Pessoa pessoa) {
        this.pessoa = pessoa;
        pbo = new PessoaBO();
        
    }

    public PessoaController() {
        pessoa = new Pessoa();
        pbo = new PessoaBO();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public boolean setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
            return pbo.save(pessoa);
        } catch (Exception ex) {
            Log.log(PessoaController.class.getName(), ex);
        }
        return false;
        
    }//fim save
//
//    @Override
    public boolean update() {
        try {
            return pbo.update(pessoa);
            
        } catch (Exception ex){
            Log.log(PessoaController.class.getName(), ex);
        }
        return false;
    }

//    @Override
    public boolean delete() {
        try {
            return pbo.delete(pessoa);
        } catch (Exception ex){
            Log.log(PessoaController.class.getName(), ex);
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
            em = pbo.getEntityManager();
            switch(param){
                case "nome":
                    consulta = em.createNamedQuery("Pessoa.findByNome", Pessoa.class).setParameter("nome", "%"+value+"%");
                    break;
                case "email":
                    consulta = em.createNamedQuery("Pessoa.findByEmail", Pessoa.class).setParameter("email", "%"+value+"%");
                    break;
                default: 
                    em.createNamedQuery("Pessoa.findAll");
                    break;
            }//fim switch param
            return consulta.getResultList();
        } catch (Exception ex) {
            Log.log(PessoaBO.class.getName(), ex);
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
                    consulta = em.createNamedQuery("Pessoa.findByNome", Pessoa.class).setParameter("nome", "%"+value+"%");
                    break;
                case "email":
                    consulta = em.createNamedQuery("Pessoa.findByEmail", Pessoa.class).setParameter("email", "%"+value+"%");
                    break;
                case "cpf":
                    switch(comp){
                        case CONTEM:
                            consulta = em.createNamedQuery("Pessoa.findByCPF", Pessoa.class).setParameter("cpf", "%"+value+"%");
                            break;
                        case IGUAL:
                            consulta = em.createNamedQuery("Pessoa.findByCPF", Pessoa.class).setParameter("cpf", value);
                            break;
                        default: break;
                    }
                    break;
                default: 
                    em.createNamedQuery("Pessoa.findAll");
                    break;
            }//fim switch param
            List resultList = consulta.getResultList();
            if (!resultList.isEmpty() || resultList.size() == 1){
                return resultList.get(0);
            }
        } catch (Exception ex) {
            Log.log(PessoaBO.class.getName(), ex);
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
            Log.log(PessoaBO.class.getName(), ex);
        }
        return null;
    }

    //@Override
    public Collection findByParameters(String query, Object... params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Pessoa> findAniversariantes(Date inicio, Date fim) {
        /*
        1.Fazer cast do valor do textFile
        2.usar comparador selecionado
        3.Consultar pelo campo escolhido acima
        */
        EntityManager em = null;
        Query consulta = null;
        try {
            
            em = pbo.getEntityManager();
            String query = "SELECT * FROM Pessoas p "
                    + "WHERE "
                    + "extract(month from p.pes_nascimento) "
                    + "BETWEEN extract(MONTH from ?) "
                    + "AND extract(MONTH from ?) "
                    + "ORDER BY p.pes_nascimento ASC";
            
            consulta = em.createNamedQuery("Pessoa.aniversariantes"
                    ,Pessoa.class)
                    .setParameter("inicio", inicio, TemporalType.DATE)
                    .setParameter("fim", fim, TemporalType.DATE);
            
            List resultList = consulta.getResultList();
            return resultList;
        } catch (Exception ex) {
            Log.log(PessoaBO.class.getName(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }//fim finally
        return new ArrayList();
    }

    
}//fim classe
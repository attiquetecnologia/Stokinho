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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.Produto;
import models.enuns.Comparadores;
import persistence.ProdutoBO;
import utils.Log;

/**
 *
 * @author Rodrigo
 */
public class ProdutoController{
    private Produto produto;
    private ProdutoBO pbo;

    public ProdutoController() {
        pbo = new ProdutoBO();
    }

    
    public ProdutoController(Produto produto) {
        this.produto = produto;
        pbo = new ProdutoBO();
    }

    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }


//    @Override
    public boolean setObject(Object object) {
        try {
            this.produto = (Produto) object;
            return true;
        } catch(Exception ex){
            Log.log(ProdutoController.class.getName(), ex);
        }
        return false;
    }//fim 

    

//    @Override
    public boolean save() {
        try {
            return pbo.save(produto);
        } catch (Exception ex) {
            Log.log(EnderecoController.class.getName(), ex);
        }
        return false;
    }

//    @Override
    public boolean update() {
        try {
            return pbo.update(produto);
            
        } catch (Exception ex){
            Log.log(EnderecoController.class.getName(), ex);
        }
        return false;
    }

//    @Override
    public boolean delete() {
        try {
            return pbo.delete(produto);
            
        } catch (Exception ex){
            Log.log(EnderecoController.class.getName(), ex);
        }
        return false;
    }

    public List<Produto> findByParameters(String param, Object value, Comparadores comp) {
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
                    consulta = em.createNamedQuery("Produto.findByNome", Produto.class).setParameter("nome", "%"+value+"%");
                    break;
                case "email":
                    consulta = em.createNamedQuery("Produto.findByEmail", Produto.class).setParameter("email", "%"+value+"%");
                    break;
                default: 
                    em.createNamedQuery("Produto.findAll");
                    break;
            }//fim switch param
            return consulta.getResultList();
        } catch (Exception ex) {
            Log.log(ProdutoBO.class.getName(), ex);
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
                    consulta = em.createNamedQuery("Produto.findByNome", Produto.class).setParameter("nome", "%"+value+"%");
                    break;
                case "email":
                    consulta = em.createNamedQuery("Produto.findByEmail", Produto.class).setParameter("nome", "%"+value+"%");
                    break;
                default: 
                    em.createNamedQuery("Produto.findAll");
                    break;
            }//fim switch param
            List resultList = consulta.getResultList();
            if (!resultList.isEmpty() || resultList.size() == 1){
                return resultList.get(0);
            }
        } catch (Exception ex) {
            Log.log(ProdutoBO.class.getName(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }//fim finally
        return null;
    }

        
//    @Override
    public List<Produto> findByParameters(String query, Object... params) {
        /*
        1.Fazer cast do valor do textFile
        2.usar comparador selecionado
        3.Consultar pelo campo escolhido acima
        */
        EntityManager em = null;
        Query consulta = null;
        try {
            em = pbo.getEntityManager();
            consulta = em.createQuery(query, Produto.class);
            for (int i=0;i<params.length;i++){
                consulta.setParameter(i+1, params[i]);
            }
            
            return consulta.getResultList();
        } catch (Exception ex) {
            Log.log(ProdutoBO.class.getName(), ex);
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
            Log.log(ProdutoBO.class.getName(), ex);
        }
        return null;
    }

    /** Esta função faz update no estoque para mais e para menos
     * 
     */
    public boolean baixaEstoque(Produto produto, int quantidade){
        try {
            //resgata o produto no banco
            produto = (Produto) pbo.findById(produto.getId());
            pbo.update(produto);
        } catch (Exception ex) {
            Log.log(ProdutoController.class.getName(), ex);
        }
        return false;
    }
    
}//fim classe

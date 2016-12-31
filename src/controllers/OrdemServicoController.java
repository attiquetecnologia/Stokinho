/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.OrdemServico;
import models.Produto;
import models.enuns.Comparadores;
import org.eclipse.persistence.internal.core.helper.CoreClassConstants;
import persistence.OrdemServicoBO;
import persistence.ProdutoBO;
import utils.Log;

/**
 *
 * @author Rodrigo
 */
public class OrdemServicoController /*implements DefaultController*/ {

    private OrdemServico ordemServico;
    private OrdemServicoBO cbo;
    //Esta variável é um vetor auxilioo
    private List<Object[]> produtosOrdem;
    
    public OrdemServicoController(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
        produtosOrdem = new ArrayList<>();
        cbo = new OrdemServicoBO();
        
    }

    public OrdemServicoController() {
        ordemServico = new OrdemServico();
        cbo = new OrdemServicoBO();
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public boolean setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
        return true;
    }
    
   
//    @Override
    public boolean setObject(Object o) {
        this.ordemServico = (OrdemServico) o;
        return true;
    }

//    @Override
    public Object[][] objectToArray(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public boolean save() {
        
        try {
            boolean saved = cbo.save(ordemServico);
            /**
             * Se o status da ordem for orçamento não altera em nada, assim como cancelamento
             */
            if (saved){
                return baixaEstoque();
            }//fim se salvo
        } catch (Exception ex) {
            Log.log(OrdemServicoController.class.getName(), ex);
        }
        return false;
        
    }//fim save
//
//    @Override
    public boolean update() {
        try {
            return cbo.update(ordemServico);
            
        } catch (Exception ex){
            Log.log(OrdemServicoController.class.getName(), ex);
        }
        return false;
    }

//    @Override
    public boolean delete() {
        try {
            return cbo.delete(ordemServico);
        } catch (Exception ex){
            Log.log(OrdemServicoController.class.getName(), ex);
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
    public List<OrdemServico> findByParameters(String param, Object value, Comparadores comp) {
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
                    consulta = em.createNamedQuery("OrdemServico.findByNome", OrdemServico.class).setParameter("param", "%"+value+"%");
                    break;
                case "id": //pesquisa por cep
                    consulta = em.createNamedQuery("OrdemServico.findById", OrdemServico.class).setParameter("param", (int)value);
                    break;
                
                default: 
                    em.createNamedQuery("OrdemServico.findAll");
                    break;
            }//fim switch param
        } catch (Exception ex) {
            Log.log(OrdemServicoBO.class.getName(), ex);
        } 
        return consulta.getResultList();
    }//fim find

    //@Override
    public OrdemServico findParameters(String param, Object value, Comparadores comp) {
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
                            consulta = em.createNamedQuery("OrdemServico.findByNome", OrdemServico.class).setParameter("param", "%"+value+"%");
                            break;
                        case IGUAL:
                            consulta = em.createNamedQuery("OrdemServico.findByNome", OrdemServico.class).setParameter("param", value);
                            break;    
                        default: break;
                    }
                    break;
                case "id": //pesquisa por cep
                    consulta = em.createNamedQuery("OrdemServico.findById", OrdemServico.class).setParameter("param", (int)value);
                    break;
                case "ativo": //pesquisa por bairro
                    consulta = em.createNamedQuery("OrdemServico.findByStatus", OrdemServico.class).setParameter("param", "%"+value+"%");
                    break;
                case "cidade": //pesquisa por cidade
                    consulta = em.createNamedQuery("OrdemServico.findByCidade", OrdemServico.class).setParameter("cidade", "%"+value+"%");
                    break;
                default: 
                    em.createNamedQuery("OrdemServico.findAll");
                    break;
            }//fim switch param
        } catch (Exception ex) {
            Log.log(OrdemServicoBO.class.getName(), ex);
        } 
        return (OrdemServico) consulta.getSingleResult();
    }

    //@Override
    public OrdemServico findById(Object object) {
        try {
            return (OrdemServico) cbo.findById(object);
        } catch (Exception ex) {
            Log.log(OrdemServicoBO.class.getName(), ex);
        }
        return null;
    }

    //@Override
    public List<OrdemServico> findByParameters(String query, Object... params) {
        /*
        1.Fazer cast do valor do textFile
        2.usar comparador selecionado
        3.Consultar pelo campo escolhido acima
        */
        EntityManager em = null;
        Query consulta = null;
        List<OrdemServico> lista = new ArrayList<>();
        try {
            em = cbo.getEntityManager();
            consulta = em.createQuery(query, OrdemServico.class);
            for (int i=0; i< params.length;i++) {
                
                consulta.setParameter(i+1, params[i]);
            }
            lista = consulta.getResultList();
        } catch (Exception ex) {
            Log.log(OrdemServicoBO.class.getName(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }//fim finally
        return lista;
    }
    
    /** Esta função faz update no estoque para mais e para menos
     * 
     */
    private boolean baixaEstoque(){
        try {
            return new ProdutoBO().baixaEstoque(produtosOrdem);
        } catch (Exception ex) {
            Log.log(ProdutoController.class.getName(), ex);
        }
        return false;
    }

    public void setProdutosOrdem(List<Object[]> produtosOrdem) {
        this.produtosOrdem = produtosOrdem;
    }
}//fim classe
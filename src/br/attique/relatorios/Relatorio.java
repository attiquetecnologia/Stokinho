/*
 * DESENVOLVEDOR: Rodrigo Attique Santana rodrigoatique@gmail.com
 * Todos os Direitos Reservados
 * DOS DADOS
 * O sistema é desenvolvido para a autoescola Impacto, sendo seus, 
 * os dados contidos no mesmo bem como a base de dados e sua estrutura, 
 * é de sua responsabilidade zelar pela integridade dos dados fazendo 
 * bom uso do sistema com backups, treinamento providos pelo DESENVOLVEDOR, 
 * atualizações entre outros.
 * DA DISTRIBUIÇÃO
 * Como se trata de um sistema customizado para contratado, o CONTRATADO 
 * recebe uma cópia da estrutura da base de dados e respectivas senhas de 
 * acesso, caso o DESENVOLVEDOR atual não seja mais capaz de continuar o 
 * desenvolvimento da versão customizada do sistema.
 * O framework, algoritmos, métodos são de autoria do DESENVOLVEDOR, não 
 * sendo permitida a distribuição do código sem o consentimento do mesmo a terceiros.
 * DOS ACESSOS
 * O sistema tem acesso simultâneo ilimitado à base de dados, podendo ser feitas 
 * quantas conexões forem necessárias, respeitando apenas os limites de conexão do 
 * servidor onde será instalada a base de dados.
 */
package br.attique.relatorios;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import models.Configuracoes;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import utils.DefaultInternalFrame;
import models.enuns.InformacoesSistema;
import utils.Log;

/**
 * Relatorio classe.
 * Esta classe é usada para imprimir relatorios. Use para imprimir relatorio dentetro
 * de um desktop pane ou em um dialog a parte.
 * @author Rodrigo
 */
public class Relatorio implements Runnable {
    /**
     * Stream contendo o relatorio.
     */
    private InputStream stream;
    /**
    * Caminho absoluto do arquivo de relatório com nome passado na função
    */
    public static final String absutePath = "/br/attique/relatorios/jasper/";
    
    /**
     * Nome do relatório
     */
    private String relName;
    
    /**
     * Parametros internos
     */
    private HashMap<String,Object> params;
    /**
     * Conexão com banco de dados
     */
    private Connection conexao;
    /**
     * Exite on close do jasper viewr
     */
    private boolean isExiteOnClose;
    /**
     * JRBCollection para receber de arrays do sistema
     */
    private JRBeanCollectionDataSource lista;
    /**
     * Exportar para um arquivo?
     */
    private boolean isExportToFile;
    
    /**
     * Tipo de arquivo
     */
    private boolean isPdfFile;

    public Relatorio(String relName, HashMap<String, Object> params, Connection conexao, boolean isExiteOnClose, JRBeanCollectionDataSource lista, boolean isExportToFile, boolean isPdfFile) {
        this.relName = relName;
        this.params = params;
        this.conexao = conexao;
        this.isExiteOnClose = isExiteOnClose;
        this.lista = lista;
        this.isExportToFile = isExportToFile;
        this.isPdfFile = isPdfFile;
    }

    public Relatorio(String relName, HashMap<String, Object> params, Connection conexao) {
        this.relName = relName;
        this.params = params;
        this.conexao = conexao;
        this.isExiteOnClose = false;
        this.lista = null;
        this.isExportToFile = false;
        this.isPdfFile = false;
    }
    
    public Relatorio() {}
    
    /**
     * Exibe um relatório no jasperReport
     * @param relName String nome do relatório
     * @param params jasper parameters
     * @param conexao conexão usada
     * @param isExitOnClose exiteon close
     */
    public void exibir(String relName,HashMap<String,Object> params,Connection conexao,boolean isExitOnClose){
        
        /**
         * Caminho absoluto do arquivo de relatório com nome passado na função
         */
        String path = absutePath+relName;
        System.out.println(path);
        stream = getClass().getResourceAsStream(path);
        try {
            JasperPrint fill = JasperFillManager.fillReport(stream, params, conexao);
            JasperExportManager.exportReportToPdfFile(fill, InformacoesSistema.PATH_SISTEMA+"\\impressao\\comprovante.pdf");
            JasperViewer jrView = new JasperViewer(fill,false);
            jrView.setVisible(true);
        } catch (JRException ex) {
            Log.log(Relatorio.class.getName(), ex);
        }
    }//fim exibir
    
    /**
     * Exibe um relatório no jasperReport
     * @param relName String nome do relatório
     * @param params jasper parameters
     */
    public void exibir(String relName,HashMap<String,Object> params){
        
        /**
         * Caminho absoluto do arquivo de relatório com nome passado na função
         */
        String path = absutePath+relName;
        System.out.println(path);
        
        stream = getClass().getResourceAsStream(path);
        try {
            
            JasperPrint fill = JasperFillManager.fillReport(stream, params);
            JasperExportManager.exportReportToPdfFile(fill, InformacoesSistema.PATH_SISTEMA+"\\impressao\\comprovante.pdf");
            JasperViewer jrView = new JasperViewer(fill,false);
            jrView.setVisible(true);
        } catch (JRException ex) {
            Log.log(Relatorio.class.getName(), ex);
        }
    }//fim exibir
    
    /**
     * Exibir Relatório em JDialog passando conexão.
     * Esta função exibe um relatório jasper usando uma conexão de banco de dados.
     * Use para relatórios que possuem querys internas.
     * @param relName String nome do relatório
     * @param params HashMap com parâmetros do relatorio
     * @param conexao Conexao conexão usada para acessar os dados.
     */
    public void exibir(String relName,HashMap<String,Object> params,Connection conexao){
        /**
         * Dialog contendo o relatório
         */
        JDialog viewer;
        /**
         * Caminho absoluto do arquivo de relatório com nome passado na função
         */
        String path = "/br/attique/relatorios/jasper/"+relName;
        
        // INICIANDO O DIALOG
        viewer = new JDialog(new javax.swing.JFrame(), "Visualização de Resultado",true);
        viewer.setLocationRelativeTo(null);
        
        stream = getClass().getResourceAsStream(path);
        try {
            JasperPrint fill = JasperFillManager.fillReport(stream, params, conexao);
            JasperViewer jrView = new JasperViewer(fill,false);
            viewer.setBounds(0, 0, jrView.getWidth(), jrView.getHeight());
            viewer.getContentPane().add(jrView.getContentPane());
            viewer.setVisible(true);
        } catch (JRException ex) {
            Log.log(Relatorio.class.getName(), ex);
        }
    }//fim exibir
    
    /**
     * 
     */
    @Deprecated
    public void exibir2(String relName,HashMap<String,Object> params,Connection conexao){
        JDialog viewer = new JDialog(new javax.swing.JFrame(),"Visualizar",true);
        viewer.setSize(800,800);
        viewer.setLocationRelativeTo(null);
        
        String path = "/br/attique/relatorios/jasper/"+relName;
        stream = getClass().getResourceAsStream(path);
        try {
            JasperPrint fill = JasperFillManager.fillReport(stream, params, conexao);
            JasperViewer jrView = new JasperViewer(fill,false);
            viewer.getContentPane().add(jrView.getContentPane());
            viewer.setVisible(true);
        } catch (JRException ex) {
            Log.log(Relatorio.class.getName(), ex);
        }
    }
    
    /**
     * Exibir Relatório em JDialog passando array de dados.
     * Esta função exibe um relatório jasper usando um array contendo as variáveis.
     * Use quando não quiser usar querys do relatório.
     * NOTA: os valores passados na JRBeanCollection devem conicindir com as variáveis do relatorio.
     * @param relName String nome do relatório
     * @param params HashMap com parâmetros do relatorio
     * @param lista  JRBeanCollection vetor contendo os dados.
     */
    public void exibir(String relName,HashMap<String,Object> params, JRBeanCollectionDataSource lista){
        JDialog viewer = new JDialog(new javax.swing.JFrame(),"Visualização de Resultado",true);
        viewer.setSize(800,600);
        viewer.setLocationRelativeTo(null);
        
        String path = "/br/attique/relatorios/jasper/"+relName;
        stream = getClass().getResourceAsStream(path);
        try {
            JasperPrint fill = JasperFillManager.fillReport(stream, params, lista);
            JasperViewer jw = new JasperViewer(fill);
            JasperViewer jrView = new JasperViewer(fill,false);
            viewer.getContentPane().add(jrView.getContentPane());
            viewer.setVisible(true);
   
        } catch (JRException ex) {
            Log.log(Relatorio.class.getName(), ex);
        }
    }
    
    /**
     * Exibe um relatório dentro de um JInternalFrame
     * @param titulo
     * @param relName
     * @param params
     * @param lista
     * @return JInternalFrame
     * 
     */
    @Deprecated
    public JInternalFrame exibirInternalFrame(String titulo,String relName,HashMap<String,Object> params, JRBeanCollectionDataSource lista){
        DefaultInternalFrame frame = new DefaultInternalFrame();
        frame.setTitle(titulo);
        String path = "/br/attique/relatorios/jasper/"+relName;
        stream = getClass().getResourceAsStream(path);
        try {
            JasperPrint fill = JasperFillManager.fillReport(stream, params, lista);
            JasperViewer jw = new JasperViewer(fill);
            JasperViewer jrView = new JasperViewer(fill,false);
            frame.getContentPane().add(jrView.getContentPane());
            frame.setVisible(true);
            frame.toFront();
        } catch (JRException ex) {
            Log.log(Relatorio.class.getName(), ex);
        }
        return frame;
    }
    
    /**
     * Exibe um relatório dentro de um JInternalFrame
     * @param titulo
     * @param relName
     * @param params
     * @param lista
     * @return JInternalFrame
     */
    @Deprecated
    public JInternalFrame exibirInternalFrame(String titulo, String relName,HashMap<String,Object> params,Connection conexao){
        DefaultInternalFrame frame = new DefaultInternalFrame();
        frame.setTitle(titulo);
        String path = "/br/attique/relatorios/jasper/"+relName;
        stream = getClass().getResourceAsStream(path);
        try {
            JasperPrint fill = JasperFillManager.fillReport(stream, params, conexao);
            JasperViewer jrView = new JasperViewer(fill,false);
            frame.getContentPane().add(jrView.getContentPane());
            frame.setVisible(true);
            frame.toFront();
        } catch (JRException ex) {
            Log.log(Relatorio.class.getName(), ex);
        } 
        return frame;
    }

    /**
     * Esta função exibe o relatório usandos parâmetros setados na instância
     * do mesmo.
     */
    private void exportToFile(){
        JasperPrint jPrint;
        stream = stream = getClass().getResourceAsStream(absutePath+relName);
        Configuracoes cfg = new Configuracoes();
        try {
            if (conexao == null)
                jPrint = JasperFillManager.fillReport(stream, params);
            else
                jPrint = JasperFillManager.fillReport(stream, params, conexao);
            if (lista != null)
                jPrint = JasperFillManager.fillReport(stream, params, lista);
            
            if (isPdfFile){
                JasperExportManager.exportReportToPdfFile(jPrint, cfg.getPathArquivosImpressao()+"\\"+relName.split(".")[0]);
            }else JasperExportManager.exportReportToHtmlFile(jPrint, cfg.getPathArquivosImpressao()+"\\"+relName.split(".")[0]);
        } catch (JRException ex) {
            Log.log(Relatorio.class.getName(), ex);
        }
                 
    }//fim exportToFile
    
    /**
     * Esta função exibe o relatório usandos parâmetros setados na instância
     * do mesmo.
     */
    private void exibir(){
        JasperPrint jPrint;
        stream = stream = getClass().getResourceAsStream(absutePath+relName);
        
        try {
            if (conexao != null)
                jPrint = JasperFillManager.fillReport(stream, params, conexao);
                
            else if (lista != null)
                jPrint = JasperFillManager.fillReport(stream, params, lista);
            else
                jPrint = JasperFillManager.fillReport(stream, params);
            
            
            JasperViewer.viewReport(jPrint, isExiteOnClose);
        } catch (JRException ex) {
            Log.log(Relatorio.class.getName(), ex);
        }
                 
    }//fim exibir
    
    /**
     * Método run da classe runnable.
     * Esta implementação permite uso de threads para melhorar o desempenho quando
     * gerar relatórios independente do tamanho.
     * @since 1.6.7 22/12/2015
     */
    @Override
    public void run() {
        if (isExportToFile) 
            exportToFile();
        else 
            exibir();
    }

    public void setRelName(String relName) {
        this.relName = relName;
    }

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
    }

    public void setConexao(Connection conexao) {
        this.conexao = conexao;
    }

    public void setIsExiteOnClose(boolean isExiteOnClose) {
        this.isExiteOnClose = isExiteOnClose;
    }

    public JRBeanCollectionDataSource getLista() {
        return lista;
    }

    public void setLista(JRBeanCollectionDataSource lista) {
        this.lista = lista;
    }

    public boolean isIsExportToFile() {
        return isExportToFile;
    }

    public void setIsExportToFile(boolean isExportToFile) {
        this.isExportToFile = isExportToFile;
    }

    public boolean isIsPdfFile() {
        return isPdfFile;
    }

    public void setIsPdfFile(boolean isPdfFile) {
        this.isPdfFile = isPdfFile;
    }
    
    
}//fim classe

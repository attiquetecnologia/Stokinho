/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.attique.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



/**
 * @author RODIROG ATTIQUE SANTANA
 * rodrigoatique@gmail.com
 * @version 1.1 05.07.2015
 * 1.0 02.02.2015
 * 
 */
public class Conexao {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://" + TextFile.getINSTANCE().getHost()
            + ":" + TextFile.getINSTANCE().getPort() + "/" + TextFile.getINSTANCE().getDatabase();
//    private static final String URL = "jdbc:postgresql://192.168.0.254:5432/auto_escola";
//    private static final String URL = "jdbc:postgresql://lenovo-pc:5432/auto";
//    private static final String URL = "jdbc:postgresql://lenovo-pc:5432/auto_escola";
    private static final String USER = "impacto";
    private static final String SENHA = "impacto";
    
    private String url;

    /**
     *
     * @return
     */
    public static Connection getConexao() {
        Connection conexao = null;
        String url = "jdbc:postgresql://" + TextFile.getINSTANCE().getHost()
            + ":" + TextFile.getINSTANCE().getPort() + "/" + TextFile.getINSTANCE().getDatabase();
        
        try {
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(url, USER, SENHA);
                        
        } catch(ClassNotFoundException ex){
            utils.Log.log(Conexao.class.getName(), ex);
            
        } catch(SQLException ex){
            utils.Log.log(Conexao.class.getName(), ex);
        }
        
        return conexao;
    }
    
    /**
     * Foi adicionado um método para JPA, assim o sistema poderá trabalhar em paralelo com ambos
     * os sistemas.
     * @return 
     * @sice 1.6.19
     */
    public static EntityManagerFactory getEntityFactory(){
        Map properties = new HashMap();   
        
        properties.put("javax.persistence.jdbc.user", USER);
        properties.put("javax.persistence.jdbc.url", URL);
        properties.put("javax.persistence.jdbc.password", SENHA);
        properties.put("javax.persistence.jdbc.driver", DRIVER);
        
        return Persistence.createEntityManagerFactory("AutoEscola1-6PU",properties);
    }
}//fim classe

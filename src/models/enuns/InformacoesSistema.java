/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.enuns;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * INFORMAÇÕES DO SISTEMA.
 * Esta classe mostra informações como versão, ip e pastas padrões do sistema.
 * @author Rodrigo
 */
public class InformacoesSistema {
    /** Sistema operacional */
    private String osName;
    /** Versão do sistema operacional */
    private String osVersion;
    /** Endereço MAC da placa de rede*/
    private String macAddress;
    /** Endereço IP */
    private String ipAddress;
    /** Usuário Logado */
    private String userName;
    /** Host */
    private String hostName;

    /** Instancia a propria classe - Singleton */
    private static final InformacoesSistema informacoes = new InformacoesSistema();
    
    /**
     * Path absoluto do sistema
     */
    public static final String PATH_SISTEMA = System.getProperty("user.dir");
    /**
     *
     */
    private InformacoesSistema() {
        osName = System.getProperty("os.name");
        osVersion = System.getProperty("os.version");
        userName = System.getProperty("user.name");
        macAddress = System.getProperty("mac.address");
        try {
            ipAddress = InetAddress.getLocalHost().getHostAddress();
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            Logger.getLogger(InformacoesSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getOsName() {
        return osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getUserName() {
        return userName;
    }

    public String getHostName() {
        return hostName;
    }

    public static InformacoesSistema getInstance(){
        return informacoes;
    }

    public static String getPATH_SISTEMA() {
        return PATH_SISTEMA;
    }
    
}//fim classe

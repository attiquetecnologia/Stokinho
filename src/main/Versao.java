/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package main;

/**
 * Classe versão.
 * A classe versão é usada para controlar as versões deste software de forma a
 * permitir sua manutenção
 * @author Rodrigo
 */
public class Versao {
    public static final int MAJOR_RELEASE = 1;
    public static final int MINOR_RELEASE = 0;
    public static final int COMPLEMENT_RELEASE = 0;
    public static final String RELEASE_DATE = "04/08/2016";
    public static final String RELEASE_NOTES = "";
    
    public static String getVersion(){
        return MAJOR_RELEASE+"."+MINOR_RELEASE+"."+COMPLEMENT_RELEASE;
    }
}//fim classe
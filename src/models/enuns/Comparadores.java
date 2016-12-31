/*
 * Este software foi desenvolvido e criado por Rodrigo Attique Santana,
 * todos os algoritimos presentes aqui são de altoria do desenvolvedor, não sendo permitido
 * cópia ou distribuição sem o consentimento do mesmo.
 * É proibido vender, modificar, distribuir sem autorização.
 * copyright Attique Tecnologia.
 */
package models.enuns;

/**
 * Esta classe na verdade representa os comparadores lógicos conhecidos
 * @author Rodrigo
 */
public enum Comparadores {
    IGUAL(0,"=")
    ,CONTEM(1,"like")
    ,MAIOR(2,">")
    ,MENOR(3,"<")
    ,DIFERENTE(4,"!=")
    ,MAIOR_IGUAL(5,">=")
    ,MENOR_IGUAL(6,"<=")
    ,ENTRE(7,"BETWEEN")
    ;

    private int idx;
    private String simbol;
    
    Comparadores(int idx,String simbol){
        this.idx = idx;
        this.simbol = simbol;
    }
    /**
     * Retorna um comparador lógico
     * @return 
     */
    public String getSimbol(){
        return simbol;
    }
    
}//fim classe

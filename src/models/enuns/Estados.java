/*7
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.enuns;

/**
 *
 * @author master
 */
public enum Estados {
    
    /**
     *
     */
    AC("Acre",0)
    ,

    /**
     *
     */
    AL("Alagoas",1)
    ,

    /**
     *
     */
    AP("Amapá",2)
    ,

    /**
     *
     */
    AM("Acre",3)
    ,

    /**
     *
     */
    BA("Bahia",4)
    ,

    /**
     *
     */
    CE("Ceará",5)
    ,

    /**
     *
     */
    DF("Distrito Federal",6)
    ,

    /**
     *
     */
    ES("Espírito Santo",7)
    ,

    /**
     *
     */
    GO("Goiás",8)
    ,

    /**
     *
     */
    MA("Maranhão",9)
    ,

    /**
     *
     */
    MT("Mato Grosso",10)
    ,

    /**
     *
     */
    MS("Mato Grosso do Sul",11)
    ,

    /**
     *
     */
    MG("Minas Gerais",12)
    ,

    /**
     *
     */
    PA("Pará",14)
    ,

    /**
     *
     */
    PB("Paraiba",15)
    ,

    /**
     *
     */
    PR("Paraná",16)
    ,

    /**
     *
     */
    PE("Pernambuco",17)
    ,

    /**
     *
     */
    PI("Piauí",18)
    ,

    /**
     *
     */
    RJ("Rio de Janeiro",19)
    ,

    /**
     *
     */
    RN("Rio Grande do Norte",20)
    ,

    /**
     *
     */
    RS("Rio Grande do Sul",21)
    ,

    /**
     *
     */
    RO("Rondônia",22)
    ,

    /**
     *
     */
    RR("Roraima",23)
    ,

    /**
     *
     */
    SC("Santa Catarina",24)
    ,

    /**
     *
     */
    SP("São Paulo",25)
    ,

    /**
     *
     */
    SE("Sergipe",26)
    ,

    /**
     *
     */
    TO("Tocantins",27);

    private String nome;
    private int idx;

    private Estados(String nome, int idx) {
        this.nome = nome;
        this.idx = idx;
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public int getIdx() {
        return idx;
    }

    /**
     *
     * @param idx
     */
    public void setIdx(int idx) {
        this.idx = idx;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.enuns;

/**
 *
 * @author master
 */
public enum EstadoCivil {
    
    /**
     *
     */
    SOLTEIRO_A("Solteiro(a)",0)
    ,

    /**
     *
     */
    CASADO_A("Casado(a)",1)
    ,

    /**
     *
     */
    VIUVO_A("Viúvo(a)",2)
    ,

    /**
     *
     */
    AMASIADO_A("Amasiado(a)",3)
    ,

    /**
     *
     */
    DIVORCIADO_A("Divorciado(a)",4)
    ;
    private String nome;
    private int idx;

    private EstadoCivil(String nome, int idx) {
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
}//fim enum

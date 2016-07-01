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

/**
 * Esta classe é uma base para todos os outros relatórios
 * @author master
 */


public class BaseRelatorio {
    private static final String pathDefault = "br/attique/relatorios/jasper/"; //string com o caminho default
    
    /**
     *
     * @param nomeRelatorio
     */
    public final void ver(String nomeRelatorio){
        InputStream stream = getClass().getResourceAsStream(pathDefault+nomeRelatorio);
    
    }
}//fim classe

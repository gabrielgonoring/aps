package br.com.aps.unip.util;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * Durante a execu��o do projeto � poss�vel trabalhar com v�rias sess�es com o banco de dados. Na JPA a sess�o com o banco � representa pelo componente EntityManager.
 * 
 * <p>Essa classe � responsavel por disponibilizar o(s) componente(s) <code>EntityManager</code>. Utilizamos o <code>EntityManagerFactory</code> fornecedor (f�brica) de sess�es com o banco de dados.</p>
 * 
 * <p>Caso n�o consiga carregar a fabrica de sess�es, problemas com a conex�o com banco ou n�o encontrou configurador, a execu��o da aplica��o � interrompida (lan�a <code>ExceptionInInitializerError</code>).</p>
 * 
 * @see persistence.xml
 * 
 * @author YaW Tecnologia
 */
public class JPAUtil {
	
	//private static Logger log = Logger.getLogger(JPAUtil.class);

	/** Unidade de persistencia definida no arquivo <code>persistence.xml</code> */
	private static final String PERSISTENCE_UNIT_NAME = "aps";
	
	private static EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Throwable ex) {
        	//log.error("N�o conseguiu carregar a EntityManagerFactory: "+ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    /**
     * @return Cria e retorna um componente <code>EntityManager</code>.
     * @throws Lan�a <code>RuntimeException</code> se <code>EntityManagerFactory</code> estiver fechada. 
     */
    public static EntityManager createEntityManager() {
    	if (!emf.isOpen())
    		throw new RuntimeException("EntityManagerFactory est� fechada!");
    	return emf.createEntityManager();
    }
    
    /**
     * Fecha o <code>EntityManagerFactory</code>.
     */
    public static void closeEntityManagerFactory() {
    	if (emf.isOpen()) {
    		emf.close();
    	}
    }

}






import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;



public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {

	        Hashtable env = new Hashtable();

	        //env.put(Context.INITIAL_CONTEXT_FACTORY, "oracle.j2ee.rmi.RMIInitialContextFactory");
	        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
	        
	        //env.put(Context.INITIAL_CONTEXT_FACTORY, "com.evermind.server.rmi.RMIInitialContextFactory");
	        env.put(Context.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");
            //env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
	        env.put("jboss.naming.client.ejb.context", true);
	        env.put(Context.SECURITY_PRINCIPAL, "ejb"); //PropriedadesEJBGED.LOGIN);
	        env.put(Context.SECURITY_CREDENTIALS, "teste"); //PropriedadesEJBGED.SENHA);
	        env.put(Context.PROVIDER_URL, "remote://localhost:4447");
	        //env.put(Context.PROVIDER_URL, PropriedadesEJBGED.URL);

	        final Context context = new InitialContext(env);
 
            // Lookup the Greeter bean using the ejb: namespace syntax which is explained here https://docs.jboss.org/author/display/AS71/EJB+invocations+from+a+remote+client+using+JNDI
            final Greeter bean = (Greeter) context.lookup("ejb:global/myapp/myejb/GreeterBean!org.myapp.ejb.Greeter");
 
            // invoke on the bean
            final String greeting = bean.greet("Tom");
 
            System.out.println("Received greeting: " + greeting);
 
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

}
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;
import javax.servlet.http.HttpServletRequest;

import br.com.josehenriqueventura.entity.ModulePermission;
import br.com.josehenriqueventura.entity.User;
import br.com.josehenriqueventura.repository.UserRepository;

/**
 * 
 * @author   - José Henrique Ventura
 * @projet   - Pickectlink SSO
 */
@Named(value = "permission")
@SessionScoped
public class MbPermission implements Serializable {
	private static final long serialVersionUID = -9114962437856464065L;

	private String name = "Informações sobre usuario";

	@Inject UserRepository ur;
	
	private User user;
	
	public MbPermission() {
	}
	
	/**
	 * Ao iniciar o managerBean, pega o usuario corrente que está logado.
	 * 
	 * @throws NamingException
	 * @throws PolicyContextException
	 */
	@PostConstruct
	public void init() throws NamingException, PolicyContextException{
		//TODO
		HttpServletRequest request =
                (HttpServletRequest) PolicyContext.getContext("javax.servlet.http.HttpServletRequest");
		name = request.getUserPrincipal().getName();
		
		user = ur.getUserAndModulePermission(name);
	}
	
	/**
	 * Verifica se o usuario possui uma determinada permissão de modulo.	  
	 * @param permissionName - Nome da permissão do modulo.
	 * @return true ou false
	 */
	public boolean hasModulePermission(String permissionName ){	
		for(ModulePermission mp : user.getModulePermissions()){
			if(mp.getPermission().equals(permissionName)){
				return true;
			}
		}		
		return false;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

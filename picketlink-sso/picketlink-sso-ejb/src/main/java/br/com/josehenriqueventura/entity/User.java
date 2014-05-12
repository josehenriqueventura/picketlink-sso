package br.com.josehenriqueventura.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author   - José Henrique Ventura
 * @projet   - Pickectlink SSO
 */
@Entity
@Table(name="users")
@NamedQueries({
	 @NamedQuery(name="BUSCA",query="select u from User u where u.name = :name ")
	 })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_USERS")
	@SequenceGenerator(name = "SEQ_USERS", sequenceName = "SEQ_USERS")
	@Column(name="id")
	private Long id;
	
	private String name;
	
	private String password;
	
	private String email;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "USERS_PERMISSIONS", joinColumns = @JoinColumn(name = "users_id"), inverseJoinColumns = @JoinColumn(name = "module_permissions_id"))
	private List<ModulePermission> modulePermissions;
	
	public User() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ModulePermission> getModulePermissions() {
		return modulePermissions;
	}

	public void setModulePermissions(List<ModulePermission> modulePermissions) {
		this.modulePermissions = modulePermissions;
	}
}

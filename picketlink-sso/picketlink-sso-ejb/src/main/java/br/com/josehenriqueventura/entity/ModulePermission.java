package br.com.josehenriqueventura.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author   - José Henrique Ventura
 * @projet   - Pickectlink SSO
 */
@Entity
@Table(name="MODULE_PERMISSION")
public class ModulePermission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_USERS_PERMISSIONS")
	@SequenceGenerator(name = "SEQ_USERS_PERMISSIONS", sequenceName = "SEQ_USERS_PERMISSIONS")
	private Long id;
	
	private String permission;
	
	public ModulePermission() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}

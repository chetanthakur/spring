package net.devmanuals.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Table(name = "users")
@Entity(name = "User")
public class User implements Serializable {
	private static final long serialVersionUID = -4930636375444147252L;

	private final static String globalSalt = "c6241e1015499102";
	
	/**
	 * There are two set of different set of password type. For student we are using blowfish encrypted password. For
	 * all other account we will be using a SHA hash. 
	 * 
	 * As for salt, both will be based on the the pre-defined salt within our code
	 * to encrypt/hash the password.
	 */
	public static enum PasswordType {
		HASH, ENCRYPTED;
	}
	
	public static enum UserStatus {
		ACTIVE,
		DISABLED,
		DELETED,
		EXPIRED,
		UNCONFIRMED_EMAIL;
	}
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue
	private long id;
	
	@NotNull
	@Size(max = 64, min = 6)
	@Column(name = "username")
	private String username;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	@Column(name = "password")
	private String passwordHash;
	
	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "password_type")
	@Enumerated(value = EnumType.ORDINAL)
	private PasswordType passwordType;
	
	@Size(max = 100, min = 1)
	@Column(name = "first_name")
	private String firstname;
	
	@Size(max = 100)
	@Column(name = "last_name")
	private String lastname;
	
	@Column(name = "email")
	private String email = "";
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login")
	private Date lastLogin;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_at")
	private Date modifiedAt;
	
	
	
	@Column(name = "salt")
	private String salt = UUID.randomUUID().toString();
	
	
	
	/**
	 * Helper method to verify if the password is the correct one
	 * 
	 * @param plainPassword
	 * @return
	 */
	public final boolean verifyPassword(String plainPassword) {
		if (this.passwordType == PasswordType.HASH) {
			StandardPasswordEncoder encoder = new StandardPasswordEncoder(this.salt);
			return encoder.matches(plainPassword, this.passwordHash);
		}
		else if (this.passwordType == PasswordType.ENCRYPTED) {
			TextEncryptor encoder = Encryptors.queryableText(this.salt, globalSalt);
			String decryptedPassword = encoder.decrypt(this.passwordHash);
			return decryptedPassword.equals(plainPassword);
		}
		else {
			throw new RuntimeException("Unknown password type for user: " + this.id);
		}
	}
	
	public PasswordType getPasswordType() {
		return passwordType;
	}

	public void setPasswordType(PasswordType passwordType) {
		this.passwordType = passwordType;
	}

	/**
	 * Retrieve the password for a student. If the user is not a student, this method will
	 * throws a runtime exception as those are stored as one-way hash.
	 * 
	 * @return
	 */
	public final String getDecryptedPassword() {
		if (this.passwordType == PasswordType.HASH) {
			throw new RuntimeException("Unable to decrypt a hashed password, user id: " + this.id);
		}
		else {
			TextEncryptor encoder = Encryptors.queryableText(this.salt, globalSalt);
			String decryptedPassword = encoder.decrypt(this.passwordHash);
			return decryptedPassword;
		}
	}
	
	/**
	 * Setting for password and bash on password type it will use a hash or an two-way encryption schema
	 * to store the password.
	 * 
	 * @param plainPassword
	 */
	public final void setPassword(String plainPassword) {
		if (this.passwordType == PasswordType.HASH) {
			StandardPasswordEncoder encoder = new StandardPasswordEncoder(this.salt);
			this.passwordHash = encoder.encode(plainPassword);
		}
		else if (this.passwordType == PasswordType.ENCRYPTED) {
			TextEncryptor encoder = Encryptors.queryableText(this.salt, globalSalt);
			this.passwordHash = encoder.encrypt(plainPassword);
		}
		else {
			throw new RuntimeException("Unknown password type for user: " + this.id);
		}
	}
}

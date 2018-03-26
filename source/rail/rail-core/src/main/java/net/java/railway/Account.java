package net.java.railway;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import net.java.railway.Constants.AccountRole;
import net.java.railway.Constants.AccountStatus;

@Entity
@Table(name = "ACC")
@NamedQueries({
		@NamedQuery(name = "findAccountByUserNameAndPassword", query = "SELECT acc FROM Account acc WHERE"
				+ " acc.userName=:userName AND acc.password=:password AND acc.status=:status"),
		@NamedQuery(name = "findAccountByMobileNumber", query = "SELECT acc FROM Account acc WHERE"
				+ " acc.mobileNumber=:mobileNumber AND acc.status=:status"),
		@NamedQuery(name = "findAllAccounts", query = "SELECT acc FROM Account acc WHERE acc.status=:status"),
		@NamedQuery(name = "findAccountByUser", query = "SELECT acc FROM Account acc WHERE"
				+ " acc.userName=:userName AND acc.status=:status") })
@Inheritance(strategy = InheritanceType.JOINED)
@TableGenerator(name = "ENT_ID_GEN", table = "ENT_ID_GEN", pkColumnName = "ENT_NAM", pkColumnValue = "ACC", valueColumnName = "CURR_ID", initialValue = 1000, allocationSize = 1)
public class Account {

	@Id
	@GeneratedValue(generator = "ENT_ID_GEN", strategy = GenerationType.TABLE)
	private Long id;

	@Column(name = "USR_NAM", unique = true)
	private String userName;

	@Column(name = "PASSWD")
	private String password;

	@Column(name = "MOB", unique = true)
	private String mobileNumber;

	@Column(name = "BAL", precision = 2)
	private Double balance;

	@Column(name = "STATUS")
	private AccountStatus status;

	@Column(name = "ROLE")
	private AccountRole role;

	@OneToMany(mappedBy = "account")
	private Set<SMS> messages;

	@OneToMany(mappedBy = "account")
	private Set<Transaction> transactions;

	@Column(name = "CRTD_TIME")
	private Date createdTime;

	@Column(name = "UPDT_TIME")
	private Date modifiedTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Set<SMS> getMessages() {
		return messages;
	}

	public void setMessages(Set<SMS> messages) {
		this.messages = messages;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public AccountRole getRole() {
		return role;
	}

	public void setRole(AccountRole role) {
		this.role = role;
	}

}

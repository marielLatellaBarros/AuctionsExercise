package be.pxl.auctions.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "users")
@NamedQueries({
		@NamedQuery(name = "findUserByEmail", query = "SELECT u FROM User u WHERE u.email=:email"),
		@NamedQuery(name = "findAllUsers", query = "SELECT u FROM User u") })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String email;
	private LocalDate dateOfBirth;

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getAge() {
		return (int) ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now());
	}
}

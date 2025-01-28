package com.example.demo.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="springsec")
public class EntityClass {
	 	@Id
	    @GeneratedValue
	    private int id;
	    private String username;
	    private String password;
	    private String hashedpassword;
	    int rollno;

		public String getHashedpassword() {
			return hashedpassword;
		}

		public void setHashedpassword(String hashedpassword) {
			this.hashedpassword = hashedpassword;
		}

		@Override
		public String toString() {
			return "EntityClass [id=" + id + ", username=" + username + ", password=" + password + ", rollno=" + rollno
					+ "]";
		}

		public EntityClass(int id, String username, String password, int rollno, String hashedpassword) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.rollno = rollno;
			this.hashedpassword = hashedpassword;
		}

		public EntityClass() {
			super();
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public int getRollno() {
			return rollno;
		}

		public void setRollno(int rollno) {
			this.rollno = rollno;
		}
	
}

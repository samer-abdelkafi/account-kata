package com.bank.kata.account.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "BANK_OPERATION")
public class Operation {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "AMOUNT")
	private double amount;

	@Column(name = "DATE_OPERATION")
	private Date dateOperation;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ACCOUNT_ID")
	private Account account;

}

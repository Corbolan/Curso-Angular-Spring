package io.github.cursodsousa.todo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter // Gera os Getters
@Setter // Gera os Setters
public class Todo {

	@Id // Define que esta variável é o ID
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Gera valores incrementais para o ID
	private Long id;

	@Column 
	private String Description;

	@Column
	private Boolean Done;

	@Column
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm") // Formata exibição data e hora
	private LocalDateTime CreateDate;

	@Column
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")// Formata exibição data e hora
	private LocalDateTime DoneDate;

	@PrePersist// Método pré-persistência de dados
	public void beforeSave() {// Antes de salvar
		setCreateDate(LocalDateTime.now());// Seta a data e hora agora
	}

}

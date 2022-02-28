package io.github.cursodsousa.todo.rest;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.cursodsousa.todo.model.Todo;
import io.github.cursodsousa.todo.repository.TodoRepository;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin("*")
public class TodoController {

	@Autowired // Injeção de dependência
	private TodoRepository repository;

	@PostMapping
	public Todo save(@RequestBody Todo todo) {
		return repository.save(todo); //Retorna um Save de todo
	}

	@GetMapping
	public List<Todo> getAll(){ // Lista de Todo
		return repository.findAll();// Retorna a listagem de todos
	}
	
	@GetMapping("{id}")
	public Todo getById(@PathVariable Long id) {
		return repository
				.findById(id)// busca pelo id
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)); // Se não achar ele retorna um NOT FOUND
	}
	//http://localhost:8080/api/todos/1
	@DeleteMapping("{id}")// Ele vai deletar pelo mapeamento de ID
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	//http://localhost:8080/api/todos/1/done
	@PatchMapping("{id}/done") // Marca esse método para o verbo Patch (Atualização Parcial do Recurso)
	public Todo markAsDone(@PathVariable Long id) { // Método irá marcar as tarefas terminadas
		return repository.findById(id).map(todo -> {
			todo.setDone(true);
			todo.setDoneDate(LocalDateTime.now());
			repository.save(todo);
			return todo;
		}).orElse(null);
	}
}

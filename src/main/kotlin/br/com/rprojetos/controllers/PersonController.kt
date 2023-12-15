package br.com.rprojetos.controllers

import br.com.rprojetos.model.Person
import br.com.rprojetos.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class PersonController {
    // lateinit irá ser setado posteriormente
    // então não vai ser instanciado aqui ...
    // e o próprio spring vai injetar a instancia do PersonService
    // definindo a injeção da instancia com a annotation @Autowired
    // seria mesma coisa de fazer:
    // var service: PersonService = PersonService()
    @Autowired
    private lateinit var service: PersonService;

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun fingAll(): List<Person>{
        return service.findAll();
    }

    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun fingById(@PathVariable(value = "id") id: Long): Person{
        return service.findById(id);
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody person: Person): Person{
        return service.create(person);
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(@RequestBody person: Person): Person{
        return service.update(person);
    }

    @DeleteMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun delete(@PathVariable(value = "id") id: Long): ResponseEntity<*>{
        service.delete(id)
        return ResponseEntity.noContent().build<Any>();
    }
}
package com.matheusHolanda.workshopMongo.resources;

import com.matheusHolanda.workshopMongo.domain.User;
import com.matheusHolanda.workshopMongo.dto.UserDTO;
import com.matheusHolanda.workshopMongo.entities.Post;
import com.matheusHolanda.workshopMongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll() { // Método que retorna uma resposta HTTP contendo uma lista de usuários
        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).
                collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);   // Retorna a lista como resposta HTTP com status 200 OK
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
        User obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable String id) { // Método que retorna uma resposta HTTP contendo uma lista de usuários
        User obj = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));   // Retorna a lista como resposta
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UserDTO> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {
        User obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

        @RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
        public ResponseEntity<List<Post>> findPosts(@PathVariable String id)
        { // Método que retorna uma lista de osts de um usuário
            User obj = service.findById(id);
            return ResponseEntity.ok().body(obj.getPosts());   // Retorna a lista como resposta
        }
    }


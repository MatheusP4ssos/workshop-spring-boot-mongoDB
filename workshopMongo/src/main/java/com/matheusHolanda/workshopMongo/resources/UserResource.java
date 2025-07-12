package com.matheusHolanda.workshopMongo.resources;

import com.matheusHolanda.workshopMongo.domain.User;
import com.matheusHolanda.workshopMongo.dto.UserDTO;
import com.matheusHolanda.workshopMongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}

package com.matheusHolanda.workshopMongo.resources;

import com.matheusHolanda.workshopMongo.domain.User;
import com.matheusHolanda.workshopMongo.dto.UserDTO;
import com.matheusHolanda.workshopMongo.entities.Post;
import com.matheusHolanda.workshopMongo.resources.util.URL;
import com.matheusHolanda.workshopMongo.services.PostService;
import com.matheusHolanda.workshopMongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostResource {

    @Autowired
    private PostService service;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id) { // Método que retorna uma resposta HTTP contendo uma lista de posts
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);   // Retorna a lista como resposta
    }

    @RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) throws UnsupportedEncodingException {
        text = URL.decodeParam(text);
        List<Post> list = service.findByTitleContaining(text);
        return ResponseEntity.ok().body(list);   // Retorna a lista como resposta
    }
}

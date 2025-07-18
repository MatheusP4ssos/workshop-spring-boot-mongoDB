package com.matheusHolanda.workshopMongo.services;

import com.matheusHolanda.workshopMongo.domain.User;
import com.matheusHolanda.workshopMongo.dto.UserDTO;
import com.matheusHolanda.workshopMongo.entities.Post;
import com.matheusHolanda.workshopMongo.repository.PostRepository;
import com.matheusHolanda.workshopMongo.repository.UserRepository;
import com.matheusHolanda.workshopMongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Post findById(String id) {
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Post não encontrado"));
    }

    public List<Post> findByTitleContaining(String text) {
        return repo.findByTitleContainingIgnoreCase(text);
    }
}
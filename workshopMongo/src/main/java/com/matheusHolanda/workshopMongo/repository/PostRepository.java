package com.matheusHolanda.workshopMongo.repository;

import com.matheusHolanda.workshopMongo.domain.User;
import com.matheusHolanda.workshopMongo.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByTitleContainingIgnoreCase(String text);
}

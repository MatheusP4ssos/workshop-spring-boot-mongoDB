package com.matheusHolanda.workshopMongo.repository;

import com.matheusHolanda.workshopMongo.domain.User;
import com.matheusHolanda.workshopMongo.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}

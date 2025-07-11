package com.matheusHolanda.workshopMongo.repository;

import com.matheusHolanda.workshopMongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}

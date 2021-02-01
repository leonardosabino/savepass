package br.com.savepass.savepass.repository;

import br.com.savepass.savepass.model.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String>, PagingAndSortingRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);
}
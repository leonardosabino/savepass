package br.com.savepass.savepass.repository;

import br.com.savepass.savepass.model.entity.KeyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeyRepository extends MongoRepository<KeyEntity, String>, PagingAndSortingRepository<KeyEntity, String> {
    Optional<KeyEntity> findByLocal(String local);
}
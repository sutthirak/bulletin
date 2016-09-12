package me.sutthirak.bulletin.repository;

import me.sutthirak.bulletin.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TopicRepository extends CrudRepository<Topic, Long> {

    Page<Topic> findAll(Pageable pageable);

    @Modifying
    @Query("update Topic t set t.title = ?1 , t.description = ?2 where id = ?3")
    @Transactional
    int update(String title, String description,Long id);

    
}

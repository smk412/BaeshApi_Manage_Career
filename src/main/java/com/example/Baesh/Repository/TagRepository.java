package com.example.Baesh.Repository;

import com.example.Baesh.Entity.TagE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<TagE, Long> {
    Optional<TagE> findByName(String name);
}

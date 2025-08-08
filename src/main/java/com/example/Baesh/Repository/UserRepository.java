package com.example.Baesh.Repository;

import com.example.Baesh.Entity.UserE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserE, Long> {
    Optional<UserE> findByUserId(String userId);
    Optional<UserE> findByEmail(String email);

    @Query("""
            SELECT DISTINCT u FROM UserE u
            JOIN u.tags t
            WHERE t.name IN :tagNames
            """)
    List<UserE> findUsersByAnyTag(@Param("tagNames") List<String> tagNames);


    /*@Query("""
            SELECT u FROM User u
            JOIN u.tags t
            WHERE t.name IN :tagNames
            GROUP BY u.id
            HAVING COUNT(DISTINCT t.name) = :count
            """)
    List<UserE> findUsersByAllTags(@Param("tagNames") List<String> tagNames, @Param("count") long count);
    */
}

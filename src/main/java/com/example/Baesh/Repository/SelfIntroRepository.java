package com.example.Baesh.Repository;

import com.example.Baesh.Entity.UserRecordManagement.SelfIntroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface SelfIntroRepository extends JpaRepository<SelfIntroEntity,Long> {
    List<SelfIntroEntity> findByUserId(Long userId);
}

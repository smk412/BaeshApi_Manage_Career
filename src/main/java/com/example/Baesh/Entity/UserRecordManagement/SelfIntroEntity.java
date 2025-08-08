package com.example.Baesh.Entity.UserRecordManagement;

import com.example.Baesh.DTO.UserRecordManagement.SelfIntroDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SelfIntroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String subject;
    @Column(length = 3000)
    private String content;
    @Column(length = 1500)
    private String feedback;

    public SelfIntroEntity(){};
    public SelfIntroEntity(Long userId,String subject, String content, String feedback){
        this.userId =userId;
        this.subject = subject;
        this.content = content;
        this.feedback =feedback;
    }
}

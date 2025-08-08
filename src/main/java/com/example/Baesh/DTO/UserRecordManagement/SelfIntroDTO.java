package com.example.Baesh.DTO.UserRecordManagement;


import com.example.Baesh.Entity.UserRecordManagement.SelfIntroEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelfIntroDTO {
    private Long userId;
    private String subject;
    private String content;
    private String feedback;

    public SelfIntroDTO(SelfIntroEntity entity) {
        this.userId = entity.getUserId();
        this.subject = entity.getSubject();
        this.content = entity.getContent();
        this.feedback = entity.getFeedback();
    }
}

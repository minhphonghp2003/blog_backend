package com.phong.blog.Logging.Model;

import com.phong.blog.User.Model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.Instant;

@Data
@Entity
@Table(name = "user_activity")
@NoArgsConstructor
public class UserActivity {
    public UserActivity(String action){
       this.action = action;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    private String action;
    @CreationTimestamp(source = SourceType.DB)
    private Instant createdAt;
}

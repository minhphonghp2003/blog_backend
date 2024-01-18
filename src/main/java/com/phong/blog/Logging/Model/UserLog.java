package com.phong.blog.Logging.Model;

import com.phong.blog.User.Model.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@Entity
@Table(name = "user_log")
public class UserLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String device;
    private String location;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    private String browserName;
    private String os;
    @CreationTimestamp(source = SourceType.DB)
    private Instant createdAt;
}

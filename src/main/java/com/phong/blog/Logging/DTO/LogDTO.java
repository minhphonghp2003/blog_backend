package com.phong.blog.Logging.DTO;

import com.phong.blog.User.Model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.Instant;

@Data
public class LogDTO {
    private String device;
    private String location;
    private String browserName;
    private String os;
    private Instant createdAt;
}

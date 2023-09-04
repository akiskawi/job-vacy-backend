package com.manpower.backendProject.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class Timestamped {

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}

// TODO: poy pragmatika xreiazetai auti i klasi kai poy oxi? Ti mas prosferei?
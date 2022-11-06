package com.isoft.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"createdBy", "createdAt", "updatedAt", "updatedBy"})
public class BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
    @CreatedBy
    @Column(updatable = false)
    protected String createdBy;
    @LastModifiedBy
    @Column(insertable = false)
    protected String updatedBy;
    @CreatedDate
    @Column(updatable = false)
    protected LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    protected LocalDateTime updatedAt;

    public String getLocalDate() {
        return createdAt.format(DateTimeFormatter.ofPattern("MMM dd,uuuu"));
    }
}

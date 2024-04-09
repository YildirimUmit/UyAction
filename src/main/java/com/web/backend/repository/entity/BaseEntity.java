package com.web.backend.repository.entity;

import com.fasterxml.jackson.annotation.*;
import com.web.backend.security.services.*;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.event.spi.*;
import org.springframework.data.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;

import javax.persistence.*;
import java.io.*;
import java.util.*;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {


    @Column(name = "created_at",updatable = false,insertable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt = new Date();


    @Column(name = "created_by",updatable = false,insertable = true)
    private String createdBy;


    @PrePersist // time to run insert
//    @PreUpdate time to run update
    public void setCreatedBy() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();
            Long currentUserName = userDetails.getId();
            this.createdBy = String.valueOf(currentUserName);
        }
    }

    @UpdateTimestamp
    @Column(name = "update_at",updatable = true,insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateAt = new Date();


    @Column(name = "update_by",updatable = true,insertable = false)
    private String updatedBy;

//    @PrePersist // time to run insert
   @PreUpdate  //time to run update
    public void setUpdatedBy() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();
            Long currentUserName = userDetails.getId();
            this.updatedBy = String.valueOf(currentUserName);
        }
    }

    @Column(name = "status")
    private Boolean status;


}

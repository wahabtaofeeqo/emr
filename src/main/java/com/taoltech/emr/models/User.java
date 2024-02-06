/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taoltech.emr.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;


/**
 *
 * @author taoltech
 */
@Entity(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column
    private String name;
    
    @Column
    private String username;
    
    @Column
    private String password;
    
    @OneToOne(optional = true)
    private Role role;
    
    @CreationTimestamp
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdAt;
    
    private Date updatedAt;
    
    @PreUpdate
    private void onUpdate() {
        updatedAt = new Date();
    }
}

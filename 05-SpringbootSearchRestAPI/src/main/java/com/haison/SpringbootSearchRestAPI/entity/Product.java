package com.haison.SpringbootSearchRestAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "sku")
    private String sku; // stock keep unit
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "active")
    private boolean active;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @Column(name = "date_updated")
    @UpdateTimestamp
    private LocalDateTime dateUpdated;

}

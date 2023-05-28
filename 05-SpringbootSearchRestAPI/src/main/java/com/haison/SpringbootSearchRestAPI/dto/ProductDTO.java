package com.haison.SpringbootSearchRestAPI.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private long id;
    private String sku;
    private String name;
    private String description;
    private boolean active;
    private String imageUrl;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}

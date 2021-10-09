package com.visionboard.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vision {
    @Id
    private String visionId;
    private String title;
    private String vision;
    private LocalDateTime createdOn;
    private LocalDateTime lastUpdated;
    private Category category;



//    public void addToCategory(Category... category) {
//        if (categories == null) {
//            this.categories = new ArrayList<>();
//            categories.addAll(Arrays.asList(category));
//        }
//    }
//
//    public void removeFromCategoty(Category category) {
//        if (categories == null) {
//            this.categories = new ArrayList<>();
//        }
//        this.categories.remove(category);
//    }
}
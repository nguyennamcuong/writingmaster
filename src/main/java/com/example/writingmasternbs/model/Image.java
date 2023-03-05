package com.example.writingmasternbs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "tb_image")
public class Image {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String image_id;
        private String image_name;
        private String user_id;

        public Image(String image_name, String user_id) {
                this.image_name = image_name;
                this.user_id = user_id;
        }
}

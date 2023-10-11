package com.ifmatch.ifmatchservice.models;

import com.ifmatch.ifmatchservice.enums.UserStatus;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private UserStatus status;

    @Column(name = "profile_img")
    private byte[] profileImg;
}

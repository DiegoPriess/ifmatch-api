package com.ifmatch.ifmatchservice.models;

import com.ifmatch.ifmatchservice.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idUser;
    private String name;
    private String email;
    private String password;
    private UserStatus status;
    @Lob
    private byte[] profileImg;
}

package com.ifmatch.ifmatchservice.models;

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
@Table(name = "conf")
public class Conf {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idSistem;
    private Boolean needUpdateUsers;
}

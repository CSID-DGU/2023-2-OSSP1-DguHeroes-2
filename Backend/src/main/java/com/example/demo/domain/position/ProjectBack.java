package com.example.demo.domain.position;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectBack extends PositionBase{
}

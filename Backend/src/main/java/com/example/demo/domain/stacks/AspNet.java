package com.example.demo.domain.stacks;

import lombok.*;

import javax.persistence.Entity;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AspNet extends StackBase {
}

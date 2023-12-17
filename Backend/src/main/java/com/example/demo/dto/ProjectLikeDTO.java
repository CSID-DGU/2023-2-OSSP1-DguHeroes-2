
package com.example.demo.dto;

import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@ToString
public class ProjectLikeDTO {

    private int project_like_id;
    private User user;
    private Project project;


}
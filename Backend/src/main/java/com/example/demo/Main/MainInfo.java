package com.example.demo.Main;

import com.example.demo.domain.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MainInfo {
    List<Project> recommend;
    List<Project> popular;
    List<Project> recent;
}




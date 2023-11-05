package com.example.demo.service;

import com.example.demo.domain.DevelopmentStack;
import com.example.demo.repository.DevelopmentStackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class DevelopmentStackService {
    DevelopmentStackRepository develop_rp;
    public DevelopmentStackService(DevelopmentStackRepository developmentStackRepository){
        develop_rp = developmentStackRepository;
    }


    public void insert(DevelopmentStack developmentStack){
        develop_rp.insert(developmentStack);
    }


    public List<DevelopmentStack> findUsersByStacks(List<String> requiredStacks) {
        List<DevelopmentStack> list = develop_rp.findUsersByStacks(requiredStacks);
        return list;

    }
}

package com.academia.services;

import com.academia.entities.Students;
import com.academia.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentService {
    @Autowired
    private StudentRepository repo;

    public List<Students> listAll() {
        return repo.findAll();
    }
}

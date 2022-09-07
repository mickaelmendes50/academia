package com.academia.services;

import com.academia.entities.Equipment;
import com.academia.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EquipmentService {
    @Autowired
    private EquipmentRepository repo;

    public List<Equipment> listAll() {
        return repo.findAll();
    }
}

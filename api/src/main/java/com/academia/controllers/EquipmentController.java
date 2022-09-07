package com.academia.controllers;

import com.academia.entities.Equipment;
import com.academia.repositories.EquipmentRepository;
import com.academia.responses.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class EquipmentController {
    @Autowired
    private EquipmentRepository equipmentRepository;

    @RequestMapping(value = "/equipment", method = RequestMethod.GET)
    public ResponseEntity<APIResponse<List<Equipment>>> findAll() {
        APIResponse<List<Equipment>> apiResponse = new APIResponse<List<Equipment>>();
        List<Equipment> equipments = equipmentRepository.findAll();

        if (equipments.isEmpty()) {
            apiResponse.getErrors().add("Nenhum equipamento encontrado");

            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

        apiResponse.setData(equipments);

        return ResponseEntity.ok(apiResponse);
    }

    @RequestMapping(value = "/equipment/{id}", method = RequestMethod.GET)
    public ResponseEntity<APIResponse<Equipment>> find(@PathVariable(value = "id") long id) {
        APIResponse<Equipment> apiResponse = new APIResponse<Equipment>();
        Optional<Equipment> equipment = equipmentRepository.findById(id);

        if (equipment.isPresent()) {
            apiResponse.setData(equipment.get());
            return ResponseEntity.ok().body(apiResponse);
        } else {
            apiResponse.getErrors().add("Equipamento não encontrado");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/equipment", method = RequestMethod.POST)
    public ResponseEntity<APIResponse<Equipment>> create(@Valid @RequestBody Equipment equipment, BindingResult result) {
        APIResponse<Equipment> apiResponse = new APIResponse<Equipment>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> apiResponse.getErrors().add(error.getDefaultMessage()));

            return ResponseEntity.badRequest().body(apiResponse);
        }

        equipmentRepository.save(equipment);
        apiResponse.setData(equipment);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/equipment/{id}", method = RequestMethod.PUT)
    public ResponseEntity<APIResponse<Equipment>> update(@PathVariable(value = "id") long id,
                                                         @Valid @RequestBody Equipment updatedEquipment,
                                                         BindingResult result) {
        APIResponse<Equipment> apiResponse = new APIResponse<Equipment>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> apiResponse.getErrors().add(error.getDefaultMessage()));

            return ResponseEntity.badRequest().body(apiResponse);
        }

        Optional<Equipment> oldEquipment = equipmentRepository.findById(id);

        if (oldEquipment.isPresent()) {
            Equipment equipment = oldEquipment.get();

            equipment.setName(updatedEquipment.getName());
            equipment.setDescription(updatedEquipment.getDescription());
            equipment.setCategory(updatedEquipment.getCategory());
            equipment.setWeight(updatedEquipment.getWeight());
            equipment.setDimensions(updatedEquipment.getDimensions());
            equipment.setPrice(updatedEquipment.getPrice());

            equipmentRepository.save(equipment);
            apiResponse.setData(equipment);

            return ResponseEntity.ok(apiResponse);
        } else {
            apiResponse.getErrors().add("Equipamento não encontrado");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/equipment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<APIResponse<Equipment>> destroy(@PathVariable(value = "id") long id) {
        APIResponse<Equipment> apiResponse = new APIResponse<Equipment>();
        Optional<Equipment> equipment = equipmentRepository.findById(id);

        if (equipment.isPresent()) {
            equipmentRepository.delete(equipment.get());

            return ResponseEntity.ok().body(apiResponse);
        } else {
            apiResponse.getErrors().add("Equipamento não encontrado");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

}

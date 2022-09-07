package com.academia.controllers;

import com.academia.entities.PhysicalMeasures;
import com.academia.repositories.PhysicalMeasuresRepository;
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
public class PhysicalMeasuresController {
    @Autowired
    private PhysicalMeasuresRepository physicalMeasuresRepository;

    @RequestMapping(value = "/physicalMeasures", method = RequestMethod.GET)
    public ResponseEntity<APIResponse<List<PhysicalMeasures>>> findAll() {
        APIResponse<List<PhysicalMeasures>> apiResponse = new APIResponse<List<PhysicalMeasures>>();
        List<PhysicalMeasures> physicalMeasuress = physicalMeasuresRepository.findAll();

        if (physicalMeasuress.isEmpty()) {
            apiResponse.getErrors().add("Nenhuma avaliação física encontrada");

            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

        apiResponse.setData(physicalMeasuress);

        return ResponseEntity.ok(apiResponse);
    }

    @RequestMapping(value = "/physicalMeasures/{id}", method = RequestMethod.GET)
    public ResponseEntity<APIResponse<PhysicalMeasures>> find(@PathVariable(value = "id") long id) {
        APIResponse<PhysicalMeasures> apiResponse = new APIResponse<PhysicalMeasures>();
        Optional<PhysicalMeasures> physicalMeasures = physicalMeasuresRepository.findById(id);

        if (physicalMeasures.isPresent()) {
            apiResponse.setData(physicalMeasures.get());
            return ResponseEntity.ok().body(apiResponse);
        } else {
            apiResponse.getErrors().add("Tabela de exercício não encontrada");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/physicalMeasures", method = RequestMethod.POST)
    public ResponseEntity<APIResponse<PhysicalMeasures>> create(@Valid @RequestBody PhysicalMeasures physicalMeasures, BindingResult result) {
        APIResponse<PhysicalMeasures> apiResponse = new APIResponse<PhysicalMeasures>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> apiResponse.getErrors().add(error.getDefaultMessage()));

            return ResponseEntity.badRequest().body(apiResponse);
        }

        physicalMeasuresRepository.save(physicalMeasures);
        apiResponse.setData(physicalMeasures);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/physicalMeasures/{id}", method = RequestMethod.PUT)
    public ResponseEntity<APIResponse<PhysicalMeasures>> update(@PathVariable(value = "id") long id,
                                                         @Valid @RequestBody PhysicalMeasures updatedPhysicalMeasures,
                                                         BindingResult result) {
        APIResponse<PhysicalMeasures> apiResponse = new APIResponse<PhysicalMeasures>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> apiResponse.getErrors().add(error.getDefaultMessage()));

            return ResponseEntity.badRequest().body(apiResponse);
        }

        Optional<PhysicalMeasures> oldPhysicalMeasures = physicalMeasuresRepository.findById(id);

        if (oldPhysicalMeasures.isPresent()) {
            PhysicalMeasures physicalMeasures = oldPhysicalMeasures.get();

            physicalMeasures.setBodybuilderName(updatedPhysicalMeasures.getBodybuilderName());
            physicalMeasures.setHeight(updatedPhysicalMeasures.getHeight());
            physicalMeasures.setWeight(updatedPhysicalMeasures.getWeight());

            physicalMeasuresRepository.save(physicalMeasures);
            apiResponse.setData(physicalMeasures);

            return ResponseEntity.ok(apiResponse);
        } else {
            apiResponse.getErrors().add("Avaliação física não encontrada");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/physicalMeasures/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<APIResponse<PhysicalMeasures>> destroy(@PathVariable(value = "id") long id) {
        APIResponse<PhysicalMeasures> apiResponse = new APIResponse<PhysicalMeasures>();
        Optional<PhysicalMeasures> physicalMeasures = physicalMeasuresRepository.findById(id);

        if (physicalMeasures.isPresent()) {
            physicalMeasuresRepository.delete(physicalMeasures.get());

            return ResponseEntity.ok().body(apiResponse);
        } else {
            apiResponse.getErrors().add("Avaliação física não encontrada");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

}

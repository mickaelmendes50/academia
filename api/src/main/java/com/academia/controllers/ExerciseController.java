package com.academia.controllers;

import com.academia.entities.Exercise;
import com.academia.repositories.ExerciseRepository;
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
public class ExerciseController {
    @Autowired
    private ExerciseRepository exerciseRepository;

    @RequestMapping(value = "/exercise", method = RequestMethod.GET)
    public ResponseEntity<APIResponse<List<Exercise>>> findAll() {
        APIResponse<List<Exercise>> apiResponse = new APIResponse<List<Exercise>>();
        List<Exercise> exercises = exerciseRepository.findAll();

        if (exercises.isEmpty()) {
            apiResponse.getErrors().add("Nenhum exercício encontrado");

            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

        apiResponse.setData(exercises);

        return ResponseEntity.ok(apiResponse);
    }

    @RequestMapping(value = "/exercise/{id}", method = RequestMethod.GET)
    public ResponseEntity<APIResponse<Exercise>> find(@PathVariable(value = "id") long id) {
        APIResponse<Exercise> apiResponse = new APIResponse<Exercise>();
        Optional<Exercise> exercise = exerciseRepository.findById(id);

        if (exercise.isPresent()) {
            apiResponse.setData(exercise.get());
            return ResponseEntity.ok().body(apiResponse);
        } else {
            apiResponse.getErrors().add("Exercício não encontrado");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/exercise", method = RequestMethod.POST)
    public ResponseEntity<APIResponse<Exercise>> create(@Valid @RequestBody Exercise exercise, BindingResult result) {
        APIResponse<Exercise> apiResponse = new APIResponse<Exercise>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> apiResponse.getErrors().add(error.getDefaultMessage()));

            return ResponseEntity.badRequest().body(apiResponse);
        }

        exerciseRepository.save(exercise);
        apiResponse.setData(exercise);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/exercise/{id}", method = RequestMethod.PUT)
    public ResponseEntity<APIResponse<Exercise>> update(@PathVariable(value = "id") long id,
                                                         @Valid @RequestBody Exercise updatedExercise,
                                                         BindingResult result) {
        APIResponse<Exercise> apiResponse = new APIResponse<Exercise>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> apiResponse.getErrors().add(error.getDefaultMessage()));

            return ResponseEntity.badRequest().body(apiResponse);
        }

        Optional<Exercise> oldExercise = exerciseRepository.findById(id);

        if (oldExercise.isPresent()) {
            Exercise exercise = oldExercise.get();

            exercise.setName(updatedExercise.getName());
            exercise.setMuscleGroup(updatedExercise.getMuscleGroup());
            exercise.setEquipment(updatedExercise.getEquipment());

            exerciseRepository.save(exercise);
            apiResponse.setData(exercise);

            return ResponseEntity.ok(apiResponse);
        } else {
            apiResponse.getErrors().add("Exercício não encontrado");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/exercise/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<APIResponse<Exercise>> destroy(@PathVariable(value = "id") long id) {
        APIResponse<Exercise> apiResponse = new APIResponse<Exercise>();
        Optional<Exercise> exercise = exerciseRepository.findById(id);

        if (exercise.isPresent()) {
            exerciseRepository.delete(exercise.get());

            return ResponseEntity.ok().body(apiResponse);
        } else {
            apiResponse.getErrors().add("Exercício não encontrado");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }
}

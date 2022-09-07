package com.example.gym_students_crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.gym_students_crud.entities.Students;
import com.example.gym_students_crud.repositories.StudentRepository;
import com.example.gym_students_crud.responses.APIResponse;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentsController {
    @Autowired
    private StudentRepository studentsRepository;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public ResponseEntity<APIResponse<List<Students>>> index() {
        APIResponse<List<Students>> apiResponse = new APIResponse<List<Students>>();
        List<Students> students = studentsRepository.findAll();

        if (students.isEmpty()) {
            apiResponse.getErrors().add("Nenhum aluno encontrado");

            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

        apiResponse.setData(students);

        return ResponseEntity.ok(apiResponse);
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public ResponseEntity<APIResponse<Students>> find(@PathVariable(value = "id") long id) {
        APIResponse<Students> apiResponse = new APIResponse<Students>();
        Optional<Students> student = studentsRepository.findById(id);

        if (student.isPresent()) {
            apiResponse.setData(student.get());
            return ResponseEntity.ok().body(apiResponse);
        } else {
            apiResponse.getErrors().add("aluno não encontrado");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public ResponseEntity<APIResponse<Students>> create(@Valid @RequestBody Students students, BindingResult result) {
        APIResponse<Students> apiResponse = new APIResponse<Students>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> apiResponse.getErrors().add(error.getDefaultMessage()));

            return ResponseEntity.badRequest().body(apiResponse);
        }

        studentsRepository.save(students);
        apiResponse.setData(students);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
    public ResponseEntity<APIResponse<Students>> update(@PathVariable(value = "id") long id,
                                                         @Valid @RequestBody Students updatedStudent,
                                                         BindingResult result) {
        APIResponse<Students> apiResponse = new APIResponse<Students>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> apiResponse.getErrors().add(error.getDefaultMessage()));

            return ResponseEntity.badRequest().body(apiResponse);
        }

        Optional<Students> oldStudent = studentsRepository.findById(id);

        if (oldStudent.isPresent()) {
            Students students = oldStudent.get();

            students.setName(updatedStudent.getName());
            students.setEmail(updatedStudent.getEmail());
            students.setCpf(updatedStudent.getCpf());
            students.setTipo(updatedStudent.getTipo());

            studentsRepository.save(students);
            apiResponse.setData(students);

            return ResponseEntity.ok(apiResponse);
        } else {
            apiResponse.getErrors().add("Aluno não encontrado");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<APIResponse<Students>> destroy(@PathVariable(value = "id") long id) {
        APIResponse<Students> apiResponse = new APIResponse<Students>();
        Optional<Students> students = studentsRepository.findById(id);

        if (students.isPresent()) {
            studentsRepository.delete(students.get());

            return ResponseEntity.ok().body(apiResponse);
        } else {
            apiResponse.getErrors().add("Aluno não encontrado");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

}

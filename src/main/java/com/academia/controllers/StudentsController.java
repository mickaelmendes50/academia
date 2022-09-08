package com.academia.controllers;

import com.academia.export.StudentExcelExporter;
import com.academia.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.academia.entities.Students;
import com.academia.repositories.StudentRepository;
import com.academia.responses.APIResponse;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentsController {
    @Autowired
    private StudentRepository studentsRepository;
    @Autowired
    private StudentService service;

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

    @GetMapping("/students/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=alunos_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Students> listStudents = service.listAll();

        StudentExcelExporter excelExporter = new StudentExcelExporter(listStudents);

        excelExporter.export(response);
    }
}

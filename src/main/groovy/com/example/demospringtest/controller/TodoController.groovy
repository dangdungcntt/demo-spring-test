package com.example.demospringtest.controller

import com.example.demospringtest.business.TodoBusiness
import com.example.demospringtest.model.Todo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todos")
class TodoController {

    @Autowired
    TodoBusiness todoBusiness

    @PostMapping
    Todo create(@RequestBody Todo todo) {
        return todoBusiness.create(todo)
    }

    @GetMapping
    List<Todo> getAll() {
        return todoBusiness.getAll()
    }
}

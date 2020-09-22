package com.example.demospringtest.business.impl

import com.example.demospringtest.business.TodoBusiness
import com.example.demospringtest.model.Todo
import com.example.demospringtest.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TodoBusinessImpl implements TodoBusiness {

    @Autowired
    TodoRepository todoRepository

    Todo create(Todo todo) {
        return todoRepository.save(todo)
    }

    List<Todo> getAll() {
        return todoRepository.findAll().toList()
    }
}

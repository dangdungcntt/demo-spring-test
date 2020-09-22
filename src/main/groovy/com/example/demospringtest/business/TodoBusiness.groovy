package com.example.demospringtest.business

import com.example.demospringtest.model.Todo

interface TodoBusiness {
    Todo create(Todo todo)

    List<Todo> getAll()
}
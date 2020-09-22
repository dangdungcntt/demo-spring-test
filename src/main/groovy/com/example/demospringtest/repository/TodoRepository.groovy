package com.example.demospringtest.repository

import com.example.demospringtest.model.Todo
import org.bson.types.ObjectId
import org.springframework.data.repository.CrudRepository

interface TodoRepository extends CrudRepository<Todo, ObjectId> {
}

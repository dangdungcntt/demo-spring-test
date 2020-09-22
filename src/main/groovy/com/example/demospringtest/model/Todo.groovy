package com.example.demospringtest.model

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "todos")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
class Todo {

    @Id
    ObjectId _id

    String get_id() {
        return this._id?.toString()
    }

    String name


}

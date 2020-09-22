package com.example.demospringtest.controller

import com.example.demospringtest.business.TodoBusiness
import com.example.demospringtest.model.Todo
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(TodoController)
class TodoControllerTest {

    @Autowired
    MockMvc mvc

    @MockBean
    TodoBusiness todoBusiness

    @Autowired
    ObjectMapper objectMapper

    protected Todo defaultData() {
        return new Todo(name: 'New Todo')
    }

    @BeforeEach
    void setUp() {
        Mockito.when(todoBusiness.getAll())
                .thenReturn((0..9).collect { new Todo(name: 'todo-' + it) })
        Mockito.when(todoBusiness.create(Mockito.any(Todo)))
                .thenReturn(defaultData())
    }

    @Test
    void testFindAll() {
        mvc.perform(MockMvcRequestBuilders.get('/todos').contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath('$', Matchers.hasSize(10)))
                .andExpect(MockMvcResultMatchers.jsonPath('$[0].name', Matchers.is('todo-0')))
                .andExpect(MockMvcResultMatchers.jsonPath('$[9].name', Matchers.is('todo-9')))
    }

    @Test
    void testCreate() {
        Todo todo = defaultData()

        mvc.perform(
                MockMvcRequestBuilders.post('/todos')
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todo))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath('$.name', Matchers.is(todo.name)))

    }
}

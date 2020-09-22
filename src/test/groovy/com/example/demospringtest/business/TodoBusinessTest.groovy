package com.example.demospringtest.business

import com.example.demospringtest.business.impl.TodoBusinessImpl
import com.example.demospringtest.model.Todo
import com.example.demospringtest.repository.TodoRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension.class)
class TodoBusinessTest {

    @MockBean
    TodoRepository todoRepository

    @Autowired
    TodoBusiness todoBusiness

    @TestConfiguration
    static class TodoBusinessTestConfiguration {
        @Bean
        TodoBusiness todoBusiness(){
            return new TodoBusinessImpl()
        }
    }

    protected Todo defaultData() {
        return new Todo(name: 'New Todo')
    }

    @BeforeEach
    void setUp() {
        Mockito.when(todoRepository.findAll())
                .thenReturn((0..9).collect { new Todo(name: 'todo-' + it) })

        Mockito.when(todoRepository.save(Mockito.any(Todo)))
                .thenReturn(defaultData())
    }

    @Test
    void testFindAll() {
        assert 10 == todoBusiness.getAll().size()
    }

    @Test
    void testCreate() {
        Todo todo = defaultData()
        assert todo.name == todoBusiness.create(todo).name
    }
}

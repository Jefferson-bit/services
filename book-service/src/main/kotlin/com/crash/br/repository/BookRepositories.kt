package com.crash.br.repository
import com.crash.br.model.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepositories: JpaRepository<Book, Long>
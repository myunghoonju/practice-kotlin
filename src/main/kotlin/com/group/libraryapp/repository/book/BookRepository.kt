package com.group.libraryapp.repository.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.dto.book.response.BookStatResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BookRepository : JpaRepository<Book, Long>, BookRepositoryCustom {

    //service layer uses Optional
    fun findByName(name: String): Book?

//    @Query("SELECT new com.group.libraryapp.dto.book.response.BookStatResponse(b.type, COUNT(b.id)) FROM Book b GROUP BY b.type")
//    fun bookTypeStatistics(): List<BookStatResponse>
}
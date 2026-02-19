package com.group.libraryapp.repository.book

import com.group.libraryapp.dto.book.response.BookStatResponse

interface BookRepositoryCustom {

    fun bookTypeStatistics(): List<BookStatResponse>
}
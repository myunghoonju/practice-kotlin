package com.group.libraryapp.repository.book

import com.group.libraryapp.domain.book.QBook.book
import com.group.libraryapp.dto.book.response.BookStatResponse
import com.querydsl.core.types.ConstructorExpression
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryCustomImpl(
    private val query: JPAQueryFactory,
): BookRepositoryCustom {

    override fun bookTypeStatistics(): List<BookStatResponse> {
        return query.select(fields()).from(book).groupBy(book.type).fetch()
    }

    private fun fields(): ConstructorExpression<BookStatResponse> {
        return Projections.constructor(BookStatResponse::class.java,
                                       book.type,
                                       book.id.count())
    }
}
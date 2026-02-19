package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.repository.book.BookRepository
import com.group.libraryapp.repository.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.LoanStatus
import com.group.libraryapp.repository.loan.UserLoanHistoryRepository
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.dto.book.response.BookStatResponse
import com.group.libraryapp.util.fail
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService constructor(
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {
    @Transactional
    fun saveBook(request: BookRequest) {
        bookRepository.save(Book(request.name, request.type))
    }

    @Transactional
    fun loanBook(request: BookLoanRequest) {
        val book = bookRepository.findByName(request.bookName) ?: fail()
        val record = userLoanHistoryRepository.findByBookNameAndStatus(request.bookName, LoanStatus.BORROW)
        if (record?.status != null) {
            throw IllegalArgumentException("진작 대출되어 있는 책입니다")
        }

        val user = userRepository.findByName(request.userName) ?: fail()
        user.loanBook(book)
    }

   @Transactional
   fun returnBook(bookReturnRequest: BookReturnRequest) {
       val user = userRepository.findByName(bookReturnRequest.userName) ?: fail()
       user.returnBook(bookReturnRequest.bookName)
   }

    @Transactional(readOnly = true)
    fun countLoanBook(): Int {
        return userLoanHistoryRepository.countByStatus(LoanStatus.BORROW).toInt()
    }

    @Transactional(readOnly = true)
    fun bookStatistics(): List<BookStatResponse> {
        return bookRepository.bookTypeStatistics()
//        return bookRepository.findAll()
//                             .groupBy { book -> book.type } // Map<BookType, List<Book>>
//                             .map { (type, books) -> BookStatResponse(type, books.size) } // List<BookStatResponse>
    }
}
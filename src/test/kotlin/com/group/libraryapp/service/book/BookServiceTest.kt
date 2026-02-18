package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.book.field.BookType
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.LoanStatus
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceTest @Autowired constructor (
    private val bookService: BookService,
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val loanRepository: UserLoanHistoryRepository,
) {

    @AfterEach
    fun cleanup() {
        bookRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    fun 책_저장_테스트() {
        //given
        var req = BookRequest("Hamlet", BookType.SOCIETY)
        //when
        bookService.saveBook(req)
        //then
        var books = bookRepository.findAll()
        assertThat(books[0].name).startsWith("Hamlet")
    }

    @Test
    fun 책_조회_후_대출_상태_여부_확인하는_테스트() {
        //given
        val req = BookRequest("Hamlet", BookType.SOCIETY)
        bookService.saveBook(req)
        val savedUser = userRepository.save(User("admin", 100))
        val loanRequest = BookLoanRequest("admin", "Hamlet")
        //when
        bookService.loanBook(loanRequest)
        val records = loanRepository.findAll()
        assertThat(records.size).isEqualTo(1)
        assertThat(records[0].bookName).isEqualTo("Hamlet")
        assertThat(records[0].user.name).isEqualTo("admin")
        assertThat(records[0].user.id).isEqualTo(savedUser.id)
        assertThat(records[0].status).isEqualTo(LoanStatus.BORROW)
    }

    @Test
    fun 대출된_책은_신규_대출이_실패하는_테스트() {
        //given
        bookService.saveBook(BookRequest("Hamlet", BookType.SOCIETY))
        val savedUser = userRepository.save(User("admin", 100))
        loanRepository.save(UserLoanHistory.fixture(savedUser, "Hamlet"))

        val message = assertThrows<IllegalArgumentException> {
            val loanRequest = BookLoanRequest("admin", "Hamlet")
            bookService.loanBook(loanRequest)
        }.message


        assertThat(message).isEqualTo("진작 대출되어 있는 책입니다")
    }

    @Test
    fun 책을_반납하는_테스트() {
        bookService.saveBook(BookRequest("Hamlet", BookType.SOCIETY))
        val savedUser = userRepository.save(User("admin", 100))
        val savedLoan = loanRepository.save(UserLoanHistory.fixture(savedUser, "Hamlet"))
        bookService.returnBook(BookReturnRequest(savedUser.name, savedLoan.bookName))

        val records = loanRepository.findAll()
        assertThat(records.size).isEqualTo(1)
        assertThat(records[0].bookName).isEqualTo("Hamlet")
        assertThat(records[0].user.name).isEqualTo("admin")
        assertThat(records[0].status).isEqualTo(LoanStatus.RETURN)
    }
}

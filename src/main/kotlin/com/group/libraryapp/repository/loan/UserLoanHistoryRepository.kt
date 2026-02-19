package com.group.libraryapp.repository.loan

import com.group.libraryapp.domain.user.loanhistory.LoanStatus
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import org.springframework.data.jpa.repository.JpaRepository

/** replaced by userLoanHistoryCustom(querydsl) */
interface UserLoanHistoryRepository : JpaRepository<UserLoanHistory, Long> {

    fun findByBookNameAndStatus(bookName: String, loanStatus: LoanStatus): UserLoanHistory?

    fun findAllByStatus(loanStatus: LoanStatus): List<UserLoanHistory>

    fun countByStatus(loanStatus: LoanStatus): Long
}
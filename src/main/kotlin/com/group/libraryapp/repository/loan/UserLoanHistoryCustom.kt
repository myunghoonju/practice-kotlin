package com.group.libraryapp.repository.loan

import com.group.libraryapp.domain.user.loanhistory.LoanStatus
import com.group.libraryapp.domain.user.loanhistory.QUserLoanHistory.userLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class UserLoanHistoryCustom(
    private val query: JPAQueryFactory,
) {

    fun find(bookName: String, status: LoanStatus? = null) : UserLoanHistory? {
        return query.select(userLoanHistory)
                    .from(userLoanHistory)
                    .where(userLoanHistory.bookName.eq(bookName),
                           statusCondition(status))
                    .fetchFirst()
    }

    fun statusCondition(status: LoanStatus?): BooleanExpression? {
        return status?.let { status -> userLoanHistory.status.eq(status) }
    }

    fun count(staus: LoanStatus): Long {
        return query.select(userLoanHistory.id.count())
                    .from(userLoanHistory)
                    .where(userLoanHistory.status.eq(staus))
                    .fetchOne()?: 0L
    }
}
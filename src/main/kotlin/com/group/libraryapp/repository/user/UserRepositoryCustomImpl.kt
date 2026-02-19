package com.group.libraryapp.repository.user

import com.group.libraryapp.domain.user.QUser.user
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.loanhistory.QUserLoanHistory.userLoanHistory
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryCustomImpl(
    private val query: JPAQueryFactory,
) : UserRepositoryCustom {

    override fun findAllHistories(): List<User> {
        return query.select(user)
                    .distinct()
                    .from(user)
                    .leftJoin(userLoanHistory)
                    .on(user.id.eq(userLoanHistory.user.id))
                    .fetch()
    }
}
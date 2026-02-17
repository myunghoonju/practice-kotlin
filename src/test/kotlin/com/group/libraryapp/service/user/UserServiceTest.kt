package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userService: UserService,
    private val userRepository: UserRepository,
){
    @AfterEach
    fun cleanup(){
        userRepository.deleteAll()
    }

    @Test
    fun save_user_test() {
        //given
        var req = UserCreateRequest("myunghoon", null)

        //when
        userService.saveUser(req)

        //then
        var allUsers = userRepository.findAll()
        assertThat(allUsers).hasSize(1)
        assertThat(allUsers[0].name).isEqualTo("myunghoon")
        assertThat(allUsers[0].age).isNull()
    }

    @Test
    fun get_user_test() {
        //given
        userRepository.saveAll(listOf(
            User("hoon", 19),
            User("myung", 20),
        ))

        //when
        val results = userService.getUsers()

        //then
        assertThat(results).hasSize(2)
        assertThat(results).extracting("name")
                           .containsExactlyInAnyOrder("hoon", "myung")
        assertThat(results).extracting("age")
                           .containsExactlyInAnyOrder(20, 19)
    }

    @Test
    fun update_user_test() {
        //given
        val saved = userRepository.save(User("hoon", 19))
        val req = UserUpdateRequest(saved.id, "ju")

        //when
        userService.updateUserName(req)

        //then
        val allUsers = userRepository.findAll()
        assertThat(allUsers[0].name).isEqualTo("ju")
    }
}

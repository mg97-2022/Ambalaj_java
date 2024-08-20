package com.Ambalaj.Ambalaj.repository;

import com.Ambalaj.Ambalaj.enums.AppUserType;
import com.Ambalaj.Ambalaj.model.AppUserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AppUserRepositoryTest {
    AppUserEntity testUser;

    @Autowired
    private AppUserRepository underTest;

    @BeforeEach
    void setUp() {
        testUser = new AppUserEntity();
        testUser.setEmail("mg97@gmail.com");
        testUser.setType(AppUserType.COMPANY);
        testUser.setPassword("password");
        testUser.setEnabled(true);
        testUser.setLocked(false);
        testUser.setToken("token");

        underTest.save(testUser);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findAppUserByEmail_WhenAppUserExists_shouldReturnAppUser() {
        // given
        String testEmail = testUser.getEmail();

        // when
        Optional<AppUserEntity> found = underTest.findByEmail(testEmail);

        // then
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getEmail()).isEqualTo(testEmail);
        assertThat(found.get().getType()).isEqualTo(AppUserType.COMPANY);
        assertThat(found.get().getPassword()).isEqualTo("password");
        assertThat(found.get().getEnabled()).isTrue();
        assertThat(found.get().getLocked()).isFalse();
    }

    @Test
    void findAppUserByEmail_WhenAppUserNotExists_shouldReturnOptionalWithIsPresentEqualFalse() {
        // given
        String notExistEmail = "notExistEmail@gmail.com";

        // when
        Optional<AppUserEntity> found = underTest.findByEmail(notExistEmail);

        // then
        assertThat(found.isPresent()).isFalse();
    }

    @Test
    void findAppUserByToken_WhenAppUserExists_shouldReturnAppUser() {
        // given
        String token = testUser.getToken();

        // when
        Optional<AppUserEntity> found = underTest.findByToken(token);

        // then
        assertThat(found.isPresent()).isTrue();
    }

    @Test
    void findAppUserByToken_WhenAppUserNotExists_shouldReturnOptionalWithIsPresentEqualFalse() {
        // given
        String notExistToken = "not_exist_token";

        // when
        Optional<AppUserEntity> found = underTest.findByToken(notExistToken);

        // then
        assertThat(found.isPresent()).isFalse();
    }
}

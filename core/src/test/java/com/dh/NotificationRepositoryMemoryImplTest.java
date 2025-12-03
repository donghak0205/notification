package com.dh;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootApplication
@SpringBootTest
class NotificationRepositoryMemoryImplTest {

    @Autowired
    private NotificationRepository sut;

    private final Instant now = Instant.now();
    private final Instant deletedAt = Instant.now().plus(90, DAYS);


    @Test
    void test_save() {
        //저장하고 조회가 잘되는지 테스트
        sut.save(new Notification("1",2L, NotificationType.LIKE, Instant.now(), Instant.now().plus(90, DAYS)));
        Optional<Notification> notification = sut.findById("1");

        assertTrue(notification.isPresent());
    }

    @Test
    void test_find_by_id(){
        sut.save(new Notification("2",2L, NotificationType.LIKE, now, deletedAt));
        Optional<Notification> optionalNotification = sut.findById("2");

        //저자하고 조회한 후 세부항목들이 명확한지 테스트
        Notification notification = optionalNotification.orElseThrow();
        assertEquals(notification.id,"2");
        assertEquals(notification.userId,2L);
        assertEquals(notification.createdAt,now);
        assertEquals(notification.deleteAt,deletedAt);
    }

    @Test
    void test_delete_by_id(){
        sut.save(new Notification("3",2L, NotificationType.LIKE, now, deletedAt));
        sut.deleteById("3");

        Optional<Notification> optionalNotification = sut.findById("3");
        assertFalse(optionalNotification.isPresent());

    }




}
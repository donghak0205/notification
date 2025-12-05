package com.dh;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootApplication
@SpringBootTest
class NotificationRepositoryMemoryImplTest {

    @Autowired
    private NotificationRepository sut;

    private final Instant now = Instant.now();
    private final Instant ninetyDaysAfter = Instant.now().plus(90, DAYS);


    @Test
    void test_save() {
        //저장하고 조회가 잘되는지 테스트
        sut.save(new Notification("1",2L, NotificationType.LIKE, Instant.now(), Instant.now().plus(90, DAYS)));
        Optional<Notification> notification = sut.findById("1");

        assertTrue(notification.isPresent());
    }

    @Test
    void test_find_by_id(){
        sut.save(new Notification("2",2L, NotificationType.LIKE, now, ninetyDaysAfter));
        Optional<Notification> optionalNotification = sut.findById("2");

        //저장하고 조회한 후 세부항목들이 명확한지 테스트
        Notification notification = optionalNotification.orElseThrow();
        assertEquals(notification.id,"2");
        assertEquals(notification.userId,2L);
        assertEquals(notification.createdAt.getEpochSecond(),now.getEpochSecond());
        assertEquals(notification.deleteAt.getEpochSecond(),ninetyDaysAfter.getEpochSecond());
    }

    @Test
    void test_delete_by_id(){
        sut.save(new Notification("3",2L, NotificationType.LIKE, now, ninetyDaysAfter));
        sut.deleteById("3");

        Optional<Notification> optionalNotification = sut.findById("3");
        assertFalse(optionalNotification.isPresent());

    }




}
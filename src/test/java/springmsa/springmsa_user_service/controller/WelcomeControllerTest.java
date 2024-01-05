package springmsa.springmsa_user_service.controller;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class WelcomeControllerTest {

    @Test
    void timeTest() {
        String dateTimeString = "2024-01-03T10:02:40.163";

        // Parse the given string to LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // Convert to Korea timezone
        ZoneId utcZone = ZoneId.of("UTC");
        ZoneId koreaZone = ZoneId.of("Asia/Seoul");

        ZonedDateTime utcDateTime = ZonedDateTime.of(dateTime, utcZone);
        ZonedDateTime koreaDateTime = utcDateTime.withZoneSameInstant(koreaZone);

        System.out.println("time:" + koreaDateTime);

        String datetime = "2024-01-03T11:32:19.564+0900";
        DateTimeFormatter formatterWithOffset = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        // DateTimeFormatter formatterWithoutOffset = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime jiraTime = LocalDateTime.parse(datetime, formatterWithOffset);

        System.out.println("cbTime: " + koreaDateTime.toLocalDateTime());
        System.out.println("jiraTime: " + jiraTime);

        System.out.println("isAfter: " + koreaDateTime.toLocalDateTime().isAfter(jiraTime));

    }

}
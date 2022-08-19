package app.test;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@EnableScheduling
@Component
public class ScheduleTest {
    Logger logger = Logger.getLogger(ScheduleTest.class.getName());
    @Scheduled(fixedRate = 5000)
    public void greeting() {
        logger.info("Logging ... I'm alive.");
    }
}

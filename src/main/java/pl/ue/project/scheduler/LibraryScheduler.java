package pl.ue.project.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.ue.project.data.Copy;
import pl.ue.project.data.CopyStatus;
import pl.ue.project.repository.CopyRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class LibraryScheduler {
    private final CopyRepository copyRepository;

    @Scheduled(cron = "* 0/15 * * * *") // every 15 minutes
    public void prepareCopies() {
        final List<Copy> collect = copyRepository.findAllByStatus(CopyStatus.PREPARING)
                .stream()
                .peek(c -> c.setStatus(CopyStatus.READY_TO_PICK_UP))
                .collect(Collectors.toList());
        copyRepository.saveAll(collect);
    }

    @Scheduled(cron = "0 0 0 ? * *") // every day at 0:00:00
    public void markCopiesAsOverdue() {
        final List<Copy> overdueCopies = copyRepository.findAllByStatusAndExpectedReturnDateLessThan(CopyStatus.BORROWED, LocalDate.now())
                .stream()
                .peek(c -> c.setStatus(CopyStatus.OVERDUE))
                .collect(Collectors.toList());
        copyRepository.saveAll(overdueCopies);
    }
}

package wiki.vero.solgit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.vero.solgit.application.StatisticsService;
import wiki.vero.solgit.application.dto.StatisticsResponse;

@RequestMapping("/statistics")
@RequiredArgsConstructor
@RestController
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<StatisticsResponse> showStatistics() {
        final StatisticsResponse response = statisticsService.showStatistics(1L);

        return ResponseEntity.ok(response);
    }
}

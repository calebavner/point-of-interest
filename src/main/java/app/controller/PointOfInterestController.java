package app.controller;

import app.model.PointOfInterest;
import app.repos.PointOfInterestRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/poi")
public class PointOfInterestController {

    private final PointOfInterestRepository poiRepository;

    public PointOfInterestController(PointOfInterestRepository poiRepository) {
        this.poiRepository = poiRepository;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PointOfInterest poi) {
        poiRepository.save(poi);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<PointOfInterest>> listAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        var list = poiRepository.findAll(PageRequest.of(page, pageSize));
        return ResponseEntity.ok(list);
    }

    @GetMapping("/near-me")
    public ResponseEntity<List<PointOfInterest>> nearMe(@RequestParam("xAxis") Long x,
                                       @RequestParam("yAxis") Long y,
                                       @RequestParam("dmax") Long dmax) {
        Long xMin = x - dmax;
        Long xMax = x + dmax;
        Long yMin = y - dmax;
        Long yMax = y + dmax;

        List<PointOfInterest> response = poiRepository.findNearMe(xMin, xMax, yMin, yMax)
                .stream()
                .filter(p -> distanceBetweenPoints(x, y, p.getxAxis(), p.getyAxis()) <= dmax)
                .toList();

        return ResponseEntity.ok(response);
    }

    private Double distanceBetweenPoints(Long xA, Long yA, Long xB, Long yB) {
        return Math.sqrt(Math.pow(xB - xA, 2) + Math.pow(yB - yA, 2));
    }
}

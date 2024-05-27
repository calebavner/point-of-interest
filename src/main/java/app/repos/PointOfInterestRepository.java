package app.repos;

import app.model.PointOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Long> {

    @Query("""
            SELECT p FROM PointOfInterest p
            WHERE(p.xAxis >= :xMin AND p.xAxis <= :xMax AND p.yAxis >= :yMin AND p.yAxis <= :yMax)
            """)
    List<PointOfInterest> findNearMe(@Param("xMin") Long xMin,
                                     @Param("xMax") Long xMax,
                                     @Param("yMin") Long yMin,
                                     @Param("yMax") Long yMax);
}

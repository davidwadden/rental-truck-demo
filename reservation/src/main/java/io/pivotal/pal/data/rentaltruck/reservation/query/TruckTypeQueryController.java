package io.pivotal.pal.data.rentaltruck.reservation.query;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;

@RestController("/truck-types")
public class TruckTypeQueryController {

    private final TruckTypeQueryRepository repository;

    public TruckTypeQueryController(TruckTypeQueryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Collection<TruckTypeQueryDto> findTruckTypesByPickupLocationDate() {

        return newArrayList(repository.findAll())
                .stream()
                .map(TruckTypeQueryDto::new)
                .collect(toList());
    }


    private class TruckTypeQueryDto {

        private final String truckType;

        private TruckTypeQueryDto(String truckType) {
            this.truckType = truckType;
        }

        private TruckTypeQueryDto(TruckType truckType) {
            this.truckType = truckType.getTruckType();
        }

        public String getTruckType() {
            return truckType;
        }

        @Override
        public String toString() {
            return "TruckTypeQueryDto{" +
                    "truckType='" + truckType + '\'' +
                    '}';
        }
    }
}

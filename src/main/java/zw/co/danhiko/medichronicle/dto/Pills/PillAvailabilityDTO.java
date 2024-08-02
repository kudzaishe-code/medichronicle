package zw.co.danhiko.medichronicle.dto.Pills;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public  class PillAvailabilityDTO {
        private PillsDetailsDTO pillDetails;
        private boolean provided;

        // Getters and setters
        public PillsDetailsDTO getPillDetails() {
            return pillDetails;
        }

        public void setPillDetails(PillsDetailsDTO pillDetails) {
            this.pillDetails = pillDetails;
        }

        public boolean isProvided() {
            return provided;
        }

        public void setProvided(boolean provided) {
            this.provided = provided;
        }
    }
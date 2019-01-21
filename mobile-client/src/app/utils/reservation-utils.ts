import { ReservationResponse, ReservationStatus, StatusToDisplay } from "../models";

class ReservationUtils {

    getStatus(reservation: ReservationResponse): StatusToDisplay {
        switch (reservation.status) {
            case ReservationStatus.SEND:
                return {
                    icon: "&#xf0ec;",       // exchange
                    description: "Oczekująca"
                };

            case ReservationStatus.ACCEPTED_BY_RESTAURANT:
                return {
                    icon: "&#xf087;",       // thumb up
                    description: "Zaakceptowana"
                };

            case ReservationStatus.REJECTED_BY_RESTAURANT:
                return {
                    icon: "&#xf165;",       // thumb down
                    description: "Odrzucona"
                };
        }
    }
}

export default new ReservationUtils();

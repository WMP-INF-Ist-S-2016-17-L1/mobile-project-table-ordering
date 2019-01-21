import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { NewReservation } from "../models";
import { ReservationResponse } from "../models/reservation-response";
import { EnvironmentService } from "./environment.service";

@Injectable({
  providedIn: "root"
})
export class ReservationService {

  private API_URL: string;
  private readonly RESERVATIONS_PATH = "reservationRequests";

  constructor(private http: HttpClient, private environmentService: EnvironmentService) {
    this.API_URL = this.environmentService.getApiUrl();
  }

  sendReservation(reservationData: NewReservation): Observable<any> {
    return this.http.post<any>(`${this.API_URL}/api/client/sendReservationRequest`, reservationData);
  }

  getClientReservations(): Observable<Array<ReservationResponse>> {
    const httpParams = {
      projection: "withRestaurant",
      sort: "createdDate,desc"
    };

    return this.http.get(
      `${this.API_URL}/api/${this.RESERVATIONS_PATH}/search/findByCurrentClient`,
      { params: httpParams }
    )
    .pipe(
      map((response: any) => {
        const reservations: Array<ReservationResponse> = response._embedded && response._embedded.reservationRequests;

        return reservations || [];
      })
    );
  }
}

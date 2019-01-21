import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { VenueDetails } from "../models/venue-details";
import { EnvironmentService } from "./environment.service";

@Injectable({
  providedIn: "root"
})
export class VenuesService {

  private API_URL: string;

  private queryResource = new Subject<string>();
  // tslint:disable-next-line:member-ordering
  userVenueQuery = this.queryResource.asObservable();

  constructor(private http: HttpClient, private environmentService: EnvironmentService) {
    this.API_URL = this.environmentService.getApiUrl();
  }

  setUserVenueQuery(query: string) {
    this.queryResource.next(query);
  }

  getVenues(venueDetails: VenueDetails): Observable<Array<VenueDetails>> {
    return this.http.post<Array<VenueDetails>>(`${this.API_URL}/api/venues/search`, venueDetails);
  }
}

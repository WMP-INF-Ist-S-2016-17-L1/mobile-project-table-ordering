import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Restaurant } from "../models";
import { EnvironmentService } from "./environment.service";

@Injectable({
  providedIn: "root"
})
export class RestaurantService {

  private API_URL: string;

  constructor(private http: HttpClient, private environmentService: EnvironmentService) {
    this.API_URL = this.environmentService.getApiUrl();
  }

  getRestaurantByApiId(apiId: string): Observable<Restaurant> {
    const httpParams = {
      apiId
    };

    return this.http.get<Restaurant>(
      `${this.API_URL}/api/activatedRestaurant/search/findByApiId`,
      { params: httpParams }
    );
  }
}

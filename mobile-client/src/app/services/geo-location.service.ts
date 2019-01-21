import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { enableLocationRequest, getCurrentLocation, isEnabled, Location } from "nativescript-geolocation";
import { Observable, Observer, Subject } from "rxjs";
import { map } from "rxjs/operators";
import { GeoLocation } from "../models";
import { Coordinates } from "../models/geo-location";

const API_KEY = "AIzaSyDeOn1yZdl6o6xUyv17VLAW1A0szWaRcMY";

@Injectable({
  providedIn: "root"
})
export class GeoLocationService {

  private locationResource = new Subject<Coordinates>();
  // tslint:disable-next-line:member-ordering
  selectedLocation = this.locationResource.asObservable();

  private currentLocationOptions = {
    desiredAccuracy: 3,
    updateDistance: 10,
    maximumAge: 20000,
    timeout: 20000
  };

  constructor(private http: HttpClient) { }

  enableLocationTap() {
    isEnabled().then((enabled: any) => {
      if (!enabled) {
        enableLocationRequest().then(() => {
          console.log("location enabled");
        }, this.onError);
      }
    }, this.onError);
  }

  onError = (error: any) => {
    console.log(`Error: ${error.message || error}`);
  }

  getUserCurrentLocation(): Observable<Location> {

    return Observable.create((observer: Observer<Location>) => {
      getCurrentLocation(this.currentLocationOptions)
        .then((loc) => {
          if (loc) {
            observer.next(loc);
            observer.complete();
          }
        }, this.onError);
    });
  }

  getLocationByAddress(address: string): Observable<Array<GeoLocation>> {
    return this.http.get<Array<GeoLocation>>(
      `https://maps.googleapis.com/maps/api/geocode/json?address=${address}&key=${API_KEY}`
    )
      .pipe(
        map(
          (response: any) => response.results || []
        )
      );
  }

  setSelectedLocationAsCurrent() {
    this.getUserCurrentLocation()
      .pipe(
        map((location: Location): Coordinates => {
          return {
            lat: location.latitude,
            lng: location.longitude
          };
        })
      )
      .subscribe(this.setSelectedCoordinates);
  }

  setSelectedCoordinates = (coordinates: Coordinates) => {
    this.locationResource.next(coordinates);
  }
}

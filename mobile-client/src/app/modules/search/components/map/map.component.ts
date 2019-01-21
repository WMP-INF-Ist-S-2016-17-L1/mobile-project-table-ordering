import { Component, OnDestroy, OnInit, ViewChild } from "@angular/core";
import { Router } from "@angular/router";
import { registerElement } from "nativescript-angular/element-registry";
import { Location } from "nativescript-geolocation";
import { MapView, Marker, Position } from "nativescript-google-maps-sdk";
import { of, Subscription } from "rxjs";
import { catchError, map, switchMap } from "rxjs/operators";
import { Coordinates, GoogleLocation } from "~/app/models";
import { VenueDetails } from "~/app/models/venue-details";
import { AuthService } from "~/app/services";
import { GeoLocationService } from "~/app/services/geo-location.service";
import { VenuesService } from "~/app/services/venues.service";
import { mapInfoWindowTemplates } from "~/app/utils/map-info-window.templates";

registerElement("MapView", () => MapView);

@Component({
  selector: "ns-map",
  templateUrl: "./map.component.html",
  styleUrls: ["./map.component.scss"],
  moduleId: module.id
})
export class MapComponent implements OnInit, OnDestroy {

  @ViewChild("MapView") mapView: MapView & { infoWindowTemplates: string };

  minZoom = 0;
  maxZoom = 22;
  bearing = 0;
  tilt = 0;
  padding = [40, 40, 40, 40];

  locationMarker: Marker = null;
  locationMarkerColor = "#7d26a5";
  venueMarkerColor = "#1245b2";
  venueRegisteredMarkerColor = "#0caf5b";
  venueMarkers: Array<Marker> = [];
  userLocation: GoogleLocation = {
    lat: null,
    lng: null,
    zoom: 12
  };

  private _searchQuerySubscription: Subscription;
  private _selectedLocationSubscription: Subscription;

  constructor(
    private authService: AuthService,
    private locationService: GeoLocationService,
    private venuesService: VenuesService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.locationService.enableLocationTap();
    this.searchVenuesByQuery();
    this.subscribeUserSelectedLocation();
  }

  ngOnDestroy(): void {
    this._searchQuerySubscription.unsubscribe();
    this._selectedLocationSubscription.unsubscribe();
  }

  searchVenuesByQuery = () => {
    this._searchQuerySubscription = this.venuesService.userVenueQuery
      .pipe(
        switchMap((query: string) => {
          const body: VenueDetails = {
            query,
            lat: this.userLocation.lat,
            lng: this.userLocation.lng
          };

          return this.venuesService.getVenues(body);
        }),
        map((data: Array<VenueDetails>) => {
          return data.map(this.getVenueMarker);
        }),
        catchError((error) => {
          console.log(error);

          return of([]);
        })
      )
      .subscribe(this.setVenueMarkers);
  }

  setVenueMarkers = (venueMarkers: Array<Marker>): void => {
    if (this.venueMarkers.length) {
      this.mapView.removeMarker(...this.venueMarkers);
    }
    this.venueMarkers = venueMarkers;
    this.mapView.addMarker(...venueMarkers);
  }

  getVenueMarker = (venueDetails: VenueDetails): Marker => {
    const marker = new Marker();
    marker.position = Position.positionFromLatLng(venueDetails.location.lat, venueDetails.location.lng);
    marker.title = venueDetails.name;
    marker.userData = { venueDetails };
    marker.color = venueDetails.isRegistered ? this.venueRegisteredMarkerColor : this.venueMarkerColor;
    marker.infoWindowTemplate = this.getVenueTemplateName(venueDetails);

    return marker;
  }

  getVenueTemplateName = (venueDetails: VenueDetails) => {
    if (venueDetails.isRegistered && this.authService.isLogged()) {
      return "registerTableTemplate";
    }
    if (venueDetails.isRegistered && !this.authService.isLogged()) {
      return "notLoggedTableTemplate";
    }

    return "venueTemplate";
  }

  // Map events
  onMapReady(event) {
    this.mapView = event.object;
    this.mapView.infoWindowTemplates = mapInfoWindowTemplates;

    this.locationService.getUserCurrentLocation()
      .subscribe(this.setLocationAndMarker);
  }

  setLocationAndMarker = (location: Location) => {
    this.setUserLocation(location);
    this.setSelectedLocationMarker(this.getMarker(location));
  }

  setUserLocation = (location: Location) => {
    this.userLocation = {
      lat: location.latitude,
      lng: location.longitude,
      zoom: 12
    };
  }

  setSelectedLocationMarker = (marker: Marker) => {
    if (this.locationMarker) {
      this.mapView.removeMarker(this.locationMarker);
    }
    this.locationMarker = marker;
    this.mapView.addMarker(marker);
  }

  getMarker(location: Location): Marker {
    const marker = new Marker();
    marker.position = Position.positionFromLatLng(location.latitude, location.longitude);
    marker.title = "Twoja lokalizacja";
    marker.color = this.locationMarkerColor;

    return marker;
  }

  onMarkerInfoWindowEvent(args) {
    const venueDetails = args.marker.userData && <VenueDetails>args.marker.userData.venueDetails;
    if (venueDetails && venueDetails.isRegistered && this.authService.isLogged()) {
      this.router.navigate(["/search/add-reservation",
        {
          restaurantApiId: venueDetails.id,
          restaurantName: venueDetails.name,
          restaurantAddress: venueDetails.location.address
        }
      ]);
    }
  }

  subscribeUserSelectedLocation = () => {
    this._selectedLocationSubscription = this.locationService.selectedLocation
      .pipe(
        map(
          (coordinates: Coordinates): Location => {
            return {
              latitude: coordinates.lat,
              longitude: coordinates.lng,
              altitude: null,
              direction: null,
              horizontalAccuracy: null,
              speed: null,
              timestamp: null,
              verticalAccuracy: null
            };
          }
        )
      )
      .subscribe(this.setLocationAndMarker);
  }
}

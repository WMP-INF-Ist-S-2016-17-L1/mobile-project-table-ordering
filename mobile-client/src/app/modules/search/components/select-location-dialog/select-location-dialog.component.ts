import { Component, OnDestroy, OnInit, ViewChild } from "@angular/core";
import { ModalDialogParams } from "nativescript-angular/modal-dialog";
import { Subject, Subscription } from "rxjs";
import { debounceTime, distinctUntilChanged, switchMap } from "rxjs/operators";
import { EventData } from "tns-core-modules/data/observable";
import { ListView } from "tns-core-modules/ui/list-view";
import { TextField } from "tns-core-modules/ui/text-field/text-field";
import { GeoLocation } from "~/app/models";
import { GeoLocationService } from "~/app/services";

const API_KEY = "AIzaSyDeOn1yZdl6o6xUyv17VLAW1A0szWaRcMY";

@Component({
  selector: "ns-select-location-dialog",
  templateUrl: "./select-location-dialog.component.html",
  styleUrls: ["./select-location-dialog.component.scss"],
  moduleId: module.id
})
export class SelectLocationDialogComponent implements OnInit, OnDestroy {

  searchInput = new Subject<string>();
  items: Array<GeoLocation> = [];
  @ViewChild("placesList") placesList: ListView;
  private _subscription: Subscription;

  constructor(private params: ModalDialogParams, private geoLocationService: GeoLocationService) { }

  ngOnInit() {
    this.searchPlaces();
  }

  ngOnDestroy(): void {
    this._subscription.unsubscribe();
  }

  searchPlaces = () => {
    this._subscription = this.searchInput
      .pipe(
        debounceTime(500),
        distinctUntilChanged(),
        switchMap((params: string) => {
          return this.geoLocationService.getLocationByAddress(params);
        })
      )
      .subscribe(this.setLocations, this.onError);
  }

  setLocations = (places: Array<GeoLocation>) => {
    this.items = places;
    this.placesList.items = this.items;
  }

  onError = (error: any) => {
    console.log(error);
  }

  onTextChange(args: EventData) {
    const textField = <TextField>args.object;
    this.searchInput.next(textField.text);
  }

  listViewItemTap = (args: any) => {
    const geoLocation: GeoLocation = this.items[args.index];

    this.geoLocationService.setSelectedCoordinates(geoLocation.geometry.location);
  }

  currentLocationButtonTap() {
    this.geoLocationService.setSelectedLocationAsCurrent();
  }

  closeDialog(result: string) {
    this.params.closeCallback(result);
  }
}

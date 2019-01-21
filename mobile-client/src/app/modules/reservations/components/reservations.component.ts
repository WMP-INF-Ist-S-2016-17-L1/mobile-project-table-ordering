import { Component, OnInit, ViewContainerRef } from "@angular/core";
import { registerElement } from "nativescript-angular/element-registry";
import { ModalDialogOptions, ModalDialogService } from "nativescript-angular/modal-dialog";
import { CardView } from "nativescript-cardview";
import { RadSideDrawer } from "nativescript-ui-sidedrawer";
import * as app from "tns-core-modules/application";
import { ReservationResponse, Restaurant, StatusToDisplay } from "~/app/models";
import { ReservationService } from "~/app/services";
import ReservationUtils from "~/app/utils/reservation-utils";
import { DialogComponent } from "./dialog/dialog.component";
registerElement("CardView", () => CardView);
registerElement("PullToRefresh", () => require("nativescript-pulltorefresh").PullToRefresh);

@Component({
  selector: "ns-reservations",
  templateUrl: "./reservations.component.html",
  styleUrls: ["./reservations.component.scss"],
  moduleId: module.id
})

export class ReservationsComponent implements OnInit {

  userReservations: Array<ReservationResponse> = [];

  constructor(
    private reservationService: ReservationService,
    private modalService: ModalDialogService,
    private viewContainerRef: ViewContainerRef
  ) { }

  ngOnInit() {
    this.fetchUserReservations();
  }

  fetchUserReservations() {
    this.reservationService.getClientReservations()
      .subscribe((reservations: Array<ReservationResponse>) => {
        this.userReservations = reservations;
      });
  }

  refreshReservations(args: any) {
    const pullRefresh = args.object;
    setTimeout(() => {
      this.fetchUserReservations();
      pullRefresh.refreshing = false;
    }, 1000);
  }

  getCardName(restaurant: Restaurant): string {
    return `${restaurant.name}, ${restaurant.city}, ${restaurant.street}`;
  }

  getUserReservationMessage(reservation: ReservationResponse): string {
    return `Twoja wiadomość: ${reservation.clientMessage || "Brak"}`;
  }

  getReservationResponseMessage(reservation: ReservationResponse): string {
    return `Odpowiedź restauracji: ${reservation.restaurateurMessage || "Brak"}`;
  }

  onDrawerButtonTap(): void {
    const sideDrawer = <RadSideDrawer>app.getRootView();
    sideDrawer.showDrawer();
  }

  getStatusToDisplay(reservation: ReservationResponse): StatusToDisplay {
    return ReservationUtils.getStatus(reservation);
  }

  openReservationDialog(reservation: ReservationResponse) {
    const options: ModalDialogOptions = {
      context: { reservation },
      viewContainerRef: this.viewContainerRef
    };

    this.modalService.showModal(DialogComponent, options);
  }

}

import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { RouterExtensions } from "nativescript-angular/router";
import * as ModalDatePicker from "nativescript-modal-datetimepicker";
import * as Toast from "nativescript-toast";
import { TextField } from "tns-core-modules/ui/text-field/text-field";
import { NewReservation, ReservationRequest, Restaurant } from "~/app/models";
import { ReservationService } from "~/app/services";
import DateUtils from "~/app/utils/date-utils";

@Component({
  selector: "ns-add-reservation",
  templateUrl: "./add-reservation.component.html",
  styleUrls: ["./add-reservation.component.scss"],
  moduleId: module.id
})
export class AddReservationComponent implements OnInit {

  currentRestaurant: Restaurant;
  reservation: ReservationRequest = {
    id: null,
    message: null,
    dateAndTime: null,
    numberOfPersons: null,
    date: null,
    time: null
  };

  constructor(
    private activeRoute: ActivatedRoute,
    private reservationService: ReservationService,
    private routerExtensions: RouterExtensions
  ) { }

  ngOnInit() {
    const queryParamMap = this.activeRoute.snapshot.paramMap;

    this.currentRestaurant = {
      apiId: queryParamMap.get("restaurantApiId"),
      city: null,
      name: queryParamMap.get("restaurantName"),
      street: queryParamMap.get("restaurantAddress")
    };
  }

  goBack() {
    this.routerExtensions.backToPreviousPage();
  }

  onTextChange(args) {
    const textField = <TextField>args.object;

    this.reservation[textField.id] = textField.text;
  }

  onTextReturn(args) {
    const textField = <TextField>args.object;

    this.reservation[textField.id] = textField.text;
  }

  getPageTitle() {
    return `Rezerwacja w - ${this.currentRestaurant.name}, ${this.currentRestaurant.street}`;
  }

  submitReservation() {
    const dateTime = DateUtils.getDateTime(this.reservation.date, this.reservation.time);

    const newReservation: NewReservation = {
      dateAndTime: dateTime,
      restaurantApiId: this.currentRestaurant.apiId,
      numberOfPersons: this.reservation.numberOfPersons,
      clientMessage: this.reservation.message
    };

    this.reservationService.sendReservation(newReservation)
      .subscribe((response: any) => {
        Toast.makeText("Dokonano rezerwacji").show();
        this.routerExtensions.navigateByUrl("/reservations");
      });
  }

  pickDate() {
    const picker = new ModalDatePicker.ModalDatetimepicker();
    picker.pickDate({
      title: "Data rezerwacji",
      theme: "dark",
      minDate: new Date(),
      is24HourView: false
    }).then((result: ModalDatePicker.DateResponse) => {
      const { day, month, year } = result;
      this.reservation.date = new Date(year, month, day);
    }).catch((error) => {
      console.log("Error: " + error);
    });
  }

  pickTime() {
    const picker = new ModalDatePicker.ModalDatetimepicker();
    picker.pickTime({
      is24HourView: true,
      theme: "dark",
      title: "Godzina rezerwacji"
    })
    .then((result: ModalDatePicker.TimeResponse) => {
      const { minute, hour } = result;
      this.reservation.time = new Date(0, 0, 0, hour, minute, 0, 0);
    });
  }

  isFormValid(): boolean {
    const { numberOfPersons, date, time } = this.reservation;

    return numberOfPersons > 0 && !!date && !!time;
  }
}

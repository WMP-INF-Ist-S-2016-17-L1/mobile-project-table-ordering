import { Component } from "@angular/core";
import { ModalDialogParams } from "nativescript-angular/modal-dialog";
import { ReservationResponse } from "~/app/models";

@Component({
  selector: "ns-dialog",
  templateUrl: "./dialog.component.html",
  styleUrls: ["./dialog.component.scss"],
  moduleId: module.id
})

export class DialogComponent {

  reservation: ReservationResponse;

  constructor(private params: ModalDialogParams) {
    this.reservation = params.context.reservation;
  }

  close(result: string) {
    this.params.closeCallback(result);
  }

}

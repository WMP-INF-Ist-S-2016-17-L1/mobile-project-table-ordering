import { NgModule, NO_ERRORS_SCHEMA } from "@angular/core";
import { NativeScriptCommonModule } from "nativescript-angular/common";
import { DialogComponent } from "~/app/modules/reservations/components/dialog/dialog.component";
import { ReservationsComponent } from "~/app/modules/reservations/components/reservations.component";
import { ReservationsRoutingModule } from "~/app/modules/reservations/reservations-routing.module";
import { ReservationService } from "~/app/services";

@NgModule({
  declarations: [
    ReservationsComponent,
    DialogComponent
  ],
  imports: [
    ReservationsRoutingModule,
    NativeScriptCommonModule
  ],
  providers: [
    ReservationService
  ],
  entryComponents: [
    DialogComponent
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class ReservationsModule { }

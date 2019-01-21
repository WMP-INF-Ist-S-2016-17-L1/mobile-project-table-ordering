import { NgModule } from "@angular/core";
import { Routes } from "@angular/router";
import { NativeScriptRouterModule } from "nativescript-angular/router";
import { ReservationsComponent } from "~/app/modules/reservations/components/reservations.component";

const routes: Routes = [
  { path: "", component: ReservationsComponent }
];

@NgModule({
  imports: [NativeScriptRouterModule.forChild(routes)],
  exports: [NativeScriptRouterModule]
})
export class ReservationsRoutingModule { }

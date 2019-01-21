import { NgModule } from "@angular/core";
import { Routes } from "@angular/router";
import { NativeScriptRouterModule } from "nativescript-angular/router";
import { AddReservationComponent } from "./components";
import { SearchComponent } from "./search.component";

const routes: Routes = [
    { path: "", component: SearchComponent },
    { path: "add-reservation", component: AddReservationComponent }
];

@NgModule({
    imports: [NativeScriptRouterModule.forChild(routes)],
    exports: [NativeScriptRouterModule]
})
export class SearchRoutingModule { }

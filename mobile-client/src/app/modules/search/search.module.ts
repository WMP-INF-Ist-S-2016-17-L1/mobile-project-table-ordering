import { NgModule, NO_ERRORS_SCHEMA } from "@angular/core";
import { NativeScriptCommonModule } from "nativescript-angular/common";
import { GeoLocationService, ReservationService, RestaurantService, VenuesService } from "~/app/services";
import { AddReservationComponent, MapComponent, SelectLocationDialogComponent } from "./components";
import { SearchRoutingModule } from "./search-routing.module";
import { SearchComponent } from "./search.component";

@NgModule({
    imports: [
        NativeScriptCommonModule,
        SearchRoutingModule
    ],
    declarations: [
        SearchComponent,
        MapComponent,
        AddReservationComponent,
        SelectLocationDialogComponent
    ],
    providers: [
        GeoLocationService,
        VenuesService,
        RestaurantService,
        ReservationService
    ],
    schemas: [
        NO_ERRORS_SCHEMA
    ],
    entryComponents: [
        SelectLocationDialogComponent
    ]
})
export class SearchModule { }

import { NgModule } from "@angular/core";
import { Routes } from "@angular/router";
import { NativeScriptRouterModule } from "nativescript-angular/router";

const routes: Routes = [
    { path: "", redirectTo: "/search", pathMatch: "full" },
    { path: "login", loadChildren: "~/app/modules/login/login.module#LoginModule" },
    { path: "register", loadChildren: "~/app/modules/register/register.module#RegisterModule" },
    { path: "search", loadChildren: "~/app/modules/search/search.module#SearchModule" },
    { path: "reservations", loadChildren: "~/app/modules/reservations/reservations.module#ReservationsModule" }
];

@NgModule({
    imports: [NativeScriptRouterModule.forRoot(routes)],
    exports: [NativeScriptRouterModule]
})
export class AppRoutingModule { }

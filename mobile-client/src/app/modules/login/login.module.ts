import { NgModule, NO_ERRORS_SCHEMA } from "@angular/core";
import { NativeScriptCommonModule } from "nativescript-angular/common";
import { HomeRoutingModule } from "./login-routing.module";
import { LoginComponent } from "./login.component";

@NgModule({
    imports: [
        NativeScriptCommonModule,
        HomeRoutingModule
    ],
    declarations: [
        LoginComponent
    ],
    schemas: [
        NO_ERRORS_SCHEMA
    ]
})
export class LoginModule { }

import { registerLocaleData } from "@angular/common";
import { HTTP_INTERCEPTORS } from "@angular/common/http";
import localePl from "@angular/common/locales/pl";
import { LOCALE_ID, NgModule, NO_ERRORS_SCHEMA } from "@angular/core";
import { NativeScriptFormsModule } from "nativescript-angular/forms";
import { NativeScriptHttpClientModule } from "nativescript-angular/http-client";
import { NativeScriptModule } from "nativescript-angular/nativescript.module";
import { ModalDatetimepicker } from "nativescript-modal-datetimepicker";
import { NativeScriptUISideDrawerModule } from "nativescript-ui-sidedrawer/angular";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { RoleNamePipe } from "./directives/role-name.pipe";
import { AccessTokenInterceptor, HttpErrorInterceptor, RequestLoaderInterceptor } from "./interceptors";

registerLocaleData(localePl);

@NgModule({
    bootstrap: [
        AppComponent
    ],
    imports: [
        AppRoutingModule,
        NativeScriptModule,
        NativeScriptUISideDrawerModule,
        NativeScriptFormsModule,
        NativeScriptHttpClientModule
    ],
    declarations: [
        AppComponent,
        RoleNamePipe
    ],
    providers: [
        ModalDatetimepicker,
        { provide: LOCALE_ID, useValue: "pl-PL" },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AccessTokenInterceptor,
            multi: true
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: RequestLoaderInterceptor,
            multi: true
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: HttpErrorInterceptor,
            multi: true
        }
    ],
    schemas: [
        NO_ERRORS_SCHEMA
    ]
})
export class AppModule { }

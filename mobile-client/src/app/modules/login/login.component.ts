import { Component } from "@angular/core";
import { Router } from "@angular/router";
import * as Toast from "nativescript-toast";
import { RadSideDrawer } from "nativescript-ui-sidedrawer";
import * as app from "tns-core-modules/application/application";
import { EventData } from "tns-core-modules/ui/page/page";
import { TextField } from "tns-core-modules/ui/text-field/text-field";
import { UserCredentials } from "~/app/models";
import { AuthService } from "~/app/services/auth.service";

@Component({
    selector: "Login",
    moduleId: module.id,
    templateUrl: "./login.component.html"
})
export class LoginComponent {

    userLogin: string;
    userPassword: string;

    constructor(private authService: AuthService, private router: Router) {}

    onTextChange(args: any) {
        const textField = <TextField>args.object;

        this[textField.id] = textField.text;
    }

    onDrawerButtonTap(): void {
        const sideDrawer = <RadSideDrawer>app.getRootView();
        sideDrawer.showDrawer();
    }

    onButtonTap(args: EventData) {
        const userCredentials: UserCredentials = {
            username: this.userLogin,
            password: this.userPassword
        };

        this.authService.login(userCredentials)
            .subscribe(this.handleSuccessLogin, this.handleErrorLogin);
    }

    private handleSuccessLogin = (response: any) => {
        Toast.makeText("Zalogowano").show();
        this.router.navigateByUrl("/reservations");
    }

    private handleErrorLogin = (response: any) => {
        Toast.makeText("Podano nieprawidłowe dane!").show();
    }

}

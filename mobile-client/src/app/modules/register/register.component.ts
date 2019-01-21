import { Component } from "@angular/core";
import { Router } from "@angular/router";
import * as Toast from "nativescript-toast";
import { RadSideDrawer } from "nativescript-ui-sidedrawer";
import * as app from "tns-core-modules/application/application";
import { ListPicker } from "tns-core-modules/ui/list-picker";
import { EventData } from "tns-core-modules/ui/page/page";
import { TextField } from "tns-core-modules/ui/text-field/text-field";
import { UserCredentials } from "~/app/models";
import { AuthService } from "~/app/services/auth.service";

@Component({
  selector: "ns-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.scss"],
  moduleId: module.id
})

export class RegisterComponent {
  roles: Array<string> = [];
  picked: string;

  userLogin: string;
  userPassword: string;
  userPassword2: string;

  constructor(private authService: AuthService, private router: Router) {
    this.roles.push("klient");
    this.roles.push("restaurator");
  }
  isFormValid(): boolean {
    return !!this.userLogin && !!this.userPassword && !!this.userPassword2 && this.userPassword === this.userPassword2;
  }

  onButtonRegisterTap(args: EventData) {
    const userCredentials: UserCredentials = {
      username: this.userLogin,
      password: this.userPassword
    };
    if (this.picked === "klient") {
      this.authService.registerClient(userCredentials)
        .subscribe(this.handleSuccessRegister, this.handleErrorRegister);
    } else {
      this.authService.registerRestaurator(userCredentials)
        .subscribe(this.handleSuccessRegister, this.handleErrorRegister);
    }
  }

  selectedIndexChanged(args) {
    const picker = <ListPicker>args.object;
    this.picked = this.roles[picker.selectedIndex];
  }

  onTextChange(args: any) {
    const textField = <TextField>args.object;

    this[textField.id] = textField.text;
  }

  onDrawerButtonTap(): void {
    const sideDrawer = <RadSideDrawer>app.getRootView();
    sideDrawer.showDrawer();
  }

  private handleSuccessRegister = (response: any) => {
    Toast.makeText("Zarejestrowano").show();
    this.router.navigateByUrl("/login");
  }

  private handleErrorRegister = (error: any) => {
    const errorMessage = error.message || error || "Podano nieprawidłowe dane!";

    Toast.makeText(errorMessage).show();
  }
}

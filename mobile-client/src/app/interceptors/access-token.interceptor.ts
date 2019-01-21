import { HttpHandler } from "@angular/common/http/src/backend";
import { HttpInterceptor } from "@angular/common/http/src/interceptor";
import { HttpRequest } from "@angular/common/http/src/request";
import { Injectable } from "@angular/core";
import { JwtHelperService } from "@auth0/angular-jwt";
import * as Toast from "nativescript-toast";
import { throwError } from "rxjs";
import { AUTHORIZATION_HEADER, AuthService } from "../services/auth.service";

@Injectable({
  providedIn: "root"
})
export class AccessTokenInterceptor implements HttpInterceptor {

  private jwtHelper: JwtHelperService;

  constructor(private authService: AuthService) {
    this.jwtHelper = new JwtHelperService();
  }

  intercept(request: HttpRequest<any>, next: HttpHandler) {
    const authHeader = AUTHORIZATION_HEADER;
    const accessToken = this.authService.getAuthorization();

    if (accessToken) {
      if (this.jwtHelper.isTokenExpired(accessToken)) {
        return this.handleExpiredTokenError();
      }

      request = request.clone({
        headers: request.headers.set(authHeader, accessToken),
        withCredentials: false
      });
    }

    return next.handle(request);
  }

  handleExpiredTokenError = () => {
    Toast.makeText("Twoja sesja wygasła, zaloguj się ponownie");
    this.authService.logout();

    return throwError("session expired");
  }
}

import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import * as Toast from "nativescript-toast";
import { Observable, throwError } from "rxjs";
import { catchError, retry } from "rxjs/operators";

@Injectable({
  providedIn: "root"
})
export class HttpErrorInterceptor implements HttpInterceptor {
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request)
      .pipe(
        retry(1),
        catchError((error: HttpErrorResponse) => {
          Toast.makeText(`Błąd: ${error.statusText}`).show();

          if (error.error) {
            return throwError(`Error: ${error.error.message}`);
          } else {
            return throwError(`Error Code: ${error.status}\nMessage: ${error.message}`);
          }
        })
      );
  }
}

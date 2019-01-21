import { HttpEvent, HttpResponse } from "@angular/common/http";
import { HttpHandler } from "@angular/common/http/src/backend";
import { HttpInterceptor } from "@angular/common/http/src/interceptor";
import { HttpRequest } from "@angular/common/http/src/request";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { tap } from "rxjs/operators";
import { RequestProgressService } from "../services/request-progress.service";

@Injectable({
    providedIn: "root"
})
export class RequestLoaderInterceptor implements HttpInterceptor {

    constructor(private requestProgressService: RequestProgressService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        this.showLoader();

        return next.handle(req)
            .pipe(
                tap(this.onSuccess, this.onError)
            );
    }

    private onSuccess = (event: HttpEvent<any>) => {
        if (event instanceof HttpResponse) {
            this.onEnd();
        }
    }

    private onError = (error: any) => {
        this.onEnd();
    }

    private onEnd(): void {
        this.hideLoader();
    }

    private showLoader(): void {
        this.requestProgressService.show();
    }
    private hideLoader(): void {
        this.requestProgressService.hide();
    }
}

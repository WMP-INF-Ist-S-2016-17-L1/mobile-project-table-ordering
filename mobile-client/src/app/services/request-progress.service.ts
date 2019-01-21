import { Injectable } from "@angular/core";
import { Subject } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class RequestProgressService {

  private loaderResource = new Subject<boolean>();
  // tslint:disable-next-line:member-ordering
  loader = this.loaderResource.asObservable();

  constructor() {
    //
  }

  show() {
    this.loaderResource.next(true);
  }
  hide() {
    this.loaderResource.next(false);
  }
}

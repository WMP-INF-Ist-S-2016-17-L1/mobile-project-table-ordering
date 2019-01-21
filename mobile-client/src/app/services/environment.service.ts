import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root"
})
export class EnvironmentService {

  private readonly API_URL = "http://0241a3eb.ngrok.io";

  getApiUrl(): string {
    return this.API_URL;
  }
}

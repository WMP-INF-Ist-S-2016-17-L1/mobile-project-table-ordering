import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { tap } from "rxjs/operators";
import { getString, hasKey, remove, setString } from "tns-core-modules/application-settings/application-settings";
import { AccountInfo, UserCredentials } from "~/app/models";
import { EnvironmentService } from "./environment.service";

export const AUTHORIZATION_HEADER = "Authorization";
const AUTHORIZATION_KEY = "authorization";
const USERNAME_KEY = "username";
const ROLE_KEY = "role";

@Injectable({
  providedIn: "root"
})
export class AuthService {

  private API_URL: string;

  constructor(private http: HttpClient, private environmentService: EnvironmentService) {
    this.API_URL = this.environmentService.getApiUrl();
  }

  isLogged() {
    return hasKey(AUTHORIZATION_KEY);
  }

  logout() {
    remove(AUTHORIZATION_KEY);
    remove(USERNAME_KEY);
    remove(ROLE_KEY);
  }

  getAccountInfo(): AccountInfo {
    return {
      username: getString(USERNAME_KEY),
      role: getString(ROLE_KEY)
    };
  }

  getAuthorization(): string {
    return getString(AUTHORIZATION_KEY);
  }

  login(userCredentials: UserCredentials): Observable<any> {

    return this.http
      .post<AccountInfo>(`${this.API_URL}/api/login`, userCredentials, { observe: "response" })
      .pipe(
        tap((response: HttpResponse<AccountInfo>) => {
          const token = response.headers.get(AUTHORIZATION_HEADER);
          this.storeAuthorization(token);

          const body = response.body;
          this.storeAccountInfo(body);
        })
      );
  }

  registerClient(userCredentials: UserCredentials): Observable<any> {
    return this.http
      .post(`${this.API_URL}/api/users/signupClient`, userCredentials);
  }

  registerRestaurator(userCredentials: UserCredentials): Observable<any> {
    return this.http
      .post(`${this.API_URL}/api/users/signupRestaurateur`, userCredentials);
  }

  private storeAuthorization(authToken: string) {
    setString(AUTHORIZATION_KEY, authToken);
  }

  private storeAccountInfo(accountInfo: AccountInfo) {
    setString(USERNAME_KEY, accountInfo.username);
    setString(ROLE_KEY, accountInfo.role.toString());
  }
}

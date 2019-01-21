export interface AccountInfo {
  username: string;
  role: Role | string;
}

export enum Role {
  ADMIN = "ROLE_ADMIN",
  CLIENT = "ROLE_CLIENT",
  RESTAURATEUR = "ROLE_RESTAURATEUR"
}

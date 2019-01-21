export interface User {
  id: number;
  username: string;
  enabled: boolean;
}

export interface UserCredentials {
  username: string;
  password: string;
}

export interface UserRegistrationDetails extends UserCredentials {
  email: string;
  userType: UserType;
}

export enum UserType {
  CLIENT = 'CLIENT',
  RESTAURATEUR = 'RESTAURATEUR'
}

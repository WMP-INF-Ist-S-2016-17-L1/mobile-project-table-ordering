import { Restaurant } from "~/app/models/restaurant";

export interface ReservationResponse {
  id: number;
  numberOfPersons: number;
  reservationDateTime: Date;
  createdDate: Date;
  clientMessage: string;
  restaurateurMessage: string;
  status: ReservationStatus;
  active: boolean;
  restaurant?: Restaurant;
  notifications: Array<any>;
}

export enum ReservationStatus {
  SEND = "SEND",
  ACCEPTED_BY_RESTAURANT = "ACCEPTED_BY_RESTAURANT",
  ACCEPTED_BY_CLIENT = "ACCEPTED_BY_CLIENT",
  REJECTED_BY_CLIENT = "REJECTED_BY_CLIENT",
  REJECTED_BY_RESTAURANT = "REJECTED_BY_RESTAURANT"
}

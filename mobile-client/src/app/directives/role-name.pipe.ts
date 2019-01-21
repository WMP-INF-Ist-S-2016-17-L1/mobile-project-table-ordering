import { Pipe, PipeTransform } from "@angular/core";
import { Role } from "~/app/models";

@Pipe({
  name: "rolename"
})
export class RoleNamePipe implements PipeTransform {

  transform(roleName: Role) {
    switch (roleName) {
      case Role.ADMIN:
        return "Administrator";

      case Role.CLIENT:
        return "Użytkownik";

      case Role.RESTAURATEUR:
        return "Restaurator";
    }
  }

}

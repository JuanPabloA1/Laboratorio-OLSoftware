import { Area } from "./area.model";
import { Role } from "./role.model";

export interface Usuario {
  typeIdentity?: string,
  numberIdentity?: string | number,
  firstName?: string,
  secondName?: string,
  firstLastName?: string,
  secondLastName?: string,
  email?: string,
  user?: string,
  pass?: string,
  phone?: string | number,
  role?: Role,
  area?: Area
}

import { Area } from "./area.model";
import { Fabricante } from "./fabricante.model";
import { Modelo } from "./modelo.model";

export interface Dispositivo {
  deviceId: string | number,
  name: string,
  area: Area,
  stateDevice: string,
  typeDevice: string,
  factory: Fabricante,
  model: Modelo,
  numberSerie: string,
}

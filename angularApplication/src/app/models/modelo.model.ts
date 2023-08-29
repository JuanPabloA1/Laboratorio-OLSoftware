import { Fabricante } from "./fabricante.model";

export interface Modelo {
  modelId: string | number,
  name: string,
  factory: Fabricante
}

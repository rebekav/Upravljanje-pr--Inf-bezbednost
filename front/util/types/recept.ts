import { PregledResDTO } from "./pregled";

export type ReceptResDTO = {
  id: number;
  naziv: string;
  overen: boolean;
  napomena: string;
  pregled: PregledResDTO;
};

export type ReceptReqDTO = {
  naziv: string;
  napomena: string;
};

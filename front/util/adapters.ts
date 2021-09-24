import { PregledResDTO } from "./types/pregled";

export function PregledResDTOToEvent(p: PregledResDTO) {
  return {
    allDay: false,
    title: p.pacijent,
    start: new Date(p.vreme),
    end: new Date(new Date(p.vreme).getTime() + p.trajanje * 60 * 1000),
    resource: p,
  };
}

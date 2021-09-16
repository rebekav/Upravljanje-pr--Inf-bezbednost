import { Session } from "next-auth";
import useSWR from "swr";
import { PregledLightDTO, PregledReqDTO } from "../types/pregled";
import { ReceptReqDTO } from "../types/recept";
import { zdravstveniKartonDTOReq } from "../types/zdravstveniKarton";
import { fetcher, URL } from "./base";

export const zapociPregled = (
  session: Session,
  data: PregledReqDTO,
  id: number
) => {
  return fetcher(session, URL + `/pregled/zapocni/${id}`, {
    method: "POST",
    body: JSON.stringify(data),
  });
};

export const getPregled = (
  session: Session,
  id: string | string[] | undefined
) => {
  const { data, error, mutate } = useSWR<PregledLightDTO>(
    URL + `/pregled/${id}`,
    (url) => fetcher(session, url)
  );
  return {
    data,
    isLoading: !data && !error,
    error: error,
    mutate,
  };
};

export const addRecept = (session: Session, id: number, data: ReceptReqDTO) => {
  return fetcher(session, URL + `/pregled/${id}/recepti`, {
    method: "POST",
    body: JSON.stringify(data),
  });
};

export const zavrsiPregled = (
  session: Session,
  data: zdravstveniKartonDTOReq
) => {
  return fetcher(session, URL + `/karton/beleska`, {
    method: "POST",
    body: JSON.stringify(data),
  });
};

import { Session } from "next-auth";
import useSWR from "swr";
import { KlinikaDtoReq, KlinikaDtoRes } from "../types/klinika";
import { fetcher, URL } from "./base";

export const getKlinike = (session: Session) => {
  const { data, error, mutate } = useSWR<[KlinikaDtoRes]>(
    URL + `/klinike`,
    (url) => fetcher(session, url)
  );
  return {
    data,
    isLoading: !data && !error,
    error: error,
    mutate,
  };
};

export const createKlinika = (session: Session, data: KlinikaDtoReq) => {
  return fetcher(session, URL + `/klinike`, {
    method: "POST",
    body: JSON.stringify(data),
  });
};

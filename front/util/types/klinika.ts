export type KlinikaDtoRes = {
  id: number;
  adresa: string;
  naziv: string;
  opis: string;
};

export type KlinikaDtoReq = Omit<KlinikaDtoRes, "id">;

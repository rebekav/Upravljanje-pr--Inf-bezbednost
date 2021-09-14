import { Button } from "@chakra-ui/button";
import { Tr, Td } from "@chakra-ui/table";
import { Tag } from "@chakra-ui/tag";
import { Session } from "next-auth";
import { useState } from "react";
import { KeyedMutator } from "swr/dist/types";
import { banKorisnik, unBanKorisnik } from "../../util/api/korisnik";
import { KorisnikResDTO } from "../../util/types/korisnik";
interface KorisnikRowProps {
  k: KorisnikResDTO;
  mutate: KeyedMutator<[KorisnikResDTO]>;
  session: Session;
}

export function KorisnikRow(props: KorisnikRowProps) {
  const { k, mutate } = props;
  const [loadingButton, setLoadingButton] = useState(false);
  return (
    <Tr key={k.id}>
      <Td>{k.id}</Td>
      <Td>{k.ime}</Td>
      <Td>{k.prezime}</Td>
      <Td>{k.email}</Td>
      <Td>{k.roles.join(", ")}</Td>
      <Td>
        {k.validiran ? (
          <Tag colorScheme="green" fontWeight="bold">
            JESTE
          </Tag>
        ) : (
          <Tag colorScheme="red" fontWeight="bold">
            NIJE
          </Tag>
        )}
      </Td>
      <Td w="44">
        {k.validiran ? (
          <Button
            isLoading={loadingButton}
            colorScheme="red"
            onClick={() => {
              setLoadingButton(true);
              banKorisnik(props.session, k.id)
                .then((data) => {
                  return mutate();
                })
                .then((v) => {
                  setLoadingButton(false);
                })
                .catch((err) => {
                  setLoadingButton(false);
                });
            }}
          >
            Deaktiviraj
          </Button>
        ) : (
          <Button
            isLoading={loadingButton}
            colorScheme="green"
            onClick={() => {
              setLoadingButton(true);
              unBanKorisnik(props.session, k.id)
                .then((data) => {
                  return mutate();
                })
                .then((v) => {
                  setLoadingButton(false);
                })
                .catch((err) => {
                  setLoadingButton(false);
                });
            }}
          >
            Aktiviraj
          </Button>
        )}
      </Td>
    </Tr>
  );
}

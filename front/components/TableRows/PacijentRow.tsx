import { Button } from "@chakra-ui/button";
import { Tr, Td } from "@chakra-ui/table";
import { Router, useRouter } from "next/dist/client/router";
import { KorisnikResDTO } from "../../util/types/korisnik";
interface KorisnikRowProps {
  k: KorisnikResDTO;
}

export function PacijentRow(props: KorisnikRowProps) {
  const router = useRouter();
  const { k } = props;
  return (
    <Tr key={k.id}>
      <Td>{k.id}</Td>
      <Td>{k.ime}</Td>
      <Td>{k.prezime}</Td>
      <Td>{k.email}</Td>
    </Tr>
  );
}

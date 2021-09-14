import { Tr, Td } from "@chakra-ui/table";
import { KorisnikResDTO } from "../../util/types/korisnik";
interface KorisnikRowProps {
  k: KorisnikResDTO;
}

export function PacijentRow(props: KorisnikRowProps) {
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

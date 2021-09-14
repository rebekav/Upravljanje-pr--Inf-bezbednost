import { Button } from "@chakra-ui/button";
import { Tr, Td } from "@chakra-ui/table";
import { Tag } from "@chakra-ui/tag";
import { Session } from "next-auth";
import { useState } from "react";
import { KeyedMutator } from "swr/dist/types";
import { overiRecept } from "../../util/api/sestra";
import { ReceptResDTO } from "../../util/types/recept";
interface ReceptProps {
  k: ReceptResDTO;
  mutate: KeyedMutator<[ReceptResDTO]>;
  session: Session;
}

export function ReceptRow(props: ReceptProps) {
  const { k, mutate } = props;
  const [loadingButton, setLoadingButton] = useState(false);
  return (
    <Tr key={k.id}>
      <Td>{k.naziv}</Td>
      <Td>{k.pregled.pacijent}</Td>
      <Td>{k.pregled.lekar}</Td>
      <Td>{k.pregled.podaciOPregledu}</Td>
      <Td>{k.pregled.vreme}</Td>
      <Td>
        {k.overen ? (
          <Tag colorScheme="green" fontWeight="bold">
            JESTE
          </Tag>
        ) : (
          <Tag colorScheme="red" fontWeight="bold">
            NIJE
          </Tag>
        )}
      </Td>
      <Td>
        {!k.overen && (
          <Button
            isLoading={loadingButton}
            colorScheme="green"
            onClick={() => {
              setLoadingButton(true);
              overiRecept(props.session, k.id)
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
            Overi
          </Button>
        )}
      </Td>
    </Tr>
  );
}

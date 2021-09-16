import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalBody,
  ModalCloseButton,
  ModalFooter,
} from "@chakra-ui/modal";
import { Button } from "@chakra-ui/button";
import { Input, Text, Select, Textarea } from "@chakra-ui/react";
import { Box, Stack } from "@chakra-ui/layout";
import { useState } from "react";
import { getUsluge, getSestre } from "../../util/api/klinika";
import { Session } from "next-auth";
import { PregledReqDTO } from "../../util/types/pregled";
interface ZapoceniPregledProps {
  isOpen: boolean;
  onClose: () => void;
  onAction: (data: PregledReqDTO) => Promise<any>;
  session: Session;
}

export function ZapoceniPregled(props: ZapoceniPregledProps) {
  const { data: sestre, isLoading } = getSestre(props.session);
  const {
    data: usluge,
    isLoading: isLoadingUsluge,
    error: errorUsluge,
  } = getUsluge(props.session);
  const [data, setData] = useState<PregledReqDTO>({
    lekarId: props.session.user.id,
    medicinskaSestraId: sestre ? sestre[0].id : 0,
    podaciOPregledu: "",
    popust: 0,
    trajanje: 0,
    uslugaId: usluge ? usluge[0].id : 0,
    vreme: new Date(),
  });
  return (
    <Modal isOpen={props.isOpen} onClose={props.onClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Zapocni pregled</ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          <Stack spacing={3}>
            <Box>
              <Text pb="2">Medicinska Sestra:</Text>
              <Select
                placeholder="Izaberite medicinsku sestru"
                value={data["medicinskaSestraId"]}
                onChange={(e) =>
                  setData({
                    ...data,
                    medicinskaSestraId: Number(e.target.value),
                  })
                }
              >
                {!isLoading &&
                  sestre?.map((k) => (
                    <option key={k.id} value={k.id}>
                      {k.naziv}
                    </option>
                  ))}
              </Select>
            </Box>
            <Stack spacing={3}>
              <Box>
                <Text pb="2">Usloga:</Text>
                <Select
                  placeholder="Izaberite uslugu"
                  value={data["uslugaId"]}
                  onChange={(e) =>
                    setData({ ...data, uslugaId: Number(e.target.value) })
                  }
                >
                  {!isLoading &&
                    usluge?.map((k) => (
                      <option key={k.id} value={k.id}>
                        {k.naziv}
                      </option>
                    ))}
                </Select>
              </Box>
              <Box>
                <Text pb="2">Podaci o pregledu:</Text>
                <Textarea
                  resize="vertical"
                  value={data["podaciOPregledu"]}
                  onChange={(e) =>
                    setData({ ...data, podaciOPregledu: e.target.value })
                  }
                ></Textarea>
              </Box>
              <Box>
                <Text pb="2">Trajanje:</Text>
                <Input
                  type="number"
                  placeholder="Trajanje:"
                  value={data["trajanje"]}
                  onChange={(e) =>
                    setData({ ...data, trajanje: Number(e.target.value) })
                  }
                ></Input>
              </Box>
              <Box>
                <Text pb="2">Popust:</Text>
                <Input
                  type="number"
                  value={data["popust"]}
                  onChange={(e) =>
                    setData({ ...data, popust: Number(e.target.value) })
                  }
                ></Input>
              </Box>
            </Stack>
          </Stack>
        </ModalBody>

        <ModalFooter>
          <Button colorScheme="blue" mr={3} onClick={props.onClose}>
            Zatvori
          </Button>
          <Button
            variant="ghost"
            onClick={() => {
              props.onAction(data).then((d) => {
                setData({
                  lekarId: props.session.user.id,
                  medicinskaSestraId: 1,
                  podaciOPregledu: "",
                  popust: 0,
                  trajanje: 0,
                  uslugaId: 1,
                  vreme: new Date().toString(),
                });
                props.onClose();
              });
            }}
          >
            Zapocni
          </Button>
        </ModalFooter>
      </ModalContent>
    </Modal>
  );
}

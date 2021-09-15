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
import { Input, Text, Textarea } from "@chakra-ui/react";
import { Box, Stack } from "@chakra-ui/layout";
import { createKlinika } from "../../util/api/klinika";
import { useState } from "react";
import { KlinikaDtoReq } from "../../util/types/klinika";
import { Session } from "next-auth";
import { ReceptReqDTO } from "../../util/types/recept";
interface ReceptCreateProps {
  isOpen: boolean;
  onClose: () => void;
  onAction: (data: ReceptReqDTO) => Promise<any>;
}

export function ReceptCreate(props: ReceptCreateProps) {
  const [data, setData] = useState<ReceptReqDTO>({
    naziv: "",
    napomena: "",
  });
  return (
    <Modal isOpen={props.isOpen} onClose={props.onClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Dodaj Recept</ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          <Stack spacing={3}>
            <Box>
              <Text pb="2">Naziv:</Text>
              <Input
                placeholder="Naziv:"
                value={data["naziv"]}
                onChange={(e) => setData({ ...data, naziv: e.target.value })}
              ></Input>
            </Box>
            <Box>
              <Text pb="2">Opis:</Text>
              <Textarea
                placeholder="Napomena:"
                resize="none"
                rows={5}
                value={data["napomena"]}
                onChange={(e) => setData({ ...data, napomena: e.target.value })}
              ></Textarea>
            </Box>
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
                setData({ naziv: "", napomena: "" });
                props.onClose();
              });
            }}
          >
            Dodaj
          </Button>
        </ModalFooter>
      </ModalContent>
    </Modal>
  );
}

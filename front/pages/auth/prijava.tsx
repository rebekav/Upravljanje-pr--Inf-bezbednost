import { GetServerSideProps, NextPage } from "next";
import { csrfToken, getCsrfToken, signIn, useSession } from "next-auth/client";
import { useRouter } from "next/dist/client/router";
import { Divider, Input, Text } from "@chakra-ui/react";
import { Button } from "@chakra-ui/button";
import { Flex, Box, Stack } from "@chakra-ui/layout";
import { useState } from "react";
import { useToast } from "@chakra-ui/react";
import { passwordLess } from "../../util/api/korisnik";

interface PrijavaProps {
  csrfToken: string;
}

const Prijava: NextPage<PrijavaProps> = (props) => {
  const router = useRouter();
  const toast = useToast();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [session, loading] = useSession();
  if (loading) return <div>Loading...</div>;
  if (!loading && session) {
    router.push("/app");
    return <div>Redirecting ...</div>;
  }

  return (
    <Flex
      minHeight="100vh"
      minWidth="100vw"
      alignItems="center"
      justifyContent="center"
    >
      <Box
        p="4"
        w="80"
        bgColor="blackAlpha.50"
        borderRadius="md"
        boxShadow="md"
      >
        <Stack spacing={3}>
          <Box>
            <Text pb="2">Email:</Text>
            <Input
              placeholder="Email:"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            ></Input>
          </Box>
          <Box>
            <Text pb="2">Lozinka:</Text>
            <Input
              type="password"
              placeholder="Lozinka:"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            ></Input>
          </Box>
          <Flex justifyContent="center">
            <Button
              colorScheme="blue"
              onClick={(e) => {
                fetch("/api/auth/callback/username-password", {
                  method: "POST",
                  headers: {
                    "Content-Type": "application/json",
                  },
                  body: JSON.stringify({
                    username,
                    password,
                    csrfToken: props.csrfToken,
                  }),
                })
                  .then((data) => {
                    if (new URL(data.url).searchParams.get("error")) {
                      toast({
                        title: "Greska",
                        description: new URL(data.url).searchParams.get(
                          "error"
                        ),
                        status: "error",
                        duration: 5000,
                        isClosable: true,
                      });
                    } else router.reload();
                  })
                  .catch((err) => {
                    console.log(err);
                    toast({
                      title: "Greska",
                      description: "Greska prilikom logovanja",
                      status: "error",
                      duration: 5000,
                      isClosable: true,
                    });
                  });
              }}
            >
              Prijavi Se
            </Button>
          </Flex>
          <Divider />
          <Box>
            <Text pb="2">Email:</Text>
            <Input
              placeholder="Email:"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            ></Input>
          </Box>
          <Flex justifyContent="center">
            <Button
              colorScheme="blue"
              onClick={(e) => {
                passwordLess({ username: email })
                  .then((data) => {
                    console.log(data);
                    if (data.poruka != "Success") {
                      toast({
                        title: "Greska",
                        description: "Losa email-a adresa",
                        status: "error",
                        duration: 5000,
                        isClosable: true,
                      });
                    } else {
                      toast({
                        title: "Uspesno",
                        description: "Poslat vam je link za prijavu na email",
                        status: "success",
                        duration: 5000,
                        isClosable: true,
                      });
                    }
                  })
                  .catch((err) => {
                    console.log(err);
                    toast({
                      title: "Greska",
                      description: "Greska prilikom passwordlessn logovanja",
                      status: "error",
                      duration: 5000,
                      isClosable: true,
                    });
                  });
              }}
            >
              PasswordLess
            </Button>
          </Flex>
        </Stack>
      </Box>
    </Flex>
  );
};

export const getServerSideProps: GetServerSideProps = async (context) => {
  return { props: { csrfToken: await getCsrfToken(context) } };
};

export default Prijava;

/*

<div>
      <button
        
        Prijava test
      </button>
    </div>

*/

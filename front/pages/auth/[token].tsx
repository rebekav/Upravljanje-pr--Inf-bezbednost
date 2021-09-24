import { useToast } from "@chakra-ui/toast";
import { GetServerSideProps, NextPage } from "next";
import { getCsrfToken } from "next-auth/client";
import { Router, useRouter } from "next/dist/client/router";
import { useEffect } from "react";

interface tokenRedirectPageProps {
  csrfToken: string;
}

const tokenRedirectPage: NextPage<tokenRedirectPageProps> = (props) => {
  const router = useRouter();
  const { token } = router.query;
  const toast = useToast();
  useEffect(() => {
    fetch("/api/auth/callback/passwordless", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        token,
        csrfToken: props.csrfToken,
      }),
    })
      .then((data) => {
        if (new URL(data.url).searchParams.get("error")) {
          toast({
            title: "Greska",
            description: new URL(data.url).searchParams.get("error"),
            status: "error",
            duration: 5000,
            isClosable: true,
          });
        } else {
          toast({
            title: "Uspesno",
            description: "Mozete se vratiti na stranicu za prijavu",
            status: "success",
            duration: 30000,
            isClosable: true,
          });
        }
      })
      .catch((err) => {
        toast({
          title: "Greska",
          description: "Greska prilikom logovanja",
          status: "error",
          duration: 5000,
          isClosable: true,
        });
      });
  }, [token, props.csrfToken]);

  return <div>Prijavljivanje u toku...</div>;
};

export const getServerSideProps: GetServerSideProps = async (context) => {
  return { props: { csrfToken: await getCsrfToken(context) } };
};

export default tokenRedirectPage;

import type { NextPage } from "next";
import { useSession } from "next-auth/client";
import { useRouter } from "next/dist/client/router";
import { Dashboard } from "../../components/layout/Dashboard";

const Home: NextPage = (props) => {
  const [session, loading] = useSession();
  if (loading) return <div>Loading...</div>;
  return (
    <Dashboard>
      <div>Something</div>
    </Dashboard>
  );
};

export default Home;

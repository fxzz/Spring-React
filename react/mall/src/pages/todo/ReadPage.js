import React from "react";
import {
  createSearchParams,
  useNavigate,
  useParams,
  useSearchParams,
} from "react-router-dom";
import ReadComponent from "../../components/todo/ReadComponent";

function ReadPage() {
  const navigate = useNavigate();
  const { tno } = useParams();
  console.log(tno);

  const [queryParams] = useSearchParams();
  const page = queryParams.get("page") ? parseInt(queryParams.get("page")) : 1;
  const size = queryParams.get("size") ? parseInt(queryParams.get("size")) : 10;

  const queryStr = createSearchParams({ page: page, size: size }).toString();

  const moveToModify = (tno) => {
    navigate({ pathname: `/todo/modify/${tno}`, search: queryStr });
  };

  const moveToList = () => {
    navigate({ pathname: "/todo/list", search: queryStr });
  };

  return (
    <div className="font-extrabold w-full bg-white mt-6">
      <ReadComponent tno={tno}></ReadComponent>
    </div>
  );
}

export default ReadPage;

import React from "react";
import { useParams } from "react-router-dom";
import ReadComponent from "../../components/todo/ReadComponent";

function ReadPage() {
  const { tno } = useParams();

  return (
    <div className="font-extrabold w-full bg-white mt-6">
      <ReadComponent tno={tno}></ReadComponent>
    </div>
  );
}

export default ReadPage;

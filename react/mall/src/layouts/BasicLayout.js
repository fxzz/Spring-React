import React from "react";
import BasicMenu from "../components/menus/BasicMenu";

function BasicLayout({ children }) {
  return (
    <>
      <header className="bg-teal-400 p-5">
        <BasicMenu />
      </header>
      <div className="bg-white my-5 w-full flex flex-col space-y-4 md:flex-row md:space-x-4 md:space-y-0">
        <main className="bg-sky-300 md:w-1/3 lg:w-1/4 px-5 py-40">
          {children}
        </main>
        <aside className="bg-green-300 md:w-1/3 lg:w-1/4 px-5 py-40">
          <h1 className="text-2xl md:text-4xl">Sidebar</h1>
        </aside>
      </div>
    </>
  );
}

export default BasicLayout;

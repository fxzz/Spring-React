import { RouterProvider } from "react-router-dom";
import root from "./router/root";
//sfc
function App() {
  return <RouterProvider router={root} />;
}

export default App;

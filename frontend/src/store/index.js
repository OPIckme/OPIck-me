import { createStore } from "vuex";
import { auth } from "./auth.module";
// import { script } from "./script";
const store = createStore({
  modules: {
    auth,
    // script,
  },
});
export default store;
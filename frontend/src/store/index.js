import { createStore } from "vuex";
import { auth } from "./auth.module";
import { script } from "./script";
import { feedback } from "./feedback";
const store = createStore({
  modules: {
    auth,
    script,
    feedback,
  },
});
export default store;
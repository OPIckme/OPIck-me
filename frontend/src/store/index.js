import { createStore } from "vuex";
import { auth } from "./auth.module";
import { script } from "./script";
import { feedback } from "./feedback";
import { consult } from "./consult";
const store = createStore({
  modules: {
    auth,
    script,
    feedback,
    consult
  },
});
export default store;
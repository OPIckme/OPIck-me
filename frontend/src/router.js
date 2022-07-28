import { createWebHistory, createRouter } from "vue-router";
import MainPageUnLoginView from "./views/MainPageUnLoginView.vue";
import MainPage from "./views/MainPageView.vue";
import Login from "./components/Login.vue";
// import Logout from "./components/Logout.vue";
import Register from "./components/Register.vue";
import Feedback from "./components/Feedback.vue";

const BoardUser = () => import("./components/BoardUser.vue")
const routes = [
  {
    path: "/",
    name: "mainpageunlogin",
    component: MainPageUnLoginView,
  },
  {
    path: "/mainpageunlogin",
    component: MainPageUnLoginView,
  },
  {
    path: "/login",
    component: Login,
  },
  // {
  //   path: "/logout",
  //   component: Logout,
  // },
  {
    path: "/register",
    component: Register,
  },
  {
    path: "/mainpage",
    name: "mainpage",
    // lazy-loaded
    component: MainPage,
  },
  {
    path: "/feedback",
    name: "feedback",
    // lazy-loaded
    component: Feedback,
  },
  {
    path: "/user",
    name: "user",
    // lazy-loaded
    component: BoardUser,
  },
];
const router = createRouter({
  history: createWebHistory(),
  routes,
});
export default router;
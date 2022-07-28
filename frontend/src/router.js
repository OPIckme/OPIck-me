import { createWebHistory, createRouter } from "vue-router";
import MainPageUnLoginView from "./views/MainPageUnLoginView.vue";
import MainPage from "./views/MainPageView.vue";
import Login from "./components/Login.vue";
import Register from "./components/Register.vue";
// lazy-loaded
// const Profile = () => import("./components/Profile.vue")
// const BoardAdmin = () => import("./components/BoardAdmin.vue")
// const BoardModerator = () => import("./components/BoardModerator.vue")
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
  // {
  //   path: "/admin",
  //   name: "admin",
  //   // lazy-loaded
  //   component: BoardAdmin,
  // },
  // {
  //   path: "/mod",
  //   name: "moderator",
  //   // lazy-loaded
  //   component: BoardModerator,
  // },
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
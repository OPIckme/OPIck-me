import { createWebHistory, createRouter } from "vue-router";
import MainPageUnLoginView from "./views/MainPageUnLoginView.vue";
import Mainpage from "./views/MainPageView.vue";
import Login from "./components/Login.vue";
import ScriptDetail from "./components/ScriptDetail.vue";
import Register from "./components/Register.vue";
import Feedback from "./components/Feedback.vue";
import FeedbackDetail from "./components/FeedbackDetail.vue";
import WebrtcStudentView from "./views/WebrtcStudentView.vue";
import ScriptEdit from "./components/ScriptEdit.vue";

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
    component: Mainpage,
  },
   {
    path: "/script/:scriptId",
    name:"scriptdetail",
    component: ScriptDetail,
  },
  {
    path: "/script/edit/:scriptId",
    name:"scriptedit",
    component: ScriptEdit,
  },
  {
    path: "/feedback",
    name: "feedback",
    // lazy-loaded
    component: Feedback,
  },
  {
    path: "/feedback/:feedbackId",
    name:"feedbackdetail",
    component: FeedbackDetail,
  },
  {
    path: "/webrtcstudent",
    name: "webrtcstudent",
    // lazy-loaded
    component: WebrtcStudentView,
    props: true
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
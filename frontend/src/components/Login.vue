<template>
<!-- Navbar -->
<nav class="navbar sticky-top" style="background-color:#052A99; height: 80px;">
  <router-link class="position-absolute top-50 start-50 translate-middle  " to="/">
    <img src="../assets/logo.png" alt="" style="width:120px">
  </router-link>
</nav>


<div :class="container" id="container">
  <!-- 회원가입 -->
	<div class="form-container sign-up-container">
    <Form @submit="handleRegister" :validation-schema="schemaSignup">
      <h1>Sign Up</h1>
      <div v-if="!successful">
      <div class="form-group">
        <Field name="username" type="text" placeholder="ID" class="form-control" maxlength="16"/>
        <ErrorMessage name="username" class="error-feedback" />
      </div>
      <div class="form-group">
        <Field name="password" type="password" placeholder="Password" class="form-control" maxlength="16"/>
        <ErrorMessage name="password" class="error-feedback" />
      </div>
      <div class="form-group">
        <Field name="email" type="email" placeholder="Email" class="form-control" />
        <ErrorMessage name="email" class="error-feedback" />
      </div>
      <div class="form-group">
        <Field name="nickname" type="nickname" placeholder="Nickname" class="form-control" />
        <ErrorMessage name="nickname" class="error-feedback" />
      </div>
      <div class="form-group">
        <div v-if="messageSignup">
        {{ messageSignup }}
        </div>
        <button class="btn" :disabled="loading">
          <span
            v-show="loading"
            class="spinner-border spinner-border-sm"
          ></span>
          Sign Up
        </button>
      </div>
    </div>
    </Form>
	</div>
  <!-- 로그인 -->
	<div class="form-container sign-in-container">
    <Form @submit="handleLogin" :validation-schema="schemaLogin">
      <h1>Sign In</h1>
      <div class="form-group">
        <Field name="username" type="text" placeholder="Username" class="form-control" maxlength="16"/>
        <ErrorMessage name="username"  class="error-feedback" />
      </div>
      <div class="form-group">
        <Field name="password" type="password" placeholder="Password" class="form-control" maxlength="16"/>
        <ErrorMessage name="password" class="error-feedback" />
      </div>
      <div v-if="messageLogin">
        {{ messageLogin }}
      </div>
      <button class="btn" :disabled="loading">
        <span
          v-show="loading"
          class="spinner-border spinner-border-sm"
        ></span>
        <span>Sign In</span>
      </button>
    </Form>
	</div>

	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-left">
				<h1>Welcome Back!</h1>
				<p>To keep connected with us please login with your personal info</p>
				<button class="ghost" id="signIn" @click="signInButton">Sign In</button>
			</div>
			<div class="overlay-panel overlay-right">
				<h1>Hello, Friend!</h1>
				<p>Enter your personal details and start journey with us</p>
				<button class="ghost" id="signUp" @click="signUpButton">Sign Up</button>
			</div>
		</div>
	</div>
</div>


</template>
<script>
import { Form, Field, ErrorMessage } from "vee-validate";
import * as yup from "yup";

export default {
  name: "Login",
  components: {
    Form,
    Field,
    ErrorMessage,
},
  data() {
    const schemaLogin = yup.object().shape({
      username: yup
        .string()
        .required("Username is required!")
        .min(4, "Must be at least 4 characters!")
        .max(16, "Must be maximum 16 characters!"),
      password: yup
        .string()
        .required("Password is required!")
        .min(9, "Must be at least 9 characters!")
        .max(16, "Must be maximum 16 characters!"),
    });
    const schemaSignup = yup.object().shape({
      username: yup
        .string()
        .required("Username is required!")
        .min(4, "Must be at least 4 characters!")
        .max(16, "Must be maximum 16 characters!"),
      email: yup
        .string()
        .required("Email is required!")
        .email("Email is invalid!")
        .max(50, "Must be maximum 50 characters!"),
      password: yup
        .string()
        .required("Password is required!")
        .min(9, "Must be at least 9 characters!")
        .max(16, "Must be maximum 16 characters!"),
    });
    return {
      // Sing Up
      successful: false,
      messageSignup: "",
      schemaSignup,
      // Login
      loading: false,
      messageLogin: "",
      schemaLogin,
      // css
      container:"container",
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push("/mainpage");
    }
  },
  methods: {
    signUpButton(){
      this.container = "container right-panel-active"
    },
    signInButton(){
      this.container = "container"
    },
    handleLogin(user) {
      this.loading = true;
      this.$store.dispatch("auth/login", user).then(
        () => {
          this.$router.push("/mainpage");
        },
        (error) => {
          this.loading = false;
          this.messageLogin =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
        }
      );
    },
    handleRegister(user) {
      this.messageSignup = "";
      this.successful = false;
      this.loading = true;
      this.$store.dispatch("auth/register", user).then(
        (data) => {
          this.messageSignup = data.message;
          this.successful = true;
          this.loading = false;
        },
        (error) => {
          this.messageSignup =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
          this.successful = false;
          this.loading = false;
        }
      );
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');
.form-group{
  width: 350px;
}

h1 {
  font-weight: bold;
  margin-bottom: 1rem;
}

p {
  font-size: 14px;
  font-weight: 100;
  line-height: 20px;
  letter-spacing: 0.5px;
  margin: 20px 0 30px;
}

span {
  font-size: 12px;
}

button {
  border-radius: 20px;
  border: 1px solid #F2CB05;
  background-color: #F2CB05;
  color: #FFFFFF;
  font-size: 12px;
  font-weight: bold;
  padding: 12px 45px;
  letter-spacing: 1px;
  text-transform: uppercase;
  transition: transform 80ms ease-in;
}

button:active {
  transform: scale(0.95);
}

button:focus {
  outline: none;
}

button.ghost {
  background-color: transparent;
  border-color: #FFFFFF;
}

form {
  background-color: #FFFFFF;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 50px;
  height: 100%;
  text-align: center;
}

input {
  background-color: #eee;
  border: none;
  padding: 12px 15px;
  margin: 8px 0;
  width: 100%;
}

.container {
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 14px 28px rgba(0,0,0,0.25), 
      0 10px 10px rgba(0,0,0,0.22);
  position: relative;
  overflow: hidden;
  width: 1296px;
  max-width: 100%;
  min-height: 600px;
  margin-top: 2rem;
}

.form-container {
	position: absolute;
	top: 0;
	height: 100%;
	transition: all 0.6s ease-in-out;
}

.sign-in-container {
	left: 0;
	width: 50%;
	z-index: 2;
}

.container.right-panel-active .sign-in-container {
	transform: translateX(100%);
}

.sign-up-container {
	left: 0;
	width: 50%;
	opacity: 0;
	z-index: 1;
}

.container.right-panel-active .sign-up-container {
	transform: translateX(100%);
	opacity: 1;
	z-index: 5;
	animation: show 0.6s;
}

@keyframes show {
	0%, 49.99% {
		opacity: 0;
		z-index: 1;
	}
	
	50%, 100% {
		opacity: 1;
		z-index: 5;
	}
}

.overlay-container {
	position: absolute;
	top: 0;
	left: 50%;
	width: 50%;
	height: 100%;
	overflow: hidden;
	transition: transform 0.6s ease-in-out;
	z-index: 100;
}

.container.right-panel-active .overlay-container{
	transform: translateX(-100%);
}

.overlay {
	background: #fed92d  ;
	background: -webkit-linear-gradient(to right, #fed92d       , #e6bd00      );
	background: linear-gradient(to right, #fed92d        , #e6bd00     );
	background-repeat: no-repeat;
	background-size: cover;
	background-position: 0 0;
	color: #FFFFFF;
	position: relative;
	left: -100%;
	height: 100%;
	width: 200%;
  transform: translateX(0);
	transition: transform 0.6s ease-in-out;
}

.container.right-panel-active .overlay {
  transform: translateX(50%);
}

.overlay-panel {
	position: absolute;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
	padding: 0 40px;
	text-align: center;
	top: 0;
	height: 100%;
	width: 50%;
	transform: translateX(0);
	transition: transform 0.6s ease-in-out;
}

.overlay-left {
	transform: translateX(-20%);
}

.container.right-panel-active .overlay-left {
	transform: translateX(0);
}

.overlay-right {
	right: 0;
	transform: translateX(0);
}

.container.right-panel-active .overlay-right {
	transform: translateX(20%);
}

</style>
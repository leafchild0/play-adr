import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import Buefy from "buefy";
import wysiwyg from "vue-wysiwyg";
import "@mdi/font/css/materialdesignicons.css";
import "buefy/dist/buefy.css";

Vue.config.productionTip = false;
Vue.use(Buefy);
Vue.use(wysiwyg, {});

new Vue({
  router,
  render: (h) => h(App),
}).$mount("#app");

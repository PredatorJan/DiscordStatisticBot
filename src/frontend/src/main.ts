import { createApp } from 'vue'
import App from './App.vue'

import './assets/globalStyle.css'

import { library } from '@fortawesome/fontawesome-svg-core'
import { faAngleDown, faCircleChevronRight, faBars, faHouse, faDesktop, faGear, faWrench, faRotate, faCircleLeft, faCircleRight, faUser, faUsers, faServer, faCircleHalfStroke } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import router from './router'

library.add(faAngleDown, faCircleChevronRight, faBars, faHouse, faDesktop, faGear, faWrench, faRotate, faCircleLeft, faCircleRight, faUser, faUsers, faServer, faCircleHalfStroke)

createApp(App).use(router).component("font-awesome-icon", FontAwesomeIcon).mount("#app");

import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

import HomeView from '../views/HomeView.vue'
import SetupView from '../views/SetupView.vue'
import UserMonitorView from '../views/monitoring/UserMonitorView.vue'
import GuildMonitorView from '../views/monitoring/GuildMonitorView.vue'
import GuildSettingsView from '../views/settings/GuildSettingsView.vue'
import ProfileSettingsView from '../views/settings/ProfileSettingsView.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/setup',
    name: 'setup',
    component: SetupView
  },
  {
    path: '/monitoring/users',
    name: 'userMonitor',
    component: UserMonitorView
  },
  {
    path: '/monitoring/guilds',
    name: 'guildMonitor',
    component: GuildMonitorView
  },
  {
    path: '/settings/profile',
    name: 'profileSettings',
    component: ProfileSettingsView
  },
  {
    path: '/settings/guilds',
    name: 'guildSettings',
    component: GuildSettingsView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
